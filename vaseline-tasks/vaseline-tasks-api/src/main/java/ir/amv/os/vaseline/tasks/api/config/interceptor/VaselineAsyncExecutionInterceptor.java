/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.amv.os.vaseline.tasks.api.config.interceptor;

import ir.amv.os.vaseline.tasks.api.server.priority.annot.Prioritorized;
import ir.amv.os.vaseline.tasks.api.server.priority.callable.PriorityCallable;
import ir.amv.os.vaseline.tasks.api.server.priority.prioritorizer.IBaseVaselineAsyncPrioritorizer;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.interceptor.AsyncExecutionAspectSupport;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.Ordered;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.util.ClassUtils;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AOP Alliance {@code MethodInterceptor} that processes method invocations
 * asynchronously, using a given {@link AsyncTaskExecutor}.
 * Typically used with the {@link org.springframework.scheduling.annotation.Async} annotation.
 *
 * <p>In terms of target method signatures, any parameter types are supported.
 * However, the return type is constrained to either {@code void} or
 * {@code java.util.concurrent.Future}. In the latter case, the Future handle
 * returned from the proxy will be an actual asynchronous Future that can be used
 * to track the result of the asynchronous method execution. However, since the
 * target method needs to implement the same signature, it will have to return
 * a temporary Future handle that just passes the return value through
 * (like Spring's {@link org.springframework.scheduling.annotation.AsyncResult}
 * or EJB 3.1's {@code javax.ejb.AsyncResult}).
 *
 * <p>When the return type is {@code java.util.concurrent.Future}, any exception thrown
 * during the execution can be accessed and managed by the caller. With {@code void}
 * return type however, such exceptions cannot be transmitted back. In that case an
 * {@link AsyncUncaughtExceptionHandler} can be registered to process such exceptions.
 *
 * <p>As of Spring 3.1.2 the {@code AnnotationAsyncExecutionInterceptor} subclass is
 * preferred for use due to its support for executor qualification in conjunction with
 * Spring's {@code @Async} annotation.
 *
 * @author Juergen Hoeller
 * @author Chris Beams
 * @author Stephane Nicoll
 * @since 3.0
 * @see org.springframework.scheduling.annotation.Async
 * @see org.springframework.scheduling.annotation.AsyncAnnotationAdvisor
 * @see org.springframework.scheduling.annotation.AnnotationAsyncExecutionInterceptor
 */
public class VaselineAsyncExecutionInterceptor extends AsyncExecutionAspectSupport
		implements MethodInterceptor, Ordered {

    private BeanFactory beanFactory;

    /**
	 * Create a new {@code VaselineAsyncExecutionInterceptor}.
	 * @param defaultExecutor the {@link Executor} (typically a Spring {@link AsyncTaskExecutor}
	 * or {@link java.util.concurrent.ExecutorService}) to delegate to
	 * @param exceptionHandler the {@link AsyncUncaughtExceptionHandler} to use
	 */
	public VaselineAsyncExecutionInterceptor(Executor defaultExecutor, AsyncUncaughtExceptionHandler exceptionHandler) {
		super(defaultExecutor, exceptionHandler);
    }

	/**
	 * Intercept the given method invocation, submit the actual calling of the method to
	 * the correct task executor and return immediately to the caller.
	 * @param invocation the method to intercept and make asynchronous
	 * @return {@link Future} if the original method returns {@code Future}; {@code null}
	 * otherwise.
	 */
	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Class<?> targetClass = (invocation.getThis() != null ? AopUtils.getTargetClass(invocation.getThis()) : null);
        final Object[] arguments = invocation.getArguments();
        final AtomicInteger count = new AtomicInteger(0);
        Prioritorized prioritorized = invocation.getMethod().getAnnotation(Prioritorized.class);
        if (prioritorized != null) {
            Class<? extends IBaseVaselineAsyncPrioritorizer> prioritorizerClass = prioritorized.prioritorizerClass();
            if (!prioritorizerClass.equals(IBaseVaselineAsyncPrioritorizer.class)) {
                IBaseVaselineAsyncPrioritorizer prioritorizer = beanFactory.getBean(prioritorizerClass);
                count.set(prioritorizer.getPriority(invocation));
            } else {
                count.set(prioritorized.value());
            }
        }
        Method specificMethod = ClassUtils.getMostSpecificMethod(invocation.getMethod(), targetClass);
		final Method userDeclaredMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);

		AsyncTaskExecutor executor = determineAsyncExecutor(userDeclaredMethod);
		if (executor == null) {
			throw new IllegalStateException(
					"No executor specified and no default executor set on VaselineAsyncExecutionInterceptor either");
		}

		Callable<Object> task = new PriorityCallable(count.get()) {
			@Override
			public Object call() throws Exception {
				try {
					Object result = invocation.proceed();
					if (result instanceof Future) {
						return ((Future<?>) result).get();
					}
				}
				catch (Throwable ex) {
					handleError(ex, userDeclaredMethod, arguments);
				}
				return null;
			}
		};

		Class<?> returnType = invocation.getMethod().getReturnType();
		if (ListenableFuture.class.isAssignableFrom(returnType)) {
			return ((AsyncListenableTaskExecutor) executor).submitListenable(task);
		}
		else if (Future.class.isAssignableFrom(returnType)) {
			return executor.submit(task);
		}
		else {
			executor.submit(task);
			return null;
		}
	}

	/**
	 * This implementation is a no-op for compatibility in Spring 3.1.2.
	 * Subclasses may override to provide support for extracting qualifier information,
	 * e.g. via an annotation on the given method.
	 * @return always {@code null}
	 * @since 3.1.2
	 * @see #determineAsyncExecutor(Method)
	 */
	@Override
	protected String getExecutorQualifier(Method method) {
		return null;
	}

	@Override
	public int getOrder() {
		return Ordered.HIGHEST_PRECEDENCE;
	}

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        super.setBeanFactory(beanFactory);
    }
}
