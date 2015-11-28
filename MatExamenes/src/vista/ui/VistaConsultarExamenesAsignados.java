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
public class VistaConsultarExamenesAsignados extends javax.swing.JPanel implements
        InterfaceVista {

    /**
     * Ruta de la imagen a mostrar en una calificación perfecta.
     */
    private static final String IMAGEN_LIKE = "/recursos/like.png";
    /**
     * Tiempo mínimo para que un alumno reinicie su examen.
     */
    private static final int HOLGURA = 1000 * 40;
    /**
     * Transforma los minutos a milisegundos.
     */
    private static final int MINUTOS_A_MILIS = 1000 * 60;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;
    /**
     * Controlador de la vista del caso de uso Contestar Exámenes, funciona para
     * manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVContestarExamen cvContestarExamen;
    /**
     * Modelo para cambiar los datos de la lista.
     */
    private final DefaultListModel DML;
    /**
     * Utilizado para cargar, y obtener respaldo anteriores.
     */
    private RespaldoJSON respaldo;
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
    * Label para el nombre del examen.
    */
    private javax.swing.JLabel lblNombreExamen;
    /**
    * Label para el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Lista que mostrara los Exámenes Asignados al alumno.
    */
    private javax.swing.JList lstExamenesEncontrados;
    // End of variables declaration//GEN-END:variables
    /**
     * Crea una nueva VistaBuscarExamenAsignado e inicializa sus atributos.
     */
    public VistaConsultarExamenesAsignados() {
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
        this.cvContestarExamen = cvContestarExamen;
    }

    /**
     * Muestra los exámenes que el alumno tiene asignados en la lista.
     *
     * @param examenesAsignados Lista de ExamenAsignadoDTO previamente obtenida
     * de la base de datos, de la cual se obtendra su título para mostrar en la
     * lista.
     */
    private void mostrarExamenes(List<ExamenAsignadoDTO> examenesAsignados) {
        for (ExamenAsignadoDTO examen : examenesAsignados) {
            DML.addElement(examen.getExamen().getNombre());
        }
    }

    /**
     * Califica el examen previamente resuelto por el alumno, con una
     * calificación de 0 a 10 y la muestra.
     *
     * @param examen El examen que se calificara.
     * @param alumno El respaldo del alumno obtenido de RespaldoJSON.
     * @see vista.controlador.RespaldoJSON
     */
    private void mostrarCalificacion(ExamenAsignadoDTO examen, ArrayList alumno) {
        double evaluacion[] = cvContestarExamen.calificarExamen(examen.getReactivos().size(),
                (List<String>) alumno.get(RespaldoJSON.I_RESPUESTAS), cvContestarExamen.getExamenAsignado());

        String mensaje = "Reactivos correctos: " + (int) evaluacion[CVContestarExamen.BUENAS]
                + "/" + (int) evaluacion[CVContestarExamen.N_REACTIVOS] + "\n"
                + "Su calificación es de: " + evaluacion[CVContestarExamen.CALIFICACION];

        if (evaluacion[CVContestarExamen.CALIFICACION] == 10) {
            JOptionPane.showMessageDialog(this, mensaje,
                    "Calificación", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource(IMAGEN_LIKE)));
        } else {
            JOptionPane.showMessageDialog(this, mensaje,
                    "Calificación", JOptionPane.INFORMATION_MESSAGE);
        }

        if (cvContestarExamen.actualizarExamen(cvContestarExamen.getExamenAsignado())) {
            respaldo.eliminarRespaldo();
        } else {
            JOptionPane.showMessageDialog(this, "Exmen no actualizado");
        }
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
        lblNombreExamen = new javax.swing.JLabel();

        setName(""); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Buscar Examen");

        btnBuscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/buscar24.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(73, 30));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarExamenes(evt);
            }
        });

        lstExamenesEncontrados.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(lstExamenesEncontrados);

        btnIniciar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar24.png"))); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.setPreferredSize(new java.awt.Dimension(65, 30));
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarExamen(evt);
            }
        });

        lblNombreExamen.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreExamen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreExamen.setText("Nombre Examen");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(255, 255, 255))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(341, 341, 341)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreExamen)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(lblTitulo)
                .addGap(42, 42, 42)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblNombreExamen)
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private void buscarExamenes(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarExamenes
        limpiar();
        boolean buscarExamen = false;
        if (respaldo.existeRespaldo()) {
            try {
                ArrayList alumno = respaldo.getRespaldo();
                ExamenAsignadoPK eapk = (ExamenAsignadoPK) alumno.get(RespaldoJSON.I_EXAMEN_ASIGNADO_PK);
                cvContestarExamen.setExamenAsignado(eapk);
                ExamenAsignadoDTO examen = cvContestarExamen.getExamenAsignado();
                System.out.println(examen.getCalificacion());
                if (examen.getCalificacion() != CALIFICACION_PREDETERMINADA) {
                    buscarExamen = true;
                } else {
                    long inicio = examen.getFechaAsignacion().getTime();
                    long fin = inicio + (examen.getTiempo() * MINUTOS_A_MILIS);
                    boolean usuarioRespaldo = padre.obtenerUsuarioActual().getId() == eapk.getIdAlumno();
                    boolean contestado = respaldo.estaContestado();

                    if (contestado && usuarioRespaldo) {
                        //Respaldo contestado y es el mismo usuario que inicio el creador del respaldo.
                        JOptionPane.showMessageDialog(this, "Tienes un examen pendiente "
                                + "por enviar." + "\n" + "Re enviando",
                                "Respaldo examen", JOptionPane.INFORMATION_MESSAGE);
                        mostrarCalificacion(examen, alumno);
                        buscarExamen = true;
                    } else if (usuarioRespaldo && cvContestarExamen.obtenerTiempoServidor() < (fin + HOLGURA) && !contestado) {
                        //El usuario que inicio es el mismo que tiene el respaldo,
                        //el tiempo actual es menor que el tiempo de terminación
                        //más la holgura y el examen no fue contestado.
                        int ok = JOptionPane.showConfirmDialog(this, "¿Desea continuar "
                                + "con el examen pendiente?" + "\n" + "Se perderán sus respuestas anteriores si selecciona no.",
                                "Respaldo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if (ok == JOptionPane.YES_OPTION) {
                            padre.mostrarVistaConEntidad(respaldo, Vista.ContestarExamen);
                            limpiar();
                        } else {
                            buscarExamen = true;
                        }
                    } else if (usuarioRespaldo && (cvContestarExamen.obtenerTiempoServidor() + HOLGURA) > fin && !contestado) {
                        //El usuario que inicio es el mismo que tiene el respaldo,
                        //el tiempo actual más la holgura supera el tiempo de terminación y
                        //el examen no fue contestado.
                        mostrarCalificacion(examen, alumno);
                        buscarExamen = true;
                    } else if (!usuarioRespaldo) {
                        //El usuario que inicio en el sistema no es igual al que genero el respaldo.
                        cvContestarExamen.calificarExamen(examen.getReactivos().size(),
                                (List<String>) alumno.get(RespaldoJSON.I_RESPUESTAS), examen);
                        cvContestarExamen.actualizarExamen(examen);
                        respaldo.eliminarRespaldo();
                        buscarExamen = true;
                    }
                }
            } catch (NullPointerException e) {
                buscarExamen = true;
            }
        }
        if (buscarExamen) {
            //no hay respaldo o no desea continuar con su examen anterior
            List<ExamenAsignadoDTO> ea;
            ea = cvContestarExamen.obtenerExamenesAsignados(padre.obtenerUsuarioActual());
            if (ea.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay exámenes asignados");
            } else {
                mostrarExamenes(ea);
            }
        }
    }//GEN-LAST:event_buscarExamenes
    private static final int CALIFICACION_PREDETERMINADA = -1;

    /**
     * Inicia el examen seleccionado de la lista.
     *
     * @param evt Objeto que contiene información sobre el evento.
     */
    private void iniciarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarExamen
        int index = lstExamenesEncontrados.getSelectedIndex();
        if (index != CALIFICACION_PREDETERMINADA) {
            ExamenAsignadoPK expk = cvContestarExamen.getExamenesAsignados().get(index).getId();
            cvContestarExamen.setExamenAsignado(expk);
            padre.mostrarVistaConEntidad(expk, Vista.ContestarExamen);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un examen");
        }
        limpiar();
    }//GEN-LAST:event_iniciarExamen

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

}
