package web;

import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@DisplayName("UI-автотесты сайта компании Тинькофф")
public class TinkoffWebTests extends TestBaseWeb {
    TinkoffPageObjects tinkoffPageObjects = new TinkoffPageObjects();

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка наличия логотипа в хедере")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void logoVisibleTest() {
        tinkoffPageObjects.openPage()
                .checkLogo()
                .logoShouldBeVisible();
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка некорректного входа в личный кабинет")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void incorrectLoginTest() {
        tinkoffPageObjects.openPage()
                .clickLogin()
                .inputPhone()
                .phoneShouldBeIncorrect();
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка формы оформления кредитной карты")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void creditCardFormTest() {
        tinkoffPageObjects.openPage()
                .clickCreditCards()
                .clickOrderCard()
                .changeLimit()
                .selectPurpose()
                .inputName()
                .inputPhoneNumber()
                .inputBirthday()
                .clickNext()
                .shouldBeSecondPage();
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем, что стоимость выбранных подписок совпадает с итоговой")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void subscriptionCheckboxTest() {
        tinkoffPageObjects.openPage()
                .clickSubscribe()
                .selectAndCountPrice()
                .priceShouldBeCorrect();
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем поиск авиабилетов")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru/travel/flights")
    void flightsSearchTest() {
        tinkoffPageObjects.openPageFlights()
                .inputFrom()
                .inputWhere()
                .selectDates()
                .clickFindTickets()
                .clickCheapest()
                .ticketShouldBeCorrect();
    }
}
