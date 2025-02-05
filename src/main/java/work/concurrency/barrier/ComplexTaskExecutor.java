package work.concurrency.barrier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComplexTaskExecutor {
    private final int threadCount;

    public ComplexTaskExecutor(int threadCount) {
        this.threadCount = threadCount;
    }

    public void executeTasks(int taskCount) {
        List<Future<Integer>> result = Collections.synchronizedList(new ArrayList<>());
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CyclicBarrier barrier = new CyclicBarrier(taskCount, () -> {
            System.out.println("All tasks crossed barrier");
            shutdownExecutor(executor);
        });

        try {
            for (int i = 0; i < taskCount; i++) {
                Future<Integer> submit = executor.submit(new ComplexTask(barrier));
                result.add(submit);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int sum = computeResult(result);
        System.out.println("Final sum = " + sum + " " + Thread.currentThread().getName());

    }

    private int computeResult(List<Future<Integer>> result) {
        return result.stream()
                .mapToInt(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .sum();
    }

    private void shutdownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            Thread.sleep(3000);
//            if (!executor.awaitTermination(5L, )) {
                executor.shutdownNow();
//            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
