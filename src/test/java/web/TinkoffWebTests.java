package web;

import helpers.WebDriverUtils;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UI-автотесты сайта компании Тинькофф")
public class TinkoffWebTests extends TestBaseWeb {

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Журнал консоли страницы не должен содержать ошибок")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void consoleShouldNotHaveErrorsTest() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () -> open("https://www.tinkoff.ru"));
        step("Журнал консоли не должен содержать текст 'SEVERE'", () -> {
            String consoleLogs = WebDriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }
}
