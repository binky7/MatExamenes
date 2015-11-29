/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerUsuarios;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 * Interfaz gráfica para registrar usuarios.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class VistaRegistrarUsuario extends javax.swing.JPanel implements
        InterfaceVista, FocusListener, KeyListener {

    /**
     * Controlador de la vista del caso de uso Mantener Usuarios, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVMantenerUsuarios cvMantenerUsuarios;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;
    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;
    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;
    /**
     * Índice del nombre.
     */
    private final int NOMBRE = 0;
    /**
     * Índice del apellido paterno.
     */
    private final int APELLIDO_PATERNO = 1;
    /**
     * Índice del apellido materno.
     */
    private final int APELLIDO_MATERNO = 2;
    /**
     * Índice del usuario.
     */
    private final int USUARIO = 3;
    /**
     * Índice del password.
     */
    private final int PASSWORD = 4;
    /**
     * Almacena el mensaje de datos faltantes.
     */
    private String mensajeDatosIncorrectos;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para guardar.
    */
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup grupoBotones;
    /**
    * Label para el apellido materno.
    */
    private javax.swing.JLabel lblApellidoMaterno;
    /**
    * Label para el apellido paterno.
    */
    private javax.swing.JLabel lblApellidoPaterno;
    /**
    * Label para mostrar el estado del campo de texto apellido materno.
    */
    private javax.swing.JLabel lblEstadoAMaterno;
    /**
    * Label para mostrar el estado del campo de texto apellido paterno.
    */
    private javax.swing.JLabel lblEstadoAPaterno;
    /**
    * Label para mostrar el estdo del campo de texto nombre.
    */
    private javax.swing.JLabel lblEstadoNombre;
    /**
    * Label para mostrar el estado del campo de texto password.
    */
    private javax.swing.JLabel lblEstadoPassword;
    /**
    * Label para mostrar el estado del campo de texto usuario.
    */
    private javax.swing.JLabel lblEstadoUsuario;
    /**
    * Label para el nombre.
    */
    private javax.swing.JLabel lblNombre;
    /**
    * Lael para el password.
    */
    private javax.swing.JLabel lblPassword;
    /**
    * Label para el tipo de usuario.
    */
    private javax.swing.JLabel lblTipo;
    /**
    * Label de título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Label para el usuario.
    */
    private javax.swing.JLabel lblUsuario;
    /**
    * Botón radio para el tipo alumno.
    */
    private javax.swing.JRadioButton rbtnAlumno;
    /**
    * Botón radio para el tipo maestro.
    */
    private javax.swing.JRadioButton rbtnMaestro;
    /**
    * Campo de texto para el apellido materno.
    */
    private javax.swing.JTextField txtfApellidoMaterno;
    /**
    * Campo de texto para el apellido paterno.
    */
    private javax.swing.JTextField txtfApellidoPaterno;
    /**
    * Campo de texto para el nombre.
    */
    private javax.swing.JTextField txtfNombre;
    /**
    * Campo de texto para el usuario.
    */
    private javax.swing.JTextField txtfUsuario;
    /**
    * Campo de texto para el password.
    */
    private javax.swing.JPasswordField txtpPassword;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaRegistrarUsuario e inicializa sus atributos.
     */
    public VistaRegistrarUsuario() {
        initComponents();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        txtfApellidoMaterno.addKeyListener(this);
        txtfApellidoPaterno.addKeyListener(this);
        txtfNombre.addKeyListener(this);
        txtfUsuario.addKeyListener(this);
        txtfUsuario.addKeyListener(this);
        limpiar();
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
     * @param cvMantenerUsuarios El objeto encargado de realizar las
     * interacciones con la base de datos.
     */
    public void setControlador(CVMantenerUsuarios cvMantenerUsuarios) {
        this.cvMantenerUsuarios = cvMantenerUsuarios;
    }

    /**
     * Crea un objeto de tipo UsuarioDTO obteniendo los datos de los campos de
     * texto.
     *
     * @return Un UsuarioDTO si los campos fueron validos.<br>
     * Retorna null de otra forma.
     */
    private UsuarioDTO encapsularUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();
        mensajeDatosIncorrectos = "";
        String aPaterno = txtfApellidoPaterno.getText();
        String aMaterno = txtfApellidoMaterno.getText();
        String nombre = txtfNombre.getText();
        String pass = String.valueOf(txtpPassword.getPassword());
        String usuari = txtfUsuario.getText();

        boolean ok = validarCampos(nombre, aPaterno, aMaterno, usuari, pass);

        if (rbtnAlumno.isSelected()) {
            usuario.setTipo(UsuarioDTO.Tipo.Alumno);
            rbtnAlumno.setSelected(false);
        } else if (rbtnMaestro.isSelected()) {
            usuario.setTipo(UsuarioDTO.Tipo.Maestro);
            rbtnMaestro.setSelected(false);
        } else {
            ok = false;
            mensajeDatosIncorrectos += "Tipo de usuario";
        }

        if (!ok) {
            usuario = null;
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                    + "siguientes campos necesitan ser corregidos:\n"
                    + mensajeDatosIncorrectos;
        } else {
            usuario.setApellidoMaterno(Validador.capitalizarNombre(aMaterno));
            usuario.setApellidoPaterno(Validador.capitalizarNombre(aPaterno));
            usuario.setNombre(Validador.capitalizarNombre(nombre));
            usuario.setPassword(pass);
            usuario.setUsuario(usuari);
        }

        return usuario;
    }

    /**
     * Valida que los datos ingresados en los campos sean correctos.
     *
     * @param datos Vector de String que contiene todos los datos de los campos
     * de texto
     * @return Verdadero en caso de que todos los datos sean correctos.<br>
     * Falso de otra forma.
     */
    private boolean validarCampos(String... datos) {
        boolean ok = true;
        if (!Validador.esNombre(datos[NOMBRE])) {
            mensajeDatosIncorrectos += "Nombre\n";
            ok = false;
        }

        if (!Validador.esNombre(datos[APELLIDO_PATERNO])) {
            mensajeDatosIncorrectos += "Apellido Paterno\n";
            ok = false;
        }

        if (!Validador.esNombre(datos[APELLIDO_MATERNO])) {
            mensajeDatosIncorrectos += "Apellido Materno\n";
            ok = false;
        }

        if (!Validador.esUsuario(datos[USUARIO]) || !cvMantenerUsuarios.validarUsuario(datos[USUARIO])) {
            mensajeDatosIncorrectos += "Usuario\n";
            ok = false;
        }

        if (!Validador.esPassword(datos[PASSWORD])) {
            mensajeDatosIncorrectos += "Password\n";
            ok = false;
        }

        return ok;
    }

    /**
     * Muestra el estado del campo de texto dependiendo si la validacion fue
     * verdadera o falsa.
     *
     * @param o El objeto campo de texto al cual se quiere cambiar el estado.
     * @param estado Si es verdadero el estado sera correcto, si es falso el
     * estado sera incorrecto.
     */
    private void mostrarLabelEstado(Object o, boolean estado) {
        JTextField ob = (JTextField) o;
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

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotones = new javax.swing.ButtonGroup();
        lblPassword = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        txtfNombre.addFocusListener(this);
        txtfNombre.setName("lblEstadoNombre");
        lblTitulo = new javax.swing.JLabel();
        txtfApellidoPaterno = new javax.swing.JTextField();
        txtfApellidoPaterno.addFocusListener(this);
        txtfApellidoPaterno.setName("lblEstadoAPaterno");
        btnGuardar = new javax.swing.JButton();
        txtfApellidoMaterno = new javax.swing.JTextField();
        txtfApellidoMaterno.addFocusListener(this);
        txtfApellidoMaterno.setName("lblEstadoAMaterno");
        rbtnMaestro = new javax.swing.JRadioButton();
        txtfUsuario = new javax.swing.JTextField();
        txtfUsuario.addFocusListener(this);
        txtfUsuario.setName("lblEstadoUsuario");
        rbtnAlumno = new javax.swing.JRadioButton();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtpPassword = new javax.swing.JPasswordField();
        txtpPassword.addFocusListener(this);
        txtpPassword.setName("lblEstadoPassword");
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoAPaterno = new javax.swing.JLabel();
        lblEstadoAMaterno = new javax.swing.JLabel();
        lblEstadoUsuario = new javax.swing.JLabel();
        lblEstadoPassword = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        lblPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword.setText("Password:");

        lblTipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTipo.setText("Tipo de usuario:");
        lblTipo.setPreferredSize(new java.awt.Dimension(89, 20));

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setToolTipText("");
        txtfNombre.setNextFocusableComponent(txtfUsuario);
        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 30));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Usuario");

        txtfApellidoPaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoPaterno.setToolTipText("");
        txtfApellidoPaterno.setNextFocusableComponent(txtfApellidoMaterno);
        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setNextFocusableComponent(btnCancelar);
        btnGuardar.setPreferredSize(new java.awt.Dimension(80, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarUsuario(evt);
            }
        });

        txtfApellidoMaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoMaterno.setToolTipText("");
        txtfApellidoMaterno.setNextFocusableComponent(txtfNombre);
        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        grupoBotones.add(rbtnMaestro);
        rbtnMaestro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnMaestro.setText("Maestro");
        rbtnMaestro.setNextFocusableComponent(btnGuardar);

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setToolTipText("Ingresar letras y/o numeros");
        txtfUsuario.setNextFocusableComponent(txtpPassword);
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        grupoBotones.add(rbtnAlumno);
        rbtnAlumno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnAlumno.setText("Alumno");
        rbtnAlumno.setNextFocusableComponent(rbtnMaestro);

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblApellidoPaterno.setText("Apellido Paterno:");
        lblApellidoPaterno.setPreferredSize(new java.awt.Dimension(93, 15));

        lblApellidoMaterno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblApellidoMaterno.setText("Apellido Materno:");
        lblApellidoMaterno.setPreferredSize(new java.awt.Dimension(94, 15));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario:");

        txtpPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpPassword.setToolTipText("No caracteres especiales, mayor de 3 caracteres");
        txtpPassword.setNextFocusableComponent(rbtnAlumno);
        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 30));

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("Ingresar solo letras");

        lblEstadoAPaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoAPaterno.setToolTipText("Ingresar solo letras");

        lblEstadoAMaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoAMaterno.setToolTipText("Ingresar solo letras");

        lblEstadoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoUsuario.setToolTipText("Ingresar letras y/o números");

        lblEstadoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoPassword.setToolTipText("No caracteres especiales, mayor de 3 caracteres");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(txtfApellidoPaterno);
        btnCancelar.setPreferredSize(new java.awt.Dimension(83, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarRegistroUsuario(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 813, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblEstadoAPaterno))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstadoAMaterno))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblNombre)
                .addGap(152, 152, 152)
                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblEstadoNombre))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblUsuario)
                .addGap(153, 153, 153)
                .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblEstadoUsuario))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblPassword)
                .addGap(139, 139, 139)
                .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblEstadoPassword))
            .addGroup(layout.createSequentialGroup()
                .addGap(232, 232, 232)
                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(81, 81, 81)
                .addComponent(rbtnMaestro)
                .addGap(18, 18, 18)
                .addComponent(rbtnAlumno))
            .addGroup(layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAPaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAMaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoNombre))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUsuario)
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoUsuario))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPassword))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbtnMaestro)
                    .addComponent(rbtnAlumno))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Guarda al usuario en la base de datos, depues de que los datos sean
     * validados.
     *
     * @param evt Objeto que contiene informacion del evento.
     */
    private void guardarUsuario(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarUsuario
        UsuarioDTO usuario = encapsularUsuario();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Registro usuario",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        Integer id = cvMantenerUsuarios.guardarUsuario(usuario);

        if (id == null) {
            JOptionPane.showMessageDialog(this, "Usuario no registrado, problemas en la conexión", "Registro usuario",
                    JOptionPane.ERROR_MESSAGE);
        } else {
//            JOptionPane.showMessageDialog(this, "Registro completo", "Registro usuario",
//                    JOptionPane.INFORMATION_MESSAGE);
            limpiar();
        }
    }//GEN-LAST:event_guardarUsuario

    /**
     * Cancela el registro de usuarios y regresa a la pantalla principal.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void cancelarRegistroUsuario(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarRegistroUsuario
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_cancelarRegistroUsuario

    @Override
    public void limpiar() {
        txtfNombre.setText("");
        txtfApellidoPaterno.setText("");
        txtfApellidoMaterno.setText("");
        txtpPassword.setText("");
        txtfUsuario.setText("");
        lblEstadoAMaterno.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoNombre.setVisible(false);
        lblEstadoPassword.setVisible(false);
        lblEstadoUsuario.setVisible(false);
        grupoBotones.clearSelection();
    }

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
        boolean cambiar = true;
//        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
//                + "desea cambiar de pantalla?\nTodos los cambios no "
//                + "guardados se perderán", "Confirmación", JOptionPane.YES_NO_OPTION);
//        if (ok == JOptionPane.YES_OPTION) {
//            cambiar = true;
//        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        return padre.obtenerUsuarioActual();
    }

    @Override
    public void focusGained(FocusEvent e) {
    }

    /**
     * Valida el estado de los campos de texto cuando se pierde el foco en los
     * campos de texto.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusLost(FocusEvent e) {
        JTextField ob = (JTextField) e.getSource();

        if (ob == txtfNombre || ob == txtfApellidoPaterno || ob == txtfApellidoMaterno) {
            mostrarLabelEstado(ob, Validador.esNombre(ob.getText()));
        } else if (ob == txtfUsuario) {
            boolean ok = cvMantenerUsuarios.validarUsuario(ob.getText());
            if (ok) {
                mostrarLabelEstado(ob, Validador.esUsuario(ob.getText()));
            } else {
                mostrarLabelEstado(ob, false);
            }
        } else {
            mostrarLabelEstado(ob, Validador.esPassword(ob.getText()));
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
        JTextField txt = (JTextField) e.getSource();
        int longitud = Validador.LONGITUD_DATOS_USUARIO;
        if (!Validador.validarLongitud(longitud, txt.getText())) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();

        } else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(longitud, txt.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
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
    public void keyPressed(KeyEvent e) {
        JTextField txt = (JTextField) e.getSource();
        int longitud = Validador.LONGITUD_DATOS_USUARIO;
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(longitud, txt.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
