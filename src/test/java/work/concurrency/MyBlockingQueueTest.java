package work.concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MyBlockingQueueTest {
    private final MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(2);

    @Test
    public void testEnqueueDequeue() throws InterruptedException {
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals(2, queue.size());

        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());

        assertEquals(0, queue.size());
    }

    @Test
    public void testDequeueBlocksWhenQueueIsEmpty() throws InterruptedException {
        Thread consumer = new Thread(() -> {
            try {
                queue.dequeue();
            } catch (InterruptedException ignored) {
            }
        });

        consumer.start();
        Thread.sleep(500);

        assertTrue(consumer.isAlive());

        queue.enqueue(1);
        Thread.sleep(500);

        assertFalse(consumer.isAlive());
    }

    @Test
    public void testEnqueueBlocksWhenQueueIsFull() throws InterruptedException {
        queue.enqueue(1);
        queue.enqueue(2);

        Thread producer = new Thread(() -> {
            try {
                queue.enqueue(3);
            } catch (InterruptedException ignored) {
            }
        });

        producer.start();
        Thread.sleep(500);

        assertTrue(producer.isAlive());

        queue.dequeue();
        Thread.sleep(500);

        assertFalse(producer.isAlive());
    }
}