package api;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.baseURI;
import static helpers.CustomAllureListener.customAllureTemplate;

public class TinkoffApiTests {

    @BeforeAll
    static void beforeAll() {
        baseURI = "https://www.tinkoff.ru";
        filters(customAllureTemplate());
    }

    @Test
    @Tag("api")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка главной страницы")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void checkMainPage() {
    }
}
