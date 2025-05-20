package com.mycompany.threads;

/**
 *
 * @author Julio Blacio, Overnight Developers Squad, DCCO-ESPE
 */
public class MyThread extends Thread {
    
    MyThread(String nombreHilo){
        super(nombreHilo);
    }
    
    public void run(){
        for(int i=0; i < 10; i++)
                System.out.println(i + " - " + getName());
        System.out.println("Hilo finalizado");
    }
    
    public static void main(String[] args) {
        new MyThread("Julio").start();
        new MyThread("Lucas").start();
        new MyThread("Beto").start();
        new MyThread("Salet").start();
        new MyThread("Adrian").start();
        new MyThread("Broos").start();
        new MyThread("Darko").start();
        new MyThread("Carjunior").start();
        new MyThread("Nahin").start();
    }
    
}
