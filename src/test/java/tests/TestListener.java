package tests;

import lombok.extern.log4j.Log4j;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

@Log4j
public class TestListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        log.info(String.format("__________________________________STARTING TEST %s__________________________________",
                result.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(String.format("__________________________________FINISHED TEST %s DURATION %s__________________________________",
                result.getName(), getExecutionTime(result)));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(String.format("__________________________________FAILED TEST %s DURATION %s__________________________________",
                result.getName(), getExecutionTime(result)));
    }

    private long getExecutionTime(ITestResult iTestResult){
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
