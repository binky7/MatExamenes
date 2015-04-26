/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;
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
//    private final Border BORDE_MAL;
//    private final Border BORDE_ORIGINAL;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;

    /**
     * Creates new form VistaRegistrarUsuario
     */
    public VistaRegistrarUsuario() {
        initComponents();
//        BORDE_MAL = BorderFactory.createLineBorder(Color.red, 2);
//        BORDE_ORIGINAL = txtfNombre.getBorder();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
//        lblEstadoNombre.setText("Ingresar solo letras");
        lblEstadoAMaterno.setVisible(false);
        lblEstadoAPaterno.setVisible(false);
        lblEstadoNombre.setVisible(false);
        lblEstadoPassword.setVisible(false);
        lblEstadoUsuario.setVisible(false);
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    public void setControlador(CVMantenerUsuarios cvMantenerUsuarios) {
        this.cvMantenerUsuarios = cvMantenerUsuarios;
    }

    private boolean validarUsuario(String usuario) {
        boolean ok = true;
        cvMantenerUsuarios.validarUsuario(usuario);
        return ok;
    }

    /**
     *
     * @return retorna un objeto de tipo usuario si los datos fueron validados.
     * de otra forma retorna null.
     */
    private UsuarioDTO encapsularUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();

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
        boolean ok = false;
        if (!Validador.esNombre(datos[0])) {
            ok = false;
//            txtfNombre.setBorder(BORDE_MAL);
            lblEstadoNombre.setVisible(true);

        } else {
//            txtfNombre.setBorder(BORDE_ORIGINAL);
            lblEstadoNombre.setVisible(false);
        }

        if (!Validador.esNombre(datos[1])) {
//            txtfApellidoPaterno.setBorder(BORDE_MAL);
            ok = false;
        } else {
//            txtfApellidoPaterno.setBorder(BORDE_ORIGINAL);
        }

        if (!Validador.esNombre(datos[2])) {
//            txtfApellidoMaterno.setBorder(BORDE_MAL);
            ok = false;
        } else {
//            txtfApellidoMaterno.setBorder(BORDE_ORIGINAL);
        }

        if (!Validador.esUsuario(datos[3])) {
//            txtfUsuario.setBorder(BORDE_MAL);
            ok = false;
        } else {
//            txtfUsuario.setBorder(BORDE_ORIGINAL);
        }

        if (!Validador.esPassword(datos[4])) {
//            txtpPassword.setBorder(BORDE_MAL);
            ok = false;
        } else {
//            txtpPassword.setBorder(BORDE_ORIGINAL);
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
        if (rbtnAlumno.isSelected()) {
            rbtnAlumno.setSelected(false);
        } else {
            rbtnMaestro.setSelected(false);
        }

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

        lblPassword.setText("Password:");

        lblTipo.setText("Tipo de usuario");

        txtfNombre.setToolTipText("Ingresar solo letras");
        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 25));

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("Registrar Usuario");

        txtfApellidoPaterno.setToolTipText("Ingresar solo letras");
        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 25));

        btnGuardar.setText("Guardar");
        btnGuardar.setMaximumSize(new java.awt.Dimension(100, 23));
        btnGuardar.setMinimumSize(new java.awt.Dimension(100, 23));
        btnGuardar.setPreferredSize(new java.awt.Dimension(80, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtfApellidoMaterno.setToolTipText("Ingresar solo letras");
        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 25));

        buttonGroup1.add(rbtnMaestro);
        rbtnMaestro.setText("Maestro");

        txtfUsuario.setToolTipText("Ingresar numeros y/o letras");
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 25));

        buttonGroup1.add(rbtnAlumno);
        rbtnAlumno.setText("Alumno");

        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setText("Apellido Paterno:");

        lblApellidoMaterno.setText("Apellido Materno:");

        lblUsuario.setText("Usuario:");

        txtpPassword.setToolTipText("No caracteres especiales\nmayor de 4 caracteres");
        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 25));

        lblEstadoNombre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoAPaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoAMaterno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblEstadoPassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTipo)
                                .addGap(149, 149, 149)
                                .addComponent(rbtnMaestro)
                                .addGap(18, 18, 18)
                                .addComponent(rbtnAlumno))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblNombre)
                                        .addGap(226, 226, 226)
                                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblApellidoPaterno)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                                    .addComponent(lblApellidoMaterno)
                                                    .addGap(182, 182, 182)
                                                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstadoNombre)
                                    .addComponent(lblEstadoAPaterno)
                                    .addComponent(lblEstadoAMaterno)
                                    .addComponent(lblEstadoUsuario)
                                    .addComponent(lblEstadoPassword)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(lblTitulo)))
                .addContainerGap(222, Short.MAX_VALUE))
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
                        .addComponent(lblNombre)))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno)
                    .addComponent(lblEstadoAPaterno))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno)
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
                    .addComponent(lblTipo)
                    .addComponent(rbtnMaestro)
                    .addComponent(rbtnAlumno))
                .addGap(28, 28, 28)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        UsuarioDTO usuario = encapsularUsuario();
        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "Datos incorrectos, porfavor "
                    + "sólo ingresa números y letras");
            return;
        }
        Integer id = cvMantenerUsuarios.guardarUsuario(usuario);

        if (id == null) {
            //No se pudo guardar porque habia un usuario duplicado
            JOptionPane.showMessageDialog(this, "Usuario existente");
        } else {
            JOptionPane.showMessageDialog(this, "Usuario Registrado");
            padre.mostrarVista(InterfaceVista.Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

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
//        ((JTextField) e.getSource()).setBorder(BORDE_ORIGINAL);
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField ob = (JTextField) e.getSource();

        if (ob == txtfNombre || ob == txtfApellidoPaterno || ob == txtfApellidoMaterno) {
            mostrarLabelEstado(ob, Validador.esNombre(ob.getText()));
//            txtfNombre.setBorder(BORDE_MAL);

        } else if (ob == txtfUsuario) {
            mostrarLabelEstado(ob, Validador.esUsuario(ob.getText()));
        } else {
            mostrarLabelEstado(ob, Validador.esPassword(ob.getText()));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
