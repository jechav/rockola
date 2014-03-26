

package Rockola.cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author joche echavez y andres echavarria
 */
public class RockolaTCPcliente {
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    //el constructor recibe el puerto por dondegyryuouib va hacer la comunicacion
    
    public RockolaTCPcliente(int port){
        try {
            socket = new Socket("Localhost",port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("error al iniciar conexion "+ex.getMessage());
        }
    }
    //este metodo coloca el msj que recibe en el buffer de salida,y espera la respuesta del servidor
    public String ComunicarMensaje(String msj){
        try{
            out.writeUTF(msj);
            out.flush();
            System.out.println("enviado "+msj);
            msj = in.readUTF();
            //System.out.println(msj);
        }catch(IOException e){
            System.out.println("error al enviar el mensaje  "+e.getMessage());
        }
        
        return msj;
    }
    //este metodo escribe en el buffer  el mensaje que recibe, sin recibir una respuesta del servidor
    public String cominicaNotificacion(String msj){
        try{
            out.writeUTF(msj);
            out.flush();
            System.out.println("enviado "+msj);
        }catch(IOException e){
            System.out.println("error al enviar el mensaje  "+e.getMessage());
        }
        
        return "ENVIADO"+msj;
    }
    //este metodo reciber un mensaje del buffer 
    public String recibeNotificacion(){
        try{
            String msj = in.readUTF();
          
        }catch(IOException e){
            System.out.println("error al recibir el mensaje  "+e.getMessage());
        }
        
        return "Reibido";
    }
    //este metodo devuelve el socket que utiliza
    public Socket getSocktCliente(){
        return socket;
    }
}
