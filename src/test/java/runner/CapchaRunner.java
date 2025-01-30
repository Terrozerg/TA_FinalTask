package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/main/resources/features/"}, glue = {"stepDefinitions"}, tags = "@capcha")
public class CapchaRunner extends AbstractTestNGCucumberTests {
}
