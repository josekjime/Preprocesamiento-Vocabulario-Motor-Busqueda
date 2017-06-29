import java.util.concurrent.CyclicBarrier;

/**
 * Created by Josek on 23/6/2017.
 */
public class Hilos {
    public void iniciarHilos(String rutaColeccion, Integer cantidadArchivos){
        /*Preprocesador preprocesador1 = new Preprocesador("1", rutaColeccion, 0, 7991);
        Preprocesador preprocesador2 = new Preprocesador("2", rutaColeccion, 7991, 7991*2);
        Preprocesador preprocesador3 = new Preprocesador("3", rutaColeccion, 7991*2, 7991*3);
        Preprocesador preprocesador4 = new Preprocesador("4", rutaColeccion, 7991*3, 7991*4);
        Preprocesador preprocesador5 = new Preprocesador("5", rutaColeccion, 7991*4, 7991*5);
        Preprocesador preprocesador6 = new Preprocesador("6", rutaColeccion, 7991*5, 7991*6);
        Preprocesador preprocesador7 = new Preprocesador("7", rutaColeccion, 7991*6, 7991*7);
        Preprocesador preprocesador8 = new Preprocesador("8", rutaColeccion, 7991*7, 7991*8);
        Preprocesador preprocesador9 = new Preprocesador("9", rutaColeccion, 7991*8, 7991*9);
        Preprocesador preprocesador10 = new Preprocesador("10", rutaColeccion, 7991*9, 79908);*/

        Integer cantidad = cantidadArchivos/10;
        Preprocesador preprocesador1 = new Preprocesador("1", rutaColeccion, 0, cantidad);
        Preprocesador preprocesador2 = new Preprocesador("2", rutaColeccion, cantidad, 2798*2);
        Preprocesador preprocesador3 = new Preprocesador("3", rutaColeccion, cantidad*2, cantidad*3);
        Preprocesador preprocesador4 = new Preprocesador("4", rutaColeccion, cantidad*3, cantidad*4);
        Preprocesador preprocesador5 = new Preprocesador("5", rutaColeccion, cantidad*4, cantidad*5);
        Preprocesador preprocesador6 = new Preprocesador("6", rutaColeccion, cantidad*5, cantidad*6);
        Preprocesador preprocesador7 = new Preprocesador("7", rutaColeccion, cantidad*6, cantidad*7);
        Preprocesador preprocesador8 = new Preprocesador("8", rutaColeccion, cantidad*7, cantidad*8);
        Preprocesador preprocesador9 = new Preprocesador("9", rutaColeccion, cantidad*8, cantidad*9);
        Preprocesador preprocesador10 = new Preprocesador("10", rutaColeccion, cantidad*9, cantidadArchivos);

        Thread hilo1 = new Thread(preprocesador1);
        Thread hilo2 = new Thread(preprocesador2);
        Thread hilo3 = new Thread(preprocesador3);
        Thread hilo4 = new Thread(preprocesador4);
        Thread hilo5 = new Thread(preprocesador5);
        Thread hilo6 = new Thread(preprocesador6);
        Thread hilo7 = new Thread(preprocesador7);
        Thread hilo8 = new Thread(preprocesador8);
        Thread hilo9 = new Thread(preprocesador9);
        Thread hilo10 = new Thread(preprocesador10);

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
        hilo5.start();
        hilo6.start();
        hilo7.start();
        hilo8.start();
        hilo9.start();
        hilo10.start();
    }
}
