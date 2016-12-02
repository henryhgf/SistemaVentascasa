/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FrmTipoUsuario extends javax.swing.JInternalFrame {

    String Total;
    String strCodigo;
    String accion;
   
    String p_venta="0",p_compra="0",p_producto="0",p_proveedor="0",p_empleado="0";
    String p_cliente="0",p_categoria="0",p_tipodoc="0",p_tipouser="0",p_anularv="0",p_anularc="0";
    String p_estadoprod="0",p_ventare="0",p_ventade="0",p_estadistica="0",p_comprare="0",p_comprade="0",p_pass="0",p_respaldar="0",p_restaurar="0",p_caja="0";
 
    int registros;
    String id[]=new String[50];
    static int intContador;
    

    public String codigo;
    static Connection conn=null;
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    String criterio,busqueda;
    
    public FrmTipoUsuario() {
        initComponents();

        buttonGroup1.add(rbtnDescripcion);
        mirar();
        actualizarTabla();
       
        this.setSize(1200, 515);
        CrearTabla();
        CantidadTotal();
    }


  void CrearTabla(){
  
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
             
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
               
                    if(column==0){
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
                    }else {
                     
                        l.setBackground(new Color(254, 227, 152));
                    }
                }     
                return l; 
            } 
        }; 
        
      
        for (int i=0;i<tblTipoUsuario.getColumnCount();i++){
            tblTipoUsuario.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
      
        tblTipoUsuario.setAutoResizeMode(tblTipoUsuario.AUTO_RESIZE_OFF);

      
        int[] anchos = {50,180,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80,80};
        for(int i = 0; i < tblTipoUsuario.getColumnCount(); i++) {
            tblTipoUsuario.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
    }
   void CantidadTotal(){
        Total= String.valueOf(tblTipoUsuario.getRowCount());   
        lblEstado.setText("Se cargaron " + Total + " registros");      
   }
   void limpiarCampos(){
   
       txtDescripcion.setText(""); 
       chkRProducto.isSelected();
       
  
       rbtnDescripcion.setSelected(false);
       chkRVenta.setSelected(false);
       chkRCompra.setSelected(false);
       chkRProducto.setSelected(false);
       chkRProveedor.setSelected(false);
       chkREmpleado.setSelected(false);
       chkRCliente.setSelected(false);
       chkRCategoria.setSelected(false);
       chkRTipodoc.setSelected(false);
       chkRTipouser.setSelected(false);
       chkAnularv.setSelected(false);
       chkAnularc.setSelected(false);
       chkEstado.setSelected(false);
       chkVentare.setSelected(false);
       chkVentade.setSelected(false);
       chkEstadistica.setSelected(false);
       chkComprare.setSelected(false);
       chkComprade.setSelected(false);
       chkPass.setSelected(false);
       chkRespaldar.setSelected(false);
       chkRestaurar.setSelected(false);
       chkCaja.setSelected(false);
       
       txtBusqueda.setText("");
   }
    
        
   void mirar(){
        tblTipoUsuario.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnSalir.setEnabled(true);
        
        txtDescripcion.setEnabled(false);
   
    }
   
    void modificar(){
        tblTipoUsuario.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnSalir.setEnabled(false);
        
        txtDescripcion.setEnabled(true);
        txtDescripcion.requestFocus();
    }
   
   
    void actualizarTabla(){
       String titulos[]={"ID","Descripción","P. venta","P. compra","P. producto","P. proveedor","P. empleado","P. cliente","P. categoria","P. tipodoc","P. tipouser","P. anularv","P. anularc",
           "P. estadoprod","P. ventare ","P. ventade","P. estadist.","P. comprare","P. comprade","P. pass","P. respaldar","P. restaurar","P. caja"};
      
       ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
       ArrayList<ClsEntidadTipoUsuario> tipousuario=tipousuarios.listarTipoUsuario();
       Iterator iterator=tipousuario.iterator();
       DefaultTableModel defaultTableModel=new DefaultTableModel(null,titulos);
       
       String fila[]=new String[23];
       while(iterator.hasNext()){
           ClsEntidadTipoUsuario TipoUsuario=new ClsEntidadTipoUsuario();
           TipoUsuario=(ClsEntidadTipoUsuario) iterator.next();
           fila[0]=TipoUsuario.getStrIdTipoUsuario();
           fila[1]=TipoUsuario.getStrDescripcionTipoUsuario();
           fila[2]=TipoUsuario.getStrP_Venta();  
           fila[3]=TipoUsuario.getStrP_Compra();  
           fila[4]=TipoUsuario.getStrP_Producto();  
           fila[5]=TipoUsuario.getStrP_Proveedor();  
           fila[6]=TipoUsuario.getStrP_Empleado();  
           fila[7]=TipoUsuario.getStrP_Cliente();  
           fila[8]=TipoUsuario.getStrP_Categoria();  
           fila[9]=TipoUsuario.getStrP_Tipodoc();  
           fila[10]=TipoUsuario.getStrP_Tipouser();  
           fila[11]=TipoUsuario.getStrP_Anularv();  
           fila[12]=TipoUsuario.getStrP_Anularc();
           fila[13]=TipoUsuario.getStrP_Estadoprod(); 
           fila[14]=TipoUsuario.getStrP_Ventare(); 
           fila[15]=TipoUsuario.getStrP_Ventade(); 
           fila[16]=TipoUsuario.getStrP_Estadistica(); 
           fila[17]=TipoUsuario.getStrP_Comprare(); 
           fila[18]=TipoUsuario.getStrP_Comprade(); 
           fila[19]=TipoUsuario.getStrP_Pass(); 
           fila[20]=TipoUsuario.getStrP_Respaldar(); 
           fila[21]=TipoUsuario.getStrP_Restaurar(); 
           fila[22]=TipoUsuario.getStrP_Caja();
           
           defaultTableModel.addRow(fila);
                
       }
       tblTipoUsuario.setModel(defaultTableModel);
   }
   void BuscarTipoUsuario(){
        String titulos[]={"ID","Descripción","P. venta","P. compra","P. producto","P. proveedor","P. empleado","P. cliente","P. categoria","P. tipodoc","P. tipouser","P. anularv","P. anularc",
            "P. estadoprod","P. ventare ","P. ventade","P. estadist.","P. comprare","P. comprade","P. pass","P. respaldar","P. restaurar","P. caja"};
        
        dtm.setColumnIdentifiers(titulos);
        
        ClsTipoUsuario categoria=new ClsTipoUsuario();
        busqueda=txtBusqueda.getText();
         if(rbtnDescripcion.isSelected()){
            criterio="descripcion";
        }
        try{
            rs=categoria.listarTipoUsuarioPorParametro(criterio,busqueda);
            boolean encuentra=false;
            String Datos[]=new String[23];
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
                Datos[16]=(String) rs.getString(17);
                Datos[17]=(String) rs.getString(18);
                Datos[18]=(String) rs.getString(19);
                Datos[19]=(String) rs.getString(20);
                Datos[20]=(String) rs.getString(21);
                Datos[21]=(String) rs.getString(22);
                Datos[22]=(String) rs.getString(23);

                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblTipoUsuario.setModel(dtm);
    }
    void listardatos(){
        String estado;
        DefaultTableModel defaultTableModel=new DefaultTableModel();
        if(registros==-1){
            JOptionPane.showMessageDialog(null,"Se debe seleccionar un registro");
        }else{
            defaultTableModel=(DefaultTableModel) tblTipoUsuario.getModel();
            strCodigo=((String) defaultTableModel.getValueAt(registros,0));
            txtDescripcion.setText((String)defaultTableModel.getValueAt(registros,1));
            if ("1".equals((String) defaultTableModel.getValueAt(registros,2))){
                chkRVenta.setSelected(true);
            }else{
                chkRVenta.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,3))){
                chkRCompra.setSelected(true);
            }else{
                chkRCompra.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(registros,4))){
                chkRProducto.setSelected(true);
            }else{
                chkRProducto.setSelected(false);
            }             
            if ("1".equals((String) defaultTableModel.getValueAt(registros,5))){
                chkRProveedor.setSelected(true);
            }else{
                chkRProveedor.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(registros,6))){
                chkREmpleado.setSelected(true);
            }else{
                chkREmpleado.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(registros,7))){
                chkRCliente.setSelected(true);
            }else{
                chkRCliente.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,8))){
                chkRCategoria.setSelected(true);
            }else{
                chkRCategoria.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,9))){
                chkRTipodoc.setSelected(true);
            }else{
                chkRTipodoc.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,10))){
                chkRTipouser.setSelected(true);
            }else{
                chkRTipouser.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,11))){
                chkAnularv.setSelected(true);
            }else{
                chkAnularv.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,12))){
                chkAnularc.setSelected(true);
            }else{
                chkAnularc.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,13))){
                chkEstado.setSelected(true);
            }else{
                chkEstado.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,14))){
                chkVentare.setSelected(true);
            }else{
                chkVentare.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,15))){
                chkVentade.setSelected(true);
            }else{
                chkVentade.setSelected(false);
            }             
            if ("1".equals((String) defaultTableModel.getValueAt(registros,16))){
                chkEstadistica.setSelected(true);
            }else{
                chkEstadistica.setSelected(false);
            }            
            if ("1".equals((String) defaultTableModel.getValueAt(registros,17))){
                chkComprare.setSelected(true);
            }else{
                chkComprare.setSelected(false);
            }         
            if ("1".equals((String) defaultTableModel.getValueAt(registros,18))){
                chkComprade.setSelected(true);
            }else{
                chkComprade.setSelected(false);
            }            
            if ("1".equals((String) defaultTableModel.getValueAt(registros,19))){
                chkPass.setSelected(true);
            }else{
                chkPass.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,20))){
                chkRespaldar.setSelected(true);
            }else{
                chkRespaldar.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(registros,21))){
                chkRestaurar.setSelected(true);
            }else{
                chkRestaurar.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(registros,22))){
                chkCaja.setSelected(true);
            }else{
                chkCaja.setSelected(false);
            }              
  
            tblTipoUsuario.setRowSelectionInterval(registros,registros);
        }
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pBuscar = new javax.swing.JPanel();
        lblEstado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipoUsuario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        rbtnDescripcion = new javax.swing.JRadioButton();
        txtBusqueda = new javax.swing.JTextField();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        pNuevo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        chkEstadistica = new javax.swing.JCheckBox();
        chkComprare = new javax.swing.JCheckBox();
        chkComprade = new javax.swing.JCheckBox();
        chkPass = new javax.swing.JCheckBox();
        chkRespaldar = new javax.swing.JCheckBox();
        chkRestaurar = new javax.swing.JCheckBox();
        chkRTipodoc = new javax.swing.JCheckBox();
        chkRTipouser = new javax.swing.JCheckBox();
        chkAnularv = new javax.swing.JCheckBox();
        chkAnularc = new javax.swing.JCheckBox();
        chkEstado = new javax.swing.JCheckBox();
        chkVentare = new javax.swing.JCheckBox();
        chkVentade = new javax.swing.JCheckBox();
        chkRVenta = new javax.swing.JCheckBox();
        chkRCompra = new javax.swing.JCheckBox();
        chkRProducto = new javax.swing.JCheckBox();
        chkRProveedor = new javax.swing.JCheckBox();
        chkREmpleado = new javax.swing.JCheckBox();
        chkRCliente = new javax.swing.JCheckBox();
        chkRCategoria = new javax.swing.JCheckBox();
        chkCaja = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Registro del Tipo de Usuario");
        getContentPane().setLayout(null);

        pBuscar.setBackground(new java.awt.Color(255, 255, 255));
        pBuscar.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion Del Usuario"));

        tblTipoUsuario.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTipoUsuario.setRowHeight(22);
        tblTipoUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTipoUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTipoUsuario);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Metodo de Busqueda"));

        rbtnDescripcion.setText("Descripción");
        rbtnDescripcion.setOpaque(false);
        rbtnDescripcion.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rbtnDescripcionStateChanged(evt);
            }
        });

        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbtnDescripcion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnDescripcion)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        javax.swing.GroupLayout pBuscarLayout = new javax.swing.GroupLayout(pBuscar);
        pBuscar.setLayout(pBuscarLayout);
        pBuscarLayout.setHorizontalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addGroup(pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pBuscarLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pBuscarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pBuscarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        pBuscarLayout.setVerticalGroup(
            pBuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        getContentPane().add(pBuscar);
        pBuscar.setBounds(0, 10, 570, 430);

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
        btnModificar.setBounds(810, 360, 81, 70);

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
        btnCancelar.setBounds(890, 360, 81, 70);

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
        btnNuevo.setBounds(650, 360, 81, 70);

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
        btnSalir.setBounds(970, 360, 81, 70);

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
        btnGuardar.setBounds(730, 360, 81, 70);

        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Mantenimiento"));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(570, 340, 570, 100);

        pNuevo.setBackground(new java.awt.Color(255, 255, 255));
        pNuevo.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de Usuario"));

        jLabel2.setText("Descripción:");

        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Roles del Empleado [Operaciones que puede realizar el empleado en el sistema]", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        chkEstadistica.setText("Info. Estadística");
        chkEstadistica.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadisticaStateChanged(evt);
            }
        });
        jPanel1.add(chkEstadistica, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, -1, -1));

        chkComprare.setText("Info. Compra realizada");
        chkComprare.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkComprareStateChanged(evt);
            }
        });
        jPanel1.add(chkComprare, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        chkComprade.setText("Info. Compra detallada");
        chkComprade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCompradeStateChanged(evt);
            }
        });
        jPanel1.add(chkComprade, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, -1, -1));

        chkPass.setText("Cambiar Contraseña");
        chkPass.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkPassStateChanged(evt);
            }
        });
        jPanel1.add(chkPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, -1));

        chkRespaldar.setText("Respaldar DB");
        chkRespaldar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRespaldarStateChanged(evt);
            }
        });
        jPanel1.add(chkRespaldar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, -1, -1));

        chkRestaurar.setText("Restaurar DB");
        chkRestaurar.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRestaurarStateChanged(evt);
            }
        });
        jPanel1.add(chkRestaurar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, -1, -1));

        chkRTipodoc.setText("Reg. Tipo de Documento");
        chkRTipodoc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRTipodocStateChanged(evt);
            }
        });
        jPanel1.add(chkRTipodoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, -1, -1));

        chkRTipouser.setText("Reg. Tipo de Usuario");
        chkRTipouser.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRTipouserStateChanged(evt);
            }
        });
        jPanel1.add(chkRTipouser, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));

        chkAnularv.setText("Anular Venta");
        chkAnularv.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAnularvStateChanged(evt);
            }
        });
        jPanel1.add(chkAnularv, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, -1, -1));

        chkAnularc.setText("Anular Compra");
        chkAnularc.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkAnularcStateChanged(evt);
            }
        });
        jPanel1.add(chkAnularc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, -1, -1));

        chkEstado.setText("Info. Estado");
        chkEstado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkEstadoStateChanged(evt);
            }
        });
        jPanel1.add(chkEstado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        chkVentare.setText("Info. Venta realizada");
        chkVentare.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentareStateChanged(evt);
            }
        });
        jPanel1.add(chkVentare, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        chkVentade.setText("Info. Venta detallada");
        chkVentade.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkVentadeStateChanged(evt);
            }
        });
        jPanel1.add(chkVentade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        chkRVenta.setText("Reg. Venta");
        chkRVenta.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRVentaStateChanged(evt);
            }
        });
        jPanel1.add(chkRVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        chkRCompra.setText("Reg. Compra");
        chkRCompra.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRCompraStateChanged(evt);
            }
        });
        jPanel1.add(chkRCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        chkRProducto.setText("Reg. Producto");
        chkRProducto.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRProductoStateChanged(evt);
            }
        });
        jPanel1.add(chkRProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        chkRProveedor.setText("Reg. Proveedor");
        chkRProveedor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRProveedorStateChanged(evt);
            }
        });
        jPanel1.add(chkRProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        chkREmpleado.setText("Reg. Empleado");
        chkREmpleado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkREmpleadoStateChanged(evt);
            }
        });
        jPanel1.add(chkREmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, -1));

        chkRCliente.setText("Reg. Cliente");
        chkRCliente.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRClienteStateChanged(evt);
            }
        });
        jPanel1.add(chkRCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        chkRCategoria.setText("Reg. Categoria");
        chkRCategoria.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkRCategoriaStateChanged(evt);
            }
        });
        jPanel1.add(chkRCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        chkCaja.setText("Info. Caja");
        chkCaja.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCajaStateChanged(evt);
            }
        });
        jPanel1.add(chkCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, -1, -1));

        javax.swing.GroupLayout pNuevoLayout = new javax.swing.GroupLayout(pNuevo);
        pNuevo.setLayout(pNuevoLayout);
        pNuevoLayout.setHorizontalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pNuevoLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        pNuevoLayout.setVerticalGroup(
            pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pNuevoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pNuevoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(pNuevo);
        pNuevo.setBounds(570, 10, 570, 330);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblTipoUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTipoUsuarioMouseClicked
        int fila;
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        fila = tblTipoUsuario.getSelectedRow();

        if (fila == -1){
            JOptionPane.showMessageDialog(null, "Se debe seleccionar un registro");
        }else{
            defaultTableModel = (DefaultTableModel)tblTipoUsuario.getModel();
            strCodigo =  ((String) defaultTableModel.getValueAt(fila, 0));
            txtDescripcion.setText((String) defaultTableModel.getValueAt(fila, 1));
            if ("1".equals((String) defaultTableModel.getValueAt(fila,2))){
                chkRVenta.setSelected(true);
            }else{
                chkRVenta.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,3))){
                chkRCompra.setSelected(true);
            }else{
                chkRCompra.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(fila,4))){
                chkRProducto.setSelected(true);
            }else{
                chkRProducto.setSelected(false);
            }             
            if ("1".equals((String) defaultTableModel.getValueAt(fila,5))){
                chkRProveedor.setSelected(true);
            }else{
                chkRProveedor.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(fila,6))){
                chkREmpleado.setSelected(true);
            }else{
                chkREmpleado.setSelected(false);
            }  
            if ("1".equals((String) defaultTableModel.getValueAt(fila,7))){
                chkRCliente.setSelected(true);
            }else{
                chkRCliente.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,8))){
                chkRCategoria.setSelected(true);
            }else{
                chkRCategoria.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,9))){
                chkRTipodoc.setSelected(true);
            }else{
                chkRTipodoc.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,10))){
                chkRTipouser.setSelected(true);
            }else{
                chkRTipouser.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,11))){
                chkAnularv.setSelected(true);
            }else{
                chkAnularv.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,12))){
                chkAnularc.setSelected(true);
            }else{
                chkAnularc.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,13))){
                chkEstado.setSelected(true);
            }else{
                chkEstado.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,14))){
                chkVentare.setSelected(true);
            }else{
                chkVentare.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,15))){
                chkVentade.setSelected(true);
            }else{
                chkVentade.setSelected(false);
            }             
            if ("1".equals((String) defaultTableModel.getValueAt(fila,16))){
                chkEstadistica.setSelected(true);
            }else{
                chkEstadistica.setSelected(false);
            }            
            if ("1".equals((String) defaultTableModel.getValueAt(fila,17))){
                chkComprare.setSelected(true);
            }else{
                chkComprare.setSelected(false);
            }         
            if ("1".equals((String) defaultTableModel.getValueAt(fila,18))){
                chkComprade.setSelected(true);
            }else{
                chkComprade.setSelected(false);
            }            
            if ("1".equals((String) defaultTableModel.getValueAt(fila,19))){
                chkPass.setSelected(true);
            }else{
                chkPass.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,20))){
                chkRespaldar.setSelected(true);
            }else{
                chkRespaldar.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,21))){
                chkRestaurar.setSelected(true);
            }else{
                chkRestaurar.setSelected(false);
            }
            if ("1".equals((String) defaultTableModel.getValueAt(fila,22))){
                chkCaja.setSelected(true);
            }else{
                chkCaja.setSelected(false);
            }                 
        }

        mirar();
    }//GEN-LAST:event_tblTipoUsuarioMouseClicked

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        char car = evt.getKeyChar();
        if((car<'a' || car>'z') && (car<'A' || car>'Z')) evt.consume();
        txtDescripcion.setBackground(Color.WHITE);
    }//GEN-LAST:event_txtDescripcionKeyTyped

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
    if(tblTipoUsuario.getSelectedRows().length > 0 ) { 
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
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
//----------------------VALIDACIÓN DE DATOS-------------------------------------
    public boolean validardatos(){
        if (txtDescripcion.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Ingrese una descripción");
            txtDescripcion.requestFocus();
            txtDescripcion.setBackground(Color.YELLOW);
            return false;
        }else{
            return true;
        }

    }
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
    if(validardatos()==true){   
        if(accion.equals("Nuevo")){
            ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
            ClsEntidadTipoUsuario tipousuario=new ClsEntidadTipoUsuario();
            tipousuario.setStrDescripcionTipoUsuario(txtDescripcion.getText());
            tipousuario.setStrP_Venta(p_venta);
            tipousuario.setStrP_Compra(p_compra);
            tipousuario.setStrP_Producto(p_producto);
            tipousuario.setStrP_Proveedor(p_proveedor);
            tipousuario.setStrP_Empleado(p_empleado);
            tipousuario.setStrP_Cliente(p_cliente);
            tipousuario.setStrP_Categoria(p_categoria);
            tipousuario.setStrP_Tipodoc(p_tipodoc);
            tipousuario.setStrP_Tipouser(p_tipouser);
            tipousuario.setStrP_Anularv(p_anularv);
            tipousuario.setStrP_Anularc(p_anularc);
            tipousuario.setStrP_Estadoprod(p_estadoprod);
            tipousuario.setStrP_Ventare(p_ventare);
            tipousuario.setStrP_Ventade(p_ventade);
            tipousuario.setStrP_Estadistica(p_estadistica);
            tipousuario.setStrP_Comprare(p_comprare);
            tipousuario.setStrP_Comprade(p_comprade);
            tipousuario.setStrP_Pass(p_pass);
            tipousuario.setStrP_Respaldar(p_respaldar);
            tipousuario.setStrP_Restaurar(p_restaurar);
            tipousuario.setStrP_Caja(p_caja);
            tipousuarios.agregarTipoUsuario(tipousuario);
            actualizarTabla();
            CantidadTotal();
        }
        if(accion.equals("Modificar")){
            ClsTipoUsuario tipousuarios=new ClsTipoUsuario();
            ClsEntidadTipoUsuario tipousuario=new ClsEntidadTipoUsuario();
            tipousuario.setStrDescripcionTipoUsuario(txtDescripcion.getText());
            tipousuario.setStrP_Venta(p_venta);
            tipousuario.setStrP_Compra(p_compra);
            tipousuario.setStrP_Producto(p_producto);
            tipousuario.setStrP_Proveedor(p_proveedor);
            tipousuario.setStrP_Empleado(p_empleado);
            tipousuario.setStrP_Cliente(p_cliente);
            tipousuario.setStrP_Categoria(p_categoria);
            tipousuario.setStrP_Tipodoc(p_tipodoc);
            tipousuario.setStrP_Tipouser(p_tipouser);
            tipousuario.setStrP_Anularv(p_anularv);
            tipousuario.setStrP_Anularc(p_anularc);
            tipousuario.setStrP_Estadoprod(p_estadoprod);
            tipousuario.setStrP_Ventare(p_ventare);
            tipousuario.setStrP_Ventade(p_ventade);
            tipousuario.setStrP_Estadistica(p_estadistica);
            tipousuario.setStrP_Comprare(p_comprare);
            tipousuario.setStrP_Comprade(p_comprade);
            tipousuario.setStrP_Pass(p_pass);
            tipousuario.setStrP_Respaldar(p_respaldar);
            tipousuario.setStrP_Restaurar(p_restaurar);
            tipousuario.setStrP_Caja(p_caja);
            tipousuarios.modificarTipoUsuario(strCodigo, tipousuario);
            actualizarTabla();
            limpiarCampos();
            modificar();
            CantidadTotal();
        }
        CrearTabla();
        mirar();
    }    
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        BuscarTipoUsuario();
        CrearTabla();
        CantidadTotal();
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void rbtnDescripcionStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rbtnDescripcionStateChanged
        txtBusqueda.setText("");
    }//GEN-LAST:event_rbtnDescripcionStateChanged

    private void txtDescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyReleased
        String cadena= (txtDescripcion.getText()).toUpperCase();
        txtDescripcion.setText(cadena);
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnGuardar.requestFocus();
    }//GEN-LAST:event_txtDescripcionKeyReleased

    private void chkRVentaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRVentaStateChanged
        if (chkRVenta.isSelected()){
            p_venta="1";
        }else{
            p_venta="0";
        }
    }//GEN-LAST:event_chkRVentaStateChanged

    private void chkRCompraStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRCompraStateChanged
        if (chkRCompra.isSelected()){
            p_compra="1";
        }else{
            p_compra="0";
        }
    }//GEN-LAST:event_chkRCompraStateChanged

    private void chkRProductoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRProductoStateChanged
        if (chkRProducto.isSelected()){
            p_producto="1";
        }else{
            p_producto="0";
        }
    }//GEN-LAST:event_chkRProductoStateChanged

    private void chkRProveedorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRProveedorStateChanged
        if (chkRProveedor.isSelected()){
            p_proveedor="1";
        }else{
            p_proveedor="0";
        }
    }//GEN-LAST:event_chkRProveedorStateChanged

    private void chkREmpleadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkREmpleadoStateChanged
        if (chkREmpleado.isSelected()){
            p_empleado="1";
        }else{
            p_empleado="0";
        }
    }//GEN-LAST:event_chkREmpleadoStateChanged

    private void chkRClienteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRClienteStateChanged
        if (chkRCliente.isSelected()){
            p_cliente="1";
        }else{
            p_cliente="0";
        }
    }//GEN-LAST:event_chkRClienteStateChanged

    private void chkRCategoriaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRCategoriaStateChanged
        if (chkRCategoria.isSelected()){
            p_categoria="1";
        }else{
            p_categoria="0";
        }
    }//GEN-LAST:event_chkRCategoriaStateChanged

    private void chkRTipodocStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRTipodocStateChanged
        if (chkRTipodoc.isSelected()){
            p_tipodoc="1";
        }else{
            p_tipodoc="0";
        }
    }//GEN-LAST:event_chkRTipodocStateChanged

    private void chkRTipouserStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRTipouserStateChanged
        if (chkRTipouser.isSelected()){
            p_tipouser="1";
        }else{
            p_tipouser="0";
        }
    }//GEN-LAST:event_chkRTipouserStateChanged

    private void chkAnularvStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAnularvStateChanged
        if (chkAnularv.isSelected()){
            p_anularv="1";
        }else{
            p_anularv="0";
        }
    }//GEN-LAST:event_chkAnularvStateChanged

    private void chkAnularcStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkAnularcStateChanged
        if (chkAnularc.isSelected()){
            p_anularc="1";
        }else{
            p_anularc="0";
        }
    }//GEN-LAST:event_chkAnularcStateChanged

    private void chkEstadoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadoStateChanged
        if (chkEstado.isSelected()){
            p_estadoprod="1";
        }else{
            p_estadoprod="0";
        }
    }//GEN-LAST:event_chkEstadoStateChanged

    private void chkVentareStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVentareStateChanged
        if (chkVentare.isSelected()){
            p_ventare="1";
        }else{
            p_ventare="0";
        }
    }//GEN-LAST:event_chkVentareStateChanged

    private void chkVentadeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkVentadeStateChanged
        if (chkVentade.isSelected()){
            p_ventade="1";
        }else{
            p_ventade="0";
        }
    }//GEN-LAST:event_chkVentadeStateChanged

    private void chkEstadisticaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkEstadisticaStateChanged
        if (chkEstadistica.isSelected()){
            p_estadistica="1";
        }else{
            p_estadistica="0";
        }
    }//GEN-LAST:event_chkEstadisticaStateChanged

    private void chkComprareStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkComprareStateChanged
        if (chkComprare.isSelected()){
            p_comprare="1";
        }else{
            p_comprare="0";
        }
    }//GEN-LAST:event_chkComprareStateChanged

    private void chkCompradeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCompradeStateChanged
        if (chkComprade.isSelected()){
            p_comprade="1";
        }else{
            p_comprade="0";
        }
    }//GEN-LAST:event_chkCompradeStateChanged

    private void chkPassStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkPassStateChanged
        if (chkPass.isSelected()){
            p_pass="1";
        }else{
            p_pass="0";
        }
    }//GEN-LAST:event_chkPassStateChanged

    private void chkRespaldarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRespaldarStateChanged
        if (chkRespaldar.isSelected()){
            p_respaldar="1";
        }else{
            p_respaldar="0";
        }
    }//GEN-LAST:event_chkRespaldarStateChanged

    private void chkRestaurarStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkRestaurarStateChanged
        if (chkRestaurar.isSelected()){
            p_restaurar="1";
        }else{
            p_restaurar="0";
        }
    }//GEN-LAST:event_chkRestaurarStateChanged

    private void chkCajaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCajaStateChanged
        if (chkCaja.isSelected()){
            p_caja="1";
        }else{
            p_caja="0";
        }
    }//GEN-LAST:event_chkCajaStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBox chkAnularc;
    private javax.swing.JCheckBox chkAnularv;
    private javax.swing.JCheckBox chkCaja;
    private javax.swing.JCheckBox chkComprade;
    private javax.swing.JCheckBox chkComprare;
    private javax.swing.JCheckBox chkEstadistica;
    private javax.swing.JCheckBox chkEstado;
    private javax.swing.JCheckBox chkPass;
    private javax.swing.JCheckBox chkRCategoria;
    private javax.swing.JCheckBox chkRCliente;
    private javax.swing.JCheckBox chkRCompra;
    private javax.swing.JCheckBox chkREmpleado;
    private javax.swing.JCheckBox chkRProducto;
    private javax.swing.JCheckBox chkRProveedor;
    private javax.swing.JCheckBox chkRTipodoc;
    private javax.swing.JCheckBox chkRTipouser;
    private javax.swing.JCheckBox chkRVenta;
    private javax.swing.JCheckBox chkRespaldar;
    private javax.swing.JCheckBox chkRestaurar;
    private javax.swing.JCheckBox chkVentade;
    private javax.swing.JCheckBox chkVentare;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JPanel pBuscar;
    private javax.swing.JPanel pNuevo;
    private javax.swing.JRadioButton rbtnDescripcion;
    private javax.swing.JTable tblTipoUsuario;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtDescripcion;
    // End of variables declaration//GEN-END:variables
}
