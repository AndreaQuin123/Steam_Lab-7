/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package archivosbinarios;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author elean
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        boxImagen = new javax.swing.JComboBox<>();
        boxUsuario = new javax.swing.JComboBox<>();
        IMAGEN = new javax.swing.JLabel();
        jCalendar1 = new com.toedter.calendar.JCalendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(830, 520));
        setMinimumSize(new java.awt.Dimension(830, 520));

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("REGISTRO STEAM");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 300, -1));

        jLabel7.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("ELIGA IMAGEN");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 300, -1, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NOMBRE");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, -1));

        txtNombre.setBackground(new java.awt.Color(255, 255, 255));
        txtNombre.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 250, 40));

        jLabel3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("USERNAME");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, -1, -1));

        txtUser.setBackground(new java.awt.Color(255, 255, 255));
        txtUser.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        txtUser.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 250, 40));

        jLabel4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PASSWORD");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, -1, -1));

        txtPassword.setBackground(new java.awt.Color(255, 255, 255));
        txtPassword.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 250, 40));

        jLabel6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("TIPO DE USUARIO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 380, -1, -1));

        jLabel5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("FECHA DE NACIMIENTO");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        btnRegistrar.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setFont(new java.awt.Font("Microsoft Tai Le", 1, 12)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(0, 0, 0));
        btnRegistrar.setText("REGISTRARSE");
        btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnRegistrarMouseClicked(evt);
            }
        });
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 500, -1, -1));

        boxImagen.setBackground(new java.awt.Color(255, 255, 255));
        boxImagen.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        boxImagen.setForeground(new java.awt.Color(0, 0, 0));
        boxImagen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Imagen 1", "Imagen 2", "Imagen 3" }));
        boxImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxImagenActionPerformed(evt);
            }
        });
        jPanel1.add(boxImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 320, 190, 40));

        boxUsuario.setBackground(new java.awt.Color(255, 255, 255));
        boxUsuario.setFont(new java.awt.Font("Microsoft Tai Le", 1, 14)); // NOI18N
        boxUsuario.setForeground(new java.awt.Color(0, 0, 0));
        boxUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Administrador", "Normal" }));
        jPanel1.add(boxUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 400, 190, 40));
        jPanel1.add(IMAGEN, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 230, 200));

        jCalendar1.setBackground(new java.awt.Color(255, 255, 255));
        jCalendar1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarMouseClicked
        String nombre = txtNombre.getText();
        String username = txtUser.getText();
        String password = txtPassword.getText();
        Date fecha = jCalendar1.getDate();
        long milliseconds = fecha.getTime();
        String imagen = IMAGEN.getIcon().toString();
        String tiipoUsuario = boxUsuario.getSelectedItem().toString();
        
        try {
            Main.steam.addPlayer(username, password, nombre, milliseconds, imagen, tiipoUsuario);
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnRegistrarMouseClicked

    private void boxImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxImagenActionPerformed
        String selectedItem = (String) boxImagen.getSelectedItem();

        if (null != selectedItem) {
            switch (selectedItem) {
                case "Imagen 1": {
                    Icon icon = new ImageIcon(getClass().getClassLoader().getResource("Elementos/TeamPerro.jpg"));
                    IMAGEN.setIcon(icon);
                    break;
                }
                case "Imagen 2": {
                    Icon icon = new ImageIcon(getClass().getClassLoader().getResource("Elementos/TeamGato.png"));
                    IMAGEN.setIcon(icon);
                    break;
                }
                case "Imagen 3": {
                    Icon icon = new ImageIcon(getClass().getClassLoader().getResource("Elementos/TeamPajaro.png"));
                    IMAGEN.setIcon(icon);
                    break;
                }
                default:
                    break;
            }
        }
    }//GEN-LAST:event_boxImagenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IMAGEN;
    private javax.swing.JComboBox<String> boxImagen;
    private javax.swing.JComboBox<String> boxUsuario;
    private javax.swing.JButton btnRegistrar;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
