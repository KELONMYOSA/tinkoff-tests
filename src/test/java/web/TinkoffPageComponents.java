package web;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class TinkoffPageComponents {

    static void changeCreditLimit(String editButtonLocator, String inputLocator, String limitValue) {
        $(editButtonLocator).click();
        $(inputLocator).doubleClick().sendKeys(Keys.BACK_SPACE);
        $(inputLocator).setValue(limitValue).pressEnter();
    }

    static void creditCardPurpose(String textFirs, String textSecond) {
        $(byText(textFirs)).click();
        $(byText(textSecond)).click();
        $("body").sendKeys(Keys.ESCAPE);
    }

    static void inputValueInField(String fieldLocator, String inputLocator, String value) {
        $(fieldLocator).click();
        $(inputLocator).setValue(value);
    }

    static void destinationSelection(String locator, String value) {
        $(locator).doubleClick().sendKeys(Keys.BACK_SPACE);
        $(locator).setValue(value);
        sleep(500);
        $(locator).sendKeys(Keys.ENTER);
    }
}
