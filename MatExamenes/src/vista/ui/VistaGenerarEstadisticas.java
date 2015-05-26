/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.TablaEstadisticas;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVGenerarEstadisticas;
import vista.interfaz.InterfaceVista;

/**
 * JPanel que mostrará la interfaz gráfica de Generar Estadísticas
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class VistaGenerarEstadisticas extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    /**
     * Controlador de la vista del caso de uso Generar Estadísticas, funciona
     * para manejar la información obtenida en la vista para comunicarse con las
     * capas inferiores
     */
    private CVGenerarEstadisticas controlVista;
    /**
     * Interface de comunicación con la interfaz padre, la cuál sirve para
     * comunicarse con ella y recibir mensajes para mostrar otras vistas. En ese
     * caso es utilizada para comunicarse con el JFrame Principal
     */
    private InterfaceVista padre;

    /**
     * JFrame que será utilizado para mostrar la gráfica de estadísticas
     * resultante
     */
    private JFrame frmGrafica;
    /**
     * JFXPanel que será utilizado para contener la gráfica de barras generada
     * por JavaFX
     */
    private JFXPanel fxpnlGrafica;
    /**
     * Gráfica de barras donde se ilustrarán las estadísticas seleccionadas por
     * el usuario
     */
    private BarChart bcGrafica;

    /**
     * Objeto que agrupa lógicamente los radio buttons para seleccionar el tipo
     * de de estadística que se desea generar
     */
    private final ButtonGroup tipoEstadistica;

    /**
     * Lista de índices de selección de las filas que se acomodan de acuerdo al
     * orden de selección de las filas de la tabla. Representan los exámenes que
     * se desean ilustrar al generar las estadísticas
     */
    private List<Integer> indexesExamen = new ArrayList<>();

    /**
     * Bandera que sirve para que el evento de cambio no se dispare en los
     * combobox al ingresar nuevos datos, para evitar que el sistema tenga
     * resultados inesperados
     */
    private boolean noSelect;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Botón utilizado para cancelar
     */
    private javax.swing.JButton btnCancelar;
    /**
     * Botón utilizado para generar la gráfica
     */
    private javax.swing.JButton btnGenerar;
    /**
     * ComboBox utilizado para mostrar los cursos
     */
    private javax.swing.JComboBox cmbCurso;
    /**
     * Scroll Pane utilizado para mostrar tblExamenes
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label para cmbCurso
     */
    private javax.swing.JLabel lblCurso;
    /**
     * Label para tblExamenes
     */
    private javax.swing.JLabel lblExamenes;
    /**
     * Label para el título de la interfaz
     */
    private javax.swing.JLabel lblTitulo1;
    /**
     * Panel para agrupar los tipos de estadísticas
     */
    private javax.swing.JPanel pnlEstadisticas;
    /**
     * Radio button para el tipo de estadística por grados
     */
    private javax.swing.JRadioButton rbtnGrados;
    /**
     * Radio button para el tipo de estadística por grupos
     */
    private javax.swing.JRadioButton rbtnGrupos;
    /**
     * Radio button para el tipo de estadística por turnos
     */
    private javax.swing.JRadioButton rbtnTurnos;
    /**
     * Table para mostrar los exámenes
     */
    private javax.swing.JTable tblExamenes;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto VistaGenerarEstadisticas e inicializa sus atributos,
     * inicializa el thread JavaFX y los componentes necesarios para mostrar la
     * gráfica y agrega los listeners necesarios
     */
    public VistaGenerarEstadisticas() {
        initComponents();
        initFX();

        tipoEstadistica = new ButtonGroup();

        tipoEstadistica.add(rbtnGrupos);
        tipoEstadistica.add(rbtnGrados);
        tipoEstadistica.add(rbtnTurnos);

        initListeners();

        addAncestorListener(this);
    }

    /**
     * Este método sirve para inicializar el thread JavaFX para mostrar la
     * gráfica y debe ser llamado al principio, en el constructor.
     */
    private void initFX() {
        //Crea los paneles JavaFX necesarios
        fxpnlGrafica = new JFXPanel();
        fxpnlGrafica.setBounds(0, 0, 800, 500);
        JFXPanel fxpnlBoton = new JFXPanel();
        fxpnlBoton.setBounds(620, 520, 150, 30);

        //Asigna un listener al boton JavaFX para guardar la gráfica como imagen
        //en el equipo
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Button btnGuardar = new Button("Guardar Gráfica");
                btnGuardar.setOnAction(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        guardarGrafica();
                    }

                });
                //Crear la scene y lo necesario para mostrar la grafica
                Scene scene = new Scene(btnGuardar);
                fxpnlBoton.setScene(scene);
            }
        });

        frmGrafica = new JFrame("Estadísticas");
        frmGrafica.setLayout(null);
        frmGrafica.add(fxpnlGrafica);
        frmGrafica.add(fxpnlBoton);
        frmGrafica.setSize(800, 600);
        frmGrafica.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frmGrafica.setResizable(false);
        frmGrafica.setLocationRelativeTo(null);
        frmGrafica.setIconImage(new ImageIcon(getClass().getResource("/recursos/icono16.png")).getImage());

    }

    /**
     * Este método inicializa y agrega los listeners necesarios para que la
     * vista funcione.
     */
    private void initListeners() {
        //Para guardar el primer seleccionado como primer examen
        tblExamenes.getModel().addTableModelListener(new TableModelListener() {

            //Listener para cuando haya cambios en la tabla. Para ir guardando
            //Las selecciones de los exámenes en tiempo real.
            @Override
            public void tableChanged(TableModelEvent e) {
                //Evitar que se actualice inesperadamente
                if (!noSelect) {
                    int index = tblExamenes.getSelectedRow();

                    if ((boolean) tblExamenes.getValueAt(index, 0)) {
                    //Si lo seleccionó agregarlo a la lista

                        //Si sobrepasó el límite no permitir que seleccione más
                        if (indexesExamen.size() == 2) {
                            tblExamenes.setValueAt(false, index, 0);
                        } else {
                            indexesExamen.add(index);
                        }
                    } else {
                        //Si lo deseleccionó eliminarlo de la lista
                        for (int i = 0; i < indexesExamen.size(); i++) {
                            if (indexesExamen.get(i) == index) {
                                indexesExamen.remove(i);
                            }
                        }
                    }
                }
            }
        });

        //Listener para el cmbCurso
        cmbCurso.addActionListener(new ActionListener() {

            //Consultar los exámenes en cuanto se seleccione un curso en el
            //cmbCurso
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!noSelect) {
                    noSelect = true;
                    consultarExamenes();
                }
            }

        });

        //Listener para la ventana, cuando se vaya a cerrar
        frmGrafica.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                //Habilitar al padre, cerrar la ventana y terminar con el thread
                Platform.exit();
                ((JFrame) padre).setEnabled(true);
                frmGrafica.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                //Habilitar al padre, cerrar la ventana y terminar con el thread
                Platform.exit();
                ((JFrame) padre).setEnabled(true);
                frmGrafica.setVisible(false);
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    /**
     * Almacena el control de la vista
     *
     * @param controlVista El objeto encargado de realizar comunicar la vista
     * con las capas inferiores para acceder a los datos
     */
    public void setControlador(CVGenerarEstadisticas controlVista) {
        this.controlVista = controlVista;
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
     * Este método es utilizado para consultar y mostrar los cursos disponibles
     * en la base de datos, mediante la utilización del controlVista. En caso de
     * que no exista ningún curso se mostrará un mensaje y se regresará a la
     * vista principal.
     */
    private void consultarCursos() {
        //la lista de cursos obtenida desde la base de datos por el controlVista
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        //Si hay cursos...
        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        } else {
            //Si no hay mostrar un mensaje, regresar a la vista principal y
            //limpiar la vista actual
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }

    /**
     * Este método es utilizado para mostrar una lista de cursos en el
     * componente comboBox de la vista para mostrar los cursos disponibles.
     *
     * @param cursos una lista de cursos CursoDTO a ser mostrada en el comboBox
     * de la vista
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        //Para limpiar el cmbCurso de información previa
        cmbCurso.removeAllItems();

        //Recorrer todos los elementos de la lista para mostrarlos en el comboBox
        for (CursoDTO curso : cursos) {
            cmbCurso.addItem(curso.getNombre());
        }

        //Deselecciona el comboBox y permite la funcionalidad correcta del
        //listener del cmbCurso al igualar la bandera a falso
        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }

    /**
     * Este método es utilizado para consultar y mostrar los exámenes en base al
     * curso seleccionado, mediante la utilización del controlVista. En caso de
     * que no exista ningún examen se mostrará un mensaje y se permitirá volver
     * a realizar otra selección.
     */
    private void consultarExamenes() {
        //La lista de exámenes obtenidos por el controlVista
        List<ExamenDTO> examenes = controlVista
                .obtenerExamenesPorCurso(cmbCurso.getSelectedItem().toString());

        //Si hay exámenes resultantes mostrarlos en la vista
        if (examenes != null && !examenes.isEmpty()) {
            mostrarExamenes(examenes);
        } else {
            JOptionPane.showMessageDialog(this, "No hay examenes");
            ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
        }

        noSelect = false;
        indexesExamen = new ArrayList<>();
    }

    /**
     * Este método es utilizado para mostrar una lista de exámenes en el
     * componente table de la vista para mostrar los exámenes ingresados.
     *
     * @param examenes una lista de examenes ExamenDTO a ser mostrada en la
     * table de la vista
     */
    private void mostrarExamenes(List<ExamenDTO> examenes) {
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ExamenDTO examen : examenes) {
            Object[] datos = new Object[6];

            datos[0] = false;
            datos[1] = examen.getId();
            datos[2] = examen.getNombre();
            datos[3] = examen.getFechaCreacion();
            datos[4] = examen.getFechaModificacion();
            if (examen.getAutor() != null) {
                datos[5] = examen.getAutor().getUsuario();
            } else {
                datos[5] = "Sin autor";
            }

            model.addRow(datos);
        }
    }

    /**
     * Este método es utilizado para mostrar el objeto BarChart gráficamente
     * como una gráfica de barras en el frame emergente, inhabilitando esta
     * vista por el momento que el frame esté activo.
     *
     * @param grafica el objeto BarChart que se mostrará como gráfica de barras
     */
    private void mostrarGrafica(BarChart grafica) {
        //deshabilita el padre de esta vista y muestra el frame que contendrá
        //la gráfica
        frmGrafica.setVisible(true);
        ((JFrame) padre).setEnabled(false);

        //Thread de JavaFX para correr la grafica
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Crear la scene y lo necesario para mostrar la grafica
                Scene scene = new Scene(grafica);
                fxpnlGrafica.setScene(scene);
            }
        });

    }

    /**
     * Este método es utilizado para generar una gráfica de barras en base a los
     * parámetros de entrada para generar el tipo de gráfica correspondiente. El
     * resultado es mostrar la gráfica en una ventana emergente si todo salió
     * bien o mostrar un mensaje indicando la razón de porque no se generó la
     * gráfica
     *
     * @param tipo el tipo de gráfica que se va a generar, los valores aceptados
     * son (Grupo, Grado, Turno) para cada tipo de estadística respectivamente
     *
     * @param ejeX la etiqueta que llevará el ejeX
     * @param ejeY la etiqueta que llevará el ejeY
     * @param titulo la etiqueta que llevará el título de la gráfica
     */
    private void generarGrafica(String tipo, String ejeX, String ejeY,
            String titulo) {

        //Comprobar que se seleccionaron 2 examenes
        if (indexesExamen.size() == 2) {
            TablaEstadisticas tabla;
            //Elegir que tipo de estadística realizar
            if (tipo.equals("Grupo")) {
                tabla = controlVista.generarEstadisticasPorGrupos(indexesExamen);
            } else if (tipo.equals("Grado")) {
                tabla = controlVista.generarEstadisticasPorGrados(indexesExamen);
            } else {
                //Turnos
                tabla = controlVista.generarEstadisticasPorTurnos(indexesExamen);
            }

            //Si la tabla que se regresa del controlVista es null significa que
            //no existen grupos
            if (tabla == null) {
                JOptionPane.showMessageDialog(this, "No hay grupos");
            } else {
                //Para saber si hay aunque sea un promedio en la tabla
                boolean ok = false;
                for (int r = 0; r < tabla.getRowCount(); r++) {
                    for (int c = 0; c < tabla.getColumnCount(); c++) {

                        //Igualar todos los datos null a 0.0
                        if (tabla.getValueAt(r, c) == null) {
                            tabla.setValueAt(0.0, r, c);
                        } else {
                            ok = true;
                        }
                    }
                }

                if (!ok) {
                    //Si la tabla está vacía
                    JOptionPane.showMessageDialog(this, "No hay exámenes "
                            + "contestados");
                } else {
                    //Crear gráfica de barras
                    bcGrafica = crearGrafica(tabla, ejeX, ejeY, titulo);

                    //Mostrar la gráfica de barras
                    mostrarGrafica(bcGrafica);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione dos exámenes");
        }
    }

    /**
     * Este método es utilizado para crear un objeto BarChart (gráfica de
     * barras) manipulable en la interfaz en base a un objeto tablaEstadísticas
     * que contiene los datos de calificación y exámenes a graficar.
     *
     * @param tabla el objeto TablaEstadisticas que contiene las calificaciones
     * y los grupos/grados/turnos junto con los dos exámenes a graficar
     *
     * @param ejeX la etiqueta del eje X
     * @param ejeY la etiqueta del eje Y
     * @param titulo la etiqueta del título de la gráfica
     * @return un objeto BarChart con toda la información de la tabla vaciada.
     */
    private BarChart crearGrafica(TablaEstadisticas tabla, String ejeX, String ejeY,
            String titulo) {

        //Crea el eje X en base a las columnas de la tabla y le asigna la etiqueta
        //correspondiente
        CategoryAxis ejeHorizontal = new CategoryAxis();
        ejeHorizontal.setCategories(FXCollections.<String>observableArrayList(tabla.getColumnas()));
        ejeHorizontal.setLabel(ejeX);

        double tickUnit = tabla.getTickUnit();

        //Crea un eje vertical numérico y le asigna la etiqueta correspondiente
        NumberAxis ejeVertical = new NumberAxis();
        ejeVertical.setTickUnit(tickUnit);
        ejeVertical.setLabel(ejeY);

        //Crea un nuevo objeto BarChart en base a la matriz de datos de la tabla
        final BarChart grafica = new BarChart(ejeHorizontal, ejeVertical,
                tabla.getBarChartDatos());

        //Pasa la información de cada celda de la tabla al objeto BarChart
        Platform.runLater(new Runnable() {
            public void run() {
                for (int row = 0; row < tabla.getRowCount(); row++) {
                    for (int column = 0; column < tabla.getColumnCount(); column++) {
                        //Crea una nueva serie de la gráfica, osea una fila de la
                        //tabla. La cual contiene las calificaciones de un examen
                        //en todos los grupos/grados/turnos según sea el caso
                        XYChart.Series<String, Number> s
                                = (XYChart.Series<String, Number>) grafica.getData().get(row);
                        //pone el nombre a cada serie con el nombre del examen...
                        s.setName(tblExamenes.getValueAt(indexesExamen.get(row), 2)
                                .toString());
                        //Obtiene la calificación de cada grupo/grado/turno
                        //en base a la columna correspondiente de la tabla
                        BarChart.Data data = s.getData().get(column);
                        Object value = tabla.getValueAt(row, column);
                        data.setYValue(value);
                    }

                }
                //Nombra el título de la gráfica con la etiqueta título de entrada
                grafica.setTitle(titulo);

            }
        });

        return grafica;
    }

    /**
     * Este método sirve para guardar la gráfica como una imagen png en la
     * ubicación especificada por el usuario. Este método es llamado al
     * seleccionar Guardar en el JFrame emergente.
     */
    private void guardarGrafica() {
        //Crear una imagen de la gráfica
        WritableImage imagen = bcGrafica.snapshot(new SnapshotParameters(), null);

        //Elegir el archivo para guardar...
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("PNG file",
                new String[]{"png"});

        //Inicializar atributos del diálogo de archivos
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar Gráfica");
        chooser.setApproveButtonText("Guardar");
        chooser.setApproveButtonToolTipText("Guarda la gráfica en la ruta "
                + "seleccionada");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);

        //Mostrar el diálogo de archivos
        chooser.showOpenDialog(null);
        //Obtener el archivo en el que se quiere guardar la imagen
        File file = chooser.getSelectedFile();

        try {
            //Si el archivo no termina en .png concatenárselo
            if (file != null) {
                String path = file.getAbsolutePath();
                if (!path.endsWith(".png")) {
                    path += ".png";
                    file = new File(path);
                }

                //Escribir la imagen en la ruta especificada
                ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", file);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblExamenes = new javax.swing.JTable();
        lblExamenes = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        cmbCurso = new javax.swing.JComboBox();
        lblTitulo1 = new javax.swing.JLabel();
        pnlEstadisticas = new javax.swing.JPanel();
        rbtnGrupos = new javax.swing.JRadioButton();
        rbtnTurnos = new javax.swing.JRadioButton();
        rbtnGrados = new javax.swing.JRadioButton();
        btnGenerar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 600));

        tblExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[X]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblExamenes.setToolTipText("Selecciona los exámenes que desee en el orden que desea que se comparen");
        tblExamenes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblExamenes.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblExamenes);
        if (tblExamenes.getColumnModel().getColumnCount() > 0) {
            tblExamenes.getColumnModel().getColumn(0).setPreferredWidth(20);
        }

        lblExamenes.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblExamenes.setText("Seleccione dos exámenes:");

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("filtrar la búsqueda por curso");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo1.setText("Generar Estadísticas");

        pnlEstadisticas.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo de Estadística", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N

        rbtnGrupos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnGrupos.setText("Por Grupos");
        rbtnGrupos.setToolTipText("El examen puede ser visto y utilizado por todos los maestros");

        rbtnTurnos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnTurnos.setText("Por Turnos");
        rbtnTurnos.setToolTipText("El examen puede ser visto y utilizado por todos los maestros");

        rbtnGrados.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        rbtnGrados.setText("Por Grados");
        rbtnGrados.setToolTipText("El examen puede ser visto y utilizado por todos los maestros");

        javax.swing.GroupLayout pnlEstadisticasLayout = new javax.swing.GroupLayout(pnlEstadisticas);
        pnlEstadisticas.setLayout(pnlEstadisticasLayout);
        pnlEstadisticasLayout.setHorizontalGroup(
            pnlEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstadisticasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(rbtnGrupos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(rbtnGrados)
                .addGap(26, 26, 26)
                .addComponent(rbtnTurnos)
                .addGap(21, 21, 21))
        );
        pnlEstadisticasLayout.setVerticalGroup(
            pnlEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEstadisticasLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(pnlEstadisticasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnTurnos, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtnGrados, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnGenerar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnGenerar.setText("Generar");
        btnGenerar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generarGrafica(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(77, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblCurso)
                .addGap(18, 18, 18)
                .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(lblExamenes))
            .addGroup(layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(pnlEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblCurso))
                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblExamenes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(pnlEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método sirve para generar una gráfica de barrras en base a las
     * selecciones de exámenes y tipo de estadística hechas por el usuario. Este
     * método es llamado al seleccionar la opción Generar
     *
     * @param evt en objeto ActionEvent generado por el evento
     */
    private void generarGrafica(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafica
        //Obtener la selección del tipo de estadística
        ButtonModel seleccion = tipoEstadistica.getSelection();

        //Si hay selección comparar con cada botón para hacer la gráfica correcta
        if (seleccion != null) {
            if (rbtnGrados.getModel() == seleccion) {
                generarGrafica("Grado", "Grados", "Calificación", "Estadísticas "
                        + "por Grados");

            } else if (rbtnGrupos.getModel() == seleccion) {
                generarGrafica("Grupo", "Grupos", "Calificación", "Estadísticas "
                        + "por Grupos");
            } else {
                //Por turnos
                generarGrafica("Turno", "Turnos", "Calificación", "Estadísticas "
                        + "por Turnos");
            }
        } else {
            //Si no hay selección mostrar un mensaje
            JOptionPane.showMessageDialog(this, "Seleccione una tipo de "
                    + "Estadística");
        }
    }//GEN-LAST:event_generarGrafica

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "desea cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

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
        int ok = JOptionPane.showConfirmDialog(this, "¿Está seguro de que "
                + "quiere cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (ok == JOptionPane.YES_OPTION) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        //Limpiar
        noSelect = true;
        cmbCurso.removeAllItems();
        ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
        tipoEstadistica.clearSelection();

        controlVista.liberarMemoria();
    }

    /**
     * Este método es invocado cuando se muestra por primera vez esta vista
     *
     * @param event el objeto AncestorEvent generado por el evento
     */
    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            noSelect = true;
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
    }
}
