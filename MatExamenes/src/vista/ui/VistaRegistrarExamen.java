/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.ExamenDTO.Permiso;
import modelo.dto.ReactivoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerExamenes;
import vista.controlador.Validador;
import vista.interfaz.InterfaceExamen;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author BoredmanDA
 */
public class VistaRegistrarExamen extends javax.swing.JPanel implements
        InterfaceVista, InterfaceExamen, FocusListener, AncestorListener,
        KeyListener {

    private CVMantenerExamenes controlVista;
    private InterfaceVista padre;
    private final FrmAgregarReactivos frmAgregarReactivos;
    private final FrmVerReactivo frmVerReactivo;

    private final ButtonGroup permiso;

    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;

    private String mensajeDatosIncorrectos;

    private boolean noSelect;

    /**
     * Creates new form RegistrarExamen
     */
    public VistaRegistrarExamen() {
        initComponents();

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));

        lblEstadoNombre.setVisible(false);
        lblEstadoInstrucciones.setVisible(false);

        frmAgregarReactivos = new FrmAgregarReactivos();
        frmAgregarReactivos.setPadre(this);
        frmAgregarReactivos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frmVerReactivo = new FrmVerReactivo();
        frmVerReactivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        permiso = new ButtonGroup();
        permiso.add(rbtnPublico);
        permiso.add(rbtnPrivado);

        txtfNombre.addKeyListener(this);
        txtaInstrucciones.addKeyListener(this);
        
        addAncestorListener(this);

        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!noSelect) {
                    cmbCurso.setEnabled(false);
                    btnDesbloquear.setEnabled(true);
                }
            }

        });
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
        frmVerReactivo.setPadre((JFrame) padre);
    }

    public void setControlador(CVMantenerExamenes controlVista) {
        this.controlVista = controlVista;
        frmAgregarReactivos.setControlador(controlVista);
    }

    private void consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        } else {
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }

    private void mostrarCursos(List<CursoDTO> cursos) {

        cmbCurso.removeAllItems();

        for (CursoDTO curso : cursos) {
            //System.out.println(cmbCurso.getSelectedIndex());
            cmbCurso.addItem(curso.getNombre());
        }

        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }

    private ExamenDTO encapsularExamen() {

        ExamenDTO examen = new ExamenDTO();
        mensajeDatosIncorrectos = "";

        String titulo = txtfNombre.getText();
        String instrucciones = txtaInstrucciones.getText();
        Permiso opPermiso = null;

        boolean ok = true;

        if (Validador.estaVacio(titulo)) {
            ok = false;
            mensajeDatosIncorrectos = "* Título del Examen\n";
            mostrarLabelEstado(txtfNombre, false);
        } else {
            mostrarLabelEstado(txtfNombre, true);
        }

        if (Validador.estaVacio(instrucciones)) {
            ok = false;
            mensajeDatosIncorrectos += "* Instrucciones del examen\n";
            mostrarLabelEstado(txtaInstrucciones, false);
        } else {
            mostrarLabelEstado(txtaInstrucciones, true);
        }

        if (!ok) {
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                    + "siguientes campos necesitan ser corregidos:\n"
                    + mensajeDatosIncorrectos;
        }

        if (permiso.getSelection() == rbtnPublico.getModel()) {
            opPermiso = Permiso.Publico;
        } else if (permiso.getSelection() == rbtnPrivado.getModel()) {
            opPermiso = Permiso.Privado;
        } else {
            //selection == null
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un permiso para el "
                    + "examen\n";
        }

        if (cmbCurso.getSelectedIndex() == -1) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un curso para el "
                    + "examen\n";
        }

        if (tbpClaves.getTabCount() == 0) {
            ok = false;
            mensajeDatosIncorrectos += "El examen debe contener por lo menos "
                    + "una clave";
        } else {
            for (Component comp : tbpClaves.getComponents()) {
                PnlReactivosTab reactivosTab = (PnlReactivosTab) comp;

                if (reactivosTab.sinReactivos()) {
                    ok = false;
                    mensajeDatosIncorrectos += "No puede haber claves de examen sin "
                            + "reactivos\n";
                    break;
                }
            }
        }
        examen.setInstrucciones(instrucciones);
        examen.setPermiso(opPermiso);
        examen.setTitulo(titulo);
        examen.setAutor(padre.obtenerUsuarioActual());
        examen.setFechaCreacion(new Date());
        examen.setFechaModificacion(new Date());

        if (!ok) {
            examen = null;
        } else {
            controlVista.setCurso(cmbCurso.getSelectedIndex());
        }

        return examen;
    }

    private void removerReactivos(List<Integer> indexesReactivo, JTable tabla) {

        Collections.sort(indexesReactivo, Collections.reverseOrder());

        for (int index : indexesReactivo) {
            ((DefaultTableModel) tabla.getModel())
                    .removeRow(index);
        }
    }

    private void mostrarReactivo(ReactivoDTO reactivo) {
        frmVerReactivo.inicializar(reactivo);
    }

    private void mostrarReactivos(List<ReactivoDTO> reactivos, JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();

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

    @Override
    public InterfaceVista getPadre() {
        return padre;
    }

    @Override
    public void limpiar() {
        txtfNombre.setText("");
        txtaInstrucciones.setText("");
        permiso.clearSelection();
        cmbCurso.removeAllItems();
        cmbCurso.setEnabled(true);
        btnDesbloquear.setEnabled(false);
        tbpClaves.removeAll();

        lblEstadoNombre.setVisible(false);
        lblEstadoInstrucciones.setVisible(false);

        controlVista.liberarMemoriaRegistrar();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        tbpClaves = new javax.swing.JTabbedPane();
        lblTitulo1 = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        txtfNombre.addFocusListener(this);
        txtfNombre.setName("lblEstadoNombre");
        lblInstrucciones = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaInstrucciones = new javax.swing.JTextArea();
        txtaInstrucciones.addFocusListener(this);
        txtaInstrucciones.setName("lblEstadoInstrucciones");
        pnlPermiso = new javax.swing.JPanel();
        rbtnPrivado = new javax.swing.JRadioButton();
        rbtnPublico = new javax.swing.JRadioButton();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        btnRemoverClave = new javax.swing.JButton();
        lblClaves = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnVer = new javax.swing.JButton();
        btnDesbloquear = new javax.swing.JButton();
        btnAgregarClave = new javax.swing.JButton();
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoInstrucciones = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(790, 579));

        jPanel4.setAutoscrolls(true);
        jPanel4.setPreferredSize(new java.awt.Dimension(790, 579));

        lblTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo1.setText("Registrar Examen");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTitulo.setText("Nombre del Examen:");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setToolTipText("");
        txtfNombre.setPreferredSize(new java.awt.Dimension(6, 30));

        lblInstrucciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInstrucciones.setText("Instrucciones del Examen:");

        txtaInstrucciones.setColumns(20);
        txtaInstrucciones.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaInstrucciones.setLineWrap(true);
        txtaInstrucciones.setRows(5);
        jScrollPane2.setViewportView(txtaInstrucciones);

        pnlPermiso.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Permiso:", 0, 0, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlPermiso.setToolTipText("");
        pnlPermiso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        rbtnPrivado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPrivado.setText("Privado");
        rbtnPrivado.setToolTipText("El examen solo puede ser utilizado por el autor y ningún maestro lo puede ver");

        rbtnPublico.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnPublico.setText("Público");
        rbtnPublico.setToolTipText("El examen puede ser visto y utilizado por todos los maestros");

        javax.swing.GroupLayout pnlPermisoLayout = new javax.swing.GroupLayout(pnlPermiso);
        pnlPermiso.setLayout(pnlPermisoLayout);
        pnlPermisoLayout.setHorizontalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPermisoLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(rbtnPublico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtnPrivado)
                .addGap(27, 27, 27))
        );
        pnlPermisoLayout.setVerticalGroup(
            pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPermisoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPermisoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnPublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnPrivado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("el curso al que pertence el examen");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        btnRemoverClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverClave.setText("Remover Clave");
        btnRemoverClave.setToolTipText("remueve la clave seleccionada");
        btnRemoverClave.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemoverClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerClave(evt);
            }
        });

        lblClaves.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblClaves.setText("Claves:");

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setToolTipText("");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarExamen(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnRemover.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/remover24.png"))); // NOI18N
        btnRemover.setToolTipText("remover reactivos");
        btnRemover.setPreferredSize(new java.awt.Dimension(77, 30));
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerReactivos(evt);
            }
        });

        btnAgregar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/agregar24.png"))); // NOI18N
        btnAgregar.setToolTipText("agregar reactivos");
        btnAgregar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarReactivos(evt);
            }
        });

        btnVer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ver24.png"))); // NOI18N
        btnVer.setToolTipText("ver reactivo");
        btnVer.setPreferredSize(new java.awt.Dimension(77, 30));
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verReactivo(evt);
            }
        });

        btnDesbloquear.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDesbloquear.setText("Desbloquear");
        btnDesbloquear.setToolTipText("habilita la selección de cursos. Esto también remueve todas las claves de examen");
        btnDesbloquear.setEnabled(false);
        btnDesbloquear.setPreferredSize(new java.awt.Dimension(77, 30));
        btnDesbloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desbloquearCurso(evt);
            }
        });

        btnAgregarClave.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregarClave.setText("Agregar Clave");
        btnAgregarClave.setToolTipText("agrega una nueva clave sin reactivos al examen");
        btnAgregarClave.setPreferredSize(new java.awt.Dimension(118, 30));
        btnAgregarClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarClave(evt);
            }
        });

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("No ingresar datos vacios");

        lblEstadoInstrucciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoInstrucciones.setToolTipText("No ingresar datos vacios");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(166, 166, 166)
                        .addComponent(lblEstadoNombre))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(lblEstadoInstrucciones)))
                .addGap(22, 22, 22))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblTitulo1, javax.swing.GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblCurso)
                                        .addGroup(jPanel4Layout.createSequentialGroup()
                                            .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addComponent(lblTitulo)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(57, 57, 57)
                                    .addComponent(lblInstrucciones))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(49, 49, 49)
                                    .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                                    .addComponent(txtfNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)))
                            .addGap(25, 25, 25)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(lblClaves)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(84, 84, 84)
                                    .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(31, 31, 31)
                                    .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(lblEstadoNombre))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(lblEstadoInstrucciones)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 345, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(13, 13, 13)
                    .addComponent(lblTitulo1)
                    .addGap(42, 42, 42)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(lblClaves)
                                    .addGap(38, 38, 38)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addComponent(tbpClaves, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRemoverClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAgregarClave, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(lblTitulo)
                            .addGap(13, 13, 13)
                            .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblInstrucciones)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(pnlPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(lblCurso)
                            .addGap(18, 18, 18)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDesbloquear, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(14, 14, 14)))
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void removerClave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerClave
        // TODO add your handling code here:
        if (tbpClaves.getSelectedIndex() != -1) {
            controlVista.removerClave(tbpClaves.getSelectedIndex());
            tbpClaves.remove(tbpClaves.getSelectedIndex());
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una clave");
        }
    }//GEN-LAST:event_removerClave

    private void guardarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarExamen

        ExamenDTO examen = encapsularExamen();

        if (examen == null) {
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            Integer id = controlVista.guardarExamen(examen);

            if (id != null) {
                JOptionPane.showMessageDialog(this, "Registro Completo");
                //padre.mostrarVista(Vista.HOME);
                limpiar();
                //Realizar todo como si fuera un nuevo registro
                noSelect = true;
                tbpClaves.add("Clave 1", new PnlReactivosTab());
                controlVista.agregarClave(1);
                consultarCursos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar "
                        + "el examen", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_guardarExamen

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarReactivos);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void removerReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerReactivos

        if (tbpClaves.getSelectedIndex() != -1) {
            PnlReactivosTab tab = (PnlReactivosTab) tbpClaves.getComponentAt(
                    tbpClaves.getSelectedIndex());

            List<Integer> indexesReactivo = tab.getSelectedRows();

            if (indexesReactivo.size() > 0) {

                controlVista.removerReactivos(indexesReactivo, tbpClaves
                        .getSelectedIndex());
                removerReactivos(indexesReactivo, tab.getTabla());
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una clave");
        }

    }//GEN-LAST:event_removerReactivos

    private void agregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarReactivos

        if (cmbCurso.getSelectedIndex() != -1) {
            if (tbpClaves.getSelectedIndex() != -1) {
                frmAgregarReactivos.inicializar(cmbCurso.getSelectedIndex(),
                        tbpClaves.getSelectedIndex());
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una clave "
                        + "primero");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un curso primero");
        }
    }//GEN-LAST:event_agregarReactivos

    private void verReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verReactivo
        // TODO add your handling code here:
        if (tbpClaves.getSelectedIndex() != -1) {
            PnlReactivosTab tab = (PnlReactivosTab) tbpClaves.getComponentAt(
                    tbpClaves.getSelectedIndex());

            if (tab.getSelectedIndex() != -1) {
                ReactivoDTO reactivo = controlVista
                        .obtenerReactivo(tab.getSelectedIndex(),
                                tbpClaves.getSelectedIndex());

                mostrarReactivo(reactivo);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una clave");
        }
    }//GEN-LAST:event_verReactivo

    private void desbloquearCurso(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desbloquearCurso
        // TODO add your handling code here:

        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres desbloquear los cursos?\nTodas las claves del examen "
                + "se eliminarán", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (ok == 0) {
            noSelect = true;

            cmbCurso.setEnabled(true);
            btnDesbloquear.setEnabled(false);
            tbpClaves.removeAll();
            controlVista.removerTodasLasClaves();
            cmbCurso.setSelectedIndex(-1);

            noSelect = false;
        }
    }//GEN-LAST:event_desbloquearCurso

    private void agregarClave(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarClave
        // TODO add your handling code here:
        int nuevo = tbpClaves.getTabCount();

        //Obtiene el numero de la ultima tab
        int ultimaTab;
        if (nuevo > 0) {
            ultimaTab = Integer.parseInt(tbpClaves.getTitleAt(nuevo - 1)
                    .substring(6));
        } else {
            ultimaTab = 0;
        }
        tbpClaves.add("Clave " + (ultimaTab + 1), new PnlReactivosTab());
        controlVista.agregarClave(ultimaTab + 1);
    }//GEN-LAST:event_agregarClave

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarClave;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDesbloquear;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JButton btnRemoverClave;
    private javax.swing.JButton btnVer;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblClaves;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstadoInstrucciones;
    private javax.swing.JLabel lblEstadoNombre;
    private javax.swing.JLabel lblInstrucciones;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel pnlPermiso;
    private javax.swing.JRadioButton rbtnPrivado;
    private javax.swing.JRadioButton rbtnPublico;
    private javax.swing.JTabbedPane tbpClaves;
    private javax.swing.JTextArea txtaInstrucciones;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Confirmación", JOptionPane.YES_NO_OPTION);
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
    public void focusGained(FocusEvent e) {
        //
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextComponent ob = (JTextComponent) e.getSource();        

        mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()));

    }

    private void mostrarLabelEstado(Object o, boolean estado) {
        JTextComponent ob = (JTextComponent) o;

        try {
            Field field = getClass().getDeclaredField(ob.getName());
            JLabel label = (JLabel) field.get(this);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            noSelect = true;
            tbpClaves.add("Clave 1", new PnlReactivosTab());
            controlVista.agregarClave(1);
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

    @Override
    public void mostrarReactivos(List<ReactivoDTO> reactivos, int clave) {
        //Mostrar reactivos agregados
        JTable tabla = ((PnlReactivosTab) tbpClaves.getComponentAt(clave))
                .getTabla();

        mostrarReactivos(reactivos, tabla);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;
        
        if(campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_EXAMEN;
        }
        else if(campo.getName().contains("Instrucciones")) {
            longitud = Validador.LONGITUD_INSTRUCCIONES_EXAMEN;
        }
        
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";
            
            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }
            
            if(!Validador.validarLongitud(longitud, campo.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
        else if (!Validador.validarLongitud(longitud, campo.getText())) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;
        
        if(campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_EXAMEN;
        }
        else if(campo.getName().contains("Instrucciones")) {
            longitud = Validador.LONGITUD_INSTRUCCIONES_EXAMEN;
        }
        
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";
            
            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }
            
            if(!Validador.validarLongitud(longitud, campo.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
