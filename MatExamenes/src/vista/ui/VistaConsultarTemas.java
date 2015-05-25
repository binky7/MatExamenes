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

    /**
     * Crea un objeto VistaConsultarTemas e inicializa sus atributos.
     */
    public VistaConsultarTemas() {
        initComponents();
        this.addAncestorListener(this);
        //Para manipular las listas
        lstCursos.setModel(new DefaultListModel());
        lstTemas.setModel(new DefaultListModel());

        //Solo seleccionar uno a la vez
        lstCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstTemas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Manejador de eventos para curso
        lstCursos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Para saber si se hizo click a la lista
                if (!e.getValueIsAdjusting()) {
                    consultarTemas();
                }
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
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        lblTemas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCursos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTemas = new javax.swing.JList();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Consultar Temas");

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos");

        lblTemas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTemas.setText("Temas");

        lstCursos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstCursos);
        lstCursos.getAccessibleContext().setAccessibleName("");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(lblCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTemas)
                .addGap(205, 205, 205))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTitulo)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCursos)
                    .addComponent(lblTemas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Elimina de la base de datos el tema seleccionado.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void eliminarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTema
        //Eliminar Tema
        //Si se selecciono algo
        boolean ok;
        if (lstTemas.getSelectedIndex() != -1) {
            int banEliminar = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                    + "desea eliminar el tema?", "Eliminación", JOptionPane.YES_NO_OPTION);
            if (banEliminar == 0) {
                ok = controlVista.eliminarTema(lstTemas.getSelectedIndex());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Tema eliminado.");
                    ((DefaultListModel) lstTemas.getModel())
                            .remove(lstTemas.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el tema.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
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
            TemaDTO tema = controlVista.obtenerTema(lstTemas.getSelectedIndex());

            if (tema != null) {
                //Mostrar la vista modificar tema enviandole el objeto tema
                padre.mostrarVistaConEntidad(tema, Vista.ModificarTema);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
                        "Error", JOptionPane.ERROR_MESSAGE);
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    * Lista para mostrar los cursos.
    */
    private javax.swing.JList lstCursos;
    /**
    * Lista para mostrar los temas de un curso.
    */
    private javax.swing.JList lstTemas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void limpiar() {
        //Limpiar las listas
        ((DefaultListModel) lstCursos.getModel()).clear();
        ((DefaultListModel) lstTemas.getModel()).clear();
        //Liberar memoria dto
        controlVista.liberarMemoriaConsultar();
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
     * Obtiene los cursos almacenados en la base de datos. Se llama
     * automaticamente después de seleccionar un curso.
     */
    private void consultarTemas() {

        List<TemaDTO> temas;

        if (lstCursos.getSelectedIndex() == -1) {
            //Evitar que ocurra algo al cambiar de panel
            return;
        }

        temas = controlVista.obtenerTemasDeCurso(lstCursos
                .getSelectedIndex());

        if (temas != null && !temas.isEmpty()) {
            mostrarTemas(temas);
        } else {
            JOptionPane.showMessageDialog(this, "El curso seleccionado no tiene temas.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            ((DefaultListModel) lstTemas.getModel()).clear();
        }
    }

    /**
     * Muestra los cursos en la vista.
     *
     * @param cursos Lista de cursos para mostrar en la vista.
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        DefaultListModel listModel = (DefaultListModel) lstCursos.getModel();
        DefaultListModel modeloTemas = (DefaultListModel) lstTemas.getModel();
        modeloTemas.clear();

        listModel.clear();
        //Mostrar cada curso, no remover, si no buscar por medio del for
        for (CursoDTO curso : cursos) {
            listModel.addElement(curso.getNombre());
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
