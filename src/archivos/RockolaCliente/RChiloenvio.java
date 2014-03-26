/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.RockolaCliente;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author jochechavez
 */
public class RChiloenvio extends Thread{
    String fichero;
    String servidor;
    int puerto;
    JLabel jlbporcentaje;
    JProgressBar jpbar;
   
    /**
     *
     * @param fichero
     * @param servidor
     * @param puerto
     * @param jpbar
     * @param jlbporcentaje
     */
    public RChiloenvio(String fichero, String servidor, int puerto, JProgressBar jpbar, JLabel jlbporcentaje){
        this.fichero = fichero;
        this.servidor = servidor;
        this.puerto = puerto;
        this.jpbar = jpbar;
        this.jlbporcentaje = jlbporcentaje;
    }
    
    @Override
    public void run(){
        /**
     * Env�a el fichero indicado a trav�s del ObjectOutputStream indicado.
     * @param fichero Nombre de fichero
     * @param oos ObjectOutputStream por el que enviar el fichero
     */

        
        try
        {
            // Se abre el socket.
            Socket socket = new Socket(servidor, puerto);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            // Se env�a un mensaje de petici�n de fichero.
            MensajeTomaFichero Tomamensaje = new MensajeTomaFichero();
            Tomamensaje.nombreFichero = fichero;
            oos.writeObject(Tomamensaje);
            
            //fichero = System.getProperty("user.dir")+"/clientecanciones/"+fichero;
            
            boolean enviadoUltimo=false;
            // Se abre el fichero.
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/clientecanciones/"+fichero);
            
            // Se instancia y rellena un mensaje de envio de fichero
            MensajeTomaFichero mensaje = new MensajeTomaFichero();
            mensaje.nombreFichero = fichero;
            mensaje.Tamaño_archivo = fis.available();
            
            //System.out.println("Disponible archivo abierto"+fis.available());
            // Se leen los primeros bytes del fichero en un campo del mensaje
            int leidos = fis.read(mensaje.contenidoFichero);
            int count=0;
            // Bucle mientras se vayan leyendo datos del fichero
            while (leidos > -1)
            {
                
                // Se rellena el n�mero de bytes leidos
                mensaje.bytesValidos = leidos;
                
                // Si no se han leido el m�ximo de bytes, es porque el fichero
                // se ha acabado y este es el �ltimo mensaje
                if (leidos < MensajeTomaFichero.LONGITUD_MAXIMA)
                {
                    mensaje.ultimoMensaje = true;
                    enviadoUltimo=true;
                }
                else
                    mensaje.ultimoMensaje = false;
                
                // Se env�a por el socket
                oos.writeObject(mensaje);
                
                System.out.println(count+"----------------Se envió "+mensaje+" byte-----------------");
                count++;
                // Si es el �ltimo mensaje, salimos del bucle.
                if (mensaje.ultimoMensaje)
                    break;
                
                // Se crea un nuevo mensaje
                mensaje = new MensajeTomaFichero();
                mensaje.nombreFichero = fichero;
                
                // y se leen sus bytes.
                leidos = fis.read(mensaje.contenidoFichero);
            }
            
            if (enviadoUltimo==false)
            {
                
                mensaje.ultimoMensaje=true;
                mensaje.bytesValidos=0;
                oos.writeObject(mensaje);
                System.out.println("El ultimo mensaje "+mensaje);
            }
            // Se cierra el ObjectOutputStream
            oos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
