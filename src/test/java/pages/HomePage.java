package pages;

import annotations.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

@PageObject
public class HomePage extends AbstractPage {
    JavascriptExecutor executor = (JavascriptExecutor) getDriver();

    @FindBy(how = How.XPATH, using = "//table[1]")

    private WebElement cryptoTable;
    @FindBy(how = How.XPATH, using = "/html/body/div[3]/div[2]/div[4]/button")
    private WebElement closePopup;
    @FindBy(how = How.XPATH, using = "//div[@id='__next']/div/div/div[2]/div/div/div[7]/div[2]/div")
    private WebElement dropdown;

    @FindBy(how = How.XPATH, using = "//div[@class='sc-1hxnufv-4 dEpvVp']//span[@class='icon-Slider']")
    private WebElement filters;

    @FindBy(how = How.XPATH, using = "//div[@class='sc-1hxnufv-4 dEpvVp']//span[@class='icon-Slider']")
    private WebElement clearFilters;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='+ 1 More Filter']")
    private WebElement additionalFilters;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Apply Filter']")
    private WebElement applyFilters;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Show results']")
    private WebElement showResults;


    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Price']")
    private WebElement priceButton;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$0']")
    private WebElement priceFromText;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$99,999']")
    private WebElement priceToText;

    @FindBy(how = How.XPATH, using = "//button[normalize-space()='Market Cap']")
    private WebElement marketCap;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$0']")
    private WebElement mcapFromText;

    @FindBy(how = How.XPATH, using = "//input[@placeholder='$999,999,999,999']")
    private WebElement mcapToText;


    @FindBy(how = How.XPATH, using = "//button[normalize-space()='100']")
    private WebElement dropdownValue100;

    public WebElement getCryptoTable() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(cryptoTable);
        return cryptoTable;
    }

    public void clickClosePopup() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(closePopup);
        closePopup.click();
    }

    public void clickDropdown() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(dropdown);
        executor.executeScript("arguments[0].scrollIntoView();", dropdown);
        dropdown.click();
    }

    public void clickFilters() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(filters);
        executor.executeScript("arguments[0].scrollIntoView();", filters);
        filters.click();
    }

    public void clickAddFilters() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(additionalFilters);
        additionalFilters.click();
    }

    public void clickApplyFilters() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(applyFilters);
        applyFilters.click();
    }

    public void clickClearFilters() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(clearFilters);
        clearFilters.click();
    }

    public void clickShowResults() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(showResults);
        showResults.click();
    }

    public void clickPriceMenu() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(priceButton);
        priceButton.click();
    }

    public void clickMarketCapMenu() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(marketCap);
        marketCap.click();
    }


    public void setDropdownValue100() throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(dropdownValue100);
        dropdownValue100.click();
    }

    public void setPriceFromText(String value) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(priceFromText);
        priceFromText.sendKeys(value);
    }

    public void setPriceToText(String value) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(priceToText);
        priceToText.sendKeys(value);
    }

    public void setMcapFromText(String value) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(mcapFromText);
        mcapFromText.sendKeys(value);
    }

    public void setMcapToText(String value) throws NoSuchFieldException {
        getDriverWait().waitForElementToLoad(mcapToText);
        mcapToText.sendKeys(value);
    }
}

