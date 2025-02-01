package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    //using a separate counter per thread
    private ThreadLocal<Integer> retryCount = ThreadLocal.withInitial(()->0);
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (retryCount.get() < MAX_RETRY) {
                retryCount.set(retryCount.get() + 1);
                return true;
            }
        }
        return false;
    }
}
