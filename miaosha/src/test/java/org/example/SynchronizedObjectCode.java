package org.example;

public class SynchronizedObjectCode implements Runnable{

    static SynchronizedObjectCode instance = new SynchronizedObjectCode();

    @Override
    public void run() {
        System.out.println("");
    }

    public static void  main(String[] args){
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while(t1.isAlive() || t2.isAlive()){

        }
        System.out.println("finshed");
    }
}
