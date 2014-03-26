/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package archivos.RockolaCliente;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;


/**
 *
 * @author jochechavez
 */
public class RChilorecibe extends Thread {
    String fichero;
    ObjectInputStream ois;
    
    /**
     *
     * @param fichero
     * @param ois
     */
    public RChilorecibe(String fichero,ObjectInputStream ois){
        this.fichero = fichero;
        this.ois = ois;
    }
    @Override
    public void run(){
           
        try {

            // Se abre un fichero para empezar a copiar lo que se reciba.
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/rockolacanciones/" + fichero);

            // Se crea un ObjectInputStream del socket para leer los mensajes
            // que contienen el fichero.
            
            MensajeTomaFichero mensajeRecibido;
            Object mensajeAux;
            int maximo=1;
            int valor=0;
            boolean flat=false;
            //FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/rockolacanciones/" + fichero);
            
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
                    /*
                    if(!flat){
                        maximo = mensajeRecibido.Tamaño_archivo;
                        flat = !flat;
                    }          
                    System.out.println(fis.available());
                    valor = ((fis.available()*100)/maximo); 
                    System.out.println(valor);
                    */
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
            //ois.close();
            System.out.println("Terminó la carga");

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
