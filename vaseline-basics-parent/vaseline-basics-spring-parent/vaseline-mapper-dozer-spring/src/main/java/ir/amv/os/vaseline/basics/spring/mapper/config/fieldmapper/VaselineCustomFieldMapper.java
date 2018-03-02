package ir.amv.os.vaseline.basics.spring.mapper.config.fieldmapper;

import org.dozer.CustomFieldMapper;
import org.dozer.classmap.ClassMap;
import org.dozer.fieldmap.FieldMap;

import java.util.List;

/**
 * Created by AMV on 2/14/2016.
 */
public class VaselineCustomFieldMapper implements CustomFieldMapper {

    private List<CustomFieldMapper> customFieldMappers;

    public VaselineCustomFieldMapper(List<CustomFieldMapper> customFieldMappers) {
        this.customFieldMappers = customFieldMappers;
    }

    @Override
    public boolean mapField(Object source, Object destination, Object sourceFieldValue, ClassMap classMap, FieldMap fieldMapping) {
        for (CustomFieldMapper customFieldMapper : customFieldMappers) {
            boolean mapField = customFieldMapper.mapField(source, destination, sourceFieldValue, classMap, fieldMapping);
            if (mapField) {
                return true;
            }
        }
        return false;
    }
}
