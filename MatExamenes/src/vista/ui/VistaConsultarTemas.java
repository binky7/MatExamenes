/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.interfaz.InterfaceVista;
import vista.interfaz.InterfaceVista.Vista;

/**
 *
 * @author Jesus Donaldo
 */
public class VistaConsultarTemas extends javax.swing.JPanel implements
        AncestorListener, InterfaceVista {

    private CVMantenerTemas controlVista;
    //Para poder mostrar vista Modificar
    private InterfaceVista padre;

    /**
     * Creates new form VistaConsultarTemas
     */
    public VistaConsultarTemas() {
        initComponents();
        this.addAncestorListener(this);
        //Para manipular las listas
        lstCursos.setModel(new DefaultListModel());
        lstTemas.setModel(new DefaultListModel());

        //Solo seleccionar uno a la vez
        lstCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lstTemas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Manejador de eventos para curso
        lstCursos.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Para saber si se hizo click a la lista
                if (!e.getValueIsAdjusting()) {
                    consultarTemas();
                }
            }

        });
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    public void setControlador(CVMantenerTemas controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Limpia los componentes y libera las listas dtos utilizadas
     */
    @Override
    public void limpiar() {
        //Limpiar las listas
        ((DefaultListModel) lstCursos.getModel()).clear();
        ((DefaultListModel) lstTemas.getModel()).clear();
        //Liberar memoria dto
        controlVista.liberarMemoriaConsultar();
    }

    /**
     * Se manda llamar al mostrarse la vista por primera vez (no despues de
     * regresar de modificar)
     */
    private boolean consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();
        boolean ok = false;

        if (cursos != null && !cursos.isEmpty()) {
            ok = true;
            mostrarCursos(cursos);
        }

        return ok;
    }

    /**
     * Se llama automaticamente despues de seleccionar un curso en lstCursos
     */
    private void consultarTemas() {

        List<TemaDTO> temas;

        if (lstCursos.getSelectedIndex() == -1) {
            //Evitar que ocurra algo al cambiar de panel
            return;
        }

        temas = controlVista.obtenerTemasDeCurso(lstCursos
                .getSelectedIndex());

        if (temas != null && !temas.isEmpty()) {
            mostrarTemas(temas);
        } else {
            JOptionPane.showMessageDialog(this, "El curso seleccionado no tiene temas.");
            ((DefaultListModel) lstTemas.getModel()).clear();
        }
    }

    /**
     * Mostrar los cursos que se consultaron en lstCursos
     *
     * @param cursos los objetos curso a mostrar
     */
    private void mostrarCursos(List<CursoDTO> cursos) {
        DefaultListModel listModel = (DefaultListModel) lstCursos.getModel();
        DefaultListModel modeloTemas = (DefaultListModel) lstTemas.getModel();
        modeloTemas.clear();

        listModel.clear();
        //Mostrar cada curso, no remover, si no buscar por medio del for
        for (CursoDTO curso : cursos) {
            listModel.addElement(curso.getNombre());
        }
    }

    /**
     * Mostrar los temas que se consultaron en lstTemas
     *
     * @param temas los objetos tema a mostrar
     */
    private void mostrarTemas(List<TemaDTO> temas) {
        DefaultListModel listModel = (DefaultListModel) lstTemas.getModel();

        listModel.clear();
        //Mostrar cada tema, no remover, si no buscar por medio del for
        for (TemaDTO tema : temas) {
            listModel.addElement(tema.getNombre());
        }
    }
    
        @Override
    public void ancestorAdded(AncestorEvent event) {
        if (!consultarCursos()) {
            limpiar();
            JOptionPane.showMessageDialog(this, "No hay cursos registrados.");
            padre.mostrarVista(Vista.HOME);
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
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarVista(Vista vista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        ((DefaultListModel) lstCursos.getModel()).clear();
        ((DefaultListModel) lstTemas.getModel()).clear();

    }

    @Override
    public boolean confirmarCambio() {
        return true;
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        lblTemas = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstCursos = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstTemas = new javax.swing.JList();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Consultar Temas");

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Cursos");

        lblTemas.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblTemas.setText("Temas");

        lstCursos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lstCursos);
        lstCursos.getAccessibleContext().setAccessibleName("");

        lstTemas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstTemas);

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modificar24.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaModificar(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarTema(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(99, 99, 99)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(lblCursos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTemas)
                .addGap(205, 205, 205))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(lblTitulo)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCursos)
                    .addComponent(lblTemas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Elimina un tema seleccionado de la base de datos
     *
     * @param evt
     */
    private void eliminarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarTema
        //Eliminar Tema
        //Si se selecciono algo
        boolean ok;
        if (lstTemas.getSelectedIndex() != -1) {
            int banEliminar = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                    + "quieres eliminar el tema?", "Eliminación", JOptionPane.YES_NO_OPTION);
            if (banEliminar == 0) {
                ok = controlVista.eliminarTema(lstTemas.getSelectedIndex());
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Tema eliminado");
                    ((DefaultListModel) lstTemas.getModel())
                            .remove(lstTemas.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un tema");
        }

    }//GEN-LAST:event_eliminarTema

    /**
     * Pasa a la vista modificar para modificar el tema
     *
     * @param evt
     */
    private void pasarControlVistaModificar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaModificar

        //Mostrar la vista modificar
        if (lstTemas.getSelectedIndex() != -1) {
            TemaDTO tema = controlVista.obtenerTema(lstTemas.getSelectedIndex());

            if (tema != null) {
                //Mostrar la vista modificar tema enviandole el objeto tema
                padre.mostrarVistaConEntidad(tema, Vista.ModificarTema);
            } else {
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un tema");
        }

    }//GEN-LAST:event_pasarControlVistaModificar

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        padre.mostrarVista(Vista.HOME);
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblTemas;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList lstCursos;
    private javax.swing.JList lstTemas;
    // End of variables declaration//GEN-END:variables


}
