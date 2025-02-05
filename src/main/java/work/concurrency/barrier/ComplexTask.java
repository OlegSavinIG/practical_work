package work.concurrency.barrier;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

public class ComplexTask implements Callable<Integer> {
    private final CyclicBarrier barrier;
    private final Random random = new Random();

    public ComplexTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public Integer call() {
        try {
            int result = random.nextInt(10);
            System.out.println(Thread.currentThread().getName() + " waiting");
            barrier.await();
            System.out.println(Thread.currentThread().getName() + " finished with result " + result);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
