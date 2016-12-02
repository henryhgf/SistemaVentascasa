/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Backup;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;

public class FrmRespaldarDB extends javax.swing.JInternalFrame {

    public FrmRespaldarDB() {
        initComponents();
 
        this.setSize(535, 416);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSeleccionar = new javax.swing.JButton();
        btnBackup = new javax.swing.JButton();
        txtRuta = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        txtServidor = new javax.swing.JTextField();
        lblMensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtDB = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setTitle("Buckup de la  Base de Datos ::.");
        getContentPane().setLayout(null);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSeleccionar);
        btnSeleccionar.setBounds(390, 40, 110, 30);

        btnBackup.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/database_save.png"))); // NOI18N
        btnBackup.setText("Generar");
        btnBackup.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBackup.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackupActionPerformed(evt);
            }
        });
        getContentPane().add(btnBackup);
        btnBackup.setBounds(270, 80, 100, 90);

        txtRuta.setEditable(false);
        getContentPane().add(txtRuta);
        txtRuta.setBounds(120, 40, 260, 30);

        txtUsuario.setText("root");
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(120, 120, 140, 30);

        txtServidor.setText("localhost");
        getContentPane().add(txtServidor);
        txtServidor.setBounds(120, 80, 140, 30);
        getContentPane().add(lblMensaje);
        lblMensaje.setBounds(20, 340, 480, 30);

        jLabel1.setText("Servidor:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 80, 60, 30);

        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 120, 60, 30);

        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 160, 80, 30);

        jLabel4.setText("Base de Datos:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(30, 210, 90, 30);

        txtDB.setText("dbventas");
        getContentPane().add(txtDB);
        txtDB.setBounds(120, 210, 140, 30);

        jLabel5.setText("Respaldar en:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 40, 90, 30);

        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Información del Respaldo"));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 10, 500, 300);
        getContentPane().add(txtContraseña);
        txtContraseña.setBounds(120, 160, 140, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cancel.png"))); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(370, 80, 100, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            txtRuta.setText(file.toString());
      

        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackupActionPerformed
        String servidor;
        String user;
        String pass;
        String dbnombre;
        servidor=txtServidor.getText();
        user=txtUsuario.getText();
        pass=txtContraseña.getText();
        dbnombre=txtDB.getText();
        if (txtRuta.getText().equals("")) {
            lblMensaje.setText("<html><font color='red'>Elija ruta para guardar!</font></html>");
        } else {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
         
            String strFilename = dateFormat.format(now);

            long nowLong = now.getTime();
 
            System.out.println(strFilename);
            String command = "mysqldump -h" + servidor + " -u" + user + " -p" + pass + " --add-drop-database -B " + dbnombre + " --routines -r " + "\"" + txtRuta.getText().toString() + "\\" + strFilename + ".sql\"";
            System.out.println(command);
            Process p = null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p = runtime.exec(command);

                int processComplete = p.waitFor();

                if (processComplete == 0) {

                    System.out.println("<html><font color='green'>Backup creado con éxito!</font></html>");
                    lblMensaje.setText("<html><font color='green'>Backup creado con éxito en <br>" + txtRuta.getText().toString() + "\\" + strFilename + ".sql</font></html>");

                    } else {
                        lblMensaje.setText("No se pudo crear la copia de seguridad");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }//GEN-LAST:event_btnBackupActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBackup;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtDB;
    private javax.swing.JTextField txtRuta;
    private javax.swing.JTextField txtServidor;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
