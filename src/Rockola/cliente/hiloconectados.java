/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rockola.cliente;

import archivos.ClienteaRockola.Descargando;
import archivos.ClienteaRockola.MensajeDameFichero;
import archivos.ClienteaRockola.MensajeTomaFichero;
import archivos.RockolaCliente.RChiloenvio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Andres Echavarria y joche echavez esta clase recibe un objeto tipo
 * DefaultListModel, en cual se le agrega,modifica y remueva elemento
 */
public class hiloconectados extends Thread implements Runnable {

    private RockolaTCPcliente TCP;
    private DefaultListModel Modeloname, modeloaux, modelopistas, modeloMipistas;
    private javax.swing.JList JListacontactos;
    private javax.swing.JList JListapistas;
    private javax.swing.JList JListaMipistas;
    private ArrayList<Conversaciones> listaConversaciones;
    private String usuario;
    private Socket socket;
    int descargando = 0;
    int enviando = 0;
    
    //esta clase recibe jlista, para luego agregarle un modelo

    public hiloconectados(RockolaTCPcliente tcp, javax.swing.JList listacontactos, javax.swing.JList listapistas,javax.swing.JList JListaMipistas, ArrayList<Conversaciones> listaconversacion, String usua) {
        TCP = tcp;
        JListacontactos = listacontactos;
        JListapistas = listapistas;
        this.JListaMipistas = JListaMipistas;
        listaConversaciones = listaconversacion;
        
        usuario = usua;

    }

