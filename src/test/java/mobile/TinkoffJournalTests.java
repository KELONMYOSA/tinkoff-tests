package mobile;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("Mobile-автотесты android приложения Тинькофф Журнал")
public class TinkoffJournalTests extends TestBaseMobile {

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем содержание стартового экрана")
    void firstLinkTest() {
    }
}
