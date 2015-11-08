/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerExamenes;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Consultar Exámenes
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaConsultarExamenes extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    /**
     * Controlador de la vista del caso de uso mantener exámenes, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerExamenes controlVista;
    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas. En ese
     * caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Botón usado para buscar examenes
     */
    private javax.swing.JButton btnBuscar;
    /**
     * Botón usado para cancelar la operación
     */
    private javax.swing.JButton btnCancelar;
    /**
     * Botón usado para eliminar examenes
     */
    private javax.swing.JButton btnEliminar;
    /**
     * Botón usado para modificar examenes
     */
    private javax.swing.JButton btnModificar;
    /**
     * ComboBox usado para mostrar los cursos
     */
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JPanel jPanel1;
    /**
     * ScrollPane usado para la tabla de examenes
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label para el cmbCurso
     */
    private javax.swing.JLabel lblCurso;
    /**
     * Label para la tblExamenes
     */
    private javax.swing.JLabel lblExamenes;
    /**
     * Label para el txtfNombre
     */
    private javax.swing.JLabel lblNombre;
    /**
     * Label para el título de la interfaz gráfica.
     */
    private javax.swing.JLabel lblTitulo1;
    /**
     * Table utilizada para mostrar los examenes
     */
    private javax.swing.JTable tblExamenes;
    /**
     * campo de texto utilizado para ingresar el nombre
     */
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaConsultarExamenes e inicializa sus atributos
     */
    public VistaConsultarExamenes() {
        initComponents();

        addAncestorListener(this);
    }

    /**
     * Almacena la interface del JFrame principal.
     *
     * @param padre Interface para interactuar con el JFrame principal.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Almacena el control de la vista
     *
     * @param controlVista El objeto encargado de realizar comunicar la vista
     * con las capas inferiores para acceder a los datos
     */
    public void setControlador(CVMantenerExamenes controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Este método es utilizado para consultar y mostrar los cursos disponibles
     * en la base de datos, mediante la utilización del controlVista. En caso de
     * que no exista ningún curso se mostrará un mensaje y se regresará a la
     * vista principal.
     */
    private void consultarCursos() {
        //la lista de cursos obtenida desde la base de datos por el controlVista
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        //Si hay cursos...
        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        } else {
            //Si no hay mostrar un mensaje, regresar a la vista principal y
            //limpiar la vista actual
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de cursos en el
     * componente comboBox de la vista para mostrar los cursos disponibles.
     *
     * @param cursos una lista de cursos CursoDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        //Para limpiar el cmbCurso de información previa
        cmbCurso.removeAllItems();

        //Agrega una selección vacía
        cmbCurso.addItem("");

        //Recorrer todos los elementos de la lista para mostrarlos en el comboBox
        for (CursoDTO curso : cursos) {
            cmbCurso.addItem(curso.getNombre());
        }

        //Selecciona el combo box en la selección vacía
        cmbCurso.setSelectedIndex(0);
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        lblNombre = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        lblExamenes = new javax.swing.JLabel();
        lblTitulo1 = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(790, 467));

        jPanel1.setAutoscrolls(true);
        jPanel1.setPreferredSize(new java.awt.Dimension(790, 579));

        tblExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblExamenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("filtrar la búsqueda por curso");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre del Examen:");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setToolTipText("filtrar la búsqueda por nombre del examen");
        txtfNombre.setPreferredSize(new java.awt.Dimension(6, 30));

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarExamenes(evt);
            }
        });

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblExamenes.setText("Exámenes:");

        lblTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo1.setText("Consultar Exámenes");

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
                eliminarExamen(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCurso)
                .addGap(18, 18, 18)
                .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(281, 281, 281)
                            .addComponent(lblTitulo1))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(62, 62, 62)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(288, 288, 288)
                                        .addComponent(lblExamenes))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(224, 224, 224)
                                            .addComponent(lblNombre)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 399, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(lblTitulo1)
                    .addGap(32, 32, 32)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre)
                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(25, 25, 25)
                    .addComponent(lblExamenes)
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(150, Short.MAX_VALUE)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método es utilizado para consultar y mostrar los exámenes en base al
     * curso, nombre o curso y nombre seleccionado, mediante la utilización del
     * controlVista. En caso de que no exista ningún examen se mostrará un
     * mensaje y se permitirá volver a realizar otra selección.
     */
    private void consultarExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarExamenes
        //Para comprobar que se hizo una consulta
        boolean ok = true;
        UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
        List<ExamenDTO> examenes = null;
        //Para validar que se haya seleccionado un curso
        String seleccionado = (String) cmbCurso.getSelectedItem();
        //Obtener el valor de lo ingresado en el titulo
        String nombre = txtfNombre.getText();
        //El curso seleccionado si hubiera...
        String curso = "";

        //Parámetro para la cosulta, null si es administrador
        UsuarioDTO usuario = null;
        //Parámetro para la consulta, true si es administrador
        boolean todos = true;

        //Para diferenciar entre una consulta a los exámenes públicos o a todos
        if (usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro) {
            //Si usuario actual es maestro....
            usuario = usuarioActual;
            todos = false;
        }

        if (!seleccionado.equals("")) {
            curso = seleccionado;
        }

        if (!seleccionado.equals("") && !Validador.estaVacio(nombre)) {

            examenes = controlVista.obtenerExamenesPorCursoYNombre(curso, nombre,
                    todos, usuario);

        } else if (!seleccionado.equals("") && Validador.estaVacio(nombre)) {

            //Solo obtener los exámenes por curso
            examenes = controlVista.obtenerExamenesPorCurso(curso, todos, usuario);

        } else if (!Validador.estaVacio(nombre) && seleccionado.equals("")) {

            //Obtener exámenes por nombre
            examenes = controlVista.obtenerExamenesPorNombre(nombre, todos,
                    usuario);
        } else {
            //No selecciono curso ni nombre
            JOptionPane.showMessageDialog(this, "Seleccione por lo menos "
                    + "un curso o ingrese un nombre");
            ok = false;
        }

        if (ok) {
            if (examenes != null && !examenes.isEmpty()) {
                mostrarExamenes(examenes);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron resultados");
                ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
            }
        }

    }//GEN-LAST:event_consultarExamenes

    /**
     * Este método es utilizado para mostrar una lista de exámenes en el
     * componente table de la vista para mostrar los exámenes ingresados.
     *
     * @param examenes una lista de examenes ExamenDTO a ser mostrada en la
     * table de la vista
     */
    private void mostrarExamenes(List<ExamenDTO> examenes) {
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ExamenDTO examen : examenes) {
            Object[] datos = new Object[5];

            datos[0] = examen.getId();
            datos[1] = examen.getNombre();
            datos[2] = examen.getFechaCreacion();
            datos[3] = examen.getFechaModificacion();
            if (examen.getAutor() != null) {
                datos[4] = examen.getAutor().getUsuario();
            } else {
                datos[4] = "Sin autor";
            }

            model.addRow(datos);
        }
    }

    /**
     * Este método sirve para pasar el control a la Vista Modificar
     * correspondiente al mismo caso de uso al que pertence esta vista. Se manda
     * llamar al seleccionar la opción Modificar en la vista.
     *
     * @param evt un objeto de tipo ActionEvent generado al ocurrir el evento
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar
        //Si hay una fila seleccionada en tblExamenes
        if (tblExamenes.getSelectedRow() != -1) {
            int indexExamen = tblExamenes.getSelectedRow();
            //Obtener el autor del examen de la tabla
            String autorExamen = (String) tblExamenes.getValueAt(tblExamenes
                    .getSelectedRow(), 4);

            UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();

            //Si el usuario actual es maestro y es el autor del examen o si
            //el usuario actual es administrador modificar el examen
            if ((usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro
                    && autorExamen.equals(usuarioActual.getUsuario()))
                    || (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin)) {
                //Obtener el examen correspondiente del controlVista
                ExamenDTO examen = controlVista
                        .obtenerExamen(indexExamen);

                if (examen != null) {
                    //Llamar al padre para que muestre la vista Modificar Examen
                    //Enviándole el objeto examen a modificar
                    padre.mostrarVistaConEntidad(examen, Vista.ModificarExamen);
                } else {
                    //Este error no debería pasar, significa problemas en las
                    //capas inferiores
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No cuenta con los permisos "
                        + "para realizar esta acción");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione primero un examen");
        }
    }//GEN-LAST:event_pasarControlVistaModificar

    /**
     * Este método elimina el examen seleccionado en la tblExamenes mediante una
     * llamada al controlVista, pide un mensaje de confirmación antes de
     * eliminar. Es llamado al seleccionar la opción de Eliminar
     *
     * @param evt
     */
    private void eliminarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarExamen

        //si se seleccionó una fila mostrar la confirmación y eliminar el examen
        //en caso afirmativo
        if (tblExamenes.getSelectedRow() != -1) {
            //Obtener el autor del examen de la tabla
            String autorExamen = (String) tblExamenes.getValueAt(tblExamenes
                    .getSelectedRow(), 4);

            UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();

            //Si el usuario actual es maestro y es el autor del examen o si
            //el usuario actual es administrador modificar el examen
            if ((usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro
                    && autorExamen.equals(usuarioActual.getUsuario()))
                    || (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin)) {

                int q = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                        + "desea eliminar el examen seleccionado?",
                        "Confirmación", JOptionPane.YES_NO_OPTION);
                if (q != 0) {
                    return;
                }

                boolean ok = controlVista.eliminarExamen(tblExamenes
                        .getSelectedRow());

            //Si la eliminación se llevó correctamente mostrar el mensaje y
                //eliminar la fila de la tabla
                if (ok) {
                    //JOptionPane.showMessageDialog(this, "Examen eliminado");
                    ((DefaultTableModel) tblExamenes.getModel())
                            .removeRow(tblExamenes.getSelectedRow());
                } else {
                    //Mostrar el mensaje en caso de un error al eliminar
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar "
                            + "el examen", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No cuenta con los permisos "
                        + "para realizar esta acción");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione primero un examen");
        }
    }//GEN-LAST:event_eliminarExamen

    /**
     * Este método es llamado cuando se selecciona el botón de Cancelar, lo que
     * hace es pedir una confirmación de la operación mediante un mensaje, en
     * caso de que se acepte se vuelve a la vista principal.
     *
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Muestra la información de la entidad modificada en la tabla, este objeto
     * es enviado desde la Vista Consultar del caso de uso correspondiente a
     * esta vista
     *
     * @param entidad el objeto entidad que contiene la información a mostrar en
     * la vista después de ser modificada
     */
    @Override
    public void mostrarEntidad(Object entidad) {
        //Mostrar los datos del examen en la vista
        ExamenDTO examen = (ExamenDTO) entidad;

        int row = tblExamenes.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        model.setValueAt(examen.getNombre(), row, 1);
        model.setValueAt(examen.getFechaModificacion(), row, 3);
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
        //Limpiar componentes
        cmbCurso.removeAllItems();
        txtfNombre.setText("");
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);

        controlVista.liberarMemoriaConsultar();
    }

    /**
     * Este método es invocado cuando se muestra por primera vez esta vista
     *
     * @param event el objeto AncestorEvent generado por el evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
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
