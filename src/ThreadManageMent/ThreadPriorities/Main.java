package ThreadManageMent.ThreadPriorities;

import java.util.concurrent.locks.LockSupport;

public class Main {
    /*public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        Thread thread1 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
        thread1.setName("Thread1");
        thread1.setPriority(Thread.MAX_PRIORITY);

        Thread thread2 = new Thread(()-> System.out.println(Thread.currentThread().getName()));

        thread2.setName("Thread2");
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
*/

   //Thread states

    public static void main(String[] args) throws InterruptedException {

        /**
         * Thread state for a thread which has not yet started.
         */
        //NEW,

            /**
             * Thread state for a runnable thread.  A thread in the runnable
             * state is executing in the Java virtual machine but it may
             * be waiting for other resources from the operating system
             * such as processor.
             */
          //  RUNNABLE,

            /**
             * Thread state for a thread blocked waiting for a monitor lock.
             * A thread in the blocked state is waiting for a monitor lock
             * to enter a synchronized block/method or
             * reenter a synchronized block/method after calling
             * {@link Object#wait() Object.wait}.
             */
          //  BLOCKED,

            /**
             * Thread state for a waiting thread.
             * A thread is in the waiting state due to calling one of the
             * following methods:
             * <ul>
             *   <li>{@link Object#wait() Object.wait} with no timeout</li>
             *   <li>{@link #join() Thread.join} with no timeout</li>
             *   <li>{@link LockSupport#park() LockSupport.park}</li>
             * </ul>
             *
             * <p>A thread in the waiting state is waiting for another thread to
             * perform a particular action.
             *
             * For example, a thread that has called {@code Object.wait()}
             * on an object is waiting for another thread to call
             * {@code Object.notify()} or {@code Object.notifyAll()} on
             * that object. A thread that has called {@code Thread.join()}
             * is waiting for a specified thread to terminate.
             */
           // WAITING,

            /**
             * Thread state for a waiting thread with a specified waiting time.
             * A thread is in the timed waiting state due to calling one of
             * the following methods with a specified positive waiting time:
             * <ul>
             *   <li>{@link #sleep Thread.sleep}</li>
             *   <li>{@link Object#wait(long) Object.wait} with timeout</li>
             *   <li>{@link #join(long) Thread.join} with timeout</li>
             *   <li>{@link LockSupport#parkNanos LockSupport.parkNanos}</li>
             *   <li>{@link LockSupport#parkUntil LockSupport.parkUntil}</li>
             * </ul>
             */
           // TIMED_WAITING,

            /**
             * Thread state for a terminated thread.
             * The thread has completed execution.
             */
           // TERMINATED;
       Thread thread1 = new Thread(()-> {
           System.out.println("NAME of thread " + Thread.currentThread().getName());
            System.out.println("[1] State : "+ Thread.currentThread().getState());


       });
      System.out.println("[2] State : "+ thread1.getState());
       thread1.start();
        System.out.println("[2] State : "+ thread1.getState());
        thread1.join();
        System.out.println("[3] State : "+ Thread.currentThread().getState());
        System.out.println("[2] State : "+ thread1.getState());

    }

}
