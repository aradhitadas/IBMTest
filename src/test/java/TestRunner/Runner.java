package TestRunner;		

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(
		features="Feature",
		tags = "@Test1",

glue={"StepDefinition"}
)						
public class Runner 				
{		

}