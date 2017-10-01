package ir.amv.os.vaseline.basics.apis.mapper.server.objmapper;

import ir.amv.os.vaseline.basics.apis.mapper.server.exc.VaselineConvertException;

/**
 * Created by AMV on 10/1/2017.
 */
public interface IVaselineObjectMapper {

    <D> D map(Object source, Class<D> destinationClass) throws VaselineConvertException;
    void map(Object source, Object destination) throws VaselineConvertException;
}
