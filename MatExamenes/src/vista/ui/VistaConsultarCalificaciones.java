/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author FernandoEnrique
 */
public class VistaConsultarCalificaciones extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    private CVConsultarCalificaciones controlVista;
    private InterfaceVista padre;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;

    /**
     * Creates new form VistaConsultarCalificaciones
     */
    public VistaConsultarCalificaciones() {
        initComponents();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        addAncestorListener(this);
        lblCorrecto.setVisible(false);
    }

    public void setControlador(CVConsultarCalificaciones controlVista) {
        this.controlVista = controlVista;
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Limpia los campos de la vista
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

    public void mostrarCursos(List<CursoDTO> cursos) {
        DefaultListModel listModelCursos = new DefaultListModel();
        for (CursoDTO curso : cursos) {
            listModelCursos.addElement(curso.getNombre());
        }
        lstCursos.setModel(listModelCursos);
    }

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

    public void mostrarExamenes(List<ExamenAsignadoDTO> examenes) {
        DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
        for (ExamenAsignadoDTO examen : examenes) {
            Object[] fila = new Object[4];
            fila[0] = examen.getId().getIdExamen();
            fila[1] = examen.getExamen().getTitulo();
            fila[2] = examen.getCalificacion();
            fila[3] = examen.getFechaAsignacion();
            modelo.addRow(fila);
        }
    }

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

    public void mostrarExamen(ExamenAsignadoDTO examen) {
        txtfAlumno.setText(examen.getAlumno().getApellidoPaterno() + " "
                + examen.getAlumno().getApellidoMaterno() + " "
                + examen.getAlumno().getNombre());
        txtfTitulo.setText(examen.getExamen().getTitulo());
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
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
                btnSiguienteCursosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCursosLayout = new javax.swing.GroupLayout(pnlCursos);
        pnlCursos.setLayout(pnlCursosLayout);
        pnlCursosLayout.setHorizontalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCursosLayout.createSequentialGroup()
                .addGroup(pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addComponent(lblCursos))
                    .addGroup(pnlCursosLayout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(318, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnSiguienteCursos)
                .addGap(38, 38, 38))
        );
        pnlCursosLayout.setVerticalGroup(
            pnlCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCursosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblCursos)
                .addGap(70, 70, 70)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
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
                btnAnteriorGruposActionPerformed(evt);
            }
        });

        btnSiguienteGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteGrupos.setText("Siguiente");
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
                        .addGap(344, 344, 344)
                        .addComponent(lblGrupos))
                    .addGroup(pnlGruposLayout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(213, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGruposLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnteriorGrupos)
                .addGap(18, 18, 18)
                .addComponent(btnSiguienteGrupos)
                .addGap(24, 24, 24))
        );
        pnlGruposLayout.setVerticalGroup(
            pnlGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGruposLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGrupos)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
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
                btnAnteriorAlumnosActionPerformed(evt);
            }
        });

        btnSiguienteAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteAlumnos.setText("Siguiente");
        btnSiguienteAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAlumnosLayout = new javax.swing.GroupLayout(pnlAlumnos);
        pnlAlumnos.setLayout(pnlAlumnosLayout);
        pnlAlumnosLayout.setHorizontalGroup(
            pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAlumnosLayout.createSequentialGroup()
                .addGroup(pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAlumnosLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(lblAlumnos))
                    .addGroup(pnlAlumnosLayout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(pnlAlumnosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAnteriorAlumnos)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteAlumnos)))
                .addContainerGap(29, Short.MAX_VALUE))
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
                btnAnteriorExamenesActionPerformed(evt);
            }
        });

        btnSiguienteExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteExamenes.setText("Siguiente");
        btnSiguienteExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteExamenesActionPerformed(evt);
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
                        .addGap(51, 51, 51)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlExamenesLayout.createSequentialGroup()
                        .addGap(348, 348, 348)
                        .addComponent(lblExamenes)))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        pnlExamenesLayout.setVerticalGroup(
            pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExamenesLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblExamenes)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(pnlExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteExamenes)
                    .addComponent(btnAnteriorExamenes))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Examenes", pnlExamenes);

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
                btnAnteriorCalificacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlExamenLayout = new javax.swing.GroupLayout(pnlExamen);
        pnlExamen.setLayout(pnlExamenLayout);
        pnlExamenLayout.setHorizontalGroup(
            pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExamenLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnteriorCalificacion)
                .addGap(146, 146, 146))
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
                            .addComponent(txtfAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExamenLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblReactivos)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRedaccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRespAlumno)
                    .addComponent(lblRespCorrecta)
                    .addComponent(txtfRespCorrecta, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addComponent(txtfRespAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCorrecto)))
                .addGap(36, 36, 36))
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
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addGroup(pnlExamenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlExamenLayout.createSequentialGroup()
                                .addComponent(lblReactivos)
                                .addGap(13, 13, 13)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))
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
                                .addGap(45, 45, 45)))
                        .addComponent(btnAnteriorCalificacion)
                        .addGap(24, 24, 24))
                    .addGroup(pnlExamenLayout.createSequentialGroup()
                        .addComponent(lblRedaccion)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
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
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tbpCalificaciones)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblTitulo)
                                .addGap(298, 298, 298))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnCancelar)
                                .addGap(56, 56, 56))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(tbpCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteExamenesActionPerformed
        if (tblExamenes.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un examen.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(4);
            consultarExamen(tblExamenes.getSelectedRow());
        }
    }//GEN-LAST:event_btnSiguienteExamenesActionPerformed

    private void btnSiguienteCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteCursosActionPerformed
        if (lstCursos.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un curso.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(1);
            String nombreCurso = String.valueOf(lstCursos.getSelectedValue());
            consultarGrupos(nombreCurso);
        }
    }//GEN-LAST:event_btnSiguienteCursosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
        controlVista.liberarMemoria();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAnteriorGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorGruposActionPerformed
        tbpCalificaciones.setSelectedIndex(0);
        ((DefaultTableModel) tblGrupos.getModel()).setRowCount(0);
    }//GEN-LAST:event_btnAnteriorGruposActionPerformed

    private void btnSiguienteGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteGruposActionPerformed
        if (tblGrupos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un grupo.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(2);
            consultarAlumnos(tblGrupos.getSelectedRow());
        }
    }//GEN-LAST:event_btnSiguienteGruposActionPerformed

    private void btnAnteriorAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorAlumnosActionPerformed
        tbpCalificaciones.setSelectedIndex(1);
        ((DefaultTableModel) tblAlumnos.getModel()).removeRow(0);
    }//GEN-LAST:event_btnAnteriorAlumnosActionPerformed

    private void btnSiguienteAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteAlumnosActionPerformed
        if (tblAlumnos.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un alumno.",
                    "Advertencia", 2);
        } else {
            tbpCalificaciones.setSelectedIndex(3);
            consultarExamenes(tblAlumnos.getSelectedRow());
        }
    }//GEN-LAST:event_btnSiguienteAlumnosActionPerformed

    private void btnAnteriorExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorExamenesActionPerformed
        tbpCalificaciones.setSelectedIndex(2);
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
    }//GEN-LAST:event_btnAnteriorExamenesActionPerformed

    private void btnAnteriorCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorCalificacionActionPerformed
        tbpCalificaciones.setSelectedIndex(3);
    }//GEN-LAST:event_btnAnteriorCalificacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnteriorAlumnos;
    private javax.swing.JButton btnAnteriorCalificacion;
    private javax.swing.JButton btnAnteriorExamenes;
    private javax.swing.JButton btnAnteriorGrupos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSiguienteAlumnos;
    private javax.swing.JButton btnSiguienteCursos;
    private javax.swing.JButton btnSiguienteExamenes;
    private javax.swing.JButton btnSiguienteGrupos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblAlumno;
    private javax.swing.JLabel lblAlumnos;
    private javax.swing.JLabel lblCalificacion;
    private javax.swing.JLabel lblCorrecto;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblExamenes;
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblReactivos;
    private javax.swing.JLabel lblRedaccion;
    private javax.swing.JLabel lblRespAlumno;
    private javax.swing.JLabel lblRespCorrecta;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloExamen;
    private javax.swing.JList lstCursos;
    private javax.swing.JList lstReactivos;
    private javax.swing.JPanel pnlAlumnos;
    private javax.swing.JPanel pnlCursos;
    private javax.swing.JPanel pnlExamen;
    private javax.swing.JPanel pnlExamenes;
    private javax.swing.JPanel pnlGrupos;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblGrupos;
    private javax.swing.JTabbedPane tbpCalificaciones;
    private javax.swing.JTextArea txtaRedaccion;
    private javax.swing.JTextField txtfAlumno;
    private javax.swing.JTextField txtfCalificacion;
    private javax.swing.JTextField txtfRespAlumno;
    private javax.swing.JTextField txtfRespCorrecta;
    private javax.swing.JTextField txtfTitulo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVista(Vista vista) {
        //No implementado.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        // No implementado.
    }

    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
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
