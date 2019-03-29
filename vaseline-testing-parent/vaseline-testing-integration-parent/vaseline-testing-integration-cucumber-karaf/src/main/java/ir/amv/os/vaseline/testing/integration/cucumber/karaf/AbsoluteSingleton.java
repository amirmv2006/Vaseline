package ir.amv.os.vaseline.testing.integration.cucumber.karaf;

public class AbsoluteSingleton {

    private static AbsoluteSingleton instance = new AbsoluteSingleton();
    private boolean value;

    public static AbsoluteSingleton getInstance() {
        return instance;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
