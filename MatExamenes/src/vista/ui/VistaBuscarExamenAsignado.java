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

import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVContestarExamen;
import vista.controlador.RespaldoJSON;
import vista.interfaz.InterfaceVista;

/**
 * Interfaz gráfica de buscar examen.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class VistaBuscarExamenAsignado extends javax.swing.JPanel implements
        InterfaceVista {

    /**
     * Tiempo mínimo para que un alumno reinicie su examen.
     */
    private static final int HOLGURA = 1000 * 40;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;
    /**
     *
     */
    private CVContestarExamen control;
    /**
     * Modelo para cambiar los datos de la lista.
     */
    private final DefaultListModel DML;
    /**
     * Utilizado para cargar, y obtener respaldo anteriores.
     */
    private RespaldoJSON respaldo;

    /**
     * Crea una nueva VistaBuscarExamenAsignado e inicializa sus atributos.
     */
    public VistaBuscarExamenAsignado() {
        initComponents();
        lstExamenesEncontrados.setModel(new DefaultListModel());
        lstExamenesEncontrados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        DML = (DefaultListModel) lstExamenesEncontrados.getModel();
        respaldo = new RespaldoJSON();
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
     * @param cvContestarExamen El objeto encargado de realizar las
     * interacciones con la base de datos.
     */
    public void setControlador(CVContestarExamen cvContestarExamen) {
        this.control = cvContestarExamen;
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstExamenesEncontrados = new javax.swing.JList();
        btnIniciar = new javax.swing.JButton();

        setName(""); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Examen");

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(73, 30));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(lstExamenesEncontrados);

        btnIniciar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setPreferredSize(new java.awt.Dimension(65, 30));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(358, 358, 358))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(253, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(43, 43, 43)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón buscar.<br>
     * Buscara los exámenes asignados al alumno y los mostrara en la lista.
     * Previamente verificara si existe un respaldo, de ser asi, se verificaran
     * una serie de condiciones detalladas a continuación.
     *
     * @param evt Objeto que contiene información sobre el evento.
     */
    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        limpiar();
        if (respaldo.existeRespaldo()) {
            ArrayList alumno = respaldo.getRespaldo();
            ExamenAsignadoPK eapk = (ExamenAsignadoPK) alumno.get(RespaldoJSON.I_EXAMEN_ASIGNADO_PK);
            control.setExamenAsignado(eapk);
            ExamenAsignadoDTO examen = control.getExamenAsignado();
            long inicio = examen.getFechaAsignacion().getTime();
            long fin = inicio + (examen.getTiempo() * 60 * 1000);
            boolean usuarioRespaldo = padre.obtenerUsuarioActual().getId() == eapk.getIdAlumno();
            boolean contestado = respaldo.estaContestado();

            if (contestado && usuarioRespaldo) {
                //Respaldo contestado y es el mismo usuario que inicio el creador del respaldo.
                JOptionPane.showMessageDialog(this, "Tienes un examen pendiente "
                        + "por enviar." + "\n" + "Re enviando",
                        "Respaldo examen", JOptionPane.INFORMATION_MESSAGE);
                calificarExamen(examen, alumno);
                respaldo.eliminarRespaldo();
            } else if (usuarioRespaldo && System.currentTimeMillis() < (fin + HOLGURA) && !contestado) {
                //El usuario que inicio es el mismo que tiene el respaldo,
                //el tiempo actual es menor que el tiempo de terminación
                //más la holgura y el examen no fue contestado.
                int ok = JOptionPane.showConfirmDialog(this, "¿Desea continuar "
                        + "con el examen pendiente?" + "\n" + "Se perderan sus respuestas anteriores si selecciona no.",
                        "Respaldo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (ok == JOptionPane.YES_OPTION) {
                    padre.mostrarVistaConEntidad(respaldo, Vista.ContestarExamen);
                    limpiar();
                }
            } else if (usuarioRespaldo && (System.currentTimeMillis() + HOLGURA) > fin && !contestado) {
                //El usuario que inicio es el mismo que tiene el respaldo,
                //el tiempo actual más la holgura supera el tiempo de terminación y
                //el examen no fue contestado.
                calificarExamen(examen, alumno);
                respaldo.eliminarRespaldo();
            } else if (!usuarioRespaldo) {
                //El usuario que inicio en el sistema no es igual al que genero el respaldo.
                control.calificarExamen(examen.getReactivos().size(),
                        (List<String>) alumno.get(RespaldoJSON.I_RESPUESTAS), examen);
                control.actualizarExamen(examen);
                respaldo.eliminarRespaldo();
            }
        }
        //no hay respaldo o no desea continuar con su examen anterior
        List<ExamenAsignadoDTO> ea;
        ea = control.obtenerExamenesAsignados(padre.obtenerUsuarioActual());
        if (ea.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Examenes no asignados");
        } else {
            for (ExamenAsignadoDTO examen : ea) {
                DML.addElement(examen.getExamen().getTitulo());
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    /**
     * Califica el examen previamente resuelto por el alumno, con una
     * calificación de 0 a 10.
     *
     * @param examen El examen que se calificara.
     * @param alumno El respaldo del alumno obtenido de RespaldoJSON.
     * @see vista.controlador.RespaldoJSON
     */
    private void calificarExamen(ExamenAsignadoDTO examen, ArrayList alumno) {
        double calificacion = control.calificarExamen(examen.getReactivos().size(),
                (List<String>) alumno.get(RespaldoJSON.I_RESPUESTAS), control.getExamenAsignado());
        String cali = String.format("%.2f", calificacion);
        if (calificacion == 100) {
            JOptionPane.showMessageDialog(this, "Su calificación es de: " + cali,
                    "Calificación", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/recursos/like.png")));
        } else {
            JOptionPane.showMessageDialog(this, "Su calificación es de: "
                    + cali, "Calificación", JOptionPane.INFORMATION_MESSAGE);
        }

        if (control.actualizarExamen(control.getExamenAsignado())) {
            respaldo.eliminarRespaldo();
        } else {
            JOptionPane.showMessageDialog(this, "Exmen no actualizado");
        }
    }

    /**
     * Inicia el examen seleccionado de la lista.
     *
     * @param evt Objeto que contiene información sobre el evento.
     */
    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        int index = lstExamenesEncontrados.getSelectedIndex();
        if (index != -1) {
            ExamenAsignadoPK expk = control.obtenerExamenesAsignados(padre.obtenerUsuarioActual()).get(index).getId();
            control.setExamenAsignado(expk);
            padre.mostrarVistaConEntidad(expk, Vista.ContestarExamen);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un examen");
        }
        limpiar();
    }//GEN-LAST:event_btnIniciarActionPerformed

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //
    }

    @Override
    public void mostrarVista(Vista vista) {
        //
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        //
    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        return padre.obtenerUsuarioActual();
    }

    @Override
    public void limpiar() {
        DML.setSize(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón usado para realizar la busqueda de los Exámenes asignados.
    */
    private javax.swing.JButton btnBuscar;
    /**
    * Botón que iniciara el examen.
    */
    private javax.swing.JButton btnIniciar;
    /**
    * ScrollPane de la lista de exámenes.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * Label para el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Lista que mostrara los Exámenes Asignados al alumno.
    */
    private javax.swing.JList lstExamenesEncontrados;
    // End of variables declaration//GEN-END:variables

}
