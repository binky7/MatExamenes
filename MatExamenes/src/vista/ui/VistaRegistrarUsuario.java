/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerUsuarios;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author Alf
 */
public class VistaRegistrarUsuario extends javax.swing.JPanel implements InterfaceVista {

    private CVMantenerUsuarios cvMantenerUsuarios;
    private InterfaceVista padre;
    private final Border bordeMal;
    private final Border bordeOriginal;

    /**
     * Creates new form VistaRegistrarUsuario
     */
    public VistaRegistrarUsuario() {
        initComponents();
        bordeMal = BorderFactory.createLineBorder(Color.red, 2);
        bordeOriginal = txtfNombre.getBorder();
        lblErrorNombre.setVisible(false);
        lblErrorNombre.setText("Ingresar solo letras");
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
            txtfNombre.setBorder(bordeMal);
            lblErrorNombre.setVisible(true);

        } else {
            txtfNombre.setBorder(bordeOriginal);
            lblErrorNombre.setVisible(false);
        }

        if (!Validador.esNombre(datos[1])) {
            txtfApellidoPaterno.setBorder(bordeMal);
            ok = false;
        } else {
            txtfApellidoPaterno.setBorder(bordeOriginal);
        }

        if (!Validador.esNombre(datos[2])) {
            txtfApellidoMaterno.setBorder(bordeMal);
            ok = false;
        } else {
            txtfApellidoMaterno.setBorder(bordeOriginal);
        }

        if (!Validador.esUsuario(datos[3])) {
            txtfUsuario.setBorder(bordeMal);
            ok = false;
        } else {
            txtfUsuario.setBorder(bordeOriginal);
        }

        if (!Validador.esPassword(datos[4])) {
            txtpPassword.setBorder(bordeMal);
            ok = false;
        } else {
            txtpPassword.setBorder(bordeOriginal);
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
        lblTitulo = new javax.swing.JLabel();
        txtfApellidoPaterno = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        txtfApellidoMaterno = new javax.swing.JTextField();
        rbtnMaestro = new javax.swing.JRadioButton();
        txtfUsuario = new javax.swing.JTextField();
        rbtnAlumno = new javax.swing.JRadioButton();
        lblNombre = new javax.swing.JLabel();
        lblApellidoPaterno = new javax.swing.JLabel();
        lblApellidoMaterno = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtpPassword = new javax.swing.JPasswordField();
        lblErrorNombre = new javax.swing.JLabel();

        lblPassword.setText("Password:");

        lblTipo.setText("Tipo de usuario");

        txtfNombre.setPreferredSize(new java.awt.Dimension(100, 25));
        txtfNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtfNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfNombreFocusLost(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("Registrar Usuario");

        txtfApellidoPaterno.setPreferredSize(new java.awt.Dimension(100, 25));
        txtfApellidoPaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfApellidoPaternoFocusLost(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.setMaximumSize(new java.awt.Dimension(100, 23));
        btnGuardar.setMinimumSize(new java.awt.Dimension(100, 23));
        btnGuardar.setPreferredSize(new java.awt.Dimension(80, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtfApellidoMaterno.setPreferredSize(new java.awt.Dimension(100, 25));
        txtfApellidoMaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfApellidoMaternoFocusLost(evt);
            }
        });

        buttonGroup1.add(rbtnMaestro);
        rbtnMaestro.setText("Maestro");

        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 25));
        txtfUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfUsuarioFocusLost(evt);
            }
        });

        buttonGroup1.add(rbtnAlumno);
        rbtnAlumno.setText("Alumno");

        lblNombre.setText("Nombre:");

        lblApellidoPaterno.setText("Apellido Paterno:");

        lblApellidoMaterno.setText("Apellido Materno:");

        lblUsuario.setText("Usuario:");

        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 25));
        txtpPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpPasswordFocusLost(evt);
            }
        });

        lblErrorNombre.setForeground(new java.awt.Color(255, 0, 0));
        lblErrorNombre.setText(".");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUsuario)
                                    .addComponent(lblPassword)
                                    .addComponent(lblTipo))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbtnMaestro)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbtnAlumno))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(193, 193, 193)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtpPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblApellidoPaterno)
                                    .addComponent(lblApellidoMaterno))
                                .addGap(182, 182, 182)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblErrorNombre)))))))
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre)
                    .addComponent(lblErrorNombre))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoPaterno))
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellidoMaterno))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
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

    private void txtfNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfNombreFocusLost
        // TODO add your handling code here:
        String nombre = txtfNombre.getText();

        if (!Validador.esNombre(nombre)) {
            txtfNombre.setBorder(bordeMal);
            lblErrorNombre.setVisible(true);

        } else {
            txtfNombre.setBorder(bordeOriginal);
            lblErrorNombre.setVisible(false);
        }
    }//GEN-LAST:event_txtfNombreFocusLost

    private void txtfApellidoPaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfApellidoPaternoFocusLost
        // TODO add your handling code here:
        String aPaterno = txtfApellidoPaterno.getText();

        if (!Validador.esNombre(aPaterno)) {
            txtfApellidoPaterno.setBorder(bordeMal);
        } else {
            txtfApellidoPaterno.setBorder(bordeOriginal);
        }
    }//GEN-LAST:event_txtfApellidoPaternoFocusLost

    private void txtfApellidoMaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfApellidoMaternoFocusLost
        // TODO add your handling code here:
        String aMaterno = txtfApellidoMaterno.getText();

        if (!Validador.esNombre(aMaterno)) {
            txtfApellidoMaterno.setBorder(bordeMal);
        } else {
            txtfApellidoMaterno.setBorder(bordeOriginal);
        }

    }//GEN-LAST:event_txtfApellidoMaternoFocusLost

    private void txtfUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfUsuarioFocusLost
        // TODO add your handling code here:
        String usuario = txtfUsuario.getText();

        if (!Validador.esUsuario(usuario)) {
            txtfUsuario.setBorder(bordeMal);
        } else {
            txtfUsuario.setBorder(bordeOriginal);
        }
    }//GEN-LAST:event_txtfUsuarioFocusLost

    private void txtpPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpPasswordFocusLost
        // TODO add your handling code here:
        String pass = String.valueOf(txtpPassword.getPassword());

        if (!Validador.esPassword(pass)) {
            txtpPassword.setBorder(bordeMal);
        } else {
            txtpPassword.setBorder(bordeOriginal);
        }
    }//GEN-LAST:event_txtpPasswordFocusLost

    private void txtfNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfNombreFocusGained
        
    }//GEN-LAST:event_txtfNombreFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel lblApellidoMaterno;
    private javax.swing.JLabel lblApellidoPaterno;
    private javax.swing.JLabel lblErrorNombre;
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
