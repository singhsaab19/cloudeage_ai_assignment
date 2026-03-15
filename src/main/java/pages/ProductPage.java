package pages;

import com.microsoft.playwright.Page;
import utils.WaitHelper;

public class ProductPage {

    private Page page;
    private WaitHelper wait;

    private String quantityDropdown = "#quantity";
    private String addToCartButton = "#add-to-cart-button";

    public ProductPage(Page page) {
        this.page = page;
        this.wait = new WaitHelper(page);
    }

    public boolean isProductPageOpened() {

        wait.waitForVisible(addToCartButton);
        return page.title().contains("Smart Tank");
    }

    public void selectQuantity(String qty) {

        wait.waitForVisible(quantityDropdown);
        page.selectOption(quantityDropdown, qty);
    }

    public void addToCart() {
        wait.waitForVisible(addToCartButton);

        // Explicitly scroll the button into view before clicking
        page.locator(addToCartButton).scrollIntoViewIfNeeded();

        page.click(addToCartButton);
    }
}