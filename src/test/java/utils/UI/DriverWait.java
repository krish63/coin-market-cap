package utils.UI;

import utils.UI.expectedConditions.ClickabilityOfElement;
import utils.UI.expectedConditions.VisibilityOfElement;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DriverWait {


    private final DriverManager driverManager;

    public DriverWait(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public void waitForElementToLoad(WebElement element) throws NoSuchFieldException {
        waitForElementVisible(element);
        waitForElementClickable(element);
    }


    /**
     * wait for element visible by element
     */
    private void waitForElementVisible(WebElement element) {
        try {
            waitLong().until(new VisibilityOfElement(element));
        } catch (Exception ignored) {
        }
    }


    /**
     * wait for element clickable by element
     */
    private void waitForElementClickable(WebElement element) throws NoSuchFieldException {
        try {
            new ClickabilityOfElement(element);
        } catch (Exception t) {
            throw new NoSuchFieldException("could not interact with the element " + element);
        }
    }


    public Wait<WebDriver> waitLong() {
        return new FluentWait<>(driverManager.getDriver()).withTimeout(Duration.ofSeconds(Constants.timeoutLong)).pollingEvery(Duration.ofMillis(Constants.pollingLong)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

    public Wait<WebDriver> waitShort() {
        return new FluentWait<>(driverManager.getDriver()).withTimeout(Duration.ofSeconds(Constants.timeoutShort)).pollingEvery(Duration.ofMillis(Constants.pollingShort)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
    }

}
