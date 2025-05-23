package ThreadManageMent.ThreadGroups;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
//        Thread thread2 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
//        Thread thread3 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
//        Thread thread4 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
//        Thread thread5 = new Thread(()-> System.out.println(Thread.currentThread().getName()));
         /** this is the problem
         * if we have to create all the thread then we have to call start methods on all threads
         * to achieve that we can use the collection and iterate over that
          * to avoid this we can use ThreadGroup
         */
       /* HashSet<Thread> threadSet = new HashSet<>();
        threadSet.add(thread1);
        threadSet.add(thread2);
        threadSet.add(thread3);
        threadSet.add(thread4);
        threadSet.add(thread5);

        threadSet.parallelStream().forEach(Thread::start);
        */

        ThreadGroup group1 = new ThreadGroup("Group1");
        group1.setMaxPriority(9);
        ThreadGroup parent = group1.getParent();

        System.out.println("Parent name: " + parent.getName() + ", priority: " + parent.getMaxPriority());

        Thread thread1 = new Thread(group1, new MyThread(),"MyThread1");
        Thread thread2 = new Thread(group1, new MyThread(), "MyThread2");
        Thread thread3 = new Thread(group1,new MyThread(), "MyThread3");

        thread1.setPriority(Thread.MAX_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(3000);
        group1.interrupt();




    }

    static class MyThread implements Runnable{
      public void run() {

          while(true){
              try {
                  Thread.sleep(3000);
              }catch (InterruptedException e){
                  Thread currentThread = Thread.currentThread();
                  System.out.println("Name: "+currentThread.getName()+ " Priority: "+currentThread.getPriority());
              }
          }


      }
    }
}
