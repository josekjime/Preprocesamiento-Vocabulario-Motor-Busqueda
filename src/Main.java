import java.io.File;

public class Main {

    public static void main(String[] args) {

        /** Parámetros */
        //String rutaColeccion = "C://Users//Josek//Desktop//Dataset_RI//Dataset_RI//files"; //args[]
        String rutaColeccion = System.getProperty("user.home") + "//Desktop//Dataset_RI//Dataset_RI//files//";

        /** Cantidad de archivos en colección */
        Integer cantidadArchivos = (new File(rutaColeccion)).listFiles().length;
        //System.out.println("Cantidad de archivos : " + cantidadArchivos + "\n");

        Hilos hilos = new Hilos();
        hilos.iniciarHilos(rutaColeccion,cantidadArchivos);
    }
}
