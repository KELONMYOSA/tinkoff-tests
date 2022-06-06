package helpers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class DownloadApkFile {

    public static File getApp() {
        String appPath = "src/test/resources/apk/ТинькоффЖурнал_2.1.14.apk";
        String appUrl = "not in official sources";

        File app = new File(appPath);
        if (!app.exists()) {
            try (InputStream in = new URL(appUrl).openStream()) {
                copyInputStreamToFile(in, app);
            } catch (IOException e) {
                throw new AssertionError("Failed to download apk", e);
            }
        }
        return app;
    }
}
