/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
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

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVConsultarCalificaciones;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Registrar Grupo
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaConsultarCalificaciones extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    /**
     * Controlador de la vista del caso de uso consultar calificaciones,
     * funciona para manejar la información obtenida en la vista para
     * comunicarse con las capas inferiores.
     */
    private CVConsultarCalificaciones controlVista;

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas, En ese
     * caso es utilizada para comunicarse con el JFrame Principal.
     */
    private InterfaceVista padre;

    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;

    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton anterior para pasar a la seccion de grupos.
    */
    private javax.swing.JButton btnAnteriorAlumnos;
    /**
    * Boton anterior para pasar a la seccion de examenes.
    */
    private javax.swing.JButton btnAnteriorCalificacion;
    /**
    * Boton anterior para pasar a la seccion de alumnos.
    */
    private javax.swing.JButton btnAnteriorExamenes;
    /**
    * Boton anterior para pasar a la seccion de crusos.
    */
    private javax.swing.JButton btnAnteriorGrupos;
    /**
    * Boton para cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Boton siguiente para pasar a la seccion de examenes.
    */
    private javax.swing.JButton btnSiguienteAlumnos;
    /**
    * Boton siguiente para pasar a la seccion de grupos.
    */
    private javax.swing.JButton btnSiguienteCursos;
    /**
    * Boton siguiente para pasar a la vista de calificacion.
    */
    private javax.swing.JButton btnSiguienteExamenes;
    /**
    * Boton siguiente para pasar a la seccion de alumnos.
    */
    private javax.swing.JButton btnSiguienteGrupos;
    /**
    * Scrollpane para la tabla de grupos.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Scrollpane para la tabla de alumnos.
    */
    private javax.swing.JScrollPane jScrollPane2;
    /**
    * Scrollpane para la tabla de examenes.
    */
    private javax.swing.JScrollPane jScrollPane4;
    /**
    * Scrollpane para la lista de cursos.
    */
    private javax.swing.JScrollPane jScrollPane5;
    /**
    * Scrollpane para la lista de reactivos.
    */
    private javax.swing.JScrollPane jScrollPane6;
    /**
    * Scrollpane para la redaccion del reactivo.
    */
    private javax.swing.JScrollPane jScrollPane7;
    /**
    * Label para el nombre del alumno.
    */
    private javax.swing.JLabel lblAlumno;
    /**
    * Label para la tabla de alumnos.
    */
    private javax.swing.JLabel lblAlumnos;
    /**
    * Label para la calificacion.
    */
    private javax.swing.JLabel lblCalificacion;
    /**
    * Label para el icon de correcto o incorrecto.
    */
    private javax.swing.JLabel lblCorrecto;
    /**
    * Label para la lista de cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label para la tabla de examenes.
    */
    private javax.swing.JLabel lblExamenes;
    /**
    * Label para la tabla de grupos.
    */
    private javax.swing.JLabel lblGrupos;
    /**
    * Label para la lista de reactivos.
    */
    private javax.swing.JLabel lblReactivos;
    /**
    * Label para la redaccion del reactivo.
    */
    private javax.swing.JLabel lblRedaccion;
    /**
    * Label para la respuesta del alumno.
    */
    private javax.swing.JLabel lblRespAlumno;
    /**
    * Label para la respuesta correcta.
    */
    private javax.swing.JLabel lblRespCorrecta;
    /**
    * Label para el titulo de la vista.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Label para el titulo del examen.
    */
    private javax.swing.JLabel lblTituloExamen;
    /**
    * Lista para mostrar los cursos.
    */
    private javax.swing.JList lstCursos;
    /**
    * Lista para mostrar los reactivos.
    */
    private javax.swing.JList lstReactivos;
    /**
    * Panel para la seccion de alumnos.
    */
    private javax.swing.JPanel pnlAlumnos;
    /**
    * Panel para la seccion de cursos.
    */
    private javax.swing.JPanel pnlCursos;
    /**
    * Panel para la seccion de calificacion.
    */
    private javax.swing.JPanel pnlExamen;
    /**
    * Panel para la seccion de examenes.
    */
    private javax.swing.JPanel pnlExamenes;
    /**
    * Panel para la seccion de grupos.
    */
    private javax.swing.JPanel pnlGrupos;
    /**
    * Tabla para mostrar alumnos.
    */
    private javax.swing.JTable tblAlumnos;
    /**
    * Tabla para mostrar examenes.
    */
    private javax.swing.JTable tblExamenes;
    /**
    * Tabla para mostrar grupos.
    */
    private javax.swing.JTable tblGrupos;
    /**
    * Tabbedpane para mostrar las secciones de consulta.
    */
    private javax.swing.JTabbedPane tbpCalificaciones;
    /**
    * Textarea para la redaccion del reactivo.
    */
    private javax.swing.JTextArea txtaRedaccion;
    /**
    * Textfield para el nombre del alumno.
    */
    private javax.swing.JTextField txtfAlumno;
    /**
    * Textfield para la calificacion.
    */
    private javax.swing.JTextField txtfCalificacion;
    /**
    * Textfield para la respuesta del alumno.
    */
    private javax.swing.JTextField txtfRespAlumno;
    /**
    * Textfield para la respuesta correcta.
    */
    private javax.swing.JTextField txtfRespCorrecta;
    /**
    * Textfield para el titulo del examen.
    */
    private javax.swing.JTextField txtfTitulo;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaRegistrarGrupo e inicializa sus atributos, y agrega
     * los listeners necesarios.
     */
    public VistaConsultarCalificaciones() {
        initComponents();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        addAncestorListener(this);
        lblCorrecto.setVisible(false);
    }

    /**
     * Asigna el controlador de vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVConsultarCalificaciones.
     */
    public void setControlador(CVConsultarCalificaciones controlVista) {
        this.controlVista = controlVista;
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
        lstCursos.removeAll();
        ((DefaultTableModel) tblGrupos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
        lstReactivos.removeAll();
        txtfAlumno.setText("");
        txtfTitulo.setText("");
        txtfCalificacion.setText("");
        txtaRedaccion.setText("");
        txtfRespCorrecta.setText("");
        txtfRespAlumno.setText("");
        tbpCalificaciones.setSelectedIndex(0);
    }

    /**
     * Obtiene los cursos disponibles dependiedo del usuario que este
     * actualmente y los envia para que sean mostrados en la vista.
     */
    public void consultarCursos() {
        UsuarioDTO actual = padre.obtenerUsuarioActual();
        List<CursoDTO> cursos = controlVista.obtenerCursos(actual);
        if (cursos == null || cursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cursos disponibles.",
                    "Advertencia", 2);
            limpiar();
        } else {
            mostrarCursos(cursos);
        }
    }

    /**
     * Recibe la lista de cursos y la muestra en los componentes de la vista.
     *
     * @param cursos Lista de cursos que mostrara.
     */
    public void mostrarCursos(List<CursoDTO> cursos) {
        DefaultListModel listModelCursos = new DefaultListModel();
        for (CursoDTO curso : cursos) {
            listModelCursos.addElement(curso.getNombre());
        }
        lstCursos.setModel(listModelCursos);
    }

    /**
     * Recibe el nombre del curso y obtiene los grupos disponibles para el curso
     * seleccionado.
     *
     * @param nombreCurso Nombre del curso.
     */
    public void consultarGrupos(String nombreCurso) {
        List<GrupoDTO> grupos = controlVista.obtenerGrupos(nombreCurso);
        if (grupos == null || grupos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay grupos disponibles para "
                    + "este curso.", "Advertencia", 2);
            tbpCalificaciones.setSelectedIndex(0);
        } else {
            mostrarGrupos(grupos);
        }
    }

    /**
     * Recibe la lista de grupos que mostrara en los componentes de la vista.
     *
     * @param grupos Lista de grupos.
     */
    public void mostrarGrupos(List<GrupoDTO> grupos) {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (GrupoDTO grupo : grupos) {
            Object[] fila = new Object[3];
            fila[0] = grupo.getGrado();
            fila[1] = grupo.getNombre();
            fila[2] = grupo.getTurno();
            modelo.addRow(fila);
        }
    }

    /**
     * Recibe el indice del grupo seleccionado y obtiene los alumnos de dicho
     * grupo.
     *
     * @param indexGrupo Indice de grupo.
     */
    public void consultarAlumnos(Integer indexGrupo) {
        List<UsuarioDTO> alumnos = controlVista.obtenerAlumnos(indexGrupo);
        if (alumnos == null || alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos disponibles en"
                    + " este grupo.", "Advertencia", 2);
            tbpCalificaciones.setSelectedIndex(1);
        } else {
            mostrarAlumnos(alumnos);
        }
    }

    /**
     * Recibe la lista de alumnos y los muestra en los componentes de la vista.
     *
     * @param alumnos Lista de alumnos.
     */
    public void mostrarAlumnos(List<UsuarioDTO> alumnos) {
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        for (UsuarioDTO alumno : alumnos) {
            Object[] fila = new Object[4];
            fila[0] = alumno.getId();
            fila[1] = alumno.getApellidoPaterno();
            fila[2] = alumno.getApellidoMaterno();
            fila[3] = alumno.getNombre();
            modelo.addRow(fila);
        }
    }

    /**
     * Recibe el indice del alumno de seleccionado y obtiene los examenes
     * contestados por el alumno.
     *
     * @param indexAlumno Indice del alumno.
     */
    public void consultarExamenes(Integer indexAlumno) {
        List<ExamenAsignadoDTO> examenes = controlVista.obtenerExamenes(indexAlumno);
        if (examenes == null || examenes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay exámenes contestados "
                    + "por este alumno.", "Advertencia", 2);
            tbpCalificaciones.setSelectedIndex(2);
        } else {
            mostrarExamenes(examenes);
        }
    }

    /**
     * Recibe la lista de examenes contestados y los muestra en los componentes
     * de la vista.
     *
     * @param examenes Lista de examenes.
     */
    public void mostrarExamenes(List<ExamenAsignadoDTO> examenes) {
        DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
        for (ExamenAsignadoDTO examen : examenes) {
            Object[] fila = new Object[4];
            fila[0] = examen.getId().getIdExamen();
            fila[1] = examen.getExamen().getNombre();
            fila[2] = examen.getCalificacion();
            fila[3] = examen.getFechaAsignacion();
            modelo.addRow(fila);
        }
    }

    /**
     * Recibe el indice del examen seleccionado y obtiene el examen que mostrara
     * en los componentes de la vista.
     *
     * @param indexExamen Indice del examen.
     */
    public void consultarExamen(Integer indexExamen) {
        ExamenAsignadoDTO examen = controlVista.obtenerExamen(indexExamen);
        if (examen == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el examen!",
                    "Advertencia", 1);
            tbpCalificaciones.setSelectedIndex(3);
        } else {
            mostrarExamen(examen);
        }
    }

    /**
     * Recibe el examen que mostrara en los componentes de la vista, obitene los
     * reactivos del mismo grupo.
     *
     * @param examen Examen
     */
    public void mostrarExamen(ExamenAsignadoDTO examen) {
        txtfAlumno.setText(examen.getAlumno().getApellidoPaterno() + " "
                + examen.getAlumno().getApellidoMaterno() + " "
                + examen.getAlumno().getNombre());
        txtfTitulo.setText(examen.getExamen().getNombre());
        txtfCalificacion.setText(String.valueOf(examen.getCalificacion()));
        DefaultListModel modelo = new DefaultListModel();
        for (int i = 0; i < controlVista.obtenerReactivos().size(); i++) {
            modelo.addElement("Reactivo " + i);
        }
        lstReactivos.setModel(modelo);
        lstReactivos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = lstReactivos.getSelectedIndex();
                if (index != -1) {
                    ReactivoAsignadoDTO reactivo = controlVista.obtenerReactivo(index);
                    txtaRedaccion.setText(reactivo.getRedaccionReactivo());
                    txtfRespCorrecta.setText(reactivo.getRespuestaReactivo());
                    txtfRespAlumno.setText(reactivo.getRespuestaAlumno());
                    if (reactivo.getRespuestaReactivo()
                            .equalsIgnoreCase(reactivo.getRespuestaAlumno())) {
                        lblCorrecto.setIcon(ICONO_BIEN);
                    } else {
                        lblCorrecto.setIcon(ICONO_MAL);
                    }
                    lblCorrecto.setVisible(true);
                }
            }
        });
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        tbpCalificaciones = new javax.swing.JTabbedPane();
        pnlCursos = new javax.swing.JPanel();
        lblCursos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstCursos = new javax.swing.JList();
        btnSiguienteCursos = new javax.swing.JButton();
        pnlGrupos = new javax.swing.JPanel();
        lblGrupos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        btnAnteriorGrupos = new javax.swing.JButton();
        btnSiguienteGrupos = new javax.swing.JButton();
        pnlAlumnos = new javax.swing.JPanel();
        lblAlumnos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAnteriorAlumnos = new javax.swing.JButton();
        btnSiguienteAlumnos = new javax.swing.JButton();
        pnlExamenes = new javax.swing.JPanel();
        lblExamenes = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        btnAnteriorExamenes = new javax.swing.JButton();
        btnSiguienteExamenes = new javax.swing.JButton();
        pnlExamen = new javax.swing.JPanel();
        lblAlumno = new javax.swing.JLabel();
        lblTituloExamen = new javax.swing.JLabel();
        lblCalificacion = new javax.swing.JLabel();
        txtfAlumno = new javax.swing.JTextField();
        txtfTitulo = new javax.swing.JTextField();
        txtfCalificacion = new javax.swing.JTextField();
        lblReactivos = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        lstReactivos = new javax.swing.JList();
        lblRedaccion = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        txtaRedaccion = new javax.swing.JTextArea();
        lblRespCorrecta = new javax.swing.JLabel();
        txtfRespCorrecta = new javax.swing.JTextField();
        lblRespAlumno = new javax.swing.JLabel();
        txtfRespAlumno = new javax.swing.JTextField();
        lblCorrecto = new javax.swing.JLabel();
        btnAnteriorCalificacion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Consultar Calificaciones");
        lblTitulo.setToolTipText("");

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos");

        jScrollPane5.setViewportView(lstCursos);

        btnSiguienteCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteCursos.setText("Siguiente");
        btnSiguienteCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvanzarCursos(evt);
            }
        });

        javax.swing.GroupLayout pnlCursosLayout = new javax.swing.GroupLayout(pnlCursos);
        pnlCursos.setLayout(pnlCursosLayout);
        pnlCursosLayout.setHorizontalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSiguienteCursos)
                .addGap(37, 37, 37))
            .addGroup(pnlCursosLayout.createSequentialGroup()
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(lblCursos))
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(273, Short.MAX_VALUE))
        );
        pnlCursosLayout.setVerticalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(lblCursos)
                .addGap(59, 59, 59)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(btnSiguienteCursos)
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Cursos", pnlCursos);

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

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
        jScrollPane1.setViewportView(tblGrupos);

        btnAnteriorGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorGrupos.setText("Anterior");
        btnAnteriorGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetrocederGrupos(evt);
            }
        });

        btnSiguienteGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteGrupos.setText("Siguiente");
        btnSiguienteGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvanzarGrupos(evt);
            }
        });

        javax.swing.GroupLayout pnlGruposLayout = new javax.swing.GroupLayout(pnlGrupos);
        pnlGrupos.setLayout(pnlGruposLayout);
        pnlGruposLayout.setHorizontalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGruposLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(lblGrupos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGruposLayout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addGroup(pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnteriorGrupos))
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteGrupos)
                .addGap(42, 42, 42))
        );
        pnlGruposLayout.setVerticalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGruposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGrupos)
                .addGap(48, 48, 48)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteGrupos)
                    .addComponent(btnAnteriorGrupos))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Grupos", pnlGrupos);

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Apellido P", "Apellido M", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAlumnos);

        btnAnteriorAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorAlumnos.setText("Anterior");
        btnAnteriorAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetrocederAlumnos(evt);
            }
        });

        btnSiguienteAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteAlumnos.setText("Siguiente");
        btnSiguienteAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvanzarAlumnos(evt);
            }
        });

        javax.swing.GroupLayout pnlAlumnosLayout = new javax.swing.GroupLayout(pnlAlumnos);
        pnlAlumnos.setLayout(pnlAlumnosLayout);
        pnlAlumnosLayout.setHorizontalGroup(
            pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlumnosLayout.createSequentialGroup()
                .addGap(443, 443, 443)
                .addComponent(btnAnteriorAlumnos)
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteAlumnos)
                .addContainerGap(38, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAlumnosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAlumnos)
                .addGap(321, 321, 321))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAlumnosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126))
        );
        pnlAlumnosLayout.setVerticalGroup(
            pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlumnosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlumnos)
                .addGap(44, 44, 44)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteAlumnos)
                    .addComponent(btnAnteriorAlumnos))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Alumnos", pnlAlumnos);

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamenes.setText("Examenes");

        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Titulo", "Calificacion", "Fecha de Asignacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblExamenes);

        btnAnteriorExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorExamenes.setText("Anterior");
        btnAnteriorExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetrocederExamenes(evt);
            }
        });

        btnSiguienteExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteExamenes.setText("Siguiente");
        btnSiguienteExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvanzarExamenes(evt);
            }
        });

        javax.swing.GroupLayout pnlExamenesLayout = new javax.swing.GroupLayout(pnlExamenes);
        pnlExamenes.setLayout(pnlExamenesLayout);
        pnlExamenesLayout.setHorizontalGroup(
            pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnteriorExamenes)
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteExamenes)
                .addGap(31, 31, 31))
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlExamenesLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlExamenesLayout.createSequentialGroup()
                        .addGap(315, 315, 315)
                        .addComponent(lblExamenes)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        pnlExamenesLayout.setVerticalGroup(
            pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblExamenes)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteExamenes)
                    .addComponent(btnAnteriorExamenes))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Examenes", pnlExamenes);

        pnlExamen.setPreferredSize(new java.awt.Dimension(754, 400));

        lblAlumno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumno.setText("Alumno:");

        lblTituloExamen.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTituloExamen.setText("Titulo del examen:");

        lblCalificacion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCalificacion.setText("Calificacion:");

        txtfAlumno.setEnabled(false);

        txtfTitulo.setEnabled(false);

        txtfCalificacion.setEnabled(false);

        lblReactivos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblReactivos.setText("Reactivos");

        jScrollPane6.setViewportView(lstReactivos);

        lblRedaccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRedaccion.setText("Redaccion");

        txtaRedaccion.setColumns(20);
        txtaRedaccion.setRows(5);
        jScrollPane7.setViewportView(txtaRedaccion);

        lblRespCorrecta.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRespCorrecta.setText("Respuesta correcta");

        lblRespAlumno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRespAlumno.setText("Respuesta del alumno");

        lblCorrecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        btnAnteriorCalificacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorCalificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorCalificacion.setText("Anterior");
        btnAnteriorCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RetrocederCalificacion(evt);
            }
        });

        javax.swing.GroupLayout pnlExamenLayout = new javax.swing.GroupLayout(pnlExamen);
        pnlExamen.setLayout(pnlExamenLayout);
        pnlExamenLayout.setHorizontalGroup(
            pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenLayout.createSequentialGroup()
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlExamenLayout.createSequentialGroup()
                                .addComponent(lblCalificacion)
                                .addGap(18, 18, 18)
                                .addComponent(txtfCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(pnlExamenLayout.createSequentialGroup()
                                    .addComponent(lblTituloExamen)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfTitulo))
                                .addGroup(pnlExamenLayout.createSequentialGroup()
                                    .addComponent(lblAlumno)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblReactivos)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)
                        .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRedaccion))
                        .addGap(30, 30, 30)
                        .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRespAlumno)
                            .addComponent(lblRespCorrecta)
                            .addComponent(txtfRespCorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlExamenLayout.createSequentialGroup()
                                .addComponent(txtfRespAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblCorrecto))
                            .addComponent(btnAnteriorCalificacion))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        pnlExamenLayout.setVerticalGroup(
            pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlumno)
                    .addComponent(txtfAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTituloExamen)
                    .addComponent(txtfTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCalificacion)
                    .addComponent(txtfCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlExamenLayout.createSequentialGroup()
                            .addComponent(lblReactivos)
                            .addGap(13, 13, 13)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExamenLayout.createSequentialGroup()
                            .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCorrecto)
                                .addGroup(pnlExamenLayout.createSequentialGroup()
                                    .addComponent(lblRespCorrecta)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtfRespCorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(lblRespAlumno)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtfRespAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)))
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addComponent(lblRedaccion)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGap(38, 38, 38)
                .addComponent(btnAnteriorCalificacion)
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Calificaciones", pnlExamen);

        tbpCalificaciones.setEnabledAt(0, false);
        tbpCalificaciones.setEnabledAt(1, false);
        tbpCalificaciones.setEnabledAt(2, false);
        tbpCalificaciones.setEnabledAt(3, false);
        tbpCalificaciones.setEnabledAt(4, false);
        tbpCalificaciones.setTitleAt(0, "<html><font color='black'>Cursos</font></html>");
        tbpCalificaciones.setTitleAt(1, "<html><font color='black'>Grupos</font></html>");
        tbpCalificaciones.setTitleAt(2, "<html><font color='black'>Alumnos</font></html>");
        tbpCalificaciones.setTitleAt(3, "<html><font color='black'>Examenes</font></html>");
        tbpCalificaciones.setTitleAt(4, "<html><font color='black'>Calificaciones</font></html>");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(66, 66, 66))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(tbpCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(278, 278, 278)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(tbpCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Obtiene el indice del examen que selecciono y lo envia para obtener el
     * examen seleccionado.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AvanzarExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvanzarExamenes
        if (tblExamenes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un examen.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(4);
            consultarExamen(tblExamenes.getSelectedRow());
        }
    }//GEN-LAST:event_AvanzarExamenes

    /**
     * Obtiene el nombre del curso seleccionado y lo envia para obtener los
     * grupos de dicho curso.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AvanzarCursos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvanzarCursos
        if (lstCursos.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un curso.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(1);
            String nombreCurso = String.valueOf(lstCursos.getSelectedValue());
            consultarGrupos(nombreCurso);
        }
    }//GEN-LAST:event_AvanzarCursos

    /**
     * Limpia la vista, libera la memoria y regresa a la vista principal.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void Cancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cancelar
        padre.mostrarVista(Vista.HOME);
        limpiar();
        controlVista.liberarMemoria();
    }//GEN-LAST:event_Cancelar

    /**
     * Regresa a la seccion de cursos y limpia la tabla de grupos.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void RetrocederGrupos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrocederGrupos
        tbpCalificaciones.setSelectedIndex(0);
        ((DefaultTableModel) tblGrupos.getModel()).setRowCount(0);
    }//GEN-LAST:event_RetrocederGrupos

    /**
     * Obtiene el indice del grupo seleccionado y lo envia para obtener los
     * alumnos de dicho grupo.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AvanzarGrupos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvanzarGrupos
        if (tblGrupos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un grupo.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(2);
            consultarAlumnos(tblGrupos.getSelectedRow());
        }
    }//GEN-LAST:event_AvanzarGrupos

    /**
     * Regresa a la seccion de grupos y limpia la tabla de alumnos.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void RetrocederAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrocederAlumnos
        tbpCalificaciones.setSelectedIndex(1);
        ((DefaultTableModel) tblAlumnos.getModel()).setRowCount(0);
    }//GEN-LAST:event_RetrocederAlumnos

    /**
     * Obtiene el indice del alumno seleccionado y lo envia para obtener los
     * exámenes de dicho alumno.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void AvanzarAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvanzarAlumnos
        if (tblAlumnos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un alumno.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(3);
            consultarExamenes(tblAlumnos.getSelectedRow());
        }
    }//GEN-LAST:event_AvanzarAlumnos

    /**
     * Regresa a la sección de alumnos y limpia la tabla de exámenes.
     *
     * @param evt Recibe el evento del botón que lo activo.
     */
    private void RetrocederExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrocederExamenes
        tbpCalificaciones.setSelectedIndex(2);
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
    }//GEN-LAST:event_RetrocederExamenes

    /**
     * Regresa a la sección de exámenes y limpia los componentes de la sección
     * de calificación.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void RetrocederCalificacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RetrocederCalificacion
        tbpCalificaciones.setSelectedIndex(3);
    }//GEN-LAST:event_RetrocederCalificacion

    @Override
    public void mostrarVista(Vista vista) {
        //No implementado.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        // No implementado.
    }

    /**
     * Muestra un mensaje de confirmación cuando se quiere cambiar de vista.
     *
     * @return Retorna una variable de tipo boleana que indicara si, se desea
     * cambiar de vista o no.
     */
    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        // No implementado
        return null;
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado.
    }

    /**
     * Este método es invocado cuando se muestra por primera vez esta vista.
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
        // No implementado.
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        // No implementado.
    }
}
