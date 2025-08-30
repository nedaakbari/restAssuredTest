package ir.sample;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int MAX_RETRY_COUNT = 2; // تعداد دفعاتی که تست دوباره اجرا می‌شود

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY_COUNT) {
            count++;
            return true; // اجازه می‌دهد تا تست دوباره اجرا شود
        }
        return false; // زمانی که تعداد دفعات retry به حداکثر رسید
    }
}