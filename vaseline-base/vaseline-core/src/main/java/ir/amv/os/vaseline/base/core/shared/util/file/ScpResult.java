package ir.amv.os.vaseline.base.core.shared.util.file;

import ir.amv.os.vaseline.base.core.shared.util.callback.IBaseCallback;

import java.io.OutputStream;

/**
 * Created by AMV on 1/9/2016.
 */
public class ScpResult {

    private OutputStream outputStream;
    private IBaseCallback<Void, Void> callback;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public IBaseCallback<Void, Void> getCallback() {
        return callback;
    }

    public void setCallback(IBaseCallback<Void, Void> callback) {
        this.callback = callback;
    }
}
