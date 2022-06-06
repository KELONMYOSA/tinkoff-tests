package helpers;

import com.codeborne.selenide.WebDriverRunner;
import config.Project;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class WebDriverUtils {
    public static final Logger LOGGER = LoggerFactory.getLogger(WebDriverUtils.class);


    public static String getSessionId() {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static byte[] getScreenshotAsBytes() {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    public static String getPageSource() {
        return WebDriverRunner.source();
    }

    public static URL getVideoUrl(String sessionId) {
        String videoUrl = Project.config.videoStorage() + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            LOGGER.warn("[ALLURE VIDEO ATTACHMENT ERROR] Wrong test video url, {}", videoUrl);
            e.printStackTrace();
        }
        return null;
    }

    public static String getConsoleLogs() {
        return String.join("\n", Selenide.getWebDriverLogs(BROWSER));
    }
}
