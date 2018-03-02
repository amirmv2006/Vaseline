package ir.amv.os.vaseline.basics.spring.core.shared.util.file;

import com.jcraft.jsch.UserInfo;

/**
 * Created by AMV on 1/10/2016.
 */
public class JSchUserInfo implements UserInfo {

    private String password;

    public JSchUserInfo(String password) {
        this.password = password;
    }

    @Override
    public String getPassphrase() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean promptPassword(String message) {
        return true;
    }

    @Override
    public boolean promptPassphrase(String message) {
        return true;
    }

    @Override
    public boolean promptYesNo(String message) {
        return true;
    }

    @Override
    public void showMessage(String message) {

    }
}
