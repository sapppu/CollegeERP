class  SychronizedMethod implements  Runnable{
    class_A obj1;
    Thread t;

    public SychronizedMethod(class_A c) {
        obj1=c;
        t=new Thread(this);
    }
    @Override
    public void run(){
            obj1.printValue();
    }

    public static void main(String[] args) {
        class_A ca = new class_A();
        SychronizedMethod one = new SychronizedMethod(ca);
        one.t.start();
        SychronizedMethod two = new SychronizedMethod(ca);
        two.t.start();
        SychronizedMethod three = new SychronizedMethod(ca);
        three.t.start();
    }
}

class class_A{
    synchronized void printValue(){
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