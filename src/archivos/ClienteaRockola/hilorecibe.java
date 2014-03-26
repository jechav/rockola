/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.ClienteaRockola;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author jochechavez
 */
public class hilorecibe extends Thread {
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
    public hilorecibe(String fichero, String servidor, int puerto, JProgressBar jpbar, JLabel jlbporcentaje){
        this.fichero = fichero;
        this.servidor = servidor;
        this.puerto = puerto;
        this.jpbar = jpbar;
        this.jlbporcentaje = jlbporcentaje;
    }
    @Override
    public void run(){
            /**
     * Establece comunicaci�n con el servidor en el puerto indicado. Pide el
     * fichero. Cuando llega, lo escribe en pantalla y en disco duro.
     *
     * @param fichero path completo del fichero que se quiere
     * @param servidor host donde est� el servidor
     * @param puerto Puerto de conexi�n
     */
        
        try {
            // Se abre el socket.
            Socket socket = new Socket(servidor, puerto);

            // Se env�a un mensaje de petici�n de fichero.
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MensajeDameFichero mensaje = new MensajeDameFichero();
            mensaje.nombreFichero = fichero;
            oos.writeObject(mensaje);

            // Se abre un fichero para empezar a copiar lo que se reciba.
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/clientecanciones/" + mensaje.nombreFichero);

            // Se crea un ObjectInputStream del socket para leer los mensajes
            // que contienen el fichero.
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MensajeTomaFichero mensajeRecibido;
            Object mensajeAux;
            int maximo=1;
            int valor=0;
            boolean flat=false;
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/clientecanciones/" + mensaje.nombreFichero);
            
            do {
                // Se lee el mensaje en una variabla auxiliar
                mensajeAux = ois.readObject();

                // Si es del tipo esperado, se trata
                if (mensajeAux instanceof MensajeTomaFichero) {
                    mensajeRecibido = (MensajeTomaFichero) mensajeAux;
                    
                    // Se escribe en pantalla y en el fichero
                    //System.out.print(new String(
                      //      mensajeRecibido.contenidoFichero, 0,
                        //    mensajeRecibido.bytesValidos));
                    fos.write(mensajeRecibido.contenidoFichero,0,mensajeRecibido.bytesValidos);
                    if(!flat){
                        maximo = mensajeRecibido.Tamaño_archivo;
                        flat = !flat;
                    }          
                    System.out.println(fis.available());
                    valor = ((fis.available()*100)/maximo); 
                    System.out.println(valor);
                    //jpbar.setValue(valor);
                    //jlbporcentaje.setText(valor+" %");
                    //pausa(200);
                } else {
                    // Si no es del tipo esperado, se marca error y se termina
                    // el bucle
                    System.err.println("Mensaje no esperado "+ mensajeAux.getClass().getName());
                    break;
                }
                //pausa(300);
            } while (!mensajeRecibido.ultimoMensaje);

            // Se cierra socket y fichero
            fos.close();
            ois.close();
            socket.close();
            System.out.println("Terminó la descarga");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param mlSeg
     */
    public void pausa(int mlSeg) {
        try {
            // pausa para el splash
            Thread.sleep(mlSeg);
        } catch (Exception e) {
        }
    }
    
}
