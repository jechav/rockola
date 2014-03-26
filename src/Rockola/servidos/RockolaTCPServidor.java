
package Rockola.servidos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author joche echavez y andres echavarria
 */
public class RockolaTCPServidor {
    
    private ServerSocket serversocket;
    private Socket socketCliente;
    private ArrayList<RockolaClientes> listaCliente;
    
    /**
     *
     * @param port
     */
    public RockolaTCPServidor(int port){
        try{
            serversocket = new ServerSocket(port);
        }catch(IOException ex){
            System.out.println("error al conectarse");
        }
        listaCliente = new ArrayList<>();
    }
    /**
     *
     */
    public void atender(){
        while(true){
            try{
                System.out.println("esperando ususario--");
                socketCliente = serversocket.accept();
                System.out.println("nuevo usuario--");
                hiloAtencion hilo = new hiloAtencion(socketCliente,listaCliente);
                hilo.start();
            }catch(IOException e){
                System.out.println("error al atender cliente");
            }
        }
    }
    /**
     *
     * @param args
     */
    public static void main(String[] args){
         
            RockolaTCPServidor  servidor = new RockolaTCPServidor(2014);
            servidor.atender();
          
        
    }
}
