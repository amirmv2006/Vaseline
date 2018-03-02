package ir.amv.os.vaseline.data.apis.search.advanced.maven.plugin.fqnconverter.impl;

/**
 * Created by amv on 12/17/16.
 */
public class FqnEntityToISOConverterImpl
        extends BaseFqnConverterImpl {

    public FqnEntityToISOConverterImpl(String entBasePackage, String isoBasePackage) {
        super("I", "SearchObject", "Entity", entBasePackage, isoBasePackage);
    }
}
