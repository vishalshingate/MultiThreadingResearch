package ThreadManageMent.DaemonThreads;

public class Main {
    public static void main(String[] args) {
        /**Daemon thread,
         *they are not required to completed before the JVM exits.
         *considered as low priority threads, which can be destroyed at any moment in time without any kind of side effects.
         * join operation works same as works for the user threads
          */

        /**User Threads,
         * any regular thread created by user is user level thread.
         * JVM consider those threads as being as high priority.
         * JVM waits for the completion of the all user level threads, before existing the java application.
         */

        Thread thread = new Thread(()-> System.out.println("Thread Name: " + Thread.currentThread().getName()));
        thread.setDaemon(true);

        Thread thread1 = new Thread(new MyThread(6));
        thread1.setDaemon(true);
        Thread thread2 = new Thread(new MyThread(2));
        thread1.start();
        thread2.start();

        /**
         * if we put thread1.join, they will wait for complete thread1 execution too.
         * example garbage collector thread is daemon thread and it will run over the application lifetime.
          */
    }

    static  class MyThread implements Runnable{
        private int seconds;
        public MyThread(int seconds) {
            this.seconds = seconds;
        }

        public void run() {
            for(int i = 0; i < seconds; i++) {

               try{
                   System.out.println("Sleeping for 1 second, Thread : " + Thread.currentThread().getName());
                   Thread.sleep(1000);
               }
               catch(InterruptedException e) {
                   e.printStackTrace();
               }
            }
            System.out.println("Thread Name: " + Thread.currentThread().getName());

        }
    }
}

/** OUTPUT

 Sleeping for 1 second, Thread : Thread-2
 Sleeping for 1 second, Thread : Thread-1
 Sleeping for 1 second, Thread : Thread-1
 Sleeping for 1 second, Thread : Thread-2
 Sleeping for 1 second, Thread : Thread-1
 Thread Name: Thread-2

 */
