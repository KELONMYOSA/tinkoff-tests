package mobile;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static mobile.SeleniumSelectors.byResourceId;

public class SkipStartScreen {
    public static void toTextbook() {
        step("Пропускаем стартовый экран, переходим в Учебник", () -> {
            $(byResourceId("SLIDE_STARTUP__BUTTON_POSITIVE")).click();
            $(byResourceId("SLIDE_STUDYING__BUTTON_POSITIVE")).click();
        });
    }

    public static void toMagazine() {
        step("Пропускаем стартовый экран, переходим в Учебник", () -> {
            $(byResourceId("SSLIDE_STARTUP__BUTTON_POSITIVE")).click();
            $(byResourceId("SLIDE_STUDYING__BUTTON_NEGATIVE")).click();
        });
    }
}
