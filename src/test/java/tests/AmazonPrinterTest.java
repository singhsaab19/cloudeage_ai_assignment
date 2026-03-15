package tests;

import base.BaseTest;
import com.microsoft.playwright.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.*;
import utils.ConfigReader;

import java.nio.file.Paths;

public class AmazonPrinterTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(AmazonPrinterTest.class);

    HomePage homePage;
    SearchResultsPage resultsPage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeMethod
    public void init() {

        setUp();

        homePage = new HomePage(page);
        resultsPage = new SearchResultsPage(page);
        productPage = new ProductPage(page);
        cartPage = new CartPage(page);
    }

    @Test
    public void verifyPrinterPurchaseFlow() {

        String product = ConfigReader.get("searchProduct");
        String printer = ConfigReader.get("productName");
        String qty = ConfigReader.get("quantity");

        log.info("Searching product {}", product);

        homePage.searchProduct(product);

        Assert.assertTrue(resultsPage.isSearchResultDisplayed(),
                "Search results not displayed");

        Page newPage = page.waitForPopup(() -> {
            resultsPage.selectPrinter();
        });

        this.page = newPage;
        this.productPage = new ProductPage(page);
        this.cartPage = new CartPage(page);

        page.waitForLoadState(com.microsoft.playwright.options.LoadState.LOAD);

        Assert.assertTrue(productPage.isProductPageOpened(), "Product page did not open");

        productPage.selectQuantity(qty);

        productPage.addToCart();

        Assert.assertTrue(cartPage.verifySubtotal(),
                "Subtotal not visible");

        // 9. Click "Go to Cart"
        cartPage.openCart();

        page.waitForSelector("#activeCartViewForm", new Page.WaitForSelectorOptions().setTimeout(20000));

        // 11. Verify items and quantity
        Assert.assertTrue(cartPage.verifyCartItem(printer), "Incorrect item name in cart");
        Assert.assertEquals(cartPage.getQuantity(), qty, "Quantity mismatch in cart");
    }

    @AfterMethod
    public void close(ITestResult result) {

        if (ITestResult.FAILURE == result.getStatus()) {

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("screenshots/" + result.getName() + ".png")));
        }

        tearDown();
    }
}