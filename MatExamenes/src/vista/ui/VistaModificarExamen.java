/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerExamenes;
import vista.interfaz.InterfaceExamen;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaModificarExamen extends javax.swing.JPanel
implements InterfaceVista, InterfaceExamen {

    private CVMantenerExamenes controlVista;
    private InterfaceVista padre;
    private FrmAgregarReactivos vistaAgregarReactivos;
    /**
     * Creates new form ModificarExamen
     */
    public VistaModificarExamen() {
        initComponents();
        vistaAgregarReactivos = new FrmAgregarReactivos();
        vistaAgregarReactivos.setPadre(this);
        vistaAgregarReactivos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }
    
    public void setControlador(CVMantenerExamenes controlVista) {
        this.controlVista = controlVista;
    }
    
    @Override
    public InterfaceVista getPadre() {
        return padre;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        tbpClaves = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblReactivos1 = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtfTitulo = new javax.swing.JTextField();
        lblInstrucciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaInstrucciones = new javax.swing.JTextArea();
        pnlPermiso = new javax.swing.JPanel();
        rbtnPrivado = new javax.swing.JRadioButton();
        rbtnPublico = new javax.swing.JRadioButton();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        btnRemoverClave = new javax.swing.JButton();
        lblClaves = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnDesbloquear = new javax.swing.JButton();
        btnAgregarClave = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(790, 467));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setAutoscrolls(true);
        jPanel4.setPreferredSize(new java.awt.Dimension(790, 579));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[X]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        lblReactivos1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReactivos1.setText("Reactivos:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReactivos1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblReactivos1)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpClaves.addTab("clave 1", jPanel1);

        lblTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo1.setText("Modificar Examen");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setText("Título del Examen:");

        txtfTitulo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfTitulo.setPreferredSize(new java.awt.Dimension(6, 30));

        lblInstrucciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInstrucciones.setText("Instrucciones del Examen:");

        txtaInstrucciones.setColumns(20);
        txtaInstrucciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaInstrucciones.setLineWrap(true);
        txtaInstrucciones.setRows(5);
        jScrollPane2.setViewportView(txtaInstrucciones);

        pnlPermiso.setBackground(new java.awt.Color(255, 255, 255));
        pnlPermiso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permiso:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlPermiso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        rbtnPrivado.setBackground(new java.awt.Color(255, 255, 255));
        rbtnPrivado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPrivado.setText("Privado");

        rbtnPublico.setBackground(new java.awt.Color(255, 255, 255));
        rbtnPublico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPublico.setText("Público");

        javax.swing.GroupLayout pnlPermisoLayout = new javax.swing.GroupLayout(pnlPermiso);
        pnlPermiso.setLayout(pnlPermisoLayout);
        pnlPermisoLayout.setHorizontalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPermisoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rbtnPublico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtnPrivado)
                .addGap(27, 27, 27))
        );
        pnlPermisoLayout.setVerticalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("");
        cmbCurso.setEnabled(false);
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        btnRemoverClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverClave.setText("Remover Clave");
        btnRemoverClave.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemoverClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverClavepasarControlVistaConsultar(evt);
            }
        });

        lblClaves.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblClaves.setText("Claves:");

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonGuardar_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarmodificarReactivo(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarpasarControlVistaConsultar(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonRemover.png"))); // NOI18N
        btnRemover.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemovermodificarReactivo(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarmodificarReactivo(evt);
            }
        });

        btnVer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonVer.png"))); // NOI18N
        btnVer.setPreferredSize(new java.awt.Dimension(77, 30));
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVermodificarReactivo(evt);
            }
        });

        btnDesbloquear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDesbloquear.setText("Desbloquear");
        btnDesbloquear.setEnabled(false);
        btnDesbloquear.setPreferredSize(new java.awt.Dimension(77, 30));
        btnDesbloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesbloquearpasarControlVistaConsultar(evt);
            }
        });

        btnAgregarClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregarClave.setText("Agregar Clave");
        btnAgregarClave.setPreferredSize(new java.awt.Dimension(118, 30));
        btnAgregarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClavepasarControlVistaConsultar(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(497, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCurso)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(txtfTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(57, 57, 57)
                            .addComponent(lblInstrucciones))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(49, 49, 49)
                            .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitulo1)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(lblClaves)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(84, 84, 84)
                            .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(31, 31, 31)
                            .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(515, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(lblTitulo1)
                    .addGap(42, 42, 42)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblClaves)
                                    .addGap(38, 38, 38)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(lblTitulo)
                            .addGap(13, 13, 13)
                            .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblInstrucciones)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblCurso)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(14, 14, 14)))
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        jScrollPane5.setViewportView(jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRemoverClavepasarControlVistaConsultar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverClavepasarControlVistaConsultar
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
            + "quieres cancelar la operación?\nTodos los cambios no "
            + "guardados se perderán");
        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarReactivos);
            limpiar();
        }
    }//GEN-LAST:event_btnRemoverClavepasarControlVistaConsultar

    private void btnGuardarmodificarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarmodificarReactivo

    }//GEN-LAST:event_btnGuardarmodificarReactivo

    private void btnCancelarpasarControlVistaConsultar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarpasarControlVistaConsultar
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
            + "quieres cancelar la operación?\nTodos los cambios no "
            + "guardados se perderán");
        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarReactivos);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarpasarControlVistaConsultar

    private void btnRemovermodificarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemovermodificarReactivo
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemovermodificarReactivo

    private void btnAgregarmodificarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarmodificarReactivo
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarmodificarReactivo

    private void btnVermodificarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVermodificarReactivo
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVermodificarReactivo

    private void btnDesbloquearpasarControlVistaConsultar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesbloquearpasarControlVistaConsultar
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDesbloquearpasarControlVistaConsultar

    private void btnAgregarClavepasarControlVistaConsultar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClavepasarControlVistaConsultar
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarClavepasarControlVistaConsultar

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarClave;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesbloquear;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverClave;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblClaves;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblInstrucciones;
    private javax.swing.JLabel lblReactivos1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel pnlPermiso;
    private javax.swing.JRadioButton rbtnPrivado;
    private javax.swing.JRadioButton rbtnPublico;
    private javax.swing.JTabbedPane tbpClaves;
    private javax.swing.JTextArea txtaInstrucciones;
    private javax.swing.JTextField txtfTitulo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        //Mostrar examen
    }

    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        //Limpiar componentes
    }
}
