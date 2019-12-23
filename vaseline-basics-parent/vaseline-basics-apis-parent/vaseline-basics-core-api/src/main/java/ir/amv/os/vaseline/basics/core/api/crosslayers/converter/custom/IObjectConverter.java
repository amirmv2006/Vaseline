package ir.amv.os.vaseline.basics.core.api.crosslayers.converter.custom;

public interface IObjectConverter<S, D> {

    Class<S> getSourceClass();
    Class<D> getDestinationClass();

    D convert(S source);
}
