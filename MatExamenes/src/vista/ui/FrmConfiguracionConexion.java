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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import remoteAccess.Enlace;
import vista.controlador.RespaldoJSON;
import vista.controlador.Validador;

/**
 * JFrame usada para combiar la configuración de la conexión con el servidor.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class FrmConfiguracionConexion extends javax.swing.JFrame implements FocusListener {

    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;
    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;
    /**
     * Utilizado para obtener la ip y puerto almacenada en el archivo de
     * configuración.
     */
    private RespaldoJSON respaldo;
    /**
     * Guarda la ip y el puerto obtenidos del respaldo.
     */
    private Map<String, String> mapRespaldo;


    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón de cancelar.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón de guardar.
    */
    private javax.swing.JButton btnGuardar;
    /**
    * Label para mostrar el estado del campo de texto de la ip
    */
    private javax.swing.JLabel lblEstadoIp;
    /**
    * Label para mostrar el estado del campo de texto puerto
    */
    private javax.swing.JLabel lblEstadoPuerto;
    /**
    * Label del ip.
    */
    private javax.swing.JLabel lblIp;
    /**
    * Label del puerto.
    */
    private javax.swing.JLabel lblPuerto;
    /**
    * Label del título.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Campo de text para ingresar la ip.
    */
    private javax.swing.JTextField txtfIp;
    /**
    * Campo de texto para ingresar el puerto.
    */
    private javax.swing.JTextField txtfPuerto;
    // End of variables declaration//GEN-END:variables
    /**
     * Creates new form FrmConfiguracionConexion
     */
    public FrmConfiguracionConexion() {
        initComponents();
        agregarIconos();
        respaldo = new RespaldoJSON();
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        txtfIp.setName("lblEstadoIp");
        txtfPuerto.setName("lblEstadoPuerto");
        txtfIp.addFocusListener(this);
        txtfPuerto.addFocusListener(this);
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
     * Modifica los campos de texto con los almacenados en el respaldo de
     * configuración de conexión.
     */
    public void actualizarRespaldoConexion() {
        try {
            mapRespaldo = respaldo.obtenerIpPuerto();
            txtfIp.setText(mapRespaldo.get(RespaldoJSON.IP));
            txtfPuerto.setText(mapRespaldo.get(RespaldoJSON.PUERTO));
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (NullPointerException ex) {
            txtfIp.setText(RespaldoJSON.LOCAL_HOST);
            txtfPuerto.setText(RespaldoJSON.PUERTO_DEFECTO);
        }

    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        lblIp = new javax.swing.JLabel();
        lblPuerto = new javax.swing.JLabel();
        txtfIp = new javax.swing.JTextField();
        txtfPuerto = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        lblEstadoIp = new javax.swing.JLabel();
        lblEstadoPuerto = new javax.swing.JLabel();

        setTitle("Configuración de conexión");
        setResizable(false);

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Configuración conexión");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarConfiguracion(evt);
            }
        });

        lblIp.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblIp.setText("Ip:");

        lblPuerto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblPuerto.setText("Puerto:");

        txtfIp.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfIp.setPreferredSize(new java.awt.Dimension(100, 30));
        txtfIp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfIpKeyTyped(evt);
            }
        });

        txtfPuerto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfPuerto.setPreferredSize(new java.awt.Dimension(100, 30));
        txtfPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfPuertoKeyTyped(evt);
            }
        });

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarConfiguracion(evt);
            }
        });

        lblEstadoIp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoIp.setToolTipText("Solo números.");

        lblEstadoPuerto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        lblEstadoPuerto.setToolTipText("Siga la estructura ipv4 xxx.xxx.xxx.xxx");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(23, 23, 23)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(lblIp)
                            .addGap(52, 52, 52)
                            .addComponent(txtfIp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblEstadoIp))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblPuerto)
                            .addGap(52, 52, 52)
                            .addComponent(txtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblEstadoPuerto))))
                .addContainerGap(136, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblIp))
                    .addComponent(txtfIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoIp))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lblPuerto))
                    .addComponent(txtfPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoPuerto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar))
                .addGap(26, 26, 26))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Oculta este JFrame.
     *
     * @param evt Objeto que contiene información sobre evento.
     */
    private void cancelarConfiguracion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarConfiguracion
        this.setVisible(false);
    }//GEN-LAST:event_cancelarConfiguracion

    /**
     * Guarda en el archivo de configuración de conexión los nuevos IP y Puerto.
     *
     * @param evt Objeto que contiene información sobre evento.
     */
    private void guardarConfiguracion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarConfiguracion
        String puerto = txtfPuerto.getText();
        String ip = txtfIp.getText();
        if (Validador.esIp(ip) && Validador.esNumero(puerto)) {
            try {
                respaldo.actualizarIpPuerto(ip, puerto);
                Enlace.setPuerto(Integer.parseInt(puerto));
                Enlace.setIp(ip);
                this.setVisible(false);
            } catch (IOException ex) {
            }

        } else {
            JOptionPane.showMessageDialog(this, "Verifique la correcta estructura "
                    + "de los datos.", "Modificación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_guardarConfiguracion

    /**
     * Método llamado cuando se teclea en el campo de texto de la ip.<br>
     * Si lo tecleado no fue un número o un punto(.), no se escribira.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void txtfIpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfIpKeyTyped
        char tecla = evt.getKeyChar();
        if (tecla != '.') {
            if (!Validador.esNumero(String.valueOf(tecla))) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_txtfIpKeyTyped

    /**
     * Método llamado cuando se teclea en el campo de texto del puerto.<br>
     * Si lo tecleado no fue un número, no se escribira.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void txtfPuertoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfPuertoKeyTyped
        char tecla = evt.getKeyChar();
        if (!Validador.esNumero(String.valueOf(tecla))) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfPuertoKeyTyped

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField ob = (JTextField) e.getSource();

        boolean ok;
        if (ob == txtfIp) {
            ok = Validador.esIp(txtfIp.getText());
        } else {
            ok = Validador.esNumero(txtfPuerto.getText());
        }
        mostrarLabelEstado(ob, ok);
    }
}
