package com.maarek.kosar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by jlyman on 10/1/15.
 */
public class Futures {
    private static final int NTHREDS = 10;

    public static class MyCallable implements Callable<Long> {
        @Override
        public Long call() throws Exception {
            long sum = 0;
            for (long i = 0; i <= 100; i++) {
                sum += i;
            }
            return sum;
        }
    }

    public static void futuresDemo() {
        ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

        List<Future<Long>> list = new ArrayList<Future<Long>>();

        for (int i = 0; i < 10; i++) {
            Callable<Long> worker = new MyCallable();
            Future<Long> submit = executor.submit(worker);
            list.add(submit);
        }

        long sum = 0;

        System.out.println("Future List Size: " + list.size());

        // now retrieve the result
        for (Future<Long> future : list) {
            try {
                sum += future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Future Sum: " + sum);
    }

    public static void completableFuturesDemo() {

        CompletableFuture<Integer> xtask = CompletableFuture.supplyAsync( ( ) -> {
            try {
                // Simulate long running task
                Thread.sleep(500);
            } catch (InterruptedException e) { }
            return 10;
        });

        CompletableFuture<Integer> ytask = CompletableFuture.supplyAsync( ( ) -> {
            try {
                // Simulate long running task
                Thread.sleep(200);
            } catch (InterruptedException e) { }
            return 7;
        });

        CompletableFuture<Integer> addition = CompletableFuture.supplyAsync( ( ) -> {
            try {
                return xtask.get() + ytask.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return 0;
        });

        try {
            System.out.println("CompletableFuture: " + addition.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
