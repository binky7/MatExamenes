/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import modelo.dto.ReactivoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerReactivos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Modificar Reactivo
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaModificarReactivo extends javax.swing.JPanel
implements InterfaceVista, FocusListener, KeyListener {

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas. En ese
     * caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;
    /**
     * Controlador de la vista del caso de uso mantener reactivos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerReactivos controlVista;
    
    /**
     * Objeto que agrupa lógicamente los radio buttons para seleccionar la
     * respuesta correcta del reactivo de sus opciones
     */
    private ButtonGroup opciones;
    
    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;
    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;
    
    /**
     * Cadena para almacenar el mensaje que se cree en caso de que los campos
     * de la interfaz no cumplan con el formato esperado
     */
    private String mensajeDatosIncorrectos;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cmbBloque;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JComboBox cmbTema;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBloque;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstadoNombre;
    private javax.swing.JLabel lblEstadoOpt1;
    private javax.swing.JLabel lblEstadoOpt2;
    private javax.swing.JLabel lblEstadoOpt3;
    private javax.swing.JLabel lblEstadoOpt4;
    private javax.swing.JLabel lblEstadoRedaccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblOpciones1;
    private javax.swing.JLabel lblOpciones2;
    private javax.swing.JLabel lblOpciones3;
    private javax.swing.JLabel lblOpciones4;
    private javax.swing.JLabel lblRedaccion;
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
    
    /**
     * Crea un objeto VistaModificarReactivo e inicializa sus atributos,
     * oculta las etiquetas para mostrar el estado de los campos de la vista
     */
    public VistaModificarReactivo() {
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
        
        //Para hacer wrap de palabras en la redacción
        txtaRedaccion.setWrapStyleWord(true);
    }

    /**
     * Almacena la interface del JFrame principal.
     * @param padre Interface para interactuar con el JFrame principal.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }
    
    /**
     * Almacena el control de la vista
     * @param controlVista El objeto encargado de realizar comunicar la vista
     * con las capas inferiores para acceder a los datos
     */
    public void setControlador(CVMantenerReactivos controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Este método inicializa tantos los componentes de las opciones del
     * reactivo para relacionarlos entre si y se puedan validar los campos
     * con otro método genérico, como agrega también los listeners necesarios
     * a los campos de la vista. Este método debe ser llamado primero, desde el
     * constructor
     * @see mostrarLabelEstado
     */
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
        
        txtfNombre.addKeyListener(this);
        txtaRedaccion.addKeyListener(this);
        txtfOpt1.addKeyListener(this);
        txtfOpt2.addKeyListener(this);
        txtfOpt3.addKeyListener(this);
        txtfOpt4.addKeyListener(this);
        
        //Agregar los listeners de cambio de foco
        txtfOpt1.addFocusListener(this);
        txtfOpt2.addFocusListener(this);
        txtfOpt3.addFocusListener(this);
        txtfOpt4.addFocusListener(this);
    }
    
    /**
     * Crea un objeto de tipo ReactivoDTO obteniendo los datos de los campos de
     * texto de la vista, también valida si la información capturada es correcta,
     * en caso de que no el objeto no se construye y se agrega la información de
     * la falla en una cadena mensajeDatosIncorrectos, de igual forma se le
     * muestra al usuario los campos donde falló por medio de las etiquetas 
     * ICONO_BIEN, ICONO_MAL
     * 
     * @return Un objeto ReactivoDTO si los datos de los campos fueron validos.<br>
     * Retorna null de otra forma.
     */
    private ReactivoDTO encapsularReactivo() {
        //El objeto reactivo donde se encapsularán todos los datos
        ReactivoDTO reactivo = new ReactivoDTO();
        mensajeDatosIncorrectos = "";
        
        //Datos del reactivo
        String nombre = txtfNombre.getText();
        String redaccion = txtaRedaccion.getText();
        String respuesta = "";
        List<String> opcionesReactivo = new ArrayList<>();
        //bandera que indica que el encapsulado fue correcto
        boolean ok = true;

        //Si el campo está vacío agregar el mensaje correspondiente y mostrar el
        //label correspondiente a un lado del campo
        if (Validador.estaVacio(nombre)) {
            ok = false;
            mensajeDatosIncorrectos = "* Nombre del Reactivo\n";
            mostrarLabelEstado(txtfNombre, false, "");
        } else {
            mostrarLabelEstado(txtfNombre, true, "");
        }
        
        if (Validador.estaVacio(redaccion)) {
            ok = false;
            mensajeDatosIncorrectos += "* Redacción del Reactivo\n";
            mostrarLabelEstado(txtaRedaccion, false, "");
        } else {
            mostrarLabelEstado(txtaRedaccion, true, "");
        }
        
        //Validación para acomodar el orden de los mensajes
        if(!ok) {
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                + "siguientes campos necesitan ser corregidos:\n" +
                    mensajeDatosIncorrectos;
        }
        
        //Este ciclo recorre todos los componentes del pnlOpciones para validar
        //cada opción
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;

                //Validar el campo y mostrar el label correspondiente
                if (Validador.estaVacio(field.getText())) {
                    mostrarLabelEstado(field, false, "lblEstado");
                    ok = false;
                } else {
                    mostrarLabelEstado(field, true, "lblEstado");
                }

                //Si se seleccionó una opción como respuesta correcta almacenar
                //la selección en la respuesta del reactivo y las demás en las
                //opciones del reactivo
                if (opciones.getSelection() != null) {
                    if (field.getName().equals(opciones.getSelection().getActionCommand())) {
                        respuesta = field.getText();
                    } else {
                        opcionesReactivo.add(field.getText());
                    }
                }
            }
        }
        
        //Comprobar si se seleccionó una opción como respuesta correcta
        if (respuesta.isEmpty()) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar una opción como "
                    + "respuesta\n";
        }
        
        //Almacenar todos los datos obtenidos en el reactivo
        reactivo.setNombre(nombre);
        reactivo.setRedaccion(redaccion);
        reactivo.setRespuesta(respuesta);
        reactivo.setOpcionesIncorrectas(opcionesReactivo);
        reactivo.setFechaModificacion(new Date());
        
        //Si falló la validación regresar null
        if(!ok){
            reactivo = null;
        }

        return reactivo;
    }
    
    /**
     * Este método es utilizado para mostrar los datos del reactivo proporcionado
     * en los componentes de la vista, para así poder editar sus datos
     * 
     * @param reactivo el objeto ReactivoDTO que se desea mostrar en los
     * componentes de la vista.
     */
    private void mostrarDatos(ReactivoDTO reactivo) {
        //Mostrar los datos del reactivo en el nombre, tema y redacción
        txtfNombre.setText(reactivo.getNombre());
        cmbTema.addItem(reactivo.getTema().getNombre());
        txtaRedaccion.setText(reactivo.getRedaccion());
        
        int i = 0;
        int size = reactivo.getOpcionesIncorrectas().size();
        
        //Mostrar Opciones
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;

                if (i < size) {
                    //Mostrar todas las opciones del reactivo primero
                    field.setText(reactivo.getOpcionesIncorrectas().get(i));
                } else {
                    //Después mostrar la opción seleccionada como respuesta
                    field.setText(reactivo.getRespuesta());
                    try {
                        //Obtener el radio button asociado a ese campo y
                        //seleccionarlo para denotar que esa opción es la respuesta
                        Field fieldObj = getClass().getDeclaredField("rbtn" + field.getName());
                        JRadioButton button = (JRadioButton) fieldObj.get(this);
                        opciones.setSelected(button.getModel(), true);
                    } catch (NoSuchFieldException | IllegalAccessException ex) {
                        //System.out.println(ex);
                    }
                }
                i++;
            }
        }
    }
    
    /**
     * Muestra el estado del campo de texto dependiendo si la validacion fue
     * verdadera o falsa.
     *
     * @param o El objeto campo de texto al cual se quiere cambiar el estado.
     * @param estado Si es verdadero el estado sera correcto, si es falso el
     * estado sera incorrecto.
     * @param prefix El prefijo que se quiere agregar al nombre del objeto o al
     * momento de comparar los campos
     */
    private void mostrarLabelEstado(Object o, boolean estado, String prefix) {
        JTextComponent ob = (JTextComponent) o;
        
        try {
            //Se utiliza la reflexión para crear un método de validación
            //genérico
            Field field = getClass().getDeclaredField(prefix + ob.getName());
            JLabel label = (JLabel) field.get(this);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            //Si el estado es verdadero, mostrar ICONO_BIEN, si no mostrar ICONO_MAL
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaRegistrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
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
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoRedaccion = new javax.swing.JLabel();
        lblBloque = new javax.swing.JLabel();
        cmbBloque = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(790, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Reactivo");

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
        cmbCurso.setEnabled(false);
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        lblTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTema.setText("Tema:");

        cmbTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbTema.setToolTipText("tema al que pertenece el reactivo");
        cmbTema.setEnabled(false);
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

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
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
                                .addComponent(txtfOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
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
                        .addGap(18, 18, 18)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEstadoOpt2)
                            .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones2))))
                    .addComponent(rbtnOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(rbtnOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOpciones3)))
                    .addComponent(lblEstadoOpt3))
                .addGap(18, 18, 18)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addComponent(lblEstadoOpt4)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones4))
                            .addComponent(rbtnOpt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 20, Short.MAX_VALUE))))
        );

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarReactivo(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaConsulta(evt);
            }
        });

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("No ingresar datos vacios");

        lblEstadoRedaccion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoRedaccion.setToolTipText("No ingresar datos vacios");

        lblBloque.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBloque.setText("Bloque:");

        cmbBloque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbBloque.setEnabled(false);
        cmbBloque.setPreferredSize(new java.awt.Dimension(78, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstadoNombre))
                    .addComponent(txtfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                    .addComponent(lblCurso)
                    .addComponent(lblTema)
                    .addComponent(cmbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTema, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBloque)
                    .addComponent(cmbBloque, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(42, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblRedaccion)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstadoRedaccion)
                        .addGap(142, 142, 142))))
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblCurso)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(lblBloque)
                        .addGap(18, 18, 18)
                        .addComponent(cmbBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(lblTema)
                        .addGap(18, 18, 18)
                        .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método es llamado cuando el botón para guardar el reactivo es
     * seleccionado. Encapsula la información de la vista en un objeto reactivo
     * y lo actualiza en la base de datos mediante el controlVista. En caso de
     * que la validación falle se muestra un mensaje al usuario. Si todo fue
     * correcto se limpian los campos y se regresa el control a la vista consultar
     * reactivos.
     * 
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado.
     */
    private void modificarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarReactivo

        //El objeto ReactivoDTO obtenido al encapsular los datos de la vista
        ReactivoDTO reactivo = encapsularReactivo();

        //Si la validación falló se muestra un mensaje de advertencia
        if(reactivo == null) {
            if(mensajeDatosIncorrectos.isEmpty()) {
                mensajeDatosIncorrectos = "Falta ingresar opciones";
            }
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
        else {
            //Si la valdiación fue correcta se actualiza el objeto mediante el
            //control vista
            boolean ok = controlVista.modificarReactivo(reactivo);

            if(ok) {
                //Si la operación se realiza con éxito se muestra un mensaje
                //de confirmación y se regresa el control a la Vista Consultar
                JOptionPane.showMessageDialog(this, "Reactivo Modificado");
                padre.mostrarVistaConEntidad(reactivo, Vista.ConsultarReactivos);
                limpiar();
            }
            else {
                //Si no se pudo modificar el reactivo mostrar un mensaje de error
                JOptionPane.showMessageDialog(this, "No se pudo modificar "
                    + "el reactivo, el nombre ya existe o alguna opción se repite",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_modificarReactivo

    /**
     * Este método sirve para pasar el control a la Vista Consultar
     * correspondiente al mismo caso de uso al que pertence esta vista. Se manda
     * llamar al seleccionar la opción de cancelar en la vista.
     * @param evt un objeto de tipo ActionEvent generado al ocurrir el evento
     */
    private void pasarControlVistaConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaConsulta

        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
            + "desea cancelar la operación?\nTodos los cambios no "
            + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.ConsultarReactivos);
            limpiar();
        }
    }//GEN-LAST:event_pasarControlVistaConsulta


    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Muestra la información de la entidad obtenida en todos los campos de la
     * vista, este objeto es enviado desde la Vista Consultar del caso de uso
     * correspondiente a esta vista
     * 
     * @param entidad el objeto entidad que contiene la información a mostrar
     * en la vista para ser modificada
     */
    @Override
    public void mostrarEntidad(Object entidad) {
        //Mostrar Datos
        mostrarDatos((ReactivoDTO)entidad);
    }

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
        
        controlVista.liberarMemoriaModificar();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    /**
     * Este método es invocado cuando un campo pierde el foco, sirve para validar
     * los campos y para mostrar su label correspondiente en caso de que los
     * campos estén correctos o incorrectos
     * @param e el objeto FocusEvent que se obtiene del evento
     */
    @Override
    public void focusLost(FocusEvent e) {
        //Cuando cambia de componente
        JTextComponent ob = (JTextComponent) e.getComponent();
        
        //Si el nombre empieza con Opt significa que se trata de los campos de 
        //opciones del reactivo
        if(ob.getName().startsWith("Opt")) {
            mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()), "lblEstado");
        }
        else {
            mostrarLabelEstado(ob, !Validador.estaVacio(ob.getText()), "");
        }
    }

    /**
     * Valida que los campos no acepten mas de los caracteres estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;
       
        //Asignar la longitud máxima según el campo de la vista
        if(campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_REACTIVO;
        }
        else if(campo.getName().contains("Redaccion")) {
            longitud = Validador.LONGITUD_REDACCION_REACTIVO;
        }
        else if(campo.getName().contains("Opt")) {
            longitud = Validador.LONGITUD_OPCION_REACTIVO;
        }
        
        //Verificar si se está pegando contenido mediante Ctrl + V
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";
            
            try {
                //Obtener la cadena del portapapeles del sistema
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }
            
            //Si la longitud del campo de texto mas la cadena del portapapeles
            //Es mayor del máximo permitido evitar la acción...
            if(!Validador.validarLongitud(longitud, campo.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
        //Si no hay acción de pegado pero aún así se llegó al límite de caracteres
        //evitar la acción
        else if (!Validador.validarLongitud(longitud, campo.getText())) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Valida que los campos no acepten mas de los caracteres estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        JTextComponent campo = (JTextComponent) e.getSource();
        int longitud = 0;
        
        //Asignar la longitud máxima según el campo de la vista
        if(campo.getName().contains("Nombre")) {
            longitud = Validador.LONGITUD_NOMBRE_REACTIVO;
        }
        else if(campo.getName().contains("Redaccion")) {
            longitud = Validador.LONGITUD_REDACCION_REACTIVO;
        }
        else if(campo.getName().contains("Opt")) {
            longitud = Validador.LONGITUD_OPCION_REACTIVO;
        }
        
        //Verificar si se está pegando contenido mediante Ctrl + V
        if(e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";
            
            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }
            
            //Si la longitud del campo de texto mas la cadena del portapapeles
            //Es mayor del máximo permitido evitar la acción...
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
