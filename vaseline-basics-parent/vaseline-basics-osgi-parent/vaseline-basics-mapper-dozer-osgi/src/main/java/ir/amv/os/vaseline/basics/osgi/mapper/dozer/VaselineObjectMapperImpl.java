package ir.amv.os.vaseline.basics.osgi.mapper.dozer;

import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;
import ir.amv.os.vaseline.basics.apis.mapper.server.objmapper.IVaselineObjectMapper;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.osgi.service.component.annotations.Component;

/**
 * @author Amir
 */
@Component(
        immediate = true,
        service = IVaselineObjectMapper.class
)
public class VaselineObjectMapperImpl
        implements IVaselineObjectMapper {

    private final Mapper mapper;

    public VaselineObjectMapperImpl() {
        mapper = DozerBeanMapperBuilder.buildDefault();
    }

    @Override
    public <D> D map(final Object source, final Class<D> destinationClass) throws VaselineConvertException {
        return mapper.map(source, destinationClass);
    }

    @Override
    public void map(final Object source, final Object destination) throws VaselineConvertException {
        mapper.map(source, destination);
    }
}
