package work.concurrency.barrier;

import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Runnable {
    private final CyclicBarrier barrier;

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " waiting");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " finished");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
