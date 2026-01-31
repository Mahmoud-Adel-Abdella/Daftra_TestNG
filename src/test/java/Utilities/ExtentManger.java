package Utilities;

import Tests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManger extends BaseTest {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    public static void initReport() {
        if (extentReports == null) {
            String reportPath = System.getProperty("user.dir") + "/Reports/extentReport.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Regression");
            sparkReporter.config().setTheme(Theme.DARK);

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Tester", "Mahmoud Adel");
            extentReports.setSystemInfo("ENV", "QA");
            extentReports.setSystemInfo("Browser", "Chrome");
        }
    }

    public static String screenShot(WebDriver driver, String testName) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String path = System.getProperty("user.dir") + "/ScreenShots/" + testName + "_" + timeStamp + ".png";

        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destination = new File(path);

        Files.createDirectories(destination.getParentFile().toPath());
        Files.copy(src.toPath(), destination.toPath());

        return path;
    }


    public static void createTest(String testName){
        extentTest = extentReports.createTest(testName);
    }

    public static ExtentTest getTest (){
        return extentTest;
    }

    public static void flushReports(){
        if(extentReports != null){
            extentReports.flush();
        }
    }
}
