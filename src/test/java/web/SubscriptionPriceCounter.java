package web;

import static com.codeborne.selenide.Selenide.$$;

public class SubscriptionPriceCounter {
    static int selectAndCount(String checkboxGroupLocator, String selectionButtonLocator, String priceLocator) {
        int subCount = $$("[data-qa-type=\"uikit/checkboxBlock\"]").size();
        int totalPrice = 0;
        int price;
        for (int i = 0; i < subCount; i++) {
            if (i != 0) {
                $$(checkboxGroupLocator).get(i).$(selectionButtonLocator).click();
            }
            price = Integer.parseInt($$(checkboxGroupLocator).get(i).
                    $(priceLocator).getOwnText().split("\n")[0]);
            totalPrice = totalPrice + price;
        }
        return totalPrice;
    }
}
