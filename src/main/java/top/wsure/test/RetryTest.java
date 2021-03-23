package top.wsure.test;

import top.wsure.common.utils.RetryUtils;

/**
 * FileName: RetryTest
 * Author:   wsure
 * Date:     2021/3/23 11:25 上午
 * Description:
 */
public class RetryTest {
    public static void main(String[] args) {
        String str = RetryUtils.retry(() -> {
            System.out.println("start :"+System.currentTimeMillis());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end :"+System.currentTimeMillis());
            throw new Exception("adasd");
        },3,5000);
    }
}
