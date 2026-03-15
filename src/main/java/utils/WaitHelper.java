package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;

public class WaitHelper {

    private Page page;

    public WaitHelper(Page page) {
        this.page = page;
    }

    public Locator waitForVisible(String selector) {

        page.waitForSelector(selector,
                new Page.WaitForSelectorOptions()
                        .setState(WaitForSelectorState.VISIBLE)
                        .setTimeout(10000));

        return page.locator(selector);
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }
}