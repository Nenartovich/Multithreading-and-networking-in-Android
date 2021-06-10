import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static long mCount = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int floorsAmount = 1000;
        int threadsAmount = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(threadsAmount);
        for (int i = 0; i < floorsAmount; i++) {
            Future<Long> future = executorService.submit(() -> {
                long cnt = 0L;
                for (int j = 0; j < 100000000; j++) {
                    cnt++;
                }
                return cnt;
            });
            lock.lock();
            mCount += future.get();
            lock.unlock();
        }
        executorService.shutdown();
        System.out.println(mCount);
    }
}