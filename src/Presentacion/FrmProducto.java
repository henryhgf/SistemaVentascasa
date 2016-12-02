/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

//--------------CODIGO DE BARRAS------------
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.*;   
import net.sourceforge.barbecue.BarcodeFactory;   
import net.sourceforge.barbecue.Barcode;   
import net.sourceforge.barbecue.BarcodeException;   
import net.sourceforge.barbecue.BarcodeImageHandler;   
import java.awt.image.BufferedImage;   
import java.awt.*;   
import java.awt.event.*;   
import java.io.File;
import java.io.FileOutputStream;   
import java.io.IOException;   
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sourceforge.barbecue.output.OutputException;

public class FrmProducto extends javax.swing.JInternalFrame {
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
    
    public FrmProducto() {
        initComponents();
        
        cargarComboCategoria();
        buttonGroup1.add(rbtnCodigo);
        buttonGroup1.add(rbtnNombre);
        buttonGroup1.add(rbtnDescripcion);
        buttonGroup2.add(rbtnActivo);
        buttonGroup2.add(rbtnInactivo);
        
        mirar();
        actualizarTabla();
        
        this.setSize(1270, 535);
        CrearTabla();
        CantidadTotal();
    }

  void CrearTabla(){

      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
                //aqui obtengo el render de la calse superior 
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
                //Determinar Alineaciones   
        
                    if(column==0 || column==1 || column==4 || column==5 || column==6 || column==7 || column==8 || column==9 || column==10){
                        l.setHorizontalAlignment(SwingConstants.CENTER); 
                    }else{
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                    }

                //Colores en Jtable        
                if (isSelected) {
                    l.setBackground(new Color(203, 159, 41));
                   
                    l.setForeground(Color.WHITE); 
                }else{
                    l.setForeground(Color.BLACK);
                    if (row != 0 ) {
                        l.setBackground(Color.WHITE);
                    } else {
                        l.setBackground(new Color(254, 227, 152));
                    }
                }     
                return l; 
            } 
        }; 
        
        //Agregar Render
        for (int i=0;i<tblProducto.getColumnCount();i++){
            tblProducto.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
  
        tblProducto.setAutoResizeMode(tblProducto.AUTO_RESIZE_OFF);

  
        int[] anchos = {40,100,150,200,60,60,60,60,60,80,100};
        for(int i = 0; i < tblProducto.getColumnCount(); i++) {
            tblProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
   }
   void CantidadTotal(){
        Total= String.valueOf(tblProducto.getRowCount());   
        lblEstado.setText("Tienes " + Total + " Productos Registrados");      
   }
   void limpiarCampos(){
       
       txtCodigoBar.setText("");
       txtNombre.setText("");
       txtDescripcion.setText("");
       txtStock.setText("");
       txtStockMin.setText("");
       txtPrecioCosto.setText("");
       txtPrecioVenta.setText("");
       txtUtilidad.setText("");
       txtCodigoBar.requestFocus();
       rbtnActivo.setSelected(true);
       rbtnInactivo.setSelected(false);
//       txtStock.setText("0");
//       txtStockMin.setText("0");
       txtPrecioCosto.setText("0.0");
       txtPrecioVenta.setText("0.0");
       txtUtilidad.setText("0.0");
       

       rbtnNombre.setSelected(false);
       rbtnDescripcion.setSelected(false);

       txtBusqueda.setText("");
       limpiarList();
   }
       
   void mirar(){
       tblProducto.setEnabled(true);
       btnNuevo.setEnabled(true);
       btnModificar.setEnabled(true);
       btnGuardar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnSalir.setEnabled(true);
        
       txtCodigoBar.setEnabled(false);
       txtNombre.setEnabled(false);
       txtDescripcion.setEnabled(false);
       txtStock.setEnabled(false);
       txtStockMin.setEnabled(false);
       txtPrecioCosto.setEnabled(false);
       txtPrecioVenta.setEnabled(false);
       cboCategoria.setEnabled(false);
       rbtnActivo.setEnabled(false);
       rbtnInactivo.setEnabled(false);
       
       cboTipoCodificacion.setEnabled(false);
       
   
   }
   
   void modificar(){
       tblProducto.setEnabled(false);
       btnNuevo.setEnabled(false);
       btnModificar.setEnabled(false);
       btnGuardar.setEnabled(true);
       btnCancelar.setEnabled(true);
       btnSalir.setEnabled(false);
        
       txtCodigoBar.setEnabled(true);
       txtNombre.setEnabled(true);
       txtDescripcion.setEnabled(true);
       txtStock.setEnabled(true);
       txtStockMin.setEnabled(true);
       txtPrecioCosto.setEnabled(true);
       txtPrecioVenta.setEnabled(true);
       cboCategoria.setEnabled(true);
       rbtnActivo.setEnabled(true);
       rbtnInactivo.setEnabled(true);
       
       cboTipoCodificacion.setEnabled(true);
       txtCodigoBar.requestFocus();
   }
  void cargarComboCategoria(){
  ClsCategoria tipodocumento=new ClsCategoria();
       ArrayList<ClsEntidadCategoria> categorias=tipodocumento.listarCategoria();
       Iterator iterator=categorias.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboCategoria.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadCategoria Categoria = new ClsEntidadCategoria();
           Categoria=(ClsEntidadCategoria) iterator.next();
           id[intContador]=Categoria.getStrIdCategoria();
           fila[0]=Categoria.getStrIdCategoria();
           fila[1]=Categoria.getStrDescripcionCategoria();
           DefaultComboBoxModel.addElement(Categoria.getStrDescripcionCategoria());
           intContador++;              
       }
       cboCategoria.setModel(DefaultComboBoxModel);
    }
    void actualizarTabla(){
       String titulos[]={"ID","Cód. de Barras","Nombre","Descripción","Stock","Stock Min.","Costo","Precio","Utilidad","Estado","Categoría"};
              
       ClsProducto productos=new ClsProducto();
       ArrayList<ClsEntidadProducto> producto=productos.listarProducto();
       Iterator iterator=producto.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[11];
       while(iterator.hasNext()){
           ClsEntidadProducto Producto=new ClsEntidadProducto();
           Producto=(ClsEntidadProducto) iterator.next();
           fila[0]=Producto.getStrIdProducto();
           fila[1]=Producto.getStrCodigoProducto();       
           fila[2]=Producto.getStrNombreProducto();
           fila[3]=Producto.getStrDescripcionProducto();
           fila[4]=Producto.getStrStockProducto();
           fila[5]=Producto.getStrStockMinProducto();
           fila[6]=Producto.getStrPrecioCostoProducto();
           fila[7]=Producto.getStrPrecioVentaProducto();
           fila[8]=Producto.getStrUtilidadProducto();
           fila[9]=Producto.getStrEstadoProducto();
           fila[10]=Producto.getStrDescripcionCategoria();
           defaultTableModel.addRow(fila);               
       }
       tblProducto.setModel(defaultTableModel);
   }
   void BuscarProducto(){
        String titulos[]={"ID","Cód. de Barras","Nombre","Descripción","Stock","Stock Min.","Costo","Precio","Utilidad","Estado","Categoría"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsProducto categoria=new ClsProducto();
        busqueda=txtBusqueda.getText();
        if(rbtnCodigo.isSelected()){
            criterio="codigo";
        }
        else if(rbtnNombre.isSelected()){
            criterio="nombre";
        }else if(rbtnDescripcion.isSelected()){
            criterio="descripcion";
        }
        try{
            rs=categoria.listarProductoPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[11];
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
                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblProducto.setModel(dtm);
    }

    void listardatos(){
        String estado;
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"Se debe seleccionar un registro");
        }else{
            defaultTableModel=(DefaultTableModel) tblProducto.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
            
            txtCodigoBar.setText((String)defaultTableModel.getValueAt(registros,1));
            txtNombre.setText((String)defaultTableModel.getValueAt(registros,2));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(registros,3));
            txtStock.setText((String)defaultTableModel.getValueAt(registros,4));
            txtStockMin.setText((String)defaultTableModel.getValueAt(registros,5));
            txtPrecioCosto.setText((String)defaultTableModel.getValueAt(registros,6));
            txtPrecioVenta.setText((String)defaultTableModel.getValueAt(registros,7));
            txtUtilidad.setText((String)defaultTableModel.getValueAt(registros,8));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(registros,9))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(registros,9))){
               rbtnInactivo.setSelected(true);
            }
            cboCategoria.setSelectedItem((String)defaultTableModel.getValueAt(registros,10));
            tblProducto.setRowSelectionInterval(registros,registros);
        }
    
    }
    void CalcularUtilidad(){
        double pre_costo=0,pre_venta=0,utilidad=0,t_utilidad;
        pre_costo=Double.parseDouble(txtPrecioCosto.getText());
        pre_venta=Double.parseDouble(txtPrecioVenta.getText());
        utilidad=pre_venta-pre_costo;
        t_utilidad=Math.rint(utilidad*100)/100;
        txtUtilidad.setText(String.valueOf(t_utilidad));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
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
        txtCodigoBar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtStockMin = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtPrecioCosto = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rbtnActivo = new javax.swing.JRadioButton();
        rbtnInactivo = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtUtilidad = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cboCategoria = new javax.swing.JComboBox();
        cboTipoCodificacion = new javax.swing.JComboBox();
        pBuscar = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtBusqueda = new javax.swing.JTextField();
        rbtnNombre = new javax.swing.JRadioButton();
        rbtnDescripcion = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        rbtnCodigo = new javax.swing.JRadioButton();
        btnImprimir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Productos");
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
        btnModificar.setBounds(900, 380, 81, 70);

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
        btnCancelar.setBounds(1060, 380, 81, 70);

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
        btnNuevo.setBounds(740, 380, 81, 70);

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
        btnSalir.setBounds(980, 380, 81, 70);

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
        btnGuardar.setBounds(820, 380, 81, 70);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(710, 360, 530, 100);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Registro"));
        pNuevo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Código de Barras:");
        pNuevo.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 100, 20));

        txtCodigoBar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtCodigoBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarActionPerformed(evt);
            }
        });
        txtCodigoBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoBarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoBarKeyTyped(evt);
            }
        });
        pNuevo.add(txtCodigoBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 130, -1));

        jLabel5.setText("Nombre:");
        pNuevo.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 50, 20));

        txtNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)));
        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });
        pNuevo.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 310, -1));

        jLabel6.setText("Stock:");
        pNuevo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 40, 30));

        txtStockMin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStockMin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockMinKeyReleased(evt);
            }
        });
        pNuevo.add(txtStockMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 220, 80, 30));

        jLabel7.setText("Stock Mínimo:");
        pNuevo.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 80, 30));

        txtPrecioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioVenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioVentaKeyTyped(evt);
            }
        });
        pNuevo.add(txtPrecioVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 80, 30));

        jLabel8.setText("Descripción:");
        pNuevo.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 70, 20));

        jLabel10.setText("Precio Costo:");
        pNuevo.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 80, 30));

        txtPrecioCosto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtPrecioCosto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCostoKeyReleased(evt);
            }
        });
        pNuevo.add(txtPrecioCosto, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 80, 30));

        jLabel12.setText("Precio Venta:");
        pNuevo.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 80, 30));

        rbtnActivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnActivo.setText("ACTIVO");
        rbtnActivo.setOpaque(false);
        pNuevo.add(rbtnActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 178, 80, -1));

        rbtnInactivo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        rbtnInactivo.setText("INACTIVO");
        rbtnInactivo.setOpaque(false);
        pNuevo.add(rbtnInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 178, 90, -1));

        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Estado"));
        pNuevo.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 160, 190, 50));

        txtStock.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtStockKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });
        pNuevo.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 80, 30));

        jLabel15.setText("Utilidad:");
        pNuevo.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 50, 30));

        txtUtilidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUtilidad.setEnabled(false);
        pNuevo.add(txtUtilidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 80, 30));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane3.setViewportView(txtDescripcion);

        pNuevo.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 310, 50));

        jLabel4.setText("Categoría:");
        pNuevo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 60, 30));

        cboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pNuevo.add(cboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 120, 30));

        cboTipoCodificacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Code 128" }));
        cboTipoCodificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTipoCodificacionActionPerformed(evt);
            }
        });
        pNuevo.add(cboTipoCodificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 160, 30));

        getContentPane().add(pNuevo);
        pNuevo.setBounds(710, 10, 530, 350);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones de Busqueda"));
        pBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pBuscar.add(lblEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 200, 20));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        jButton3.setText("Imprimir Lista");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        pBuscar.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 150, 50));

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });
        pBuscar.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 440, -1));

        rbtnNombre.setText("Nombre");
        rbtnNombre.setOpaque(false);
        rbtnNombre.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnNombreStateChanged(evt);
            }
        });
        pBuscar.add(rbtnNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 80, -1));

        rbtnDescripcion.setText("Descripción");
        rbtnDescripcion.setOpaque(false);
        rbtnDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnDescripcionActionPerformed(evt);
            }
        });
        pBuscar.add(rbtnDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, -1, -1));

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblProducto.setRowHeight(22);
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        pBuscar.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 640, 260));

        rbtnCodigo.setBackground(new java.awt.Color(255, 255, 255));
        rbtnCodigo.setText("Codigo");
        pBuscar.add(rbtnCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        getContentPane().add(pBuscar);
        pBuscar.setBounds(10, 10, 665, 460);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/printer.png"))); // NOI18N
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimir);
        btnImprimir.setBounds(1140, 380, 80, 70);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(tblProducto.getSelectedRows().length > 0 ) {
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
        tblProducto.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if(txtCodigoBar.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Especifique un código de barras para el Producto");
            txtCodigoBar.requestFocus();
            txtCodigoBar.setBackground(Color.YELLOW);
            return false;
        }else if(txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese un nombre para el Producto");
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
            ClsProducto productos=new ClsProducto();
            ClsEntidadProducto producto=new ClsEntidadProducto();
            producto.setStrCodigoProducto(txtCodigoBar.getText());
            producto.setStrNombreProducto(txtNombre.getText());
            producto.setStrDescripcionProducto(txtDescripcion.getText());
            if(txtStock.getText().equals("")){
                producto.setStrStockProducto("0");
            }else{
                producto.setStrStockProducto(txtStock.getText());
            }
            if(txtStockMin.getText().equals("")){
                producto.setStrStockMinProducto("0");
            }else{
                producto.setStrStockMinProducto(txtStockMin.getText());
            }
            if(txtPrecioCosto.getText().equals("")){
                producto.setStrPrecioCostoProducto("0");
            }else{
                producto.setStrPrecioCostoProducto(txtPrecioCosto.getText());
            }
            if(txtPrecioVenta.getText().equals("")){
                producto.setStrPrecioVentaProducto("0");
            }else{
                producto.setStrPrecioVentaProducto(txtPrecioVenta.getText());
            }
            if(txtUtilidad.getText().equals("")){
                producto.setStrUtilidadProducto("0");
            }else{
                producto.setStrUtilidadProducto(txtUtilidad.getText());
            }           
            if (rbtnActivo.isSelected()){
                producto.setStrEstadoProducto("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                producto.setStrEstadoProducto("INACTIVO");
            }
            producto.setStrIdCategoria(id[cboCategoria.getSelectedIndex()]);
            productos.agregarProducto(producto);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsProducto productos=new ClsProducto();
            ClsEntidadProducto producto=new ClsEntidadProducto();
            producto.setStrCodigoProducto(txtCodigoBar.getText());
            producto.setStrNombreProducto(txtNombre.getText());
            producto.setStrDescripcionProducto(txtDescripcion.getText());
            if(txtStock.getText().equals("")){
                producto.setStrStockProducto("0");
            }else{
                producto.setStrStockProducto(txtStock.getText());
            }
            if(txtStockMin.getText().equals("")){
                producto.setStrStockMinProducto("0");
            }else{
                producto.setStrStockMinProducto(txtStockMin.getText());
            }
            if(txtPrecioCosto.getText().equals("")){
                producto.setStrPrecioCostoProducto("0");
            }else{
                producto.setStrPrecioCostoProducto(txtPrecioCosto.getText());
            }
            if(txtPrecioVenta.getText().equals("")){
                producto.setStrPrecioVentaProducto("0");
            }else{
                producto.setStrPrecioVentaProducto(txtPrecioVenta.getText());
            }
            if(txtUtilidad.getText().equals("")){
                producto.setStrUtilidadProducto("0");
            }else{
                producto.setStrUtilidadProducto(txtUtilidad.getText());
            } 
            if (rbtnActivo.isSelected()){
                producto.setStrEstadoProducto("ACTIVO");
            }else if (rbtnInactivo.isSelected()){
                producto.setStrEstadoProducto("INACTIVO");
            }
            producto.setStrIdCategoria(id[cboCategoria.getSelectedIndex()]);

            productos.modificarProducto(strCodigo, producto);
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
        BuscarProducto();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnNombreStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnNombreStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnNombreStateChanged

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblProducto.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblProducto.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));
            
            txtCodigoBar.setText((String)defaultTableModel.getValueAt(fila,1));
            txtNombre.setText((String)defaultTableModel.getValueAt(fila,2));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(fila,3));
            txtStock.setText((String)defaultTableModel.getValueAt(fila,4));
            txtStockMin.setText((String)defaultTableModel.getValueAt(fila,5));
            txtPrecioCosto.setText((String)defaultTableModel.getValueAt(fila,6));
            txtPrecioVenta.setText((String)defaultTableModel.getValueAt(fila,7));
            txtUtilidad.setText((String)defaultTableModel.getValueAt(fila,8));
            if ("ACTIVO".equals((String) defaultTableModel.getValueAt(fila,9))){
               rbtnActivo.setSelected(true);
            }else if ("INACTIVO".equals((String) defaultTableModel.getValueAt(fila,9))){
               rbtnInactivo.setSelected(true);
            }
            cboCategoria.setSelectedItem((String)defaultTableModel.getValueAt(fila,10));

        }

        mirar();
    }//GEN-LAST:event_tblProductoMouseClicked

    private void txtCodigoBarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarKeyTyped
        txtCodigoBar.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtCodigoBarKeyTyped

    private void txtPrecioCostoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCostoKeyReleased
        CalcularUtilidad();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtPrecioVenta.requestFocus();  
    }//GEN-LAST:event_txtPrecioCostoKeyReleased

    private void txtPrecioVentaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyReleased
        CalcularUtilidad();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();  
    }//GEN-LAST:event_txtPrecioVentaKeyReleased
       void verificarCodigoBar(){
        String busqueda=null;
        int sen = 2;
        busqueda=txtCodigoBar.getText(); 
        
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.verificarCodigoBar(busqueda);
                while (rs.next()) {
                    if(!rs.getString(2).equals("")) {
                               
                       sen=1;
                    }else{

                       sen=2;
                    }
                   break;
                }
 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
            
            if(sen==1){
                JOptionPane.showMessageDialog(null, "Codigo No Disponible");
            }else if (sen==2){
                JOptionPane.showMessageDialog(null, "Codigo Disponible");
            }else if(rs==null){
                JOptionPane.showMessageDialog(null, "no hay");
            }
    
    }
    void limpiarList(){
        DefaultListModel model = new DefaultListModel();
    }

    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Map p=new HashMap();
        p.put("busqueda",txtBusqueda.getText());
       if(rbtnNombre.isSelected()){
            p.put("criterio", "nombre");
        }else if(rbtnDescripcion.isSelected()){
            p.put("criterio", "descripcion");
        }else{
            p.put("criterio", "");
        }
        JasperReport report;
        JasperPrint print;
        try{
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte General de Productos");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        txtNombre.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
        txtStock.setBackground(Color.WHITE);        
    }//GEN-LAST:event_txtStockKeyTyped

    private void txtPrecioVentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioVentaKeyTyped
        txtPrecioVenta.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtPrecioVentaKeyTyped

    private void txtCodigoBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoBarKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtNombre.requestFocus();        
    }//GEN-LAST:event_txtCodigoBarKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtDescripcion.requestFocus();         
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtStockKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtStockMin.requestFocus();        
    }//GEN-LAST:event_txtStockKeyReleased

    private void txtStockMinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockMinKeyReleased
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtPrecioCosto.requestFocus();     
    }//GEN-LAST:event_txtStockMinKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        Map p=new HashMap();
        String cod=txtCodigoBar.getText();
        p.put("codigo",cod);

        JasperReport report;
        JasperPrint print;
        if(cboTipoCodificacion.getSelectedItem().equals("Code 128")){
            try{
                report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptProducto_Code128.jrxml");
                print=JasperFillManager.fillReport(report, p,new JREmptyDataSource());
                JasperViewer view=new JasperViewer(print,false);
                view.setTitle("Código de Barras - CODE128");
                view.setVisible(true);
            }catch(JRException e){
                e.printStackTrace();
            }
         }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cboTipoCodificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTipoCodificacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTipoCodificacionActionPerformed

    private void txtCodigoBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodigoBarActionPerformed

    private void rbtnDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnDescripcionActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cboCategoria;
    private javax.swing.JComboBox cboTipoCodificacion;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JRadioButton rbtnActivo;
    private javax.swing.JRadioButton rbtnCodigo;
    private javax.swing.JRadioButton rbtnDescripcion;
    private javax.swing.JRadioButton rbtnInactivo;
    private javax.swing.JRadioButton rbtnNombre;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtCodigoBar;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecioCosto;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtStockMin;
    private javax.swing.JTextField txtUtilidad;
    // End of variables declaration//GEN-END:variables
}
