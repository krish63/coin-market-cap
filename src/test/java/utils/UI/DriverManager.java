package utils.UI;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();
    private static WebDriver driver;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    /**
     * By default to web driver will be Chrome
     * <p>
     * Override it by passing -Dbrowser=firefox to the command line arguments
     *
     * @return webdriver
     */
    private static WebDriver chooseDriver() {
        String preferredDriver = System.getProperty("browser", "chrome");
        boolean headless = System.getProperty("headless", "false").equals("true");

        switch (preferredDriver.toLowerCase()) {
            case "safari":
                return new SafariDriver();
            case "edge":
                return new EdgeDriver();
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                final FirefoxOptions ffOptions = new FirefoxOptions();

                if (headless) {
                    ffOptions.setHeadless(true);
                }
                return new FirefoxDriver(ffOptions);
            default:


            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
            final ChromeOptions chromeOptions = new ChromeOptions();

            if (headless) {
                chromeOptions.addArguments("--headless");
            }

            chromeOptions.addArguments("window-size=1920,1080");
            chromeOptions.addArguments("-incognito");
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("disable-infobars");
            chromeOptions.addArguments("--disable-extensions");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--no-sandbox");

            return new ChromeDriver(chromeOptions);
        }
    }



    public WebDriver getDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        } else {
            return getDefaultDriver();
        }
    }

    public WebDriver getDefaultDriver() {
        if (driverThreadLocal.get() != null) {
            return driverThreadLocal.get();
        }
        driver = chooseDriver();
        driver.manage().window().maximize();
        driverThreadLocal.set(driver);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
        return getDriver();
    }


}
