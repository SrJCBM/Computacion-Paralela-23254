package ec.edu.espe.hilomedio;

/**
 *
 * @author Julio Blacio, Overnight Developers Squad, DCCO-ESPE
 */
public class HiloMedio extends Thread {
    @Override
    public void run() {
        for(int i=0; i <= 5; i++){
            if(isInterrupted()){
                System.out.println(getName() + " fue interrumpido");
                return;
            }
            System.out.println(getName() + " ejecutando paso" + i);
        }
    }

    public static void main(String[] args) {
        HiloMedio hilo1 = new HiloMedio();
        HiloMedio hilo2 = new HiloMedio();
        HiloMedio hilo3 = new HiloMedio();

        hilo1.start();
        try {
            hilo1.join();
            hilo2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
       hilo2.start();
       hilo3.start();
    }   
}
