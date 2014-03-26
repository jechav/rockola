/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rockola.servidos;

import java.util.ArrayList;

/**
 *
 * @author Andres echavarria y joche echavez
 */
public class RockolaClientes {
    private String nombre;
    private String contraseña;
    private String estado;
    private String cancion;
    private ArrayList<String> listamensaje;

    /**
     * @param name 
     * @param password 
     */
    public RockolaClientes(String name,String password){
        nombre = name;
        contraseña = password;
        listamensaje = new ArrayList<>();
        cancion = "sin cancion";
        estado = "Inactivo";
    }
    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the cancion
     */
    public String getCancion() {
        return cancion;
    }

    /**
     * @param cancion the cancion to set
     */
    public void setCancion(String cancion) {
        this.cancion = cancion;
    }

    /**
     * @return the lista
     */
    public ArrayList<String> getLista() {
        return listamensaje;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ArrayList<String> lista) {
        this.listamensaje = lista;
    }
    /**
     *
     * @param mensaje
     */
    public void agregarmensaje(String mensaje){
        listamensaje.add(mensaje);
    }
    /**
     *
     * @return
     */
    public String cogermensaje(){
        String resp = "Nada";
        if(listamensaje.size()>0){
            resp = listamensaje.get(0);
            listamensaje.remove(0);
        }
        return resp;
    }
}
