package ThreadSyncronizationpart1.producerConsumer;

import java.util.LinkedList;
import java.util.Queue;

public class MainApp {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<String>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();
    }
}
