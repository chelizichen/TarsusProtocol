package org.lib.events;

import java.util.concurrent.*;

public class PromiseExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FunctionA();
    }

    public static void FunctionA() throws ExecutionException, InterruptedException {
        System.out.println("aaaa");
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                return FunctionB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        futureB.thenAccept(result -> System.out.println(result));
        System.out.println("cccc");
        Thread.sleep(2000);
    }

    public static String FunctionB() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        try {
            // 模拟耗时操作
            Thread.sleep(1000);
            return "bbbb";
        } finally {
            executorService.shutdown();
        }
    }
}
