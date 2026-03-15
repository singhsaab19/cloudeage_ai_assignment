package base;

import com.microsoft.playwright.*;
import utils.ConfigReader;

import java.nio.file.Paths;
import java.util.Arrays;

public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected BrowserContext context;

    public void setUp() {
        playwright = Playwright.create();

        // 1. ANALYZE ENVIRONMENT: Check for GitHub Actions specific variable
        String githubActions = System.getenv("GITHUB_ACTIONS");
        boolean isCI = (githubActions != null && githubActions.equalsIgnoreCase("true"));

        // 2. INITIALIZE BROWSER: Smart toggle for Headless mode
        // Locals runs show the UI; CI runs remain silent (headless)
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(isCI)
                        .setArgs(Arrays.asList("--start-maximized"))
        );

        // 3. CONTEXT SETUP: Handle resolutions for different environments
        Browser.NewContextOptions options = new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get("videos/"));

        if (isCI) {
            // Standard HD resolution for CI servers
            options.setViewportSize(1920, 1080);
        } else {
            // null allows Playwright to use the full screen on your local monitor
            options.setViewportSize(null);
        }

        context = browser.newContext(options);
        page = context.newPage();

        // Navigate to the URL from your config.properties
        page.navigate(ConfigReader.get("baseUrl"));
    }

    public void tearDown() {

        if(context != null)
            context.close();

        if(browser != null)
            browser.close();

        if(playwright != null)
            playwright.close();
    }
}