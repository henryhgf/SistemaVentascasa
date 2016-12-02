
package Presentacion;

import Negocio.*;
import Entidad.*;
import javax.swing.*;
import Presentacion.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.lang.String.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class FrmLogin extends javax.swing.JFrame {
    
    //algoritmos
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    
    String id[]=new String[50];
    static int intContador;
    int intentos=0;
    public String codigo;
    String usu,pass;
    static Connection conn=null;
    static ResultSet rs=null;
    static ResultSet rs1=null;
    DefaultTableModel dtm=new DefaultTableModel();
        
    public FrmLogin() {
        initComponents();
        cargarComboCargo();
  
        lblIntentos.setVisible(false);
 
        this.setSize(586, 307);
    }

    void cargarComboCargo(){
       ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
       ArrayList<ClsEntidadTipoUsuario> operacion=tipousuarios.listarTipoUsuario();
       Iterator iterator=operacion.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboCargo.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadTipoUsuario TipoUsuario=new ClsEntidadTipoUsuario();
           TipoUsuario=(ClsEntidadTipoUsuario) iterator.next();
           id[intContador]=TipoUsuario.getStrIdTipoUsuario();
           fila[0]=TipoUsuario.getStrIdTipoUsuario();
           fila[1]=TipoUsuario.getStrDescripcionTipoUsuario();

        
           DefaultComboBoxModel.addElement(TipoUsuario.getStrDescripcionTipoUsuario());
           intContador++;
              
       }
       cboCargo.setModel(DefaultComboBoxModel);
   }
 
  
    
     private static String toHexadecimal(byte[] digest)
    {
        String hash = "";
        for(byte aux : digest) 
        {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        return hash;
    }
    public static String getStringMessageDigest(String message, String algorithm)
    {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try 
        {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        }
        catch (NoSuchAlgorithmException ex) 
        {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        btnIniciar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        lblIntentos = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboCargo = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("ACCESO AL SISTEMA");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 0, 194, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Usuario:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 60, 43, 15);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Constraseña:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 100, 70, 20);

        txtUsuario.setToolTipText("");
        txtUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuarioActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(100, 60, 160, 30);

        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bocallave.png"))); // NOI18N
        btnIniciar.setText("Ingresar");
        btnIniciar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIniciar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar);
        btnIniciar.setBounds(50, 180, 100, 70);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btnCancelar.setText("Salir");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(170, 180, 100, 70);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/image.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(300, 60, 256, 200);
        getContentPane().add(txtContraseña);
        txtContraseña.setBounds(100, 100, 160, 30);
        getContentPane().add(lblIntentos);
        lblIntentos.setBounds(478, 11, 11, 18);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cargo:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 140, 35, 15);

        cboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(cboCargo);
        cboCargo.setBounds(100, 140, 160, 30);

        jLabel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jLabel6);
        jLabel6.setBounds(10, 50, 560, 220);

        setSize(new java.awt.Dimension(586, 307));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuarioActionPerformed
        txtContraseña.requestFocus();
    }//GEN-LAST:event_txtUsuarioActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        String algorithm="SHA-512";  
        String resultado1,resultado2;
        String usuario=null;
        String contraseña=null;
        String descripcion=null;
        int sen=1;int tu=1;
        
        usuario=txtUsuario.getText();
        contraseña=txtContraseña.getText();
        descripcion= String.valueOf(cboCargo.getSelectedItem());
        resultado1=getStringMessageDigest(contraseña,algorithm);   
       
        boolean encuentra=false;
        
        if(usuario.length()==0 ||contraseña.length()==0) {
            JOptionPane.showMessageDialog(this, "!Ingrese Usuario y/o Constraseña!");
            txtUsuario.setText("");
            txtContraseña.setText("");
            txtUsuario.requestFocus();
            intentos=intentos+1;
            lblIntentos.setText(Integer.toString(intentos));

        }
        else 
        {
            try{
               ClsEmpleado oUsuario=new ClsEmpleado();
                
               rs= oUsuario.LoginEmpleados(usuario,resultado1,descripcion);
           
               while (rs.next()&& sen==1) {
                    if(!rs.getString(14).equals("")){
                        if(rs.getString(14).equals(usuario)&&rs.getString(15).equals(resultado1)&&rs.getString(16).equals(descripcion)) { 
                            if(rs.getString(13).equals("ACTIVO")) { 
                                JOptionPane.showMessageDialog(null,"Bienvenidos: Sr."+rs.getString(2)+" "+rs.getString(3),"Login Usuarios",1);
                                sen=2;
                            }else{
                                JOptionPane.showMessageDialog(this, "Usuario Inactivo");
                                sen=3;
                            }
                                
                       }
                    }else{
                        JOptionPane.showMessageDialog(this, "Usuario no existe");
                        sen=3;
                    }
                        
                        

                   encuentra=true;   
                   break;
                }
               
  
                if(sen==1) {
                    JOptionPane.showMessageDialog(this, "¡Usuario y/o Contraseña incorrectos!");
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                    txtUsuario.requestFocus();
                    intentos=intentos+1;
                    lblIntentos.setText(Integer.toString(intentos));
                    
                } else if(sen==3){
                    txtUsuario.setText("");
                    txtContraseña.setText("");
                    txtUsuario.requestFocus();
                }else{

                    FrmPrincipal mdi=new FrmPrincipal();
                    mdi.show();
                    this.dispose();                    
                    mdi.strUsuario=rs.getString(14);
                    mdi.strIdEmpleado=rs.getString(1);
                    mdi.strNombreEmpleado = rs.getString(2) +" "+rs.getString(3);
                    mdi.strTipo=cboCargo.getSelectedItem().toString();
                    mdi.intEstado=1; 
                    
                    
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }
        
        if(intentos==3){
            this.dispose();
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
          //new FrmLogin().setVisible(true);
                
         Presentacion.FrmLogin f = new Presentacion.FrmLogin();

         try {
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");

            SwingUtilities.updateComponentTreeUI(f);
            f.setVisible(true);
            //new FrmLogin().setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {}
          catch (ClassNotFoundException ex) {}
          catch (InstantiationException ex) {}
          catch (IllegalAccessException ex) {}
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JComboBox cboCargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblIntentos;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
