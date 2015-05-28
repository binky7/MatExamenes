/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerUsuarios;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 * Interfaz gráfica para consultar usuarios.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class VistaConsultarUsuarios extends javax.swing.JPanel implements InterfaceVista {

    /**
     * Controlador de la vista del caso de uso Mantener Usuarios, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerUsuarios cvMantenerUsuarios;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;
    /**
     * Modelo para cambiar los datos de la tabla.
     */
    private final DefaultTableModel DTM;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para la busqueda.
    */
    private javax.swing.JButton btnBuscar;
    /**
    * Botón para cancelar la consulta de usuarios.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para eliminar un usuario.
    */
    private javax.swing.JButton btnEliminar;
    /**
    * Botón para modificar un usuario.
    */
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Label utilizada para mostrar el texto de la busqueda.
    */
    private javax.swing.JLabel lblBusqueda;
    /**
    * Label utilizada para mostrar el título de la interfaz gráfic.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Tabla para mostrar los resultados de la busqueda.
    */
    private javax.swing.JTable tblUsuarios;
    /**
    * Campo de texto para ingresar parte del nombre a buscar.
    */
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea una nueva VistaConsultarUsuario e inicializa sus atributos.
     */
    public VistaConsultarUsuarios() {
        initComponents();
        DTM = (DefaultTableModel) tblUsuarios.getModel();
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
     * Alamacena el control de la vista.
     *
     * @param cvMantenerUsuarios El objeto encargado de realizar las
     * interacciones con la base de datos.
     */
    public void setControlador(CVMantenerUsuarios cvMantenerUsuarios) {
        this.cvMantenerUsuarios = cvMantenerUsuarios;
    }

    /**
     * Muesta los datos de los usuarios en la tabla.
     *
     * @param usuarios Lista de usuarios para mostrar en la tabla.
     */
    private void mostrarUsuariosTabla(List<UsuarioDTO> usuarios) {
        DTM.setRowCount(0);
        Object datos[] = new Object[5];

        for (UsuarioDTO usuario : usuarios) {
            datos[0] = usuario.getApellidoPaterno();
            datos[1] = usuario.getApellidoMaterno();
            datos[2] = usuario.getNombre();
            datos[3] = usuario.getUsuario();
            datos[4] = usuario.getTipo().toString();

            DTM.addRow(datos);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        lblBusqueda = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24_2.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarUsuarios(evt);
            }
        });

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido Paterno", "Apellido Materno", "Nombre", "Usuario", "Tipo Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblUsuarios);

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(81, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaModificar(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(79, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarUsuario(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Consultar Usuarios");

        lblBusqueda.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBusqueda.setText("Nombre o Apellidos: ");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setNextFocusableComponent(btnBuscar);
        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 30));
        txtfNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtfNombreKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfNombreKeyTyped(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarConsultaUsuarios(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(lblBusqueda)
                .addGap(27, 27, 27)
                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(138, 138, 138)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(319, 319, 319)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(lblBusqueda))
                            .addComponent(btnBuscar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(45, 45, 45)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón modificar.<br>
     * Obtiene el índice del objeto seleccionado de la lista, y asi pasarlo a la
     * vista de modificación.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar
        int i = tblUsuarios.getSelectedRow();
        if (i != -1) {
            UsuarioDTO usuario = cvMantenerUsuarios.getUsuariosBuscados().get(i);
            padre.mostrarVistaConEntidad(usuario, Vista.ModificarUsuario);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario primero", "Modificación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_pasarControlVistaModificar

    /**
     * Método llamado cuando se acciona el botón buscar.<br>
     * Obtiene el texto del campo de texto para proceder a su busqueda en al
     * base de datos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void buscarUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarUsuarios
        String apellido = txtfNombre.getText();
        boolean ok;
        if (Validador.estaVacio(apellido)) {
            int opcion = JOptionPane.showConfirmDialog(this, "Realizar una consulta con"
                    + " el campo vacio regresara todos los usuarios.\n¿Desea continuar?",
                    "Busqueda", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            ok = (opcion == JOptionPane.YES_OPTION);
        } else {
            ok = true;
        }
        if (ok) {
            try {
                List<UsuarioDTO> usuarios;
                usuarios
                        = cvMantenerUsuarios.obtenerUsuariosPorNombreOApellidos(apellido);
                if (usuarios.isEmpty()) {
                    DTM.setNumRows(0);
                    JOptionPane.showMessageDialog(this, "No se encontraron coincidencias",
                            "Busqueda", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    mostrarUsuariosTabla(usuarios);
                }
            } catch (NullPointerException e) {
                JOptionPane.showMessageDialog(this, "Probleas en conexión",
                            "Busqueda", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }//GEN-LAST:event_buscarUsuarios

    /**
     * Método llamado cuando se acciona el botón eliminar.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void eliminarUsuario(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarUsuario
        int i = tblUsuarios.getSelectedRow();
        UsuarioDTO usuario = cvMantenerUsuarios.getUsuariosBuscados().get(i);

        int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar al usuario: "
                + usuario.getUsuario() + "?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            boolean ok = cvMantenerUsuarios.eliminarUsuario(usuario);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado",
                        "Eliminar usuario", JOptionPane.INFORMATION_MESSAGE);
                btnBuscar.doClick();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario no eliminado",
                        "Eliminar usuario", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_eliminarUsuario

    /**
     * Método llamado cuando se teclea en el campo de texto.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void txtfNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNombreKeyTyped
        if (evt.getKeyChar() == '\n') {
            btnBuscar.doClick();
        }
        JTextField txt = (JTextField) evt.getSource();
        int longitud = Validador.LONGITUD_DATOS_USUARIO;
        if (!Validador.validarLongitud(longitud, txt.getText())) {
            evt.consume();
            Toolkit.getDefaultToolkit().beep();

        } else if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(longitud, txt.getText() + portapapeles)) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtfNombreKeyTyped

    /**
     * Método llamado cuando se presiona una tecla en el campo de texto.
     *
     * @param evt Objeto que contiene información sobre el evento.
     */
    private void txtfNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNombreKeyPressed
        JTextField txt = (JTextField) evt.getSource();
        int longitud = Validador.LONGITUD_DATOS_USUARIO;
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(longitud, txt.getText() + portapapeles)) {
                evt.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }//GEN-LAST:event_txtfNombreKeyPressed

    /**
     * Método llamado cuando se acciona el botón cancelar.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void cancelarConsultaUsuarios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarConsultaUsuarios
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_cancelarConsultaUsuarios

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        List<UsuarioDTO> usuarios = (List<UsuarioDTO>) entidad;
        mostrarUsuariosTabla(usuarios);
    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        return padre.obtenerUsuarioActual();
    }

    @Override
    public void limpiar() {
        DTM.setRowCount(0);
        txtfNombre.setText("");
    }

}
