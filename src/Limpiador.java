import java.util.*;

/**
 * Created by Josek on 21/6/2017.
 */
public class Limpiador {

    public String[] limpiarLinea(String linea)
    {
        linea = linea.replaceAll("[\\s\t\\u00a0\"‘´(){}\\[\\]“”«»_—\\-–.…/,;%<>¡!¿?#|°=+*~•^►◄]", "\n");
        String[] listaPalabras = linea.replaceAll("[:]","").replaceAll("\n+", "\n").toLowerCase().split("\n");
        return listaPalabras;
    }

    public static Map<String, Integer> ordenarDCPorValor(Map<String, Integer> map, Boolean mayorAMenor )
    {
        List<Map.Entry<String, Integer>> lista = new LinkedList<Map.Entry<String, Integer>>( map.entrySet() );

        Collections.sort( lista, new Comparator<Map.Entry<String, Integer>>() {
            public int compare( Map.Entry<String, Integer> palabra1, Map.Entry<String, Integer> palabra2 )
            {
                if(mayorAMenor)
                    return palabra2.getValue().compareTo(palabra1.getValue());
                else
                    return palabra1.getValue().compareTo(palabra2.getValue());
            }
        } );

        Map<String, Integer> resultado = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : lista)
            resultado.put( entry.getKey(), entry.getValue() );

        return resultado;
    }

    public static Map<String, Preprocesador.ValoresPalabra> ordenarDGPorValor(Map<String, Preprocesador.ValoresPalabra> map, Boolean mayorAMenor )
    {
        List<Map.Entry<String, Preprocesador.ValoresPalabra>> lista = new LinkedList<Map.Entry<String, Preprocesador.ValoresPalabra>>( map.entrySet() );

        Collections.sort( lista, new Comparator<Map.Entry<String, Preprocesador.ValoresPalabra>>() {
            public int compare( Map.Entry<String, Preprocesador.ValoresPalabra> palabra1, Map.Entry<String, Preprocesador.ValoresPalabra> palabra2 )
            {
                if(mayorAMenor)
                    return palabra2.getValue().cantidadDocumentos.compareTo(palabra1.getValue().cantidadDocumentos);
                else
                    return palabra1.getValue().cantidadDocumentos.compareTo(palabra2.getValue().cantidadDocumentos);
            }
        } );

        Map<String, Preprocesador.ValoresPalabra> resultado = new LinkedHashMap<>();
        for (Map.Entry<String, Preprocesador.ValoresPalabra> entry : lista)
            resultado.put( entry.getKey(), entry.getValue() );

        return resultado;
    }
}