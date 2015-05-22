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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import vista.controlador.CVLogin;

/**
 * JFrame que mostrara la interfaz gráfica del Login.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class FrmLogin extends javax.swing.JFrame implements KeyListener {

    /**
     * Objeto de la interfaz gráfica principal.
     */
    private final FrmPrincipal p;
    private final CVLogin cvLogin;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón usado para iniciar sesión.
    */
    private javax.swing.JButton btnLogin;
    /**
    * Label texto password.
    */
    private javax.swing.JLabel lblPassword;
    /**
    * Label para el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Label texto usuario.
    */
    private javax.swing.JLabel lblUsuario;
    /**
    * Campo de texto utilizado para ingresar el usuario.
    */
    private javax.swing.JTextField txtfUsuario;
    /**
    * Campo de texto tipo password utilizado para ingresar el password.
    */
    private javax.swing.JPasswordField txtpPassword;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmLogin e inicializa sus atributos.
     */
    public FrmLogin() {
        initComponents();
        p = new FrmPrincipal();
        cvLogin = new CVLogin();
        txtfUsuario.addKeyListener(this);
        txtpPassword.addKeyListener(this);
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
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
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogin(evt);
            }
        });

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        lblPassword.setText("Password:");

        txtpPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                .addContainerGap(112, Short.MAX_VALUE))
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

    /**
     * Método llamado cuando se acciona el botón de Login.
     *
     * @param evt Objeto que contiene información sobre evento.
     */
    private void btnLogin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogin
        UsuarioDTO usuario;
        usuario = encapsularUsuario();

        int error = cvLogin.validarCredenciales(usuario);
        if (error == -1) {
            //Credenciales validas
            UsuarioDTO usuarioValido = cvLogin.getUsuarioValidado();
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

    /**
     * Crea un objeto tipo UsuarioDTO con el usuario y password obtenidos de los
     * campos de texto.
     *
     * @return Un objeto tipo UsuarioDTO.
     */
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
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * @param args Los argumentos de la línea de comandos.
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

}
