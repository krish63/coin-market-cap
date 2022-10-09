
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:reports/cucumber-json-reports/json-report.json",
                "html:reports/cucumber-html-reports/html-report.html",
        },
        glue = {"steps"},
        features = {"classpath:features/"}
)
public class RunnerTest {
}
