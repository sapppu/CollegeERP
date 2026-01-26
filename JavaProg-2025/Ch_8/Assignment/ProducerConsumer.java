package com.company;

import java.util.Scanner;

class PC
{
    int n;
    boolean value = false;
    synchronized int getN() {
        if (!value)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        System.out.printf("\n Got: %d", n);
        value = false;
        notify();
        System.out.println("\n notify 1");
        return n;
    }
    synchronized void put(int n)
    {
        if(value)
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        this.n = n;
        value = true;
        System.out.printf("\n Put : %d",n);
        notify();
        System.out.println("\n Notify 2");

    }
}

class producer implements Runnable
{
    PC obj;

    producer(PC obj)
    {
        this.obj = obj;
        new Thread(this,"Producer").start();

    }

    @Override
    public void run() {
        int i=0;
        while(i<=30)
        {
            obj.put(i++);
        }

    }
}

class consumer implements Runnable
{
    PC obj;
    int i;

    consumer(PC obj)
    {
        this.obj = obj;
        new Thread(this,"Consumer").start();
    }

    @Override
    public void run() {
        while(i<=30)
        {
            obj.getN();
        }
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {
        PC obj = new PC();

            new producer(obj);
            new consumer(obj);

    }
}
