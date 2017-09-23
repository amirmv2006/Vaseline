package ir.amv.os.vaseline.base.core.api.server.polymorphysm.defimpl;

import ir.amv.os.vaseline.base.core.api.server.polymorphysm.IVaselinePolymorphysmClassHolder;

import java.util.Arrays;
import java.util.List;

public class VaselinePolymorphysmClassHolderImpl implements IVaselinePolymorphysmClassHolder {

    private List<Class<?>> parentClasses;

    public VaselinePolymorphysmClassHolderImpl(Class<?>... parentClasses) {
        this(Arrays.asList(parentClasses));
    }

    public VaselinePolymorphysmClassHolderImpl(List<Class<?>> parentClasses) {
        this.parentClasses = parentClasses;
    }

    public VaselinePolymorphysmClassHolderImpl(VaselinePolymorphysmClassHolderImpl anotherClassHolder, Class<?>... parentClasses) {
        this(anotherClassHolder, Arrays.asList(parentClasses));
    }

    public VaselinePolymorphysmClassHolderImpl(VaselinePolymorphysmClassHolderImpl anotherClassHolder, List<Class<?>> parentClasses) {
        this.parentClasses = parentClasses;
        parentClasses.addAll(anotherClassHolder.parentClasses);
    }

    @Override
    public List<Class<?>> parentClasses() {
        return parentClasses;
    }
}
