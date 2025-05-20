

package ec.edu.espe.hilobasico;

/**
 *
 * @author Julio Blacio, Overnight Developers Squad, DCCO-ESPE
 */
public class HiloBasico extends Thread {
    @Override
    public void run(){
        System.out.println("Hola desde el hilo" +Thread.currentThread().getName());
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("Hilo interrumpido");
        }
    }
    public static void main(String[] args) {
        HiloBasico hilo1 = new HiloBasico();
        HiloBasico hilo2 = new HiloBasico();
        HiloBasico hilo3 = new HiloBasico();
        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("Hola desde el hilo principal" +Thread.currentThread().getName());


    }
}