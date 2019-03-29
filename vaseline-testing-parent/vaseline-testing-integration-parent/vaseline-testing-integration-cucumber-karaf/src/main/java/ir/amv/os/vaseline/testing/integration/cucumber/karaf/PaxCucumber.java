package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

import cucumber.api.junit.Cucumber;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.ops4j.pax.exam.junit.PaxExam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * This class allows for running Cucumber features tests using PaxExam in-container testing.
 * 
 * When added as a RunWith annotation to a test class
 *	 @RunWith(PaxCucumber.class)
 * this effectively means that 
 *	 @RunWith(PaxCucumber.class) and @RunWith(Cucumber.class)
 * are used as test runners for that class. Cucumber and PaxExam are run 
 * in parallel, and any Cucumber tasks must manually verify that any PaxExam
 * dependencies have been satisfied.
 * 
 * @author sethrylan
 */
public class PaxCucumber extends BlockJUnit4ClassRunner {
 
	private PaxExam paxExamRunner;
	private Cucumber cucumberRunner;
	
	private final ExecutorService executorService = Executors.newCachedThreadPool();
	private static Logger logger = LoggerFactory.getLogger(PaxCucumber.class);

	public PaxCucumber(Class<?> klass) throws InitializationError, Exception {
		super(klass);
		paxExamRunner = new PaxExam(klass);
		cucumberRunner = new Cucumber(klass);		
	}
	
	@Override
	public void run(RunNotifier rn) {
		PaxExamTask paxExamTask = new PaxExamTask(rn, paxExamRunner);
		executorService.submit(paxExamTask);
		
		CucumberTask cucumberTask = new CucumberTask(rn, cucumberRunner);
		executorService.submit(cucumberTask);
		
		executorService.shutdown() ;
		try {
			executorService.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	public class PaxExamTask implements Callable<RunNotifier> {
		private RunNotifier rn;
		private PaxExam paxExamRunner;
		
		public PaxExamTask(RunNotifier rn, PaxExam paxExamRunner) {
			this.rn = rn;
			this.paxExamRunner = paxExamRunner;
		}
		
		@Override
		public RunNotifier call() throws Exception {
			AbsoluteSingleton.getInstance().setValue(true);
			this.paxExamRunner.run(this.rn);
			logger.debug("PaxExamTask finished");
			return null;
		}
	}
	
	public class CucumberTask implements Callable<RunNotifier> {
		private RunNotifier rn;
		private Cucumber cucumberRunner;
		
		public CucumberTask(RunNotifier rn, Cucumber cucumberRunner) {
			this.rn = rn;
			this.cucumberRunner = cucumberRunner;
		}
		
		@Override
		public RunNotifier call() throws Exception {
			this.cucumberRunner.run(this.rn);
			logger.debug("CucumberTask finished");
			AbsoluteSingleton.getInstance().setValue(false);
			return null;
		}
	}
}