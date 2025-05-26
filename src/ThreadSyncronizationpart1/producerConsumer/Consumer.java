package ThreadSyncronizationpart1.producerConsumer;

import java.util.Queue;

public class Consumer implements Runnable {
   private final Queue<String> queue;

   public Consumer(Queue<String> queue) {
       this.queue = queue;
   }
   @Override
   //long-running threads
    public void run() {
        while(true) {
            try {
                consumeData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void consumeData() throws InterruptedException {
       synchronized (queue) {
           if(queue.isEmpty()) {
               System.out.println("Queue is empty, Consumer is waiting...");
               queue.wait();

           }

           Thread.sleep(700); //wait 700 seconds before consuming the data
           String data = queue.poll();
           System.out.println("Consumed Data: "+data);
           if(queue.size()==9){
               queue.notify();
           }
       }
       }

}
/**
 * if we call notify :-
 * if no one is waiting for an thread then it does nothing
 */
