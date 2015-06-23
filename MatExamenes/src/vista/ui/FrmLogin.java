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

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.dto.UsuarioDTO;
import modelo.dto.UsuarioDTO.Tipo;
import remoteAccess.Enlace;
import vista.controlador.CVLogin;
import vista.controlador.RespaldoJSON;

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
    private FrmPrincipal frmPrincipal;
    /**
     * Controlador de la vista del caso de uso Login, funciona para manejar la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores
     */
    private CVLogin cvLogin;

    /**
     * JFrame usada para combiar la configuración de la conexión con el
     * servidor.
     */
    private FrmConfiguracionConexion configuracion;

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
    * Menu para la configuración.
    */
    private javax.swing.JMenu mConfiguracion;
    /**
    * Barra de menú de Login.
    */
    private javax.swing.JMenuBar mbLogin;
    /**
    * MenuItem de la conexión
    */
    private javax.swing.JMenuItem miConexion;
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
        agregarIconos();
        frmPrincipal = new FrmPrincipal();
        cvLogin = new CVLogin();
        txtfUsuario.addKeyListener(this);
        txtpPassword.addKeyListener(this);
        RespaldoJSON respaldo = new RespaldoJSON();
        Map<String, String> map = null;
        try {
            map = respaldo.obtenerIpPuerto();
            Enlace.setIp(map.get(RespaldoJSON.IP));
            Enlace.setPuerto(Integer.parseInt(map.get(RespaldoJSON.PUERTO)));
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (NullPointerException ex) {
            Enlace.setIp(RespaldoJSON.LOCAL_HOST);
            Enlace.setPuerto(Integer.parseInt(RespaldoJSON.PUERTO_DEFECTO));
        }
        System.out.println(System.getProperty("os.name"));
    }

    /**
     * Agrega los iconos a la interfaz gráfica y a la barra de tareas.
     */
    private void agregarIconos() {
        List<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono16.png")).getImage());
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono32.png")).getImage());
        this.setIconImages(icons);
    }

    /**
     * Crea un objeto tipo UsuarioDTO con el usuario y password obtenidos de los
     * campos de texto.
     *
     * @return Un objeto tipo UsuarioDTO.
     */
    private UsuarioDTO encapsularUsuario() {
        UsuarioDTO unUsuario = new UsuarioDTO();

        String pass = String.valueOf(txtpPassword.getPassword());
        String usuario = txtfUsuario.getText();
        if (pass.isEmpty() || usuario.isEmpty()) {
            unUsuario = null;
        } else {
            unUsuario.setPassword(pass);
            unUsuario.setUsuario(usuario);
        }

        return unUsuario;
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
        mbLogin = new javax.swing.JMenuBar();
        mConfiguracion = new javax.swing.JMenu();
        miConexion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setMinimumSize(new java.awt.Dimension(500, 370));
        setResizable(false);

        btnLogin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/usuarios.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login(evt);
            }
        });

        txtfUsuario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfUsuario.setPreferredSize(new java.awt.Dimension(100, 30));

        lblUsuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario:");

        lblPassword.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPassword.setText("Password:");

        txtpPassword.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtpPassword.setPreferredSize(new java.awt.Dimension(100, 25));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Login");

        mConfiguracion.setText("Configuración");

        miConexion.setText("Conexión");
        miConexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConexionActionPerformed(evt);
            }
        });
        mConfiguracion.add(miConexion);

        mbLogin.add(mConfiguracion);

        setJMenuBar(mbLogin);

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
                .addContainerGap(101, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón de Login.<br>
     * Se encargara de validar las credenciales.
     *
     * @param evt Objeto que contiene información sobre evento.
     */
    private void login(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login
        UsuarioDTO usuario;
        usuario = encapsularUsuario();

        if (usuario == null) {
            //Si no ingresaron algun dato.
            JOptionPane.showMessageDialog(this, "Datos faltantes, favor de llenar todos los datos",
                    "Login", JOptionPane.ERROR_MESSAGE);
        } else {
            int error = cvLogin.validarCredenciales(usuario);
            if (error == -1) {
                //Credenciales validas
                UsuarioDTO usuarioValido = cvLogin.getUsuarioValidado();
                JOptionPane.showMessageDialog(this, "Bienvenido "
                        + usuarioValido.getNombre(), "Login", JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon(getClass().getResource("/recursos/usuario.png")));
                frmPrincipal.setUsuarioActual(usuarioValido);
                Tipo tipo = usuarioValido.getTipo();
                if (tipo == Tipo.Admin) {
                    frmPrincipal.setVistaAdmin();
                } else if (tipo == Tipo.Alumno) {
                    frmPrincipal.setVistaAlumno();
                } else if (tipo == Tipo.Maestro) {
                    frmPrincipal.setVistaMaestro();
                }
                frmPrincipal.setVisible(true);
                dispose();
            } else if (error == 1) {
                JOptionPane.showMessageDialog(this, "Password Incorrecto",
                        "Login", JOptionPane.ERROR_MESSAGE);
            } else if (error == 0) {
                JOptionPane.showMessageDialog(this, "Usuario no existente",
                        "Login", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Problemas con la conexión",
                        "Login", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (configuracion != null) {
            configuracion = null;
        }
    }//GEN-LAST:event_login

    /**
     * Método llamado cuando selecciona el MenuItem de Conexión, mostrara el
     * FrmConfiguracionConexion.
     *
     * @param evt Objeto que contiene información sobre evento.
     */
    private void miConexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConexionActionPerformed
        if (configuracion == null) {
            configuracion = new FrmConfiguracionConexion();
        }
        configuracion.setVisible(true);
        configuracion.actualizarRespaldoConexion();
    }//GEN-LAST:event_miConexionActionPerformed

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
