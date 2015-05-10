/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceGrupo;

/**
 *
 * @author FernandoEnrique
 */
public class FrmAgregarMaestro extends javax.swing.JFrame {

    private InterfaceGrupo padre;
    private CVMantenerGrupos controladorVista;
    private List<UsuarioDTO> listaMaestros;
    private List<CursoDTO> listaCursos;

    /**
     * Creates new form FrmAgregarMaestro
     */
    public FrmAgregarMaestro() {
        controladorVista = new CVMantenerGrupos();
        initComponents();
        this.setTitle("MatExamenes/Agregar Alumnos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cbBusqueda.setSelectedIndex(-1);
    }

    private void limpiar() {
        txtBusqueda.setText("");
        DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }
        tblMaestros.setModel(model);
        DefaultTableModel modelo = (DefaultTableModel) tblCursos.getModel();
        for (int i = model.getRowCount() - 1; i > -1; i--) {
            model.removeRow(i);
        }
        tblCursos.setModel(modelo);
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
        lblTitulo = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        lblCursos = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMaestros = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        cbBusqueda = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Agregar Maestro");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonBuscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Apellido Paterno", "Apellido Materno", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblMaestros.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tblMaestros);

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos:");

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblMaestros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMaestros.setText("Maestros:");

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[X]", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblCursos);

        cbBusqueda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Apellido paterno", "Apellido materno", "Nombre" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(263, 263, 263)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(86, 86, 86)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCursos)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(lblTitulo))
                            .addComponent(lblMaestros)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(cbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblMaestros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnCancelar))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        int indexMaestro = tblMaestros.getSelectedRow();
        if (indexMaestro == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un maestro", "Advertencia", 1);
        } else {
            List<Integer> indexesCursos = new ArrayList<>();
            int cont = tblCursos.getRowCount();
            for (int i = 0; i < cont; i++) {
                if (tblCursos.getValueAt(i, 0).equals(true)) {
                    indexesCursos.add(i);
                }
            }
            if (indexesCursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Selecciona al menos un curso", "Advertencia", 1);
            } else {
                btnCancelarActionPerformed(evt);
                HashMap<CursoDTO, UsuarioDTO> mapa = controladorVista.agregarMaestro(indexesCursos, indexMaestro);
                padre.mostrarMaestros(mapa);
            }
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        int tipoBusqueda = cbBusqueda.getSelectedIndex();
        if (tipoBusqueda != -1) {
            if (cbBusqueda.getSelectedIndex() == 0) {
                listaMaestros = controladorVista.obtenerMaestrosPorApellido(txtBusqueda.getText(), Tipo.Maestro);
            }
            if (cbBusqueda.getSelectedIndex() == 1) {
                listaMaestros = controladorVista.obtenerMaestrosPorApellidoM(txtBusqueda.getText(), Tipo.Maestro);
            }
            if (cbBusqueda.getSelectedIndex() == 2) {
                listaMaestros = controladorVista.obtenerMaestrosPorNombre(txtBusqueda.getText(), Tipo.Maestro);
            }
            DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
            for (int x = model.getRowCount() - 1; x > -1; x--) {
                model.removeRow(x);
            }
            if (listaMaestros == null || listaMaestros.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No se encontraron maestros!", "Mensaje", 1);
            } else {
                for (UsuarioDTO maestro : listaMaestros) {
                    Object[] fila = new Object[4];
                    fila[0] = String.valueOf(maestro.getId());
                    fila[1] = maestro.getApellidoPaterno();
                    fila[2] = maestro.getApellidoMaterno();
                    fila[3] = maestro.getNombre();
                    model.addRow(fila);
                }
                tblMaestros.setModel(model);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un criterio de "
                    + "busqueda", "Mensaje", 1);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cbBusqueda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblMaestros;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTable tblMaestros;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
    void inicializar(CVMantenerGrupos controladorVista, InterfaceGrupo padre) {
        this.setVisible(true);
        this.controladorVista = controladorVista;
        this.padre = padre;
        tblMaestros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblMaestros.getSelectedRow() != -1) {
                    listaCursos = controladorVista.obtenerCursos(tblMaestros.getSelectedRow());
                    DefaultTableModel modelo = (DefaultTableModel) tblCursos.getModel();
                    for (int i = modelo.getRowCount() - 1; i > -1; i--) {
                        modelo.removeRow(i);
                    }
                    if (listaCursos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay cursos disponibles "
                                + "para este maestro!", "Advertencia", 1);
                    } else {
                        for (CursoDTO listaCurso : listaCursos) {
                            Object[] fila = new Object[2];
                            fila[0] = false;
                            fila[1] = listaCurso.getNombre();
                            modelo.addRow(fila);
                        }
                        tblCursos.setModel(modelo);
                    }
                }
            }
        });
    }
}
