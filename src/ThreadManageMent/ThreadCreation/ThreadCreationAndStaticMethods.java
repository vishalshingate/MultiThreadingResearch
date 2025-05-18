package ThreadManageMent.ThreadCreation;

public class ThreadCreationAndStaticMethods {
/*
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        System.out.println("Current Thread Name: " + thread.getName());

        try {
            Thread.sleep(700); // suspend the execution of thread for given time.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
*/
public static void main(String[] args) throws InterruptedException {

  // using extend code {

    MyThread myThread = new MyThread();

    myThread.start();
    Thread.sleep(400);
    myThread.test1();
    System.out.println("Name"+ Thread.currentThread().getName());
    //}

    // using Runnable Interface code
    MyRunnableThread myThread2 = new MyRunnableThread();
    Thread runnablThread = new Thread(myThread2);
    runnablThread.start();

    // another way using runnable
    Runnable myThread3;


    Thread runnableThread2 = new Thread(() -> {
        Thread.currentThread().setName("anonymousRunnable");
        System.out.println("using anonymous class"+ Thread.currentThread().getName());
    });
    runnableThread2.start();
    runnableThread2.join();
    System.out.println("end of line ThreadName "+ Thread.currentThread().getName());
}

//Using Extend Threads
static class MyThread extends Thread {

    public MyThread() {
        // this will get run by main thread
        System.out.println("from constructor Name"+ Thread.currentThread().getName());
    }
    public void test1    () {

        System.out.println("Name"+ Thread.currentThread().getName());
    }

    public void run() {
        setName("MyThread1");
        test1();// this will executed by the MyThread1
        System.out.println("Name"+ Thread.currentThread().getName());
    }
}

//Using Runnable interface

    static class MyRunnableThread implements Runnable {
  // 1 way
    public void run() {
        Thread.currentThread().setName("runnable");
        System.out.println("Current Thread: " + Thread.currentThread().getName());

    }
    }

}
