package pages;

import utils.UI.BaseTest;
import utils.UI.DriverManager;
import utils.UI.DriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractPage implements BaseTest {

    private final DriverManager driverManager = new DriverManager();
    private final DriverWait driverWait = new DriverWait(driverManager);

    protected AbstractPage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public WebDriver getDriver() {
        return driverManager.getDriver();
    }

    public DriverWait getDriverWait() {
        return driverWait;
    }

    public void wait(int time) throws InterruptedException {
        Thread.sleep(time * 1000L);
    }
}
