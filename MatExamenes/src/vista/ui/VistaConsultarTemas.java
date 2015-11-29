/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.interfaz.InterfaceVista;
import vista.interfaz.InterfaceVista.Vista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaConsultarTemas extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    /**
     * Controlador de la vista del caso de uso mantener temas, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVMantenerTemas controlVista;

    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para buscar los temas.
    */
    private javax.swing.JButton btnBuscar;
    /**
    * Botón para cancelar la consulta de temas.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para eliminar un tema seleccionado.
    */
    private javax.swing.JButton btnEliminar;
    /**
    * Botón para modificar un tema seleccionado.
    */
    private javax.swing.JButton btnModificar;
    /**
    * ComboBox para mostrar los bloques.
    */
    private javax.swing.JComboBox cbBloques;
    /**
    * ComboBox para mostrar los cursos.
    */
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JScrollPane jScrollPane2;
    /**
    * Label para los bloques.
    */
    private javax.swing.JLabel lblBloques;
    /**
    * Label para los cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label para los temas.
    */
    private javax.swing.JLabel lblTemas;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Lista para mostrar los temas de un curso.
    */
    private javax.swing.JList lstTemas;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaConsultarTemas e inicializa sus atributos.
     */
    public VistaConsultarTemas() {
        initComponents();
        this.addAncestorListener(this);
        //Para manipular las listas
        lstTemas.setModel(new DefaultListModel());

        //Sólo seleccionar un tema de la lista a la vez
        lstTemas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Manejador de eventos para curso
        cbCursos.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                verificarCursoSeleccionado();
                cbBloques.setSelectedIndex(0);
            }
        });
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
     * Almacena el control de la vista.
     *
     * @param controlVista El objeto encargado de realizar las interacciones con
     * la base de datos.
     */
    public void setControlador(CVMantenerTemas controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Obtiene los cursos almacenados en la base de datos.
     *
     * @return Regresa verdadero si la consulta de cursos se realiza
     * exitósamente. Regresa falso si no se encuentran cursos en la base de
     * datos.
     */
    private boolean consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        boolean ok = false;

        if (cursos != null && !cursos.isEmpty()) {
            ok = true;
            mostrarCursos(cursos);
        }

        return ok;
    }

    /**
     * Muestra los cursos en la vista.
     *
     * @param cursos Lista de cursos para mostrar en la vista.
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        DefaultListModel modeloTemas = (DefaultListModel) lstTemas.getModel();
        modeloTemas.clear();

        //Mostrar cada curso, no remover, si no buscar por medio del for
        for (CursoDTO curso : cursos) {
            cbCursos.addItem(curso.getNombre());
        }
    }

    /**
     * Mostrar los temas que se consultaron en lstTemas
     *
     * @param temas los objetos tema a mostrar
     */
    private void mostrarTemas(List<TemaDTO> temas) {
        DefaultListModel listModel = (DefaultListModel) lstTemas.getModel();

        listModel.clear();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for (TemaDTO tema : temas) {
            listModel.addElement(tema.getNombre());
        }
    }

    /**
     * Verifica cuál curso se seleccionó y si el seleccionado fue "Otros"
     * desactiva el ComboBox de bloques ya que son temas que no están asignados
     * a ningún curso.
     */
    private void verificarCursoSeleccionado() {
        if (cbCursos.getItemCount() != 0) {
            String cursoSeleccionado = cbCursos.getSelectedItem().toString();
            if (cursoSeleccionado.compareToIgnoreCase("Otros") == 0) {
                //Si el curso seleccionado es "Otros" se inhabilita el ComboBox
                //de bloques.
                cbBloques.setEnabled(false);
            } else {
                if (!cbBloques.isEnabled()) {
                    //Si el curso seleccionado es diferente de "Otros" 
                    //se verifica si el ComboBox de bloques está inhabilitado
                    //para habilitarlo.
                    cbBloques.setEnabled(true);
                }
            }
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        lblTemas = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTemas = new javax.swing.JList();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        cbCursos = new javax.swing.JComboBox();
        lblBloques = new javax.swing.JLabel();
        cbBloques = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Consultar Temas");

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos:");

        lblTemas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTemas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTemas.setText("Temas:");

        lstTemas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstTemas);

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaModificar(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTema(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setPreferredSize(new java.awt.Dimension(155, 25));

        lblBloques.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBloques.setText("Bloques:");

        cbBloques.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBloques.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cbBloques.setPreferredSize(new java.awt.Dimension(80, 25));

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(99, 30));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarTemas(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCursos)
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBloques)
                    .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTemas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(651, 651, 651)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTitulo)
                .addGap(48, 48, 48)
                .addComponent(lblTemas)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCursos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(lblBloques)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Elimina de la base de datos el tema seleccionado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void eliminarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTema
        //Eliminar Tema
        //Verificar si se seleccionó un tema
        boolean ok;
        if (lstTemas.getSelectedIndex() != -1) {
            int indexTema = lstTemas.getSelectedIndex();

            //Se verifica si el tema seleccionado tiene reactivos asociados.
            //Si el tema no tiene reactivos se procede a solicitar la
            //confirmación del usuario para eliminar el tema.
            if (!controlVista.tieneRactivosAsociados(indexTema)) {
                int banEliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                        + "desea eliminar el tema?", "Eliminación", JOptionPane.YES_NO_OPTION);
                if (banEliminar == JOptionPane.YES_OPTION) {
                    ok = controlVista.eliminarTema(indexTema);
                    if (ok) {
                        ((DefaultListModel) lstTemas.getModel())
                                .remove(lstTemas.getSelectedIndex());
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar el tema.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                //El tema tiene reactivos asociados por lo que no se puede eliminar.
                JOptionPane.showMessageDialog(this, "El tema seleccionado no "
                        + "se puede eliminar debido a que tiene "
                        + "reactivos asociados.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tema.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_eliminarTema

    /**
     * Pasa a la vista modificar para modificar el tema seleccionado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar

        //Mostrar la vista modificar
        if (lstTemas.getSelectedIndex() != -1) {
            if (cbCursos.getItemCount() == 1) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                TemaDTO tema = controlVista.obtenerTema(lstTemas.getSelectedIndex());

                if (tema != null) {
                    //Mostrar la vista modificar tema enviandole el objeto tema
                    padre.mostrarVistaConEntidad(tema, Vista.ModificarTema);
                } else {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tema.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_pasarControlVistaModificar

    /**
     * Cancela la consulta de temas y regresa a la vista principal.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Obtiene los temas almacenados en la base de datos dependiendo del curso y
     * bloque que se haya seleccionado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void consultarTemas(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarTemas
        // TODO add your handling code here:
        List<TemaDTO> temas;
        boolean esTemasSinAsignar = false;

        //Si el curso seleccionado es "Otros", traer los temas sin asignar
        if (cbCursos.getSelectedItem().toString().compareToIgnoreCase("Otros") == 0) {
            temas = controlVista.obtenerTemasSinAsignar();
            esTemasSinAsignar = true;
        } else {
            int indexCurso = cbCursos.getSelectedIndex();
            int bloque = Integer.parseInt(cbBloques.getSelectedItem().toString());
            temas = controlVista.obtenerTemasDeCurso(indexCurso, bloque);
        }
        if (temas != null && !temas.isEmpty()) {
            mostrarTemas(temas);
        } else {
            String mensaje;

            if (esTemasSinAsignar) {
                mensaje = "No hay temas sin asignar.";
            } else {
                mensaje = "El bloque seleccionado no tiene temas";
            }
            JOptionPane.showMessageDialog(this, mensaje,
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            ((DefaultListModel) lstTemas.getModel()).clear();
        }
    }//GEN-LAST:event_consultarTemas

    @Override
    public void limpiar() {
        //Limpiar las listas
        cbCursos.removeAllItems();
        cbBloques.setSelectedIndex(0);
        ((DefaultListModel) lstTemas.getModel()).clear();
        //Liberar memoria dto
        controlVista.liberarMemoriaConsultar();
    }

    /**
     * Este método es llamado cada vez que se muestra esta vista en el frame
     * principal, sirve para realizar el método inicial al mostrar una vista,
     * como una consulta.
     *
     * @param event el objeto AncestorEvent que se obtiene del evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        limpiar();

        if (!consultarCursos()) {
//            JOptionPane.showMessageDialog(this, "No hay cursos registrados.",
//                    "Advertencia", JOptionPane.WARNING_MESSAGE);
//            padre.mostrarVista(Vista.HOME);
        }
        cbCursos.addItem("Otros");
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
    }

    @Override
    public void mostrarEntidad(Object entidad) {
    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
