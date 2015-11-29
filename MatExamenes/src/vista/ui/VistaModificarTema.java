/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import modelo.dto.CursoDTO;
import modelo.dto.CursoTemaDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
public class VistaModificarTema extends javax.swing.JPanel implements
        InterfaceVista, FocusListener, KeyListener {

    /**
     * Controlador de la vista del caso de uso mantener temas, maneja la
     * información obtenida en la vista para comunicarse con las capas
     * inferiores.
     */
    private CVMantenerTemas controlVista;

    /**
     * Interface para interactuar con el JFrame principal.
     */
    private InterfaceVista padre;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
    * Botón para cancelar la modificación de temas.
    */
    private javax.swing.JButton btnCancelar;
    /**
    * Botón para modificar el tema.
    */
    private javax.swing.JButton btnGuardar;
    /**
    * ComboBox para mostrar los bloques.
    */
    private javax.swing.JComboBox cbBloques;
    /**
    * ComboBox para los cursos.
    */
    private javax.swing.JComboBox cbCursos;
    /**
    * Label para los bloques.
    */
    private javax.swing.JLabel lblBloques;
    /**
    * Label para los cursos.
    */
    private javax.swing.JLabel lblCursos;
    /**
    * Label de estado para el campo de texto del nombre del tema.
    */
    private javax.swing.JLabel lblEstadoNombreTema;
    /**
    * Label para el  nombre del tema.
    */
    private javax.swing.JLabel lblNombreTema;
    /**
    * Label para mostrar el título de la interfaz gráfica.
    */
    private javax.swing.JLabel lblTitulo;
    /**
    * Campo de texto para el nombre del tema.
    */
    private javax.swing.JTextField txtfNombreTema;
    // End of variables declaration//GEN-END:variables

    /**
     * Almacena el icono del estado correcto.
     */
    private final ImageIcon ICONO_BIEN;

    /**
     * Almacena el icno del estado incorrecto.
     */
    private final ImageIcon ICONO_MAL;

    /**
     * Almacena el mensaje de datos incorrectos.
     */
    private final String MENSAJE_DATOS_INCORRECTOS = "No se puede completar la "
            + "operación, los siguientes campos necesitan ser corregidos:\n"
            + "* Nombre del curso.";

    /**
     * Crea un objeto VistaModificarTema e inicializa sus atributos.
     */
    public VistaModificarTema() {
        initComponents();
        txtfNombreTema.addFocusListener(this);
        txtfNombreTema.addKeyListener(this);

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));

        lblEstadoNombreTema.setVisible(false);
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
     * Almacena el control de la vista.
     *
     * @param controlVista El objeto encargado de realizar las interacciones con
     * la base de datos.
     */
    public void setControlador(CVMantenerTemas controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Crea un objeto tipo TemaDTO con sus atributos obtenidos de la vista.
     * También se validan los datos.
     *
     * @return Un objeto de tipo TemaDTO si los datos cumplen con las
     * validaciones. Regresa null si los datos no cumplen con las validaciones.
     *
     */
    private TemaDTO encapsularTema() {
        TemaDTO tema = null;
        boolean ok = true;

        //Si escribió otro nombre verificar si ya existe.
        String txtNombre = txtfNombreTema.getText();
        if (!Validador.estaVacio(txtNombre)) {
            //Si el campo del nombre del tema no está vacío se crea
            //el nuevo objeto TemaDTO.
            tema = new TemaDTO();
            tema.setNombre(txtNombre);

            //Se obtiene el nombre del tema seleccionado y se le quitan las
            //letras con acentos para poder comparar posteriormente si el
            //usuario escribió un nombre de tema diferente.
            String temaActual = controlVista.getTemaSeleccionado().getNombre();
            temaActual = Validador.quitarAcentos(temaActual);
            txtNombre = Validador.quitarAcentos(txtNombre);

            //Validar si escribió el mismo nombre de antes.
            if (txtNombre.compareToIgnoreCase(temaActual) != 0) {

                //Si escribió otro nombre verificar si ya existe.
                boolean existe = controlVista.verificarExistencia(tema.getNombre());

                if (existe) {
                    ok = false;
                    mostrarLabelEstado(txtfNombreTema, false,
                            "Ya existe un tema con ese nombre.");
                } else {
                    mostrarLabelEstado(txtfNombreTema, true, "");
                }
            } else {
                mostrarLabelEstado(txtfNombreTema, true, "");
            }
        } else {
            ok = false;
            mostrarLabelEstado(txtfNombreTema, false,
                    "No ingrese datos vacíos.");

        }

        if (!ok) {
            return null;
        }

        return tema;
    }

    /**
     * Muestra los datos del objeto tema en la vista para poder ser modificado
     *
     * @param tema el objeto tema
     */
    private void mostrarDatos(TemaDTO tema) {
        cbCursos.removeAllItems();
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        if (cursos != null && !cursos.isEmpty()) {
            for (CursoDTO curso : cursos) {
                cbCursos.addItem(curso.getNombre());
            }
        }
        txtfNombreTema.setText(tema.getNombre());
        //Si el tema actual está asignado a un curso se busca el curso
        //al que pertenece.
        if (!controlVista.esTemaSinAsignar()) {
            CursoDTO objCurso = controlVista.obtenerCursoPorTema(tema);
            cbCursos.setSelectedItem(objCurso.getNombre());

            List<CursoTemaDTO> temasDeCurso = objCurso.getTemas();
            int bloqueDeTema = 0;
            for (CursoTemaDTO j : temasDeCurso) {
                if (j.getTema().getId() == tema.getId()) {
                    bloqueDeTema = j.getBloque();
                }
            }

            cbBloques.setSelectedItem(String.valueOf(bloqueDeTema));
        }
    }

    /**
     * Muestra el estado del campo de texto dependiendo si la validacion fue
     * verdadera o falsa.
     *
     * @param o El objeto campo de texto al cual se quiere cambiar el estado.
     * @param estado Si es verdadero el estado será correcto, si es falso el
     * estado será incorrecto.
     */
    private void mostrarLabelEstado(Object o, boolean estado, String causa) {
        JTextField ob = (JTextField) o;
        try {
            Field field = getClass().getDeclaredField(ob.getName());
            JLabel label = (JLabel) field.get(this);
            label.setToolTipText(causa);
            if (!label.isVisible()) {
                label.setVisible(true);
            }
            if (estado) {
                label.setIcon(ICONO_BIEN);
            } else {
                label.setIcon(ICONO_MAL);
            }
        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(VistaModificarTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombreTema = new javax.swing.JLabel();
        txtfNombreTema = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        lblEstadoNombreTema = new javax.swing.JLabel();
        lblBloques = new javax.swing.JLabel();
        cbBloques = new javax.swing.JComboBox();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Tema");

        lblNombreTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreTema.setText("Nombre del tema: ");

        txtfNombreTema.setName("lblEstadoNombreTema");
        txtfNombreTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreTema.setPreferredSize(new java.awt.Dimension(350, 30));

        btnGuardar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarTema(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaConsulta(evt);
            }
        });

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Seleccione un curso:");

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setPreferredSize(new java.awt.Dimension(200, 30));

        lblEstadoNombreTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        lblBloques.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblBloques.setText("Seleccione un bloque:");

        cbBloques.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbBloques.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        cbBloques.setPreferredSize(new java.awt.Dimension(80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(500, 500, 500)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblNombreTema)
                        .addComponent(lblCursos))
                    .addComponent(lblBloques))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtfNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblEstadoNombreTema))
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblTitulo)
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfNombreTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreTema)))
                    .addComponent(lblEstadoNombreTema))
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCursos)
                    .addComponent(cbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBloques)
                    .addComponent(cbBloques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cancela la modificación de tema y regresa a la vista de consultar cursos.
     *
     * @param evt Objeto que contiene información del evento.
     */
    private void pasarControlVistaConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaConsulta
        // Ejecutado cuando selecciona regresar, pero es lo mismo que al
        //Seleccionar modificar
        //Muestra la vista consultar temas
        padre.mostrarVista(Vista.ConsultarTemas);
        limpiar();
    }//GEN-LAST:event_pasarControlVistaConsulta

    /**
     * Para modificar un tema en la base de datos y regresar a la vista
     * consultar
     *
     * @param evt
     */
    private void modificarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarTema
        //Modificar Tema
        //Encapsular objeto
        TemaDTO tema = encapsularTema();
        if (tema == null) {
            JOptionPane.showMessageDialog(this, MENSAJE_DATOS_INCORRECTOS, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            //Persistir el objeto en la base de datos
            int indexCurso = cbCursos.getSelectedIndex();
            int bloque = Integer.parseInt(cbBloques.getSelectedItem().toString());
            boolean ok = controlVista.modificarTema(tema, indexCurso, bloque);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el tema.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                padre.mostrarVista(Vista.ConsultarTemas);
                limpiar();
            }
        }

    }//GEN-LAST:event_modificarTema

    @Override
    public void limpiar() {
        txtfNombreTema.setText("");
        cbBloques.setSelectedIndex(0);
        controlVista.liberarMemoriaModificar();
        lblEstadoNombreTema.setVisible(false);
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado
    }

    @Override
    public void mostrarVista(Vista vista) {
        //No implementado
    }

    /**
     * Muestra la información de la entidad obtenida en todos los campos de la
     * vista, este objeto es enviado desde la Vista Consultar del caso de uso
     * correspondiente a esta vista.
     *
     * @param entidad El objeto entidad que contiene la información a mostrar en
     * la vista para ser modificada.
     */
    @Override
    public void mostrarEntidad(Object entidad) {
        //Mostrar los datos de toda la entidad
        mostrarDatos((TemaDTO) entidad);
    }

    @Override
    public boolean confirmarCambio() {
        
        /*
        Esta parte del código se eliminó el 24 de octubre del 2015 debido
        a que ya no se utilizará confirmación de cambio de vistas. El método
        'confirmarCambio' posiblemente sea eliminado de la 'InterfaceVista'
        
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "quiere cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
        */
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Esconde la etiqueta de estado del campo de texto que obtiene el foco.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusGained(FocusEvent e) {
        lblEstadoNombreTema.setVisible(false);
    }

    /**
     * Valida el estado de los campos de texto cuando se pierde el foco en los
     * campos de texto.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();

        String nombreTema = src.getText();
        if (Validador.estaVacio(nombreTema)) {
            mostrarLabelEstado(txtfNombreTema, false,
                    "No ingrese datos vacíos.");
        } else {
            //El nombre del tema ingresado cumple con las validaciones.

            //Se obtiene el nombre del tema seleccionado y se le quitan las
            //letras con acentos para poder comparar posteriormente si el
            //usuario escribió un nombre de tema diferente.
            String temaActual = controlVista.getTemaSeleccionado().getNombre();
            nombreTema = Validador.quitarAcentos(nombreTema);
            temaActual = Validador.quitarAcentos(temaActual);
            
            //Validar si escribió el mismo nombre de antes.
            if (nombreTema.compareToIgnoreCase(temaActual) == 0) {
                mostrarLabelEstado(txtfNombreTema, true, "");
            } else {
                //Si escribió otro nombre verificar si ya existe.
                boolean ok = controlVista.verificarExistencia(nombreTema);

                if (ok) {
                    mostrarLabelEstado(txtfNombreTema, false,
                            "Ya existe un tema con ese nombre.");
                } else {
                    mostrarLabelEstado(txtfNombreTema, true, "");
                }
            }
        }
    }

    /**
     * Valida que los campos no acepten más caracteres de los estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, txtfNombreTema.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        } else if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, txtfNombreTema.getText())) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Valida que los campos no acepten más caracteres de los estipulados en la
     * base de datos.
     *
     * @param e Objeto que contiene información del evento.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
            String portapapeles = "";

            try {
                portapapeles = (String) Toolkit.getDefaultToolkit()
                        .getSystemClipboard().getData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                System.out.println(ex);
            }

            if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, txtfNombreTema.getText() + portapapeles)) {
                e.consume();
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
