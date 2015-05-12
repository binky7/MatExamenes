/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
 *
 * @author BoredmanDA
 */
public class VistaGenerarEstadisticas extends javax.swing.JPanel
        implements InterfaceVista, AncestorListener {

    /**
     * Creates new form GenerarEstadisticasAlumno
     */
    private CVGenerarEstadisticas controlVista;
    private InterfaceVista padre;

    private JFrame frmGrafica;
    private JFXPanel fxpnlGrafica;
    private BarChart bcGrafica;

    private final ButtonGroup tipoEstadistica;

    private List<Integer> indexesExamen = new ArrayList<>();

    private boolean noSelect;

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

    private void initFX() {
        fxpnlGrafica = new JFXPanel();
        fxpnlGrafica.setBounds(0, 0, 800, 500);
        JFXPanel fxpnlBoton = new JFXPanel();
        fxpnlBoton.setBounds(620, 520, 150, 30);
        
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
    }
    
    private void initListeners() {
        //Para guardar el primer seleccionado como primer examen
        tblExamenes.getModel().addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                //Evitar que se actualice inesperadamente
                if (!noSelect) {
                    int index = tblExamenes.getSelectedRow();

                    if ((boolean) tblExamenes.getValueAt(index, 0)) {
                    //Si lo selecciono agregarlo a la lista

                        //Si sobrepaso el limite no permitir que seleccione mas
                        if (indexesExamen.size() == 2) {
                            tblExamenes.setValueAt(false, index, 0);
                        } else {
                            indexesExamen.add(index);
                        }
                    } else {
                        //Si lo deselecciono eliminarlo de la lista
                        for (int i = 0; i < indexesExamen.size(); i++) {
                            if (indexesExamen.get(i) == index) {
                                indexesExamen.remove(i);
                            }
                        }
                    }

                    System.out.println("");
                    for (int i = 0; i < indexesExamen.size(); i++) {
                        System.out.println(indexesExamen.get(i));
                    }
                    System.out.println("");
                }
            }
        });

        //Listener para el cmbCurso
        cmbCurso.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if (!noSelect) {
                    noSelect = true;
                    consultarExamenes();
                }
            }

        });

        frmGrafica.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                //
            }

            @Override
            public void windowClosing(WindowEvent e) {
                Platform.exit();
                ((JFrame) padre).setEnabled(true);
                frmGrafica.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                Platform.exit();
                ((JFrame) padre).setEnabled(true);
                frmGrafica.setVisible(false);
            }

            @Override
            public void windowIconified(WindowEvent e) {
                //
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                //
            }

            @Override
            public void windowActivated(WindowEvent e) {
                //
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                //
            }
        });
    }
    
    public void setControlador(CVGenerarEstadisticas controlVista) {
        this.controlVista = controlVista;
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    private void consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
        } else {
            JOptionPane.showMessageDialog(this, "No hay cursos");
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }

    private void mostrarCursos(List<CursoDTO> cursos) {

        cmbCurso.removeAllItems();

        for (CursoDTO curso : cursos) {
            //System.out.println(cmbCurso.getSelectedIndex());
            cmbCurso.addItem(curso.getNombre());
        }

        cmbCurso.setSelectedIndex(-1);
        noSelect = false;
    }

    private void consultarExamenes() {
        List<ExamenDTO> examenes = controlVista
                .obtenerExamenesPorCurso(cmbCurso.getSelectedItem().toString());

        if (examenes != null && !examenes.isEmpty()) {
            mostrarExamenes(examenes);
        } else {
            JOptionPane.showMessageDialog(this, "No hay examenes");
            ((DefaultTableModel) tblExamenes.getModel()).setRowCount(0);
        }

        noSelect = false;
        indexesExamen = new ArrayList<>();
    }

    private void mostrarExamenes(List<ExamenDTO> examenes) {
        DefaultTableModel model = (DefaultTableModel) tblExamenes.getModel();

        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for (ExamenDTO examen : examenes) {
            Object[] datos = new Object[6];

            datos[0] = false;
            datos[1] = examen.getId();
            datos[2] = examen.getTitulo();
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

    private void mostrarGrafica(BarChart grafica) {
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

    private void generarGrafica(String tipo, String ejeX, String ejeY,
            String titulo) {

//Comprobar que se seleccionaron 2 examenes
        if (indexesExamen.size() == 2) {
            TablaEstadisticas tabla;
            //Elegir que tipo de estadistica realizar
            if (tipo.equals("Grupo")) {
                tabla = controlVista.generarEstadisticasPorGrupos(indexesExamen);
            } else if (tipo.equals("Grado")) {
                tabla = controlVista.generarEstadisticasPorGrados(indexesExamen);
            } else {
                //Turnos
                tabla = controlVista.generarEstadisticasPorTurnos(indexesExamen);
            }

            if (tabla == null) {
                JOptionPane.showMessageDialog(this, "No hay grupos");
            } else {
                //Para saber si hay aunque sea un promedio
                boolean ok = false;
                for (int r = 0; r < tabla.getRowCount(); r++) {
                    for (int c = 0; c < tabla.getColumnCount(); c++) {

                        if (tabla.getValueAt(r, c) == null) {
                            tabla.setValueAt(0.0, r, c);
                        } else {
                            ok = true;
                        }
                    }
                }

                if (!ok) {
                    //Si la tabla esta vacia
                    JOptionPane.showMessageDialog(this, "No hay exámenes "
                            + "contestados");
                } else {
                    //Crear grafica de barras
                    bcGrafica = crearGrafica(tabla, ejeX, ejeY, titulo);

                    //Mostrar la grafica de barras
                    mostrarGrafica(bcGrafica);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona dos exámenes");
        }
    }

    private BarChart crearGrafica(TablaEstadisticas tabla, String ejeX, String ejeY,
            String titulo) {

        CategoryAxis ejeHorizontal = new CategoryAxis();
        ejeHorizontal.setCategories(FXCollections.<String>observableArrayList(tabla.
                getColumnNames()));
        ejeHorizontal.setLabel(ejeX);

        double tickUnit = tabla.getTickUnit();

        NumberAxis ejeVertical = new NumberAxis();
        ejeVertical.setTickUnit(tickUnit);
        ejeVertical.setLabel(ejeY);

        final BarChart grafica = new BarChart(ejeHorizontal, ejeVertical,
                tabla.getBarChartData());

        Platform.runLater(new Runnable() {
            public void run() {
                for (int row = 0; row < tabla.getRowCount(); row++) {
                    for (int column = 0; column < tabla.getColumnCount(); column++) {
                        XYChart.Series<String, Number> s
                                = (XYChart.Series<String, Number>) 
                                grafica.getData().get(row);
                        //pone el nombre a cada serie con el titulo del examen...
                        s.setName(tblExamenes.getValueAt(indexesExamen.get(row), 2)
                                .toString());
                        BarChart.Data data = s.getData().get(column);
                        Object value = tabla.getValueAt(row, column);
                        data.setYValue(value);
                    }

                }
                grafica.setTitle(titulo);

            }
        });

        return grafica;
    }

    private void guardarGrafica() {
        //Crear una imagen de la grafica
        WritableImage imagen = bcGrafica.snapshot(new SnapshotParameters(), null);

        //Elegir el archivo para guardar...
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("PNG file",
                new String[] {"png"});
        
        chooser.addChoosableFileFilter(filter);
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar Gráfica");
        chooser.setApproveButtonText("Guardar");
        chooser.setApproveButtonToolTipText("Guarda la gráfica en la ruta "
                + "seleccionada");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setMultiSelectionEnabled(false);
        //Mostrar el dialogo
        chooser.showOpenDialog(null);
        //Obtener el archivo a guardar
        File file = chooser.getSelectedFile();
        
        try {
            if(file != null) {
                String path = file.getAbsolutePath();
                if(!path.endsWith(".png")) {
                    path += ".png";
                    file = new File(path);
                }
                
                ImageIO.write(SwingFXUtils.fromFXImage(imagen, null), "png", file);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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

        setPreferredSize(new java.awt.Dimension(800, 600));

        tblExamenes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[X]", "Id", "Título", "Fecha Creación", "Fecha Modificación", "Autor"
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
        lblExamenes.setText("Exámenes:");

        lblCurso.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCurso.setText("Curso:");

        cmbCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cmbCurso.setToolTipText("filtrar la búsqueda por curso");
        cmbCurso.setPreferredSize(new java.awt.Dimension(78, 25));

        lblTitulo1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(307, 307, 307)
                .addComponent(lblTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(75, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lblCurso)
                                    .addGap(18, 18, 18)
                                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addGap(117, 117, 117)
                                    .addComponent(lblExamenes)
                                    .addGap(292, 292, 292))))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(pnlEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(202, 202, 202))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitulo1)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurso))
                .addGap(18, 18, 18)
                .addComponent(lblExamenes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(pnlEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void generarGrafica(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generarGrafica
        ButtonModel seleccion = tipoEstadistica.getSelection();

        //Si hay seleccion comparar con cada boton para hacer la grafica correcta
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
            JOptionPane.showMessageDialog(this, "Selecciona una tipo de "
                    + "Estadística");
        }
    }//GEN-LAST:event_generarGrafica

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JComboBox cmbCurso;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblExamenes;
    private javax.swing.JLabel lblTitulo1;
    private javax.swing.JPanel pnlEstadisticas;
    private javax.swing.JRadioButton rbtnGrados;
    private javax.swing.JRadioButton rbtnGrupos;
    private javax.swing.JRadioButton rbtnTurnos;
    private javax.swing.JTable tblExamenes;
    // End of variables declaration//GEN-END:variables

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            noSelect = true;
            consultarCursos();
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //
    }
}
