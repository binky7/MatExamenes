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

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import modelo.dto.CursoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerCursos;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaConsultarCursos extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    /**
     * Controlador de la vista del caso de uso mantener cursos, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVMantenerCursos controlVista;

    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar la consulta de cursos.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para eliminar un curso.
    */
    private javax.swing.JButton btnEliminar;
    /**
    * Botón para modificar un curso.
    */
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane3;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Lista para mostrar los cursos.
    */
    private javax.swing.JList lstCursos;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaConsultarCursos e inicializa sus atributos.
     */
    public VistaConsultarCursos() {
        initComponents();
        this.addAncestorListener(this);

        lstCursos.setModel(new DefaultListModel());

        lstCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
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
    public void setControlador(CVMantenerCursos controlVista) {
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
        DefaultListModel listModelCursos
                = (DefaultListModel) lstCursos.getModel();
        listModelCursos.clear();

        for (CursoDTO curso : cursos) {
            listModelCursos.addElement(curso.getNombre());
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        lstCursos = new javax.swing.JList();
        lblTitulo = new javax.swing.JLabel();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lstCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstCursos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstCursos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lstCursos);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Consultar Cursos");

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
                eliminarCurso(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarConsultarCursos(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(661, 661, 661)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblTitulo)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Pasa a la vista modificar para modificar el curso seleccionado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar
        // TODO add your handling code here:
        if (!lstCursos.isSelectionEmpty()) {
            int indexCurso = lstCursos.getSelectedIndex();
            CursoDTO curso = controlVista.obtenerCurso(indexCurso);

            if (curso != null) {
                padre.mostrarVistaConEntidad(curso, Vista.ModificarCurso);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso.", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_pasarControlVistaModificar

    /**
     * Elimina el curso seleccionado de la base de datos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void eliminarCurso(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCurso
        // TODO add your handling code here:
        if (!lstCursos.isSelectionEmpty()) {
            int banEliminar = JOptionPane.showConfirmDialog(this, "Este curso "
                    + "podría tener temas asociados ¿Está seguro de que "
                    + "desea eliminarlo?", "Eliminación", JOptionPane.YES_NO_OPTION);
            if (banEliminar == 0) {
                int indexCurso = lstCursos.getSelectedIndex();
                boolean ok = controlVista.eliminarCurso(indexCurso);

                if (ok) {
                    ((DefaultListModel) lstCursos.getModel()).remove(indexCurso);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el "
                            + "curso.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_eliminarCurso

    /**
     * Cancela la consulta de cursos y regresa a la vista principal.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void cancelarConsultarCursos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarConsultarCursos
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_cancelarConsultarCursos


    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
    }

    /**
     * Muestra el curso con los nuevos datos que se modificaron.
     *
     * @param entidad Objeto de tipo CursoDTO a mostrar en la vista.
     */
    @Override
    public void mostrarEntidad(Object entidad) {
        int index = lstCursos.getSelectedIndex();
        String nombreCurso = ((CursoDTO) entidad).getNombre();

        ((DefaultListModel) lstCursos.getModel()).setElementAt(nombreCurso, index);
    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        ((DefaultListModel) lstCursos.getModel()).clear();
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
        if (!consultarCursos()) {
            limpiar();
            JOptionPane.showMessageDialog(this, "No hay cursos registrados.", 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            padre.mostrarVista(Vista.HOME);
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado
    }
}
