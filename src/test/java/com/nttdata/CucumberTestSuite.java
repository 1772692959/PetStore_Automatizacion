package com.nttdata;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"glue"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        monochrome = true
)
public class CucumberTestSuite {

}
