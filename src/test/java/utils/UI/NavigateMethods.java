package utils.UI;

import pages.AbstractPage;

public class NavigateMethods extends AbstractPage implements BaseTest {

    public void navigateTo(String url) {
        getDriver().get(url);
    }

}
