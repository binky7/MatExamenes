/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
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
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class VistaModificarUsuario extends javax.swing.JPanel implements
        InterfaceVista, FocusListener, KeyListener {

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

    /**
     * Crea un objeto VistaModificarUsuario e inicialza los atributos.
     */
    public VistaModificarUsuario() {
        initComponents();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        lblEstadoNombre.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoAMaterno.setVisible(false);
        lblEstadoUsuario.setVisible(false);
        lblEstadoPassword.setVisible(false);
        txtfApellidoMaterno.addKeyListener(this);
        txtfApellidoPaterno.addKeyListener(this);
        txtfNombre.addKeyListener(this);
        txtfUsuario.addKeyListener(this);
        txtfUsuario.addKeyListener(this);
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
     * Alamacena el control de la vista.
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
    public UsuarioDTO encapsularUsuario() {
        String aPaterno = txtfApellidoPaterno.getText();
        String aMaterno = txtfApellidoMaterno.getText();
        String nombre = txtfNombre.getText();
        String pass = txtfPassword.getText();
        String usuari = txtfUsuario.getText();
        mensajeDatosIncorrectos = "";

        boolean ok = validarCampos(nombre, aPaterno, aMaterno, usuari, pass);
        UsuarioDTO usuario;

        if (!ok) {
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                    + "siguientes campos necesitan ser corregidos:\n"
                    + mensajeDatosIncorrectos;
            return null;
        } else {
            usuario = cvMantenerUsuarios.getUsuarioModificar();
            usuario.setApellidoMaterno(Validador.capitalizarNombre(aMaterno));
            usuario.setApellidoPaterno(Validador.capitalizarNombre(aPaterno));
            usuario.setNombre(Validador.capitalizarNombre(nombre));
            usuario.setUsuario(usuari);
            usuario.setPassword(pass);
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

        if (!Validador.esUsuario(datos[USUARIO])) {
            mensajeDatosIncorrectos += "Usuario\n";
            ok = false;
        } else if (cvMantenerUsuarios.getUsuarioModificar().getUsuario().compareTo(datos[USUARIO]) != 0) {
            if (!cvMantenerUsuarios.validarUsuario(datos[USUARIO])) {
                mensajeDatosIncorrectos += "Usuario\n";
                ok = false;
            }
        }

        if (!Validador.esPassword(datos[PASSWORD])) {
            mensajeDatosIncorrectos += "Password\n";
            ok = false;
        }

        return ok;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        txtfNombre.addFocusListener(this);
        txtfNombre.setName("lblEstadoNombre");
        txtfApellidoPaterno = new javax.swing.JTextField();
        txtfApellidoPaterno.addFocusListener(this);
        txtfApellidoPaterno.setName("lblEstadoAPaterno");
        txtfApellidoMaterno = new javax.swing.JTextField();
        txtfApellidoMaterno.addFocusListener(this);
        txtfApellidoMaterno.setName("lblEstadoAMaterno");
        txtfUsuario = new javax.swing.JTextField();
        txtfUsuario.addFocusListener(this);
        txtfUsuario.setName("lblEstadoUsuario");
        txtfPassword = new javax.swing.JTextField();
        txtfPassword.addFocusListener(this);
        txtfPassword.setName("lblEstadoPassword");
        lblTitulo = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        lblEstadoNombre = new javax.swing.JLabel();
        lblEstadoAPaterno = new javax.swing.JLabel();
        lblEstadoAMaterno = new javax.swing.JLabel();
        lblEstadoUsuario = new javax.swing.JLabel();
        lblEstadoPassword = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        lblNombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblApellidoPaterno.setText("Apellido Paterno:");

        lblApellidoMaterno.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblApellidoMaterno.setText("Apellido Materno:");

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword.setText("Password:");

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setNextFocusableComponent(txtfUsuario);
        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 30));

        txtfApellidoPaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoPaterno.setNextFocusableComponent(txtfApellidoMaterno);
        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        txtfApellidoMaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoMaterno.setNextFocusableComponent(txtfNombre);
        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setToolTipText("Ingresar letras y/o numeros");
        txtfUsuario.setNextFocusableComponent(txtfPassword);
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        txtfPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfPassword.setToolTipText("No caracteres especiales, mayor de 3 caracteres");
        txtfPassword.setNextFocusableComponent(btnGuardar);
        txtfPassword.setPreferredSize(new java.awt.Dimension(100, 30));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Usuario");

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setNextFocusableComponent(btnCancelar);
        btnGuardar.setPreferredSize(new java.awt.Dimension(75, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCambios(evt);
            }
        });

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoNombre.setToolTipText("Ingresar solo letras");

        lblEstadoAPaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoAPaterno.setToolTipText("Ingresar solo letras");

        lblEstadoAMaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoAMaterno.setToolTipText("Ingresar solo letras");

        lblEstadoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoUsuario.setToolTipText("Ingresar letras y/o numeros");

        lblEstadoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoPassword.setToolTipText("No caracteres especiales, mayor de 3 caracteres");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setNextFocusableComponent(txtfApellidoMaterno);
        btnCancelar.setPreferredSize(new java.awt.Dimension(75, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarModificacion(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuario)
                                    .addComponent(lblPassword))
                                .addGap(157, 157, 157)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEstadoUsuario))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEstadoPassword))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(170, 170, 170)
                                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(lblEstadoNombre))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(lblApellidoPaterno))
                                    .addComponent(lblApellidoMaterno))
                                .addGap(105, 105, 105)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEstadoAPaterno))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(lblEstadoAMaterno)))))))
                .addGap(42, 42, 42))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblApellidoPaterno)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAPaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAMaterno)
                    .addComponent(lblApellidoMaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombre)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoNombre))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoUsuario)
                    .addComponent(lblUsuario))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPassword)
                    .addComponent(txtfPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPassword))
                .addGap(120, 120, 120)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón de modificar.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void guardarCambios(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCambios
        boolean ok;
        if (encapsularUsuario() != null) {
            //datos capturados correctos
            ok = cvMantenerUsuarios.modificarUsuario(cvMantenerUsuarios.getUsuarioModificar());
            if (ok) {
                JOptionPane.showMessageDialog(this, "Usuario Modificado", "Modificación",
                        JOptionPane.INFORMATION_MESSAGE);
                limpiar();
                padre.mostrarVistaConEntidad(cvMantenerUsuarios.getUsuariosBuscados(),
                        Vista.ConsultarUsuarios);

            } else {
                JOptionPane.showMessageDialog(this, "Usuario no Modiicado", "Modificación",
                        JOptionPane.ERROR_MESSAGE);
                padre.mostrarVista(Vista.ConsultarUsuarios);
            }
        } else {
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Modificación",
                    JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_guardarCambios

    /**
     * Método llamado cuando se acciona el botón de cancelar.
     *
     * @param evt Objeto que contiene la información del evento.
     */
    private void cancelarModificacion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarModificacion
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.ConsultarUsuarios);
            limpiar();
        }
    }//GEN-LAST:event_cancelarModificacion

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

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        UsuarioDTO usuario = (UsuarioDTO) entidad;
        cvMantenerUsuarios.setUsuarioModificar(usuario);
        txtfApellidoMaterno.setText(usuario.getApellidoMaterno());
        txtfApellidoPaterno.setText(usuario.getApellidoPaterno());
        txtfNombre.setText(usuario.getNombre());
        txtfPassword.setText(usuario.getPassword());
        txtfUsuario.setText(usuario.getUsuario());
    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public void limpiar() {
        txtfApellidoMaterno.setText("");
        txtfApellidoPaterno.setText("");
        txtfNombre.setText("");
        txtfUsuario.setText("");
        txtfPassword.setText("");
        lblEstadoAMaterno.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoNombre.setVisible(false);
        lblEstadoPassword.setVisible(false);
        lblEstadoUsuario.setVisible(false);
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
            boolean ok = true;
            if (ob.getText().compareTo(cvMantenerUsuarios.getUsuarioModificar().getUsuario()) != 0) {
                if (!cvMantenerUsuarios.validarUsuario(ob.getText())) {
                    ok = false;
                } else {
                    ok = cvMantenerUsuarios.validarUsuario(ob.getText());
                }
            }
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
        if (e.getKeyChar() == '\n') {
            btnGuardar.doClick();
        }
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar la modificación.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para modificar el usuario.
    */
    private javax.swing.JButton btnGuardar;
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
    * Label para mostrar el estado del campo de texto del nombre.
    */
    private javax.swing.JLabel lblEstadoNombre;
    /**
    * Label para mostrar el estado del campo de texto de password.
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
    * Label para el password.
    */
    private javax.swing.JLabel lblPassword;
    /**
    * Label del título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Label para el usuario.
    */
    private javax.swing.JLabel lblUsuario;
    /**
    * Campo de texto para ingresar el apellido materno.
    */
    private javax.swing.JTextField txtfApellidoMaterno;
    /**
    * Campo de texto para ingresar el apellido paterno.
    */
    private javax.swing.JTextField txtfApellidoPaterno;
    /**
    * Campo de texto para ingresar el nombre.
    */
    private javax.swing.JTextField txtfNombre;
    /**
    * Campo de texto tipo password para ingresar el password.
    */
    private javax.swing.JTextField txtfPassword;
    /**
    * Campo de texto para ingresar el usuario.
    */
    private javax.swing.JTextField txtfUsuario;
    // End of variables declaration//GEN-END:variables
}
