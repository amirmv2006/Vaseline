package ir.amv.os.vaseline.basics.apis.core.shared.util.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Amir
 */
public class StreamUtils {

    public static void copyStreams(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while (-1 != (n = inputStream.read(buffer))) {
            outputStream.write(buffer, 0, n);
        }
    }
}
