/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Rockola.cliente;

import javax.swing.JOptionPane;

/**
 *
 * @author Andres
 */
public class RockolaInicio extends javax.swing.JFrame {

    
    private RockolaTCPcliente TCP;
    
    public RockolaInicio() {
        initComponents();
        TCP = new RockolaTCPcliente(2014);
        this.setTitle("Rockola");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Txtname = new javax.swing.JTextField();
        Botoniniciarsesion = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Botonregistrar = new javax.swing.JButton();
        Txtpassword = new javax.swing.JPasswordField();
        BotonSalir = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("Password");

        Txtname.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        Txtname.setName(""); // NOI18N

        Botoniniciarsesion.setText("Iniciar sesion");
        Botoniniciarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoniniciarsesionActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Rockola");

        Botonregistrar.setText("Registrarse");
        Botonregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonregistrarActionPerformed(evt);
            }
        });

        Txtpassword.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        BotonSalir.setText("Salir");
        BotonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Txtname, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(Botoniniciarsesion)
                        .addGap(44, 44, 44)
                        .addComponent(Botonregistrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(BotonSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Txtpassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Botonregistrar)
                    .addComponent(Botoniniciarsesion))
                .addGap(18, 18, 18)
                .addComponent(BotonSalir)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotoniniciarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoniniciarsesionActionPerformed
        String name = Txtname.getText();
        String Password = Txtpassword.getText();
        String mensajerecibido=TCP.ComunicarMensaje(("Iniciar"+"%.%"+name+"%.%"+Password));
        if("Iniciar".equals(mensajerecibido)){
            Iniciarsesion inicio = new Iniciarsesion(this, TCP,Txtname.getText());
            Txtname.setText("");
            Txtpassword.setText("");
        }else{
            JOptionPane.showMessageDialog(rootPane, mensajerecibido);
        }
        
    }//GEN-LAST:event_BotoniniciarsesionActionPerformed

    private void BotonregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonregistrarActionPerformed
        String name = Txtname.getText();
        String Password = Txtpassword.getText();
        String mensajerecibido=TCP.ComunicarMensaje("Registrar"+"%.%"+name+"%.%"+Password);
        JOptionPane.showMessageDialog(rootPane, mensajerecibido);
        //Txtname.setText("");
        //Txtpassword.setText("");
    }//GEN-LAST:event_BotonregistrarActionPerformed

    private void BotonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonSalirActionPerformed
        TCP.ComunicarMensaje("Salir");
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_BotonSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RockolaInicio().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonSalir;
    private javax.swing.JButton Botoniniciarsesion;
    private javax.swing.JButton Botonregistrar;
    private javax.swing.JTextField Txtname;
    private javax.swing.JPasswordField Txtpassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
