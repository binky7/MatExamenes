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

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Consultar Grupo.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaConsultarGrupo extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    /**
     * Controlador de la vista del caso de uso mantener grupos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores.
     */
    private CVMantenerGrupos controladorVista;

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas, En ese
     * caso es utilizada para comunicarse con el JFrame Principal.
     */
    private InterfaceVista padre;

    /**
     * Lista de objetos de tipo GrupoDTO utilizada para mostrar la lista de
     * grupos disponibles.
     */
    private List<GrupoDTO> listaGrupos;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton para cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Boton para eliminar.
    */
    private javax.swing.JButton btnEliminar;
    /**
    * Boton para modificar.
    */
    private javax.swing.JButton btnModificar;
    /**
    * Scrollpane para la tabla de grupos.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Label para la tabla grupos.
    */
    private javax.swing.JLabel lblGrupos;
    /**
    * Label para el titulo de la vista.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Tabla para mostrar los grupos.
    */
    private javax.swing.JTable tblGrupos;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaConsultarGrupo e inicializa sus atributos, agrega los
     * listener necesarios.
     */
    public VistaConsultarGrupo() {
        initComponents();
        this.addAncestorListener(this);
    }

    /**
     * Asigna el controlador de la vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVMantenerGrupos.
     */
    public void setControlador(CVMantenerGrupos controlVista) {
        this.controladorVista = controlVista;
    }

    /**
     * Asigna el padre que tendra la vista.
     *
     * @param padre Recibe un objeto de tipo InterfaceVista.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Limpia los campos de la vista.
     */
    @Override
    public void limpiar() {
        ((DefaultTableModel) tblGrupos.getModel()).setRowCount(0);
        controladorVista.liberarMemoriaConsultar();
    }

    /**
     * Llama al controlador de vista para que obtenga todos los grupos.
     */
    private void consultarGrupos() {
        this.listaGrupos = controladorVista.obtenerGrupos();
        if (listaGrupos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay grupos disponibles.",
                    "Advertencia", 2);
            this.padre.mostrarVista(Vista.HOME);
            limpiar();
        } else {
            mostrarGrupos(this.listaGrupos);
        }
    }

    /**
     * Muestra los grupos en la tabla de grupos.
     *
     * @param listaGrupos Recibe la lista de los grupos.
     */
    private void mostrarGrupos(List<GrupoDTO> listaGrupos) {
        ((DefaultTableModel) tblGrupos.getModel()).setRowCount(0);
        for (GrupoDTO grupo : listaGrupos) {
            Object[] fila = new Object[3];
            fila[0] = grupo.getGrado();
            fila[1] = grupo.getNombre();
            fila[2] = grupo.getTurno();
            ((DefaultTableModel) tblGrupos.getModel()).addRow(fila);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblGrupos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Consultar Grupos");

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

        tblGrupos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grado", "Nombre", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblGrupos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblGrupos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblGrupos);

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarGrupo(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarGrupo(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cancelar(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(318, 318, 318)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(lblGrupos)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addComponent(lblGrupos)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Verifica que un grupo haya sido seleccionado, obtiene el grupo del
     * controlador de vista y lo envia a su padre para que lo muestre en la
     * vista de modificar.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void ModificarGrupo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarGrupo
        int index = tblGrupos.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un grupo",
                    "Advertencia", 1);
        } else {
            GrupoDTO grupo = controladorVista.obtenerGrupo(index);
            if (grupo != null) {
                padre.mostrarVistaConEntidad(grupo, Vista.ModificarGrupo);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error!",
                        "Advertencia", 1);
            }
        }
    }//GEN-LAST:event_ModificarGrupo

    /**
     * Verifica que un grupo haya sido seleccionado, muestra un mensaje de
     * confirmacion, obtiene el indice y lo envia al controlador de vista para
     * que lo elimine.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void EliminarGrupo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarGrupo
        int index = tblGrupos.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un grupo.",
                    "Advertencia", 2);
        } else {
            int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar este grupo?\nEste proceso es irreversible");
            if (ok == JOptionPane.YES_OPTION) {
                if (controladorVista.eliminarGrupo(index)) {
                    JOptionPane.showMessageDialog(this, "Grupo eliminado "
                            + "correctamente", "Exito", 1);
                    DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
                    modelo.removeRow(index);
                    tblGrupos.setModel(modelo);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el grupo!.",
                            "Advertencia", 3);
                }
            }
        }
    }//GEN-LAST:event_EliminarGrupo

    /**
     * Regresa a la vista principal y limpia la vista de consultar.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_Cancelar

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado.
    }

    @Override
    public void mostrarVista(Vista vista) {
        //No implementado.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        //No implementado.
    }

    /**
     * Muestra un mensaje de confirmacion cuando se quiere cambiar de vista.
     *
     * @return Retorna una variable de tipo boleana que indicara si, se desea
     * cambiar de vista o no.
     */
    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        //No implementado.
        return null;
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            consultarGrupos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado.
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado.
    }
}
