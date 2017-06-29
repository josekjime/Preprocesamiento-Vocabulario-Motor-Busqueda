import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

/**
 * Created by Josek on 22/6/2017.
 */
public class Contador {
    private String nombreArchivo;
    private String[] listaPalabras;
    private Map<String, Integer> vocabularioLocal; // Key: palabra, Value: cantidad de apariciones en documento

    Contador(String nombreArchivo, String[] listaPalabras) {
        this.nombreArchivo = nombreArchivo;
        this.listaPalabras = listaPalabras;
        this.vocabularioLocal = new TreeMap<>();
    }

    public Map<String, Preprocesador.ValoresPalabra> contarApariciones(Map<String, Preprocesador.ValoresPalabra> vocabularioGlobal) {
        /** Agrega palabras a diccionario */
        for (String palabra : this.listaPalabras) {
            if (palabra != "\n" && !palabra.isEmpty() && !palabra.contains("http")) {
                /** Agrega palabra a vocabulario local */
                if(!this.vocabularioLocal.containsKey(palabra))
                    this.vocabularioLocal.put(palabra, 1);
                else
                    this.vocabularioLocal.put(palabra, this.vocabularioLocal.get(palabra) + 1);

                /** Agrega palabra a vocabulario global */
                Preprocesador.ValoresPalabra vp = new Preprocesador.ValoresPalabra();
                if(!vocabularioGlobal.containsKey(palabra)) {
                    vp.cantidadDocumentos = 1;
                    vp.aparicion = true;
                    vocabularioGlobal.put(palabra, vp);
                }
                else{
                    vp = vocabularioGlobal.get(palabra);
                    /** Si es la primera vez que aparece la palabra en el documento */
                    if(!vp.aparicion){
                        vp.cantidadDocumentos += 1;
                        vp.aparicion = true;
                        vocabularioGlobal.put(palabra, vp);
                    }
                }
            }
        }

        return vocabularioGlobal;
    }

    public void imprimirFrecuencias(String nombreArchivoSalida){
        Archivo archivo = new Archivo("Frecuencias//", nombreArchivoSalida, ".freq");
        Limpiador limpiador = new Limpiador();

        /** Ordenada palabras de vocabulario local de mayor a menor frecuencia, y las escribe en archivo */
        Map<String, Integer> vocabularioLocalOrdenado = limpiador.ordenarDCPorValor(this.vocabularioLocal, true);

        for (Map.Entry<String, Integer> palabra : vocabularioLocalOrdenado.entrySet()) {
            archivo.escribirEnArchivo(palabra.getKey() + " " + palabra.getValue(), true);
        }
    }
}
