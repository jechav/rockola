/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Rockola.servidos;

import archivos.ClienteaRockola.hiloenvio;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joche echavez y andres echavarria
 */
public class hiloAtencion extends Thread implements Runnable{
    
    private Socket socket; 
    private DataInputStream in;
    private DataOutputStream out;
    private RockolaIMplementacionServer Rockolaimplementacion;
    
    /**
     *
     * @param cliente
     * @param lista
     */
    public hiloAtencion(Socket cliente ,ArrayList<RockolaClientes> lista){
      try{
      
        socket = cliente;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
      
      }catch(IOException e){
          System.out.println("error al asigna las io");
      }
      Rockolaimplementacion = new RockolaIMplementacionServer(lista);
      
    }
    @Override
    public void run(){
        String entrada = "";
        String Salida = "";
        while(!entrada.equals("Salir")){
                try{
                 entrada = in.readUTF();
                 System.out.println("recibir "+entrada );
                 Salida = funcion(entrada);
                }catch(IOException e){
                 System.out.println("hilo, error de lectura "+e.getMessage() );
                }
               try{
                   out.writeUTF(Salida);
                   out.flush();
                   System.out.println("envie "+Salida );
                }catch(IOException e){
                   System.out.println("hilo, error de escritura "+e.getMessage());
                }            
        }
        System.out.println("cliente salio");
    }
    /**
     *
     * @param entrada
     * @return
     */
    public String funcion(String entrada){
        String resp = "Error" ;
        String vec[]=entrada.split("%.%");
        switch(vec[0]){   
            case "Iniciar":
                resp = ""+Rockolaimplementacion.IniciarSesion(vec[1], vec[2]);
                break;
            case "Registrar":
                resp = ""+Rockolaimplementacion.registrar(vec[1], vec[2]);
                break;  
            case "Cerrar":
                resp = ""+Rockolaimplementacion.cerrarsesion();
                break;
            case "Verconectados":
                resp = ""+Rockolaimplementacion.VerConctato();
                break;
            case "EnviarMensaje":
                resp = ""+Rockolaimplementacion.EnviarMensaje(vec[1], vec[2], vec[3]);
                break;
            case "CogerMensaje":
                resp = ""+Rockolaimplementacion.SacarMensaje();
               break;   
            case "RecibirCancionesRockola":
                resp = ""+Rockolaimplementacion.recibirCancionesRockola();
            break;
                
            case "DescargaCancion":
                resp = ""+Rockolaimplementacion.DescargaCancion(socket);
             break;
                
            case "CargarCancion":
                resp = ""+Rockolaimplementacion.CompatirCancion(socket);
            break;
            case "EnvioMiPista":
                resp = ""+Rockolaimplementacion.enviarpistaactual(vec[1]);
                break;
            
            case "Salir":
                Rockolaimplementacion.salir();
                resp = "Salir";
                break;
        }
        return resp;
    }
}
