package ir.amv.os.vaseline.basics.apis.core.shared.util.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

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

    public static String inputStreamToString(final InputStream entityStream) throws IOException {
        StringBuilder textBuilder = new StringBuilder();
        int c = 0;
        try (Reader streamReader = new InputStreamReader(entityStream)) {
            while ((c = streamReader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();

    }
}
