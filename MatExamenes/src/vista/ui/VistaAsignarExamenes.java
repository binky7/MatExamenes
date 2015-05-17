/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
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
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.ReactivoAsignadoPK;
import modelo.dto.ReactivoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVAsignarExamenes;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author ivan
 */
public class VistaAsignarExamenes extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    private CVAsignarExamenes controlVista;
    private InterfaceVista padre;

    /**
     * Creates new form VistaAsignarExamenes
     */
    public VistaAsignarExamenes() {
        initComponents();
        this.addAncestorListener(this);
        lstGrupos.setModel(new DefaultListModel());
        lstClaves.setModel(new DefaultListModel());
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    public void setControlador(CVAsignarExamenes controlVista) {
        this.controlVista = controlVista;
    }

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

    private void mostrarCursos(List<CursoDTO> listaCursos) {
        cbCursos.removeAllItems();
        for (CursoDTO curso : listaCursos) {
            ((DefaultComboBoxModel) cbCursos.getModel())
                    .addElement(curso.getNombre());
        }

        cbCursos.setSelectedIndex(-1);
    }

    private void consultarExamenes(CursoDTO curso) {
        UsuarioDTO usuarioActual = padre.obtenerUsuarioActual();
        List<ExamenDTO> listaExamenes = controlVista
                .obtenerExamenesPorCurso(curso, usuarioActual);
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        model.setRowCount(0);

        if (listaExamenes != null && !listaExamenes.isEmpty()) {
            mostrarExamenes(listaExamenes);
        } else {
            JOptionPane.showMessageDialog(this, "No hay exámenes");
        }
    }

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

    private void mostrarGrupos(List<GrupoDTO> listaGrupos) {
        DefaultListModel modeloLista = (DefaultListModel) lstGrupos.getModel();
        modeloLista.clear();

        for (GrupoDTO grupo : listaGrupos) {
            String nombreGrupo = grupo.getGrado() + grupo.getNombre() + grupo.getTurno();
            modeloLista.addElement(nombreGrupo);
        }
    }

    private void consultarClaves() {
        ExamenDTO examen = controlVista.obtenerExamen(tblExamenes.getSelectedRow());
        if (examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            if (claves != null) {
                mostrarClaves(claves);
            } else {
                System.out.println("Error claves");
            }
        } else {
            System.out.println("Error examen");
        }
    }

    private void mostrarClaves(List<ClaveExamenDTO> claves) {
        DefaultListModel modeloClaves = (DefaultListModel) lstClaves.getModel();
        modeloClaves.clear();

        for (ClaveExamenDTO clave : claves) {
            modeloClaves.addElement("Clave " + clave.getId().getClave());
        }
    }

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

    private void agregarAsignacion(List<Integer> indexes) {
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

    private void removerAsignacion(List<Integer> indexes) {
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

    private void asignarExamen() {
        List<ExamenAsignadoDTO> listaExamenesAsignados = encapsularExamenAsignado();

        if (listaExamenesAsignados != null && !listaExamenesAsignados.isEmpty()) {
            boolean ok = controlVista.asignarExamenes(listaExamenesAsignados);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Exámenes asignados");
                limpiar();
                padre.mostrarVista(Vista.HOME);
            } else {
                JOptionPane.showMessageDialog(this, "Error al asignar examenes.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Error al encapsular examenes.");
        }
    }

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
            //El alumno que tendra el examen.... (no le pongas null)
            examenAsignado.setAlumno(alumno);
            //El examen que sera asignado al alumno
            examenAsignado.setExamen(examen);
            //Aqui le pones el tiempo
            examenAsignado.setTiempo((Integer) jsTiempo.getValue());

            int idClave = Integer.parseInt(tblAsignacionesFinal.getValueAt(i, 4)
                    .toString()
                    .substring(6));

            //Obtener la clave que se asignó de la lista de claves que tiene el examen
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
                //Se crea el id para el reactivo
                ReactivoAsignadoPK pk = new ReactivoAsignadoPK();
                //Aqui se pone el id del reactivo
                pk.setIdReactivo(claveExamen.getReactivos().get(j).getId());
                //Se guarda el id
                reactivo.setId(pk);
                //redaccion...
                reactivo.setRedaccionReactivo(claveExamen.getReactivos().get(j).getRedaccion());
                //respuesta...
                reactivo.setRespuestaAlumno(null);
                //respuesta reactivo...
                reactivo.setRespuestaReactivo(claveExamen.getReactivos().get(j).getRespuesta());
                //La lista de opciones de los reactivos...
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

    private List<ReactivoAsignadoDTO> encapsularReactivosAsignados(
            ExamenDTO examen, int idClave, ExamenAsignadoDTO examenAsignado) {
        List<ClaveExamenDTO> listaClavesExamen = examen.getClaves();
        ClaveExamenDTO claveExamen = null;

        List<ReactivoAsignadoDTO> listaReactivosAsignados
                = new ArrayList<ReactivoAsignadoDTO>();

        //Obtener la clave que se asignó de la lista de claves que tiene el examen
        for (ClaveExamenDTO clave : listaClavesExamen) {
            if (idClave == clave.getId().getClave()) {
                claveExamen = clave;
            }
        }

        List<ReactivoDTO> listaReactivos = claveExamen.getReactivos();
        //Se encapsulan los reactivos asignados a partir de la información
        //de los reactivos de la clave del examen
        for (ReactivoDTO reactivo : listaReactivos) {
            ReactivoAsignadoDTO reactivoAsignado = new ReactivoAsignadoDTO();

            ReactivoAsignadoPK claveReactivoAsignado = new ReactivoAsignadoPK();
            claveReactivoAsignado.setIdExamenAsignado(examenAsignado.getId());
            claveReactivoAsignado.setIdReactivo(reactivo.getId());
            reactivoAsignado.setId(claveReactivoAsignado);

            reactivoAsignado.setExamen(examenAsignado);
            reactivoAsignado.setRedaccionReactivo(reactivo.getRedaccion());
            reactivoAsignado.setOpcionesReactivo(reactivo.getOpciones());
            reactivoAsignado.setRespuestaReactivo(reactivo.getRespuesta());

            listaReactivosAsignados.add(reactivoAsignado);
        }

        return listaReactivosAsignados;
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
                + "quieres cancelar la operación?\nTodos los cambios no "
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

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if(!consultarCursos()) {
            JOptionPane.showMessageDialog(this, "No hay cursos disponibles.");
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        tbpAsignarExamenes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        lblExamenes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        btnSiguienteExamenes = new javax.swing.JButton();
        btnConsultarExamenes = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblGrupos = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGrupos = new javax.swing.JList();
        btnAnteriorGrupos = new javax.swing.JButton();
        btnSiguienteGrupos = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblClaves = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstClaves = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAsignarClave = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblAsignaciones = new javax.swing.JTable();
        btnRemoverAsignacion = new javax.swing.JButton();
        btnSiguienteAsignaciones = new javax.swing.JButton();
        btnAnteriorAsignaciones = new javax.swing.JButton();
        btnSeleccionarAlumnos = new javax.swing.JButton();
        btnDeseleccionarAlumnos = new javax.swing.JButton();
        btnSeleccionarAsignaciones = new javax.swing.JButton();
        btnDeseleccionarAsignaciones = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jsTiempo = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtfGrupo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfCurso = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtfExamen = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
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
        tbpAsignarExamenes.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tbpAsignarExamenesStateChanged(evt);
            }
        });

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos");

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursos.setSelectedIndex(-1);
        cbCursos.setPreferredSize(new java.awt.Dimension(200, 30));
        cbCursos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCursosItemStateChanged(evt);
            }
        });
        cbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursosActionPerformed(evt);
            }
        });

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblExamenes.setText("Exámenes");

        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Título", "Fecha creación", "Fecha Modificación", "Autor"
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
                btnConsultarExamenesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSiguienteExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCursos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsultarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblExamenes))
                        .addGap(0, 352, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        tbpAsignarExamenes.addTab("Exámenes", jPanel1);

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnteriorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(265, 265, 265)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(359, 359, 359)
                                .addComponent(lblGrupos)))
                        .addGap(0, 262, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(lblGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnteriorGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguienteGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpAsignarExamenes.addTab("Grupos", jPanel2);

        jPanel3.setPreferredSize(new java.awt.Dimension(775, 477));

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

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Alumnos");

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
                btnAsignarClaveActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Asignaciones");

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
                btnRemoverAsignacionActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAsignarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(517, 517, 517)
                        .addComponent(btnAnteriorAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRemoverAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSeleccionarAsignaciones)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeseleccionarAsignaciones)
                        .addGap(21, 21, 21))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblClaves)
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSeleccionarAlumnos)
                .addGap(18, 18, 18)
                .addComponent(btnDeseleccionarAlumnos)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClaves)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(btnSeleccionarAlumnos)
                        .addComponent(btnDeseleccionarAlumnos)))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(btnAsignarClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btnDeseleccionarAsignaciones)
                    .addComponent(btnSeleccionarAsignaciones))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverAsignacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAnteriorAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSiguienteAsignaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tbpAsignarExamenes.addTab("Asignaciones", jPanel3);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Tiempo límite:");

        jsTiempo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jsTiempo.setModel(new javax.swing.SpinnerNumberModel(50, 50, 120, 1));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("minutos");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la asignación"));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Grupo:");

        txtfGrupo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfGrupo.setEnabled(false);
        txtfGrupo.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Examen:");

        txtfCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfCurso.setEnabled(false);
        txtfCurso.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setText("Curso:");

        txtfExamen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfExamen.setEnabled(false);
        txtfExamen.setPreferredSize(new java.awt.Dimension(150, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Alumnos:");

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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfExamen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtfGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtfCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtfExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
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
                btnGuardarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jsTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAnteriorTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jsTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(29, 29, 29)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnteriorTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        JFormattedTextField txt = ((JSpinner.NumberEditor) jsTiempo.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);

        tbpAsignarExamenes.addTab("Tiempo límite", jPanel4);

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

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tbpAsignarExamenesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tbpAsignarExamenesStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_tbpAsignarExamenesStateChanged

    private void cbCursosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCursosItemStateChanged
        // TODO add your handling code here:
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
    }//GEN-LAST:event_cbCursosItemStateChanged

    private void cbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursosActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cbCursosActionPerformed

    private void btnConsultarExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarExamenesActionPerformed
        // TODO add your handling code here:
        if (cbCursos.getSelectedIndex() != -1) {
            CursoDTO curso = controlVista.obtenerCurso(cbCursos.getSelectedIndex());
            consultarExamenes(curso);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un curso");
        }
    }//GEN-LAST:event_btnConsultarExamenesActionPerformed

    private void btnSiguienteExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteExamenesActionPerformed
        // TODO add your handling code here:
        if (cbCursos.getSelectedIndex() != -1) {
            if (tblExamenes.getSelectedRow() != -1) {
                if (consultarGruposPorCurso()) {
                    tbpAsignarExamenes.setSelectedIndex(1);

                } else {
                    JOptionPane.showMessageDialog(this, "No hay grupos asociados con el curso.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un examen");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un curso");
        }
    }//GEN-LAST:event_btnSiguienteExamenesActionPerformed

    private void btnAnteriorGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorGruposActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(0);
    }//GEN-LAST:event_btnAnteriorGruposActionPerformed

    private void btnSiguienteGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteGruposActionPerformed
        // TODO add your handling code here:
        if (!lstGrupos.isSelectionEmpty()) {
            if (consultarAlumnos()) {
                consultarClaves();
                tbpAsignarExamenes.setSelectedIndex(2);
            } else {
                JOptionPane.showMessageDialog(this, "El grupo seleccionado no tiene alumnos.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un grupo");
        }
    }//GEN-LAST:event_btnSiguienteGruposActionPerformed

    private void btnAnteriorAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorAsignacionesActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(1);
    }//GEN-LAST:event_btnAnteriorAsignacionesActionPerformed

    private void btnSiguienteAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteAsignacionesActionPerformed
        // TODO add your handling code here:
        if (tblAsignaciones.getRowCount() != 0) {
            tbpAsignarExamenes.setSelectedIndex(3);
            mostrarDatos();
        } else {
            JOptionPane.showMessageDialog(this, "Agrega asignaciones");
        }
    }//GEN-LAST:event_btnSiguienteAsignacionesActionPerformed

    private void btnAnteriorTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorTiempoActionPerformed
        // TODO add your handling code here:
        tbpAsignarExamenes.setSelectedIndex(2);
    }//GEN-LAST:event_btnAnteriorTiempoActionPerformed

    private void btnAsignarClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarClaveActionPerformed
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
                        "Selecciona al menos un alumno", "Advertencia", 1);
            } else {

                agregarAsignacion(indexes);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Selecciona una clave", "Advertencia", 1);
        }
    }//GEN-LAST:event_btnAsignarClaveActionPerformed

    private void btnRemoverAsignacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverAsignacionActionPerformed
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
                    "Selecciona al menos una asignación", "Advertencia", 1);
        } else {
            removerAsignacion(indexes);
        }
    }//GEN-LAST:event_btnRemoverAsignacionActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        asignarExamen();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSeleccionarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAlumnosActionPerformed
        // TODO add your handling code here:
        int cont = tblAlumnos.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(false)) {
                ((DefaultTableModel)tblAlumnos.getModel()).setValueAt(true, i, 0);
            }
        }
    }//GEN-LAST:event_btnSeleccionarAlumnosActionPerformed

    private void btnDeseleccionarAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeseleccionarAlumnosActionPerformed
        // TODO add your handling code here:
        int cont = tblAlumnos.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                ((DefaultTableModel)tblAlumnos.getModel()).setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_btnDeseleccionarAlumnosActionPerformed

    private void btnSeleccionarAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarAsignacionesActionPerformed
        // TODO add your handling code here:
        int cont = tblAsignaciones.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAsignaciones.getValueAt(i, 0).equals(false)) {
                ((DefaultTableModel)tblAsignaciones.getModel()).setValueAt(true, i, 0);
            }
        }
    }//GEN-LAST:event_btnSeleccionarAsignacionesActionPerformed

    private void btnDeseleccionarAsignacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeseleccionarAsignacionesActionPerformed
        // TODO add your handling code here:
        int cont = tblAsignaciones.getRowCount();

        for (int i = 0; i < cont; i++) {
            if (tblAsignaciones.getValueAt(i, 0).equals(true)) {
                ((DefaultTableModel)tblAsignaciones.getModel()).setValueAt(false, i, 0);
            }
        }
    }//GEN-LAST:event_btnDeseleccionarAsignacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnteriorAsignaciones;
    private javax.swing.JButton btnAnteriorGrupos;
    private javax.swing.JButton btnAnteriorTiempo;
    private javax.swing.JButton btnAsignarClave;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultarExamenes;
    private javax.swing.JButton btnDeseleccionarAlumnos;
    private javax.swing.JButton btnDeseleccionarAsignaciones;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRemoverAsignacion;
    private javax.swing.JButton btnSeleccionarAlumnos;
    private javax.swing.JButton btnSeleccionarAsignaciones;
    private javax.swing.JButton btnSiguienteAsignaciones;
    private javax.swing.JButton btnSiguienteExamenes;
    private javax.swing.JButton btnSiguienteGrupos;
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSpinner jsTiempo;
    private javax.swing.JLabel lblClaves;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblExamenes;
    private javax.swing.JLabel lblGrupos;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lstClaves;
    private javax.swing.JList lstGrupos;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblAsignaciones;
    private javax.swing.JTable tblAsignacionesFinal;
    private javax.swing.JTable tblExamenes;
    private javax.swing.JTabbedPane tbpAsignarExamenes;
    private javax.swing.JTextField txtfCurso;
    private javax.swing.JTextField txtfExamen;
    private javax.swing.JTextField txtfGrupo;
    // End of variables declaration//GEN-END:variables
}
