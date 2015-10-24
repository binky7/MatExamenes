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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.dto.CursoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerCursos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaModificarCurso extends javax.swing.JPanel
        implements InterfaceVista, FocusListener, KeyListener {

    /**
     * Controlador de la vista del caso de uso mantener cursos, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVMantenerCursos controlVista;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar la modificación de curso.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para guardar las modificaciones del curso.
    */
    private javax.swing.JButton btnModificar;
    /**
    * Label de estado del campo de texto del nombre del curso.
    */
    private javax.swing.JLabel lblEstadoNombreCurso;
    /**
    * Label del nombre del curso.
    */
    private javax.swing.JLabel lblNombreCurso;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Campo de texto del nombre del curso.
    */
    private javax.swing.JTextField txtfNombreCurso;
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
            + "* Nombre del curso.";

    /**
     * Crea un objeto VistaModificarCurso e inicializa sus atributos.
     */
    public VistaModificarCurso() {
        initComponents();
        txtfNombreCurso.addFocusListener(this);
        txtfNombreCurso.addKeyListener(this);

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        lblEstadoNombreCurso.setVisible(false);
    }

    /**
     * Almacena el control de la vista.
     *
     * @param controlVista El objeto encargado de realizar las interacciones con
     * la base de datos.
     */
    public void setControlador(CVMantenerCursos controlVista) {
        this.controlVista = controlVista;
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
     * Muestra los datos del curso en la vista.
     *
     * @param curso El curso a mostrar en la vista.
     */
    private void mostrarDatos(CursoDTO curso) {
        txtfNombreCurso.setText(curso.getNombre());
    }

    /**
     * Crea un objeto tipo CursoDTO con el nombre del curso obtenido del campo
     * de texto. También se valida el dato.
     *
     * @return Un objeto de tipo CursoDTO si el campo de texto cumple con las
     * validaciones. Regresa null si el campo de texto no cumple con las
     * validaciones.
     *
     */
    private CursoDTO encapsularCurso() {
        CursoDTO curso = null;
        boolean ok = true;

        String nombreCurso = txtfNombreCurso.getText();
        if (Validador.esCurso(nombreCurso)) {
            curso = new CursoDTO();
            curso.setNombre(nombreCurso);
            
            //Se obtiene el nombre del curso seleccionado y se le quitan las
            //letras con acentos para poder comparar posteriormente si el
            //usuario escribió un nombre de curso diferente
            String cursoActual = controlVista.getCursoSeleccionado().getNombre();
            cursoActual = Validador.quitarAcentos(cursoActual);
            
            nombreCurso = Validador.quitarAcentos(nombreCurso);
            //Validar si escribió el mismo nombre de antes.
            if (nombreCurso.compareToIgnoreCase(cursoActual) != 0) {
                
                //Si escribió otro nombre verificar si ya existe.
                boolean existe = controlVista.verificarExistencia(nombreCurso);

                if (existe) {
                    ok = false;
                    mostrarLabelEstado(txtfNombreCurso, false,
                            "Ya existe un curso con ese nombre.");
                } else {
                    mostrarLabelEstado(txtfNombreCurso, true, "");
                }
            } else {
                mostrarLabelEstado(txtfNombreCurso, true, "");
            }
        } else {
            ok = false;
            curso = null;
            if (Validador.estaVacio(nombreCurso)) {
                mostrarLabelEstado(txtfNombreCurso, false,
                        "No ingrese datos vacíos.");
            } else {
                mostrarLabelEstado(txtfNombreCurso, false,
                        "Ingrese sólo letras y/o números.");
            }
        }

        if (!ok) {
            return null;
        }

        return curso;
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
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaModificarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreCurso = new javax.swing.JLabel();
        txtfNombreCurso = new javax.swing.JTextField();
        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lblEstadoNombreCurso = new javax.swing.JLabel();

        lblNombreCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreCurso.setText("Nombre del curso:");

        txtfNombreCurso.setName("lblEstadoNombreCurso");
        txtfNombreCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreCurso.setPreferredSize(new java.awt.Dimension(350, 30));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Curso");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaConsulta(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnModificar.setText("Guardar");
        btnModificar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarCurso(evt);
            }
        });

        lblEstadoNombreCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

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
                        .addGap(537, 537, 537)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(lblNombreCurso)
                        .addGap(18, 18, 18)
                        .addComponent(txtfNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstadoNombreCurso)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(lblTitulo)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreCurso))
                    .addComponent(lblEstadoNombreCurso))
                .addGap(280, 280, 280)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón de modificar.
     *
     * @param evt
     */
    private void modificarCurso(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarCurso
        // TODO add your handling code here:
        CursoDTO curso = encapsularCurso();

        if (curso == null) {
            JOptionPane.showMessageDialog(this, MENSAJE_DATOS_INCORRECTOS, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            boolean ok = controlVista.modificarCurso(curso);

            if (ok) {
                padre.mostrarVistaConEntidad(curso, Vista.ConsultarCursos);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el curso.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_modificarCurso

    /**
     * Cancela la modificación de curso y regresa a la vista de consultar
     * cursos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void pasarControlVistaConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaConsulta
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.ConsultarCursos);
            limpiar();
        }
    }//GEN-LAST:event_pasarControlVistaConsulta


    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
    }

    /**
     * Muestra la información de la entidad obtenida en todos los campos de la
     * vista, este objeto es enviado desde la Vista Consultar del caso de uso
     * correspondiente a esta vista.
     *
     * @param entidad El objeto entidad que contiene la información a mostrar en
     * la vista para ser modificada.
     */
    @Override
    public void mostrarEntidad(Object entidad) {
        mostrarDatos((CursoDTO) entidad);
    }

    @Override
    public boolean confirmarCambio() {
        /*
        Esta parte del código se eliminó el 19 de octubre del 2015 debido
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
        txtfNombreCurso.setText("");
        lblEstadoNombreCurso.setVisible(false);
        controlVista.liberarMemoriaRegistrarModificar();
    }

    /**
     * Esconde la etiqueta de estado del campo de texto que obtiene el foco.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusGained(FocusEvent e) {
        lblEstadoNombreCurso.setVisible(false);
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

        String nombreCurso = src.getText();
        if (!Validador.esCurso(nombreCurso)) {
            if (Validador.estaVacio(nombreCurso)) {
                mostrarLabelEstado(txtfNombreCurso, false,
                        "No ingrese datos vacíos.");
            } else {
                mostrarLabelEstado(txtfNombreCurso, false,
                        "Ingrese sólo letras y/o números.");
            }
        } else {
            //El nombre del curso ingresado cumple con las validaciones.
            
            String cursoActual = controlVista.getCursoSeleccionado().getNombre();
            cursoActual = Validador.quitarAcentos(cursoActual);
            
            //Validar si escribió el mismo nombre de antes.
            nombreCurso = Validador.quitarAcentos(nombreCurso);
            if (nombreCurso.compareToIgnoreCase(cursoActual) == 0) {
                mostrarLabelEstado(txtfNombreCurso, true, "");
            } else {
                //Si escribió otro nombre verificar si ya existe.
                boolean ok = controlVista.verificarExistencia(nombreCurso);

                if (ok) {
                    mostrarLabelEstado(txtfNombreCurso, false,
                            "Ya existe un curso con ese nombre.");
                } else {
                    mostrarLabelEstado(txtfNombreCurso, true, "");
                }
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

            if (!Validador.validarLongitud(Validador.LONGITUD_CURSO, txtfNombreCurso.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } else if (!Validador.validarLongitud(Validador.LONGITUD_CURSO, txtfNombreCurso.getText())) {
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

            if (!Validador.validarLongitud(Validador.LONGITUD_CURSO, txtfNombreCurso.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
