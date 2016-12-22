package com.github.amirmv2006.base.classconverter;

import org.jboss.forge.roaster.model.source.JavaSource;

/**
 * Created by amv on 12/17/16.
 */
public interface IBaseClassConverter<S extends JavaSource<S>, D extends JavaSource<D>> {

    D convert(S source);
}