    @Override
    public void run() {
        String recibir = "", aux;
        String mensaje = "";
        String[] vec;
        Conversaciones aux1;
        int bandera;

        while (true) {
              // aqui miramos si se presiono el boton de descargar o compatir
            if (descargando !=1 && enviando != 1) {
                if(descargando == 2 || enviando == 2){
                    System.out.println(TCP.recibeNotificacion());
                    descargando = 0;
                    enviando = 0;
                }
                //verconectados
                aux = "";
                modeloaux = new DefaultListModel();
                setModeloname(new DefaultListModel());
                recibir = TCP.ComunicarMensaje("Verconectados");
                System.out.println("paso de recibir conectados");
                vec = recibir.split("%.%");
                for (int i = 0; i < vec.length; i = i + 2) {
                    if (vec[i] != null) {
                        aux = "" + vec[i] + "\t escucha:" + vec[i + 1];
                    }
                    getModeloname().addElement(aux);
                    modeloaux.addElement(vec[i]);
                }
                JListacontactos.setModel(getModeloname());
                //parte de recibir mensajeria
                bandera = -1;
                mensaje = TCP.ComunicarMensaje("CogerMensaje");
                vec = mensaje.split("%.%");
                if (!"Nada".equals(vec[0])) {
                    for (int i = 0; i < listaConversaciones.size(); i++) {
                        aux1 = listaConversaciones.get(i);
                        if (vec[0].equals(aux1.getDestinatario())) {
                            bandera = i;
                        }
                    }
                    if (bandera != -1) {
                        aux1 = listaConversaciones.get(bandera);
                        aux1.ColocarMensaje(vec[0] + " dijo:" + vec[1]);
                    } else {
                        aux1 = new Conversaciones(vec[0], usuario, TCP);
                        listaConversaciones.add(aux1);
                        aux1.ColocarMensaje(vec[0] + " dijo:" + vec[1]);
                    }
                }

                //Recibir Canciones de la rockola
                aux = "";
                modelopistas = new DefaultListModel();
                recibir = TCP.ComunicarMensaje("RecibirCancionesRockola");
                vec = recibir.split("%.%");
                for (int i = 0; i < vec.length; i++) {
                    if (vec[i] != null) {
                        aux = vec[i];
                    }
                    modelopistas.addElement(aux);
                }
                JListapistas.setModel(modelopistas);

                //Listar Mis pistas
                ListarMipistas();
                
               
                
                
                
                try {
                    sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(hiloconectados.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //Listar Mis pistas
    public void ListarMipistas(){
        String aux = "";
        modeloMipistas = new DefaultListModel();
        setModeloname(new DefaultListModel());
        File dir = new File(System.getProperty("user.dir")+"\\clientecanciones");
        String[] ficheros = dir.list();
        
        if (ficheros == null)
            System.out.println("No hay ficheros en el directorio especificado Del cliente");
        else { 
            for(int x=0;x<ficheros.length;x++){
                //System.out.println(ficheros[x]);
                modeloMipistas.addElement(ficheros[x]);
                
            }  
            JListaMipistas.setModel(modeloMipistas);
        }
    }
    //prepara al cliente para que reciba los paquetes de bits
    public String descargaCancion(String fichero) throws ClassNotFoundException {
        //Descargando ventanadescarga = new Descargando(null, false);   
        //ventanadescarga.setNombreCancion(fichero);
        //ventanadescarga.setVisible(true);
        
        descargando = 1;
        TCP.cominicaNotificacion("DescargaCancion");
        socket = TCP.getSocktCliente();
        
        try {
        // Se env�a un mensaje de petici�n de fichero.
       
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            MensajeDameFichero mensaje = new MensajeDameFichero();
            mensaje.nombreFichero = fichero;
            oos.writeObject(mensaje);
            
            System.out.println("Se envia un archivo");
            
            // Se abre un fichero para empezar a copiar lo que se reciba.
            FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/clientecanciones/" + mensaje.nombreFichero);

            // Se crea un ObjectInputStream del socket para leer los mensajes
            // que contienen el fichero.
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            MensajeTomaFichero mensajeRecibido;
            Object mensajeAux;
            int maximo = 1;
            int valor = 0;
            boolean flat = false;
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
                    fos.write(mensajeRecibido.contenidoFichero, 0, mensajeRecibido.bytesValidos);
                    if (!flat) {
                        maximo = mensajeRecibido.Tamaño_archivo;
                        flat = !flat;
                    }
                    System.out.println(fis.available());
                    valor = ((fis.available() * 100) / maximo);
                    System.out.println(valor);
                    //ventanadescarga.getprogressbar().setValue(valor);
                    //ventanadescarga.getPorcentaje().setText(valor+" %");
                    //pausa(200);
                } else {
                    // Si no es del tipo esperado, se marca error y se termina
                    // el bucle
                    System.err.println("Mensaje no esperado " + mensajeAux.getClass().getName());
                    break;
                }
                //pausa(300);
            } while (!mensajeRecibido.ultimoMensaje);

            // Se cierra socket y fichero
            fos.close();
            //ois.close();
            //socket.close();
            System.out.println("Terminó la descarga");
            descargando = 2;
        } catch (IOException e) {
            System.out.println("error al atender cliente");
        }

        

        return "listo";

    }
    //prepara el cliente para cargar el archivo y enviarlo mediante paquetes de bits
    public String cargandoCanción(String fichero){
        // Se abre el socket.
            Socket socket = TCP.getSocktCliente();
            enviando = 1;
            TCP.cominicaNotificacion("CargarCancion");
        try
        {
            
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            
            // Se env�a un mensaje de petici�n de fichero.
            archivos.RockolaCliente.MensajeTomaFichero Tomamensaje = new archivos.RockolaCliente.MensajeTomaFichero();
            Tomamensaje.nombreFichero = fichero;
            oos.writeObject(Tomamensaje);
            
            //fichero = System.getProperty("user.dir")+"/clientecanciones/"+fichero;
            
            boolean enviadoUltimo=false;
            // Se abre el fichero.
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/clientecanciones/"+fichero);
            
            // Se instancia y rellena un mensaje de envio de fichero
            archivos.RockolaCliente.MensajeTomaFichero mensaje = new archivos.RockolaCliente.MensajeTomaFichero();
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
                if (leidos < archivos.RockolaCliente.MensajeTomaFichero.LONGITUD_MAXIMA)
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
                mensaje = new archivos.RockolaCliente.MensajeTomaFichero();
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
            //oos.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        enviando = 2;
        return "Carga Exitosa";
    }

    public String enviarPistaActual(String miPista){
        return TCP.ComunicarMensaje("EnvioMiPista"+"%.%"+miPista);
        
    }
    
    public String getselecion(int selecionado) {
        String resp;
        resp = (String) modeloaux.get(selecionado);
        return resp;
    }

    public String getSeleccionArchivo(int id){
        return modelopistas.getElementAt(id).toString();
    }
    
    public String getSeleccionMusica(int id){
        return modeloMipistas.getElementAt(id).toString();
    }
    
    /**
     * @return the Modeloname
     */
    public DefaultListModel getModeloname() {
        return Modeloname;
    }

    /**
     * @param Modeloname the Modeloname to set
     */
    public void setModeloname(DefaultListModel Modeloname) {
        this.Modeloname = Modeloname;
    }

}
