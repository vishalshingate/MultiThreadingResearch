package ThreadSyncronizationpart1.producerConsumer;

public class Main {
    private static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {


       // Thread 1
        synchronized (lock) {
            lock.wait(); // it does 2 things-> it suspend the execution of current thread and releases the lock
            System.out.println("Thread 1 is waiting for lock");// it acquired the monitor

        }
        // and it will release the monitor


        // Thread 2
        synchronized (lock) {
            lock.notify(); //1 we can exit from that waiting state by calling wait or interrupt, can be received by another thread
            System.out.println("Thread 2 is notifying for lock");//2
        }

        /**
         *
         * when we call notify, thread 1 is not unlocked directly from the waiting state, because thread2 has the lock on that object.
         * it sets the flag thread1 can exit from the waiting state but it needs to execute all the instructions has in critical section of thread 2
         */
    }
}
