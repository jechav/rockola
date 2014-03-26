/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rockola.servidos;


import archivos.ClienteaRockola.hiloenvio;
import Rockola.Interface.interfaces;
import archivos.RockolaCliente.RChilorecibe;
import archivos.ClienteaRockola.MensajeDameFichero;
import archivos.RockolaCliente.MensajeTomaFichero;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres echavarria y joche echavez
 */
public class RockolaIMplementacionServer implements interfaces{
    
    private RockolaClientes cliente;
    private ArrayList<RockolaClientes> listaCliente;
    private ArrayList<String> listaCanciones;
    
    /**
     *
     * @param lista
     */
    public RockolaIMplementacionServer(ArrayList<RockolaClientes> lista){
            cliente = null;
            listaCliente = lista;
    }

    @Override
    public String registrar(String name, String contraseña) {
        int encontrado = -1;
        String resp = "Registrado";
        for(int i=0; i<listaCliente.size(); i++){
            if(listaCliente.get(i).getNombre().equals(name)){
                encontrado=1;
                break;
            }
        }
        if(encontrado!=-1){
            resp="Ya esta Registrado"; 
        }else{
            RockolaClientes nuevocliente = new RockolaClientes(name, contraseña);
            listaCliente.add(nuevocliente);
        }
        return resp;
    }

    @Override
    public String IniciarSesion(String name, String contraseña) {
        int encontrado = -1;
        String resp = "Error de nombre o contraseña";
        for(int i=0; i<listaCliente.size(); i++){
            if(listaCliente.get(i).getNombre().equals(name)){
                encontrado=i;
                break;
            }
        }
        if(encontrado!=-1){
            if(listaCliente.get(encontrado).getContraseña().equals(contraseña )&& ("Inactivo".equals(listaCliente.get(encontrado).getEstado()))){
                    cliente = listaCliente.get(encontrado);
                    cliente.setEstado("Activo");
                    resp="Iniciar";
            }
        }
        return resp; 
    }

    @Override
    public String VerConctato() {
        String resp = "";
        RockolaClientes aux;
        for(int i=0; i<listaCliente.size(); i++){
            if(listaCliente.get(i).getEstado().equals("Activo")){
                    aux = listaCliente.get(i);
                    resp = resp+aux.getNombre()+"%.%";
                    resp = resp+aux.getCancion()+"%.%";
                    }
        }
        return resp;
    }

    @Override
    public String CompatirCancion(Socket cliente) {
        System.out.println("entro a la implementacion");
                try {
            
            // Se lee el mensaje de petici�n de fichero del cliente.
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            Object mensaje = ois.readObject();

            // Si el mensaje es de petici�n de fichero
            if (mensaje instanceof MensajeTomaFichero) {
                // Se muestra en pantalla el fichero pedido y se envia
                System.out.println("Suben: "+ ((MensajeTomaFichero) mensaje).nombreFichero);

                //enviaFichero(((MensajeDameFichero) mensaje).nombreFichero,
                //new ObjectOutputStream(cliente.getOutputStream()));
                RChilorecibe hilorecib = new RChilorecibe(((MensajeTomaFichero) mensaje).nombreFichero, ois);
                hilorecib.run();
            } else {
                // Si no es el mensaje esperado, se avisa y se sale todo.
                System.err.println("Mensaje no esperado " + mensaje.getClass().getName());
            }

            // Cierre de sockets 
            //cliente.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "compartio";
    }

    @Override
    public String DescargaCancion(Socket cliente) {
        //System.out.println("entro a la implementacion");
        
        // Se lee el mensaje de petici�n de fichero del cliente.
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(cliente.getInputStream());
            Object mensaje = ois.readObject();
            System.out.println("Me piden: " + ((MensajeDameFichero) mensaje).nombreFichero);
        
        
        

            //enviaFichero(((MensajeDameFichero) mensaje).nombreFichero,
            //new ObjectOutputStream(cliente.getOutputStream()));
            hiloenvio hiloenv = new hiloenvio(((MensajeDameFichero) mensaje).nombreFichero, new ObjectOutputStream(cliente.getOutputStream()));
            hiloenv.run();
        } catch (IOException ex) {
            Logger.getLogger(RockolaIMplementacionServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RockolaIMplementacionServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "listo";
    }

    @Override
    public String cerrarsesion() {
        String resp = "Cerrar";
        cliente.setEstado("Inactivo");
        cliente=null;
        return resp;
    }

    @Override
    public void salir() {
        
    }

    @Override
    public String enviarpistaactual(String pista) {
        cliente.setCancion(pista);
        return "cambio";
    }

    @Override
    public String SacarMensaje(){
           return cliente.cogermensaje();
    }
    @Override
    public String EnviarMensaje(String destinatario,String remitente, String txt) {
        
        RockolaClientes aux=null;
        String resp = "Mensaje Enviado";
        for(int i=0; i<listaCliente.size(); i++){
            if(listaCliente.get(i).getNombre().equals(destinatario)){
                aux = listaCliente.get(i);
                break;
            }
        }
        aux.agregarmensaje(remitente+"%.%"+txt);
        return resp;
    }

    @Override
    public String recibirCancionesRockola() {
        String resp = "";
        
        File dir = new File(System.getProperty("user.dir")+"\\rockolacanciones");
        String[] ficheros = dir.list();
        
        if (ficheros == null)
            System.out.println("No hay ficheros en el directorio especificado");
        else { 
            for (int x=0;x<ficheros.length;x++){
                //System.out.println(ficheros[x]);
                resp = resp+ficheros[x]+"%.%";
                
            }      
        }
        return resp;
        
    }
}
