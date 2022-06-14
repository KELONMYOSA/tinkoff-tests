package mobile;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class SeleniumSelectors {
    static By byResourceId(String resourceId) {
        return AppiumBy.xpath("//*[@resource-id=\"" + resourceId + "\"]");
    }

    static By byText(String text) {
        return AppiumBy.xpath("//*[@text=\"" + text + "\"]");
    }

    static By byTextAndReturn(String text) {
        return AppiumBy.xpath("//*[@text=\"" + text + "\"]/..");
    }

}
