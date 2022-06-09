package helpers;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class AllureAttachments {

    @Attachment(value = "{attachName}", type = "text/plain")
    private static String addMessage(String attachName, String text) {
        return text;
    }

    public static void addBrowserConsoleLogs() {
        addMessage("Browser console logs", WebDriverUtils.getConsoleLogs());
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] addScreenshotAs(String attachName) {
        return WebDriverUtils.getScreenshotAsBytes();
    }

    @Attachment(value = "Page source", type = "text/html", fileExtension = "html")
    public static String addPageSource() {
        return WebDriverUtils.getPageSource();
    }

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] addPageSourceMobile() { return WebDriverUtils.getPageSourceMobile(); }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String addVideo(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + WebDriverUtils.getVideoUrl(sessionId)
                + "' type='video/mp4'></video></body></html>";
    }

    public static void addSvgFromUrl(String attachName, URL url) {
        try (InputStream is = url.openConnection().getInputStream()) {
            Allure.addAttachment(attachName, "image/svg+xml", is, "svg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
