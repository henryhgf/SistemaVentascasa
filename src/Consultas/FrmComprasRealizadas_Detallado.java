/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;
import Conexion.ClsConexion;
import Entidad.*;
import Negocio.*;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class FrmComprasRealizadas_Detallado extends javax.swing.JInternalFrame {

    private Connection connection=new ClsConexion().getConection();
    static ResultSet rs=null;
    DefaultTableModel dtm=new DefaultTableModel();
    String id[]=new String[50];
    static int intContador;
    Date fecha_ini,fecha_fin;
    String documento,criterio,busqueda,Total;
    boolean valor=true;
    int n=0;
    
    public FrmComprasRealizadas_Detallado() {
        initComponents();
     
        this.setSize(876, 375);

    
        Date date=new Date();
        String format=new String("dd/MM/yyyy");
        SimpleDateFormat formato=new SimpleDateFormat(format);
        dcFechaini.setDate(date);
        dcFechafin.setDate(date);
        
        BuscarCompra();
        CrearTabla(); 
        CantidadTotal();
        ComprasTotal();
        CantidadCompra();
    }



    void CrearTabla(){
   
      
        TableCellRenderer render = new DefaultTableCellRenderer() { 

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { 
              
                JLabel l = (JLabel)super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
           
                    if(column==0 || column==2 || column==4 || column==5 || column==6 || column==7 || column==8){
                        l.setHorizontalAlignment(SwingConstants.CENTER); 
                    }else{
                        l.setHorizontalAlignment(SwingConstants.LEFT);
                    }

                      
               if (isSelected) {
                    l.setBackground(new Color(203, 159, 41));
                  
                    l.setForeground(Color.WHITE); 
                }else{
                    l.setForeground(Color.BLACK);
                    if (row % 2 != 0) {
                        l.setBackground(Color.WHITE);
                    }
                }     
                return l; 
            } 
        }; 
        
        
        for (int i=0;i<tblCompra.getColumnCount();i++){
            tblCompra.getColumnModel().getColumn(i).setCellRenderer(render);
        }
      
    
        tblCompra.setAutoResizeMode(tblCompra.AUTO_RESIZE_OFF);

    
        int[] anchos = {120,270,120,80,80,80};
        for(int i = 0; i < tblCompra.getColumnCount(); i++) {
            tblCompra.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
        }
       
        ocultarColumnas(tblCompra,new int[]{3});

    }
  void BuscarCompra(){
        String titulos[]={"Código Prod.","Producto","Categoría","Costo","Cantidad","Total"};
        dtm.setColumnIdentifiers(titulos);
        
        ClsCompra venta=new ClsCompra();

        fecha_ini=dcFechaini.getDate();
        fecha_fin=dcFechafin.getDate();

        try{
            rs=venta.listarCompraPorDetalle("consultar",fecha_ini,fecha_fin);
            boolean encuentra=false;
            String Datos[]=new String[6];
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


                dtm.addRow(Datos);
                encuentra=true;

            }
            if(encuentra=false){
                JOptionPane.showMessageDialog(null, "¡No se encuentra!");
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        tblCompra.setModel(dtm);
    }
      
   
  
    void CantidadTotal(){
        Total= String.valueOf(tblCompra.getRowCount());   
        lblEstado.setText("Se Registraron " + Total + " Compras");      
    }

    private void ocultarColumnas(JTable tbl, int columna[]){
        for(int i=0;i<columna.length;i++)
        {
             tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
             tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMinWidth(0);
        }
    }
    void ComprasTotal(){
        Double Total_compra=0.0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            Total_compra += Double.parseDouble(String.valueOf(tblCompra.getModel().getValueAt(f, 5)));            
        }
        txtTotalCompra.setText(String.valueOf(Total_compra));
    }
    void CantidadCompra(){
        Double cantidad=0.0;    
        int fila = 0;
        fila = dtm.getRowCount();
        for (int f=0; f<fila; f++){
            cantidad += Double.parseDouble(String.valueOf(tblCompra.getModel().getValueAt(f, 4)));            
        }
        txtCantidad.setText(String.valueOf(cantidad));
    }

    public double Truncar(double nD, int nDec)
    {
      if(nD > 0)
        nD = Math.floor(nD * Math.pow(10,nDec))/Math.pow(10,nDec);
      else
        nD = Math.ceil(nD * Math.pow(10,nDec))/Math.pow(10,nDec);

      return nD;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        tblCompra = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        dcFechaini = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        dcFechafin = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblEstado = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtTotalCompra = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Reporte de compras al detalle");
        getContentPane().setLayout(null);

        tblCompra.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCompra.setRowHeight(22);
        tblCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCompraMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblCompra);

        getContentPane().add(jScrollPane5);
        jScrollPane5.setBounds(10, 10, 840, 190);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar Compras por Fecha"));
        jPanel1.setLayout(null);

        dcFechaini.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(dcFechaini);
        dcFechaini.setBounds(20, 40, 100, 25);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("DESDE:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 20, 70, 20);

        dcFechafin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(dcFechafin);
        dcFechafin.setBounds(130, 40, 100, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("HASTA:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(130, 20, 70, 20);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Buscar_32.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(240, 23, 110, 50);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/printer.png"))); // NOI18N
        jButton2.setText("Imprimir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(360, 23, 110, 50);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 240, 480, 90);
        getContentPane().add(lblEstado);
        lblEstado.setBounds(10, 210, 230, 20);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Gastos de Compras"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CANTIDAD");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 100, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTAL COMPRAS");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 20, 100, 20));

        txtCantidad.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 100, 30));

        txtTotalCompra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTotalCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotalCompra.setCaretColor(new java.awt.Color(255, 51, 51));
        jPanel2.add(txtTotalCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, 100, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(500, 240, 350, 90);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCompraMouseClicked

    }//GEN-LAST:event_tblCompraMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        BuscarCompra();
        CrearTabla();
        CantidadTotal();
        ComprasTotal();
        CantidadCompra();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Map p=new HashMap();

        Date fecha_inicial=(dcFechaini.getDate());
        Date fecha_final=(dcFechafin.getDate());
        p.put("criterio" ,"consultar");
        p.put("fecha_ini" ,fecha_inicial);
        p.put("fecha_fin" ,fecha_final);

        JasperReport report;
        JasperPrint print;
        try{

            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/RptCompraDetallada.jrxml");
            print=JasperFillManager.fillReport(report, p,connection);
            JasperViewer view=new JasperViewer(print,false);
            view.setTitle("Reporte General de Compras Realizadas");
            view.setVisible(true);
        }catch(JRException e){
            e.printStackTrace();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dcFechafin;
    private com.toedter.calendar.JDateChooser dcFechaini;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JTable tblCompra;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtTotalCompra;
    // End of variables declaration//GEN-END:variables
}
