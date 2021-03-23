package top.wsure.test;

import top.wsure.common.utils.PagingUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * FileName: PagingTest
 * Author:   wsure
 * Date:     2021/3/23 10:22 上午
 * Description:
 */
public class PagingTest {
    public static void main(String[] args) {
        List<Integer> list = IntStream.range(0, 2000).boxed().collect(Collectors.toList());
        System.out.println("list size:" + list.size() + "\ntop 20:" + Arrays.toString(list.subList(0, 20).toArray()));

        long pageSize = 50;
        long start = System.currentTimeMillis();
        PagingUtils.shardingExecution(pageSize, list, page -> {
            Integer min = page.stream().min(Comparator.naturalOrder()).orElse(null);
            Integer max = page.stream().max(Comparator.naturalOrder()).orElse(null);
            System.out.println("[" + min + " -> " + max + "] work on " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("no result use time :" + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();

        List<String> res = PagingUtils.shardingExecution(pageSize, list, page -> {
            Integer min = page.stream().min(Comparator.naturalOrder()).orElse(null);
            Integer max = page.stream().max(Comparator.naturalOrder()).orElse(null);
            System.out.println("[" + min + " -> " + max + "] work on " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return page.stream().map(v -> (v + 1) % 50 == 0 ? String.format("%4s", v) + "\n" : String.format("%4s", v)).collect(Collectors.toList());
        });
        System.out.println("with result use time :" + (System.currentTimeMillis() - start) + "ms");

        System.out.println("reverseOrder list size:" + list.size() + "\nreverseOrder list:\n[" + String.join(",", res) + "]");

    }
}
