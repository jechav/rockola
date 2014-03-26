/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Musica;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSlider;
import javax.swing.JLabel;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Jose Echavez
 */


public class sonido extends Thread{
    
    String nombrePista;
    JSlider jslider;
    Clip musica;
    JLabel jlbDuracion;
    JLabel jlbFrame;
    
    /**
     *
     * @param cancion
     * @param jslider
     * @param jlbDuracion
     * @param jlbFrame
     */
    public sonido(String cancion, JSlider jslider, JLabel jlbDuracion, JLabel jlbFrame) {
        this.nombrePista = cancion;
        this.jslider = jslider;
        this.jlbDuracion = jlbDuracion;
        this.jlbFrame = jlbFrame;
        
    }
    
    private void init(){
        
       URL url = null;
       InputStream in = null;
       
       try {
            url = new URL("file:\\"+System.getProperty("user.dir")+"\\clientecanciones\\"+nombrePista);
            in = url.openStream();
        } catch (IOException ex) {
            Logger.getLogger(sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(url));
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(sonido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(sonido.class.getName()).log(Level.SEVERE, null, ex);
        }
        musica.loop(0);
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args){
        String cancion = "001_008 Linkin Park - Point Of Authority.wav";
        reproductor reproductor = new reproductor();
        reproductor.setVisible(true);
        reproductor.setNombre(cancion);
        sonido son = new sonido(cancion, reproductor.getJslider(), reproductor.getJlbDuracion(), reproductor.getJlbFrame());     
        reproductor.setSonido(son);
        son.run();
        
        
    }
    @Override
    public void run(){
        init();
        jslider.setMaximum(getFrameDuracion());
        jlbDuracion.setText(getDuracion());
        while (getFrame()<getFrameDuracion()) {            
            jslider.setValue(getFrame());
            jlbFrame.setText(getActualDuracion());
            //try {
                //Thread.sleep(100);
                //System.out.println(getFrameDuracion());       
                //System.out.println(getFrameDuracion());       
            //} catch (InterruptedException ex) {
            //   Logger.getLogger(sonido.class.getName()).log(Level.SEVERE, null, ex);
            //}
        }
        System.out.println("Termino");
    }

    /**
     *
     * @return
     */
    public int getFrameDuracion(){
        return musica.getFrameLength();
    }

    /**
     *
     * @return
     */
    public int getFrame(){
        return musica.getFramePosition();
    }

    /**
     *
     * @return
     */
    public String getDuracion(){
        int duracion;
        duracion = (int)musica.getMicrosecondLength()/1000000;
        return (int)(duracion/60)+":"+(duracion%60);
    }

    /**
     *
     * @return
     */
    public String  getActualDuracion(){
        int duracion;
        if(musica.getMicrosecondPosition()!= 0){
            duracion = (int)musica.getMicrosecondPosition()/1000000;
            return (int)(duracion/60)+":"+(duracion%60);
        }
        return 00+"";
    }

    /**
     *
     */
    public void pausar(){
        musica.stop();
    }

    /**
     *
     */
    public void detener(){
        musica.stop();
        musica.close();
    }

    /**
     *
     */
    public void reproducir(){
        musica.start();
    }
}
            
    