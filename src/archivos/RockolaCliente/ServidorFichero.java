/**
 * Javier Abell�n, 18 Mar 2006
 *
 * Programa de ejemplo de como transmitir un fichero por un socket. Esta es la
 * parte del servidor.
 */
package archivos.RockolaCliente;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clases servidora que env�a un fichero al primer cliente que se lo pida.
 *
 * @author Javier Abell�n
 */
public class ServidorFichero {

    /**
     * Instancia la clase servidora y la pone a la escucha del puerto 35557
     *
     * @param args de la l�nea de comandos.
     */
    public static void main(String[] args) {
        ServidorFichero sf = new ServidorFichero();
        sf.escucha(35557);
    }

    /**
     * Se escucha el puerto indicado en espera de clientes a los que enviar el
     * fichero.
     *
     * @param puerto El puerto de escucha
     */
    public void escucha(int puerto) {
        try {
            // Se abre el socket servidor
            ServerSocket socketServidor = new ServerSocket(puerto);

            // Se espera un cliente
            Socket cliente = socketServidor.accept();

            // Llega un cliente.
            System.out.println("Aceptado cliente");

            // Cuando se cierre el socket, esta opci�n hara que el cierre se
            // retarde autom�ticamente hasta 10 segundos dando tiempo al cliente
            // a leer los datos.
            cliente.setSoLinger(true, 10);

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
            cliente.close();
            socketServidor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
