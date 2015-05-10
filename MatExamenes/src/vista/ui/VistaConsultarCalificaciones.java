/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
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

    /**
     * Creates new form VistaConsultarCalificaciones
     */
    public VistaConsultarCalificaciones() {
        initComponents();
        addAncestorListener(this);
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

    }

    public void consultarCursos() {
        UsuarioDTO actual = padre.obtenerUsuarioActual();
        List<CursoDTO> cursos = controlVista.obtenerCursos(actual);
        if (cursos == null || cursos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay cursos!", "Advertencia", 1);
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
            JOptionPane.showMessageDialog(this, "No hay grupos para este curso!", "Advertencia", 1);
            limpiar();
        } else {
            mostrarGrupos(grupos);
        }
    }

    public void mostrarGrupos(List<GrupoDTO> grupos) {
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (GrupoDTO grupo : grupos) {
            Object[] fila = new Object[4];
            fila[0] = false;
            fila[1] = grupo.getGrado();
            fila[2] = grupo.getNombre();
            fila[3] = grupo.getTurno();
            modelo.addRow(fila);
        }
        tblGrupos.setModel(modelo);
    }

    public void consultarAlumnos(Integer indexGrupo) {
        List<UsuarioDTO> alumnos = controlVista.obtenerAlumnos(indexGrupo);
        if (alumnos == null || alumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos en este grupo!", "Advertencia", 1);
            limpiar();
        } else {
            mostrarAlumnos(alumnos);
        }
    }

    public void mostrarAlumnos(List<UsuarioDTO> alumnos) {
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (UsuarioDTO alumno : alumnos) {
            Object[] fila = new Object[5];
            fila[0] = false;
            fila[1] = alumno.getId();
            fila[2] = alumno.getApellidoPaterno();
            fila[3] = alumno.getApellidoMaterno();
            fila[4] = alumno.getNombre();
            modelo.addRow(fila);
        }
        tblAlumnos.setModel(modelo);
    }

    public void consultarExamenes(Integer indexAlumno) {
        List<ExamenAsignadoDTO> examenes = controlVista.obtenerExamenes(indexAlumno);
        if (examenes == null || examenes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay examenes contestados por este alumno!", "Advertencia", 1);
            limpiar();
        } else {
            mostrarExamenes(examenes);
        }
    }

    public void mostrarExamenes(List<ExamenAsignadoDTO> examenes) {
        DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (ExamenAsignadoDTO examen : examenes) {
            Object[] fila = new Object[5];
            fila[0] = false;
            fila[1] = examen.getId();
            fila[2] = examen.getExamen().getTitulo();
            fila[3] = examen.getCalificacion();
            fila[4] = examen.getFechaAsignacion();
            modelo.addRow(fila);
        }
        tblExamenes.setModel(modelo);
    }
    
    public void consultarExamen(Integer indexExamen){
        ExamenAsignadoDTO examen = controlVista.obtenerExamen(indexExamen);
        if (examen == null) {
            JOptionPane.showMessageDialog(this, "No se pudo obtener el examen!", "Advertencia", 1);
            limpiar();
        } else {
            mostrarExamen(examen);
        }
    }
    
    public void mostrarExamen(ExamenAsignadoDTO examen){
        DefaultTableModel modelo = (DefaultTableModel) tblReactivos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (ReactivoAsignadoDTO reactivo : examen.getReactivos()) {
            Object[] fila = new Object[4];
            fila[0] = reactivo.getRedaccionReactivo();
            fila[1] = reactivo.getRespuestaAlumno();
            fila[2] = reactivo.getRespuestaReactivo();
            if(reactivo.getRespuestaAlumno().equalsIgnoreCase(reactivo.getRespuestaReactivo())){
                fila[3] = "No";
            }else{
                fila[3] = "Si";
            }
            modelo.addRow(fila);
        }
        tblReactivos.setModel(modelo);
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
        jPanel1 = new javax.swing.JPanel();
        lblCursos = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstCursos = new javax.swing.JList();
        btnSiguienteCursos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblGrupos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGrupos = new javax.swing.JTable();
        btnSiguienteGrupos = new javax.swing.JButton();
        btnAnteriorGrupos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblAlumnos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnSiguienteAlumnos = new javax.swing.JButton();
        btnAnteriorAlumnos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        lblExamenes = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        btnSiguienteExamenes = new javax.swing.JButton();
        btnAnteriorExamenes = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReactivos = new javax.swing.JTable();
        btnAnteriorCalificacion = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(338, 338, 338)
                                .addComponent(lblCursos))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(271, 271, 271)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 306, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSiguienteCursos)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(lblCursos)
                .addGap(70, 70, 70)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(btnSiguienteCursos)
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Cursos", jPanel1);

        lblGrupos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrupos.setText("Grupos");

        tblGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Grado", "Nombre", "Turno"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblGrupos);

        btnSiguienteGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteGrupos.setText("Siguiente");
        btnSiguienteGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteGruposActionPerformed(evt);
            }
        });

        btnAnteriorGrupos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorGrupos.setText("Anterior");
        btnAnteriorGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(344, 344, 344)
                                .addComponent(lblGrupos))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 201, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnteriorGrupos)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteGrupos)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblGrupos)
                .addGap(51, 51, 51)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteGrupos)
                    .addComponent(btnAnteriorGrupos))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Grupos", jPanel2);

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Apellido P", "Apellido M", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblAlumnos);

        btnSiguienteAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteAlumnos.setText("Siguiente");
        btnSiguienteAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteAlumnosActionPerformed(evt);
            }
        });

        btnAnteriorAlumnos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorAlumnos.setText("Anterior");
        btnAnteriorAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorAlumnosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(323, 323, 323)
                                .addComponent(lblAlumnos))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 227, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnteriorAlumnos)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteAlumnos)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAlumnos)
                .addGap(45, 45, 45)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 151, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteAlumnos)
                    .addComponent(btnAnteriorAlumnos))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Alumnos", jPanel3);

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamenes.setText("Examenes");

        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Titulo", "Calificacion", "Fecha de Asignacion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblExamenes);

        btnSiguienteExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguienteExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/siguiente24.png"))); // NOI18N
        btnSiguienteExamenes.setText("Siguiente");
        btnSiguienteExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteExamenesActionPerformed(evt);
            }
        });

        btnAnteriorExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorExamenes.setText("Anterior");
        btnAnteriorExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorExamenesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnteriorExamenes)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteExamenes))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(348, 348, 348)
                                .addComponent(lblExamenes)))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblExamenes)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSiguienteExamenes)
                    .addComponent(btnAnteriorExamenes))
                .addContainerGap())
        );

        tbpCalificaciones.addTab("Examenes", jPanel4);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Alumno:");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Titulo del examen:");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Calificacion:");

        jTextField1.setEnabled(false);

        jTextField2.setEnabled(false);

        jTextField3.setEnabled(false);

        tblReactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pregunta", "Respuesta del alumno", "Respuesta correcta", "Correcto Si/No"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblReactivos);

        btnAnteriorCalificacion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAnteriorCalificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/atras24.png"))); // NOI18N
        btnAnteriorCalificacion.setText("Anterior");
        btnAnteriorCalificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorCalificacionActionPerformed(evt);
            }
        });

        btnSalir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField2))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnteriorCalificacion)
                .addGap(18, 18, 18)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnteriorCalificacion)
                    .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        tbpCalificaciones.addTab("Calificaciones", jPanel5);

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
                        .addComponent(btnCancelar)
                        .addGap(23, 23, 23))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(298, 298, 298))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tbpCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSiguienteExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteExamenesActionPerformed
        int index = -1;
        int contador = tblExamenes.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblExamenes.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un examen",
                    "Advertencia", 1);
        } else {
            tbpCalificaciones.setSelectedIndex(4);
            consultarExamen(index);
        }
    }//GEN-LAST:event_btnSiguienteExamenesActionPerformed

    private void btnSiguienteCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteCursosActionPerformed
        if (lstCursos.isSelectionEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona un curso!", "Advertencia", 1);
        } else {
            tbpCalificaciones.setSelectedIndex(1);
            String nombreCurso = String.valueOf(lstCursos.getSelectedValue());
            consultarGrupos(nombreCurso);
        }
    }//GEN-LAST:event_btnSiguienteCursosActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAnteriorGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorGruposActionPerformed
        tbpCalificaciones.setSelectedIndex(0);
        DefaultTableModel modelo = (DefaultTableModel) tblGrupos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblGrupos.setModel(modelo);
    }//GEN-LAST:event_btnAnteriorGruposActionPerformed

    private void btnSiguienteGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteGruposActionPerformed
        int index = -1;
        int contador = tblGrupos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblGrupos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un grupo",
                    "Advertencia", 1);
        } else {
            tbpCalificaciones.setSelectedIndex(2);
            consultarAlumnos(index);
        }
    }//GEN-LAST:event_btnSiguienteGruposActionPerformed

    private void btnAnteriorAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorAlumnosActionPerformed
        tbpCalificaciones.setSelectedIndex(1);
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblAlumnos.setModel(modelo);
    }//GEN-LAST:event_btnAnteriorAlumnosActionPerformed

    private void btnSiguienteAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteAlumnosActionPerformed
        int index = -1;
        int contador = tblAlumnos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                index = i;
            }
        }
        if (contador == 0 || index == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un alumno",
                    "Advertencia", 1);
        } else {
            tbpCalificaciones.setSelectedIndex(3);
            consultarExamenes(index);
        }
    }//GEN-LAST:event_btnSiguienteAlumnosActionPerformed

    private void btnAnteriorExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorExamenesActionPerformed
        tbpCalificaciones.setSelectedIndex(2);
        DefaultTableModel modelo = (DefaultTableModel) tblExamenes.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblExamenes.setModel(modelo);
    }//GEN-LAST:event_btnAnteriorExamenesActionPerformed

    private void btnAnteriorCalificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorCalificacionActionPerformed
        tbpCalificaciones.setSelectedIndex(3);
    }//GEN-LAST:event_btnAnteriorCalificacionActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnteriorAlumnos;
    private javax.swing.JButton btnAnteriorCalificacion;
    private javax.swing.JButton btnAnteriorExamenes;
    private javax.swing.JButton btnAnteriorGrupos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSiguienteAlumnos;
    private javax.swing.JButton btnSiguienteCursos;
    private javax.swing.JButton btnSiguienteExamenes;
    private javax.swing.JButton btnSiguienteGrupos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lblAlumnos;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblExamenes;
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lstCursos;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTable tblGrupos;
    private javax.swing.JTable tblReactivos;
    private javax.swing.JTabbedPane tbpCalificaciones;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //
    }
}
