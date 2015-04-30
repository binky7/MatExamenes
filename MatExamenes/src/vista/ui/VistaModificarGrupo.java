/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.GrupoDTO.Turno;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceGrupo;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaModificarGrupo extends javax.swing.JPanel implements
        InterfaceVista, InterfaceGrupo {

    private CVMantenerGrupos controlVista;
    private InterfaceVista padre;
    private FrmAgregarAlumnos vistaAgregarAlumnos;
    private FrmAgregarMaestro vistaAgregarMaestro;

    /**
     * Creates new form CrearNuevoGrupo
     */
    public VistaModificarGrupo() {
        initComponents();
    }

    /**
     * Asigna el controlador de la vista que tendra la vista.
     *
     * @param controlVista Recibe un objeto de tipo CVMantenerGrupos.
     */
    public void setControlador(CVMantenerGrupos controlVista) {
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
     * Limpia los campos de la vista
     */
    @Override
    public void limpiar() {
        txtfNombre.setText("");
        cbGrado.setSelectedIndex(0);
        cbTurno.setSelectedIndex(0);
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblAlumnos.setModel(modelo);
        modelo = (DefaultTableModel) tblMaestros.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        tblMaestros.setModel(modelo);
        controlVista.liberarMemoriaModificar();
    }

    /**
     * Muestra los datos del grupo en los componentes graficos de la vista.
     *
     * @param grupo Recibe un objeto de tipo GrupoDTO.
     */
    private void mostrarDatos(GrupoDTO grupo) {
        txtfNombre.setText(grupo.getNombre());
        cbGrado.setSelectedIndex(grupo.getGrado() - 1);
        if (grupo.getTurno() == Turno.M) {
            cbTurno.setSelectedIndex(0);
        } else {
            cbTurno.setSelectedIndex(1);
        }
        mostrarAlumnos(grupo.getAlumnos());
        HashMap<CursoDTO, UsuarioDTO> mapa = new HashMap<>();
        DefaultTableModel modelo2 = (DefaultTableModel) tblMaestros.getModel();
        for (int x = modelo2.getRowCount() - 1; x > -1; x--) {
            modelo2.removeRow(x);
        }
        for (CursoDTO curso : grupo.getMaestros().keySet()) {
            UsuarioDTO maestro = grupo.getMaestros().get(curso);
            mapa.put(curso, maestro);
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
        }
        mostrarMaestros(mapa);
    }

    /**
     * Muestra los alumnos en la tabla de alumnos.
     *
     * @param listaAlumnos Recibe la lista de alumnos que mostrara en la tabla
     * de alumnos.
     */
    @Override
    public void mostrarAlumnos(List<UsuarioDTO> listaAlumnos) {
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (int i = 0; i < listaAlumnos.size(); i++) {
            UsuarioDTO alumno = listaAlumnos.get(i);
            Object[] fila = new Object[5];
            fila[0] = false;
            fila[1] = String.valueOf(alumno.getId());
            fila[2] = alumno.getNombre();
            fila[3] = alumno.getApellidoPaterno();
            fila[4] = alumno.getApellidoMaterno();
            modelo.addRow(fila);
        }
        tblAlumnos.setModel(modelo);
    }

    /**
     * Muestra los maestros en la tabla de maestros.
     *
     * @param mapaMaestros Recibe el mapa que contiene los maestros y los cursos
     * que mostrara en la tabla de maestros.
     */
    @Override
    public void mostrarMaestros(HashMap<CursoDTO, UsuarioDTO> mapaMaestros) {
        DefaultTableModel modelo = (DefaultTableModel) tblMaestros.getModel();
        for (int i = modelo.getRowCount() - 1; i > -1; i--) {
            modelo.removeRow(i);
        }
        for (CursoDTO curso : mapaMaestros.keySet()) {
            UsuarioDTO maestro = mapaMaestros.get(curso);
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
            modelo.addRow(fila);
        }
        tblMaestros.setModel(modelo);
    }

    /**
     * Remueve los alumnos de la tabla de alumnos.
     *
     * @param noFilasAlumnos Recibe los numeros de las filas de los alumnos que
     * seran eliminados de la tabla de alumnos.
     */
    public void removerAlumnos(List<Integer> noFilasAlumnos) {
        DefaultTableModel model = (DefaultTableModel) tblAlumnos.getModel();
        int cont = model.getRowCount();
        for (int i = 0; i < noFilasAlumnos.size(); i++) {
            model.removeRow(noFilasAlumnos.get(i) - i);
        }
        tblAlumnos.setModel(model);
    }

    /**
     * Remueve el maestro de la tabla de maestros.
     *
     * @param nombreCurso Recibe el nombre del curso del maestro que sera
     * eliminado de la tabla maestros.
     */
    public void removerMaestro(String nombreCurso) {
        DefaultTableModel modelo = (DefaultTableModel) tblMaestros.getModel();
        int cont = modelo.getRowCount();
        for (int i = 0; i < cont; i++) {
            if (String.valueOf(modelo.getValueAt(i, 5)).equals(nombreCurso)) {
                modelo.removeRow(i);
                break;
            }
        }
        tblMaestros.setModel(modelo);
    }

    /**
     * Valida y encapsula los datos ingresados en los componentes graficos y los
     * convierte en un objeto de tipo GrupoDTO.
     *
     * @return Retorna un objeto de tipo GrupoDTO.
     */
    public GrupoDTO encapsularGrupo() {
        GrupoDTO grupo = new GrupoDTO();
        String nombre = txtfNombre.getText();
        if (Validador.esGrupo(nombre)) {
            grupo.setNombre(nombre);
            grupo.setGrado(cbGrado.getSelectedIndex() + 1);
            if (cbTurno.getSelectedIndex() == 0) {
                grupo.setTurno(GrupoDTO.Turno.M);
            } else {
                grupo.setTurno(GrupoDTO.Turno.V);
            }
            //Guardar alumnos y maestros
        } else {
            grupo = null;
        }
        return grupo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblGrado = new javax.swing.JLabel();
        lblAlumnos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        txtfNombre = new javax.swing.JTextField();
        btnAgrAlumnos = new javax.swing.JButton();
        btnRmvAlumnos = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblTitulo = new javax.swing.JLabel();
        cbGrado = new javax.swing.JComboBox();
        lblTurno = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        lblMaestros = new javax.swing.JLabel();
        btnRmvMaestro = new javax.swing.JButton();
        btnAgrMaestros = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre del Grupo:");

        lblGrado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrado.setText("Grado:");

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        tblAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "A.P.", "A.M.", "Nom"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblAlumnos);

        txtfNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgrAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgrAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrAlumnosActionPerformed(evt);
            }
        });

        btnRmvAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonRemover.png"))); // NOI18N
        btnRmvAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvAlumnosActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonGuardar_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Modificar Grupo");

        cbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1ro", "2do", "3ro" }));

        lblTurno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTurno.setText("Turno:");

        cbTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino", "Vespertino" }));

        tblMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "A.P.", "A.M.", "Nom", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblMaestros);

        lblMaestros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMaestros.setText("Maestros");

        btnRmvMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvMaestro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonRemover.png"))); // NOI18N
        btnRmvMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvMaestroActionPerformed(evt);
            }
        });

        btnAgrMaestros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrMaestros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonAgregar.png"))); // NOI18N
        btnAgrMaestros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrMaestrosActionPerformed(evt);
            }
        });

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
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAlumnos)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnRmvAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgrAlumnos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnRmvMaestro, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnAgrMaestros, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMaestros)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(lblGrado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(43, 43, 43)
                        .addComponent(lblTurno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(99, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblTitulo)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(lblGrado)
                    .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurno)
                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(lblMaestros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrMaestros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRmvMaestro))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlumnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrAlumnos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnRmvAlumnos))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnGuardar))
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Llama al frame de agregar alumnos.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnAgrAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrAlumnosActionPerformed
        vistaAgregarAlumnos = new FrmAgregarAlumnos();
        vistaAgregarAlumnos.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgrAlumnosActionPerformed

    /**
     * Muestra un mensaje de confirmacion para cancelar y regresa a la vista
     * principal.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarGrupo);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Llama a encapsular grupo y lo manda al controlador de la vista para que
     * lo guarde.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        GrupoDTO grupo = encapsularGrupo();
        if (grupo == null) {
            JOptionPane.showMessageDialog(this, "Faltan datos!", "Advertencia", 1);
        } else {
            boolean ok = controlVista.modificarGrupo(grupo);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Grupo modificado", "Exito", 1);
                padre.mostrarVista(Vista.ConsultarGrupo);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar!", "Advertencia", 1);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * Verifica que alumnos se seleccionaron de la tabla y obtiene sus indices,
     * los envia al controlador de la vista y al metodo de remover alumnos para
     * su eliminacion.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnRmvAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmvAlumnosActionPerformed
        List<Integer> indexes = new ArrayList<>();
        int cont = tblAlumnos.getRowCount();
        for (int x = 0; x < cont; x++) {
            if (tblAlumnos.getValueAt(x, 0).equals(true)) {
                indexes.add(x);
            }
        }
        if (cont == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un alumno", "Advertencia", 1);
        } else {
            controlVista.removerAlumnos(indexes);
            removerAlumnos(indexes);
        }
    }//GEN-LAST:event_btnRmvAlumnosActionPerformed

    /**
     * Llama al frame de agregar maestros.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnAgrMaestrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrMaestrosActionPerformed
        vistaAgregarMaestro = new FrmAgregarMaestro();
        vistaAgregarMaestro.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgrMaestrosActionPerformed

    /**
     * Verifica que maestros se seleccionaron de la tabla y obtiene sus indices,
     * los envia al controlador de la vista y al metodo de remover maestros para
     * su eliminacion.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnRmvMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmvMaestroActionPerformed
        String curso = null;
        int cont = tblMaestros.getRowCount();
        for (int x = 0; x < cont; x++) {
            if (tblMaestros.getValueAt(x, 0).equals(true)) {
                DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
                curso = String.valueOf(model.getValueAt(x, 5));
            }
        }
        if (cont == 0 || curso == null) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un maestro", "Advertencia", 1);
        } else {
            controlVista.removerMaestro(curso);
            removerMaestro(curso);
        }
    }//GEN-LAST:event_btnRmvMaestroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgrAlumnos;
    private javax.swing.JButton btnAgrMaestros;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRmvAlumnos;
    private javax.swing.JButton btnRmvMaestro;
    private javax.swing.JComboBox cbGrado;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAlumnos;
    private javax.swing.JLabel lblGrado;
    private javax.swing.JLabel lblMaestros;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTurno;
    private javax.swing.JTable tblAlumnos;
    private javax.swing.JTable tblMaestros;
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

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
        mostrarDatos((GrupoDTO) entidad);
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
    public InterfaceVista getPadre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
