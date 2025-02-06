package work.stream.fork;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Long> {
    private final int end;
    private final int start;
    private final static int THRESHOLD = 10;

    public FactorialTask(int n) {
        this.end = n;
        this.start = 1;
    }

    private FactorialTask(int start, int end) {
        this.end = end;
        this.start = start;
    }

    @Override
    protected Long compute() {
        System.out.println(Thread.currentThread().getName() +
                " processing " + start + "-" + end);
        if (end - start + 1 < THRESHOLD) {
            long result = 1;
            for (int i = start; i <= end; i++) {
                result *= i;
            }
            return result;
        } else {
            int mid = start+(end-start) / 2;

            FactorialTask leftTask = new FactorialTask(start, mid);
            FactorialTask rightTask = new FactorialTask(mid + 1, end);

            leftTask.fork();
            Long rightResult = rightTask.compute();
            Long leftResult = leftTask.join();
            System.out.println("Left = " + leftResult + " Right = " + rightResult);
            return rightResult * leftResult;
        }
    }
}
