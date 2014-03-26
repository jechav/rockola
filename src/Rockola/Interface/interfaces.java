

package Rockola.Interface;

import java.net.Socket;

/*
 *
 * @author joche echavez y andres echavarria
 */

/**
 *
 * @author Andres
 */
public interface interfaces {
    
    /**
     *
     * @param name
     * @param contraseña
     * @return
     */
    public String registrar(String name , String contraseña);
    /**
     *
     * @param name
     * @param contraseña
     * @return
     */
    public String IniciarSesion(String name, String contraseña);
    /**
     *
     * @return
     */
    public String VerConctato(); 
    /**
     *
     * @param pista
     * @return
     */
    public String enviarpistaactual(String pista);
    /**
     *
     * @param cliente
     * @return
     */
    public String CompatirCancion(Socket cliente);
    /**
     *
     * @param cliente
     * @return
     */
    public String DescargaCancion(Socket cliente);
    /**
     *
     * @param name
     * @param remitente
     * @param txt
     * @return
     */
    public String EnviarMensaje(String name,String remitente,String txt);
    /**
     *
     * @return
     */
    public String SacarMensaje();
    /**
     *
     * @return
     */
    public String cerrarsesion();
    /**
     *
     * @return
     */
    public String recibirCancionesRockola();
    /**
     *
     */
    public void salir();
    
}
