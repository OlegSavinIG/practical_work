package work.concurrency.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {
    private final int threadCount;

    public ComplexTaskExecutor(int threadCount) {
        this.threadCount = threadCount;
    }

    public void executeTasks(int taskCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CyclicBarrier barrier = new CyclicBarrier(taskCount,
                () -> {
                    System.out.println("All tasks finished");
                    executor.shutdown();
                });

        try {
            for (int i = 0; i < taskCount; i++) {
                executor.execute(new ComplexTask(barrier));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
