package ir.amv.os.vaseline.basics.spring.core.crosslayers.converter.impl.polymorphism;

import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.List;

public abstract class PolymorphismConverters<P, D> {

    private FromConverter fromConverter;
    private ToConverter toConverter;

    public PolymorphismConverters() {
        fromConverter = new FromConverter();
        toConverter = new ToConverter();
    }

    protected abstract D to(P dest, Class<?> childType);

    protected abstract <C extends P> Class<C> extractType(D source);

    protected abstract <C extends P> C from(D dest, Class<C> childType);

    public List<Converter<?, ?>> converters() {
        return Arrays.asList(fromConverter, toConverter);
    }

    class ToConverter implements Converter<P, D> {

        @Override
        public D convert(P source) {
            Class<?> childType = source.getClass();
            return to(source, childType);
        }
    }

    class FromConverter implements Converter<D, P> {

        @Override
        public P convert(D source) {
            Class<? extends P> childType = extractType(source);
            return from(source, childType);
        }

    }

}
