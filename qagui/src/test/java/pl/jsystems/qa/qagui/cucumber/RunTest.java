package pl.jsystems.qa.qagui.cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = "classpath:pl.jsystems.qa.qagui.cucumber",
        plugin = { "pretty", "summary", "html:target/cucumber/report.html", "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "rerun:target/rerun.txt"
        },
        tags= "@login"
)
public class RunTest {

}
