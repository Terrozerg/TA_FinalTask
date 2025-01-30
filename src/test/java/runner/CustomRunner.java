package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/main/resources/features/"}, glue = {"stepDefinitions"}, tags = "@none")
public class CustomRunner extends AbstractTestNGCucumberTests {
}
