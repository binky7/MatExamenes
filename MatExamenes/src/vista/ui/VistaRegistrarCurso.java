/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerCursos;
import vista.controlador.Validador;
import vista.interfaz.InterfazVista;

/**
 *
 * @author Jesus Donaldo
 */
public class VistaRegistrarCurso extends javax.swing.JPanel 
implements AncestorListener, FocusListener, InterfazVista{

    private CVMantenerCursos controlVista;
    private InterfazVista padre;
    
    /**
     * Creates new form RegistrarModificarCursos
     */
    public VistaRegistrarCurso() {
        initComponents();
        this.addAncestorListener(this);
        txtfNombreCurso.addFocusListener(this);
        
        lstTemasSinAsignar.setModel(new DefaultListModel());
        lstTemas.setModel(new DefaultListModel());
        
        lstTemasSinAsignar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstTemas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void limpiar() {
        txtfNombreCurso.setText("");
        ((DefaultListModel)lstTemas.getModel()).clear();
        ((DefaultListModel)lstTemasSinAsignar.getModel()).clear();
        
        controlVista.liberarMemoriaRegistrarModificar();
    }
    
    public void setControlador(CVMantenerCursos controlVista) {
        this.controlVista = controlVista;
    }
    
    public void setPadre(InterfazVista padre) {
        this.padre = padre;
    }
    
    private void consultarTemas() {
        List<TemaDTO> temasSinAsignar = controlVista.obtenerTemasSinAsignar();
        
        if(temasSinAsignar != null && !temasSinAsignar.isEmpty()) {
            mostrarTemas(temasSinAsignar);
        }
    }
    
    private void mostrarTemas(List<TemaDTO> temasSinAsignar) {
        System.out.println("Estoy mostrando temas.");
        DefaultListModel listModelTemasSinAsignar = 
                (DefaultListModel) lstTemasSinAsignar.getModel();
        
        for(TemaDTO tema : temasSinAsignar) {
            listModelTemasSinAsignar.addElement(tema.getNombre());
        }
    }
    
    public CursoDTO encapsularCurso() {
        CursoDTO curso = null;
        
        String nombreCurso = txtfNombreCurso.getText();
        if(Validador.esCurso(nombreCurso)) {
            curso = new CursoDTO();
            curso.setNombre(nombreCurso);
        } else {
            curso = null;
        }
        
        return curso;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        txtfNombreCurso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTemas = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstTemasSinAsignar = new javax.swing.JList();
        btnRemover = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 579));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Curso:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Temas Disponibles");

        lstTemas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstTemas);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Temas Agregados");

        lstTemasSinAsignar.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lstTemasSinAsignar);

        btnRemover.setText("<- Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar ->");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnRegresar.setText("Regresar");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Registrar Curso");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel3)
                        .addGap(30, 30, 30)
                        .addComponent(txtfNombreCurso)
                        .addGap(175, 175, 175))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(183, 183, 183))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnRegresar)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAgregar)
                            .addComponent(btnRemover))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addGap(131, 131, 131))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(303, 303, 303))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(btnAgregar)
                        .addGap(42, 42, 42)
                        .addComponent(btnRemover)))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegresar)
                    .addComponent(btnGuardar))
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if(!lstTemasSinAsignar.isSelectionEmpty()) {
            DefaultListModel listModelTemasSinAsignar = 
                (DefaultListModel) lstTemasSinAsignar.getModel();
            DefaultListModel listModelTemas = 
                    (DefaultListModel) lstTemas.getModel();
            
            int indexTema = lstTemasSinAsignar.getSelectedIndex();
            controlVista.agregarTemaSeleccionado(indexTema);
            
            listModelTemas.addElement(lstTemasSinAsignar.getSelectedValue());
            listModelTemasSinAsignar.remove(indexTema);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tema.");
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // TODO add your handling code here:
        if(!lstTemas.isSelectionEmpty()) {
            DefaultListModel listModelTemasSinAsignar = 
                (DefaultListModel) lstTemasSinAsignar.getModel();
            DefaultListModel listModelTemas = 
                    (DefaultListModel) lstTemas.getModel();
            
            int indexTema = lstTemas.getSelectedIndex();
            controlVista.removerTemaSeleccionado(indexTema);
            
            listModelTemasSinAsignar.addElement(lstTemas.getSelectedValue());
            listModelTemas.remove(indexTema);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un tema.");
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        CursoDTO curso = encapsularCurso();
        if(curso == null) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre válido.");
        } else {
            Integer id = controlVista.guardarCurso(curso);
            if(id != null) {
                JOptionPane.showMessageDialog(this, "Registro completo.");
                padre.mostrarVista(Vista.HOME);
                limpiar();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo guardar el curso.");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lstTemas;
    private javax.swing.JList lstTemasSinAsignar;
    private javax.swing.JTextField txtfNombreCurso;
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
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");
        if (ok == 0) {
            cambiar = true;
        }
        return cambiar;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ancestorAdded(AncestorEvent event) {
        if(isShowing()) {
            if(((DefaultListModel)lstTemasSinAsignar.getModel()).isEmpty()) {
                consultarTemas();
            }
        }
    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {
        //No implementado
    }

    @Override
    public void ancestorMoved(AncestorEvent event) {
        //No implementado
    }

    @Override
    public void focusGained(FocusEvent e) {
        //No implementado
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField)e.getSource();
        
        String nombreCurso = src.getText();
        System.out.println(nombreCurso);
        if(Validador.esCurso(nombreCurso)) {
            boolean ok = controlVista.verificarExistencia(nombreCurso);
            System.out.println(ok);
            
            if(ok) {
                JOptionPane.showMessageDialog(this, "Ya existe un curso con ese nombre.");
            }
        } else {
            //el curso no cumple con el formato.
        }
    }
}
