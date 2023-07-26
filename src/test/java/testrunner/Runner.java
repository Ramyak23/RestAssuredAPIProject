package testrunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber-report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"}, //reporting purpose
		monochrome=false,  //console output color
		dryRun=false,
		//tags="@tag01",
		features = {"src/test/resources/Features"}, //location of feature files
		glue= "stepdefinition") //location of step definition files
       


public class Runner extends AbstractTestNGCucumberTests  {
	@Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
				
		return super.scenarios();
}
}
