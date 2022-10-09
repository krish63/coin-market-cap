package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.AbstractPage;
import pages.HomePage;
import org.junit.Assert;

import java.util.Arrays;
import java.util.List;

public class HomePageSteps extends AbstractPage {
    HomePage homePage = new HomePage();

    @When("I should get the table should have {int} rows")
    public void iShouldGetTheTableHavingXpathHaveRows(int arg1) throws NoSuchFieldException {
        WebElement table = homePage.getCryptoTable();
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        tableRows.remove(0);
        System.out.println(tableRows.get(0).getText() + ":" + tableRows.size());
        Assert.assertEquals("Table Dropdown mismatch ", arg1, tableRows.size());
    }

    @Then("I click on close popup")
    public void iClickOnClosePopup() throws NoSuchFieldException {
        homePage.clickClosePopup();
    }


    @Then("I click on size dropdown")
    public void iClickOnSizeDropdown() throws NoSuchFieldException {
        homePage.clickDropdown();
    }

    @Then("I click on dropdown value {int}")
    public void iClickOnDropdownValue(int arg0) throws NoSuchFieldException {
        homePage.setDropdownValue100();
    }

    @And("I click on filters")
    public void iClickOnFilters() throws NoSuchFieldException {
        homePage.clickFilters();
    }

    @And("I click on add filters")
    public void iClickOnAddFilters() throws NoSuchFieldException {
        homePage.clickAddFilters();
    }

    @And("I set the price filter from {int} to {int}")
    public void iSetThePriceFilterFromTo(int arg0, int arg1) throws NoSuchFieldException {
        homePage.clickPriceMenu();
        homePage.setPriceFromText(arg0 + "");
        homePage.setPriceToText(arg1 + "");
        homePage.clickApplyFilters();

    }

    @And("I set the market cap from {string} to {string}")
    public void iSetTheMarketCapFromTo(String arg0, String arg1) throws NoSuchFieldException {
        homePage.clickMarketCapMenu();
        homePage.setMcapFromText(arg0);
        homePage.setMcapToText(arg1);
        homePage.clickApplyFilters();
    }


    @When("I click on show results")
    public void iClickOnShowResults() throws NoSuchFieldException {
        homePage.clickShowResults();
    }

    @Then("I should have all the rows market cap from {string} to {string}")
    public void iShouldHaveAllTheRowsMarketCapFromTo(String agr0, String arg1) throws Exception {

        WebElement table = homePage.getCryptoTable();
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));

        List<String> headers = Arrays.asList(tableRows.get(0).getText().split("\n"));
        System.out.println("Size = " + tableRows.size() + "***********\n" + headers);
        tableRows.remove(0);
        for (int i = 0; i < tableRows.size(); i++) {
            List<String> currentRow = Arrays.asList(tableRows.get(i).getText().split("\n"));
            System.out.println(currentRow + " : " );
            Long actualMCap = Long.parseLong(currentRow.get(headers.indexOf("Market Cap")-1).replaceAll("\\$", "").replaceAll(",", ""));
            Assert.assertTrue("Actual val :" + actualMCap, actualMCap > Long.parseLong(agr0));
            Assert.assertTrue("Actual val :" + actualMCap, actualMCap < Long.parseLong(arg1));
        }
    }

    @And("I should have all the rows price  from {string} to {string}")
    public void iShouldHaveAllTheRowsPriceFromTo(String arg0, String arg1) throws Exception {

        WebElement table = homePage.getCryptoTable();
        List<WebElement> tableRows = table.findElements(By.tagName("tr"));
        System.out.println("Size = " + tableRows.size());
        List<String> headers = Arrays.asList(tableRows.get(0).getText().split("\n"));
        tableRows.remove(0);
        for (int i = 0; i < tableRows.size(); i++) {
            List<String> currentRow = Arrays.asList(tableRows.get(i).getText().split("\n"));
            Double actualPrice = Double.parseDouble(currentRow.get(headers.indexOf("Price") + 1).replaceAll("\\$", "").replaceAll(",", ""));

            Assert.assertTrue("Actual val :" + actualPrice, actualPrice > Double.parseDouble(arg0));
            Assert.assertTrue("Actual val :" + actualPrice, actualPrice < Double.parseDouble(arg1));
        }
    }

    @Then("I click on clear filters")
    public void iClickOnClearFilters() throws NoSuchFieldException {
        homePage.clickClearFilters();
    }


}
