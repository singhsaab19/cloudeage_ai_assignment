package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WaitHelper;

public class CartPage {

    private Page page;
    private WaitHelper wait;

    private String cartSubtotal = "#attach-accessory-cart-subtotal";
    private String confirmationAlert = "#NATC_SMART_WAGON_CONF_MSG_SUCCESS";
    private String alternateCartLink = "#sw-gtc";

    public CartPage(Page page) {
        this.page = page;
        this.wait = new WaitHelper(page);
    }

    public boolean verifySubtotal() {
        page.waitForSelector(cartSubtotal + ", " + confirmationAlert,
                new Page.WaitForSelectorOptions().setTimeout(15000));

        return page.locator(cartSubtotal).isVisible() || page.locator(confirmationAlert).isVisible();
    }

    public void openCart() {
        Locator sideDrawerBtn = page.locator("text=Go to Cart");
        Locator confirmationPageBtn = page.locator(alternateCartLink);

        if (sideDrawerBtn.isVisible()) {
            sideDrawerBtn.click();
        } else if (confirmationPageBtn.isVisible()) {
            confirmationPageBtn.click();
        } else {
            page.click("#nav-cart");
        }
        page.waitForLoadState();
    }

    public boolean verifyCartItem(String name) {
        return page.locator(".sc-list-item-content")
                .filter(new Locator.FilterOptions().setHasText(name))
                .isVisible();
    }

    public String getQuantity() {
        Locator qtyElement = page.locator(".sc-action-quantity .a-dropdown-prompt, .sc-list-item-content select[name='quantity'], .a-dropdown-prompt, [id^='sc-active-'] > div.sc-list-item-content > div > div:nth-child(2) > div.a-row.sc-action-links > span.sc-action-quantity > span.sc-quantity-stepper > fieldset > div.a-stepper-inner-container > div > div.a-declarative").first();

        qtyElement.waitFor(new com.microsoft.playwright.Locator.WaitForOptions()
                .setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE)
                .setTimeout(20000));

        // Extract the text
        String rawText = qtyElement.innerText().trim();

        String cleanQty = rawText.replaceAll("[^0-9]", "");
        if (!cleanQty.isEmpty()) {
            cleanQty = String.valueOf(cleanQty.charAt(0));
        }

        System.out.println("Final Verified Quantity: " + cleanQty);
        return cleanQty;
    }
}