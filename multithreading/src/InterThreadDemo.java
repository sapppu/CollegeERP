class SharedResource {
    int data;
    boolean hasData = false;

    public synchronized void produce(int value) {
        while (hasData) {
            try { wait(); } catch (InterruptedException e) {}
        }
        data = value;
        hasData = true;
        System.out.println("Produced: " + data);
        notifyAll();
    }

    public synchronized int consume() {
        while (!hasData) {
            try { wait(); } catch (InterruptedException e) {}
        }
        System.out.println("Consumed: " + data);
        hasData = false;
        notify();
        return data;
    }
}

class Producer extends Thread {
    SharedResource obj;
    Producer(SharedResource obj) {
        this.obj = obj;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            obj.produce(i);
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }
}

class Consumer extends Thread {
    SharedResource obj;
    Consumer(SharedResource obj) {
        this.obj = obj;
    }
    public void run() {
        for (int i = 1; i <= 5; i++) {
            obj.consume();
            try { Thread.sleep(500); } catch (Exception e) {}
        }
    }
}

public class InterThreadDemo {
    public static void main(String[] args) {
        SharedResource obj = new SharedResource();
        Producer p = new Producer(obj);
        Consumer c = new Consumer(obj);

        p.start();
        c.start();
    }
}
