
package Presentacion;

import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class FrmEmpleado extends javax.swing.JInternalFrame {
    private Connection connection=new ClsConexion().getConection();
  
    public static String MD2 = "MD2";
    public static String MD5 = "MD5";
    public static String SHA1 = "SHA-1";
    public static String SHA256 = "SHA-256";
    public static String SHA384 = "SHA-384";
    public static String SHA512 = "SHA-512";
    
    String Total;
    String strCodigo;
    String accion;
    int registros;
    String id[]=new String[50];
    static int intContador;
    
    //-----------------------------------------------
    public String codigo;
    static Connection conn=null;
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    String criterio,busqueda;
    
    public FrmEmpleado() {
        initComponents();
     
        Date date=new Date();
        String format=new String("yyyy-MM-dd");
        SimpleDateFormat formato=new SimpleDateFormat(format);
        txtFechaIng.setDate(date);
        txtFechaNac.setDate(date);
        
        cargarComboTipoUsuario();
       
        buttonGroup1.add(rbtnMasculino);
        buttonGroup1.add(rbtnFemenino);
        buttonGroup2.add(rbtnActivo);
        buttonGroup2.add(rbtnInactivo);
        buttonGroup3.add(rbtnNombre);
        buttonGroup3.add(rbtnDni);
        mirar();        
        actualizarTabla();

        this.setSize(1200, 525);
        CrearTabla();                
        CantidadTotal();

        
    }

  void CrearTabla(){

      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
   
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
            
                    if(column==0 || column==3 || column==4 || column==6 || column==7 || column==9 || column==10 || column==11 || column==12 || column==13 || column==15){
                        l.setHorizontalAlignment(SwingConstants.CENTER); 
                    }else{
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                    }

          
                if (isSelected) {
                    l.setBackground(new Color(203, 159, 41));
             
                    l.setForeground(Color.WHITE); 
                }else{
                    l.setForeground(Color.BLACK);
                    if (row % 2 == 0) {
                        l.setBackground(Color.WHITE);
                    } else {
                        l.setBackground(new Color(254, 227, 152));
                    }
                }     
                return l; 
            } 
        }; 
        

        for (int i=0;i<tblEmpleado.getColumnCount();i++){
            tblEmpleado.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
      
        tblEmpleado.setAutoResizeMode(tblEmpleado.AUTO_RESIZE_OFF);

       
        int[] anchos = {40,80,100,40,80,200,80,80,150,80,80,80,80,80,80,100};
        for(int i = 0; i < tblEmpleado.getColumnCount(); i++) {
            tblEmpleado.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
      
        setOcultarColumnasJTable(tblEmpleado,new int[]{14});
    }
   void CantidadTotal(){
        Total= String.valueOf(tblEmpleado.getRowCount());   
        lblEstado.setText("Tienes  " + Total + " Empleados Registrados");      
   }
   void limpiarCampos(){
     
       txtNombre.setText("");
       txtApellido.setText("");
       txtDireccion.setText("");
       txtTelefono.setText("");
       txtCelular.setText("");
       txtEmail.setText("");
       txtDni.setText("");
       txtSueldo.setText("");
       txtUsuario.setText("");
       txtContraseña.setText("");
       
       rbtnMasculino.setSelected(true);
       rbtnFemenino.setSelected(false);
       rbtnActivo.setSelected(true);
       rbtnInactivo.setSelected(false);
       txtBusqueda.setText("");
   }
    
        
   void mirar(){
        tblEmpleado.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        
  
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        rbtnMasculino.setEnabled(false);
        rbtnFemenino.setEnabled(false);
        txtFechaNac.setEnabled(false);
        txtDireccion.setEnabled(false);
        txtTelefono.setEnabled(false);
        txtCelular.setEnabled(false);
        txtEmail.setEnabled(false);
        txtDni.setEnabled(false);
        txtFechaIng.setEnabled(false);
        txtSueldo.setEnabled(false);
        rbtnActivo.setEnabled(false);
        rbtnInactivo.setEnabled(false);
        txtUsuario.setEnabled(false);
        txtContraseña.setEnabled(false);
        cboTipoUsuario.setEnabled(false);
    }
   
    void modificar(){
        tblEmpleado.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        
        txtNombre.setEnabled(true);
        txtApellido.setEnabled(true);
        rbtnMasculino.setEnabled(true);
        rbtnFemenino.setEnabled(true);
        txtFechaNac.setEnabled(true);
        txtDireccion.setEnabled(true);
        txtTelefono.setEnabled(true);
        txtCelular.setEnabled(true);
        txtEmail.setEnabled(true);
        txtDni.setEnabled(true);
        txtFechaIng.setEnabled(true);
        txtSueldo.setEnabled(true);
        rbtnActivo.setEnabled(true);
        rbtnInactivo.setEnabled(true);
        txtUsuario.setEnabled(true);
        txtContraseña.setEnabled(true);
        cboTipoUsuario.setEnabled(true);
        txtNombre.requestFocus();
    }
   
   
    void actualizarTabla(){
       String titulos[]={"ID","Nombre","Apellidos","Sexo","Fecha Nac.","Dirección","Teléfono","Celular","Email","DNI","Fecha Ing.","Sueldo","Estado","Usuario","Contraseña","Tipo de Usuario"};
          
       ClsEmpleado empleados=new ClsEmpleado();
       ArrayList<ClsEntidadEmpleado> empleado=empleados.listarEmpleado();
       Iterator iterator=empleado.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[16];
       while(iterator.hasNext()){
           ClsEntidadEmpleado Empleado=new ClsEntidadEmpleado();
           Empleado=(ClsEntidadEmpleado) iterator.next();
           fila[0]=Empleado.getStrIdEmpleado();
           fila[1]=Empleado.getStrNombreEmpleado();
           fila[2]=Empleado.getStrApellidoEmpleado();
           fila[3]=Empleado.getStrSexoEmpleado();
           fila[4]=Empleado.getStrFechaNacEmpleado().toString();
           fila[5]=Empleado.getStrDireccionEmpleado();
           fila[6]=Empleado.getStrTelefonoEmpleado();
           fila[7]=Empleado.getStrCelularEmpleado();
           fila[8]=Empleado.getStrEmailEmpleado();
           fila[9]=Empleado.getStrDniEmpleado();
           fila[10]=Empleado.getStrFechaIngEmpleado().toString();
           fila[11]=Empleado.getStrSueldoEmpleado();
           fila[12]=Empleado.getStrEstadoEmpleado();
           fila[13]=Empleado.getStrUsuarioEmpleado();
           fila[14]=Empleado.getStrContraseñaEmpleado();
           fila[15]=Empleado.getStrTipoUsuario();
           defaultTableModel.addRow(fila);
                
       }
       tblEmpleado.setModel(defaultTableModel);


   }
   void BuscarEmpleado(){
        String titulos[]={"ID","Nombre","Apellidos","Sexo","Fecha Nac.","Dirección","Teléfono","Celular","Email","DNI","Fecha Ing.","Sueldo","Estado","Usuario","Contraseña","Tipo de Usuario"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsEmpleado empleado=new ClsEmpleado();
        busqueda=txtBusqueda.getText();
         if(rbtnNombre.isSelected()){
            criterio="nombre";
        }else if(rbtnDni.isSelected()){
            criterio="dni";
        }
        try{
            rs=empleado.listarEmpleadoPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[16];
            int f,i;
            f=dtm.getRowCount();
            if(f>0){
                for(i=0;i<f;i++){
                    dtm.removeRow(0);
                }
            }
            while(rs.next()){
                Datos[0]=(String) rs.getString(1);
                Datos[1]=(String) rs.getString(2);
                Datos[2]=(String) rs.getString(3);
                Datos[3]=(String) rs.getString(4);
                Datos[4]=(String) rs.getString(5);
                Datos[5]=(String) rs.getString(6);
                Datos[6]=(String) rs.getString(7);
                Datos[7]=(String) rs.getString(8);
                Datos[8]=(String) rs.getString(9);
                Datos[9]=(String) rs.getString(10);
                Datos[10]=(String) rs.getString(11);
                Datos[11]=(String) rs.getString(12);
                Datos[12]=(String) rs.getString(13);
                Datos[13]=(String) rs.getString(14);
                Datos[14]=(String) rs.getString(15);
                Datos[15]=(String) rs.getString(16);
                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblEmpleado.setModel(dtm);
    }
    void listardatos(){

        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"¡Se debe seleccionar un registro!");
        }else{
            defaultTableModel=(DefaultTableModel) tblEmpleado.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
    
            txtNombre.setText((String)defaultTableModel.getValueAt(registros,1));
            txtApellido.setText((String)defaultTableModel.getValueAt(registros,2));
            if ("M".equals((String) defaultTableModel.getValueAt(registros,3))){
               rbtnMasculino.setSelected(true);
            }else if ("F".equals((String) defaultTableModel.getValueAt(registros,3))){
               rbtnFemenino.setSelected(true);
            }
            txtFechaNac.setDate((Date)defaultTableModel.getValueAt(registros,4));
            txtDireccion.setText((String)defaultTableModel.getValueAt(registros,5));
            txtTelefono.setText((String)defaultTableModel.getValueAt(registros,6));
            txtCelular.setText((String)defaultTableModel.getValueAt(registros,7));
            txtEmail.setText((String)defaultTableModel.getValueAt(registros,8));
            txtDni.setText((String)defaultTableModel.getValueAt(registros,9));
            txtFechaIng.setDate((Date)defaultTableModel.getValueAt(registros,10));
            txtSueldo.setText((String)defaultTableModel.getValueAt(registros,11));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(registros,12))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(registros,12))){
               rbtnInactivo.setSelected(true);
            }
            txtUsuario.setText((String)defaultTableModel.getValueAt(registros,13));
            txtContraseña.setText((String)defaultTableModel.getValueAt(registros,14));
            cboTipoUsuario.setSelectedItem((String)defaultTableModel.getValueAt(registros,15));
            tblEmpleado.setRowSelectionInterval(registros,registros);
        }
    
    }
    void cargarComboTipoUsuario(){
       ClsTipoUsuario tipousuario=new ClsTipoUsuario();
       ArrayList<ClsEntidadTipoUsuario> tipousuarios=tipousuario.listarTipoUsuario();
       Iterator iterator=tipousuarios.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboTipoUsuario.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadTipoUsuario TipoDocumento = new ClsEntidadTipoUsuario();
           TipoDocumento=(ClsEntidadTipoUsuario) iterator.next();
           id[intContador]=TipoDocumento.getStrIdTipoUsuario();
           fila[0]=TipoDocumento.getStrIdTipoUsuario();
           fila[1]=TipoDocumento.getStrDescripcionTipoUsuario();
           DefaultComboBoxModel.addElement(TipoDocumento.getStrDescripcionTipoUsuario());
           intContador++;              
       }
       cboTipoUsuario.setModel(DefaultComboBoxModel);
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
    private void setOcultarColumnasJTable(JTable tbl, int columna[]){
        for(int i=0;i<columna.length;i++)
        {
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pNuevo = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rbtnMasculino = new javax.swing.JRadioButton();
        rbtnFemenino = new javax.swing.JRadioButton();
        txtFechaNac = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtFechaIng = new com.toedter.calendar.JDateChooser();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtSueldo = new javax.swing.JTextField();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDireccion = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        cboTipoUsuario = new javax.swing.JComboBox();
        pBuscar = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleado = new javax.swing.JTable();
        lblEstado = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        rbtnDni = new javax.swing.JRadioButton();
        rbtnNombre = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Empleados");
        getContentPane().setLayout(null);

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pencil.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnModificar.setIconTextGap(0);
        btnModificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar);
        btnModificar.setBounds(880, 390, 81, 70);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCancelar.setIconTextGap(0);
        btnCancelar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(960, 390, 81, 70);

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNuevo.setIconTextGap(0);
        btnNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevo);
        btnNuevo.setBounds(720, 390, 81, 70);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSalir.setIconTextGap(0);
        btnSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(1040, 390, 81, 70);

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGuardar.setIconTextGap(0);
        btnGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar);
        btnGuardar.setBounds(800, 390, 81, 70);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(630, 370, 550, 100);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Registro"));
        pNuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        pNuevo.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 150, -1));

        jLabel2.setText("Nombre:");
        pNuevo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 50, 20));

        jLabel6.setText("Apellidos:");
        pNuevo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 60, 20));

        txtApellido.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });
        pNuevo.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 150, 20));

        jLabel7.setText("Sexo:");
        pNuevo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 40, 20));

        rbtnMasculino.setText("Masculino");
        pNuevo.add(rbtnMasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, -1, -1));

        rbtnFemenino.setText("Femenino");
        pNuevo.add(rbtnFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, -1, -1));

        txtFechaNac.setDateFormatString("yyyy-MM-dd");
        pNuevo.add(txtFechaNac, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 110, -1));

        jLabel8.setText("Fecha de Nacimiento:");
        pNuevo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        jLabel9.setText("Dirección:");
        pNuevo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 60, 20));

        jLabel10.setText("Teléfono:");
        pNuevo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 60, 20));

        jLabel11.setText("Celular:");
        pNuevo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 290, 50, 20));

        jLabel12.setText("Email:");
        pNuevo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 40, 20));

        jLabel13.setText("DNI:");
        pNuevo.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 40, 20));

        jLabel14.setText("Fecha de Ingreso:");
        pNuevo.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 100, 20));

        txtFechaIng.setDateFormatString("yyyy-MM-dd");
        pNuevo.add(txtFechaIng, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 120, -1));
        pNuevo.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 150, -1));
        pNuevo.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 150, -1));
        pNuevo.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 180, -1));

        txtDni.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        pNuevo.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 320, 150, -1));

        jLabel15.setText("Sueldo:");
        pNuevo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 50, -1));
        pNuevo.add(txtSueldo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 100, -1));

        rbtnActivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnActivo.setText("ACTIVO");
        pNuevo.add(rbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, -1, -1));

        rbtnInactivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnInactivo.setText("INACTIVO");
        pNuevo.add(rbtnInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, -1, -1));

        jLabel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));
        pNuevo.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 170, 50));

        txtDireccion.setColumns(20);
        txtDireccion.setRows(5);
        jScrollPane2.setViewportView(txtDireccion);

        pNuevo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 430, 40));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Usuario"));

        jLabel17.setText("Usuario:");

        jLabel18.setText("Contraseña:");

        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyTyped(evt);
            }
        });

        jLabel19.setText("Tipo de usuario:");

        cboTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pNuevo.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 270, 130));

        getContentPane().add(pNuevo);
        pNuevo.setBounds(630, 10, 550, 360);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Busqueda"));
        pBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblEmpleado.setRowHeight(22);
        tblEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleado);

        pBuscar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 570, 240));
        pBuscar.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 200, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        jButton3.setText("Imprimir Lista");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pBuscar.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, 150, 50));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        pBuscar.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 310, -1));

        rbtnDni.setText("D.N.I.");
        rbtnDni.setOpaque(false);
        pBuscar.add(rbtnDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, -1, -1));

        rbtnNombre.setText("Nombre o Apellido");
        rbtnNombre.setOpaque(false);
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });
        pBuscar.add(rbtnNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 120, -1));

        getContentPane().add(pBuscar);
        pBuscar.setBounds(10, 10, 595, 420);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(tblEmpleado.getSelectedRows().length > 0 ) {
            accion="Modificar";
            modificar();
        
        }else{
            JOptionPane.showMessageDialog(null, "¡Se debe seleccionar un registro!");
        }

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
 
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion="Nuevo";
        modificar();
        limpiarCampos();
        txtContraseña.setEnabled(true);
    
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    public boolean validardatos(){
        if (txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese nombre del Empleado");
            txtNombre.requestFocus();
            txtNombre.setBackground(Color.YELLOW);
            return false;
        }else if (txtApellido.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese apellido del Empleado");
            txtApellido.requestFocus();
            txtApellido.setBackground(Color.YELLOW);
            return false;
        }else if (txtDni.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese DNI del Empleado");
            txtDni.requestFocus();
            txtDni.setBackground(Color.YELLOW);
            return false;
        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        String algorithm="SHA-512";  
        String resultado1,resultado2;
        String contraseña=null;
    if(validardatos()==true){     
        if(accion.equals("Nuevo")){
            ClsEmpleado empleados=new ClsEmpleado();
            ClsEntidadEmpleado empleado=new ClsEntidadEmpleado();
            empleado.setStrNombreEmpleado(txtNombre.getText());
            empleado.setStrApellidoEmpleado(txtApellido.getText());
            if (rbtnMasculino.isSelected()){
                empleado.setStrSexoEmpleado("M");
            }else if (rbtnFemenino.isSelected()){
                empleado.setStrSexoEmpleado("F");
            }
            empleado.setStrFechaNacEmpleado(txtFechaNac.getDate());
            empleado.setStrDireccionEmpleado(txtDireccion.getText());
            empleado.setStrTelefonoEmpleado(txtTelefono.getText());
            empleado.setStrCelularEmpleado(txtCelular.getText());
            empleado.setStrEmailEmpleado(txtEmail.getText());
            empleado.setStrDniEmpleado(txtDni.getText());
            empleado.setStrFechaIngEmpleado(txtFechaIng.getDate());
            if(txtSueldo.getText().equals("")){
                empleado.setStrSueldoEmpleado("0");
            }else{
                empleado.setStrSueldoEmpleado(txtSueldo.getText());
            }
            if (rbtnActivo.isSelected()){
                empleado.setStrEstadoEmpleado("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                empleado.setStrEstadoEmpleado("INACTIVO");
            }
            empleado.setStrUsuarioEmpleado(txtUsuario.getText());
            if (txtContraseña.getText().length()==0){
                empleado.setStrContraseñaEmpleado("");
            }else{
                contraseña=txtContraseña.getText();
                resultado1=getStringMessageDigest(contraseña,algorithm);   
                empleado.setStrContraseñaEmpleado(resultado1);
            }
            
            empleado.setStrIdTipoUsuario(id[cboTipoUsuario.getSelectedIndex()]);
            empleados.agregarEmpleado(empleado);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsEmpleado empleados=new ClsEmpleado();
            ClsEntidadEmpleado empleado=new ClsEntidadEmpleado();
            empleado.setStrNombreEmpleado(txtNombre.getText());
            empleado.setStrApellidoEmpleado(txtApellido.getText());
            if (rbtnMasculino.isSelected()){
                empleado.setStrSexoEmpleado("M");
            }else if (rbtnFemenino.isSelected()){
                empleado.setStrSexoEmpleado("F");
            }
            empleado.setStrFechaNacEmpleado(txtFechaNac.getDate());
            empleado.setStrDireccionEmpleado(txtDireccion.getText());
            empleado.setStrTelefonoEmpleado(txtTelefono.getText());
            empleado.setStrCelularEmpleado(txtCelular.getText());
            empleado.setStrEmailEmpleado(txtEmail.getText());
            empleado.setStrDniEmpleado(txtDni.getText());
            empleado.setStrFechaIngEmpleado(txtFechaIng.getDate());
            if(txtSueldo.getText().equals("")){
                empleado.setStrSueldoEmpleado("0");
            }else{
                empleado.setStrSueldoEmpleado(txtSueldo.getText());
            }
            if (rbtnActivo.isSelected()){
                empleado.setStrEstadoEmpleado("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                empleado.setStrEstadoEmpleado("INACTIVO");
            }
            empleado.setStrUsuarioEmpleado(txtUsuario.getText());
            //empleado.setStrContraseñaEmpleado(txtContraseña.getText());
            if (txtContraseña.getText().length()==0){
                empleado.setStrContraseñaEmpleado("");
            }else{
                contraseña=txtContraseña.getText();
                resultado1=getStringMessageDigest(contraseña,algorithm);   
                empleado.setStrContraseñaEmpleado(resultado1);
            }
            empleado.setStrIdTipoUsuario(id[cboTipoUsuario.getSelectedIndex()]);
            empleados.modificarEmpleado(strCodigo, empleado);
            actualizarTabla();
            limpiarCampos();
            modificar();
            CantidadTotal();
        }
        CrearTabla();
        mirar();

    }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tblEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadoMouseClicked
    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
    Date fechanac = null,fechaing=null;
       
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblEmpleado.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblEmpleado.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));

            txtNombre.setText((String) defaultTableModel.getValueAt(fila, 1));
            txtApellido.setText((String) defaultTableModel.getValueAt(fila, 2));
            if ("M".equals((String) defaultTableModel.getValueAt(fila,3))){
               rbtnMasculino.setSelected(true);
            }else if ("F".equals((String) defaultTableModel.getValueAt(fila,3))){
               rbtnFemenino.setSelected(true);
            }
 
            String fecha_nac=defaultTableModel.getValueAt(fila, 4).toString().trim();
            try{
                fechanac=formatoDelTexto.parse(fecha_nac);
                
           } catch (ParseException ex) {

            ex.printStackTrace();

            }
            txtFechaNac.setDate(fechanac);
            txtDireccion.setText((String) defaultTableModel.getValueAt(fila, 5));
            txtTelefono.setText((String) defaultTableModel.getValueAt(fila, 6));
            txtCelular.setText((String) defaultTableModel.getValueAt(fila, 7));
            txtEmail.setText((String) defaultTableModel.getValueAt(fila, 8));
            txtDni.setText((String) defaultTableModel.getValueAt(fila, 9));
            
            String fecha_ing=defaultTableModel.getValueAt(fila, 10).toString().trim();
            try{
                fechaing=formatoDelTexto.parse(fecha_ing);
                
           } catch (ParseException ex) {

            ex.printStackTrace();

            }
            txtFechaIng.setDate(fechaing);
            txtSueldo.setText((String) defaultTableModel.getValueAt(fila, 11));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(fila,12))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(fila,12))){
               rbtnInactivo.setSelected(true);
            }
            txtUsuario.setText((String) defaultTableModel.getValueAt(fila, 13));
            txtContraseña.setText((String) defaultTableModel.getValueAt(fila, 14));
            cboTipoUsuario.setSelectedItem((String)defaultTableModel.getValueAt(fila,15));
        }

        mirar();
    }//GEN-LAST:event_tblEmpleadoMouseClicked

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarEmpleado();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
        txtNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtApellido.requestFocus();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
        txtApellido.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
       
        int i = txtDni.getText().length();
        if(txtDni.getText().trim().length()<8){

        }else{
            i=10;
            String com=txtDni.getText().substring(0, 7);
            txtDni.setText("");
            txtDni.setText(com);
        }
        txtDni.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDniKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Map p=new HashMap();
        p.put("busqueda",txtBusqueda.getText());
        if(rbtnNombre.isSelected()){
            p.put("criterio", "nombre");
        }else if(rbtnDni.isSelected()){
            p.put("criterio", "dni");
        }else{
            p.put("criterio", "");
        }
        JasperReport report;
        JasperPrint print;
        try{

            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptEmpleado.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte de Clientes");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtContraseñaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox cboTipoUsuario;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnDni;
    private javax.swing.JRadioButton rbtnFemenino;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.JRadioButton rbtnMasculino;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JTable tblEmpleado;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextArea txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtFechaIng;
    private com.toedter.calendar.JDateChooser txtFechaNac;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSueldo;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

