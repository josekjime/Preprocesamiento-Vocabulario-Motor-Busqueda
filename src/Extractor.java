import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

/**
 * Created by Josek on 20/6/2017.
 */
public class Extractor {
    private String nombreArchivo;
    private InputStream documento;

    Extractor(File doc) {
        this.nombreArchivo = doc.getName();

        try {
            this.documento = new FileInputStream(doc);
        }
        catch (Exception ex) {
            System.out.println("Error al leer el archivo " + doc.getName() + " .");
        }
    }

    public String[] tokenizar(String nombreArchivoSalida) {
        String[] listaPalabras = new String[]{};
        Limpiador limpiador = new Limpiador();

        Parser parser = new AutoDetectParser();
        org.xml.sax.ContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        ParseContext parseContext = new ParseContext();

        try {
            /** Extrae texto de documento */
            parser.parse(this.documento, handler, metadata, parseContext);

            /** Limpia texto y separa en palabras */
            listaPalabras = limpiador.limpiarLinea(handler.toString());

            /** Escribe palabras en archivo */
            Archivo archivo = new Archivo("Tokens//", nombreArchivoSalida, ".tok");
            for (String palabra : listaPalabras) {
                if (palabra != "\n" && !palabra.isEmpty() && !palabra.contains("http")) {
                    archivo.escribirEnArchivo(palabra, true);
                }
            }
        }
        catch (Exception ex){
            System.out.println("Error al extraer del archivo " + this.nombreArchivo + " .");
        }

        return listaPalabras;
    }
}
