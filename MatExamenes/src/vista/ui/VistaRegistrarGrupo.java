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
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerGrupos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceGrupo;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaRegistrarGrupo extends javax.swing.JPanel implements InterfaceVista, InterfaceGrupo {

    private CVMantenerGrupos controlVista;
    private InterfaceVista padre;
    private FrmAgregarAlumnos vistaAgrAlumnos;
    private FrmAgregarMaestro vistaAgrMaestro;

    /**
     * Creates new form CrearNuevoGrupo
     */
    public VistaRegistrarGrupo() {
        initComponents();
    }

    /**
     * Asigna el controlador de vista que tendra la vista.
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
     * Limpia los campos de la vista.
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
        controlVista.liberarMemoriaRegistrar();
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
        DefaultTableModel modelo = (DefaultTableModel) tblAlumnos.getModel();
        int cont = modelo.getRowCount();
        for (int i = 0; i < noFilasAlumnos.size(); i++) {
            modelo.removeRow(noFilasAlumnos.get(i) - i);
        }
        tblAlumnos.setModel(modelo);
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
        lblAlumnos = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        btnAgrAlumnos = new javax.swing.JButton();
        btnRmvAlumnos = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        lblMaestros = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAgrMaestro = new javax.swing.JButton();
        btnRmvMaestro = new javax.swing.JButton();
        lblGrado = new javax.swing.JLabel();
        cbGrado = new javax.swing.JComboBox();
        lblTurno = new javax.swing.JLabel();
        cbTurno = new javax.swing.JComboBox();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblMaestros = new javax.swing.JTable();
        lblTitulo = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre del Grupo:");

        lblAlumnos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblAlumnos.setText("Alumnos");

        txtfNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnAgrAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgrAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrAlumnos(evt);
            }
        });

        btnRmvAlumnos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvAlumnos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRmvAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvAlumnosActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblMaestros.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMaestros.setText("Maestros");

        tblAlumnos.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Nom", "Apellido P.", "Apellido M."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tblAlumnos);

        btnAgrMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAgrMaestro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgrMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgrMaestroActionPerformed(evt);
            }
        });

        btnRmvMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnRmvMaestro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRmvMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRmvMaestroActionPerformed(evt);
            }
        });

        lblGrado.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblGrado.setText("Grado:");

        cbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1ro", "2do", "3ro" }));

        lblTurno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTurno.setText("Turno:");

        cbTurno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Matutino", "Vespertino" }));

        tblMaestros.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tblMaestros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Nom", "Apellido P.", "Apellido M.", "Curso"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tblMaestros);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Grupo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(lblTitulo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(478, 478, 478)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgrAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(btnRmvAlumnos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lblMaestros)
                            .addComponent(lblAlumnos)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblNombre)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(28, 28, 28)
                                        .addComponent(lblGrado)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblTurno)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgrMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRmvMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(37, 37, 37))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(lblGrado)
                    .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTurno)
                    .addComponent(cbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(lblMaestros)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrMaestro)
                        .addGap(18, 18, 18)
                        .addComponent(btnRmvMaestro))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlumnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgrAlumnos)
                        .addGap(18, 18, 18)
                        .addComponent(btnRmvAlumnos))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(56, 56, 56))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Llama al frame de agregar alumnos.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnAgrAlumnos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrAlumnos
        vistaAgrAlumnos = new FrmAgregarAlumnos();
        vistaAgrAlumnos.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgrAlumnos

    /**
     * Llama al frame de agregar maestros.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnAgrMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgrMaestroActionPerformed
        vistaAgrMaestro = new FrmAgregarMaestro();
        vistaAgrMaestro.inicializar(controlVista, this);
    }//GEN-LAST:event_btnAgrMaestroActionPerformed

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
            padre.mostrarVista(Vista.HOME);
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
            Integer id = controlVista.guardarGrupo(grupo);
            if (id != null) {
                JOptionPane.showMessageDialog(this, "Agregado Correctamente!", "Exito", 1);
                padre.mostrarVista(Vista.HOME);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo agregar!", "Advertencia", 2);
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
        int contador = tblAlumnos.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblAlumnos.getValueAt(i, 0).equals(true)) {
                indexes.add(i);
            }
        }
        if (contador == 0 || indexes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un alumno", "Advertencia", 1);
        } else {
            controlVista.removerAlumnos(indexes);
            removerAlumnos(indexes);
        }
    }//GEN-LAST:event_btnRmvAlumnosActionPerformed

    /**
     * Verifica que maestros se seleccionaron de la tabla y obtiene sus indices,
     * los envia al controlador de la vista y al metodo de remover maestros para
     * su eliminacion.
     *
     * @param evt Recibe el evento del boton que lo activo.
     */
    private void btnRmvMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmvMaestroActionPerformed
        String nombreCurso = null;
        int contador = tblMaestros.getRowCount();
        for (int i = 0; i < contador; i++) {
            if (tblMaestros.getValueAt(i, 0).equals(true)) {
                DefaultTableModel model = (DefaultTableModel) tblMaestros.getModel();
                nombreCurso = String.valueOf(model.getValueAt(i, 5));
            }
        }
        if (contador == 0 || nombreCurso == null) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos un maestro", "Advertencia", 1);
        } else {
            controlVista.removerMaestro(nombreCurso);
            removerMaestro(nombreCurso);
        }
    }//GEN-LAST:event_btnRmvMaestroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgrAlumnos;
    private javax.swing.JButton btnAgrMaestro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRmvAlumnos;
    private javax.swing.JButton btnRmvMaestro;
    private javax.swing.JComboBox cbGrado;
    private javax.swing.JComboBox cbTurno;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
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
    public void mostrarEntidad(Object entidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Regresa el padre de la vista.
     *
     * @return Retorna un objeto de tipo InterfaceVista.
     */
    @Override
    public InterfaceVista getPadre() {
        return this;
    }

}
