package pages;

import com.microsoft.playwright.Page;

public class HomePage {

    private Page page;

    private String searchBox = "#twotabsearchtextbox";
    private String searchButton = "#nav-search-submit-button";

    public HomePage(Page page) {
        this.page = page;
    }

    public void searchProduct(String product) {

        page.fill(searchBox, product);
        page.click(searchButton);
        page.waitForLoadState();
    }
}