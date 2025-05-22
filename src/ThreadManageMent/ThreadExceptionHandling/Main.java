package ThreadManageMent.ThreadExceptionHandling;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /**
         * when we use join method, we have to throws keyword or use try catch to handle InterruptException
         * Thread.Sleep() also we need to handle the interruptException.
         * when we use any this methods in the run methods`

         */
        Thread thread = new Thread(new customThreadGroup("ThreadGroup1"), new MyThread(1));
        // another way
       // Thread thread = new Thread(new MyThread(1));

        thread.setUncaughtExceptionHandler((Thread t, Throwable exception) -> {
            System.out.println(exception.getMessage());
        });
        Thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable exception) -> {
            System.out.println(exception.getMessage());
        });


        thread.start();
        thread.join();





        }

        static class MyThread implements Runnable{
        int seconds;
        public MyThread(int seconds) {
                this.seconds = seconds;
            }
            public void run() { // we can not use throws keyword here because we have to override the method of runnable class
                                // where we have public void run only not public void throws.

                for(int i = 0; i < seconds; i++) {
                    System.out.println("Sleeping for 1s, thread: " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                List<String> list = null;
                list.size();

                System.out.println("Result: "+ (4/0));

                }


            }

            static class customThreadGroup extends ThreadGroup {


                public customThreadGroup(String name) {
                    super(name);
                }

                public customThreadGroup(ThreadGroup parent, String name) {
                    super(parent, name);
                }

                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    super.uncaughtException(t, e);
                    System.out.println("Uncaught exception in thread: " + t.getName());
                }
            }
    }

    /** Unchecked exception-> the exception are not checked at the compile time, checked at the runtime.
     * Usually derived from the runtime exception  and error class.
     * here using custom threadGroup class we are handling the runtime exception, without breaking the code.
     *
     *  thread.setDefaultUncaughtExceptionHandler((Thread t, Throwable exception) -> {
     *             System.out.println(exception.getMessage());
     *         });
     *         this is another way of handling runtime exception in the threads.
     *
     *
     *
     *          thread.setUncaughtExceptionHandler((Thread t, Throwable exception) -> {
     *             System.out.println(exception.getMessage());
     *         });
     *
     *         this get called first
     */

