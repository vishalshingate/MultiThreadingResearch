package ThreadSyncronizationpart1.producerConsumer;

import java.util.Queue;

public class Producer implements Runnable {
    private final Queue<String> queue;

    public Producer(Queue<String> queue) {
        this.queue = queue;
    }
    @Override
    //log running threads
    public void run() {
        while (true) {
            try {
                produceData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void produceData() throws InterruptedException {
        synchronized (queue) {
            if(queue.size()==10){
                System.out.println("Queue is Full, In producer , waiting ....");
                queue.wait();
            }
            Thread.sleep(1000); // waiting 1 second before producing the data
            System.out.println("Produce data with id " + queue.size());
            queue.add("element_" + queue.size());
            if(queue.size()==1){
                queue.notify();
            }

        }
    }
}
