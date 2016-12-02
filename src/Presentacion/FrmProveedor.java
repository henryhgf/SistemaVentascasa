/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
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

public class FrmProveedor extends javax.swing.JInternalFrame {
    private Connection connection=new ClsConexion().getConection();
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
    
    public FrmProveedor() {
        initComponents();
        buttonGroup1.add(rbtnCodigo);
        buttonGroup1.add(rbtnNombre);
        buttonGroup1.add(rbtnRuc);
        buttonGroup1.add(rbtnDni);
        buttonGroup2.add(rbtnActivo);
        buttonGroup2.add(rbtnInactivo);
        mirar();
        actualizarTabla();
      
        this.setSize(1250, 540);
        CrearTabla();
        CantidadTotal();
    }


  void CrearTabla(){
 
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
            
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                
                    if(column==0 || column==2 || column==3 || column==5 || column==6 || column==8 || column==9 || column==10){
                        l.setHorizontalAlignment(SwingConstants.CENTER); 
                    }else{
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                    }

                   
                if (isSelected) {
                    l.setBackground(new Color(203, 159, 41));
                 
                    l.setForeground(Color.WHITE); 
                }else{
                    l.setForeground(Color.BLACK);
                    if (row != 0) {
                        l.setBackground(Color.WHITE);
                    } else {
                     
                        l.setBackground(new Color(254, 227, 152));
                    }
                }      
                return l; 
            } 
        }; 
        
       
        for (int i=0;i<tblProveedor.getColumnCount();i++){
            tblProveedor.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        
        tblProveedor.setAutoResizeMode(tblProveedor.AUTO_RESIZE_OFF);

        
        int[] anchos = {30,200,80,80,180,70,70,150,80,80,70,200};
        for(int i = 0; i < tblProveedor.getColumnCount(); i++) {
            tblProveedor.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
   void CantidadTotal(){
        Total= String.valueOf(tblProveedor.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
   }
   void limpiarCampos(){
       txtNombre.setText("");
       txtRuc.setText("");
       txtDni.setText("");
       txtDireccion.setText("");
       txtTelefono.setText("");
       txtCelular.setText("");
       txtEmail.setText("");
       txtCuenta1.setText("");
       txtCuenta2.setText("");
       rbtnActivo.setSelected(true);
       rbtnInactivo.setSelected(false);
       txtObservacion.setText("");
       
       rbtnCodigo.setSelected(false);
       rbtnNombre.setSelected(false);
       rbtnRuc.setSelected(false);
       rbtnDni.setSelected(false);
       txtBusqueda.setText("");
   }
       
   void mirar(){
       tblProveedor.setEnabled(true);
       btnNuevo.setEnabled(true);
       btnModificar.setEnabled(true);
       btnGuardar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnSalir.setEnabled(true);
        
       txtNombre.setEnabled(false);
       txtRuc.setEnabled(false);
       txtDni.setEnabled(false);
       txtDireccion.setEnabled(false);
       txtTelefono.setEnabled(false);
       txtCelular.setEnabled(false);
       txtEmail.setEnabled(false);
       txtCuenta1.setEnabled(false);
       txtCuenta2.setEnabled(false);
       rbtnActivo.setEnabled(false);
       rbtnInactivo.setEnabled(false);
       txtObservacion.setEnabled(false);
   
   }
   
   void modificar(){
       tblProveedor.setEnabled(false);
       btnNuevo.setEnabled(false);
       btnModificar.setEnabled(false);
       btnGuardar.setEnabled(true);
       btnCancelar.setEnabled(true);
       btnSalir.setEnabled(false);
        
       txtNombre.setEnabled(true);
       txtRuc.setEnabled(true);
       txtDni.setEnabled(true);
       txtDireccion.setEnabled(true);
       txtTelefono.setEnabled(true);
       txtCelular.setEnabled(true);
       txtEmail.setEnabled(true);
       txtCuenta1.setEnabled(true);
       txtCuenta2.setEnabled(true);
       rbtnActivo.setEnabled(true);
       rbtnInactivo.setEnabled(true);
       txtObservacion.setEnabled(true); 
       txtNombre.requestFocus();
   }
   
   
    void actualizarTabla(){
       String titulos[]={"ID","Nombre o Razón Social","RUC","DNI","Dirección","Teléfono","Celular","Email","Nº Cuenta 1","Nº Cuenta 2","Estado","Observación"};
              
       ClsProveedor proveedores=new ClsProveedor();
       ArrayList<ClsEntidadProveedor> proveedor=proveedores.listarProveedor();
       Iterator iterator=proveedor.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[12];
       while(iterator.hasNext()){
           ClsEntidadProveedor Proveedor=new ClsEntidadProveedor();
           Proveedor=(ClsEntidadProveedor) iterator.next();
           fila[0]=Proveedor.getStrIdProveedor();
           fila[1]=Proveedor.getStrNombreProveedor();       
           fila[2]=Proveedor.getStrRucProveedor();
           fila[3]=Proveedor.getStrDniProveedor();
           fila[4]=Proveedor.getStrDireccionProveedor();
           fila[5]=Proveedor.getStrTelefonoProveedor();
           fila[6]=Proveedor.getStrCelularProveedor();
           fila[7]=Proveedor.getStrEmailProveedor();
           fila[8]=Proveedor.getStrCuenta1Proveedor();
           fila[9]=Proveedor.getStrCuenta2Proveedor();
           fila[10]=Proveedor.getStrEstadoProveedor();
           fila[11]=Proveedor.getStrObsvProveedor();
           defaultTableModel.addRow(fila);               
       }
       tblProveedor.setModel(defaultTableModel);
   }
   void BuscarProveedor(){
        String titulos[]={"ID","Nombre o Razón Social","RUC","DNI","Dirección","Teléfono","Celular","Email","Nº Cuenta 1","Nº Cuenta 2","Estado","Observación"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsProveedor categoria=new ClsProveedor();
        busqueda=txtBusqueda.getText();
        if(rbtnCodigo.isSelected()){
            criterio="id";
        }else if(rbtnNombre.isSelected()){
            criterio="nombre";
        }else if(rbtnRuc.isSelected()){
            criterio="ruc";
        }else if(rbtnDni.isSelected()){
            criterio="dni";
        }
        try{
            rs=categoria.listarProveedorPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[12];
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

                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblProveedor.setModel(dtm);
    }
    void listardatos(){
        String estado;
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"Se debe seleccionar un registro");
        }else{
            defaultTableModel=(DefaultTableModel) tblProveedor.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
            txtNombre.setText((String)defaultTableModel.getValueAt(registros,1));
            txtRuc.setText((String)defaultTableModel.getValueAt(registros,2));
            txtDni.setText((String)defaultTableModel.getValueAt(registros,3));
            txtDireccion.setText((String)defaultTableModel.getValueAt(registros,4));
            txtTelefono.setText((String)defaultTableModel.getValueAt(registros,5));
            txtCelular.setText((String)defaultTableModel.getValueAt(registros,6));
            txtEmail.setText((String)defaultTableModel.getValueAt(registros,7));
            txtCuenta1.setText((String)defaultTableModel.getValueAt(registros,8));
            txtCuenta2.setText((String)defaultTableModel.getValueAt(registros,9));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(registros,10))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(registros,10))){
               rbtnInactivo.setSelected(true);
            }
            txtObservacion.setText((String)defaultTableModel.getValueAt(registros,11));
            tblProveedor.setRowSelectionInterval(registros,registros);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pNuevo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        txtCelular = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCuenta1 = new javax.swing.JTextField();
        txtCuenta2 = new javax.swing.JTextField();
        pBuscar = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProveedor = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnRuc = new javax.swing.JRadioButton();
        rbtnDni = new javax.swing.JRadioButton();
        rbtnCodigo = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Proveedores");
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
        btnModificar.setBounds(870, 370, 81, 70);

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
        btnCancelar.setBounds(950, 370, 81, 70);

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
        btnNuevo.setBounds(710, 370, 81, 70);

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
        btnSalir.setBounds(1030, 370, 81, 70);

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
        btnGuardar.setBounds(790, 370, 81, 70);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(620, 350, 570, 100);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Registro"));
        pNuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre o Razón Social:");
        pNuevo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 20));

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        pNuevo.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 380, 20));

        jLabel5.setText("R.U.C.:");
        pNuevo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 90, 50, 20));

        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRucKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });
        pNuevo.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 150, -1));

        jLabel6.setText("Dirección:");
        pNuevo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 120, 55, 20));

        txtDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDireccionKeyReleased(evt);
            }
        });
        pNuevo.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 380, -1));

        jLabel7.setText("Teléfono:");
        pNuevo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 150, 50, 20));

        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });
        pNuevo.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 150, -1));

        jLabel8.setText("DNI:");
        pNuevo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 30, 20));

        txtDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDniKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniKeyTyped(evt);
            }
        });
        pNuevo.add(txtDni, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 150, -1));

        jLabel9.setText("Observación:");
        pNuevo.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 70, 20));

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane2.setViewportView(txtObservacion);

        pNuevo.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 380, 50));

        jLabel10.setText("Celular:");
        pNuevo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 150, 45, 20));

        txtCelular.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCelularKeyReleased(evt);
            }
        });
        pNuevo.add(txtCelular, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 150, -1));

        jLabel12.setText("E-mail:");
        pNuevo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 40, 20));

        rbtnActivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnActivo.setText("ACTIVO");
        rbtnActivo.setOpaque(false);
        pNuevo.add(rbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 195, 70, -1));

        rbtnInactivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        rbtnInactivo.setText("INACTIVO");
        rbtnInactivo.setOpaque(false);
        pNuevo.add(rbtnInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 195, 80, -1));

        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));
        pNuevo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 190, 45));

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyReleased(evt);
            }
        });
        pNuevo.add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 150, -1));

        jLabel4.setText("Nº Cuenta 1:");
        pNuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 70, 20));

        jLabel15.setText("Nº Cuenta 2:");
        pNuevo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 70, 20));

        txtCuenta1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuenta1KeyReleased(evt);
            }
        });
        pNuevo.add(txtCuenta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 230, 150, -1));

        txtCuenta2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCuenta2KeyReleased(evt);
            }
        });
        pNuevo.add(txtCuenta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 150, -1));

        getContentPane().add(pNuevo);
        pNuevo.setBounds(620, 20, 570, 330);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel De Busqueda"));
        pBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        jButton3.setText("Imprimir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pBuscar.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 370, 120, 50));
        pBuscar.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 200, 20));

        tblProveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProveedor.setRowHeight(22);
        tblProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProveedor);

        pBuscar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 530, 230));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones de Busqueda"));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        rbtnNombre.setText("Nombre o Razón Social");
        rbtnNombre.setOpaque(false);
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });

        rbtnRuc.setText("R.U.C.");
        rbtnRuc.setOpaque(false);

        rbtnDni.setText("D.N.I.");
        rbtnDni.setOpaque(false);

        rbtnCodigo.setText("ID Proveedor");
        rbtnCodigo.setOpaque(false);
        rbtnCodigo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnCodigoStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(rbtnCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(rbtnRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbtnDni)))
                .addContainerGap(128, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnNombre)
                    .addComponent(rbtnRuc)
                    .addComponent(rbtnDni)
                    .addComponent(rbtnCodigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pBuscar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 530, 70));

        getContentPane().add(pBuscar);
        pBuscar.setBounds(30, 20, 570, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProveedorMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblProveedor.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblProveedor.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));
            txtNombre.setText((String) defaultTableModel.getValueAt(fila, 1));
            txtRuc.setText((String)defaultTableModel.getValueAt(fila,2));
            txtDni.setText((String)defaultTableModel.getValueAt(fila,3));
            txtDireccion.setText((String)defaultTableModel.getValueAt(fila,4));
            txtTelefono.setText((String)defaultTableModel.getValueAt(fila,5));
            txtCelular.setText((String)defaultTableModel.getValueAt(fila,6));
            txtEmail.setText((String)defaultTableModel.getValueAt(fila,7));
            txtCuenta1.setText((String)defaultTableModel.getValueAt(fila,8));
            txtCuenta2.setText((String)defaultTableModel.getValueAt(fila,9));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(fila,10))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(fila,10))){
               rbtnInactivo.setSelected(true);
            }
            txtObservacion.setText((String)defaultTableModel.getValueAt(fila,11));
        }

        mirar();
    }//GEN-LAST:event_tblProveedorMouseClicked

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
    txtNombre.setBackground(Color.WHITE);
        //        char car = evt.getKeyChar();
        //        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
    }//GEN-LAST:event_txtNombreKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    if(tblProveedor.getSelectedRows().length > 0 ) { 
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
        tblProveedor.setEnabled(false);  
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if (txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese un nombre o razón social del proveedor");
            txtNombre.requestFocus();
            txtNombre.setBackground(Color.YELLOW);
            return false;
        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(validardatos()==true){  
        if(accion.equals("Nuevo")){
            ClsProveedor proveedores=new ClsProveedor();
            ClsEntidadProveedor proveedor=new ClsEntidadProveedor();
            proveedor.setStrNombreProveedor(txtNombre.getText());
            proveedor.setStrRucProveedor(txtRuc.getText());
            proveedor.setStrDniProveedor(txtDni.getText());
            proveedor.setStrDireccionProveedor(txtDireccion.getText());
            proveedor.setStrTelefonoProveedor(txtTelefono.getText());
            proveedor.setStrCelularProveedor(txtCelular.getText());
            proveedor.setStrEmailProveedor(txtEmail.getText());
            proveedor.setStrCuenta1Proveedor(txtCuenta1.getText());
            proveedor.setStrCuenta2Proveedor(txtCuenta2.getText());
            if (rbtnActivo.isSelected()){
                proveedor.setStrEstadoProveedor("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                proveedor.setStrEstadoProveedor("INACTIVO");
            }
            proveedor.setStrObsvProveedor(txtObservacion.getText());
            proveedores.agregarProveedor(proveedor);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsProveedor proveedores=new ClsProveedor();
            ClsEntidadProveedor proveedor=new ClsEntidadProveedor();
            proveedor.setStrNombreProveedor(txtNombre.getText());
            proveedor.setStrRucProveedor(txtRuc.getText());
            proveedor.setStrDniProveedor(txtDni.getText());
            proveedor.setStrDireccionProveedor(txtDireccion.getText());
            proveedor.setStrTelefonoProveedor(txtTelefono.getText());
            proveedor.setStrCelularProveedor(txtCelular.getText());
            proveedor.setStrEmailProveedor(txtEmail.getText());
            proveedor.setStrCuenta1Proveedor(txtCuenta1.getText());
            proveedor.setStrCuenta2Proveedor(txtCuenta2.getText());
            if (rbtnActivo.isSelected()){
                proveedor.setStrEstadoProveedor("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                proveedor.setStrEstadoProveedor("INACTIVO");
            }
            proveedor.setStrObsvProveedor(txtObservacion.getText());
            proveedores.modificarProveedor(strCodigo, proveedor);
            actualizarTabla();            
            modificar();
            limpiarCampos();
            CantidadTotal();
        }
        CrearTabla();
        mirar();
    }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarProveedor();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnCodigoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnCodigoStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnCodigoStateChanged

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        String cadena= (txtNombre.getText()).toUpperCase();
        txtNombre.setText(cadena);
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtRuc.requestFocus();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtRucKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtDni.requestFocus();
    }//GEN-LAST:event_txtRucKeyReleased

    private void txtDniKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtDireccion.requestFocus();
    }//GEN-LAST:event_txtDniKeyReleased

    private void txtDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDireccionKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtTelefono.requestFocus();
    }//GEN-LAST:event_txtDireccionKeyReleased

    private void txtTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtCelular.requestFocus();        
    }//GEN-LAST:event_txtTelefonoKeyReleased

    private void txtCelularKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCelularKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtEmail.requestFocus();    
    }//GEN-LAST:event_txtCelularKeyReleased

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtCuenta1.requestFocus();
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtCuenta1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuenta1KeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtCuenta2.requestFocus();
    }//GEN-LAST:event_txtCuenta1KeyReleased

    private void txtCuenta2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuenta2KeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();
    }//GEN-LAST:event_txtCuenta2KeyReleased

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
        //----------------Poner limite de caracteres--------------------
        int i = txtRuc.getText().length();
        if(txtRuc.getText().trim().length()<11){

        }else{
            i=10;
            String com=txtRuc.getText().substring(0, 10);
            txtRuc.setText("");
            txtRuc.setText(com); 
        }
    }//GEN-LAST:event_txtRucKeyTyped

    private void txtDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
        //----------------Poner limite de caracteres--------------------
        int i = txtDni.getText().length();
        if(txtDni.getText().trim().length()<8){

        }else{
            i=10;
            String com=txtDni.getText().substring(0, 7);
            txtDni.setText("");
            txtDni.setText(com);
        }
    }//GEN-LAST:event_txtDniKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Map p=new HashMap();
        p.put("busqueda",txtBusqueda.getText());
        if(rbtnCodigo.isSelected()){
            p.put("criterio", "id");
        }
        else if(rbtnNombre.isSelected()){
            p.put("criterio", "nombre");
        }else if(rbtnRuc.isSelected()){
            p.put("criterio", "ruc");
        }else if(rbtnDni.isSelected()){
            p.put("criterio", "dni");
        }else{
            p.put("criterio", "");
        }
        JasperReport report;
        JasperPrint print;
        try{

            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProveedor.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte de Clientes");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnDni;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JRadioButton rbtnRuc;
    private javax.swing.JTable tblProveedor;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCuenta1;
    private javax.swing.JTextField txtCuenta2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
