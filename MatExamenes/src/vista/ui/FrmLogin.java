/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import vista.controlador.CVLogin;

/**
 *
 * @author Jesus Donaldo
 */
public class FrmLogin extends javax.swing.JFrame implements KeyListener {

    private final FrmPrincipal p;
    private final CVLogin cvLogin;

    /**
     * Creates new form VistaLogin
     */
    public FrmLogin() {
        initComponents();
        p = new FrmPrincipal();
        cvLogin = new CVLogin();
        txtfUsuario.addKeyListener(this);
        txtpPassword.addKeyListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnLogin = new javax.swing.JButton();
        txtfUsuario = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtpPassword = new javax.swing.JPasswordField();
        lblTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(500, 370));
        setResizable(false);

        btnLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuarios.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.setNextFocusableComponent(lblUsuario);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin(evt);
            }
        });

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setNextFocusableComponent(txtpPassword);
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPassword.setText("Password:");

        txtpPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpPassword.setNextFocusableComponent(btnLogin);
        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 25));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(44, 44, 44))
            .addGroup(layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblUsuario)
                        .addGap(104, 104, 104)
                        .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addGap(90, 90, 90)
                        .addComponent(txtpPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(112, 112, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(lblTitulo)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblUsuario))
                    .addComponent(txtfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(lblPassword))
                    .addComponent(txtpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin
        UsuarioDTO usuario;
        usuario = encapsularUsuario();

        int error = cvLogin.validarCredenciales(usuario);
        if (error == -1) {
            UsuarioDTO usuarioValido = cvLogin.obtenerUsuarioValidado();
            JOptionPane.showMessageDialog(this, "Bienvenido "
                    + usuarioValido.getNombre(), "Login", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/usuario.png")));
            p.setUsuarioActual(usuarioValido);
            Tipo tipo = usuarioValido.getTipo();
            if (tipo == Tipo.Admin) {
                p.setVistaAdmin();
            } else if (tipo == Tipo.Alumno) {
                p.setVistaAlumno();
            } else if (tipo == Tipo.Maestro) {
                p.setVistaMaestro();
            }
            p.setVisible(true);
            dispose();
        } else if (error == 1) {
            JOptionPane.showMessageDialog(this, "Password Incorrecto",
                    "Login", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/incorrecto.png")));
        } else {
            JOptionPane.showMessageDialog(this, "Usuario no existente",
                    "Login", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource("/recursos/usuarioIncorrecto.png")));
        }


    }//GEN-LAST:event_btnLogin

    private UsuarioDTO encapsularUsuario() {
        UsuarioDTO usuario = new UsuarioDTO();

        String pass = String.valueOf(txtpPassword.getPassword());
        String usuari = txtfUsuario.getText();

        usuario.setPassword(pass);
        usuario.setUsuario(usuari);

        return usuario;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == '\n') {
            btnLogin.doClick();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                //UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtfUsuario;
    private javax.swing.JPasswordField txtpPassword;
    // End of variables declaration//GEN-END:variables
}
