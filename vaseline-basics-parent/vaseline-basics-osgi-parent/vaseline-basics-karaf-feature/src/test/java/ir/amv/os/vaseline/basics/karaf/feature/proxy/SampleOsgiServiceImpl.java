package ir.amv.os.vaseline.basics.karaf.feature.proxy;

public class SampleOsgiServiceImpl implements ISampleOsgiService {
    @Override
    public String concat(String first, String tail) {
        return first + tail;
    }
}
