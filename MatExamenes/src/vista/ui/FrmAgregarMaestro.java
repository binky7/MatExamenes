/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceGrupo;

/**
 * JPanel que mostrará la interfaz gráfica de Agregar Maestros a un grupo.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class FrmAgregarMaestro extends javax.swing.JFrame {

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas, En ese
     * caso es utilizada para comunicarse con el JFrame Principal.
     */
    private InterfaceGrupo padre;

    /**
     * Controlador de la vista del caso de uso mantener grupos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores.
     */
    private CVMantenerGrupos controladorVista;

    /**
     * Lista de maestros utilizada para guardar temporalmente los maestros que
     * se manejan en la vista.
     */
    private List<UsuarioDTO> listaMaestros;

    /**
     * Lista de cursos utilizada para guardar temporalmente los cursos que se
     * manejan en la vista.
     */
    private List<CursoDTO> listaCursos;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton para agregar maestros.
    */
    private javax.swing.JButton btnAgregar;
    /**
    * Boton para la busqueda.
    */
    private javax.swing.JButton btnBuscar;
    /**
    * Boton para cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Panel para la vista agregar maestros.
    */
    private javax.swing.JPanel jPanel1;
    /**
    * Scrollpane para la tabla de maestros.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Srcollpane para la tabla de cursos.
    */
    private javax.swing.JScrollPane jScrollPane2;
    /**
    * Label para la busqueda.
    */
    private javax.swing.JLabel lblBusqueda;
    /**
    * Label para la tabla cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label para la tabla maestros.
    */
    private javax.swing.JLabel lblMaestros;
    /**
    * Label para el titulo de la vista.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Tabla para mostrar los cursos.
    */
    private javax.swing.JTable tblCursos;
    /**
    * Tabla para mostrar los maestros.
    */
    private javax.swing.JTable tblMaestros;
    /**
    * Textfield para la busqueda.
    */
    private javax.swing.JTextField txtfBusqueda;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmAgregarMaestros e inicializa todos sus atributos.
     */
    public FrmAgregarMaestro() {
        controladorVista = new CVMantenerGrupos();
        initComponents();
        this.setTitle("MatExamenes/Agregar Alumnos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listaMaestros = new ArrayList<>();
    }

    /**
     * Inicializa atributos necesarios para la vista y agrega los listeners
     * necesarios.
     *
     * @param controladorVista el controlador de vista.
     * @param padre el padre de la vista.
     */
    void inicializar(CVMantenerGrupos controladorVista, InterfaceGrupo padre) {
        this.setVisible(true);
        this.controladorVista = controladorVista;
        this.padre = padre;
        tblMaestros.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (tblMaestros.getSelectedRow() != -1) {
                    listaCursos = controladorVista.obtenerCursos(tblMaestros.getSelectedRow());
                    ((DefaultTableModel) tblCursos.getModel()).setRowCount(0);
                    if (listaCursos.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No hay cursos disponibles "
                                + "para este maestro.", "Advertencia", 2);
                    } else {
                        for (CursoDTO listaCurso : listaCursos) {
                            Object[] fila = new Object[2];
                            fila[0] = false;
                            fila[1] = listaCurso.getNombre();
                            ((DefaultTableModel) tblCursos.getModel()).addRow(fila);
                        }
                    }
                }
            }
        });
    }

    /**
     * Limpia los componentes de la vista.
     */
    private void limpiar() {
        txtfBusqueda.setText("");
        ((DefaultTableModel) tblCursos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblMaestros.getModel()).setRowCount(0);
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        txtfBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        lblCursos = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblMaestros = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        lblBusqueda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Agregar Maestro");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonBuscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarMaestros(evt);
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
                AgregarMaestros(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
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
        jScrollPane2.setViewportView(tblCursos);

        lblBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBusqueda.setText("Busqueda:");

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
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblCursos)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(lblTitulo))
                            .addComponent(lblMaestros)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(lblBusqueda)
                                .addGap(18, 18, 18)
                                .addComponent(txtfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
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
                    .addComponent(txtfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(lblBusqueda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblMaestros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    /**
     * Llama a limpiar para que la vista de limpie y cierra la vista.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        limpiar();
        this.setVisible(false);
    }//GEN-LAST:event_Cancelar

    /**
     * Obtiene los indices del maestro y cursos seleccionados y los obtiene de
     * las listas, los al controlador de la vista para que los agregue al grupo.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AgregarMaestros(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarMaestros
        int indexMaestro = tblMaestros.getSelectedRow();
        if (indexMaestro == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un maestro",
                    "Advertencia", 2);
        } else {
            List<Integer> indexesCursos = new ArrayList<>();
            int cont = tblCursos.getRowCount();
            for (int i = 0; i < cont; i++) {
                if (tblCursos.getValueAt(i, 0).equals(true)) {
                    indexesCursos.add(i);
                }
            }
            if (indexesCursos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debes seleccionar al menos"
                        + " un curso.", "Advertencia", 2);
            } else {
                Cancelar(evt);
                HashMap<CursoDTO, UsuarioDTO> mapa = controladorVista.
                        agregarMaestro(indexesCursos, indexMaestro);
                padre.mostrarMaestros(mapa);
            }
        }
    }//GEN-LAST:event_AgregarMaestros

    /**
     * Envia el crtiterio de busqueda ingresado y lo envia al controlador de la
     * vista para que obtenga los maestros que coincidan con la busqueda, los
     * los muestra en la tabla.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void BuscarMaestros(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarMaestros
        listaMaestros = controladorVista.obtenerMaestros(txtfBusqueda.getText());
        DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
        for (int x = model.getRowCount() - 1; x > -1; x--) {
            model.removeRow(x);
        }
        if (listaMaestros == null || listaMaestros.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron maestros.", "Advertencia", 2);
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
    }//GEN-LAST:event_BuscarMaestros

}
