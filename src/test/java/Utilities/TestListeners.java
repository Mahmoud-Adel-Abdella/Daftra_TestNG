package Utilities;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static Utilities.ExtentManger.screenShot;

public class TestListeners extends BaseTest implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        System.out.println("============= Start Selenium Framework =============");
        ExtentManger.initReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("======== Start Test ========");
        ExtentManger.createTest(result.getMethod().getMethodName());
        ExtentManger.getTest().log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManger.getTest().log(Status.INFO, "Test Suite Finished: "+ context.getName());
        System.out.println("================== Report Flushed ==================");
        ExtentManger.flushReports();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentManger.getTest().log(Status.PASS, "✅✅ Test Passed ✅✅");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentManger.getTest().log(Status.PASS, "🟨🟨 Test Skipped 🟨🟨: " + result.getThrowable());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentManger.getTest().log(Status.FAIL, "❎❎ Test Failed ❎❎: " + result.getThrowable());

        try {
            String screenShotPath = screenShot(driver, result.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


