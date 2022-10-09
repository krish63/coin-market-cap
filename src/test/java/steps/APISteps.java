package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;


import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import utils.API.APIEndpoints;
import utils.API.APIMethods;
import utils.Constants;
import utils.context.TestContext;


public class APISteps {
    TestContext testContext = new TestContext();
    APIMethods apiMethods = new APIMethods();
    APIEndpoints apiEndpoints = new APIEndpoints();

    String token = "";



    @Given("I set the API token in Context")
    public void iSetTheAPITokenInContext() {
        testContext.scenarioContext.setContext("apiToken", Constants.TOKEN);
    }

    @Then("I get the crypto map")
    public void iGetTheCryptoMap() {
        token = testContext.scenarioContext.getContext("apiToken");
        Response response = apiMethods.getMethod(token, apiEndpoints.getCryptoMap());

        testContext.scenarioContext.setContext("CryptoMAP", response.getBody().asString());
        System.out.println(response.getBody().asString());
    }

    @And("I store ID of {string}")
    public void iFetchIDOf(String cryptos) {
        token = testContext.scenarioContext.getContext("apiToken");
        String responseString = null;
        List<String> cryptoList = Arrays.asList(cryptos.split(","));
        if (testContext.scenarioContext.isContains("CryptoMAP")) {
            responseString = testContext.scenarioContext.getContext("CryptoMAP");
        } else {
            responseString = apiMethods.getMethod(token, apiEndpoints.getCryptoMap()).getBody().asString();
        }
        JSONArray dataArray = new JSONObject(responseString).getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject data = dataArray.getJSONObject(i);
            String symbol = data.getString("symbol");
            if (cryptoList.contains(symbol)) {
                testContext.scenarioContext.setContext(symbol, data.get("id").toString());
            }
        }
    }

    @Then("I should be able to Convert ID of {string} to {string}")
    public void iShouldBeAbleToConvertIDOf(String cryptos, String convertTo) {
        token = testContext.scenarioContext.getContext("apiToken");

        String fiatId = "";
        String responseString = apiMethods.getMethod(token, apiEndpoints.getFiatMap()).getBody().asString();

        JSONArray dataArray = new JSONObject(responseString).getJSONArray("data");

        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject data = dataArray.getJSONObject(i);
            String name = data.getString("name");
            if (name.equalsIgnoreCase(convertTo)) {
                fiatId = data.get("id").toString();
            }
        }

        if (fiatId.equals("")) {
            throw new RuntimeException("Currency : " + convertTo + "Does not exist. Please update the feature file");
        }

        List<String> cryptoList = Arrays.asList(cryptos.split(","));

        for (String cryptoName : cryptoList) {
            String cryptoId = testContext.scenarioContext.getContext(cryptoName);
            String conversionResponse = apiMethods.getMethod(token, apiEndpoints.priceConversion("1", fiatId, cryptoId)).asString();
            System.out.println("\n***************************************\nConversion res :\n" + conversionResponse);
        }
    }


    @Then("I store the crypto info for {string}")
    public void iGetTheCryptoInfoFor(String cryptoId) {
        token = testContext.scenarioContext.getContext("apiToken");
        Response response = apiMethods.getMethod(token, apiEndpoints.getCryptoInfo(cryptoId));
        testContext.scenarioContext.setContext("CryptoInfo_" + cryptoId, response.getBody().asString());
    }

    @And("Logo URL should be {string} for {string}")
    public void logoURLShouldBe(String expectedLogo, String cryptoId) {

        String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + cryptoId);
        JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(cryptoId);
        String actualLogo = data.getString("logo");
        Assert.assertEquals("LOGO Mismatch for Response :\n" + responseBody, expectedLogo, actualLogo);

    }

    @Then("Currency symbol should be {string} for {string}")
    public void currencySymbolShouldBe(String expectedSymbol, String cryptoId) {
        String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + cryptoId);
        JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(cryptoId);
        String actualSymbol = data.getString("symbol");

        Assert.assertEquals("Symbol Mismatch for Response :\n" + responseBody, expectedSymbol, actualSymbol);

    }

    @And("technical_doc should be {string} for {string}")
    public void technical_docShouldBe(String expectedTechDoc, String cryptoId) {
        String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + cryptoId);
        JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(cryptoId);
        String actualTechDoc = data.getJSONObject("urls").getJSONArray("technical_doc").getString(0);
        Assert.assertEquals("Tech_DOC Mismatch for Response :\n" + responseBody, expectedTechDoc, actualTechDoc);

    }

    @Then("Date added should be {string} for {string}")
    public void dateAddedShouldBe(String expectedDateAdded, String cryptoId) {
        String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + cryptoId);
        JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(cryptoId);
        String actualDateAdded = data.getString("date_added");
        Assert.assertEquals("Date Added Mismatch for Response :\n" + responseBody, expectedDateAdded, actualDateAdded);


    }

    @And("Response should have {string} tag for {string}")
    public void responseShouldHaveTag(String tagName, String cryptoId) {
        String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + cryptoId);
        JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(cryptoId);
        JSONArray tags = data.getJSONArray("tags");
        List<String> actualTagsList = new ArrayList<>();

        for (int i = 0; i < tags.length(); i++)
            actualTagsList.add(tags.getString(i));

        Assert.assertTrue("This crypto does not have Mineable tag.\n Response: " + responseBody, actualTagsList.contains(tagName));

    }

    @When("I store the crypto info until {int} id")
    public void iStoreTheCryptoInfoUntilId(int idCount) throws InterruptedException {
        for (int i = 1; i <= idCount; i++) {
            token = testContext.scenarioContext.getContext("apiToken");
            Response response = apiMethods.getMethod(token, apiEndpoints.getCryptoInfo(i + ""));
            testContext.scenarioContext.setContext("CryptoInfo_" + i, response.getBody().asString());
        }
    }

    @Then("I store cryptos with {string} Tag for {int} cryptos")
    public void iStoreCryptosWithTag(String tagName, int count) {
        Map<Integer, String> mineableMap = new HashMap<>();
        for (int i = 1; i <= count; i++) {
            try {
                String responseBody = testContext.scenarioContext.getContext("CryptoInfo_" + i);
                JSONObject data = new JSONObject(responseBody).getJSONObject("data").getJSONObject(i + "");

                responseShouldHaveTag(tagName, i + "");
                testContext.scenarioContext.setContext("isMineable_" + i, "true");
                mineableMap.put(i, data.getString("name"));
            } catch (RuntimeException ignored) {
                System.out.println("MINEABLE NOT FOUND for ID: " + i );
                //No Action
            }
        }
        System.out.println(mineableMap.size());
        System.out.println(mineableMap);

    }

    @And("Response should have {string} tag for all Ids until {int}")
    public void responseShouldHaveTagForAllIdsUntil(String tagName, int id) {
        for (int i = 1; i < id; i++) {
            if (testContext.scenarioContext.isContains("isMineable_" + i)) {
                responseShouldHaveTag(tagName, i + "");
            }
        }
    }
}
