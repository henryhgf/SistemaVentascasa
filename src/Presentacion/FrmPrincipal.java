/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import Backup.*;
import Fondo.ImagenFondo;
import Entidad.*;
import Negocio.*;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.util.Date;
import java.text.*;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;



public class FrmPrincipal extends javax.swing.JFrame {
   public int intEstado; 
   public String strUsuario; 
   public String strIdEmpleado;
   public String strNombreEmpleado;
   public String strTipo; 
   public String idAcceso;
   static ResultSet rs=null;
      private boolean mFullScreen = false;

    String p_venta,p_compra,p_producto,p_proveedor,p_empleado;
    String p_cliente,p_categoria,p_tipodoc,p_tipouser,p_anularv,p_anularc;
    String p_estadoprod,p_ventare,p_ventade,p_estadistica,p_comprare,p_comprade,p_pass,p_respaldar,p_restaurar,p_caja;
    //----------------------------------------------------
   public FrmPrincipal() {
        initComponents();
        JMIniciarSesion.setEnabled(false);
        Escritorio.setBorder(new ImagenFondo());
        this.setExtendedState(FrmPrincipal.MAXIMIZED_BOTH);
        
        Date date=new Date();
        String format=new String("dd/MM/yyyy");
        SimpleDateFormat formato=new SimpleDateFormat(format);
        lblFecha.setText(formato.format(date));
      
        this.setExtendedState(MAXIMIZED_BOTH);
    
            
        lblIdEmpleado.setVisible(false);
        lblUsuarioEmpleado.setVisible(false);
        lblEstado.setVisible(false);
   }

void BuscarPermisos(){
        String usuario=null;
        String tipo=null;
        int sen=1;int tu=1;
        usuario=strUsuario;
        tipo=strTipo;
        boolean encuentra=false;
    
            try{
                ClsTipoUsuario oTipoUsuario=new ClsTipoUsuario();
                
                rs= oTipoUsuario.consultarLoginPermisos(usuario,tipo);
                while (rs.next()&& sen==1) {
                    if(rs.getString(2).equals(strUsuario)&&rs.getString(3).equals(strTipo)) {
        
                            p_venta=rs.getString(4);
                            p_compra=rs.getString(5);
                            p_producto=rs.getString(6);
                            p_proveedor=rs.getString(7);
                            p_empleado=rs.getString(8);
                            p_cliente=rs.getString(9);
                            p_categoria=rs.getString(10);
                            p_tipodoc=rs.getString(11);
                            p_tipouser=rs.getString(12);
                            p_anularv=rs.getString(13);
                            p_anularc=rs.getString(14);
                            p_estadoprod=rs.getString(15);
                            p_ventare=rs.getString(16);
                            p_ventade=rs.getString(17);
                            p_estadistica=rs.getString(18);
                            p_comprare=rs.getString(19);
                            p_comprade=rs.getString(20);
                            p_pass=rs.getString(21);
                            p_respaldar=rs.getString(22);
                            p_restaurar=rs.getString(23);
                            p_caja=rs.getString(24);
    
                            sen=2;
         
                    }
                   encuentra=true;
                   break;
                }
                if(sen==1) {
                    JOptionPane.showMessageDialog(this, "¡Código de Asistente no Registrado!");

                } else {
                              
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
                System.out.println(ex.getMessage());
            }        
    
    }
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new javax.swing.JDesktopPane();
        lblTipo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblUsuarioEmpleado = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        lblNombreEmpleado = new javax.swing.JLabel();
        lblIdEmpleado = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        JMIniciarSesion = new javax.swing.JMenuItem();
        JMCerrarSesion = new javax.swing.JMenuItem();
        JMSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mProducto = new javax.swing.JMenuItem();
        mCategoria = new javax.swing.JMenuItem();
        mnuRegistro = new javax.swing.JMenu();
        mCompra = new javax.swing.JMenuItem();
        mProveedor = new javax.swing.JMenuItem();
        mnuOperaciones = new javax.swing.JMenu();
        mVenta = new javax.swing.JMenuItem();
        mEstado = new javax.swing.JMenuItem();
        mCliente = new javax.swing.JMenuItem();
        mbtnEstado = new javax.swing.JMenu();
        mVentare = new javax.swing.JMenuItem();
        mVentade = new javax.swing.JMenuItem();
        mEstadistica = new javax.swing.JMenuItem();
        mComprare = new javax.swing.JMenuItem();
        mComprade = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        mnuMantenimiento = new javax.swing.JMenu();
        mEmpleado = new javax.swing.JMenuItem();
        mTipodoc = new javax.swing.JMenuItem();
        mTipouser = new javax.swing.JMenuItem();
        mnuAnulaciones = new javax.swing.JMenu();
        mAnularv = new javax.swing.JMenuItem();
        mAnularc = new javax.swing.JMenuItem();
        mnuHerramientas = new javax.swing.JMenu();
        mCambiarpass = new javax.swing.JMenuItem();
        mCalculadora = new javax.swing.JMenuItem();
        mRespaldar = new javax.swing.JMenuItem();
        mRestaurar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Ventas V.1b");
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        Escritorio.setBackground(new java.awt.Color(204, 255, 255));

        lblTipo.setText("Tipo");

        lblFecha.setText("Fecha");

        lblUsuarioEmpleado.setText("Usuario");

        lblEstado.setText("Estado");

        lblNombreEmpleado.setText("Nombres");

        lblIdEmpleado.setText("ID");

        jLabel8.setText("Nombre:");

        jLabel9.setText("Cargo:");

        jLabel10.setText("Fecha:");

        mnuArchivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/session.png"))); // NOI18N
        mnuArchivo.setText("Salir");

        JMIniciarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        JMIniciarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iniciarse.png"))); // NOI18N
        JMIniciarSesion.setText("Iniciar sesión");
        JMIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMIniciarSesionActionPerformed(evt);
            }
        });
        mnuArchivo.add(JMIniciarSesion);

        JMCerrarSesion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        JMCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrarse.png"))); // NOI18N
        JMCerrarSesion.setText("Cerrar sesión");
        JMCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMCerrarSesionActionPerformed(evt);
            }
        });
        mnuArchivo.add(JMCerrarSesion);

        JMSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        JMSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/door_in.png"))); // NOI18N
        JMSalir.setText("Salir de la aplicación");
        JMSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JMSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(JMSalir);

        jMenuBar1.add(mnuArchivo);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Almacen32.png"))); // NOI18N
        jMenu1.setText("Almacén");

        mProducto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/salida-del-producto.png"))); // NOI18N
        mProducto.setText("Producto");
        mProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mProductoActionPerformed(evt);
            }
        });
        jMenu1.add(mProducto);

        mCategoria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/boton-de-anadir-categorias.png"))); // NOI18N
        mCategoria.setText("Categoria");
        mCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCategoriaActionPerformed(evt);
            }
        });
        jMenu1.add(mCategoria);

        jMenuBar1.add(jMenu1);

        mnuRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/almacen.png"))); // NOI18N
        mnuRegistro.setText("Compras");

        mCompra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        mCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/bolsa.png"))); // NOI18N
        mCompra.setText("Compra");
        mCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCompraActionPerformed(evt);
            }
        });
        mnuRegistro.add(mCompra);

        mProveedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/dependiente-hablador.png"))); // NOI18N
        mProveedor.setText("Proveedor");
        mProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mProveedorActionPerformed(evt);
            }
        });
        mnuRegistro.add(mProveedor);

        jMenuBar1.add(mnuRegistro);

        mnuOperaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/vender.png"))); // NOI18N
        mnuOperaciones.setText("Ventas");

        mVenta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        mVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/venta.png"))); // NOI18N
        mVenta.setText("Venta");
        mVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mVentaActionPerformed(evt);
            }
        });
        mnuOperaciones.add(mVenta);

        mEstado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        mEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/codigo-de-barras.png"))); // NOI18N
        mEstado.setText("Verificar Producto");
        mEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEstadoActionPerformed(evt);
            }
        });
        mnuOperaciones.add(mEstado);

        mCliente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        mCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/sponsor_m.png"))); // NOI18N
        mCliente.setText("Cliente");
        mCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mClienteActionPerformed(evt);
            }
        });
        mnuOperaciones.add(mCliente);

        jMenuBar1.add(mnuOperaciones);

        mbtnEstado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/estadistica.png"))); // NOI18N
        mbtnEstado.setText("Consultas");

        mVentare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ventasrealizadas.png"))); // NOI18N
        mVentare.setText("Ventas Realizadas");
        mVentare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mVentareActionPerformed(evt);
            }
        });
        mbtnEstado.add(mVentare);

        mVentade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/ventasdetalladas.png"))); // NOI18N
        mVentade.setText("Ventas Detalladas");
        mVentade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mVentadeActionPerformed(evt);
            }
        });
        mbtnEstado.add(mVentade);

        mEstadistica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/estadisticamensual.png"))); // NOI18N
        mEstadistica.setText("Estadística Mensual");
        mEstadistica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEstadisticaActionPerformed(evt);
            }
        });
        mbtnEstado.add(mEstadistica);

        mComprare.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/comprasrealizadas.png"))); // NOI18N
        mComprare.setText("Compras Realizadas");
        mComprare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mComprareActionPerformed(evt);
            }
        });
        mbtnEstado.add(mComprare);

        mComprade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/comprasdetalladas.png"))); // NOI18N
        mComprade.setText("Compras Detalladas");
        mComprade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCompradeActionPerformed(evt);
            }
        });
        mbtnEstado.add(mComprade);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/caja_m.png"))); // NOI18N
        jMenuItem1.setText("Ventas del Dia");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mbtnEstado.add(jMenuItem1);

        jMenuBar1.add(mbtnEstado);

        mnuMantenimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/confi.png"))); // NOI18N
        mnuMantenimiento.setText("Mantenimiento");

        mEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/empleados.png"))); // NOI18N
        mEmpleado.setText("Empleado");
        mEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mEmpleadoActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mEmpleado);

        mTipodoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/carpeta.png"))); // NOI18N
        mTipodoc.setText("Tipo de Documento");
        mTipodoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTipodocActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mTipodoc);

        mTipouser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/usuarios.png"))); // NOI18N
        mTipouser.setText("Tipo de Usuario");
        mTipouser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mTipouserActionPerformed(evt);
            }
        });
        mnuMantenimiento.add(mTipouser);

        jMenuBar1.add(mnuMantenimiento);

        mnuAnulaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anular32.png"))); // NOI18N
        mnuAnulaciones.setText("Anulaciones");

        mAnularv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/archivo.png"))); // NOI18N
        mAnularv.setText("Anular Venta");
        mAnularv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAnularvActionPerformed(evt);
            }
        });
        mnuAnulaciones.add(mAnularv);

        mAnularc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/archivo.png"))); // NOI18N
        mAnularc.setText("Anular Compra");
        mAnularc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mAnularcActionPerformed(evt);
            }
        });
        mnuAnulaciones.add(mAnularc);

        jMenuBar1.add(mnuAnulaciones);

        mnuHerramientas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Gear.png"))); // NOI18N
        mnuHerramientas.setText("Herramientas");

        mCambiarpass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/group_key.png"))); // NOI18N
        mCambiarpass.setText("Cambiar contraseña");
        mCambiarpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCambiarpassActionPerformed(evt);
            }
        });
        mnuHerramientas.add(mCambiarpass);

        mCalculadora.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/calculator.png"))); // NOI18N
        mCalculadora.setText("Calculadora");
        mCalculadora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCalculadoraActionPerformed(evt);
            }
        });
        mnuHerramientas.add(mCalculadora);

        mRespaldar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/database_save.png"))); // NOI18N
        mRespaldar.setText("Respaldar BD");
        mRespaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRespaldarActionPerformed(evt);
            }
        });
        mnuHerramientas.add(mRespaldar);

        mRestaurar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/database_refresh.png"))); // NOI18N
        mRestaurar.setText("Restaurar BD");
        mRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mRestaurarActionPerformed(evt);
            }
        });
        mnuHerramientas.add(mRestaurar);

        jMenuBar1.add(mnuHerramientas);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Escritorio)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIdEmpleado)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblUsuarioEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(lblEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(lblTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUsuarioEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblNombreEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIdEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1050, 604));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        BuscarPermisos();
        lblEstado.setText("Desconectado");
        if(intEstado==1){
            lblIdEmpleado.setText(strIdEmpleado);
            lblNombreEmpleado.setText(strNombreEmpleado);            
            lblUsuarioEmpleado.setText(strUsuario);
            lblTipo.setText(strTipo);
            lblEstado.setText("Conectado");
        }
        if(lblEstado.getText().equals("Desconectado")){
            mnuArchivo.setVisible(false);
            mnuMantenimiento.setVisible(false);
           
        }
         if(lblUsuarioEmpleado.getText().equals("Coordinador")){
            mnuRegistro.setVisible(false);
            mnuMantenimiento.setVisible(false);
            
          
        }
         

    if (p_venta.equals("1")){
        mVenta.setEnabled(true);
   
    }else{
        mVenta.setEnabled(false);
      
    }
    if (p_compra.equals("1")){
        mCompra.setEnabled(true);
      
    }else{
        mCompra.setEnabled(false);
      
    }
    if (p_producto.equals("1")){
        mProducto.setEnabled(true);
        
    }else{
        mProducto.setEnabled(false);
     
    }
    if (p_proveedor.equals("1")){
        mProveedor.setEnabled(true);
    }else{
        mProveedor.setEnabled(false);
    }
    if (p_empleado.equals("1")){
        mEmpleado.setEnabled(true);
       
    }else{
        mEmpleado.setEnabled(false);
      
    }
    if (p_cliente.equals("1")){
        mCliente.setEnabled(true);
      
    }else{
        mCliente.setEnabled(false);
       
    }
    if (p_categoria.equals("1")){
        mCategoria.setEnabled(true);
    }else{
        mCategoria.setEnabled(false);
    }
    if (p_tipodoc.equals("1")){
        mTipodoc.setEnabled(true);
    }else{
        mTipodoc.setEnabled(false);
    }
    if (p_tipouser.equals("1")){
        mTipouser.setEnabled(true);
    }else{
        mTipouser.setEnabled(false);
    }    
    if (p_anularv.equals("1")){
        mAnularv.setEnabled(true);
    }else{
        mAnularv.setEnabled(false);
    }  
    if (p_anularc.equals("1")){
        mAnularc.setEnabled(true);
    }else{
        mAnularc.setEnabled(false);
    }  
    if (p_estadoprod.equals("1")){
        mEstado.setEnabled(true);
        mbtnEstado.setEnabled(true);
    }else{
        mEstado.setEnabled(false);
        mbtnEstado.setEnabled(false);
    }  
    if (p_ventare.equals("1")){
        mVentare.setEnabled(true);
    }else{
        mVentare.setEnabled(false);
    }  
    if (p_ventade.equals("1")){
        mVentade.setEnabled(true);
    }else{
        mVentade.setEnabled(false);
    }  
    if (p_estadistica.equals("1")){
        mEstadistica.setEnabled(true);
    }else{
        mEstadistica.setEnabled(false);
    }  
    if (p_comprare.equals("1")){
        mComprare.setEnabled(true);
    }else{
        mComprare.setEnabled(false);
    }  
    if (p_comprade.equals("1")){
        mComprade.setEnabled(true);
    }else{
        mComprade.setEnabled(false);
    }  
    if (p_pass.equals("1")){
        mCambiarpass.setEnabled(true);
    }else{
        mCambiarpass.setEnabled(false);
    }  
    if (p_respaldar.equals("1")){
        mRespaldar.setEnabled(true);
    }else{
        mRespaldar.setEnabled(false);
    } 
    if (p_restaurar.equals("1")){
        mRestaurar.setEnabled(true);
    }else{
        mRestaurar.setEnabled(false);
    } 
    if (p_caja.equals("1")){
        jMenuItem1.setEnabled(true);
    }else{
        jMenuItem1.setEnabled(false);
    }     
    }//GEN-LAST:event_formComponentShown

    private void mCambiarpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCambiarpassActionPerformed
        Presentacion.FrmCambiarContraseña Cambiar=new Presentacion.FrmCambiarContraseña();
        Escritorio.add(Cambiar);
        Cambiar.show();
        Cambiar.IdEmpleado=lblIdEmpleado.getText();
        CentrarVentanaInterna(Cambiar);
       
    }//GEN-LAST:event_mCambiarpassActionPerformed

    private void JMSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMSalirActionPerformed
        //ClsAcceso acceso = new ClsAcceso();
        //acceso.modificarAcceso(idAcceso);
        int salir = JOptionPane.showConfirmDialog(this, "¿Desea realmente cerrar la aplicación?","Mensaje del Sistema WorldEvent",0,3);
        if(salir==JOptionPane.OK_OPTION)
        {
            System.exit(0);
        }
        
    }//GEN-LAST:event_JMSalirActionPerformed

    private void mVentadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mVentadeActionPerformed
       Consultas.FrmVentasRealizadas_Detallado ventasdetalladas=new Consultas.FrmVentasRealizadas_Detallado();
       Escritorio.add(ventasdetalladas);
       ventasdetalladas.show(); 
        CentrarVentanaInterna(ventasdetalladas);
    }//GEN-LAST:event_mVentadeActionPerformed

    private void mVentareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mVentareActionPerformed
       Consultas.FrmVentasRealizadas VentasRealizadas=new Consultas.FrmVentasRealizadas();
       Escritorio.add(VentasRealizadas);
       VentasRealizadas.show();
        CentrarVentanaInterna(VentasRealizadas);
    }//GEN-LAST:event_mVentareActionPerformed

    private void mComprareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mComprareActionPerformed
       Consultas.FrmComprasRelizadas ComprasRelizadas=new Consultas.FrmComprasRelizadas();
       Escritorio.add(ComprasRelizadas);
       ComprasRelizadas.show();
        CentrarVentanaInterna(ComprasRelizadas);
    }//GEN-LAST:event_mComprareActionPerformed

    private void mRespaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRespaldarActionPerformed
        Backup.FrmRespaldarDB Respaldar=new Backup.FrmRespaldarDB();
        Escritorio.add(Respaldar);
        Respaldar.show();
        CentrarVentanaInterna(Respaldar);
    }//GEN-LAST:event_mRespaldarActionPerformed

    private void mRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mRestaurarActionPerformed
        Backup.FrmRestaurarDB Restaurar=new Backup.FrmRestaurarDB();
        Escritorio.add(Restaurar);
        Restaurar.show();
        CentrarVentanaInterna(Restaurar);
    }//GEN-LAST:event_mRestaurarActionPerformed

    private void mCalculadoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCalculadoraActionPerformed
        try
        {
            Runtime obj = Runtime.getRuntime();
            obj.exec("C:\\WINDOWS\\system32\\CALC.EXE");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_mCalculadoraActionPerformed

    private void JMIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMIniciarSesionActionPerformed
        this.dispose();
        Presentacion.FrmLogin Login=new Presentacion.FrmLogin();

        Login.show();
      


    }//GEN-LAST:event_JMIniciarSesionActionPerformed

    private void JMCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JMCerrarSesionActionPerformed
        JMCerrarSesion.setEnabled(false);
        mnuRegistro.setEnabled(false);
        mnuOperaciones.setEnabled(false);
        mnuMantenimiento.setEnabled(false);
        mbtnEstado.setEnabled(false);
        mnuHerramientas.setEnabled(false);
        mnuAnulaciones.setEnabled(false);
      
        jMenuItem1.setEnabled(false);
        mbtnEstado.setEnabled(false);
        JMIniciarSesion.setEnabled(true);
        
    }//GEN-LAST:event_JMCerrarSesionActionPerformed

    private void mCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCategoriaActionPerformed
        Presentacion.FrmCategoria Categoria=new Presentacion.FrmCategoria();
        Escritorio.add(Categoria);
        Categoria.show();
        CentrarVentanaInterna(Categoria);
    }//GEN-LAST:event_mCategoriaActionPerformed

    private void mClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mClienteActionPerformed
        Presentacion.FrmCliente Cliente=new Presentacion.FrmCliente();
        Escritorio.add(Cliente);
        Cliente.show();
        CentrarVentanaInterna(Cliente);
    }//GEN-LAST:event_mClienteActionPerformed

    private void mProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mProveedorActionPerformed
        Presentacion.FrmProveedor Proveedor=new Presentacion.FrmProveedor();
        Escritorio.add(Proveedor);
        Proveedor.show();
        CentrarVentanaInterna(Proveedor);
    }//GEN-LAST:event_mProveedorActionPerformed

    private void mTipodocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTipodocActionPerformed
        Presentacion.FrmTipoDocumento Documento=new Presentacion.FrmTipoDocumento();
        Escritorio.add(Documento);
        Documento.show();
        CentrarVentanaInterna(Documento);
    }//GEN-LAST:event_mTipodocActionPerformed

    private void mTipouserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mTipouserActionPerformed
        Presentacion.FrmTipoUsuario Usuario=new Presentacion.FrmTipoUsuario();
        Escritorio.add(Usuario);
        Usuario.show();
        CentrarVentanaInterna(Usuario);
    }//GEN-LAST:event_mTipouserActionPerformed

    private void mProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mProductoActionPerformed
        Presentacion.FrmProducto Producto=new Presentacion.FrmProducto();
        Escritorio.add(Producto);
        Producto.show();
        CentrarVentanaInterna(Producto);
    }//GEN-LAST:event_mProductoActionPerformed

    private void mVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mVentaActionPerformed
      
        Presentacion.FrmVenta venta=new Presentacion.FrmVenta();
        Escritorio.add(venta);
        venta.show();
        venta.IdEmpleado=lblIdEmpleado.getText();
        venta.NombreEmpleado=lblNombreEmpleado.getText();
        CentrarVentanaInterna(venta);

    }//GEN-LAST:event_mVentaActionPerformed

    private void mCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCompraActionPerformed
        Presentacion.FrmCompra compra=new Presentacion.FrmCompra();
        Escritorio.add(compra);
        compra.show();
        compra.IdEmpleado=lblIdEmpleado.getText();
        compra.NombreEmpleado=lblNombreEmpleado.getText();
        CentrarVentanaInterna(compra);
    }//GEN-LAST:event_mCompraActionPerformed

    private void mEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEmpleadoActionPerformed
        Presentacion.FrmEmpleado Empleado=new Presentacion.FrmEmpleado();
        Escritorio.add(Empleado);
        Empleado.show();
        CentrarVentanaInterna(Empleado);
    }//GEN-LAST:event_mEmpleadoActionPerformed

    private void mEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEstadoActionPerformed
        Presentacion.FrmProductoEstado ProductoEstado=new Presentacion.FrmProductoEstado();
        Escritorio.add(ProductoEstado);
        ProductoEstado.show();
        CentrarVentanaInterna(ProductoEstado);
    }//GEN-LAST:event_mEstadoActionPerformed

    private void mAnularvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAnularvActionPerformed
        Presentacion.FrmAnularVenta AnularVenta=new Presentacion.FrmAnularVenta();
        Escritorio.add(AnularVenta);
        AnularVenta.show();
        CentrarVentanaInterna(AnularVenta);
    }//GEN-LAST:event_mAnularvActionPerformed

    private void mAnularcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mAnularcActionPerformed
        Presentacion.FrmAnularCompra AnularCompra=new Presentacion.FrmAnularCompra();
        Escritorio.add(AnularCompra);
        AnularCompra.show();
        CentrarVentanaInterna(AnularCompra);
    }//GEN-LAST:event_mAnularcActionPerformed

    private void mEstadisticaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mEstadisticaActionPerformed
        Consultas.FrmVentasMensuales Ventas=new Consultas.FrmVentasMensuales();
        Escritorio.add(Ventas);
        Ventas.show();
        CentrarVentanaInterna(Ventas);
    }//GEN-LAST:event_mEstadisticaActionPerformed

    private void mCompradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCompradeActionPerformed
       Consultas.FrmComprasRealizadas_Detallado Compras=new Consultas.FrmComprasRealizadas_Detallado();
        Escritorio.add(Compras);
        Compras.show();
        CentrarVentanaInterna(Compras);
    }//GEN-LAST:event_mCompradeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        Presentacion.FrmCaja Caja=new Presentacion.FrmCaja();
        Escritorio.add(Caja);
        Caja.show();
       
        CentrarVentanaInterna(Caja);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane Escritorio;
    private javax.swing.JMenuItem JMCerrarSesion;
    private javax.swing.JMenuItem JMIniciarSesion;
    private javax.swing.JMenuItem JMSalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdEmpleado;
    private javax.swing.JLabel lblNombreEmpleado;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblUsuarioEmpleado;
    private javax.swing.JMenuItem mAnularc;
    private javax.swing.JMenuItem mAnularv;
    private javax.swing.JMenuItem mCalculadora;
    private javax.swing.JMenuItem mCambiarpass;
    private javax.swing.JMenuItem mCategoria;
    private javax.swing.JMenuItem mCliente;
    private javax.swing.JMenuItem mCompra;
    private javax.swing.JMenuItem mComprade;
    private javax.swing.JMenuItem mComprare;
    private javax.swing.JMenuItem mEmpleado;
    private javax.swing.JMenuItem mEstadistica;
    private javax.swing.JMenuItem mEstado;
    private javax.swing.JMenuItem mProducto;
    private javax.swing.JMenuItem mProveedor;
    private javax.swing.JMenuItem mRespaldar;
    private javax.swing.JMenuItem mRestaurar;
    private javax.swing.JMenuItem mTipodoc;
    private javax.swing.JMenuItem mTipouser;
    private javax.swing.JMenuItem mVenta;
    private javax.swing.JMenuItem mVentade;
    private javax.swing.JMenuItem mVentare;
    private javax.swing.JMenu mbtnEstado;
    private javax.swing.JMenu mnuAnulaciones;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenu mnuHerramientas;
    private javax.swing.JMenu mnuMantenimiento;
    private javax.swing.JMenu mnuOperaciones;
    private javax.swing.JMenu mnuRegistro;
    // End of variables declaration//GEN-END:variables

    public void CentrarVentanaInterna(JInternalFrame internalFrame){
        int x = ((Escritorio.getWidth()/ 2) - internalFrame.getWidth() / 2);
        int y = ((Escritorio.getHeight()/ 2 ) - internalFrame.getHeight()/ 2);
        if(internalFrame.isShowing()){
        internalFrame.setLocation(x,y);
        }else{
            Escritorio.add(internalFrame);
            internalFrame.setLocation(x,y);
            internalFrame.show();
        
        }
    
    
    
    }



}
