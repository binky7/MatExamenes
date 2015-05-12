/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import modelo.dto.CursoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerReactivos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author Jesus Donaldo
 */
public class VistaRegistrarReactivo extends javax.swing.JPanel
implements InterfaceVista, FocusListener, AncestorListener {

    private InterfaceVista padre;
    private CVMantenerReactivos controlVista;
    
    private ButtonGroup opciones;
    
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;
    
    private boolean noSelect;
    private String mensajeDatosIncorrectos;
    
    private final String DATOS_VACIOS = "No ingresar datos vacios";
    private final String MAXIMO_CARACTERES = "El máximo de caracteres es: ";
    
    /**
     * Creates new form RegistrarReactivo
     */
    public VistaRegistrarReactivo() {
        initComponents();
        
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        
        lblEstadoNombre.setVisible(false);
        lblEstadoRedaccion.setVisible(false);
        lblEstadoOpt1.setVisible(false);
        lblEstadoOpt2.setVisible(false);
        lblEstadoOpt3.setVisible(false);
        lblEstadoOpt4.setVisible(false);
        
        init();
        initContadoresTexto();
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }
    
    public void setControlador(CVMantenerReactivos controlVista) {
        this.controlVista = controlVista;
    }

    private void init() {
        //Para igualar los radios con los textos
        rbtnOpt1.setActionCommand("Opt1");
        rbtnOpt2.setActionCommand("Opt2");
        rbtnOpt3.setActionCommand("Opt3");
        rbtnOpt4.setActionCommand("Opt4");
        
        txtfOpt1.setName("Opt1");
        txtfOpt2.setName("Opt2");
        txtfOpt3.setName("Opt3");
        txtfOpt4.setName("Opt4");
        
        opciones = new ButtonGroup();
        opciones.add(rbtnOpt1);
        opciones.add(rbtnOpt2);
        opciones.add(rbtnOpt4);
        opciones.add(rbtnOpt3);
        
        //Agregar los listeners de cambio de foco
        txtfOpt1.addFocusListener(this);
        txtfOpt2.addFocusListener(this);
        txtfOpt3.addFocusListener(this);
        txtfOpt4.addFocusListener(this);
        
        addAncestorListener(this);
        
        //Listener para el cmbCurso
        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!noSelect) {
                    consultarTemasDeCurso();
                }
            }
            
        });
    }
    
    private void initContadoresTexto() {
        
        txtfNombre.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtfNombre.getText().length();
                
                lblNombreCounter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtfNombre.getText().length();
                
                lblNombreCounter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        txtaRedaccion.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtaRedaccion.getText().length();
                
                lblRedaccionCounter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtaRedaccion.getText().length();
                
                lblRedaccionCounter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        txtfOpt1.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtfOpt1.getText().length();
                
                lblOpt1Counter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtfOpt1.getText().length();
                
                lblOpt1Counter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        txtfOpt2.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtfOpt2.getText().length();
                
                lblOpt2Counter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtfOpt2.getText().length();
                
                lblOpt2Counter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        txtfOpt3.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtfOpt3.getText().length();
                
                lblOpt3Counter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtfOpt3.getText().length();
                
                lblOpt3Counter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
        
        txtfOpt4.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
        
                int length = txtfOpt4.getText().length();
                
                lblOpt4Counter.setText(String.valueOf(length));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                int length = txtfOpt4.getText().length();
                
                lblOpt4Counter.setText(String.valueOf(length));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
        });
    }
    
    private void consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        
        if(cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }
    
    private void mostrarCursos(List<CursoDTO> cursos) {
        
        cmbCurso.removeAllItems();
        
        for(CursoDTO curso : cursos) {
            //System.out.println(cmbCurso.getSelectedIndex());
            cmbCurso.addItem(curso.getNombre());
        }
        
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }
    
    private void consultarTemasDeCurso() {
        
        if (cmbCurso.getSelectedIndex() != -1) {
            List<TemaDTO> temas = controlVista.obtenerTemasDeCurso(cmbCurso
                    .getSelectedIndex());

            if (temas != null && !temas.isEmpty()) {
                mostrarTemas(temas);
            } else {
                JOptionPane.showMessageDialog(this, "No hay temas");
                cmbTema.removeAllItems();
            }
        }
        else {
            cmbTema.removeAllItems();
        }
    }
    
    private void mostrarTemas(List<TemaDTO> temas) {
        
        cmbTema.removeAllItems();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for(TemaDTO tema : temas) {
            cmbTema.addItem(tema.getNombre());
        }
        
        cmbTema.setSelectedIndex(-1);
    }
    
    private ReactivoDTO encapsularReactivo() {
        
        ReactivoDTO reactivo = new ReactivoDTO();
        mensajeDatosIncorrectos = "";
        String mensajeMaxCaracteres = "";
        
        String nombre = txtfNombre.getText();
        String redaccion = txtaRedaccion.getText();
        String respuesta = "";
        List<String> opcionesReactivo = new ArrayList<>();
        boolean ok = true;

        if (Validador.estaVacio(nombre)) {
            ok = false;
            mensajeDatosIncorrectos = "* Nombre del Reactivo\n";
            mostrarLabelEstado(txtfNombre, false, "", DATOS_VACIOS);
        } else {
            //Validar el maximo de caracteres
            boolean max = !(nombre.length() > 50);
            mostrarLabelEstado(txtfNombre, max, "", MAXIMO_CARACTERES + "50");
            
            if(max) {
                mostrarLabelEstado(txtfNombre, true, "", DATOS_VACIOS);
            }
            else {
                ok = false;
                mensajeMaxCaracteres += "Máximo de caracteres para Nombre del "
                        + "Reactivo: 50\n";
            }
        }
        
        if (Validador.estaVacio(redaccion)) {
            ok = false;
            mensajeDatosIncorrectos += "* Redacción del Reactivo\n";
            mostrarLabelEstado(txtaRedaccion, false, "", DATOS_VACIOS);
        } else {
            //Validar el maximo de caracteres
            boolean max = !(redaccion.length() > 1000);
            mostrarLabelEstado(txtaRedaccion, max, "", MAXIMO_CARACTERES + "1000");
            
            if(max) {
                mostrarLabelEstado(txtaRedaccion, true, "", DATOS_VACIOS);
            }
            else {
                ok = false;
                mensajeMaxCaracteres += "Máximo de caracteres para Redaccion del "
                        + "Reactivo: 1000\n";
            }
        } 
        
        if(!ok) {
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                + "siguientes campos necesitan ser corregidos:\n" +
                    mensajeDatosIncorrectos;
        }
        
        if(cmbTema.getSelectedIndex() == -1) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un tema para el "
                    + "reactivo\n";
        }
        
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;

                if (Validador.estaVacio(field.getText())) {
                    mostrarLabelEstado(field, false, "lblEstado", DATOS_VACIOS);
                    ok = false;
                } else {
                    mostrarLabelEstado(field, true, "lblEstado", DATOS_VACIOS);
                }

                if (opciones.getSelection() != null) {
                    if (field.getName().equals(opciones.getSelection().getActionCommand())) {
                        respuesta = field.getText();
                    } else {
                        opcionesReactivo.add(field.getText());
                    }
                }
            }
        }
        
        if (respuesta.isEmpty()) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar una opción como "
                    + "respuesta\n";
        }
        
        reactivo.setNombre(nombre);
        reactivo.setRedaccion(redaccion);
        reactivo.setRespuesta(respuesta);
        reactivo.setOpciones(opcionesReactivo);
        reactivo.setAutor(padre.obtenerUsuarioActual());
        reactivo.setFechaCreacion(new Date());
        reactivo.setFechaModificacion(new Date());
        
        if(!ok){
            reactivo = null;
            mensajeDatosIncorrectos += mensajeMaxCaracteres;
        }
        else {
            controlVista.setTema(cmbTema.getSelectedIndex());
        }

        return reactivo;
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
        lblNombre = new javax.swing.JLabel();
        lblRedaccion = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        txtfNombre.addFocusListener(this);
        txtfNombre.setName("lblEstadoNombre");
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaRedaccion = new javax.swing.JTextArea();
        txtaRedaccion.addFocusListener(this);
        txtaRedaccion.setName("lblEstadoRedaccion");
        cmbCurso = new javax.swing.JComboBox();
        lblCurso = new javax.swing.JLabel();
        lblTema = new javax.swing.JLabel();
        cmbTema = new javax.swing.JComboBox();
        pnlOpciones = new javax.swing.JPanel();
        txtfOpt1 = new javax.swing.JTextField();
        txtfOpt1.addFocusListener(this);
        txtfOpt2 = new javax.swing.JTextField();
        txtfOpt2.addFocusListener(this);
        txtfOpt3 = new javax.swing.JTextField();
        txtfOpt3.addFocusListener(this);
        lblOpciones1 = new javax.swing.JLabel();
        lblOpciones2 = new javax.swing.JLabel();
        lblOpciones3 = new javax.swing.JLabel();
        txtfOpt4 = new javax.swing.JTextField();
        txtfOpt4.addFocusListener(this);
        lblOpciones4 = new javax.swing.JLabel();
        rbtnOpt1 = new javax.swing.JRadioButton();
        rbtnOpt2 = new javax.swing.JRadioButton();
        rbtnOpt4 = new javax.swing.JRadioButton();
        rbtnOpt3 = new javax.swing.JRadioButton();
        lblRespuesta = new javax.swing.JLabel();
        lblEstadoOpt1 = new javax.swing.JLabel();
        lblEstadoOpt2 = new javax.swing.JLabel();
        lblEstadoOpt3 = new javax.swing.JLabel();
        lblEstadoOpt4 = new javax.swing.JLabel();
        lblOpt1Counter = new javax.swing.JLabel();
        lblOpt2Counter = new javax.swing.JLabel();
        lblOpt3Counter = new javax.swing.JLabel();
        lblOpt4Counter = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoRedaccion = new javax.swing.JLabel();
        lblNombreCounter = new javax.swing.JLabel();
        lblRedaccionCounter = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(790, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setText("Registrar Reactivo");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre del Reactivo:");

        lblRedaccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRedaccion.setText("Redacción del Reactivo:");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setPreferredSize(new java.awt.Dimension(6, 30));

        txtaRedaccion.setColumns(20);
        txtaRedaccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaRedaccion.setLineWrap(true);
        txtaRedaccion.setRows(5);
        jScrollPane1.setViewportView(txtaRedaccion);

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("Selección de curso existente");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        lblTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTema.setText("Tema:");

        cmbTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTema.setToolTipText("tema al que pertenece el reactivo");
        cmbTema.setPreferredSize(new java.awt.Dimension(78, 25));

        pnlOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlOpciones.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtfOpt1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt1.setPreferredSize(new java.awt.Dimension(6, 30));

        txtfOpt2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt2.setPreferredSize(new java.awt.Dimension(6, 30));

        txtfOpt3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt3.setPreferredSize(new java.awt.Dimension(6, 30));

        lblOpciones1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones1.setText("Opción 1:");

        lblOpciones2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones2.setText("Opción 2:");

        lblOpciones3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones3.setText("Opción 3:");

        txtfOpt4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt4.setPreferredSize(new java.awt.Dimension(6, 30));

        lblOpciones4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones4.setText("Opción 4:");

        rbtnOpt1.setToolTipText("indica cual opción es la respuesta correcta");

        rbtnOpt2.setToolTipText("indica cual opción es la respuesta correcta");

        rbtnOpt4.setToolTipText("indica cual opción es la respuesta correcta");

        rbtnOpt3.setToolTipText("indica cual opción es la respuesta correcta");

        lblRespuesta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblRespuesta.setText("Respuesta");

        lblEstadoOpt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt1.setToolTipText("No ingresar datos vacios");

        lblEstadoOpt2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt2.setToolTipText("No ingresar datos vacios");

        lblEstadoOpt3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt3.setToolTipText("No ingresar datos vacios");

        lblEstadoOpt4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt4.setToolTipText("No ingresar datos vacios");

        lblOpt1Counter.setText("0");

        lblOpt2Counter.setText("0");

        lblOpt3Counter.setText("0");

        lblOpt4Counter.setText("0");

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblOpt4Counter)
                    .addComponent(lblOpt3Counter)
                    .addComponent(lblOpt2Counter)
                    .addComponent(lblOpt1Counter)
                    .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlOpcionesLayout.createSequentialGroup()
                            .addComponent(lblRespuesta)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                    .addComponent(rbtnOpt4)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblOpciones4)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                    .addComponent(rbtnOpt3)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblOpciones3)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                    .addComponent(rbtnOpt2)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblOpciones2)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                    .addComponent(rbtnOpt1)
                                    .addGap(18, 18, 18)
                                    .addComponent(lblOpciones1)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtfOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoOpt1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstadoOpt2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstadoOpt3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblEstadoOpt4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRespuesta)
                .addGap(7, 7, 7)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones1))
                            .addComponent(lblEstadoOpt1))
                        .addGap(1, 1, 1)
                        .addComponent(lblOpt1Counter)
                        .addGap(3, 3, 3)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstadoOpt2)
                            .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones2))))
                    .addComponent(rbtnOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(lblOpt2Counter)
                .addGap(3, 3, 3)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rbtnOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOpciones3)))
                    .addComponent(lblEstadoOpt3))
                .addGap(1, 1, 1)
                .addComponent(lblOpt3Counter)
                .addGap(3, 3, 3)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addComponent(lblEstadoOpt4)
                        .addContainerGap())
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones4))
                            .addComponent(rbtnOpt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblOpt4Counter))))
        );

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarReactivo(evt);
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

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("No ingresar datos vacios");

        lblEstadoRedaccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoRedaccion.setToolTipText("No ingresar datos vacios");

        lblNombreCounter.setText("0");

        lblRedaccionCounter.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblNombre)
                            .addGap(18, 18, 18)
                            .addComponent(lblEstadoNombre))
                        .addComponent(txtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addComponent(lblCurso)
                        .addComponent(lblTema)
                        .addComponent(cmbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbTema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblNombreCounter))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTitulo)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(163, 163, 163)
                                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblRedaccionCounter)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRedaccion)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstadoRedaccion)
                        .addGap(145, 145, 145))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre)
                        .addComponent(lblRedaccion))
                    .addComponent(lblEstadoNombre)
                    .addComponent(lblEstadoRedaccion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblNombreCounter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCurso)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblRedaccionCounter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(lblTema)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarReactivo
        
        ReactivoDTO reactivo = encapsularReactivo();
        
        if(reactivo == null) {
            if(mensajeDatosIncorrectos.isEmpty()) {
                mensajeDatosIncorrectos = "Falta ingresar opciones";
            }
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
        else {
            Integer id = controlVista.guardarReactivo(reactivo);
            
            if(id != null) {
                JOptionPane.showMessageDialog(this, "Registro Completo");
                //padre.mostrarVista(Vista.HOME);
                limpiar();
                //Mostrar de nuevo los cursos
                noSelect = true;
                consultarCursos();
            }
            else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar "
                        + "el reactivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_guardarReactivo

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstadoNombre;
    private javax.swing.JLabel lblEstadoOpt1;
    private javax.swing.JLabel lblEstadoOpt2;
    private javax.swing.JLabel lblEstadoOpt3;
    private javax.swing.JLabel lblEstadoOpt4;
    private javax.swing.JLabel lblEstadoRedaccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreCounter;
    private javax.swing.JLabel lblOpciones1;
    private javax.swing.JLabel lblOpciones2;
    private javax.swing.JLabel lblOpciones3;
    private javax.swing.JLabel lblOpciones4;
    private javax.swing.JLabel lblOpt1Counter;
    private javax.swing.JLabel lblOpt2Counter;
    private javax.swing.JLabel lblOpt3Counter;
    private javax.swing.JLabel lblOpt4Counter;
    private javax.swing.JLabel lblRedaccion;
    private javax.swing.JLabel lblRedaccionCounter;
    private javax.swing.JLabel lblRespuesta;
    private javax.swing.JLabel lblTema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlOpciones;
    private javax.swing.JRadioButton rbtnOpt1;
    private javax.swing.JRadioButton rbtnOpt2;
    private javax.swing.JRadioButton rbtnOpt3;
    private javax.swing.JRadioButton rbtnOpt4;
    private javax.swing.JTextArea txtaRedaccion;
    private javax.swing.JTextField txtfNombre;
    private javax.swing.JTextField txtfOpt1;
    private javax.swing.JTextField txtfOpt2;
    private javax.swing.JTextField txtfOpt3;
    private javax.swing.JTextField txtfOpt4;
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
    public void limpiar() {
        //Limpiar
        txtfNombre.setText("");
        cmbCurso.removeAllItems();
        cmbTema.removeAllItems();
        txtaRedaccion.setText("");
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;
                
                field.setText("");
            }
        }
        opciones.clearSelection();
        
        lblEstadoNombre.setVisible(false);
        lblEstadoRedaccion.setVisible(false);
        lblEstadoOpt1.setVisible(false);
        lblEstadoOpt2.setVisible(false);
        lblEstadoOpt3.setVisible(false);
        lblEstadoOpt4.setVisible(false);
        
        controlVista.liberarMemoriaRegistrar();
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        int w = getWidth();
//        int h = getHeight();
//        Color color1 = new Color(0x66CCFF);
//        Color color2 = Color.WHITE;
//        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
//        g2d.setPaint(gp);
//        g2d.fillRect(0, 0, w, h);
//    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        String maximoCaracteres = MAXIMO_CARACTERES;
        
        //Cuando cambia de componente
        JTextComponent ob = (JTextComponent) e.getComponent();
        
        if(ob.getName().startsWith("Opt")) {
            //Para validar el maximo de caracteres...
            boolean ok = !(ob.getText().length() > 250);
            maximoCaracteres += "250";
            mostrarLabelEstado(ob, ok, "lblEstado", maximoCaracteres);
            
            if(ok) {
                mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()),
                        "lblEstado", DATOS_VACIOS);
            }
        }
        else {
            boolean ok = true;
            
            //Validar el maximo de caracteres....
            if(ob == txtfNombre) {
                ok = !(ob.getText().length() > 50);
                maximoCaracteres += "50";
                mostrarLabelEstado(ob, ok, "", maximoCaracteres);
            }
            else if(ob == txtaRedaccion) {
                //Validar si es igual a la redaccion
                ok = !(ob.getText().length() > 1000);
                maximoCaracteres += "1000";
                mostrarLabelEstado(ob, ok, "", maximoCaracteres);
            }
            
            //Para validar si no hubo problemas al validar el maximo de caracteres
            if(ok) {
                mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()), "",
                    DATOS_VACIOS);
            }
        }
    }

    private void mostrarLabelEstado(Object o, boolean estado, String prefix, 
            String tooltip) {
        JTextComponent ob = (JTextComponent) o;
        
        try {
            Field field = getClass().getDeclaredField(prefix + ob.getName());
            JLabel label = (JLabel) field.get(this);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
            label.setToolTipText(tooltip);
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if(isShowing()) {
            noSelect = true;
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