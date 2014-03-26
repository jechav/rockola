/**
 * Javier Abell�n. 18 Mar 2006
 * 
 * Programa de ejemplo de como transmitir un fichero por un socket.
 * Esta es el mensaje que contiene los cachos de fichero que se van enviando
 * 
 */

package archivos.RockolaCliente;

import java.io.Serializable;

/**
 * Mensaje para pedir un fichero.
 * @author Javier Abell�n
 *
 */
public class MensajeDameFichero implements Serializable
{
    /** path completo del fichero que se pide */
    public String nombreFichero;
}
