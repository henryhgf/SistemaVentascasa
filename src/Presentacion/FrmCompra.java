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
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class FrmCompra extends javax.swing.JInternalFrame {

String Total;
    String strCodigo;
    String accion;
    String numCompra;
    int registros;
    String id[]=new String[50];
   

    static int intContador;
    public String IdEmpleado,NombreEmpleado;
    int idventa,nidventa;
    //-----------------------------------------------
    public String codigo;
    static Connection conn=null;
    
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    DefaultTableModel dtmDetalle = new DefaultTableModel();

    String criterio,busqueda;
    
    public FrmCompra() {
        initComponents();
 
        Date date=new Date();
        String format=new String("dd/MM/yyyy");
        SimpleDateFormat formato=new SimpleDateFormat(format);

        txtFecha.setDate(date);
      
        numCompra=generaNumCompra();
        txtNumero.setText(numCompra);
       
        this.setSize(970, 620);
        cargarComboTipoDocumento();
       
        lblIdProducto.setVisible(false);
        lblIdProveedor.setVisible(false);
        txtDescripcionProducto.setVisible(false);
        mirar();
     
        
        String titulos[] = {"ID","CODIGO", "PRODUCTO","DESCRIPCION", "CANT.", "PRECIO", "TOTAL"};
        dtmDetalle.setColumnIdentifiers(titulos);
        tblDetalleProducto.setModel(dtmDetalle);
        CrearTablaDetalleProducto();
    }


  public String generaNumCompra(){

        ClsCompra oCompra=new ClsCompra(); 
        try {

            rs= oCompra.obtenerUltimoIdCompra();
            while (rs.next()) {
            if (rs.getString(1) != null) {
                Scanner s = new Scanner(rs.getString(1));
                int c = s.useDelimiter("C").nextInt() + 1;

                if (c < 10) {
                    return "C0000" + c;
                }
                if (c < 100) {
                    return "C000" + c;
                }
                if (c < 1000) {
                    return "C00" + c;
                }
                if (c < 10000) {
                    return "C0" + c;
                } else {
                    return "C" + c;
                }
            }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return "C00001";
              
  }
  
   void CrearTablaDetalleProducto(){
 
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
              
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
              
                    if(column==0 || column==1 || column==4 || column==5 || column==6){
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
        
      
        for (int i=0;i<tblDetalleProducto.getColumnCount();i++){
            tblDetalleProducto.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
        
        tblDetalleProducto.setAutoResizeMode(tblDetalleProducto.AUTO_RESIZE_OFF);

        int[] anchos = {50,100,180,247,70,70,70};
        for(int i = 0; i < tblDetalleProducto.getColumnCount(); i++) {
            tblDetalleProducto.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
   }

   void limpiarCampos(){

       txtSubTotal.setText("0.0");
       txtIGV.setText("0.0");
       txtTotalCompra.setText("0.0");
       
       lblIdProducto.setText("");
       txtCodigoProducto.setText("");
       txtNombreProducto.setText("");
       txtStockProducto.setText("");
       txtPrecioProducto.setText("");
       txtCantidadProducto.setText("");
       txtTotalProducto.setText("");
       txtCodigoProducto.requestFocus();
   }
       
   void mirar(){
       btnNuevo.setEnabled(true);
       btnGuardar.setEnabled(false);
       btnCancelar.setEnabled(false);
       btnSalir.setEnabled(true);
        

       cboTipoDocumento.setEnabled(false);
       txtCodigoProducto.setEnabled(false);
       txtCantidadProducto.setEnabled(false);
       txtFecha.setEnabled(false);
       txtNumero.setEnabled(false);
       
       btnBuscarProveedor.setEnabled(false);
       btnBuscarProducto.setEnabled(false);
       btnAgregarProducto.setEnabled(false);
       btnEliminarProducto.setEnabled(false);
       btnLimpiarTabla.setEnabled(false);
       chkCambiarNumero.setEnabled(false);
       chkCambiarNumero.setSelected(false);
       
       txtSubTotal.setText("0.0");
       txtIGV.setText("0.0");
       txtTotalCompra.setText("0.0");
       lblIdProducto.setText("");
       txtCodigoProducto.setText("");
       txtNombreProducto.setText("");
       txtStockProducto.setText("");
       txtPrecioProducto.setText("");
       txtCantidadProducto.setText("");
       txtTotalProducto.setText("");
       txtCodigoProducto.requestFocus();
       

   }
   
   void modificar(){

       btnNuevo.setEnabled(false);

       btnGuardar.setEnabled(true);
       btnCancelar.setEnabled(true);
       btnSalir.setEnabled(false);
        
       cboTipoDocumento.setEnabled(true);
       txtCodigoProducto.setEnabled(true);
       txtCantidadProducto.setEnabled(true);
       txtFecha.setEnabled(true);
       
       btnBuscarProveedor.setEnabled(true);
       btnBuscarProducto.setEnabled(true);
       btnAgregarProducto.setEnabled(true);
       btnEliminarProducto.setEnabled(true);
       btnLimpiarTabla.setEnabled(true);
       chkCambiarNumero.setEnabled(true);
       
       txtCodigoProducto.requestFocus();
   }

    void cargarComboTipoDocumento(){
       ClsTipoDocumento tipodocumento=new ClsTipoDocumento();
       ArrayList<ClsEntidadTipoDocumento> tipodocumentos=tipodocumento.listarTipoDocumento();
       Iterator iterator=tipodocumentos.iterator();
       DefaultComboBoxModel DefaultComboBoxModel=new DefaultComboBoxModel();
       DefaultComboBoxModel.removeAllElements();
       
       cboTipoDocumento.removeAll();
       String fila[]=new String[2];
       intContador=0;
       
       while(iterator.hasNext()){
           ClsEntidadTipoDocumento TipoDocumento = new ClsEntidadTipoDocumento();
           TipoDocumento=(ClsEntidadTipoDocumento) iterator.next();
           id[intContador]=TipoDocumento.getStrIdTipoDocumento();
           fila[0]=TipoDocumento.getStrIdTipoDocumento();
           fila[1]=TipoDocumento.getStrDescripcionTipoDocumento();
           DefaultComboBoxModel.addElement(TipoDocumento.getStrDescripcionTipoDocumento());
           intContador++;              
       }
       cboTipoDocumento.setModel(DefaultComboBoxModel);
    }
    void BuscarProductoPorCodigo(){
        String busqueda=null;
        busqueda=txtCodigoProducto.getText(); 
            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.listarProductoActivoPorParametro("codigo",busqueda);
                while (rs.next()) {
                    if(rs.getString(2).equals(busqueda)) {
        
                            lblIdProducto.setText(rs.getString(1));
                            txtNombreProducto.setText(rs.getString(3));
                            txtDescripcionProducto.setText(rs.getString(4));
                     
                            txtStockProducto.setText(rs.getString(5));
                            txtPrecioProducto.setText(rs.getString(7));             
                    }
                   break;
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }
    
    }
void BuscarProveedorPorDefecto(){
        try{
            ClsProveedor oProveedor=new ClsProveedor();
            rs= oProveedor.listarProveedorPorParametro("id","1");
            while (rs.next()) {
                lblIdProveedor.setText(rs.getString(1));
                txtNombreProveedor.setText(rs.getString(2));             
            break;
            }

         }catch(Exception ex){
            JOptionPane.showMessageDialog(this,ex.getMessage());
            System.out.println(ex.getMessage());
         }
    
    }
    
void CalcularTotal(){
            
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);
        double precio_prod=0,cant_prod=0,total_prod=0;
        precio_prod=Double.parseDouble(txtPrecioProducto.getText());
        cant_prod=Double.parseDouble(txtCantidadProducto.getText());
        total_prod=precio_prod*cant_prod;
        txtTotalProducto.setText(String.valueOf(formateador.format(total_prod)));
}




    

    public int recorrer(int id){
        int fila = 0,valor=-1;
        
        fila = tblDetalleProducto.getRowCount();
        
        for (int f = 0; f < fila;f++) {
            if(Integer.parseInt(String.valueOf(dtmDetalle.getValueAt(f, 0)))==id){

                valor=f;
              
                break;
                
                
            }else{
               
                valor= -1;
            }          
              
        }
        return valor;
    } 

    void agregardatos(int item, String cod, String nom,String descrip,double cant,double pre,double tot){
        
        int p=recorrer(item);
        double n_cant,n_total;
        if (p>-1){
                               
            n_cant=Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,4)))+cant;
            tblDetalleProducto.setValueAt(n_cant,p,4);
                       
            n_total=Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,4)))*Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(p,5)));
            tblDetalleProducto.setValueAt(n_total,p,6);
            
                            
        }else{
            String Datos[] = {String.valueOf(item),cod,nom,descrip,String.valueOf(cant),String.valueOf(pre),String.valueOf(tot)};
            dtmDetalle.addRow(Datos);
        }
        tblDetalleProducto.setModel(dtmDetalle);
    }
    void CalcularSubTotal(){
        double subtotal=0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);        
        subtotal=Double.parseDouble(txtTotalCompra.getText())/1.18;
        txtSubTotal.setText(String.valueOf(formateador.format(subtotal)));
    }
    void CalcularIGV(){
        double igv=0;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("####.##",simbolos);       
        igv=Double.parseDouble(txtSubTotal.getText())*0.18;
        txtIGV.setText(String.valueOf(formateador.format(igv)));
    }
    void CalcularTotal_Compra(){
        int fila = 0;
        double valorCompra = 0;
        fila = dtmDetalle.getRowCount();
        for (int f=0; f<fila; f++){
            valorCompra += Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 6)));            
        }
        txtTotalCompra.setText(String.valueOf(valorCompra));
    }
    void limpiarTabla(){
        try{      
	int filas=tblDetalleProducto.getRowCount();
            for (int i = 0;filas>i; i++) {
                dtmDetalle.removeRow(0);
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }
    }
    void obtenerUltimoIdCompra(){
        try{
        ClsCompra oCompra=new ClsCompra(); 
        rs= oCompra.obtenerUltimoIdCompra();
            while (rs.next()) {
                idventa = rs.getInt(1);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    void guardarDetalle(){
        obtenerUltimoIdCompra();
        ClsDetalleCompra detallecompras=new ClsDetalleCompra();
        ClsEntidadDetalleCompra detallecompra=new ClsEntidadDetalleCompra();
        ClsProducto productos=new ClsProducto();
        String codigo,strId;
        ClsEntidadProducto producto=new ClsEntidadProducto();
        int fila=0;
        double cant = 0,ncant,stock;   
        fila =tblDetalleProducto.getRowCount();
        for (int f=0; f<fila; f++){
            detallecompra.setStrIdCompra(String.valueOf(idventa));
            detallecompra.setStrIdProducto(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 0)));
            detallecompra.setStrCantidadDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 4)));
            detallecompra.setStrPrecioDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 5)));
            detallecompra.setStrTotalDet(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 6)));  
            detallecompras.agregarDetalleCompra(detallecompra);
            


            try{
                ClsProducto oProducto=new ClsProducto();
                
                rs= oProducto.listarProductoActivoPorParametro("id",((String) tblDetalleProducto.getValueAt(f, 0)));
                while (rs.next()) {

                            cant=Double.parseDouble(rs.getString(5));
                }
                

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }                   
            
        strId =  ((String) tblDetalleProducto.getValueAt(f, 0));
        ncant=Double.parseDouble(String.valueOf(tblDetalleProducto.getModel().getValueAt(f, 4)));
        stock=cant+ncant;
        producto.setStrStockProducto(String.valueOf(stock));
        productos.actualizarProductoStock(strId, producto);

    }
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleProducto = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtCodigoProducto = new javax.swing.JTextField();
        btnBuscarProducto = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDescripcionProducto = new javax.swing.JLabel();
        txtStockProducto = new javax.swing.JTextField();
        txtPrecioProducto = new javax.swing.JTextField();
        lblIdProducto = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        btnBuscarProveedor = new javax.swing.JButton();
        lblIdProveedor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        cboTipoDocumento = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        txtFecha = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        chkCambiarNumero = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtTotalProducto = new javax.swing.JTextField();
        btnAgregarProducto = new javax.swing.JButton();
        btnEliminarProducto = new javax.swing.JButton();
        btnLimpiarTabla = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtSubTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtIGV = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTotalCompra = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Compras");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        tblDetalleProducto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblDetalleProducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDetalleProducto.setRowHeight(22);
        jScrollPane3.setViewportView(tblDetalleProducto);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(0, 370, 770, 190);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Formulario de Compra"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/foto.png"))); // NOI18N
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 200, 160));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Producto"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("CÓDIGO:");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, -1));

        txtCodigoProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoProductoKeyTyped(evt);
            }
        });
        jPanel2.add(txtCodigoProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 190, -1));

        btnBuscarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar24.png"))); // NOI18N
        btnBuscarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProductoActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 40, 35));

        jLabel17.setText("NOMBRE:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, 20));

        txtNombreProducto.setEnabled(false);
        jPanel2.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 240, -1));

        jLabel19.setText("STOCK:");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, -1, -1));
        jPanel2.add(txtDescripcionProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 30, 20));

        txtStockProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtStockProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStockProducto.setEnabled(false);
        jPanel2.add(txtStockProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 90, -1));

        txtPrecioProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecioProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioProducto.setEnabled(false);
        jPanel2.add(txtPrecioProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 90, -1));
        jPanel2.add(lblIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 20, 20));

        jLabel23.setText("COSTO:");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 50, -1));

        jLabel1.setText("PROVEEDOR:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 80, 20));

        txtNombreProveedor.setEnabled(false);
        jPanel2.add(txtNombreProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 190, -1));

        btnBuscarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar24.png"))); // NOI18N
        btnBuscarProveedor.setAlignmentY(1.0F);
        btnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProveedorActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 40, -1));
        jPanel2.add(lblIdProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 20, 29));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 550, 170));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Documento"));

        cboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("DOCUMENTO:");

        jLabel2.setText("FECHA:");

        txtNumero.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Nº DE COMPRA");

        chkCambiarNumero.setText("Cambiar Número");
        chkCambiarNumero.setOpaque(false);
        chkCambiarNumero.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkCambiarNumeroStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkCambiarNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkCambiarNumero))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 500, 150));

        jLabel21.setText("CANTIDAD:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 190, 70, 30));

        txtCantidadProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidadProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidadProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadProductoKeyTyped(evt);
            }
        });
        jPanel5.add(txtCantidadProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 190, 80, 30));

        jLabel24.setText("TOTAL:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 50, 30));

        txtTotalProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtTotalProducto.setForeground(new java.awt.Color(255, 0, 0));
        txtTotalProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalProducto.setDisabledTextColor(new java.awt.Color(0, 102, 204));
        txtTotalProducto.setEnabled(false);
        jPanel5.add(txtTotalProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 240, 80, 30));

        btnAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Agregar_p1.png"))); // NOI18N
        btnAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btnAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 50, 40));

        btnEliminarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        btnEliminarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProductoActionPerformed(evt);
            }
        });
        jPanel5.add(btnEliminarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 50, 40));

        btnLimpiarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/nuevo1.png"))); // NOI18N
        btnLimpiarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTablaActionPerformed(evt);
            }
        });
        jPanel5.add(btnLimpiarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 50, 40));

        getContentPane().add(jPanel5);
        jPanel5.setBounds(0, 10, 770, 360);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("SUB TOTAL");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 20));

        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setEnabled(false);
        jPanel6.add(txtSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 100, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("I.G.V.");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 100, 20));

        txtIGV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtIGV.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtIGV.setEnabled(false);
        jPanel6.add(txtIGV, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 100, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("TOTAL A PAGAR");
        jLabel12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel6.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 150, 20));

        txtTotalCompra.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTotalCompra.setForeground(new java.awt.Color(255, 0, 0));
        txtTotalCompra.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotalCompra.setDisabledTextColor(new java.awt.Color(255, 0, 0));
        txtTotalCompra.setEnabled(false);
        jPanel6.add(txtTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 100, 30));

        getContentPane().add(jPanel6);
        jPanel6.setBounds(790, 360, 150, 200);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(790, 15, 150, 335);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        accion="Nuevo";
        modificar();
        limpiarCampos();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        mirar();
        limpiarTabla();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtCantidadProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyReleased
        CalcularTotal();
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) btnAgregarProducto.requestFocus(); 
    }//GEN-LAST:event_txtCantidadProductoKeyReleased

    private void btnAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProductoActionPerformed
    double stock,cant;
  
    if (!txtCantidadProducto.getText().equals("")){
        if(txtCantidadProducto.getText().equals("")){
            txtCantidadProducto.setText("0");
            cant=Double.parseDouble(txtCantidadProducto.getText());
        }else{
        cant=Double.parseDouble(txtCantidadProducto.getText());
        }
            
        if(cant>0){

            int d1=Integer.parseInt(lblIdProducto.getText());
            String d2=txtCodigoProducto.getText();
            String d3=txtNombreProducto.getText();
            String d4=txtDescripcionProducto.getText();
            double d5=Double.parseDouble(txtCantidadProducto.getText());
            double d6=Double.parseDouble(txtPrecioProducto.getText());
            double d7=Double.parseDouble(txtTotalProducto.getText());
            agregardatos(d1,d2,d3,d4,d5,d6,d7);

            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIGV();

            txtCantidadProducto.setText("");
            txtTotalProducto.setText("");
            txtCodigoProducto.requestFocus();  
            
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese Cantidad mayor a 0");
            txtCantidadProducto.requestFocus();   
        }
     
    }else{
        JOptionPane.showMessageDialog(null, "Ingrese cantidad");
        txtCantidadProducto.requestFocus();   
    }

       
    }//GEN-LAST:event_btnAgregarProductoActionPerformed

    private void btnEliminarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProductoActionPerformed
        int fila = tblDetalleProducto.getSelectedRow();
        if (fila > 0) {
            dtmDetalle.removeRow(fila);
            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIGV();
        } else if (fila == 0) {
            dtmDetalle.removeRow(fila);
            txtSubTotal.setText("0.0");
            txtIGV.setText("0.0");
            txtTotalCompra.setText("0.0");

            CalcularTotal_Compra();
            CalcularSubTotal();
            CalcularIGV();
        }

        CalcularTotal_Compra();
        CalcularSubTotal();
        CalcularIGV();
    }//GEN-LAST:event_btnEliminarProductoActionPerformed

    private void btnLimpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTablaActionPerformed
        limpiarTabla();
        txtSubTotal.setText("0.0");
        txtIGV.setText("0.0");
        txtTotalCompra.setText("0.0");
    }//GEN-LAST:event_btnLimpiarTablaActionPerformed

    private void txtCodigoProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyReleased
        BuscarProductoPorCodigo(); 
        int keyCode = evt.getKeyCode();
        if (keyCode==KeyEvent.VK_ENTER) txtCantidadProducto.requestFocus(); 
    }//GEN-LAST:event_txtCodigoProductoKeyReleased

    private void btnBuscarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProductoActionPerformed
    Consultas.FrmBuscarProducto_Compra producto= new Consultas.FrmBuscarProducto_Compra();
    Presentacion.FrmPrincipal.Escritorio.add(producto);
    producto.toFront();
    producto.setVisible(true);
    }//GEN-LAST:event_btnBuscarProductoActionPerformed

    private void btnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProveedorActionPerformed
    Consultas.FrmBuscarProveedor_Compra proveedor= new Consultas.FrmBuscarProveedor_Compra();
    Presentacion.FrmPrincipal.Escritorio.add(proveedor);
    proveedor.toFront();
    proveedor.setVisible(true);
    }//GEN-LAST:event_btnBuscarProveedorActionPerformed

    private void chkCambiarNumeroStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkCambiarNumeroStateChanged
        if (chkCambiarNumero.isSelected()){
            txtNumero.setText("");
            txtNumero.setEnabled(true);
        }else{
            txtNumero.setEnabled(false);
            numCompra=generaNumCompra();
            txtNumero.setText(numCompra);
        }
    }//GEN-LAST:event_chkCambiarNumeroStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int result = JOptionPane.showConfirmDialog(this, "¿Desea Generar la Compra?", "Mensaje del Sistema", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if(accion.equals("Nuevo")){
                ClsCompra compras=new ClsCompra();
                ClsEntidadCompra venta=new ClsEntidadCompra();
                venta.setStrIdTipoDocumento(id[cboTipoDocumento.getSelectedIndex()]);
                venta.setStrIdProveedor(lblIdProveedor.getText());
                venta.setStrIdEmpleado(IdEmpleado);
                venta.setStrNumeroCompra(txtNumero.getText());
                venta.setStrFechaCompra(txtFecha.getDate());
                venta.setStrSubTotalCompra(txtSubTotal.getText());
                venta.setStrIgvCompra(txtIGV.getText());
                venta.setStrTotalCompra(txtTotalCompra.getText());
                venta.setStrEstadoCompra("NORMAL");
                compras.agregarCompra(venta);
                guardarDetalle();
            }
            mirar();
            limpiarTabla();
            numCompra=generaNumCompra();
            txtNumero.setText(numCompra);
            BuscarProveedorPorDefecto();
        }
        if (result == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "Compra Anulada!");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        BuscarProveedorPorDefecto();
        cargarComboTipoDocumento();

    }//GEN-LAST:event_formComponentShown

    private void txtCodigoProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoProductoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtCodigoProductoKeyTyped

    private void txtCantidadProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadProductoKeyTyped
        char car = evt.getKeyChar();
        if((car<'0' || car>'9')) evt.consume();
    }//GEN-LAST:event_txtCantidadProductoKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProducto;
    private javax.swing.JButton btnBuscarProducto;
    private javax.swing.JButton btnBuscarProveedor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarProducto;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiarTabla;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cboTipoDocumento;
    private javax.swing.JCheckBox chkCambiarNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JLabel lblIdProducto;
    public static javax.swing.JLabel lblIdProveedor;
    private javax.swing.JTable tblDetalleProducto;
    private javax.swing.JTextField txtCantidadProducto;
    public static javax.swing.JTextField txtCodigoProducto;
    public static javax.swing.JLabel txtDescripcionProducto;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtIGV;
    public static javax.swing.JTextField txtNombreProducto;
    public static javax.swing.JTextField txtNombreProveedor;
    private javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtPrecioProducto;
    public static javax.swing.JTextField txtStockProducto;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtTotalCompra;
    private javax.swing.JTextField txtTotalProducto;
    // End of variables declaration//GEN-END:variables
}
