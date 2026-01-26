class Table7 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println(i*7);
        }
    }
}

class Table13 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println(i*13);
        }
    }
}

class Table5 extends Thread {
    public void run(){
        for(int i=1;i<=10;i++){
            System.out.println(i*5);
        }
    }
}

class TableThread {
    public static void main(String[] args) {
        try{
            TableThread d = new TableThread(this);
            Table7 t1 = new Table7();
            Table13 t2 = new Table13();
            Table5 t3 = new Table5();
            t1.start();
            t2.start();
            t3.start();
            d.sleep(8000);
        }
        catch (InterruptedException e){
            System.out.println(e);
        }
    }
}
