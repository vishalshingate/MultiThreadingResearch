package ThreadSyncronizationpart1.Semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static Semaphore semaphore = new Semaphore(2);
    public static void main(String[] args) throws InterruptedException {
    Executor executor = new Executor();
    executor.submit(new Job(4000));
    executor.submit(new Job(6000));
    executor.submit(new Job(3000));



    }


    private static class Executor {
        public void submit(Job job) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " submitting job " + job.getWork());
            semaphore.acquire();
            Thread thread = new Thread(()->{
                try {
                    System.out.println("Executing job " + job.getWork());
                    Thread.sleep(job.getWork());
                    semaphore.release();
                    System.out.println("Job finished with id: " + job.getWork());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
            thread.start();
        }

    }
    private static class Job {
        private final int work;
        public Job(int work) {
            this.work = work;
        }

        public int getWork() {
            return work;
        }
    }
}
