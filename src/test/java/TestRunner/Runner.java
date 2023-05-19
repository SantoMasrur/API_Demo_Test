package TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;


    @CucumberOptions(
            features = {"src/test/resources/features/EmpRegPostReqModel.feature"},
            glue = {"StepDefinition"},
            tags ="@test",
            monochrome = true,
            dryRun = false,
            plugin = {
                    "pretty",
                    "html:build/reports/feature.html"
            })
    @Test
    public class Runner extends AbstractTestNGCucumberTests {
    }

