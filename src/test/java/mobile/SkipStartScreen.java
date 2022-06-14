package mobile;

import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static mobile.SeleniumSelectors.byResourceId;

public class SkipStartScreen {
    static void toTextbook() {
        step("Пропускаем стартовый экран, переходим в Учебник", () -> {
            $(byResourceId("SLIDE_STARTUP__BUTTON_POSITIVE")).click();
            $(byResourceId("SLIDE_STUDYING__BUTTON_POSITIVE")).click();
        });
    }

    static void toMagazine() {
        step("Пропускаем стартовый экран, переходим в Журнал", () -> {
            $(byResourceId("SLIDE_STARTUP__BUTTON_POSITIVE")).click();
            $(byResourceId("SLIDE_STUDYING__BUTTON_NEGATIVE")).click();
        });
    }
}
