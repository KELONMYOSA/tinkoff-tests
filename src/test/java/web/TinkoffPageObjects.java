package web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import helpers.AllureAttachments;

import java.net.URL;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static web.CurrentAndNextDate.currentDate;
import static web.CurrentAndNextDate.nextDate;
import static web.SubscriptionPriceCounter.selectAndCount;
import static web.TinkoffPageComponents.*;

public class TinkoffPageObjects {

    TinkoffPageObjects openPage() {
        step("Открываем страницу 'https://www.tinkoff.ru'", () ->
                open("https://www.tinkoff.ru"));

        return this;
    }

    TinkoffPageObjects checkLogo() {
        step("Проверяем наличие изображения логотипа", () -> {
            $(".ab2EQgLPq").should(exist);
            String urlString = $(".ab2EQgLPq").getCssValue("background");
            URL logoUrl = new URL(urlString.substring(urlString.indexOf("(\"") + 2, urlString.indexOf("\")")));
            AllureAttachments.addSvgFromUrl("Logo SVG file", logoUrl);
        });

        return this;
    }

    void logoShouldBeVisible() {
        step("Проверяем видимость изображения логотипа", () ->
                $(".ab2EQgLPq").shouldBe(Condition.visible));
    }

    TinkoffPageObjects clickLogin() {
        step("Нажимаем 'Войти'", () -> $(".abNhhL3Ul").click());

        return this;
    }

    TinkoffPageObjects inputPhone() {
        step("Вводим некорректный номер телефона", () -> {
            $("#phoneNumber").setValue("12345");
            $("#submit-button").click();
        });

        return this;
    }

    void phoneShouldBeIncorrect() {
        step("Проверяем сообщение, что телефон некорректен", () ->
                $("[automation-id=server-error]").shouldHave(text("Введён неверный номер телефона")));
    }

    TinkoffPageObjects clickCreditCards() {
        step("Нажимаем в меню на пункт 'Кредитные карты'", () ->
                $(byText("Кредитные карты")).click());

        return this;
    }

    TinkoffPageObjects clickOrderCard() {
        step("Нажимаем 'Оформить карту'", () ->
                $(byText("Оформить карту")).click());

        return this;
    }

    TinkoffPageObjects changeLimit() {
        step("Изменяем кредитный лимит", () -> changeCreditLimit(".ab2QATGUx",
                "[data-qa-type=\"uikit/inlineInput.input\"]", "100000"));

        return this;
    }

    TinkoffPageObjects selectPurpose() {
        step("Выбираем, для чего нужна карта", () ->
                creditCardPurpose("Покупки", "Снятие наличных"));

        return this;
    }

    TinkoffPageObjects inputName() {
        step("Вводим ФИО", () -> inputValueInField("[data-qa-type=\"uikit/dropdown\"]",
                "[name=fio]", "Иванов Иван Иванович"));

        return this;
    }

    TinkoffPageObjects inputPhoneNumber() {
        step("Вводим номер телефона", () -> inputValueInField("[data-qa-type=\"uikit/inputPhone\"]",
                "[name=phone_mobile]", "9991234567"));

        return this;
    }

    TinkoffPageObjects inputBirthday() {
        step("Вводим дату рождения", () -> $("[name=birthdate]").setValue("01012000"));

        return this;
    }

    TinkoffPageObjects clickNext() {
        step("Нажимаем 'Далее'", () -> $(".ab2bpZOnZ").click());
        sleep(500);

        return this;
    }

    void shouldBeSecondPage() {
        step("Проверяем, что перешли на следующую страницу", () -> $(".Db1_txME4")
                .shouldHave(text("Шаг 2 из 4")));
    }

    TinkoffPageObjects clickSubscribe() {
        step("Нажимаем в меню на пункт 'Подписка'", () -> $(byText("Подписка")).click());

        return this;
    }

    private int totalPrice;

    TinkoffPageObjects selectAndCountPrice() {
        step("Выбираем все пункты подписки и суммируем их стоимость", () ->
                totalPrice = selectAndCount("[data-qa-type=\"uikit/checkboxBlock\"]",
                        ".gb9tYq3d5", ".db1FZb0Vj"));

        return this;
    }

    void priceShouldBeCorrect() {
        step("Проверяем, что стоимость равна итоговой", () -> {
            int price = Integer.parseInt($(".eb3GusWDk").getOwnText().split(" ")[0]);
            assertThat(price).isEqualTo(totalPrice);
        });
    }

    TinkoffPageObjects openPageFlights() {
        step("Открываем страницу 'https://www.tinkoff.ru/travel/flights'",
                () -> open("https://www.tinkoff.ru/travel/flights"));

        return this;
    }

    TinkoffPageObjects inputFrom() {
        step("Выбираем 'Откуда'", () ->
                destinationSelection("[aria-labelledby=\"Input_Откуда\"]", "Санкт-Петербург"));

        return this;
    }

    TinkoffPageObjects inputWhere() {
        step("Выбираем 'Куда'", () ->
                destinationSelection("[aria-labelledby=\"Input_Куда\"]", "Москва"));

        return this;
    }

    TinkoffPageObjects selectDates() {
        step("Выбираем даты", () -> {
            $("[data-qa-type=\"uikit/inputBox.inputContainer\"]").$("input").setValue(currentDate());
            $$("[data-qa-type=\"uikit/inputBox.inputContainer\"]").get(1).$("input").setValue(nextDate());
        });

        return this;
    }

    TinkoffPageObjects clickFindTickets() {
        step("Нажимаем 'Найти'", () -> {
            $(".travelsearchform__c75PbE").click();
            sleep(500);
            Selenide.switchTo().window(1);
            sleep(8000);
        });

        return this;
    }

    TinkoffPageObjects clickCheapest() {
        step("Выбираем самый дешевый вариант", () ->
                $(".Carousel-module__item_TOXJA").$("button").click());

        return this;
    }

    void ticketShouldBeCorrect() {
        step("Проверяем, что направление совпадает", () ->
                $(".TravelPopup__content_Z5B2I").$("h2")
                        .shouldHave(text("Санкт-Петербург – Москва")));
    }
}

