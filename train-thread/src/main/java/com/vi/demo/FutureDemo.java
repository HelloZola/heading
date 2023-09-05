package com.vi.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

public class FutureDemo {


    public static class DemoCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            System.out.println("当前时间：" + new Date().toLocaleString() + " " + Thread.currentThread().getName());
            return "我是" + Thread.currentThread().getName();
        }
    }

    public static void main(String[] args) {

        try {
            List<Future<String>> results = new ArrayList<Future<String>>();
            ExecutorService executorService = Executors.newCachedThreadPool();
            for (int i = 0; i < 10; i++) {
                results.add(executorService.submit(new DemoCallable()));
            }

            for (Future<String> result : results) {
                System.out.println(result.get());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
