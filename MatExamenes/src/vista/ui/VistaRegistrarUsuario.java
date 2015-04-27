/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
 * @author Alf
 */
public class VistaRegistrarUsuario extends javax.swing.JPanel implements
        InterfaceVista, FocusListener {

    private CVMantenerUsuarios cvMantenerUsuarios;
    private InterfaceVista padre;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;
    private final int NOMBRE;
    private final int APELLIDO_PATERNO;
    private final int APELLIDO_MATERNO;
    private final int USUARIO;
    private final int PASSWORD;
    private String mensajeDatosIncorrectos;

    /**
     * Creates new form VistaRegistrarUsuario
     */
    public VistaRegistrarUsuario() {
        initComponents();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        lblEstadoAMaterno.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoNombre.setVisible(false);
        lblEstadoPassword.setVisible(false);
        lblEstadoUsuario.setVisible(false);
        NOMBRE = 0;
        APELLIDO_PATERNO = 1;
        APELLIDO_MATERNO = 2;
        USUARIO = 3;
        PASSWORD = 4;
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    public void setControlador(CVMantenerUsuarios cvMantenerUsuarios) {
        this.cvMantenerUsuarios = cvMantenerUsuarios;
    }

    /**
     *
     * @return retorna un objeto de tipo usuario si los datos fueron validados.
     * de otra forma retorna null.
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
        } else if (rbtnMaestro.isSelected()) {
            usuario.setTipo(UsuarioDTO.Tipo.Maestro);
        } else {
            ok = false;
        }

        if (!ok) {
            usuario = null;
            mensajeDatosIncorrectos = "No se puede completar la operación, los "
                    + "siguientes campos necesitan ser corregidos:\n"
                    + mensajeDatosIncorrectos;
        } else {
            usuario.setApellidoMaterno(aMaterno);
            usuario.setApellidoPaterno(aPaterno);
            usuario.setNombre(nombre);
            usuario.setPassword(pass);
            usuario.setUsuario(usuari);
        }

        return usuario;
    }

    /**
     *
     * @return retorna verdadero si todos los campos son correctos
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

    @Override
    public void limpiar() {
        txtfNombre.setText("");
        txtfApellidoPaterno.setText("");
        txtfApellidoMaterno.setText("");
        txtpPassword.setText("");
        txtfUsuario.setText("");
        rbtnAlumno.setSelected(false);
        rbtnMaestro.setSelected(false);
        lblEstadoAMaterno.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoNombre.setVisible(false);
        lblEstadoPassword.setVisible(false);
        lblEstadoUsuario.setVisible(false);
        

    }

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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

        lblPassword.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPassword.setText("Password:");

        lblTipo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblTipo.setText("Tipo de usuario:");
        lblTipo.setPreferredSize(new java.awt.Dimension(89, 20));

        txtfNombre.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombre.setToolTipText("Ingresar solo letras");
        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 30));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setText("Registrar Usuario");

        txtfApellidoPaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoPaterno.setToolTipText("Ingresar solo letras");
        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonGuardar_1.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(80, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtfApellidoMaterno.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfApellidoMaterno.setToolTipText("Ingresar solo letras");
        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonGroup1.add(rbtnMaestro);
        rbtnMaestro.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnMaestro.setText("Maestro");

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setToolTipText("Ingresar numeros y/o letras");
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        buttonGroup1.add(rbtnAlumno);
        rbtnAlumno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnAlumno.setText("Alumno");

        lblNombre.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblNombre.setText("Nombre:");
        lblNombre.setPreferredSize(new java.awt.Dimension(48, 20));

        lblApellidoPaterno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblApellidoPaterno.setText("Apellido Paterno:");
        lblApellidoPaterno.setPreferredSize(new java.awt.Dimension(93, 20));

        lblApellidoMaterno.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblApellidoMaterno.setText("Apellido Materno:");
        lblApellidoMaterno.setPreferredSize(new java.awt.Dimension(94, 20));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblUsuario.setText("Usuario:");

        txtpPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpPassword.setToolTipText("No caracteres especiales\nmayor de 4 caracteres");
        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 30));

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoAPaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoAMaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/botonCancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(83, 30));
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(226, 226, 226)
                                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblPassword)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblUsuario)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(165, 165, 165)
                                            .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstadoNombre)
                                    .addComponent(lblEstadoAPaterno)
                                    .addComponent(lblEstadoAMaterno)
                                    .addComponent(lblEstadoUsuario)
                                    .addComponent(lblEstadoPassword)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(127, 127, 127)
                                .addComponent(rbtnMaestro)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnAlumno))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(209, 209, 209)
                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(lblTitulo)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstadoNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAPaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoAMaterno))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario)
                    .addComponent(lblEstadoUsuario))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPassword))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnMaestro)
                    .addComponent(rbtnAlumno))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        UsuarioDTO usuario = encapsularUsuario();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Registro",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/incorrecto.png")));
            return;
        }
        Integer id = cvMantenerUsuarios.guardarUsuario(usuario);

        if (id == null) {
            //No se pudo guardar porque habia un usuario duplicado
            JOptionPane.showMessageDialog(this, "Usuario existente", "Registro",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/usuarioIncorrecto.png")));
        } else {
            JOptionPane.showMessageDialog(this, "Usuario Registrado", "Registro",
                    JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/usuarioRegistrado.png")));
            padre.mostrarVista(InterfaceVista.Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

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
    public void focusGained(FocusEvent e) {
    }

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblEstadoAMaterno;
    private javax.swing.JLabel lblEstadoAPaterno;
    private javax.swing.JLabel lblEstadoNombre;
    private javax.swing.JLabel lblEstadoPassword;
    private javax.swing.JLabel lblEstadoUsuario;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JRadioButton rbtnAlumno;
    private javax.swing.JRadioButton rbtnMaestro;
    private javax.swing.JTextField txtfApellidoMaterno;
    private javax.swing.JTextField txtfApellidoPaterno;
    private javax.swing.JTextField txtfNombre;
    private javax.swing.JTextField txtfUsuario;
    private javax.swing.JPasswordField txtpPassword;
    // End of variables declaration//GEN-END:variables

}
