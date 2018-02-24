package ir.amv.os.vaseline.business.apis.basic.layer.server.action.metadata;

/**
 * @author Amir
 */
public enum VaselineAllBuinessMetadata implements IBusinessMetadata {
    VASELINE_DB_READ_ONLY(VaselineDbOpMetadata.class, VaselineDbOpMetadata.READ_ONLY.name()),
    VASELINE_DB_READ_WRITE(VaselineDbOpMetadata.class, VaselineDbOpMetadata.WRITE.name());

    private Class<? extends IBusinessMetadata> metadataClass;
    private String metadataMetadata;

    VaselineAllBuinessMetadata(final Class<? extends IBusinessMetadata> metadataClass, String metadataMetadata) {
        this.metadataClass = metadataClass;
        this.metadataMetadata = metadataMetadata;
    }

    public Class<? extends IBusinessMetadata> getMetadataClass() {
        return metadataClass;
    }

    public String getMetadataMetadata() {
        return metadataMetadata;
    }
}
