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

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ReactivoAsignadoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVContestarExamen;
import vista.controlador.Cronometro;
import vista.interfaz.InterfaceVista;
import vista.controlador.RespaldoJSON;
import vista.interfaz.InterfaceContestarExamen;

/**
 * Interfaz gráfica para contestar examen.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class VistaContestarExamen extends javax.swing.JPanel implements
        InterfaceVista, ActionListener, InterfaceContestarExamen {

    /**
     * Número de opciones.
     */
    private static final int OPCIONES = 4;
    /**
     * Número máximo de reactivos por panel.
     */
    private static final int REACTIVOS_POR_PANEL = 13;
    /**
     * Ruta de la imagen a mostrar en una calificación perfecta.
     */
    private static final String IMAGEN_LIKE = "/recursos/like.png";
    /**
     * Identificador para los reactivos no contestados.
     */
    private static final int NO_CONTESTADO = -1;
    /**
     * Posiciona al principio de la redacción en el campo de texto redacción.
     */
    private static final int INICIO_TEXT_AREA = 0;
    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;
    private CVContestarExamen cvContestarExamen;
    /**
     * Alamacenara el numero máximo de reactivos.
     */
    private int nReactivos;
    /**
     * Almacenara el númeor del reactivo actual.
     */
    private int reactivoActual;
    /**
     * Vector contenedor de todos los botones de los reactivos.
     */
    private JButton[] btnReactivos;
    /**
     * Inicializa y modifica el respaldo del examen que se esta contestando.
     */
    private RespaldoJSON respaldo;
    /**
     * Vector contenedor de los botones radios.
     */
    private JRadioButton[] rbtnOpciones;
    /**
     * Lista de listas, cada índice de la primera lista es el número de
     * reactivo, y cada lista en ese índice almacena las opciones de respuestas
     * en un orden aleatorio calculado cada que el examen es iniciado.
     */
    private List<List<String>> opcionesAleatorias;
    /**
     * Lista que almacena las respuestas del alumno.
     */
    private List<String> respuestasAlumno;
    /**
     * Bandera que almacena los reactivos que fueron vistos previamente.
     */
    private boolean[] reactivosVistos;
    /**
     * Bandera utilizada para saber cual de las 4 opciones selecciono el alumno.
     */
    private int[] reactivosContestados;
    /**
     * Vector de paneles que almacenaran los botones de los reactivos.
     */
    private JPanel[] pnlsReactivos;
    /**
     * El cronómetro encargado del llevar el tiempo.
     */
    private Cronometro cronometro;

    /**
     * Crea una nueva VistaContestarExamen e inicializa sus atributos.
     */
    public VistaContestarExamen() {
        initComponents();
        inicializarComponentes();
    }

    /**
     * Inicializa algunos componentes gráficos.
     */
    private void inicializarComponentes() {
        rbtnOpciones = new JRadioButton[4];
        rbtnOpciones[0] = rbtnOpcion1;
        rbtnOpciones[1] = rbtnOpcion2;
        rbtnOpciones[2] = rbtnOpcion3;
        rbtnOpciones[3] = rbtnOpcion4;
        rbtnOpcion1.addActionListener(this);
        rbtnOpcion2.addActionListener(this);
        rbtnOpcion3.addActionListener(this);
        rbtnOpcion4.addActionListener(this);
    }

    /**
     * Inicializa el resto de objetos a utilizar.
     */
    private void inicializarObjetos() {
        respaldo = new RespaldoJSON();
        reactivoActual = 0;
        opcionesAleatorias = new ArrayList();
        respuestasAlumno = new ArrayList();
    }

    /**
     * Inicializa las banderas.
     */
    private void iniciarBanderas() {
        reactivosVistos = new boolean[nReactivos];
        reactivosContestados = new int[nReactivos];
        for (int i = 0; i < nReactivos; i++) {
            reactivosVistos[i] = false;
            reactivosContestados[i] = NO_CONTESTADO;
            respuestasAlumno.add(".");
        }
        reactivosVistos[0] = true;
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
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbtnGrupoRespuestas = new javax.swing.ButtonGroup();
        lblTiempo = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaRedaccion = new javax.swing.JTextArea();
        rbtnOpcion1 = new javax.swing.JRadioButton();
        rbtnOpcion2 = new javax.swing.JRadioButton();
        rbtnOpcion3 = new javax.swing.JRadioButton();
        btnSiguiente = new javax.swing.JButton();
        rbtnOpcion4 = new javax.swing.JRadioButton();
        btnTerminarExamen = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnlReactivos = new javax.swing.JPanel();
        lblInstrucciones = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTiempo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTiempo.setText("Tiempo restante:");
        add(lblTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(603, 23, -1, -1));

        txtaRedaccion.setEditable(false);
        txtaRedaccion.setBackground(new java.awt.Color(240, 240, 240));
        txtaRedaccion.setColumns(20);
        txtaRedaccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaRedaccion.setRows(5);
        txtaRedaccion.setToolTipText("");
        jScrollPane2.setViewportView(txtaRedaccion);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 249, 740, 125));

        rbtnGrupoRespuestas.add(rbtnOpcion1);
        rbtnOpcion1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnOpcion1.setText("opcion1");
        add(rbtnOpcion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 376, -1, -1));

        rbtnGrupoRespuestas.add(rbtnOpcion2);
        rbtnOpcion2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnOpcion2.setText("opcion2");
        add(rbtnOpcion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 417, -1, -1));

        rbtnGrupoRespuestas.add(rbtnOpcion3);
        rbtnOpcion3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnOpcion3.setText("opcion3");
        add(rbtnOpcion3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 458, -1, -1));

        btnSiguiente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSiguiente.setText("Siguiente");
        btnSiguiente.setPreferredSize(new java.awt.Dimension(77, 30));
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                siguienteReactivo(evt);
            }
        });
        add(btnSiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(461, 534, 101, -1));

        rbtnGrupoRespuestas.add(rbtnOpcion4);
        rbtnOpcion4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbtnOpcion4.setText("opcion4");
        add(rbtnOpcion4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 493, -1, -1));

        btnTerminarExamen.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnTerminarExamen.setText("Terminar Examen");
        btnTerminarExamen.setToolTipText("");
        btnTerminarExamen.setPreferredSize(new java.awt.Dimension(77, 30));
        btnTerminarExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminarExamen(evt);
            }
        });
        add(btnTerminarExamen, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 534, 138, -1));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Reactivos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jScrollPane1.setMaximumSize(null);
        jScrollPane1.setMinimumSize(null);

        pnlReactivos.setMaximumSize(null);
        pnlReactivos.setMinimumSize(null);
        jScrollPane1.setViewportView(pnlReactivos);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 58, 740, 130));

        lblInstrucciones.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblInstrucciones.setText("jLabel1");
        add(lblInstrucciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 214, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método llamado cuando se acciona el botón siguiente.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void siguienteReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_siguienteReactivo
        siguienteReactivo();
    }//GEN-LAST:event_siguienteReactivo

    /**
     * Método llamado cuando se acciona el botón terminar examen.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void terminarExamen(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminarExamen
        int ok;
        if (examenTerminado()) {
            ok = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea terminar el examen?",
                    "Examen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        } else {
            ok = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea terminar el examen?"
                    + "\n" + "Aún tiene reactivos pendientes", "Examen",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        }

        if (ok == JOptionPane.YES_OPTION) {
            mostrarCalificacion();
        }
    }//GEN-LAST:event_terminarExamen

    /**
     * Verifica si hay algun reactivo no contestado.
     *
     * @return Verdadero si no hay reactivos pendientes.<br>
     * Falso de otra forma.
     */
    private boolean examenTerminado() {
        boolean ok = true;
        for (int i : reactivosContestados) {
            if (i == NO_CONTESTADO) {
                ok = false;
                break;
            }
        }
        return ok;
    }

    /**
     * Cuenta los reactivos que el examen tiene asignados.
     *
     * @param examen El examen al cual contar los reactivos.
     */
    private void contarReactivos(ExamenAsignadoDTO examen) {
        nReactivos = examen.getReactivos().size();
    }

    /**
     * Muestra la información del reactivo actual en la interfaz gráfica.
     */
    private void mostrarDatosReactivo() {
        for (int i = 0; i < OPCIONES; i++) {
            rbtnOpciones[i].setText(opcionesAleatorias.get(reactivoActual).get(i));
        }
        txtaRedaccion.setText(opcionesAleatorias.get(reactivoActual).get(OPCIONES));
        txtaRedaccion.setCaretPosition(INICIO_TEXT_AREA);
        rbtnGrupoRespuestas.clearSelection();
        seleccionarRespuestaAlumno();
    }

    /**
     * Selecciona el botón radio de la respuesta contestada por el alumno.
     */
    private void seleccionarRespuestaAlumno() {
        if (reactivosContestados[reactivoActual] != NO_CONTESTADO) {
            rbtnGrupoRespuestas.setSelected(rbtnOpciones[reactivosContestados[reactivoActual]].getModel(), true);
        }
    }

    /**
     * Agrega las tres opciones y la repuesta correcta auna lista, y despues
     * esta lista es barajeada.
     */
    private void ordenarOpcionesReactivos() {
        List<ReactivoAsignadoDTO> reactivos = cvContestarExamen.getExamenAsignado().getReactivos();

        for (int i = 0; i < nReactivos; i++) {
            List<String> opciones = new ArrayList();
            for (int j = 0; j < OPCIONES - 1; j++) {
                // -1 ya que una se agrega abajo la respuesta correcta
                opciones.add(reactivos.get(i).getOpcionesReactivo().get(j));
            }
            opciones.add(reactivos.get(i).getRespuestaReactivo());
            Collections.shuffle(opciones);
            opciones.add(reactivos.get(i).getRedaccionReactivo());
            opcionesAleatorias.add(i, opciones);
        }
    }

    /**
     * Califica el examen, muestra en pantalla la calificación y actualiza el
     * examen en la base de datos.
     */
    private void mostrarCalificacion() {
        double[] evaluacion = cvContestarExamen.calificarExamen(nReactivos,
                respuestasAlumno, cvContestarExamen.getExamenAsignado());
        String cali = String.format("%.2f", evaluacion[CVContestarExamen.CALIFICACION]);

        String mensaje = "Reactivos correctos: " + ((int) evaluacion[CVContestarExamen.BUENAS])
                + "/" + ((int) evaluacion[CVContestarExamen.N_REACTIVOS]) + "\n"
                + "Su calificación es de: " + cali;

        if (evaluacion[CVContestarExamen.CALIFICACION] == 10) {
            JOptionPane.showMessageDialog(this, mensaje,
                    "Calificación", JOptionPane.INFORMATION_MESSAGE,
                    new ImageIcon(getClass().getResource(IMAGEN_LIKE)));
        } else {
            JOptionPane.showMessageDialog(this, mensaje,
                    "Calificación", JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            respaldo.setContestado();
        } catch (IOException e) {
        }

        cronometro.detener();

        if (cvContestarExamen.actualizarExamen(cvContestarExamen.getExamenAsignado())) {
            respaldo.eliminarRespaldo();
        } else {
            JOptionPane.showMessageDialog(this, "Exmen no actualizado");
        }

        padre.mostrarVista(Vista.HOME);
    }

    /**
     * Verifica los reactivos, si fueron visitados previamente y no fueron
     * contestados, su color sera cambiado a rojo, si fueron contestados, su
     * color sera cambiado a azul, deshabilitando el boton del reactivo actual.
     */
    private void verificarReactivos() {
        for (int i = 0; i < nReactivos; i++) {
            if (reactivosVistos[i] && reactivosContestados[i] == NO_CONTESTADO) {
                btnReactivos[i].setBackground(Color.red);
                btnReactivos[i].setForeground(Color.white);
            } else if (reactivosContestados[i] != NO_CONTESTADO) {
                btnReactivos[i].setBackground(Color.blue);
                btnReactivos[i].setForeground(Color.white);
            }
            btnReactivos[i].setEnabled(true);
        }
        btnReactivos[reactivoActual].setEnabled(false);
    }

    /**
     * Cambia el reactivo actual por el reactivo siguiente.
     */
    private void siguienteReactivo() {
        reactivosVistos[reactivoActual] = true;
        if (!(reactivoActual == nReactivos - 1)) {
            reactivoActual++;
            mostrarDatosReactivo();
        }
        if (reactivoActual == nReactivos - 1) {
            //Se encuentra en el penultimo reactivo.
            btnSiguiente.setVisible(false);
        }
        verificarReactivos();
    }

    /**
     * Método llamado cuando se reinicia un examen.<br>
     * Verifica
     *
     * @param respuestas Lista de respuestas obtenida del respaldo.
     * @see vista.controlador.RespaldoJSON
     */
    private void respaldoRespuestas(List<String> respuestas) {
        for (int i = 0; i < nReactivos; i++) {
            for (int j = 0; j < OPCIONES; j++) {
                if (opcionesAleatorias.get(i).get(j).compareTo(respuestas.get(i)) == 0) {
                    reactivosContestados[i] = j;
                    guardarRespuestaAlumno(i, respuestas.get(i));
                }
            }
        }
    }

    /**
     * Calcula el tiempo restante en base a la fehca de asignación y la duración
     * del examen.
     *
     * @param fechaAsignacion Fecha en la que el examen fue asignado.
     * @param duracion Duracion del examen en minutos.
     */
    private void tiempoRestanteExamen(Date fechaAsignacion, int duracion) {
        long asignadoMilis = fechaAsignacion.getTime();
        long actualMilis = System.currentTimeMillis();
        long terminacionMilis = asignadoMilis + (duracion * 60 * 1000);
        long diferenciaMilis = terminacionMilis - actualMilis;
        int seg = (int) diferenciaMilis / 1000;
        int min = seg / 60;
        int seg2 = (seg % 60);
        iniciarCronometro(min, seg2);
    }

    /**
     * Agrega los botones de los reactivos a los paneles.
     */
    private void agregarBotonesReactivos() {
        calcularFilasBotones();
        btnReactivos = new JButton[nReactivos];
        for (int i = 0, j = 0; i < nReactivos; i++) {
            btnReactivos[i] = new JButton(String.valueOf(i + 1));
            btnReactivos[i].addActionListener(this);
            pnlsReactivos[j].add(btnReactivos[i]);
            if (i % (REACTIVOS_POR_PANEL) == 0) {
                if (i != 0) {
                    j++;
                }
            }
        }
        btnReactivos[reactivoActual].setEnabled(false);
    }

    /**
     * Calcula cuatos paneles seran necesarios para los botones de los
     * reactivos.
     */
    private void calcularFilasBotones() {
        int y = nReactivos / REACTIVOS_POR_PANEL;
        int k = nReactivos % REACTIVOS_POR_PANEL;
        if (k != 0) {
            y++;
        }
        pnlReactivos.setLayout(new GridLayout(0, 1));
        pnlsReactivos = new JPanel[y];
        for (int i = 0; i < y; i++) {
            pnlsReactivos[i] = new JPanel();
            pnlsReactivos[i].setLayout(new FlowLayout());
            pnlReactivos.add(pnlsReactivos[i]);
        }
    }

    /**
     * Obtiene los botones en el grupo de botones y verifica si estan
     * seleccionados.
     *
     * @param grupoBotones El grupo de botones del cual se quiere obtener el
     * texto del seleccionado.
     * @return El texto del boton seleccionado.<br>
     * Si no hay un boton seleccionado retorna null.
     */
    private String obtenerTextoBotonSeleccionado(ButtonGroup grupoBotones) {
        int i = 0;
        for (Enumeration<AbstractButton> buttons = grupoBotones.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                reactivosContestados[reactivoActual] = i;
                return button.getText();
            }
            i++;
        }
        return null;
    }

    /**
     * Inicia el Thread del cronómetro.
     *
     * @param minutos Los minutos del examen.
     * @param segundos Los segundos del examen.
     */
    private void iniciarCronometro(int minutos, int segundos) {
        cronometro = new Cronometro(this, minutos, segundos);
        cronometro.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            int num = Integer.parseInt(((JButton) e.getSource()).getText()) - 1;
            // -1 para que concuerden los índices del numero de reactivo
            //con el número del botón.
            reactivosVistos[reactivoActual] = true;
            reactivoActual = num;
            mostrarDatosReactivo();
            if (reactivoActual != nReactivos - 1) {
                btnSiguiente.setVisible(true);
            } else {
                btnSiguiente.setVisible(false);
            }
        } else {
            //botones radio
            String respuesta = obtenerTextoBotonSeleccionado(rbtnGrupoRespuestas);
            guardarRespuestaAlumno(reactivoActual, respuesta);
            try {
                respaldo.modificarRespuesta(reactivoActual, respuesta);
            } catch (IOException ex) {
            }
        }
        verificarReactivos();
    }

    /**
     * Guarda la respuesta que el alumno selecciono en la lista
     * respuestasAlumno.
     *
     * @param numeroReactivo Índice de la lista en donde se guardara la
     * respuesta.
     * @param respuesta Respuesta seleccionada por el alumno.
     */
    private void guardarRespuestaAlumno(int numeroReactivo, String respuesta) {
        respuestasAlumno.add(numeroReactivo, respuesta);
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        limpiar();
        inicializarObjetos();
        ExamenAsignadoDTO examen = cvContestarExamen.getExamenAsignado();
        lblInstrucciones.setText(examen.getExamen().getInstrucciones());
        contarReactivos(examen);
        iniciarBanderas();
        ordenarOpcionesReactivos();
        mostrarDatosReactivo();
        agregarBotonesReactivos();
        tiempoRestanteExamen(examen.getFechaAsignacion(), examen.getTiempo());
        if (entidad instanceof RespaldoJSON) {
            respaldo = (RespaldoJSON) entidad;
            ArrayList resp = respaldo.getRespaldo();
            List<String> respuestas = (List<String>) resp.get(RespaldoJSON.I_RESPUESTAS);
            respaldoRespuestas(respuestas);
            verificarReactivos();
            seleccionarRespuestaAlumno();
        } else {
            try {
                respaldo.inicializarArchivo(nReactivos, examen.getId().getIdExamen(),
                        examen.getId().getIdAlumno());
            } catch (IOException ex) {
            }
        }
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
    }

    @Override
    public void mostrarVista(Vista vista) {
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
        nReactivos = 0;
        reactivoActual = 0;
        btnReactivos = null;
        respaldo = null;
        opcionesAleatorias = null;
        respuestasAlumno = null;
        reactivosVistos = null;
        reactivosContestados = null;
        pnlsReactivos = null;
        cronometro = null;
        pnlReactivos.removeAll();
    }

    /**
     * Actualiza el texto que muestra el Label cada segundo.
     *
     * @param txtLabel El Texto que mostrara el Label.
     */
    @Override
    public void actualizarLblTiempo(String txtLabel) {
        lblTiempo.setText(txtLabel);
    }

    /**
     * Método llamado cuando se termina el tiempo del examen.<br>
     */
    @Override
    public void tiempoTerminado() {
        mostrarCalificacion();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Boton usado para cambiar de reactivo.
    */
    private javax.swing.JButton btnSiguiente;
    /**
    * Botón usado para terminar el examen.
    */
    private javax.swing.JButton btnTerminarExamen;
    /**
    * ScrollPane para el panel de reactivos.
    */
    private javax.swing.JScrollPane jScrollPane1;
    /**
    * ScrollPane para la redacción del reactivo.
    */
    private javax.swing.JScrollPane jScrollPane2;
    /**
    * Label para mostrar las instrucciones del examen.
    */
    private javax.swing.JLabel lblInstrucciones;
    /**
    * Label para mostrar el tiempo restante del examen.
    */
    private javax.swing.JLabel lblTiempo;
    /**
    * Panel para mostrar los reactivos del examen.
    */
    private javax.swing.JPanel pnlReactivos;
    /**
    * Agrupa los Botónes de radio.
    */
    private javax.swing.ButtonGroup rbtnGrupoRespuestas;
    /**
    * Botón de la opción uno.
    */
    private javax.swing.JRadioButton rbtnOpcion1;
    /**
    * Botón de la opción dos.
    */
    private javax.swing.JRadioButton rbtnOpcion2;
    /**
    * Botón de la opción tres.
    */
    private javax.swing.JRadioButton rbtnOpcion3;
    /**
    * Botón de la opción cuatro.
    */
    private javax.swing.JRadioButton rbtnOpcion4;
    /**
    * Campo de texto para mostrar la redacción del reactivo.
    */
    private javax.swing.JTextArea txtaRedaccion;
    // End of variables declaration//GEN-END:variables
}
