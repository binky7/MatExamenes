/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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
 * JPanel que mostrará la interfaz gráfica de Consultar Reactivos
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaConsultarReactivos extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas. En ese
     * caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;
    /**
     * Controlador de la vista del caso de uso mantener reactivos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerReactivos controlVista;
    /**
     * Bandera que sirve para que el evento de cambio no se dispare en los
     * combobox al ingresar nuevos datos, para evitar que el sistema tenga
     * resultados innesperados
     */
    private boolean noSelect;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Botón usado para buscar reactivos
     */
    private javax.swing.JButton btnBuscar;
    /**
     * Botón usado para cancelar la operación
     */
    private javax.swing.JButton btnCancelar;
    /**
     * Botón usado para eliminar reactivos
     */
    private javax.swing.JButton btnEliminar;
    /**
     * Botón usado para modificar reactivos
     */
    private javax.swing.JButton btnModificar;
    /**
     * ComboBox usado para mostrar los cursos
     */
    private javax.swing.JComboBox cmbCurso;
    /**
     * ComboBox usado para mostrar los temas
     */
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JPanel jPanel1;
    /**
     * ScrollPane usado para la tabla de reactivos
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label para el cmbCurso
     */
    private javax.swing.JLabel lblCurso;
    /**
     * Label para la tblReactivos
     */
    private javax.swing.JLabel lblReactivos;
    /**
     * Label para el cmbTema
     */
    private javax.swing.JLabel lblTema;
    /**
     * Label para el título de la interfaz gráfica.
     */
    private javax.swing.JLabel lblTitulo;
    /**
     * Table utilizada para mostrar los reactivos
     */
    private javax.swing.JTable tblReactivos;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaConsultarReactivos e inicializa sus atributos, agrega
     * los listeners necesarios
     */
    public VistaConsultarReactivos() {
        initComponents();

        addAncestorListener(this);

        //Listener para el cmbCurso
        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!noSelect) {
                    //Una vez seleccionado un curso del cmbCurso se consultan
                    //los temas de ese curso
                    consultarTemasDeCurso();
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
     * Almacena el control de la vista
     *
     * @param controlVista El objeto encargado de realizar comunicar la vista
     * con las capas inferiores para acceder a los datos
     */
    public void setControlador(CVMantenerReactivos controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Método que sirve para deshabilitar el botón de eliminar reactivos de la
     * vista. Es utilizado para el momento en que se validan los privilegios del
     * tipo de usuario al momento del login
     */
    public void deshabilitarBtnEliminar() {
        btnEliminar.setEnabled(false);
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
     * Este método es utilizado para mostrar una lista de cursos en el componente
     * comboBox de la vista para mostrar los cursos disponibles.
     * @param cursos una lista de cursos CursoDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        //Para limpiar el cmbCurso de información previa
        cmbCurso.removeAllItems();
        
        //Recorrer todos los elementos de la lista para mostrarlos en el comboBox
        for(CursoDTO curso : cursos) {
            cmbCurso.addItem(curso.getNombre());
        }
        
        //Deselecciona el comboBox y permite la funcionalidad correcta del
        //listener del cmbCurso al igualar la bandera a falso
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }

    /**
     * Este método sirve para consultar los temas pertenecientes al curso
     * seleccionado en el cmbCurso. Al haber obtenido todos los cursos de la
     * base de datos anteriormente pero no tener los temas inicializados (lazy),
     * se requiere hacer una segunda llamada a la base de datos para obtener
     * los temas, por medio del controlVista.
     * @see consultarCursos
     */
    private void consultarTemasDeCurso() {
        //Si el cmbCurso tiene un curso seleccionado
        if (cmbCurso.getSelectedIndex() != -1) {
            //La lista de temas recibida del controlVista, la cual pertenece
            //al curso seleccionado en el cmbCurso
            List<TemaDTO> temas = controlVista.obtenerTemasDeCurso(cmbCurso
                    .getSelectedIndex());

            //Si la lista de temas no está vacía...
            if (temas != null && !temas.isEmpty()) {
                mostrarTemas(temas);
            } else {
                //Si está vacía mostrar un mensaje y limpiar cmbTema
                JOptionPane.showMessageDialog(this, "No hay temas");
                cmbTema.removeAllItems();
            }
        }
        else {
            //Si no hay selección de curso limpiar cmbTema
            cmbTema.removeAllItems();
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de temas en el componente
     * comboBox de la vista para mostrar los temas disponibles.
     * @param temas una lista de temas TemaDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarTemas(List<TemaDTO> temas) {
    
        //Limpia la información anterior
        cmbTema.removeAllItems();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for(TemaDTO tema : temas) {
            cmbTema.addItem(tema.getNombre());
        }
        
        cmbTema.setSelectedIndex(-1);
    }

    /**
     * Este método es utilizado para consultar y mostrar los reactivos
     * disponibles en la base de datos en base al tema seleccionado,
     * mediante la utilización del controlVista. En caso de que no exista ningún
     * reactivo del tema seleccionado se mostrará un mensaje y se permitirá volver
     * a seleccionar otro tema
     */
    private void consultarReactivos() {

        //Si se seleccionó un tema...
        if (cmbTema.getSelectedIndex() != -1) {
            //Lista de reactivos resultante de obtener los datos a través del
            //controlVista
            List<ReactivoDTO> reactivos = controlVista
                    .obtenerReactivosPorTema(cmbTema
                            .getSelectedItem().toString());

            //Si hay resultados mostrar los reactivos
            if (reactivos != null && !reactivos.isEmpty()) {
                mostrarReactivos(reactivos);
            } else {
                //Si no hay reactivos mostrar un mensaje y limpiar la tblReactivos
                JOptionPane.showMessageDialog(this, "No hay reactivos");
                ((DefaultTableModel) tblReactivos.getModel()).setRowCount(0);
            }
        } else {
            //Si no se seleccionó un tema primero mostrar un mensaje
            JOptionPane.showMessageDialog(this, "Selecciona un Tema primero");
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de reactivos en la tabla
     * de la vista para mostrar los reactivos disponibles.
     * @param reactivos una lista de reactivos ReactivoDTO a ser mostrada en la
     * tabla de la vista
     */
    private void mostrarReactivos(List<ReactivoDTO> reactivos) {
        //Se obtiene el modelo de la tabla para manipular sus datos
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();

        //Limpiar las filas anteriores
        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ReactivoDTO reactivo : reactivos) {
            //Arreglo de datos que será insertado como fila
            Object[] datos = new Object[6];

            datos[0] = false;
            datos[1] = reactivo.getId();
            datos[2] = reactivo.getNombre();
            datos[3] = reactivo.getFechaCreacion();
            datos[4] = reactivo.getFechaModificacion();
            if (reactivo.getAutor() != null) {
                datos[5] = reactivo.getAutor().getUsuario();
            } else {
                datos[5] = "Sin autor";
            }

            //Agregar la fila a la tabla
            model.addRow(datos);
        }

    }

    /**
     * Método utilizado para obtener todos los números de fila seleccionados de
     * la tabla en base a los checkboxs seleccionados en la primera columna
     * 
     * @return Una lista de enteros que almacena los números de fila o índices
     * seleccionados, en el mismo orden en los que se muestran en la tabla
     */
    private List<Integer> getSelectedRows() {
        List<Integer> selectedIndexes = new ArrayList<>();

        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();

        //Recorrer todas las filas de la tabla para verificar si el checkbox
        //está seleccionado o no.
        for (int i = 0; i < model.getRowCount(); i++) {
            if ((boolean) model.getValueAt(i, 0) == true) {
                selectedIndexes.add(i);
            }
        }
        return selectedIndexes;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
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
            tblReactivos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblReactivos.getColumnModel().getColumn(1).setPreferredWidth(20);
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método sirve para pasar el control a la Vista Modificar
     * correspondiente al mismo caso de uso al que pertence esta vista. Se manda
     * llamar al seleccionar la opción Modificar en la vista.
     * @param evt un objeto de tipo ActionEvent generado al ocurrir el evento
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar

        //Si hay una fila seleccionada en tblReactivos
        if (tblReactivos.getSelectedRow() != -1) {
            int indexReactivo = tblReactivos.getSelectedRow();
            //Obtener el autor del reactivo de la tabla
            String autorReactivo = (String) tblReactivos.getValueAt(tblReactivos
                    .getSelectedRow(), 5);
            UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();

            //Si el usuario actual es maestro y es el autor del reactivo o si
            //el usuario actual es administrador modificar el reactivo
            if ((usuarioActual.getTipo() == UsuarioDTO.Tipo.Maestro
                    && autorReactivo.equals(usuarioActual.getUsuario()))
                    || (usuarioActual.getTipo() == UsuarioDTO.Tipo.Admin)) {
                //Obtener el reactivo correspondiente del controlVista
                ReactivoDTO reactivo = controlVista
                        .obtenerReactivo(indexReactivo);

                if (reactivo != null) {
                    //Llamar al padre para que muestre la vista Modificar Reactivo
                    //Enviándole el objeto reactivo a modificar
                    padre.mostrarVistaConEntidad(reactivo,
                            Vista.ModificarReactivo);
                } else {
                    //Este error no debería pasar, significa problemas en las
                    //capas inferiores
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No cuentas con los permisos "
                        + "para realizar esta acción");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona primero un reactivo");
        }
    }//GEN-LAST:event_pasarControlVistaModificar

    /**
     * Este método llama a consultar reactivos y es invocado cuando se selecciona
     * la opción Buscar
     * @param evt objeto ActionEvent generado al ocurrir el evento
     */
    private void buscarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarReactivos

        consultarReactivos();
    }//GEN-LAST:event_buscarReactivos

    /**
     * Este método elimina los reactivos seleccionados en la tblReactivos mediante
     * una llamada al controlVista, pide un mensaje de confirmación antes de 
     * eliminar. Es llamado al seleccionar la opción de Eliminar
     * @param evt 
     */
    private void eliminarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarReactivos
        //Obtener las filas seleccionadas de la tabla
        List<Integer> indexesReactivos = getSelectedRows();

        //Si hay por lo menos una selección mostrar la confirmación y eliminar
        //los reactivos en caso afirmativo
        if (indexesReactivos.size() > 0) {
            int q = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar el(los) reactivo(s) seleccionado(s)?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);
            if (q != 0) {
                return;
            }

            boolean ok = controlVista.eliminarReactivos(indexesReactivos);

            //Si la eliminación se llevó correctamente mostrar el mensaje y
            //eliminar las filas de la tabla
            if (ok) {
                JOptionPane.showMessageDialog(this, "Reactivos Eliminados");
                //Ordena los indices alrrevez para eliminar las filas selecc.
                Collections.sort(indexesReactivos, Collections.reverseOrder());

                for (int index : indexesReactivos) {
                    ((DefaultTableModel) tblReactivos.getModel())
                            .removeRow(index);
                }
            } else {
                //Mostrar el mensaje en caso de un error al eliminar
                JOptionPane.showMessageDialog(this, "No se pudieron eliminar "
                        + "los reactivos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
        }
    }//GEN-LAST:event_eliminarReactivos

    /**
     * Este método es llamado cuando se selecciona el botón de Cancelar,
     * lo que hace es pedir una confirmación de la operación mediante un mensaje,
     * en caso de que se acepte se vuelve a la vista principal.
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
     * @param entidad el objeto entidad que contiene la información a mostrar
     * en la vista después de ser modificada
     */
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
        ((DefaultTableModel) tblReactivos.getModel()).setRowCount(0);
        controlVista.liberarMemoriaConsultar();
    }

    /**
     * Este método es invocado cuando se muestra por primera vez esta vista
     * 
     * @param event el objeto AncestorEvent generado por el evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        //Invocar consultar cursos al mostrarse esta vista
        if (isShowing()) {
            noSelect = true;
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
    }
}
