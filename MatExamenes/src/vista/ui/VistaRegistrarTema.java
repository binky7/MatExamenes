/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaRegistrarTema extends javax.swing.JPanel implements
        AncestorListener, FocusListener, KeyListener, InterfaceVista {

    /**
     * Controlador de la vista del caso de uso mantener temas, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVMantenerTemas controlVista;

    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar el registro de temas.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para guardar el tema.
    */
    private javax.swing.JButton btnGuardar;
    /**
    * ComboBox para los bloques (del 1 al 5).
    */
    private javax.swing.JComboBox cbBloques;
    /**
    * ComboBox para los cursos.
    */
    private javax.swing.JComboBox cbCursos;
    /**
    * Label para los bloques.
    */
    private javax.swing.JLabel lblBloque;
    /**
    * Label para los cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label de estado para el campo de texto del nombre del tema.
    */
    private javax.swing.JLabel lblEstadoNombreTema;
    /**
    * Label para el nombre del tema.
    */
    private javax.swing.JLabel lblNombreTema;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Campo de texto para el nombre del tema.
    */
    private javax.swing.JTextField txtfNombreTema;
    // End of variables declaration//GEN-END:variables

    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;

    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;

    /**
     * Almacena el mensaje de datos incorrectos.
     */
    private final String MENSAJE_DATOS_INCORRECTOS = "No se puede completar la "
            + "operación, los siguientes campos necesitan ser corregidos:\n"
            + "* Nombre del tema.";

    /**
     * Crea un objeto VistaRegistrarTema e inicializa sus atributos.
     */
    public VistaRegistrarTema() {
        initComponents();
        this.addAncestorListener(this);
        txtfNombreTema.addFocusListener(this);
        txtfNombreTema.addKeyListener(this);

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));

        lblEstadoNombreTema.setVisible(false);
    }

    /**
     * Almacena la interface del JFrame principal.
     *
     * @param padre Interface para interactuar con el JFrame principal.
     */
    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    /**
     * Almacena el control de la vista.
     *
     * @param controlVista El objeto encargado de realizar las interacciones con
     * la base de datos.
     */
    public void setControlador(CVMantenerTemas controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Crea un objeto tipo TemaDTO con sus atributos obtenidos de la vista.
     * También se validan los datos.
     *
     * @return Un objeto de tipo TemaDTO si los datos cumplen con las
     * validaciones. Regresa null si los datos no cumplen con las validaciones.
     *
     */
    private TemaDTO encapsularTema() {
        TemaDTO tema = null;

        //Se valida que el campo del nombre del tema no esté vacío.
        String txtNombre = txtfNombreTema.getText();
        if (!Validador.estaVacio(txtNombre)) {
            //Si el campo del nombre del tema no está vacío se crea
            //el nuevo objeto TemaDTO.
            tema = new TemaDTO();
            tema.setNombre(txtNombre);
            mostrarLabelEstado(txtfNombreTema, true, "");
        } else {
            mostrarLabelEstado(txtfNombreTema, false,
                    "No ingrese datos vacíos.");
        }

        return tema;
    }

    /**
     * Obtiene los cursos almacenados en la base de datos.
     *
     * @return Regresa verdadero si la consulta de cursos se realiza
     * exitósamente. Regresa falso si no se encuentran cursos en la base de
     * datos.
     */
    private boolean consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Muestra los cursos en la vista.
     *
     * @param cursos Lista de cursos para mostrar en la vista.
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        cbCursos.removeAllItems();
        for (CursoDTO curso : cursos) {
            cbCursos.addItem(curso.getNombre());
        }
    }

    /**
     * Muestra el estado del campo de texto dependiendo si la validacion fue
     * verdadera o falsa.
     *
     * @param o El objeto campo de texto al cual se quiere cambiar el estado.
     * @param estado Si es verdadero el estado será correcto, si es falso el
     * estado será incorrecto.
     */
    private void mostrarLabelEstado(Object o, boolean estado, String causa) {
        JTextField ob = (JTextField) o;
        try {
            Field field = getClass().getDeclaredField(ob.getName());
            JLabel label = (JLabel) field.get(this);
            label.setToolTipText(causa);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
        } catch (NoSuchFieldException | SecurityException | 
                IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaRegistrarTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        txtfNombreTema = new javax.swing.JTextField();
        lblNombreTema = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        lblEstadoNombreTema = new javax.swing.JLabel();
        lblBloque = new javax.swing.JLabel();
        cbBloques = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(800, 600));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(115, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarTema(evt);
            }
        });

        txtfNombreTema.setName("lblEstadoNombreTema");
        txtfNombreTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreTema.setPreferredSize(new java.awt.Dimension(350, 30));

        lblNombreTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreTema.setText("Nombre del tema:");

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Tema");

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Seleccione un curso:");

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setPreferredSize(new java.awt.Dimension(200, 30));

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(115, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblEstadoNombreTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblBloque.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBloque.setText("Seleccione un bloque:");

        cbBloques.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBloques.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cbBloques.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(498, 498, 498)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(lblBloque)
                        .addGap(18, 18, 18)
                        .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombreTema)
                            .addComponent(lblCursos))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtfNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblEstadoNombreTema)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(lblTitulo)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreTema))
                    .addComponent(lblEstadoNombreTema))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCursos))
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBloque)
                    .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(113, 113, 113)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Guarda el tema en la base de datos, después de que los datos sean
     * validados.
     *
     * @param evt Objeto que contiene informacion del evento.
     */
    private void guardarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarTema
        //Guardar Tema
        //Encapsular objeto con los datos obtenidos de la interfaz.
        TemaDTO tema = encapsularTema();
        if (tema == null) {
            JOptionPane.showMessageDialog(this, MENSAJE_DATOS_INCORRECTOS, 
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        } else {
            //Verifica si el nombre de tema ingresado ya existe.
            if (controlVista.verificarExistencia(tema.getNombre())) {
                mostrarLabelEstado(txtfNombreTema, false,
                        "Ya existe un tema con ese nombre.");

                JOptionPane.showMessageDialog(this, MENSAJE_DATOS_INCORRECTOS, 
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                int indexCurso = cbCursos.getSelectedIndex();
                int bloque = Integer.parseInt(cbBloques.getSelectedItem().toString());
                Integer id = controlVista.guardarTema(tema, indexCurso, bloque);

                if (id == null) {
                    JOptionPane.showMessageDialog(this, 
                            "No se pudo guardar el curso.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    limpiar();
                }
            }
        }


    }//GEN-LAST:event_guardarTema

    /**
     * Cancela el registro de temas y regresa a la vista principal.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
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

        /*
        Esta parte del código se eliminó el 24 de octubre del 2015 debido
        a que ya no se utilizará confirmación de cambio de vistas. El método
        'confirmarCambio' posiblemente sea eliminado de la 'InterfaceVista'
        
        boolean cambiar = false;

        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "quiere cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
        */
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        txtfNombreTema.setText("");
        cbBloques.setSelectedIndex(0);
        lblEstadoNombreTema.setVisible(false);
    }

    /**
     * Este método es llamado cada vez que se muestra esta vista en el frame
     * principal, sirve para realizar el método inicial al mostrar una vista,
     * como una consulta
     *
     * @param event el objeto AncestorEvent que se obtiene del evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            if (!consultarCursos()) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados.", "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                padre.mostrarVista(Vista.HOME);
            }
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
     * Esconde la etiqueta de estado del campo de texto que obtiene el foco.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusGained(FocusEvent e) {
        lblEstadoNombreTema.setVisible(false);
    }

    /**
     * Valida el estado de los campos de texto cuando se pierde el foco en los
     * campos de texto.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();

        String nombreTema = src.getText();
        if (Validador.estaVacio(nombreTema)) {
            mostrarLabelEstado(txtfNombreTema, false,
                    "No ingrese datos vacíos.");
        } else {
            boolean ok = controlVista.verificarExistencia(nombreTema);

            if (ok) {
                mostrarLabelEstado(txtfNombreTema, false, 
                        "Ya existe un tema con ese nombre.");
            } else {
                mostrarLabelEstado(txtfNombreTema, true, "");
            }
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
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, 
                    txtfNombreTema.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } else if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, 
                txtfNombreTema.getText())) {
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
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, 
                    txtfNombreTema.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
