import java.io.File;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * Created by Josek on 22/6/2017.
 */
public class Preprocesador implements Runnable{
    @Override
    public void run(){
        try{
            preprocesar();
        }
        catch (Exception ex) {
            System.out.println("Error hilo");
        }
    }

    static class ValoresPalabra{
        public Integer cantidadDocumentos;
        public Boolean aparicion;
    };

    private String nombreHilo;
    private String rutaColeccion;
    private Integer archivoInicial;
    private Integer archivoFinal;
    private static Semaphore mutex_vocabularioGlobal = new Semaphore(1);
    private static CyclicBarrier barrier = new CyclicBarrier(10);
    private static Map<String, ValoresPalabra> vocabularioGlobal; // Key: palabra, Value: cantidad de documentos donde aparece

    Preprocesador(String nombreHilo, String rutaColeccion, Integer archivoInicial, Integer archivoFinal){
        this.nombreHilo = nombreHilo;
        this.rutaColeccion = rutaColeccion;
        this.archivoInicial = archivoInicial;
        this.archivoFinal = archivoFinal;
        this.vocabularioGlobal = new TreeMap<>();
    }

    public void preprocesar(){

        /** Para cada archivo */
        for (int i = this.archivoInicial; i < this.archivoFinal; i++) {
            /** Carga el archivo */
            File archivoActual = new File(this.rutaColeccion + "/" + i); // Archivo actual
            String nombreArchivo = Integer.toString(i);

            if (archivoActual.isFile()) {
                /** Extrae texto de documento y lo separa en palabras */
                Extractor extractor = new Extractor(archivoActual);
                String[] listaPalabras = extractor.tokenizar(nombreArchivo);

                /** Cuenta palabras y las agrega a diccionario local (frecuencia) y a diccionario global (n sub i). */
                Contador contador = new Contador(nombreArchivo, listaPalabras);

                try {mutex_vocabularioGlobal.acquire();}
                catch (InterruptedException e) {e.printStackTrace();}

                this.vocabularioGlobal = contador.contarApariciones(this.vocabularioGlobal);
                /** Resetea aparicion en documento */
                for (Map.Entry<String, Preprocesador.ValoresPalabra> palabra : this.vocabularioGlobal.entrySet()) {
                    ValoresPalabra vp = palabra.getValue();
                    if(vp.aparicion) {
                        vp.aparicion = false;
                        this.vocabularioGlobal.put(palabra.getKey(), vp);
                    }
                }

                mutex_vocabularioGlobal.release();

                contador.imprimirFrecuencias(nombreArchivo);

                //System.out.println("Archivo " + nombreArchivo + " listo.");
            } else
                System.out.println("Archivo " + nombreArchivo + " no se pudo procesar.");
        }

        try{barrier.await();}
        catch (Exception ex){System.out.println("No espera a los otros hilos.");}

        if(this.nombreHilo == "1") {
            System.out.println("\nImprimiendo vocabulario.");

            /** Ordenada palabras de vocabulario globarl de mayor a menor frecuencia, y las escribe en archivo */
            Limpiador limpiador = new Limpiador();
            Map<String, Preprocesador.ValoresPalabra> vocabularioLocalOrdenado = limpiador.ordenarDGPorValor(this.vocabularioGlobal, true);

            Archivo archivo = new Archivo("Vocabulario//", "vocabulario", ".txt");
            for (Map.Entry<String, Preprocesador.ValoresPalabra> palabra : vocabularioLocalOrdenado.entrySet()) {
                archivo.escribirEnArchivo(palabra.getKey() + " " + palabra.getValue().cantidadDocumentos, true);
            }

            System.out.println("\nPreprocesamiento completado.");
        }
    }
}
