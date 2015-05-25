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

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import vista.controlador.CVMantenerExamenes;
import vista.interfaz.InterfaceExamen;

/**
 * JFrame que mostrará la interfaz gráfica para Agregar Reactivos
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class FrmAgregarReactivos extends javax.swing.JFrame {

    /**
     * Controlador de la vista del caso de uso mantener exámenes, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerExamenes controlVista;
    /**
     * Interface de comunicación para enviar mensajes hacia la vista padre del
     * frame agregar reactivos para enviar los reactivos agregados
     */
    private InterfaceExamen padre;
    /**
     * índice del curso que representa el objeto CursoDTO almacenado en el
     * controlVista y que está ordenado de igual manera en la vista de Mantener
     * Exámenes. Este curso es usado para buscar los temas a mostrar en la vista
     */
    private int indexCurso;
    /**
     * Este número representa la clave a la que se agregarán los reactivos que
     * se hayan seleccionado en la vista
     */
    private int clave;

    /**
     * Frame para mostrar los detalles de un reactivo
     */
    private final FrmVerReactivo frmVerReactivo;

    /**
     * Bandera que sirve para que el evento de cambio no se dispare en los
     * combobox al ingresar nuevos datos, para evitar que el sistema tenga
     * resultados inesperados
     */
    private boolean noSelect;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Botón usado para aceptar el agregar reactivos
     */
    private javax.swing.JButton btnAceptar;
    /**
     * Botón usado para agregar una selección
     */
    private javax.swing.JButton btnAgregarSeleccion;
    /**
     * Botón usado para cancelar el agregar reactivos
     */
    private javax.swing.JButton btnCancelar;
    /**
     * Botón usado para remover una selección
     */
    private javax.swing.JButton btnRemoverSeleccion;
    /**
     * Botón usado para ver un reactivo
     */
    private javax.swing.JButton btnVer;
    /**
     * Panel de la pestaña de selección manual
     */
    private javax.swing.JPanel jPanel3;
    /**
     * Panel de la pestaña de selección automática
     */
    private javax.swing.JPanel jPanel4;
    /**
     * Scroll pane para mostrar tblReactivos
     */
    private javax.swing.JScrollPane jScrollPane3;
    /**
     * Scroll pane para mostrar lstTemasManual
     */
    private javax.swing.JScrollPane jScrollPane4;
    /**
     * Scroll pane para mostrar lstTemasAuto
     */
    private javax.swing.JScrollPane jScrollPane7;
    /**
     * Scroll pane para mostrar tblSeleccion
     */
    private javax.swing.JScrollPane jScrollPane8;
    /**
     * Label para spnCantidad
     */
    private javax.swing.JLabel lblCantidad;
    /**
     * Label para tblReactivos
     */
    private javax.swing.JLabel lblReactivos;
    /**
     * Label para tblSeleccion
     */
    private javax.swing.JLabel lblSeleccion;
    /**
     * Label para lstTemasAuto
     */
    private javax.swing.JLabel lblTemasAuto;
    /**
     * Label para lstTemasManual
     */
    private javax.swing.JLabel lblTemasManual;
    /**
     * Label para mostrar el total de reactivos por tema
     */
    private javax.swing.JLabel lblTotal;
    /**
     * Label para mostrar la leyenda de total de reactivos por tema
     */
    private javax.swing.JLabel lblTotalReactivos;
    /**
     * List para mostrar los temas en la selección aleatoria.
     */
    private javax.swing.JList lstTemasAuto;
    /**
     * List para mostrar los temas en la selección manual
     */
    private javax.swing.JList lstTemasManual;
    /**
     * Spinner para seleccionar la cantidad
     */
    private javax.swing.JSpinner spnCantidad;
    /**
     * Table para mostrar los reactivos
     */
    private javax.swing.JTable tblReactivos;
    /**
     * Table para mostrar las selecciones
     */
    private javax.swing.JTable tblSeleccion;
    /**
     * Tabbed pane que muestra las dos tipos de selecciones de reactivos
     */
    private javax.swing.JTabbedPane tbpSeleccion;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmAgregarReactivos e inicializa sus atributos, inicializa
     * el frame para ver reactivo y agrega los listeners necesarios
     */
    public FrmAgregarReactivos() {
        initComponents();

        frmVerReactivo = new FrmVerReactivo();
        frmVerReactivo.setPadre(this);
        frmVerReactivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.setTitle("Agregar Reactivos");

        lstTemasAuto.setModel(new DefaultListModel());
        lstTemasManual.setModel(new DefaultListModel());

        initListeners();
    }

    /**
     * Método para inicializar todos los listeners necesarios por la vista para
     * funcionar
     */
    private void initListeners() {

        lstTemasManual.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Para saber si se hizo click a la lista
                if (!e.getValueIsAdjusting()) {
                    if (!noSelect) {
                        //consultar reactivos
                        consultarReactivos();
                    }
                }
            }

        });

        lstTemasAuto.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Para saber si se hizo click a la lista
                if (!e.getValueIsAdjusting()) {
                    //Mostrar total de reactivos por tema
                    if (lstTemasAuto.getSelectedValue() != null) {
                        int total = controlVista.obtenerTotalReactivos(lstTemasAuto
                                .getSelectedValue().toString());

                        lblTotal.setText(String.valueOf(total));
                    }
                }
            }

        });

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                ((JFrame) padre.getPadre()).setEnabled(true);
                limpiar();
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    /**
     * Este método sirve para mostrar el frame inicialmente con la información
     * de los temas en base al índice del curso ingresado, la clave se guarda
     * para utilizarse en métodos futuros.
     *
     * @param indexCurso el índice del curso que representa un objeto CursoDTO
     * en el controlVista
     * @param clave el índice que representa un objeto ClaveExamenDTO en el
     * controlVista
     */
    public void inicializar(int indexCurso, int clave) {
        //Deshabilita el padre para que no pueda hacer nada hasta que finalice
        //el control en este frame
        ((JFrame) padre.getPadre()).setEnabled(false);
        this.indexCurso = indexCurso;
        this.clave = clave;
        setVisible(true);
        consultarTemas();
    }

    /**
     * Este método es utilizado para mostrar una lista de temas en el componente
     * list de la vista para mostrar los temas disponibles.
     *
     * @param temas una lista de temas TemaDTO a ser mostrada en la lista de la
     * vista
     */
    private void mostrarTemas(List<TemaDTO> temas, JList lista) {

        DefaultListModel listModel = (DefaultListModel) lista.getModel();

        listModel.clear();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for (TemaDTO tema : temas) {
            listModel.addElement(tema.getNombre());
        }
        noSelect = false;
    }

    /**
     * Este método sirve para mostrar el objeto ReactivoDTO ingresado en el
     * frame Ver Reactivo. Este método deshabilita la vista y muestra en un
     * frame nuevo los datos del reactivo.
     *
     * @param reactivo el objeto ReactivoDTO que se desea mostrar.
     */
    private void mostrarReactivo(ReactivoDTO reactivo) {
        frmVerReactivo.inicializar(reactivo);
    }

    /**
     * Este método es utilizado para consultar y mostrar los reactivos
     * disponibles en la base de datos en base al tema seleccionado, mediante la
     * utilización del controlVista. En caso de que no exista ningún reactivo
     * del tema seleccionado se mostrará un mensaje y se permitirá volver a
     * seleccionar otro tema
     */
    private void consultarReactivos() {
        List<ReactivoDTO> reactivos = controlVista
                .obtenerReactivosPorTema((String) lstTemasManual
                        .getSelectedValue(), clave);

        if (reactivos != null && !reactivos.isEmpty()) {
            mostrarReactivos(reactivos);
        } else {
            JOptionPane.showMessageDialog(this, "No hay reactivos");
            ((DefaultTableModel) tblReactivos.getModel()).setRowCount(0);
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de reactivos en la tabla
     * de la vista para mostrar los reactivos disponibles.
     *
     * @param reactivos una lista de reactivos ReactivoDTO a ser mostrada en la
     * tabla de la vista
     */
    private void mostrarReactivos(List<ReactivoDTO> reactivos) {
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();

        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ReactivoDTO reactivo : reactivos) {
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

            model.addRow(datos);
        }
    }

    /**
     * Este método sirve para consultar los temas pertenecientes al curso que
     * representa el índice del curso almacenado en este frame.
     */
    private void consultarTemas() {
        List<TemaDTO> temas;

        //Si el indexCurso == -1 significa que no se envió el curso porque era
        //en modificar examen
        if (indexCurso != -1) {
            temas = controlVista.obtenerTemasDeCurso(indexCurso);
        } else {
            temas = controlVista.obtenerTemasDeCurso();
        }

        //Si llegó algo mostrar los temas en las dos listas
        if (temas != null && !temas.isEmpty()) {
            mostrarTemas(temas, lstTemasManual);
            mostrarTemas(temas, lstTemasAuto);
        } else {
            JOptionPane.showMessageDialog(this, "No hay temas");
            ((JFrame) padre.getPadre()).setEnabled(true);
            dispose();
            limpiar();
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
     * Este método es utilizado para obtener reactivos aleatorios en base a las
     * selecciones hechas por el usuario. En caso de que no haya reactivos
     * suficientes se mostrará un mensaje. Si no se agregan los reactivos
     * seleccionados al examen y se muestran en la clave indicada
     */
    private void consultarReactivosAleatorios() {
        //Obtiene los reactivos aleatorios
        List<ReactivoDTO> reactivos = controlVista
                .obtenerReactivosAleatorios(clave);

        //Muestra los reactivos en la clave seleccionada, se limpia este frame
        //y se cierra
        if (reactivos != null) {
            padre.mostrarReactivos(reactivos, clave);
            ((JFrame) padre.getPadre()).setEnabled(true);
            limpiar();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No hay reactivos suficientes "
                    + "para los temas seleccionados");
        }
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
     * Almacena la interface del padre de este JFrame
     *
     * @param padre Interface para interactuar con el padre del JFrame.
     */
    public void setPadre(InterfaceExamen padre) {
        this.padre = padre;
    }

    /**
     * Este método es utilizado para limpiar todos los componentes de esta vista
     * y es llamado cuando la vista es cerrada. Sirve también para liberar la
     * memoria utilizada por el controlVista
     */
    public void limpiar() {

        noSelect = true;
        ((DefaultListModel) lstTemasAuto.getModel()).clear();
        ((DefaultListModel) lstTemasManual.getModel()).clear();
        ((DefaultTableModel) tblReactivos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblSeleccion.getModel()).setRowCount(0);
        spnCantidad.setValue(0);

        lblTotal.setText("");

        controlVista.liberarMemoriaAgregarReactivos();
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tbpSeleccion = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReactivos = new javax.swing.JTable();
        lblReactivos = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstTemasManual = new javax.swing.JList();
        lblTemasManual = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        spnCantidad = new javax.swing.JSpinner();
        lblTemasAuto = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstTemasAuto = new javax.swing.JList();
        lblCantidad = new javax.swing.JLabel();
        lblSeleccion = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSeleccion = new javax.swing.JTable();
        btnAgregarSeleccion = new javax.swing.JButton();
        btnRemoverSeleccion = new javax.swing.JButton();
        lblTotalReactivos = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setResizable(false);

        tbpSeleccion.setPreferredSize(new java.awt.Dimension(500, 300));

        tblReactivos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblReactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
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
        jScrollPane3.setViewportView(tblReactivos);
        if (tblReactivos.getColumnModel().getColumnCount() > 0) {
            tblReactivos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblReactivos.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        lblReactivos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReactivos.setText("Reactivos:");

        lstTemasManual.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstTemasManual.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTemasManual.setToolTipText("selecciona un tema para la búsqueda");
        jScrollPane7.setViewportView(lstTemasManual);

        lblTemasManual.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTemasManual.setText("Temas:");

        btnVer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ver24.png"))); // NOI18N
        btnVer.setToolTipText("ver reactivo");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verReactivo(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lblTemasManual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblReactivos)
                        .addGap(162, 162, 162))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblReactivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTemasManual))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        tbpSeleccion.addTab("Selección Manual", jPanel3);

        spnCantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spnCantidad.setToolTipText("cantidad de reactivos a seleccionar del tema");

        lblTemasAuto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTemasAuto.setText("Temas:");

        lstTemasAuto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstTemasAuto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTemasAuto.setToolTipText("tema a agregar");
        jScrollPane8.setViewportView(lstTemasAuto);

        lblCantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidad.setText("Cantidad:");

        lblSeleccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblSeleccion.setText("Selección:");

        tblSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tema", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSeleccion.setToolTipText("temas a agregar y cantidad de reactivos por tema a seleccionar");
        tblSeleccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSeleccion.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblSeleccion);
        if (tblSeleccion.getColumnModel().getColumnCount() > 0) {
            tblSeleccion.getColumnModel().getColumn(0).setResizable(false);
            tblSeleccion.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAgregarSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregarSeleccion.setText("Agregar Seleccion");
        btnAgregarSeleccion.setToolTipText("agrega una nueva selección del tema y cantidad seleccionados");
        btnAgregarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSeleccion(evt);
            }
        });

        btnRemoverSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverSeleccion.setText("Remover Seleccion");
        btnRemoverSeleccion.setToolTipText("Remueve la fila seleccionada de la tabla selecciones");
        btnRemoverSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerSeleccion(evt);
            }
        });

        lblTotalReactivos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTotalReactivos.setText("Total de Reactivos:");

        lblTotal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblTotal.setText(".");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(lblTemasAuto))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(lblCantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotalReactivos)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotal)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblSeleccion)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAgregarSeleccion)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoverSeleccion)
                                .addGap(47, 47, 47))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTemasAuto)
                    .addComponent(lblSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTotalReactivos)
                            .addComponent(lblTotal))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpSeleccion.addTab("Selección Aleatoria", jPanel4);

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAgregarReactivos(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar24.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setToolTipText("agregar los reactivos automática o manualmente al examen");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarAgregarReactivos(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(400, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(20, 20, 20))
            .addComponent(tbpSeleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbpSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método sirve para diferenciar entre agregar reactivos manualmente o
     * agregarlos aleatoriamente. Este método agrega los reactivos según la
     * opción seleccionada al controlVista y después los muestra en la vista del
     * padre de este frame. Este método es llamado al seleccionar la opción de
     * Aceptar
     *
     * @param evt el objeto ActionEvent generado por el evento
     */
    private void aceptarAgregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarAgregarReactivos

        if (tbpSeleccion.getSelectedIndex() == 0) {
            //Seleccion Manual
            //Obtener las filas seleccionadas
            List<Integer> indexesReactivo = getSelectedRows();
            if (indexesReactivo.size() > 0) {
                //Agregar los reactivos seleccionados si se seleccionó más de uno
                List<ReactivoDTO> reactivos = controlVista
                        .agregarReactivosSeleccionados(indexesReactivo, clave);

                //mostrar los reactivos agregados en la vista.
                padre.mostrarReactivos(reactivos, clave);
                ((JFrame) padre.getPadre()).setEnabled(true);
                limpiar();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona reactivos");
            }
        } else {
            //Seleccion Aleatoria
            if (tblSeleccion.getRowCount() > 0) {
                consultarReactivosAleatorios();
            } else {
                JOptionPane.showMessageDialog(this, "Agrega alguna selección");
            }
        }
    }//GEN-LAST:event_aceptarAgregarReactivos

    /**
     * Este método es llamado cuando se selecciona la opción de Cancelar, es
     * para pedir la confirmación antes de cerrar el frame y cancelar la
     * operación. En caso de que se acepte se cancela la operación y se regresa
     * al padre habilitado.
     *
     * @param evt el objeto ActionEvent que contiene la información del evento
     */
    private void cancelarAgregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAgregarReactivos

        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodo el progreso "
                + "se perderá", "Confirmación", JOptionPane.YES_NO_OPTION);
        
        if (ok == 0) {
            ((JFrame) padre.getPadre()).setEnabled(true);
            limpiar();
            dispose();
        }
    }//GEN-LAST:event_cancelarAgregarReactivos

    /**
     * Este método es llamado cuando se selecciona la opción Ver Reactivo, y es
     * utilizado para mostrar la información del reactivo seleccionado
     *
     * @param evt el objeto ActionEvent generado por el evento
     */
    private void verReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verReactivo
        // TODO add your handling code here:
        if (tblReactivos.getSelectedRow() != -1) {
            ReactivoDTO reactivo = controlVista.obtenerReactivo(tblReactivos
                    .getSelectedRow());

            mostrarReactivo(reactivo);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
        }
    }//GEN-LAST:event_verReactivo

    /**
     * Este método es llamado cuando se selecciona la opción de Agregar
     * Selección. Lo que hace este método es guardar la selección del tema y la
     * cantidad seleccionada en el controlVista al mismo tiempo que la muestra
     * en la tabla de selecciones en la pestaña de selección aleatoria en el
     * frame. Esto para llevar un control de las selecciones que hace el usuario
     * para una vez aceptado la operación se guarden todos los reactivos
     * seleccionados
     *
     * @param evt el objeto ActionEvent generado por el evento
     */
    private void agregarSeleccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSeleccion
        //Verifica que la lista tenga seleccionado un tema
        if (lstTemasAuto.getSelectedIndex() != -1) {
            DefaultListModel model = (DefaultListModel) lstTemasAuto.getModel();

            int cantidad = (int) spnCantidad.getValue();

            //Verifica que la cantidad seleccionada sea mayor a 0
            if (cantidad > 0) {
                DefaultTableModel tblModel = (DefaultTableModel) tblSeleccion
                        .getModel();

                String nombreTema = (String) model.remove(lstTemasAuto
                        .getSelectedIndex());
                //Se agrega la selección en el controlVista
                controlVista.agregarSeleccion(nombreTema, cantidad);
                Object[] seleccion = new Object[2];

                seleccion[0] = nombreTema;
                seleccion[1] = cantidad;

                //Se remueve el tema de la lista y se agrega a la tabla de
                //selecciones
                tblModel.addRow(seleccion);
            } else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una "
                        + "cantidad mayor a 0");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona primero un tema");
        }
    }//GEN-LAST:event_agregarSeleccion

    /**
     * Este método es llamado cuando se selecciona la opción de Remover
     * Selección. En ese momento el sistema remueve la fila seleccionada (tema y
     * cantidad) de la vista y también lo hace del controlVista, por lo que al
     * aceptar agregar reactivos esta selección no será tomada en cuenta a menos
     * que se vuelva a seleccionar de la lista de temas
     *
     * @param evt el objeto ActionEvent generado por el evento
     */
    private void removerSeleccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerSeleccion
        // Si se seleccionó una fila de la tabla
        if (tblSeleccion.getSelectedRow() != -1) {
            DefaultListModel model = (DefaultListModel) lstTemasAuto.getModel();
            DefaultTableModel tblModel = (DefaultTableModel) tblSeleccion
                    .getModel();

            String nombreTema = (String) tblModel.getValueAt(tblSeleccion
                    .getSelectedRow(), 0);

            //Remover la selección en el control
            controlVista.removerSeleccion(tblSeleccion.getSelectedRow());

            //Remover la fila y agregar el tema de nuevo a la lista de temas
            tblModel.removeRow(tblSeleccion.getSelectedRow());
            model.addElement(nombreTema);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona primero una fila");
        }
    }//GEN-LAST:event_removerSeleccion

}
