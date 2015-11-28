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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.text.JTextComponent;
import modelo.dto.CursoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerReactivos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Registrar Reactivo
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaRegistrarReactivo extends javax.swing.JPanel
implements InterfaceVista, FocusListener, AncestorListener, KeyListener {

    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas.
     * En ese caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;
    /**
     * Controlador de la vista del caso de uso mantener reactivos, funciona para
     * manejar la información obtenida en la vista para comunicarse con las capas
     * inferiores
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
     * Bandera que sirve para que el evento de cambio no se dispare en los
     * combobox al ingresar nuevos datos, para evitar que el sistema tenga
     * resultados innesperados
     */
    private boolean noSelect;
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
     * Crea un objeto VistaRegistrarReactivo e inicializa sus atributos,
     * oculta las etiquetas para mostrar el estado de los campos de la vista
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
        
        //Agregar los listeners de cambio de foco
        txtfOpt1.addFocusListener(this);
        txtfOpt2.addFocusListener(this);
        txtfOpt3.addFocusListener(this);
        txtfOpt4.addFocusListener(this);
        
        txtfNombre.addKeyListener(this);
        txtaRedaccion.addKeyListener(this);
        txtfOpt1.addKeyListener(this);
        txtfOpt2.addKeyListener(this);
        txtfOpt3.addKeyListener(this);
        txtfOpt4.addKeyListener(this);
        
        addAncestorListener(this);
        
        //Listener para el cmbBloque
        cmbBloque.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!noSelect) {
                    //Una vez seleccionado un bloque del cmbBloque se consultan
                    //los temas del curso y bloque seleccionados
                    consultarTemasDeCurso();
                }
            }
            
        });
    }
    
    /**
     * Este método es utilizado para consultar y mostrar los cursos disponibles
     * en la base de datos, mediante la utilización del controlVista. En caso de
     * que no exista ningún curso se mostrará un mensaje y se regresará a la
     * vista principal.
     */
    private void consultarCursos() {
        //la lista de cursos obtenida desde la base de datos por el controlVista
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        
        //Si hay cursos...
        if(cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        }
        else {
            //Si no hay mostrar un mensaje, regresar a la vista principal y
            //limpiar la vista actual
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }
    
    /**
     * Este método es utilizado para mostrar una lista de cursos en el componente
     * comboBox de la vista para mostrar los cursos disponibles.
     * @param cursos una lista de cursos CursoDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        //Para limpiar el cmbCurso de información previa
        cmbCurso.removeAllItems();
        
        //Recorrer todos los elementos de la lista para mostrarlos en el comboBox
        for(CursoDTO curso : cursos) {
            cmbCurso.addItem(curso.getNombre());
        }
        
        //Deselecciona el comboBox y permite la funcionalidad correcta del
        //listener del cmbCurso al igualar la bandera a falso
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }
    
    /**
     * Este método sirve para consultar los temas pertenecientes al curso
     * seleccionado en el cmbCurso. Al haber obtenido todos los cursos de la
     * base de datos anteriormente pero no tener los temas inicializados (lazy),
     * se requiere hacer una segunda llamada a la base de datos para obtener
     * los temas, por medio del controlVista.
     * @see consultarCursos
     */
    private void consultarTemasDeCurso() {
        //Si el cmbCurso y el cmbBloque tienen un curso seleccionado
        if (cmbCurso.getSelectedIndex() != -1 && cmbBloque.getSelectedIndex() != -1) {
            //La lista de temas recibida del controlVista, la cual pertenece
            //al curso seleccionado en el cmbCurso y al bloque del cmbBloque
            List<TemaDTO> temas = controlVista.obtenerTemasDeCurso(cmbCurso
                    .getSelectedIndex(), cmbBloque.getSelectedIndex() + 1);

            //Si la lista de temas no está vacía...
            if (temas != null && !temas.isEmpty()) {
                mostrarTemas(temas);
            } else {
                //Si está vacía mostrar un mensaje y limpiar cmbTema
                JOptionPane.showMessageDialog(this, "No hay temas");
                cmbTema.removeAllItems();
            }
        }
        else {
            //Si no hay selección de curso o bloque limpiar cmbTema
            cmbTema.removeAllItems();
        }
    }
    
    /**
     * Este método es utilizado para mostrar una lista de temas en el componente
     * comboBox de la vista para mostrar los temas disponibles.
     * @param temas una lista de temas TemaDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarTemas(List<TemaDTO> temas) {
    
        //Limpia la información anterior
        cmbTema.removeAllItems();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for(TemaDTO tema : temas) {
            cmbTema.addItem(tema.getNombre());
        }
        
        cmbTema.setSelectedIndex(-1);
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
        
        //Si no se seleccionó un tema no se puede almacenar la información en
        //el reactivo
        if(cmbTema.getSelectedIndex() == -1) {
            ok = false;
            mensajeDatosIncorrectos += "Debes seleccionar un tema para el "
                    + "reactivo\n";
        }
        
        //Este ciclo recorre todos los componentes del pnlOpciones para validar
        //cada opción
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                //Si el componente es un JTextField...
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
        reactivo.setAutor(padre.obtenerUsuarioActual());
        reactivo.setFechaCreacion(new Date());
        reactivo.setFechaModificacion(new Date());
        
        //Si falló la validación regresar null
        if(!ok){
            reactivo = null;
        }
        else {
            //Si no almacenar el tema seleccionado en controlVista
            controlVista.setTema(cmbTema.getSelectedIndex());
        }

        return reactivo;
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
        pnlOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtfOpt1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt1.setPreferredSize(new java.awt.Dimension(6, 30));
        pnlOpciones.add(txtfOpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 430, -1));

        txtfOpt2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt2.setPreferredSize(new java.awt.Dimension(6, 30));
        pnlOpciones.add(txtfOpt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 430, -1));

        txtfOpt3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt3.setPreferredSize(new java.awt.Dimension(6, 30));
        pnlOpciones.add(txtfOpt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 430, -1));

        lblOpciones1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones1.setText("Opción 1:");
        pnlOpciones.add(lblOpciones1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        lblOpciones2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones2.setText("Opción 2:");
        pnlOpciones.add(lblOpciones2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        lblOpciones3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones3.setText("Opción 3:");
        pnlOpciones.add(lblOpciones3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        txtfOpt4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt4.setPreferredSize(new java.awt.Dimension(6, 30));
        pnlOpciones.add(txtfOpt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 430, -1));

        lblOpciones4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones4.setText("Opción 4:");
        pnlOpciones.add(lblOpciones4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 180, -1, -1));

        rbtnOpt1.setToolTipText("indica cual opción es la respuesta correcta");
        pnlOpciones.add(rbtnOpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, 30));

        rbtnOpt2.setToolTipText("indica cual opción es la respuesta correcta");
        pnlOpciones.add(rbtnOpt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 30));

        rbtnOpt4.setToolTipText("indica cual opción es la respuesta correcta");
        pnlOpciones.add(rbtnOpt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, 30));

        rbtnOpt3.setToolTipText("indica cual opción es la respuesta correcta");
        pnlOpciones.add(rbtnOpt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 30));

        lblRespuesta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblRespuesta.setText("Respuesta");
        pnlOpciones.add(lblRespuesta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblEstadoOpt1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt1.setToolTipText("No ingresar datos vacios");
        pnlOpciones.add(lblEstadoOpt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 40, -1, -1));

        lblEstadoOpt2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt2.setToolTipText("No ingresar datos vacios");
        pnlOpciones.add(lblEstadoOpt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, -1, -1));

        lblEstadoOpt3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt3.setToolTipText("No ingresar datos vacios");
        pnlOpciones.add(lblEstadoOpt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 130, -1, -1));

        lblEstadoOpt4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoOpt4.setToolTipText("No ingresar datos vacios");
        pnlOpciones.add(lblEstadoOpt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, -1, -1));

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

        lblBloque.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBloque.setText("Bloque:");

        cmbBloque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbBloque.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cmbBloque.setSelectedIndex(-1);
        cmbBloque.setToolTipText("Selección del bloque al que pertenecen los temas");
        cmbBloque.setPreferredSize(new java.awt.Dimension(78, 25));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(lblTitulo))
            .addGroup(layout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(lblCurso)
                .addGap(14, 14, 14)
                .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblBloque)
                .addGap(16, 16, 16)
                .addComponent(cmbBloque, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblTema)
                .addGap(9, 9, 9)
                .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(lblNombre)
                .addGap(9, 9, 9)
                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lblEstadoNombre))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblRedaccion)
                .addGap(20, 20, 20)
                .addComponent(lblEstadoRedaccion))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(470, 470, 470)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitulo)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCurso)
                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBloque)
                    .addComponent(cmbBloque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTema)
                    .addComponent(cmbTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblEstadoNombre))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRedaccion)
                    .addComponent(lblEstadoRedaccion))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método es llamado cuando el botón para guardar el reactivo es
     * seleccionado. Encapsula la información de la vista en un objeto reactivo
     * y lo almacena en la base de datos mediante el controlVista. En caso de
     * que la validación falle se muestra un mensaje al usuario. Si todo fue
     * correcto se limpian los campos para volver a ingresar otro registro
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado.
     */
    private void guardarReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarReactivo
        
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
            //Si la valdiación fue correcta se guarda el objeto mediante el
            //control vista
            Integer id = controlVista.guardarReactivo(reactivo);
            
            if(id != null) {
                //Si la operación se realiza con éxito se muestra un mensaje
                //de confirmación y se limpian los datos de la vista
                //JOptionPane.showMessageDialog(this, "Registro Completo");
                limpiar();
                //Mostrar de nuevo los cursos
                noSelect = true;
                consultarCursos();
            }
            else {
                //Si no se pudo guardar el reactivo mostrar un mensaje de error
                JOptionPane.showMessageDialog(this, "No se pudo guardar "
                        + "el reactivo, el nombre ya existe o alguna opción se "
                        + "repite",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }//GEN-LAST:event_guardarReactivo

    /**
     * Este método es llamado cuando se selecciona el botón de cancelar el registro,
     * lo que hace es pedir una confirmación de la operación mediante un mensaje,
     * en caso de que se acepte se vuelve a la vista principal.
     * @param evt el objeto ActionEvent generado por el evento, no es utilizado
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed


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
        cmbBloque.setSelectedIndex(-1);
        
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
     * Este método es llamado cada vez que se muestra esta vista en el frame
     * principal, sirve para realizar el método inicial al mostrar una vista,
     * como una consulta
     * @param event el objeto AncestorEvent que se obtiene del evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        //Si la vista se está mostrando consultar cursos
        if(isShowing()) {
            noSelect = true;
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
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