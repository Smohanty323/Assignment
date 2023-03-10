package com.test.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;

		@CucumberOptions(features={"src/test/resources/BookingFeatureFiles"},dryRun=false,
				glue= {"com.test.Stepdefination"},
				plugin= {"json:target/jsonReports/restful-booker-report.json","html:target/cucumber-html-report.html"},
				tags="@regression")

public class TestRunner extends AbstractTestNGCucumberTests{

			@Test
			public void run()
			{
				
			}			

			public static File file;
/*
 * reporting code, post execution automatically user can view Cucumber html report in chrome driver 	
 */
			@AfterSuite
			public static void cucumberSandwichReportGeneration() 
			{
				 List<String> jsonFiles = new ArrayList<>();
				 jsonFiles.add("target/jsonReports/restful-booker-report.json");
				 File reportOutputDirectory = null;
				 reportOutputDirectory = new File(System.getProperty("user.dir") + "/report/SandwichReports" + System.currentTimeMillis());

				 String projectName="restful-booker";
				 Configuration configuration = new Configuration(reportOutputDirectory, projectName);
				 configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
				 
				 List<String> classificationFiles = new ArrayList<>();
				 configuration.addClassificationFiles(classificationFiles);
				 configuration.addPresentationModes(PresentationMode.PARALLEL_TESTING);
				 
				 ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
				 reportBuilder.generateReports();
				 file = configuration.getReportDirectory();
				 
				 System.setProperty("webdriver.chrome.driver", "Utilities/chromedriver.exe");
				 
				 ChromeOptions options=new ChromeOptions();
				 options.addArguments("--remote-allow-origins=*");
				 
				 
				 WebDriver driver = new ChromeDriver(options);
				 
				 driver.get(file.toString() + "/cucumber-html-reports/overview-features.html");

			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		