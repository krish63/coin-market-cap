package steps;

import pages.AbstractPage;
import io.cucumber.java.en.Then;

import java.io.IOException;

public class PredefinedStepDefinitions extends AbstractPage {
    // Step to navigate to specified URL
    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link) {
        navigationObj.navigateTo(link);
    }

    // wait for specific period of time
    @Then("^I wait for (\\d+) sec$")
    public void wait(int time) throws NumberFormatException, InterruptedException {
        super.wait(time);
    }

    //Take a screenshot of the current page
    @Then("^I take screenshot$")
    public void take_screenshot() throws IOException {
        screenshotObj.takeScreenShot();
    }
}