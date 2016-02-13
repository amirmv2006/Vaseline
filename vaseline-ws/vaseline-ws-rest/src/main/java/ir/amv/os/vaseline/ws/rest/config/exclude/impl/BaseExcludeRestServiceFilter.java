package ir.amv.os.vaseline.ws.rest.config.exclude.impl;

import ir.amv.os.vaseline.ws.rest.config.exclude.IExcludeRestServiceFilter;

import java.util.List;

public abstract class BaseExcludeRestServiceFilter implements IExcludeRestServiceFilter {

	@Override
	public List<String> excludedBeanNames() {
		return null;
	}

	@Override
	public List<Class<?>> excludedBeanClasses() {
		return null;
	}

}
