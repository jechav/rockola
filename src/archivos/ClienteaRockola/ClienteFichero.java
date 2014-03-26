/**
 * Javier Abell�n. 18 Mar 2006
 *
 * Programa de ejemplo de como transmitir un fichero por un socket. Esta es el
 * main con el cliente, que piede un fichero, lo muestra en pantalla y lo
 * escribe cambiando el nombre.
 */
package archivos.ClienteaRockola;

import Musica.reproductor;
import Musica.sonido;



/**
 * Pide un fichero al ServidorFichero, lo escribe en pantalla cuando lo recibe y
 * lo guarda en disco a�adiendo "_copia" al final del nombre del fichero.
 *
 * @author Jose Echavez, Andres Echavarria
 */
public class ClienteFichero {

    /**
     * Main del Cliente
     *
     * @param args de la l�nea de comandos. Se ignora.
     */
    public static void main(String[] args) {
        // Se crea el cliente y se le manda pedir el fichero.
        ClienteFichero cf = new ClienteFichero();
        cf.pide("001_008 Linkin Park - Point Of Authority.wav", "localhost", 35557);
    }

    /**
     *
     * @param cancion
     * @param servidor
     * @param puerto
     */
    public void pide(String cancion, String servidor, int puerto){
        
        Descargando ventanadescarga = new Descargando(null, false);   
        ventanadescarga.setNombreCancion(cancion);
        ventanadescarga.setVisible(true);

        hilorecibe hilorecib = new hilorecibe(cancion,servidor,puerto,ventanadescarga.getprogressbar() ,ventanadescarga.getPorcentaje() );
        hilorecib.run();
        ventanadescarga.setVisible(false);
        ventanadescarga = null;
        
        reproductor reproductor = new reproductor();
        reproductor.setVisible(true);
        reproductor.setNombre(cancion);
        sonido son = new sonido(cancion, reproductor.getJslider(), reproductor.getJlbDuracion(), reproductor.getJlbFrame());     
        son.run();

        
        
        
    }
}
