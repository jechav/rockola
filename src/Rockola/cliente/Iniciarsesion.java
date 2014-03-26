/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rockola.cliente;

import Musica.reproductor;
import Musica.sonido;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * 
 * @author Andres Echavarria y joche echavez
 */
public class Iniciarsesion extends javax.swing.JFrame {
    
 
    private JFrame anterior;
    private String usuario;
    private DefaultListModel modeloname;
    private int Selecionname;
    private DefaultListModel modelopista;
    private int Selecionpista;
    private int seleccionmipista;
    private RockolaTCPcliente TCP;
    private hiloconectados hiloverconectados;
    
    private ArrayList<Conversaciones> listaConversaciones;
    sonido son;
    
    
    public Iniciarsesion(JFrame aux,RockolaTCPcliente tcp,String usua) {
        
        anterior = aux;
        anterior.setVisible(false);
        this.setVisible(true);
        TCP = tcp;
        initComponents();
        modeloname = new DefaultListModel();
        JListaContacto.setModel(modeloname);
        Selecionname = -1;
        modelopista =   new DefaultListModel();
        JListapistas.setModel(modelopista);
        Selecionpista = -1;
        seleccionmipista = -1;
        usuario = usua;
        listaConversaciones = new ArrayList<>();
        hiloverconectados = new hiloconectados(TCP, JListaContacto, JListapistas, jlMipistas,listaConversaciones,usuario);
        hiloverconectados.start();
        jbtreproducir.setEnabled(false);
        
        
    }
   
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JListaContacto = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        JListapistas = new javax.swing.JList();
        Botoncerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BotonMensaje = new javax.swing.JButton();
        BotonCompartir = new javax.swing.JButton();
        BotonDescargar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlMipistas = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jsMusica = new javax.swing.JSlider();
        jlbActualFrame = new javax.swing.JLabel();
        jlbDuracion = new javax.swing.JLabel();
        jbtstop = new javax.swing.JButton();
        jbtpause = new javax.swing.JButton();
        jbtreproducir = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        JListaContacto.setBackground(java.awt.SystemColor.menu);
        JListaContacto.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        JListaContacto.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JListaContactoValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(JListaContacto);

        JListapistas.setBackground(new java.awt.Color(204, 255, 204));
        JListapistas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        JListapistas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                JListapistasValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(JListapistas);

        Botoncerrar.setText("Cerrar sesion");
        Botoncerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoncerrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel1.setText("Rockola");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel2.setText("Conectados");

