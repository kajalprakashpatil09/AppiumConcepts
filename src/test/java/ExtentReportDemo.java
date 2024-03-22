import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/*
This class contains functions to create report using ExtentReports
 */

public class ExtentReportDemo {
        ExtentReports extent;

        @BeforeMethod
        public void config() {
            String path = System.getProperty("user.dir") + "/reports/index.html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(path);
            reporter.config().setReportName("Mobile Automation Results");
            reporter.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "KPP");
        }

        @Test
        public void reportDemoTest1() {
            ExtentTest test = extent.createTest("Test 1");
            Assert.assertEquals(true, true);
        }

        @AfterClass
        public void teardown(){
            extent.flush();
        }
    }

