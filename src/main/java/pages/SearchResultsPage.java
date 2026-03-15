package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WaitHelper;

public class SearchResultsPage {

    private Page page;
    private WaitHelper wait;

    public SearchResultsPage(Page page) {
        this.page = page;
        this.wait = new WaitHelper(page);
    }

    public boolean isSearchResultDisplayed() {

        wait.waitForVisible("div[data-component-type='s-search-result']");

        return page.locator("div[data-component-type='s-search-result']").count() > 0;
    }

    public void selectPrinter() {
        String printerName = utils.ConfigReader.get("productName");

        Locator printerLink = page.locator("a h2, h2 a").filter(new Locator.FilterOptions().setHasText(printerName)).first();

        // Scroll the item into view to ensure it's interactable
        printerLink.scrollIntoViewIfNeeded();

        System.out.println("Clicking on: " + printerLink.innerText());

        // Use force click in case of invisible overlays
        printerLink.click(new com.microsoft.playwright.Locator.ClickOptions().setForce(true));
    }
}