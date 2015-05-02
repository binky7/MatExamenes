/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerReactivos;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author Jesus Donaldo
 */
public class VistaConsultarReactivos extends javax.swing.JPanel
implements InterfaceVista, AncestorListener {

    private InterfaceVista padre;
    private CVMantenerReactivos controlVista;
    private boolean noSelect;
    
    /**
     * Creates new form ConsultarReactivos
     */
    public VistaConsultarReactivos() {
        initComponents();
        
        addAncestorListener(this);
        
        //Listener para el cmbCurso
        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!noSelect) {
                    consultarTemasDeCurso();
                }
            }
            
        });
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }
   
    public void setControlador(CVMantenerReactivos controlVista) {
        this.controlVista = controlVista;
    }

    public void deshabilitarBtnEliminar() {
        btnEliminar.setEnabled(false);
    }
    
    private void consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        
        if(cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }
    
    private void mostrarCursos(List<CursoDTO> cursos) {
        
        cmbCurso.removeAllItems();
        
        for(CursoDTO curso : cursos) {
            System.out.println(cmbCurso.getSelectedIndex());
            cmbCurso.addItem(curso.getNombre());
        }
        
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }
    
    private void consultarTemasDeCurso() {
        
        if (cmbCurso.getSelectedIndex() != -1) {
            List<TemaDTO> temas = controlVista.obtenerTemasDeCurso(cmbCurso
                    .getSelectedIndex());

            if (temas != null && !temas.isEmpty()) {
                mostrarTemas(temas);
            } else {
                JOptionPane.showMessageDialog(this, "No hay temas");
                cmbTema.removeAllItems();
            }
        }
        else {
            cmbTema.removeAllItems();
        }
    }
    
    private void mostrarTemas(List<TemaDTO> temas) {
        
        cmbTema.removeAllItems();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for(TemaDTO tema : temas) {
            cmbTema.addItem(tema.getNombre());
        }
        
        cmbTema.setSelectedIndex(-1);
    }
    
    private void consultarReactivos() {
        
        if (cmbTema.getSelectedIndex() != -1) {
            List<ReactivoDTO> reactivos = controlVista
                    .obtenerReactivosPorTema(cmbTema
                            .getSelectedItem().toString());

            if (reactivos != null && !reactivos.isEmpty()) {
                mostrarReactivos(reactivos);
            } else {
                JOptionPane.showMessageDialog(this, "No hay reactivos");
                ((DefaultTableModel)tblReactivos.getModel()).setRowCount(0);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona un Tema primero");
        }
    }
    
    private void mostrarReactivos(List<ReactivoDTO> reactivos) {
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();
        
        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for(ReactivoDTO reactivo : reactivos) {
            Object[] datos = new Object[6];
            
            datos[0] = false;
            datos[1] = reactivo.getId();
            datos[2] = reactivo.getNombre();
            datos[3] = reactivo.getFechaCreacion();
            datos[4] = reactivo.getFechaModificacion();
            if(reactivo.getAutor() != null) {
                datos[5] = reactivo.getAutor().getUsuario();
            }
            else {
                datos[5] = "Sin autor";
            }
            
            model.addRow(datos);
        }
        
    }
    
    private List<Integer> getSelectedRows() {
        List<Integer> selectedIndexes = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();
        
        for(int i = 0; i < model.getRowCount(); i++) {
            if((boolean)model.getValueAt(i, 0) == true) {
                selectedIndexes.add(i);
            }
        }
        return selectedIndexes;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblReactivos = new javax.swing.JTable();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        lblTema = new javax.swing.JLabel();
        cmbTema = new javax.swing.JComboBox();
        lblReactivos = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(790, 579));

        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(790, 579));

        tblReactivos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblReactivos.setModel(new javax.swing.table.DefaultTableModel(
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
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblReactivos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblReactivos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblReactivos);
        if (tblReactivos.getColumnModel().getColumnCount() > 0) {
            tblReactivos.getColumnModel().getColumn(0).setResizable(false);
            tblReactivos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblReactivos.getColumnModel().getColumn(1).setResizable(false);
            tblReactivos.getColumnModel().getColumn(1).setPreferredWidth(20);
            tblReactivos.getColumnModel().getColumn(2).setResizable(false);
            tblReactivos.getColumnModel().getColumn(3).setResizable(false);
            tblReactivos.getColumnModel().getColumn(4).setResizable(false);
            tblReactivos.getColumnModel().getColumn(5).setResizable(false);
        }

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("Selección de un curso existente");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTema.setText("Tema:");

        cmbTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTema.setToolTipText("Selección del tema al que pertenecen los reactivos a buscar");
        cmbTema.setPreferredSize(new java.awt.Dimension(78, 25));

        lblReactivos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReactivos.setText("Reactivos:");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Consultar Reactivos");

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaModificar(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarReactivos(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarReactivos(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(359, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(434, 434, 434)
                            .addComponent(lblReactivos)
                            .addGap(245, 245, 245))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lblCurso)
                                .addComponent(lblTema)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbCurso, 0, 156, Short.MAX_VALUE)
                                .addComponent(cmbTema, 0, 156, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(110, 110, 110)
                                    .addComponent(lblTitulo)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(29, 29, 29)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(456, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(lblTitulo)
                    .addGap(33, 33, 33)
                    .addComponent(lblReactivos)
                    .addGap(18, 18, 18)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(lblCurso)
                            .addGap(7, 7, 7)
                            .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(53, 53, 53)
                            .addComponent(lblTema)
                            .addGap(18, 18, 18)
                            .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(47, 47, 47)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar
        // TODO add your handling code here:
        if(tblReactivos.getSelectedRow() != -1) {
            int indexReactivo = tblReactivos.getSelectedRow();
            String autorReactivo = (String) tblReactivos.getValueAt(tblReactivos
                    .getSelectedRow(), 5);
            UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
            
            if((usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro && 
                    autorReactivo.equals(usuarioActual.getUsuario())) ||
                    (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin)) {
                ReactivoDTO reactivo = controlVista
                        .obtenerReactivo(indexReactivo);
                
                if(reactivo != null) {
                    padre.mostrarVistaConEntidad(reactivo,
                            Vista.ModificarReactivo);
                }
                else {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "No cuentas con los permisos "
                        + "para realizar esta acción");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona primero un reactivo");
        }
    }//GEN-LAST:event_pasarControlVistaModificar

    private void buscarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarReactivos
        // TODO add your handling code here:
        consultarReactivos();
    }//GEN-LAST:event_buscarReactivos

    private void eliminarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarReactivos
        // TODO add your handling code here:
        List<Integer> indexesReactivos = getSelectedRows();
        
        if(indexesReactivos.size() > 0) {
            int q = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar el(los) reactivo(s) seleccionado(s)?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (q != 0) {
                return;
            }
            
            boolean ok = controlVista.eliminarReactivos(indexesReactivos);
            
            if(ok) {
                JOptionPane.showMessageDialog(this, "Reactivos Eliminados");
                //Ordena los indices alrrevez para eliminar las filas selecc.
                Collections.sort(indexesReactivos, Collections.reverseOrder());
                
                for(int index : indexesReactivos) {
                    ((DefaultTableModel)tblReactivos.getModel())
                            .removeRow(index);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "No se pudieron eliminar "
                        + "los reactivos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
        }
    }//GEN-LAST:event_eliminarReactivos

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblReactivos;
    private javax.swing.JLabel lblTema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblReactivos;
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
        //Mostrar datos
        ReactivoDTO reactivo = (ReactivoDTO) entidad;
        int row = tblReactivos.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();
        
        model.setValueAt(reactivo.getNombre(), row, 2);
        model.setValueAt(reactivo.getFechaModificacion(), row, 4);
        
    }

    @Override
    public boolean confirmarCambio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        //Limpiar datos
        cmbCurso.removeAllItems();
        cmbTema.removeAllItems();
        ((DefaultTableModel)tblReactivos.getModel()).setRowCount(0);
        controlVista.liberarMemoriaConsultar();
    }
    
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if(isShowing()) {
            noSelect = true;
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //
    }
}
