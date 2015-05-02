/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import vista.controlador.CVMantenerExamenes;
import vista.interfaz.InterfaceExamen;

/**
 *
 * @author Jesus Donaldo
 */
public class FrmAgregarReactivos extends javax.swing.JFrame {

    private CVMantenerExamenes controlVista;
    private InterfaceExamen padre;
    private int indexCurso;
    private int clave;
    
    private final FrmVerReactivo frmVerReactivo;
    
    private boolean noSelect;
    
    /**
     * Creates new form VistaAgregarReactivos
     */
    public FrmAgregarReactivos() {
        initComponents();
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
                //ads
            }

            @Override
            public void windowClosing(WindowEvent e) {
                ((JFrame)padre.getPadre()).setEnabled(true);
                limpiar();
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                ((JFrame)padre.getPadre()).setEnabled(true);
                limpiar();
                dispose();
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
        
        frmVerReactivo = new FrmVerReactivo();
        frmVerReactivo.setPadre(this);
        frmVerReactivo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        this.setTitle("Agregar Reactivos");
        
        lstTemasAuto.setModel(new DefaultListModel());
        lstTemasManual.setModel(new DefaultListModel());
        
        lstTemasManual.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Para saber si se hizo click a la lista
                if(!e.getValueIsAdjusting()) {
                    if (!noSelect) {
                        //consultar reactivos
                        consultarReactivos();
                    }
                }
            }
            
        });
    }

    public void inicializar(int indexCurso, int clave) {
        
        ((JFrame)padre.getPadre()).setEnabled(false);
        this.indexCurso = indexCurso;
        this.clave = clave;
        setVisible(true);
        consultarTemas();
    }
    
    private void mostrarTemas(List<TemaDTO> temas, JList lista) {
        
        DefaultListModel listModel = (DefaultListModel) lista.getModel();
        
        listModel.clear();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for(TemaDTO tema : temas) {
            listModel.addElement(tema.getNombre());
        }
        noSelect = false;
    }
    
    private void mostrarReactivo(ReactivoDTO reactivo) {
        frmVerReactivo.inicializar(reactivo);
    }
    
    private void consultarReactivos() {
        List<ReactivoDTO> reactivos = controlVista
                .obtenerReactivosPorTema((String)lstTemasManual
                        .getSelectedValue());
        
        if(reactivos != null && !reactivos.isEmpty()) {
            mostrarReactivos(reactivos);
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay reactivos");
            ((DefaultTableModel)tblReactivos.getModel()).setRowCount(0);
        }
    }
    
    private void mostrarReactivos(List<ReactivoDTO> reactivos) {
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();
        
        model.setRowCount(0);
        //Mostrar cada reactivo, no remover, si no buscar por medio del for
        for(ReactivoDTO reactivo : reactivos) {
            Object[] datos = new Object[6];
            
            datos[0] = false;
            datos[1] = reactivo.getId();
            datos[2] = reactivo.getNombre();
            datos[3] = reactivo.getFechaCreacion();
            datos[4] = reactivo.getFechaModificacion();
            if(reactivo.getAutor() != null) {
                datos[5] = reactivo.getAutor().getUsuario();
            }
            else {
                datos[5] = "Sin autor";
            }
            
            model.addRow(datos);
        }
    }
    
    private void consultarTemas() {
        List<TemaDTO> temas;
        
        if(indexCurso != -1) {
            temas = controlVista.obtenerTemasDeCurso(indexCurso);
        }
        else {
            temas = controlVista.obtenerTemasDeCurso();
        }
        
        if (temas != null && !temas.isEmpty()) {
            mostrarTemas(temas, lstTemasManual);
            mostrarTemas(temas, lstTemasAuto);
        } else {
            JOptionPane.showMessageDialog(this, "No hay temas");
            ((JFrame)padre.getPadre()).setEnabled(true);
            dispose();
            limpiar();
        }
        
    }

    private List<Integer> getSelectedRows() {
        List<Integer> selectedIndexes = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) tblReactivos.getModel();
        
        for(int i = 0; i < model.getRowCount(); i++) {
            if((boolean)model.getValueAt(i, 0) == true) {
                selectedIndexes.add(i);
            }
        }
        return selectedIndexes;
    }
    
    private void consultarReactivosAleatorios() {
        List<ReactivoDTO> reactivos = controlVista
                .obtenerReactivosAleatorios(clave);

        if (reactivos != null) {
            padre.mostrarReactivos(reactivos, clave);
            ((JFrame) padre.getPadre()).setEnabled(true);
            limpiar();
            dispose();
        }
        else {
            JOptionPane.showMessageDialog(this, "No hay reactivos suficientes "
                    + "para los temas seleccionados");
        }
    }
    
    public void setControlador(CVMantenerExamenes controlVista) {
        this.controlVista = controlVista;
    }
    
    public void setPadre(InterfaceExamen padre) {
        this.padre = padre;
    }
    
    public void limpiar() {
        
        noSelect = true;
        ((DefaultListModel)lstTemasAuto.getModel()).clear();
        ((DefaultListModel)lstTemasManual.getModel()).clear();
        ((DefaultTableModel)tblReactivos.getModel()).setRowCount(0);
        ((DefaultTableModel)tblSeleccion.getModel()).setRowCount(0);
        spnCantidad.setValue(0);
        
        controlVista.liberarMemoriaAgregarReactivos();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        tbpSeleccion = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblReactivos = new javax.swing.JTable();
        lblReactivos = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        lstTemasManual = new javax.swing.JList();
        lblTemasManual = new javax.swing.JLabel();
        btnVer = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        spnCantidad = new javax.swing.JSpinner();
        lblTemasAuto = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        lstTemasAuto = new javax.swing.JList();
        lblCantidad = new javax.swing.JLabel();
        lblSeleccion = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblSeleccion = new javax.swing.JTable();
        btnAgregarSeleccion = new javax.swing.JButton();
        btnRemoverSeleccion = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "[x]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
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
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Reactivos:");

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane5.setViewportView(jList1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Temas:");

        jButton7.setText("Ver");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(175, 175, 175)
                        .addComponent(jLabel1)
                        .addGap(161, 161, 161)))
                .addComponent(jButton7)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jButton7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(20, 20, 20))))
        );

        jTabbedPane1.addTab("Selección Manual", jPanel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Tema:");

        jList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane6.setViewportView(jList2);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Cantidad:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Selección:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tema", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Agregar Seleccion");

        jButton4.setText("Remover Seleccion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jSpinner1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addComponent(jLabel3))
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jLabel4))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(189, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Selección Automática", jPanel2);

        jButton2.setText("Cancelar");

        jButton1.setText("Aceptar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbpSeleccion.setPreferredSize(new java.awt.Dimension(500, 300));

        tblReactivos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblReactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[x]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
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
        tblReactivos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblReactivos.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblReactivos);
        if (tblReactivos.getColumnModel().getColumnCount() > 0) {
            tblReactivos.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblReactivos.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        lblReactivos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReactivos.setText("Reactivos:");

        lstTemasManual.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstTemasManual.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTemasManual.setToolTipText("selecciona un tema para la búsqueda");
        jScrollPane7.setViewportView(lstTemasManual);

        lblTemasManual.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTemasManual.setText("Temas:");

        btnVer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/ver24.png"))); // NOI18N
        btnVer.setToolTipText("ver reactivo");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verReactivo(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(lblTemasManual)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(lblReactivos)
                        .addGap(162, 162, 162))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblReactivos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVer, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTemasManual))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        tbpSeleccion.addTab("Selección Manual", jPanel3);

        spnCantidad.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spnCantidad.setToolTipText("cantidad de reactivos a seleccionar del tema");

        lblTemasAuto.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblTemasAuto.setText("Temas:");

        lstTemasAuto.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstTemasAuto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstTemasAuto.setToolTipText("tema a agregar");
        jScrollPane8.setViewportView(lstTemasAuto);

        lblCantidad.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCantidad.setText("Cantidad:");

        lblSeleccion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblSeleccion.setText("Selección:");

        tblSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tblSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tema", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSeleccion.setToolTipText("temas a agregar y cantidad de reactivos por tema a seleccionar");
        tblSeleccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblSeleccion.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tblSeleccion);
        if (tblSeleccion.getColumnModel().getColumnCount() > 0) {
            tblSeleccion.getColumnModel().getColumn(0).setResizable(false);
            tblSeleccion.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAgregarSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAgregarSeleccion.setText("Agregar Seleccion");
        btnAgregarSeleccion.setToolTipText("agrega una nueva selección del tema y cantidad seleccionados");
        btnAgregarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarSeleccion(evt);
            }
        });

        btnRemoverSeleccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnRemoverSeleccion.setText("Remover Seleccion");
        btnRemoverSeleccion.setToolTipText("Remueve la fila seleccionada de la tabla selecciones");
        btnRemoverSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removerSeleccion(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(lblCantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spnCantidad))
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(lblTemasAuto)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblSeleccion)
                        .addGap(194, 194, 194))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnAgregarSeleccion)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoverSeleccion)
                                .addGap(47, 47, 47))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTemasAuto)
                    .addComponent(lblSeleccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCantidad)
                            .addComponent(spnCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRemoverSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbpSeleccion.addTab("Selección Automática", jPanel4);

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarAgregarReactivos(evt);
            }
        });

        btnAceptar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar24.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.setToolTipText("agregar los reactivos automática o manualmente al examen");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarAgregarReactivos(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(18, 18, 18)
                .addComponent(btnCancelar)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbpSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tbpSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarAgregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarAgregarReactivos

        if (tbpSeleccion.getSelectedIndex() == 0) {
            //Seleccion Manual
            List<Integer> indexesReactivo = getSelectedRows();
            if (indexesReactivo.size() > 0) {
                List<ReactivoDTO> reactivos = controlVista
                        .agregarReactivosSeleccionados(indexesReactivo, clave);

                padre.mostrarReactivos(reactivos, clave);
                ((JFrame) padre.getPadre()).setEnabled(true);
                limpiar();
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Selecciona reactivos");
            }
        }
        else {
            //Seleccion Aleatoria
            if(tblSeleccion.getRowCount() > 0) {
                consultarReactivosAleatorios();
            }
            else {
                JOptionPane.showMessageDialog(this, "Agrega alguna selección");
            }
        }
    }//GEN-LAST:event_aceptarAgregarReactivos

    private void cancelarAgregarReactivos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarAgregarReactivos
        
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodo el progreso "
                + "se perderá");
        if (ok == 0) {
            ((JFrame) padre.getPadre()).setEnabled(true);
            limpiar();
            dispose();
        }
    }//GEN-LAST:event_cancelarAgregarReactivos

    private void verReactivo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verReactivo
        // TODO add your handling code here:
        if(tblReactivos.getSelectedRow() != -1) {
            ReactivoDTO reactivo = controlVista.obtenerReactivo(tblReactivos
                    .getSelectedRow());
            
            mostrarReactivo(reactivo);
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona un reactivo");
        }
    }//GEN-LAST:event_verReactivo

    private void agregarSeleccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarSeleccion
        // TODO add your handling code here:
        if(lstTemasAuto.getSelectedIndex() != -1) {
            DefaultListModel model = (DefaultListModel) lstTemasAuto.getModel();
            
            int cantidad = (int) spnCantidad.getValue();
            
            if(cantidad > 0) {
                DefaultTableModel tblModel = (DefaultTableModel) tblSeleccion
                        .getModel();
                
                String nombreTema = (String) model.remove(lstTemasAuto
                        .getSelectedIndex());
                controlVista.agregarSeleccion(nombreTema, cantidad);
                Object[] seleccion = new Object[2];
                
                seleccion[0] = nombreTema;
                seleccion[1] = cantidad;
                
                tblModel.addRow(seleccion);
            }
            else {
                JOptionPane.showMessageDialog(this, "Debes seleccionar una "
                        + "cantidad mayor a 0");
            }
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona primero un tema");
        }
    }//GEN-LAST:event_agregarSeleccion

    private void removerSeleccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removerSeleccion
        // TODO add your handling code here:
        if(tblSeleccion.getSelectedRow() != -1) {
            DefaultListModel model = (DefaultListModel) lstTemasAuto.getModel();
            DefaultTableModel tblModel = (DefaultTableModel) tblSeleccion
                    .getModel();
            
            String nombreTema = (String) tblModel.getValueAt(tblSeleccion
                    .getSelectedRow(), 0);
            
            controlVista.removerSeleccion(tblSeleccion.getSelectedRow());
            
            tblModel.removeRow(tblSeleccion.getSelectedRow());
            model.addElement(nombreTema);
        }
        else {
            JOptionPane.showMessageDialog(this, "Selecciona primero una fila");
        }
    }//GEN-LAST:event_removerSeleccion


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregarSeleccion;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRemoverSeleccion;
    private javax.swing.JButton btnVer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblReactivos;
    private javax.swing.JLabel lblSeleccion;
    private javax.swing.JLabel lblTemasAuto;
    private javax.swing.JLabel lblTemasManual;
    private javax.swing.JList lstTemasAuto;
    private javax.swing.JList lstTemasManual;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTable tblReactivos;
    private javax.swing.JTable tblSeleccion;
    private javax.swing.JTabbedPane tbpSeleccion;
    // End of variables declaration//GEN-END:variables
}
