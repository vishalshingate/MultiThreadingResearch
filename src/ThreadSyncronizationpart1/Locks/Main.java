package ThreadSyncronizationpart1.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Object obj = new Object();
    public static void main(String[] args) {

        Lock lock = new ReentrantLock();
        /**
         * Let's understand 2 methods from lock interface
         * lock:-
         * unlock:-
         */




        // both are similar
        synchronized(obj){

        }
        /**
         * between the lock and unlock object we can put any kind o f instructions that are critical.
         * lock insures us those critical section get executed by the single thread only.
         * lock() is more flexible than synchronized keyword
         * there are upsides of this and downsize of this.
         * diff:-
         * synchronize keyword can not start the  critical section in one method and end in another method.|| we can lock() anywhere and unlock it from anywhere
         * the responsibility of ending the critical section block is on developer when we use the lock/ unlock.
         * when we use synchronize there is by default block or method ending.
         */
        lock.lock();

        lock.unlock();

    }
}