        BotonMensaje.setText("Enviar mensaje");
        BotonMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonMensajeActionPerformed(evt);
            }
        });

        BotonCompartir.setText("<= Compartir");
        BotonCompartir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCompartirActionPerformed(evt);
            }
        });

        BotonDescargar.setText("Descargar=>");
        BotonDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDescargarActionPerformed(evt);
            }
        });

        jlMipistas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.light"));
        jlMipistas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlMipistas.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jlMipistasValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(jlMipistas);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 24)); // NOI18N
        jLabel3.setText("Mi lista de Reproducción");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordenar Lista"));

        jButton2.setText("Subir");

        jButton3.setText("Bajar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton4.setText("Reproducir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jsMusica.setValue(0);
        jsMusica.setFocusable(false);

        jlbActualFrame.setText("00:00");

        jlbDuracion.setText("00:00");

        jbtstop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop2.png"))); // NOI18N
        jbtstop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtstopActionPerformed(evt);
            }
        });

        jbtpause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pause2.png"))); // NOI18N
        jbtpause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtpauseActionPerformed(evt);
            }
        });

        jbtreproducir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/play2.png"))); // NOI18N
        jbtreproducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtreproducirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonMensaje))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BotonDescargar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonCompartir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jlbActualFrame)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jsMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addComponent(jlbDuracion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbtreproducir)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbtpause, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jbtstop, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Botoncerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonMensaje))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(BotonDescargar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BotonCompartir)
                                .addGap(45, 45, 45)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jsMusica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jlbActualFrame)
                                    .addComponent(jlbDuracion))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jbtstop)
                                    .addComponent(jbtreproducir)
                                    .addComponent(jbtpause, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Botoncerrar))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JListaContactoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JListaContactoValueChanged
         Selecionname = JListaContacto.getSelectedIndex();
    }//GEN-LAST:event_JListaContactoValueChanged

    private void JListapistasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_JListapistasValueChanged
        Selecionpista = JListapistas.getSelectedIndex();
    }//GEN-LAST:event_JListapistasValueChanged

    private void BotoncerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoncerrarActionPerformed
      
      hiloverconectados.stop();
     // hiloMensaje.stop();;
      JOptionPane.showMessageDialog(rootPane, TCP.ComunicarMensaje("Cerrar"));
      this.setVisible(false);
      anterior.setVisible(true);
         
    }//GEN-LAST:event_BotoncerrarActionPerformed

    private void BotonMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonMensajeActionPerformed
       String auxnombre;
       int bandera=-1;
       Conversaciones aux;
       if(Selecionname>=0){
           auxnombre =  hiloverconectados.getselecion(Selecionname);
           for (int i = 0; i < listaConversaciones.size(); i++) {
                    aux = listaConversaciones.get(i);
                    if (auxnombre.equals(aux.getDestinatario())) {
                        bandera = i;
                    }
                }
           if(bandera==-1){
               listaConversaciones.add(new Conversaciones(auxnombre, usuario, TCP));
           }else{
               JOptionPane.showMessageDialog(null,"ventana ya esta abierta");
           }
       }else{
           JOptionPane.showMessageDialog(null,"selecione un usuario");
       }
    }//GEN-LAST:event_BotonMensajeActionPerformed

    private void BotonDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDescargarActionPerformed
        String archivo = hiloverconectados.getSeleccionArchivo(Selecionpista);
        
        try {
            System.out.println(hiloverconectados.descargaCancion(archivo));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Iniciarsesion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonDescargarActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        String cancion = hiloverconectados.getSeleccionMusica(seleccionmipista);
        System.out.println("\t\t"+hiloverconectados.enviarPistaActual(cancion));
        //reproductor reproductor = new reproductor();
        //reproductor.setVisible(true);
        //reproductor.setNombre(cancion);
        son = new sonido(cancion, this.jsMusica, this.jlbDuracion, this.jlbActualFrame);     
        //reproductor.setSonido(son);
        son.start();
        jbtreproducir.setEnabled(false);
        jbtpause.setEnabled(true);
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jlMipistasValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jlMipistasValueChanged
        seleccionmipista = jlMipistas.getSelectedIndex();
    }//GEN-LAST:event_jlMipistasValueChanged

    private void jbtstopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtstopActionPerformed
        son.detener();
        son.stop();
        jbtreproducir.setEnabled(false);
        jbtpause.setEnabled(false);
        jbtstop.setEnabled(false);
    }//GEN-LAST:event_jbtstopActionPerformed

    private void jbtpauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtpauseActionPerformed
        son.pausar();
        jbtreproducir.setEnabled(true);
        jbtpause.setEnabled(false);
    }//GEN-LAST:event_jbtpauseActionPerformed

    private void jbtreproducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtreproducirActionPerformed
        if(son.isAlive()){
            son.stop();
        }else{
            son.reproducir();
            jbtreproducir.setEnabled(false);
            jbtpause.setEnabled(true);
        }
    }//GEN-LAST:event_jbtreproducirActionPerformed

    private void BotonCompartirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCompartirActionPerformed
        String archivo = hiloverconectados.getSeleccionMusica(seleccionmipista);
        System.out.println(archivo);
        System.out.println(hiloverconectados.cargandoCanción(archivo));
    }//GEN-LAST:event_BotonCompartirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonCompartir;
    private javax.swing.JButton BotonDescargar;
    private javax.swing.JButton BotonMensaje;
    private javax.swing.JButton Botoncerrar;
    private javax.swing.JList JListaContacto;
    private javax.swing.JList JListapistas;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton jbtpause;
    private javax.swing.JButton jbtreproducir;
    private javax.swing.JButton jbtstop;
    private javax.swing.JList jlMipistas;
    private javax.swing.JLabel jlbActualFrame;
    private javax.swing.JLabel jlbDuracion;
    private javax.swing.JSlider jsMusica;
    // End of variables declaration//GEN-END:variables
}
