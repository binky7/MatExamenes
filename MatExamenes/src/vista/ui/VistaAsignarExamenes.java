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

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.NumberFormatter;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.ReactivoAsignadoPK;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVAsignarExamenes;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaAsignarExamenes extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    /**
     * Controlador de la vista del caso de uso asignar exámenes, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVAsignarExamenes controlVista;

    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    /**
     * Crea un objeto VistaAsignarExamenes e inicializa sus atributos.
     */
    public VistaAsignarExamenes() {
        initComponents();
        this.addAncestorListener(this);
        lstGrupos.setModel(new DefaultListModel());
        lstClaves.setModel(new DefaultListModel());
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
    public void setControlador(CVAsignarExamenes controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Obtiene los cursos almacenados en la base de datos dependiendo del tipo
     * de usuario que sea el usuario actual.
     *
     * @return Regresa verdadero si la consulta de cursos se realiza
     * exitósamente. Regresa falso si no se encuentran cursos en la base de
     * datos.
     */
    private boolean consultarCursos() {
        UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
        List<CursoDTO> listaCursos = controlVista.obtenerCursos(usuarioActual);
        boolean ok = false;

        if (listaCursos != null && !listaCursos.isEmpty()) {
            ok = true;
            mostrarCursos(listaCursos);
        }

        return ok;
    }

    /**
     * Muestra los cursos en la vista.
     *
     * @param cursos Lista de cursos para mostrar en la vista.
     */
    private void mostrarCursos(List<CursoDTO> listaCursos) {
        cbCursos.removeAllItems();
        for (CursoDTO curso : listaCursos) {
            ((DefaultComboBoxModel) cbCursos.getModel())
                    .addElement(curso.getNombre());
        }

        cbCursos.setSelectedIndex(-1);
    }

    /**
     * Muestra los exámenes en la vista.
     *
     * @param listaExamenes Lista de exámenes para mostrar en la vista.
     */
    private void mostrarExamenes(List<ExamenDTO> listaExamenes) {
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        for (ExamenDTO examen : listaExamenes) {
            Object[] datos = new Object[5];

            datos[0] = examen.getId();
            datos[1] = examen.getTitulo();
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
     * Obtiene los grupos asociados al curso seleccionado dependiendo del tipo
     * de usuario que sea el usuario actual.
     *
     * @return Regresa verdadero si la consulta de grupos se realiza
     * exitósamente. Regresa falso si no se encuentran grupos disponibles.
     */
    private boolean consultarGruposPorCurso() {
        UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
        List<GrupoDTO> listaGrupos = controlVista.obtenerGruposPorCurso(usuarioActual);

        if (listaGrupos != null && !listaGrupos.isEmpty()) {
            mostrarGrupos(listaGrupos);
            return true;
        } else {
            //No hay grupos asociados con el curso
            return false;
        }
    }

    /**
     * Muestra los grupos en la vista.
     *
     * @param listaGrupos Lista de grupos para mostrar en la vista.
     */
    private void mostrarGrupos(List<GrupoDTO> listaGrupos) {
        DefaultListModel modeloLista = (DefaultListModel) lstGrupos.getModel();
        modeloLista.clear();

        for (GrupoDTO grupo : listaGrupos) {
            String nombreGrupo = grupo.getGrado() + grupo.getNombre() + grupo.getTurno();
            modeloLista.addElement(nombreGrupo);
        }
    }

    /**
     * Obtiene las claves del examen seleccionado.
     */
    private void consultarClaves() {
        ExamenDTO examen = controlVista.obtenerExamen(tblExamenes.getSelectedRow());
        if (examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            if (claves != null) {
                mostrarClaves(claves);
            } 
        } 
    }

    /**
     * Muestra las claves del examen seleccionado.
     *
     * @param claves Lista de claves para mostrar en la vista.
     */
    private void mostrarClaves(List<ClaveExamenDTO> claves) {
        DefaultListModel modeloClaves = (DefaultListModel) lstClaves.getModel();
        modeloClaves.clear();

        for (ClaveExamenDTO clave : claves) {
            modeloClaves.addElement("Clave " + clave.getId().getClave());
        }
    }

    /**
     * Obtiene los alumnos pertenecientes al grupo seleccionado.
     *
     * @return Regresa verdadero si la consulta se realiza exitósamente. Regresa
     * falso si no se encuentran alumnos en el grupo seleccionado.
     */
    private boolean consultarAlumnos() {
        int indexGrupo = lstGrupos.getSelectedIndex();
        List<UsuarioDTO> listaAlumnos = controlVista.obtenerAlumnosDeGrupo(indexGrupo);
        boolean ok = false;

        if (listaAlumnos != null && !listaAlumnos.isEmpty()) {
            mostrarAlumnos(listaAlumnos);
            ok = true;
        } else {
            //Error con alumnos
            ok = false;
        }

        return ok;
    }

    /**
     * Muestra la lista de alumnos en la vista.
     *
     * @param alumnos Lista de alumnos para mostrar en la vista.
     */
    private void mostrarAlumnos(List<UsuarioDTO> alumnos) {
        ((DefaultTableModel) tblAsignaciones.getModel()).setRowCount(0);
        DefaultTableModel modeloAlumnos = (DefaultTableModel) tblAlumnos.getModel();
        modeloAlumnos.setRowCount(0);

        for (UsuarioDTO alumno : alumnos) {
            Object[] datos = new Object[5];

            datos[0] = false;
            datos[1] = alumno.getApellidoPaterno();
            datos[2] = alumno.getApellidoMaterno();
            datos[3] = alumno.getNombre();
            datos[4] = alumno.getUsuario();

            modeloAlumnos.addRow(datos);
        }
    }

    /**
     * Se muestran los datos finales de la asignación del examen.
     */
    private void mostrarDatos() {
        DefaultTableModel modeloAsignacionesFinal
                = (DefaultTableModel) tblAsignacionesFinal.getModel();
        modeloAsignacionesFinal.setRowCount(0);

        txtfGrupo.setText(lstGrupos.getSelectedValue().toString());
        txtfCurso.setText(cbCursos.getSelectedItem().toString());
        int indexExamen = tblExamenes.getSelectedRow();
        txtfExamen.setText(tblExamenes.getValueAt(indexExamen, 1).toString());

        for (int i = 0; i < tblAsignaciones.getRowCount(); i++) {
            Object datos[] = new Object[5];

            for (int j = 1; j < 6; j++) {

                datos[j - 1] = tblAsignaciones.getValueAt(i, j);
            }
            modeloAsignacionesFinal.addRow(datos);
        }
    }

    /**
     * Crea una lista de objetos de tipo ExamenAsignadoDTO con sus atributos
     * obtenidos de la vista.
     *
     * @return La lista de exámenes asignados.
     */
    private List<ExamenAsignadoDTO> encapsularExamenAsignado() {
        List<ExamenAsignadoDTO> listaExamenesAsignados
                = new ArrayList<ExamenAsignadoDTO>();
        int numeroAsignaciones = tblAsignacionesFinal.getRowCount();
        ExamenDTO examen = controlVista.getExamenSeleccionado();
        List<ClaveExamenDTO> listaClavesExamen = examen.getClaves();
        ClaveExamenDTO claveExamen = null;

        for (int i = 0; i < numeroAsignaciones; i++) {
            ExamenAsignadoDTO examenAsignado = new ExamenAsignadoDTO();
            String usuario = tblAsignacionesFinal.getValueAt(i, 3).toString();
            UsuarioDTO alumno = controlVista.obtenerAlumno(usuario);
            examenAsignado.setAlumno(alumno);
            examenAsignado.setExamen(examen);
            examenAsignado.setTiempo((Integer) jsTiempo.getValue());

            //Obtiene la clave asignada; Se remueven los primeros 6 caracteres
            //que contienen la palabra "Clave" para dejar sólo el número de clave.
            int idClave = Integer.parseInt(tblAsignacionesFinal.getValueAt(i, 4)
                    .toString()
                    .substring(6));

            //Obtener la clave que se asignó de la lista de claves 
            //que tiene el examen
            for (ClaveExamenDTO clave : listaClavesExamen) {
                if (idClave == clave.getId().getClave()) {
                    claveExamen = clave;
                }
            }

            //Obtener la clave con todos sus reactivos
            claveExamen = controlVista.obtenerClaveExamen(claveExamen.getId());

            //Para cada reactivo que se vaya a agregar
            for (int j = 0; j < claveExamen.getReactivos().size(); j++) {
                ReactivoAsignadoDTO reactivo = new ReactivoAsignadoDTO();
                ReactivoAsignadoPK pk = new ReactivoAsignadoPK();
                pk.setIdReactivo(claveExamen.getReactivos().get(j).getId());
                //Se guarda el id
                reactivo.setId(pk);
                reactivo.setRedaccionReactivo(claveExamen.getReactivos().get(j).getRedaccion());
                reactivo.setRespuestaAlumno(null);
                reactivo.setRespuestaReactivo(claveExamen.getReactivos().get(j).getRespuesta());
                //La lista de opciones de los reactivos.
                List<String> opciones = new ArrayList<>();
                for (String opcion : claveExamen.getReactivos().get(j).getOpciones()) {
                    opciones.add(opcion);
                }
                reactivo.setOpcionesReactivo(opciones);

                //Relacion entre reactivo y examenAsignado
                examenAsignado.addReactivo(reactivo);
            }
            listaExamenesAsignados.add(examenAsignado);

        }

        return listaExamenesAsignados;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        tbpAsignarExamenes = new javax.swing.JTabbedPane();
        pnlExamenes = new javax.swing.JPanel();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        lblExamenes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        btnSiguienteExamenes = new javax.swing.JButton();
        btnConsultarExamenes = new javax.swing.JButton();
        pnlGrupos = new javax.swing.JPanel();
        lblGrupos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGrupos = new javax.swing.JList();
        btnAnteriorGrupos = new javax.swing.JButton();
        btnSiguienteGrupos = new javax.swing.JButton();
        pnlAsignaciones = new javax.swing.JPanel();
        lblClaves = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstClaves = new javax.swing.JList();
        lblAlumnos = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAsignarClave = new javax.swing.JButton();
        lblAsignaciones = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAsignaciones = new javax.swing.JTable();
        btnRemoverAsignacion = new javax.swing.JButton();
        btnSiguienteAsignaciones = new javax.swing.JButton();
        btnAnteriorAsignaciones = new javax.swing.JButton();
        btnSeleccionarAlumnos = new javax.swing.JButton();
        btnDeseleccionarAlumnos = new javax.swing.JButton();
        btnSeleccionarAsignaciones = new javax.swing.JButton();
        btnDeseleccionarAsignaciones = new javax.swing.JButton();
        pnlTiempo = new javax.swing.JPanel();
        lblTiempo = new javax.swing.JLabel();
        jsTiempo = new javax.swing.JSpinner();
        lblMinutos = new javax.swing.JLabel();
        pnlDatosAsignacion = new javax.swing.JPanel();
        lblGrupoFinal = new javax.swing.JLabel();
        txtfGrupo = new javax.swing.JTextField();
        lblExamenFinal = new javax.swing.JLabel();
        txtfCurso = new javax.swing.JTextField();
        lblCursoFinal = new javax.swing.JLabel();
        txtfExamen = new javax.swing.JTextField();
        lblAlumnosFinal = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAsignacionesFinal = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        btnAnteriorTiempo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Asignar Exámenes");

        tbpAsignarExamenes.setPreferredSize(new java.awt.Dimension(780, 481));

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos");

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursos.setSelectedIndex(-1);
        cbCursos.setPreferredSize(new java.awt.Dimension(200, 30));

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamenes.setText("Exámenes");

        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nombre", "Fecha creación", "Fecha Modificación", "Autor"
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
        tblExamenes.setFocusable(false);
        tblExamenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(0).setResizable(false);
            tblExamenes.getColumnModel().getColumn(1).setResizable(false);
            tblExamenes.getColumnModel().getColumn(2).setResizable(false);
            tblExamenes.getColumnModel().getColumn(3).setResizable(false);
            tblExamenes.getColumnModel().getColumn(4).setResizable(false);
        }
        tblExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblExamenes.getColumnModel().getColumn(0).setPreferredWidth(55);
        tblExamenes.getColumnModel().getColumn(1).setPreferredWidth(175);
        tblExamenes.getColumnModel().getColumn(2).setPreferredWidth(175);
        tblExamenes.getColumnModel().getColumn(3).setPreferredWidth(175);
        tblExamenes.getColumnModel().getColumn(4).setPreferredWidth(175);

        btnSiguienteExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteExamenes.setText("Siguiente");
        btnSiguienteExamenes.setPreferredSize(new java.awt.Dimension(115, 30));
        btnSiguienteExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteExamenesActionPerformed(evt);
            }
        });

        btnConsultarExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnConsultarExamenes.setText("Mostrar exámenes");
        btnConsultarExamenes.setPreferredSize(new java.awt.Dimension(135, 30));
        btnConsultarExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarExamenes(evt);
            }
        });

        javax.swing.GroupLayout pnlExamenesLayout = new javax.swing.GroupLayout(pnlExamenes);
        pnlExamenes.setLayout(pnlExamenesLayout);
        pnlExamenesLayout.setHorizontalGroup(
            pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExamenesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguienteExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlExamenesLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(pnlExamenesLayout.createSequentialGroup()
                        .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlExamenesLayout.createSequentialGroup()
                                .addComponent(lblCursos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblExamenes))
                        .addGap(0, 352, Short.MAX_VALUE))))
        );
        pnlExamenesLayout.setVerticalGroup(
            pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCursos)
                    .addComponent(btnConsultarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblExamenes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnSiguienteExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tbpAsignarExamenes.addTab("Exámenes", pnlExamenes);

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

        lstGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstGrupos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstGrupos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstGrupos);

        btnAnteriorGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorGrupos.setText("Anterior");
        btnAnteriorGrupos.setPreferredSize(new java.awt.Dimension(115, 30));
        btnAnteriorGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorGruposActionPerformed(evt);
            }
        });

        btnSiguienteGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteGrupos.setText("Siguiente");
        btnSiguienteGrupos.setPreferredSize(new java.awt.Dimension(115, 30));
        btnSiguienteGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlGruposLayout = new javax.swing.GroupLayout(pnlGrupos);
        pnlGrupos.setLayout(pnlGruposLayout);
        pnlGruposLayout.setHorizontalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGruposLayout.createSequentialGroup()
                .addGroup(pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGruposLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnteriorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlGruposLayout.createSequentialGroup()
                        .addGroup(pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlGruposLayout.createSequentialGroup()
                                .addGap(265, 265, 265)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlGruposLayout.createSequentialGroup()
                                .addGap(359, 359, 359)
                                .addComponent(lblGrupos)))
                        .addGap(0, 262, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlGruposLayout.setVerticalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGruposLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnteriorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguienteGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpAsignarExamenes.addTab("Grupos", pnlGrupos);

        pnlAsignaciones.setPreferredSize(new java.awt.Dimension(775, 477));

        lblClaves.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblClaves.setText("Claves");

        lstClaves.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lstClaves.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstClaves.setPreferredSize(new java.awt.Dimension(120, 80));
        jScrollPane3.setViewportView(lstClaves);

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Apellido paterno", "Apellido materno", "Nombre", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAlumnos.setFocusable(false);
        tblAlumnos.setRowSelectionAllowed(false);
        tblAlumnos.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblAlumnos);
        if (tblAlumnos.getColumnModel().getColumnCount() > 0) {
            tblAlumnos.getColumnModel().getColumn(0).setResizable(false);
            tblAlumnos.getColumnModel().getColumn(1).setResizable(false);
            tblAlumnos.getColumnModel().getColumn(2).setResizable(false);
            tblAlumnos.getColumnModel().getColumn(3).setResizable(false);
            tblAlumnos.getColumnModel().getColumn(4).setResizable(false);
        }
        tblAlumnos.getColumnModel().getColumn(0).setPreferredWidth(36);
        tblAlumnos.getColumnModel().getColumn(1).setPreferredWidth(145);
        tblAlumnos.getColumnModel().getColumn(2).setPreferredWidth(145);
        tblAlumnos.getColumnModel().getColumn(3).setPreferredWidth(145);
        tblAlumnos.getColumnModel().getColumn(4).setPreferredWidth(145);

        btnAsignarClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAsignarClave.setText("Asignar clave");
        btnAsignarClave.setPreferredSize(new java.awt.Dimension(120, 23));
        btnAsignarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAsignacion(evt);
            }
        });

        lblAsignaciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAsignaciones.setText("Asignaciones");

        tblAsignaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Apellido paterno", "Apellido materno", "Nombre", "Usuario", "Clave asignada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblAsignaciones.setFocusable(false);
        tblAsignaciones.setRowSelectionAllowed(false);
        tblAsignaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tblAsignaciones);
        if (tblAsignaciones.getColumnModel().getColumnCount() > 0) {
            tblAsignaciones.getColumnModel().getColumn(0).setResizable(false);
            tblAsignaciones.getColumnModel().getColumn(1).setResizable(false);
            tblAsignaciones.getColumnModel().getColumn(2).setResizable(false);
            tblAsignaciones.getColumnModel().getColumn(3).setResizable(false);
            tblAsignaciones.getColumnModel().getColumn(4).setResizable(false);
            tblAsignaciones.getColumnModel().getColumn(5).setResizable(false);
        }
        TableColumnModel modeloColAsignaciones = tblAsignaciones.getColumnModel();
        modeloColAsignaciones.getColumn(0).setPreferredWidth(36);
        modeloColAsignaciones.getColumn(1).setPreferredWidth(116);
        modeloColAsignaciones.getColumn(2).setPreferredWidth(116);
        modeloColAsignaciones.getColumn(3).setPreferredWidth(116);
        modeloColAsignaciones.getColumn(4).setPreferredWidth(116);
        modeloColAsignaciones.getColumn(4).setPreferredWidth(116);

        btnRemoverAsignacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverAsignacion.setText("Quitar asginación");
        btnRemoverAsignacion.setPreferredSize(new java.awt.Dimension(120, 23));
        btnRemoverAsignacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerAsignacion(evt);
            }
        });

        btnSiguienteAsignaciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteAsignaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteAsignaciones.setText("Siguiente");
        btnSiguienteAsignaciones.setPreferredSize(new java.awt.Dimension(115, 30));
        btnSiguienteAsignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteAsignacionesActionPerformed(evt);
            }
        });

        btnAnteriorAsignaciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorAsignaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorAsignaciones.setText("Anterior");
        btnAnteriorAsignaciones.setPreferredSize(new java.awt.Dimension(115, 30));
        btnAnteriorAsignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorAsignacionesActionPerformed(evt);
            }
        });

        btnSeleccionarAlumnos.setText("Seleccionar todos");
        btnSeleccionarAlumnos.setFocusable(false);
        btnSeleccionarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarAlumnosActionPerformed(evt);
            }
        });

        btnDeseleccionarAlumnos.setText("Deseleccionar todos");
        btnDeseleccionarAlumnos.setFocusable(false);
        btnDeseleccionarAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeseleccionarAlumnosActionPerformed(evt);
            }
        });

        btnSeleccionarAsignaciones.setText("Seleccionar todos");
        btnSeleccionarAsignaciones.setFocusable(false);
        btnSeleccionarAsignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarAsignacionesActionPerformed(evt);
            }
        });

        btnDeseleccionarAsignaciones.setText("Deseleccionar todos");
        btnDeseleccionarAsignaciones.setFocusable(false);
        btnDeseleccionarAsignaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeseleccionarAsignacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAsignacionesLayout = new javax.swing.GroupLayout(pnlAsignaciones);
        pnlAsignaciones.setLayout(pnlAsignacionesLayout);
        pnlAsignacionesLayout.setHorizontalGroup(
            pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAsignarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                        .addGap(517, 517, 517)
                        .addComponent(btnAnteriorAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAsignacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                        .addComponent(btnRemoverAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(lblAsignaciones)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSeleccionarAsignaciones)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeseleccionarAsignaciones)
                        .addGap(21, 21, 21))))
            .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblClaves)
                .addGap(91, 91, 91)
                .addComponent(lblAlumnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeleccionarAlumnos)
                .addGap(18, 18, 18)
                .addComponent(btnDeseleccionarAlumnos)
                .addGap(23, 23, 23))
        );
        pnlAsignacionesLayout.setVerticalGroup(
            pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClaves)
                    .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblAlumnos)
                        .addComponent(btnSeleccionarAlumnos)
                        .addComponent(btnDeseleccionarAlumnos)))
                .addGap(2, 2, 2)
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAsignacionesLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnAsignarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAsignaciones)
                    .addComponent(btnDeseleccionarAsignaciones)
                    .addComponent(btnSeleccionarAsignaciones))
                .addGap(2, 2, 2)
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlAsignacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnteriorAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguienteAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpAsignarExamenes.addTab("Asignaciones", pnlAsignaciones);

        lblTiempo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTiempo.setText("Tiempo límite:");

        jsTiempo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jsTiempo.setModel(new javax.swing.SpinnerNumberModel(50, 50, 120, 1));

        lblMinutos.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lblMinutos.setText("minutos");

        pnlDatosAsignacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la asignación"));

        lblGrupoFinal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupoFinal.setText("Grupo:");

        txtfGrupo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfGrupo.setEnabled(false);
        txtfGrupo.setPreferredSize(new java.awt.Dimension(150, 30));

        lblExamenFinal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamenFinal.setText("Examen:");

        txtfCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfCurso.setEnabled(false);
        txtfCurso.setPreferredSize(new java.awt.Dimension(150, 30));

        lblCursoFinal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursoFinal.setText("Curso:");

        txtfExamen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfExamen.setEnabled(false);
        txtfExamen.setPreferredSize(new java.awt.Dimension(150, 30));

        lblAlumnosFinal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnosFinal.setText("Alumnos:");

        tblAsignacionesFinal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Apellido paterno", "Apellido materno", "Nombre", "Usuario", "Clave asignada"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        tblAsignacionesFinal.setRowSelectionAllowed(false);
        tblAsignacionesFinal.getTableHeader().setReorderingAllowed(false);
        jScrollPane6.setViewportView(tblAsignacionesFinal);
        if (tblAsignacionesFinal.getColumnModel().getColumnCount() > 0) {
            tblAsignacionesFinal.getColumnModel().getColumn(0).setResizable(false);
            tblAsignacionesFinal.getColumnModel().getColumn(1).setResizable(false);
            tblAsignacionesFinal.getColumnModel().getColumn(2).setResizable(false);
            tblAsignacionesFinal.getColumnModel().getColumn(3).setResizable(false);
            tblAsignacionesFinal.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout pnlDatosAsignacionLayout = new javax.swing.GroupLayout(pnlDatosAsignacion);
        pnlDatosAsignacion.setLayout(pnlDatosAsignacionLayout);
        pnlDatosAsignacionLayout.setHorizontalGroup(
            pnlDatosAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosAsignacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatosAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addGroup(pnlDatosAsignacionLayout.createSequentialGroup()
                        .addComponent(lblAlumnosFinal)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlDatosAsignacionLayout.createSequentialGroup()
                        .addComponent(lblGrupoFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCursoFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblExamenFinal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfExamen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDatosAsignacionLayout.setVerticalGroup(
            pnlDatosAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosAsignacionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDatosAsignacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrupoFinal)
                    .addComponent(txtfGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExamenFinal)
                    .addComponent(txtfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCursoFinal)
                    .addComponent(txtfExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlumnosFinal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        txtfGrupo.setDisabledTextColor(Color.BLACK);
        txtfCurso.setDisabledTextColor(Color.BLACK);
        txtfExamen.setDisabledTextColor(Color.BLACK);

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(115, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asignarExamen(evt);
            }
        });

        btnAnteriorTiempo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorTiempo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorTiempo.setText("Anterior");
        btnAnteriorTiempo.setPreferredSize(new java.awt.Dimension(115, 30));
        btnAnteriorTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorTiempoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTiempoLayout = new javax.swing.GroupLayout(pnlTiempo);
        pnlTiempo.setLayout(pnlTiempoLayout);
        pnlTiempoLayout.setHorizontalGroup(
            pnlTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiempoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatosAsignacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTiempoLayout.createSequentialGroup()
                        .addComponent(lblTiempo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblMinutos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTiempoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnteriorTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTiempoLayout.setVerticalGroup(
            pnlTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTiempoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTiempo)
                    .addComponent(jsTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMinutos))
                .addGap(29, 29, 29)
                .addComponent(pnlDatosAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTiempoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnteriorTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JFormattedTextField txt = ((JSpinner.NumberEditor) jsTiempo.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

        tbpAsignarExamenes.addTab("Tiempo límite", pnlTiempo);

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAsignarExamenes(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(661, 661, 661)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tbpAsignarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpAsignarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbpAsignarExamenes.setEnabledAt(0, false);
        tbpAsignarExamenes.setEnabledAt(1, false);
        tbpAsignarExamenes.setEnabledAt(2, false);
        tbpAsignarExamenes.setEnabledAt(3, false);
        tbpAsignarExamenes.setTitleAt(0, "<html><font color=black>Exámenes</font></html>");
        tbpAsignarExamenes.setTitleAt(1, "<html><font color=black>Grupos</font></html>");
        tbpAsignarExamenes.setTitleAt(2, "<html><font color=black>Asignaciones</font></html>");
        tbpAsignarExamenes.setTitleAt(3, "<html><font color=black>Tiempo límite</font></html>");
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cancela la asignación de exámenes y regresa a la vista principal.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void cancelarAsignarExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAsignarExamenes
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desesa cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_cancelarAsignarExamenes

    /**
     * Obtiene los exámenes del curso seleccionado dependiendo del tipo que sea
     * el usuario actual.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void consultarExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarExamenes
        // TODO add your handling code here:
        if (cbCursos.getSelectedIndex() != -1) {
            CursoDTO curso = controlVista.obtenerCurso(cbCursos.getSelectedIndex());
            UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
            List<ExamenDTO> listaExamenes = controlVista
                    .obtenerExamenesPorCurso(curso, usuarioActual);
            DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

            model.setRowCount(0);

            if (listaExamenes != null && !listaExamenes.isEmpty()) {
                mostrarExamenes(listaExamenes);
            } else {
                JOptionPane.showMessageDialog(this, "No hay exámenes disponibles.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_consultarExamenes

    /**
     * Valida que se hayan seleccionado datos correctos y cambia a la pestaña de
     * grupos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnSiguienteExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteExamenesActionPerformed
        // TODO add your handling code here:
        if (cbCursos.getSelectedIndex() != -1) {
            if (tblExamenes.getSelectedRow() != -1) {
                if (consultarGruposPorCurso()) {
                    tbpAsignarExamenes.setSelectedIndex(1);

                } else {
                    JOptionPane.showMessageDialog(this, "No hay grupos "
                            + "asociados al curso.", "Advertencia",
                            JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un examen.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un curso.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteExamenesActionPerformed

    /**
     * Regresa a la pestaña de exámenes.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnAnteriorGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorGruposActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(0);
    }//GEN-LAST:event_btnAnteriorGruposActionPerformed

    /**
     * Valida que el grupo seleccionado tenga alumnos y cambia a la pestaña de
     * asignaciones.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnSiguienteGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteGruposActionPerformed
        // TODO add your handling code here:
        if (!lstGrupos.isSelectionEmpty()) {
            if (consultarAlumnos()) {
                consultarClaves();
                tbpAsignarExamenes.setSelectedIndex(2);
            } else {
                JOptionPane.showMessageDialog(this, "El grupo seleccionado no "
                        + "tiene alumnos.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un grupo.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteGruposActionPerformed

    /**
     * Regresa a la pestaña de grupos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnAnteriorAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorAsignacionesActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(1);
    }//GEN-LAST:event_btnAnteriorAsignacionesActionPerformed

    /**
     * Valida que se hayan seleccionado datos correctos y cambia a la pestaña de
     * tiempo límite.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnSiguienteAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteAsignacionesActionPerformed
        // TODO add your handling code here:
        if (tblAsignaciones.getRowCount() != 0) {
            tbpAsignarExamenes.setSelectedIndex(3);
            mostrarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Agregue asignaciones.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnSiguienteAsignacionesActionPerformed

    /**
     * Regresa a la pestaña de asignaciones.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnAnteriorTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorTiempoActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(2);
    }//GEN-LAST:event_btnAnteriorTiempoActionPerformed

    /**
     * Se asignan la clave seleccionada a los alumnos seleccionados.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void agregarAsignacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAsignacion
        // TODO add your handling code here:
        List<Integer> indexes = new ArrayList<>();

        if (!lstClaves.isSelectionEmpty()) {

            int cont = tblAlumnos.getRowCount();

            //Verifica cuáles filas de la tabla han sido seleccionadas
            //y almacena sus index
            for (int i = 0; i < cont; i++) {
                if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                    indexes.add(i);
                }
            }

            if (cont == 0 || indexes.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Seleccione por lo menos un alumno.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                DefaultTableModel modeloAlumnos
                        = (DefaultTableModel) tblAlumnos.getModel();
                DefaultTableModel modeloAsignaciones
                        = (DefaultTableModel) tblAsignaciones.getModel();

                //Se copian los datos de las filas seleccionadas de
                //la tabla de alumnos a la tabla de asignaciones
                for (Integer index : indexes) {
                    Object datos[] = new Object[6];

                    datos[0] = false;
                    for (int i = 1; i < 5; i++) {
                        datos[i] = modeloAlumnos.getValueAt(index, i);
                    }
                    datos[5] = lstClaves.getSelectedValue();

                    modeloAsignaciones.addRow(datos);
                }

                //Se remueven las filas que ya han sido copiadas
                for (int i = indexes.size() - 1; i >= 0; i--) {
                    modeloAlumnos.removeRow(indexes.get(i));
                }
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Seleccione una clave.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_agregarAsignacion

    /**
     * Se remueven las asignaciones seleccionadas.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void removerAsignacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerAsignacion
        // TODO add your handling code here:
        List<Integer> indexes = new ArrayList<>();

        int cont = tblAsignaciones.getRowCount();

        //Verifica cuáles filas de la tabla han sido seleccionadas
        //y almacena sus index
        for (int i = 0; i < cont; i++) {
            if (tblAsignaciones.getValueAt(i, 0).equals(true)) {
                indexes.add(i);
            }
        }

        if (cont == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione por lo menos una asignación.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            DefaultTableModel modeloAlumnos
                    = (DefaultTableModel) tblAlumnos.getModel();
            DefaultTableModel modeloAsignaciones
                    = (DefaultTableModel) tblAsignaciones.getModel();

            //Se copian los datos de las filas seleccionadas de
            //la tabla de alumnos a la tabla de asignaciones
            for (Integer index : indexes) {
                Object datos[] = new Object[5];

                datos[0] = false;
                for (int i = 1; i < 5; i++) {
                    datos[i] = modeloAsignaciones.getValueAt(index, i);
                }

                modeloAlumnos.addRow(datos);
            }

            //Se remueven las filas que ya han sido copiadas
            for (int i = indexes.size() - 1; i >= 0; i--) {
                modeloAsignaciones.removeRow(indexes.get(i));
            }
        }
    }//GEN-LAST:event_removerAsignacion

    /**
     * Almacena la lista de exámenes asignados en la base de datos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void asignarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asignarExamen
        // TODO add your handling code here:
        List<ExamenAsignadoDTO> listaExamenesAsignados = encapsularExamenAsignado();

        if (listaExamenesAsignados != null && !listaExamenesAsignados.isEmpty()) {
            boolean ok = controlVista.asignarExamenes(listaExamenesAsignados);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Asignación de exámenes completa.");
                limpiar();
                padre.mostrarVista(Vista.HOME);
            } else {
                JOptionPane.showMessageDialog(this, "Error al asignar exámenes.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al encapsular examenes.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_asignarExamen

    /**
     * Selecciona todos los registros de la tabla tblAlumnos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnSeleccionarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAlumnosActionPerformed
        // TODO add your handling code here:
        int cont = tblAlumnos.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(false)) {
                ((DefaultTableModel) tblAlumnos.getModel()).setValueAt(true, i, 0);
            }
        }
    }//GEN-LAST:event_btnSeleccionarAlumnosActionPerformed

    /**
     * Deselecciona todos los registros de la tabla tblAlumnos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnDeseleccionarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeseleccionarAlumnosActionPerformed
        // TODO add your handling code here:
        int cont = tblAlumnos.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                ((DefaultTableModel) tblAlumnos.getModel()).setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_btnDeseleccionarAlumnosActionPerformed

    /**
     * Selecciona todos los registros de la tabla tblAsignaciones.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnSeleccionarAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAsignacionesActionPerformed
        // TODO add your handling code here:
        int cont = tblAsignaciones.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAsignaciones.getValueAt(i, 0).equals(false)) {
                ((DefaultTableModel) tblAsignaciones.getModel()).setValueAt(true, i, 0);
            }
        }
    }//GEN-LAST:event_btnSeleccionarAsignacionesActionPerformed

    /**
     * Deselecciona todos los registros de la tabla tblAsignaciones.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnDeseleccionarAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeseleccionarAsignacionesActionPerformed
        // TODO add your handling code here:
        int cont = tblAsignaciones.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAsignaciones.getValueAt(i, 0).equals(true)) {
                ((DefaultTableModel) tblAsignaciones.getModel()).setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_btnDeseleccionarAsignacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Para regresar a la pestaña de grupos.
    */
    private javax.swing.JButton btnAnteriorAsignaciones;
    /**
    * Botón para regresar a la pestaña de exámenes.
    */
    private javax.swing.JButton btnAnteriorGrupos;
    /**
    * Botón para regresar a la pestaña de asignaciones.
    */
    private javax.swing.JButton btnAnteriorTiempo;
    /**
    * Botón para asignar una clave a alumnos seleccionados.
    */
    private javax.swing.JButton btnAsignarClave;
    /**
    * Botón para cancelar la asignación de exámenes.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para consultar los exámenes del curso seleccionado.
    */
    private javax.swing.JButton btnConsultarExamenes;
    /**
    * Botón para deseleccionar todas las filas de la tabla tblAlumnos.
    */
    private javax.swing.JButton btnDeseleccionarAlumnos;
    /**
    * Botón para deseleccionar todas las filas de la tabla tblAsignaciones.
    */
    private javax.swing.JButton btnDeseleccionarAsignaciones;
    /**
    * Botón para guardar la lista exámenes asignados.
    */
    private javax.swing.JButton btnGuardar;
    /**
    * Botón para remover asignaciones seleccionadas.
    */
    private javax.swing.JButton btnRemoverAsignacion;
    /**
    * Botón para seleccionar todas las filas de la tabla tblAlumnos.
    */
    private javax.swing.JButton btnSeleccionarAlumnos;
    /**
    * Botón para seleccionar todas las filas de la tabla tblAsignaciones.
    */
    private javax.swing.JButton btnSeleccionarAsignaciones;
    /**
    * Botón para pasar a la pestaña de tiempo.
    */
    private javax.swing.JButton btnSiguienteAsignaciones;
    /**
    * Botón para pasar a la pestaña de grupos.
    */
    private javax.swing.JButton btnSiguienteExamenes;
    /**
    * Botón para pasar a la pestaña de asignaciones.
    */
    private javax.swing.JButton btnSiguienteGrupos;
    /**
    * ComboBox para mostrar los cursos.
    */
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    /**
    * Se utiliza para seleccionar el tiempo límite del examen.
    */
    private javax.swing.JSpinner jsTiempo;
    /**
    * Label para los alumnos.
    */
    private javax.swing.JLabel lblAlumnos;
    /**
    * Label de la tabla de alumnos de la asignación final.
    */
    private javax.swing.JLabel lblAlumnosFinal;
    /**
    * Label para las asignaciones.
    */
    private javax.swing.JLabel lblAsignaciones;
    /**
    * Label para las claves.
    */
    private javax.swing.JLabel lblClaves;
    /**
    * Label para el campo de texto de curso de la asignación final.
    */
    private javax.swing.JLabel lblCursoFinal;
    /**
    * Label de la lista de cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label para el campo de texto de examen de la asignación final.
    */
    private javax.swing.JLabel lblExamenFinal;
    /**
    * Label para la tabla de exámenes.
    */
    private javax.swing.JLabel lblExamenes;
    /**
    * Label para el campo de texto del grupo de la asignación final.
    */
    private javax.swing.JLabel lblGrupoFinal;
    /**
    * Label para la lista de grupos.
    */
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblMinutos;
    /**
    * Label para la selección del tiempo límite del examen.
    */
    private javax.swing.JLabel lblTiempo;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Lista para mostrar las claves del examen seleccionado.
    */
    private javax.swing.JList lstClaves;
    /**
    * Lista para mostrar los grupos.
    */
    private javax.swing.JList lstGrupos;
    /**
    * Panel para agrupar los componentes utilizados para mostrar las claves,
    * los alumnos y las asignaciones.
    */
    private javax.swing.JPanel pnlAsignaciones;
    /**
    * Panel para agrupar los componentes utilizados para mostrar todos los datos
    * de la asignación final.
    */
    private javax.swing.JPanel pnlDatosAsignacion;
    /**
    * Panel para agrupar los componentes utilizados para mostrar cursos y exámenes.
    */
    private javax.swing.JPanel pnlExamenes;
    /**
    * Panel para agrupar los componentes utilizados para mostrar los grupos.
    */
    private javax.swing.JPanel pnlGrupos;
    /**
    * Panel para agrupar todos los componentes utilizados para la selección del
    * tiempo final y los datos finales de la asignación.
    */
    private javax.swing.JPanel pnlTiempo;
    /**
    * Tabla para mostrar los datos de los alumnos del grupo seleccionado.
    */
    private javax.swing.JTable tblAlumnos;
    /**
    * Tabla para mostrar los datos de los alumnos seleccionado y su clave asignada.
    */
    private javax.swing.JTable tblAsignaciones;
    /**
    * Tabla para mostrar los alumnos y su clave asignada de la asignación final.
    */
    private javax.swing.JTable tblAsignacionesFinal;
    /**
    * Tabla para mostrar los datos de los exámenes.
    */
    private javax.swing.JTable tblExamenes;
    /**
    * Panel de pestañas para agrupar los páneles utilizados para asignar exámenes.
    */
    private javax.swing.JTabbedPane tbpAsignarExamenes;
    /**
    * Campo de texto para mostrar el curso de la asignación final.
    */
    private javax.swing.JTextField txtfCurso;
    /**
    * Campo de texto para mostrar el título del examen de la asignación final.
    */
    private javax.swing.JTextField txtfExamen;
    /**
    * Campo de texto para mostrar el grupo de la asignación final.
    */
    private javax.swing.JTextField txtfGrupo;
    // End of variables declaration//GEN-END:variables

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
        boolean cambiar = false;

        int ok = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que "
                + "quiere cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        cbCursos.removeAllItems();
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
        ((DefaultListModel) lstGrupos.getModel()).clear();
        ((DefaultListModel) lstClaves.getModel()).clear();
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblAsignaciones.getModel()).setRowCount(0);
        jsTiempo.setValue(50);
        tbpAsignarExamenes.setSelectedIndex(0);
    }

    /**
     * Este método es llamado cada vez que se muestra esta vista en el frame
     * principal, sirve para realizar el método inicial al mostrar una vista,
     * como una consulta.
     *
     * @param event El objeto AncestorEvent que se obtiene del evento.
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (!consultarCursos()) {
            JOptionPane.showMessageDialog(this, "No hay cursos disponibles.", 
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
