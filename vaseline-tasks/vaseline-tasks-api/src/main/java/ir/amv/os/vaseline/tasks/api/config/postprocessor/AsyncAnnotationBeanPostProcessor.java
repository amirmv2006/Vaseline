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

package ir.amv.os.vaseline.tasks.api.config.postprocessor;

import ir.amv.os.vaseline.tasks.api.config.interceptor.VaselineAsyncExecutionInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AbstractAdvisingBeanPostProcessor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncAnnotationAdvisor;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.concurrent.Executor;

/**
 * Bean post-processor that automatically applies asynchronous invocation
 * behavior to any bean that carries the {@link Async} annotation at class or
 * method-level by adding a corresponding {@link AsyncAnnotationAdvisor} to the
 * exposed proxy (either an existing AOP proxy or a newly generated proxy that
 * implements all of the target's interfaces).
 *
 * <p>The {@link TaskExecutor} responsible for the asynchronous execution may
 * be provided as well as the annotation type that indicates a method should be
 * invoked asynchronously. If no annotation type is specified, this post-
 * processor will detect both Spring's {@link Async @Async} annotation as well
 * as the EJB 3.1 {@code javax.ejb.Asynchronous} annotation.
 *
 * <p>For methods having a {@code void} return type, any exception thrown
 * during the asynchronous method invocation cannot be accessed by the
 * caller. An {@link AsyncUncaughtExceptionHandler} can be specified to handle
 * these cases.
 *
 * <p>Note: The underlying async advisor applies before existing advisors by default,
 * in order to switch to async execution as early as possible in the invocation chain.
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Stephane Nicoll
 * @since 3.0
 * @see #setBeforeExistingAdvisors
 */
@SuppressWarnings("serial")
public class AsyncAnnotationBeanPostProcessor extends AbstractAdvisingBeanPostProcessor implements BeanFactoryAware {

	private Class<? extends Annotation> asyncAnnotationType;

	private Executor executor;

	private AsyncUncaughtExceptionHandler exceptionHandler;


    public AsyncAnnotationBeanPostProcessor() {
		setBeforeExistingAdvisors(true);
	}


	public void setAsyncAnnotationType(Class<? extends Annotation> asyncAnnotationType) {
		Assert.notNull(asyncAnnotationType, "'asyncAnnotationType' must not be null");
		this.asyncAnnotationType = asyncAnnotationType;
	}

	/**
	 * Set the {@link Executor} to use when invoking methods asynchronously.
	 */
	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	/**
	 * Set the {@link AsyncUncaughtExceptionHandler} to use to handle uncaught
	 * exceptions thrown by asynchronous method executions.
	 * @since 4.1
	 */
	public void setExceptionHandler(AsyncUncaughtExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) {
        AsyncAnnotationAdvisor advisor =  new AsyncAnnotationAdvisor(this.executor, this.exceptionHandler) {
            @Override
            protected Advice buildAdvice(Executor executor, AsyncUncaughtExceptionHandler exceptionHandler) {
                return new VaselineAsyncExecutionInterceptor(executor, exceptionHandler);
            }
        };
		if (this.asyncAnnotationType != null) {
			advisor.setAsyncAnnotationType(this.asyncAnnotationType);
		}
		advisor.setBeanFactory(beanFactory);
		this.advisor = advisor;
	}

}
