/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.ClienteaRockola;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jochechavez
 */
public class hiloenvio extends Thread{
    String fichero;
    ObjectOutputStream oos;
   
    /**
     *
     * @param fichero
     * @param oos
     */
    public hiloenvio(String fichero,ObjectOutputStream oos){
        this.fichero = fichero;
        this.oos = oos;
    }
    
    @Override
    public void run(){
        /**
     * Env�a el fichero indicado a trav�s del ObjectOutputStream indicado.
     * @param fichero Nombre de fichero
     * @param oos ObjectOutputStream por el que enviar el fichero
     */

        fichero = System.getProperty("user.dir")+"/rockolacanciones/"+fichero;

        try
        {
            boolean enviadoUltimo=false;
            // Se abre el fichero.
            FileInputStream fis = new FileInputStream(fichero);
            //System.out.println(fichero);
            // Se instancia y rellena un mensaje de envio de fichero
            MensajeTomaFichero mensaje = new MensajeTomaFichero();
            mensaje.nombreFichero = fichero;
            mensaje.Tamaño_archivo = fis.available();
            
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
                //pausa();
            }
            
            if (enviadoUltimo==false)
            {
                
                mensaje.ultimoMensaje=true;
                mensaje.bytesValidos=0;
                oos.writeObject(mensaje);
                System.out.println("El ultimo mensaje "+mensaje);
            }
            // Se cierra el ObjectOutputStream
            //oos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    public void pausa(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(hiloenvio.class.getName()).log(Level.SEVERE, null, ex);
        }
}    

}
