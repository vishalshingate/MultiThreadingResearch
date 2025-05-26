package ThreadSyncronizationpart1.SyncronizeKeyWord;



import java.util.ArrayList;
import java.util.List;

public class Main {
    private static int globalcount = 0;
    private static final Object lock = new Object();
    private static  ThreadLocal<String> threadLocal = new ThreadLocal<>(); /// every value set from the run method will be private to the each thread

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<Thread>();
        ThreadGroup group = new ThreadGroup("RaceConditions");
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(group, new MyThread());
            t.start();
            threads.add(t);
        }
        group.interrupt();
        threads.forEach(t -> {
            try {
                t.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(globalcount);
    }
    /*static class MyThread implements Runnable {
        public void run() {
            int counter = 0;
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(threadLocal.get());
        }
    }

     */

    static class MyThread implements Runnable {
        public void run() {
            try{
                Thread.sleep(99999);
            }
            catch (InterruptedException e) {
                // e.printStackTrace();
            }
//            synchronized(lock){ // lock is the key to enter this block, if it is given to one thread then other thread have to wait at this block to get new key
//                globalcount++;
//            }
            increment();

            // following is unwrapped version of the counter
//            int localcount = globalcount;
//            localcount = localcount + 1;
//            globalcount = localcount;
        }

        private static synchronized void increment(){
            globalcount++;
        }
    }
}

/**
 * local variables:-
 * 1st:-
 * in run method if we declare variable then, its store at the stack level , so every thread have its own stack memory.
 * so every thread will have its own copy of local variables.
 *2nd:- Thread will be having access to class level variables too, this is getting store at the heap memory of the area,
 * so this variables getting shared with the all threads.
 */

/** Race condition
 * Here we are seeing effect of race condition, when all thread trying to update the counter
 */
