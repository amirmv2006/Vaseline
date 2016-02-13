package ir.amv.os.vaseline.ws.rest.config.exclude;

import java.util.List;

public interface IExcludeRestServiceFilter {

	List<String> excludedBeanNames();
	List<Class<?>> excludedBeanClasses();
	
}
