package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.classconverter;

import org.jboss.forge.roaster.model.source.JavaSource;

/**
 * Created by amv on 12/17/16.
 */
public interface IBaseClassConverter<S extends JavaSource<S>, D extends JavaSource<D>> {

    D convert(S source);
}
