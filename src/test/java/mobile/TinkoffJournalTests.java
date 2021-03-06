package mobile;

import io.appium.java_client.AppiumBy;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static mobile.SeleniumSelectors.*;

@DisplayName("Mobile-автотесты android приложения Тинькофф Журнал")
public class TinkoffJournalTests extends TestBaseMobile {

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем приветственный экран")
    void startupTest() {
        step("Проверяем заголовок приветствия", () ->
                $(AppiumBy.className("android.widget.TextView")).shouldHave(text("Привет!")));
        step("Нажимаем на кнопку \"Вперед\"", () ->
                $(byResourceId("SLIDE_STARTUP__BUTTON_POSITIVE")).click());
        sleep(1000);
        step("Проверяем содержимое второй страницы", () -> {
            $$(AppiumBy.className("android.widget.TextView")).get(0)
                    .shouldHave(text("Любим учиться и учить"));
            $$(AppiumBy.className("android.widget.TextView")).get(1)
                    .shouldHave(text("Статьи о деньгах, здоровье и путешествиях"));
        });
        step("Нажимаем на кнопку \"Чему можно научиться?\"", () ->
                $(byResourceId("SLIDE_STUDYING__BUTTON_POSITIVE")).click());
        step("Проверяем заголовок вкладки", () ->
                $(byResourceId("VIEW_COURSES_HEADER")).$("android.view.View").shouldHave(text("Учебник")));
    }

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем версию приложения")
    void appVersionTest() {
        SkipStartScreen.toMagazine();
        step("Нажимаем на иконку профиля", () ->
                $(byResourceId("VIEW_COURSES_HEADER__BUTTON_PROFILE")).click());
        step("Нажимаем на пункт \"О приложении\"", () ->
                $(byResourceId("SCREEN_PROFILE__BUTTON_ABOUT")).click());
        step("Проверяем версию приложения", () ->
                $(byTextAndReturn("Версия приложения")).$("android.widget.TextView", 1)
                        .shouldHave(text("2.1.14")));
    }

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем поиск")
    void searchTest() {
        SkipStartScreen.toTextbook();
        step("Нажимаем на поиск", () ->
                $(AppiumBy.accessibilityId("Поиск")).click());
        step("Выбираем первый вариант подсказки", () ->
                $(AppiumBy.xpath("//android.widget.EditText[@content-desc=\"Введите текст для поиска\"]/.." +
                        "/android.view.ViewGroup[1]")).click());
        step("Проверяем, что существуют результаты поиска", () ->
                $(AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]"))
                        .should(exist));
    }

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Проверяем курсы в учебнике")
    void textbookTest() {
        SkipStartScreen.toTextbook();
        step("Выбираем первый курс", () ->
                $(byResourceId("VIEW_COURSES_HEADER")).click());
        step("Нажимаем на кнопку \"Начать учиться\"", () ->
                $(byText("Начать учиться")).click());
        step("Вводим данные в поле", () ->
                $(byResourceId("SCREEN_SING_IN_LOGIN__INPUT")).setValue("12345"));
        step("Кнопка \"Войти\" активна", () ->
                $(byResourceId("SCREEN_SING_IN_LOGIN__BUTTON_SEND"))
                        .shouldHave(attribute("enabled", "true")));
    }

    @Test
    @Tag("mobile")
    @Owner("KELONMYOSA")
    @DisplayName("Провоеряем наличее статей в журнале")
    void magazineTest() {
        SkipStartScreen.toMagazine();
        step("Проверяем, что существуют статьи", () ->
                $(AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]"))
                        .should(exist));
        step("Проверяем, что в первой статье есть контент", () -> {
            $(AppiumBy.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")).click();
            sleep(1000);
            $(AppiumBy.className("android.widget.TextView")).shouldBe(visible);
        });
    }
}
