class  SynchronizedStatement implements  Runnable{
    class_A obj1;
    Thread t;

    public SynchronizedStatement(class_A c) {
        obj1=c;
        t=new Thread(this);
    }
    @Override
    public void run(){
        synchronized (obj1) {
            obj1.printValue();
        }
    }

    public static void main(String[] args) {
        class_A ca = new class_A();
        SynchronizedStatement one = new SynchronizedStatement(ca);
        one.t.start();
        SynchronizedStatement two = new SynchronizedStatement(ca);
        two.t.start();
        SynchronizedStatement three = new SynchronizedStatement(ca);
        three.t.start();
    }
}

class class_A{
    void printValue(){
        try {
            int i;
            for(i = 0; i <=5; i++) {
                System.out.print(i+" ");
                Thread.sleep(1000);
            }
            System.err.println("Thread Finished!");
        } catch (InterruptedException e) {
            System.out.println(""+e.getMessage());
        }
    }
}