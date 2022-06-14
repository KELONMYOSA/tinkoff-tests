package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.AllureAttachments;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static web.TinkoffPageComponents.*;
import static web.SubscriptionPriceCounter.*;
import static web.CurrentAndNextDate.*;

import java.net.URL;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("UI-автотесты сайта компании Тинькофф")
public class TinkoffWebTests extends TestBaseWeb {

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка наличия логотипа в хедере")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void logoVisibleTest() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () -> open("https://www.tinkoff.ru"));
        step("Проверяем наличие изображения логотипа", () -> {
            $(".ab2LmdBh1").should(exist);
            String urlString = $(".ab2LmdBh1").getCssValue("background");
            URL logoUrl = new URL(urlString.substring(urlString.indexOf("(\"") + 2, urlString.indexOf("\")")));
            AllureAttachments.addSvgFromUrl("Logo SVG file", logoUrl);
        });
        step("Проверяем видимость изображения логотипа", () -> $(".ab2LmdBh1").shouldBe(Condition.visible));
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка некорректного входа в личный кабинет")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void incorrectLoginTest() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () -> open("https://www.tinkoff.ru"));
        step("Нажимаем 'Войти'", () -> $(".bb3jOCKHi").click());
        step("Вводим некорректный номер телефона", () -> {
            $("#phoneNumber").setValue("12345");
            $("#submit-button").click();
        });
        step("Проверяем сообщение, что телефон некорректен", () -> $("[automation-id=server-error]")
                .shouldHave(text("Некорректный номер телефона")));
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверка формы оформления кредитной карты")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void creditCardFormTest() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () -> open("https://www.tinkoff.ru"));
        step("Нажимаем в меню на пункт 'Кредитные карты'", () -> $(byText("Кредитные карты")).click());
        step("Нажимаем 'Оформить карту'", () -> $(byText("Оформить карту")).click());
        step("Изменяем кредитный лимит", () -> changeCreditLimit(".bb2wUe_y6",
                "[data-qa-type=\"uikit/inlineInput.input\"]", "100000"));
        step("Выбираем, для чего нужна карта", () -> creditCardPurpose("Покупки", "Снятие наличных"));
        step("Вводим ФИО", () -> inputValueInField("[data-qa-type=\"uikit/dropdown\"]",
                "[name=fio]", "Иванов Иван Иванович"));
        step("Вводим номер телефона", () -> inputValueInField("[data-qa-type=\"uikit/inputPhone\"]",
                "[name=phone_mobile]", "9991234567"));
        step("Вводим дату рождения", () -> $("[name=birthdate]").setValue("01012000"));
        step("Нажимаем 'Далее'", () -> $(".ab2AEBI-e").click());
        step("Проверяем, что перешли на следующую страницу", () -> $(".Db39zw2m2")
                .shouldHave(text("Шаг 2 из 4")));
    }

    private int totalPrice;

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем, что стоимость выбранных подписок совпадает с итоговой")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru")
    void subscriptionCheckboxTest() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () -> open("https://www.tinkoff.ru"));
        step("Нажимаем в меню на пункт 'Подписка'", () -> $(byText("Подписка")).click());
        step("Выбираем все пункты подписки и суммируем их стоимость", () ->
                totalPrice = selectAndCount("[data-qa-type=\"uikit/checkboxBlock\"]", ".gb9tYq3d5", ".db1FZb0Vj"));
        step("Проверяем, что стоимость равна итоговой", () -> {
            int price = Integer.parseInt($(".eb3GusWDk").getOwnText().split(" ")[0]);
            assertThat(price).isEqualTo(totalPrice);
        });
    }

    @Test
    @Tag("web")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем поиск авиабилетов")
    @Link(value = "Testing URL", url = "https://www.tinkoff.ru/travel/flights")
    void flightsSearchTest() {
        step("Открываем страницу 'https://www.tinkoff.ru/travel/flights'",
                () -> open("https://www.tinkoff.ru/travel/flights"));
        step("Выбираем 'Откуда'", () ->
                destinationSelection("[aria-labelledby=\"Input_Откуда\"]", "Санкт-Петербург"));
        step("Выбираем 'Куда'", () ->
                destinationSelection("[aria-labelledby=\"Input_Куда\"]", "Москва"));
        step("Выбираем даты", () -> {
            $("[data-qa-type=\"uikit/inputBox.inputContainer\"]").$("input").setValue(currentDate());
            $$("[data-qa-type=\"uikit/inputBox.inputContainer\"]").get(1).$("input").setValue(nextDate());
        });
        step("Нажимаем 'Найти'", () -> {
            $(".travelsearchform__c75PbE").click();
            sleep(500);
            Selenide.switchTo().window(1);
        });
        sleep(8000);
        step("Выбираем самый дешевый вариант", () -> $(".Carousel-module__item_TOXJA").$("button").click());
        step("Проверяем, что направление совпадает", () -> $(".TravelPopup__content_Z5B2I").$("h2")
                .shouldHave(text("Санкт-Петербург – Москва")));
    }
}
