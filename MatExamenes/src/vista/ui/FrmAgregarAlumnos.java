/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
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
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceGrupo;

/**
 * JPanel que mostrará la interfaz gráfica de Agregar Alumnos a un grupo.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class FrmAgregarAlumnos extends javax.swing.JFrame {

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
     * Lista de alumnos utilizada para guardar temporalmente los alumnos que se
     * manejan en la vista.
     */
    private List<UsuarioDTO> listaAlumnos;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton para agregar.
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
    * Panel para la vista de agregar alumnos.
    */
    private javax.swing.JPanel jPanel1;
    /**
    * Scrollpane para la tabla de alumnos.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Label para la busqueda.
    */
    private javax.swing.JLabel lblBusqueda;
    /**
    * Label para el titulo de la vista.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Tabla para mostrar los alumnos.
    */
    private javax.swing.JTable tblAlumnos;
    /**
    * Textfield para la busqueda.
    */
    private javax.swing.JTextField txtfBusqueda;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmAgregarAlumnos e inicializa todos sus atributos.
     */
    public FrmAgregarAlumnos() {
        listaAlumnos = new ArrayList<>();
        controladorVista = new CVMantenerGrupos();
        initComponents();
        setVisible(false);
        this.setTitle("Agregar Alumnos");
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * Inicializa atributos necesarios para la vista.
     *
     * @param controladorVista el controlador de vista.
     * @param padre el padre de la vista.
     */
    void inicializar(CVMantenerGrupos controladorVista, InterfaceGrupo padre) {
        this.setVisible(true);
        this.controladorVista = controladorVista;
        this.padre = padre;
    }

    /**
     * Limpia los componentes de la vista.
     */
    private void limpiar() {
        txtfBusqueda.setText("");
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
    }

    /**
     * Asigna el padre que tendra la vista.
     *
     * @param padre Recibe un objeto de tipo InterfaceVista.
     */
    private void setPadre(InterfaceGrupo padre) {
        this.padre = padre;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        txtfBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblBusqueda = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Apellido Paterno", "Apellido Materno", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAlumnos);

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarAlumnos(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Agregar Alumnos");

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarAlumnos(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        lblBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBusqueda.setText("Busqueda:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblBusqueda)
                        .addGap(18, 18, 18)
                        .addComponent(txtfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(115, 115, 115)
                        .addComponent(btnBuscar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(232, 232, 232)
                        .addComponent(btnAgregar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lblTitulo)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblBusqueda))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtfBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar)
                    .addComponent(btnCancelar)))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Obtiene los indices de los alumnos seleccionados y los obtiene de la
     * lista, envia los alumnos al controlador de la vista para que los agregue
     * al grupo.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AgregarAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarAlumnos
        List<Integer> indexes = new ArrayList<>();
        int cont = tblAlumnos.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                indexes.add(i);
            }
        }
        if (cont == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar al menos un"
                    + " alumno.", "Advertencia", 2);
        } else {
            listaAlumnos = controladorVista.agregarAlumnos(indexes);
            padre.mostrarAlumnos(listaAlumnos);
            Cancelar(evt);
        }
    }//GEN-LAST:event_AgregarAlumnos

    /**
     * Envia el crtiterio de busqueda ingresado y lo envia al controlador de la
     * vista para que obtenga los alumnos que coincidan con la busqueda, los los
     * muestra en la tabla.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void BuscarAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarAlumnos
        listaAlumnos = controladorVista.obtenerAlumnos(txtfBusqueda.getText());
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
        if (listaAlumnos == null || listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron alumnos.",
                    "Advertencia", 2);
        } else {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                UsuarioDTO alumno = listaAlumnos.get(i);
                Object[] fila = new Object[5];
                fila[0] = false;
                fila[1] = String.valueOf(alumno.getId());
                fila[2] = alumno.getApellidoPaterno();
                fila[3] = alumno.getApellidoMaterno();
                fila[4] = alumno.getNombre();
                ((DefaultTableModel) tblAlumnos.getModel()).addRow(fila);
            }
        }
    }//GEN-LAST:event_BuscarAlumnos

    /**
     * Llama a limpiar para que la vista de limpie y cierra la vista.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        limpiar();
        this.setVisible(false);
    }//GEN-LAST:event_Cancelar

}
