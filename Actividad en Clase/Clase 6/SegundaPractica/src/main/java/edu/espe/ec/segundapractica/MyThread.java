package edu.espe.ec.segundapractica;

/**
 *
 * @author Julio Blacio, Overnight Developers Squad, DCCO-ESPE
 */
public class MyThread extends Thread{

    MyThread(String nombreHilo) {
        super(nombreHilo);
        
    }
    
    @Override
    public void run(){
       for(int i = 0; i< 10; i++){
           System.out.println(i+(i+2)+getName());
        }
        System.out.println("Hilo Finalizado");
    }
    public static void main(String[] args) {
        new MyThread("HILO 1").start();
        new MyThread("HILO 2").start();
        new MyThread("HILO 3").start();
        new MyThread("HILO 4").start();
    }
}