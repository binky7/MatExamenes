/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import modelo.dto.CursoDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerCursos;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author ivan
 */
public class VistaModificarCurso extends javax.swing.JPanel
        implements InterfaceVista, FocusListener {

    private CVMantenerCursos controlVista;
    private InterfaceVista padre;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;
    private String mensajeDatosIncorrectos;

    /**
     * Creates new form VistaModificarCurso
     */
    public VistaModificarCurso() {
        initComponents();
        txtfNombreCurso.addFocusListener(this);
        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));
        lblEstadoNombreCurso.setVisible(false);
    }

    public void setControlador(CVMantenerCursos controlVista) {
        this.controlVista = controlVista;
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    private void mostrarDatos(CursoDTO curso) {
        txtfNombreCurso.setText(curso.getNombre());
    }
    
    public CursoDTO encapsularCurso() {
        CursoDTO curso = null;

        String nombreCurso = txtfNombreCurso.getText();
        if (Validador.esCurso(nombreCurso)) {
            curso = new CursoDTO();
            curso.setNombre(nombreCurso);
            mostrarLabelEstado(txtfNombreCurso, true, "");
        } else {
            curso = null;
            if (Validador.estaVacio(nombreCurso)) {
                mensajeDatosIncorrectos = "No ingresar datos vacíos.";
            } else {
                mensajeDatosIncorrectos = "Ingresa sólo letras y/o números.";
            }
        }

        return curso;
    }

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
            Logger.getLogger(VistaModificarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        mostrarDatos((CursoDTO) entidad);
    }

    @Override
    public boolean confirmarCambio() {
        boolean cambiar = false;
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
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
    public void limpiar() {
        txtfNombreCurso.setText("");
        lblEstadoNombreCurso.setVisible(false);
        controlVista.liberarMemoriaRegistrarModificar();
    }

    @Override
    public void focusGained(FocusEvent e) {
        lblEstadoNombreCurso.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();

        String nombreCurso = src.getText();
        if (!Validador.esCurso(nombreCurso)) {
            if (Validador.estaVacio(nombreCurso)) {
                mensajeDatosIncorrectos = "No ingresar datos vacíos.";
            } else {
                mensajeDatosIncorrectos = "Ingresa sólo letras y/o números.";
            }
            mostrarLabelEstado(txtfNombreCurso, false, mensajeDatosIncorrectos);
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

        jLabel1 = new javax.swing.JLabel();
        txtfNombreCurso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        lblEstadoNombreCurso = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Nombre del curso:");

        txtfNombreCurso.setName("lblEstadoNombreCurso");
        txtfNombreCurso.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreCurso.setPreferredSize(new java.awt.Dimension(350, 30));
        txtfNombreCurso.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfNombreCursoKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Modificar Curso");

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        btnModificar.setText("Guardar");
        btnModificar.setPreferredSize(new java.awt.Dimension(110, 30));
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarCurso(evt);
            }
        });

        lblEstadoNombreCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(537, 537, 537)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtfNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstadoNombreCurso)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel4)
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfNombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(lblEstadoNombreCurso))
                .addGap(280, 280, 280)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void modificarCurso(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarCurso
        // TODO add your handling code here:
        CursoDTO curso = encapsularCurso();

        if (curso == null) {
            mostrarLabelEstado(txtfNombreCurso, false, mensajeDatosIncorrectos);
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            boolean ok = controlVista.modificarCurso(curso);

            if (ok) {
                JOptionPane.showMessageDialog(this, "Curso modificado.");
                padre.mostrarVistaConEntidad(curso, Vista.ConsultarCursos);
                limpiar();
            } else {
                mensajeDatosIncorrectos = "Ya existe un curso con ese nombre.";
                mostrarLabelEstado(txtfNombreCurso, false, mensajeDatosIncorrectos);
                JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_modificarCurso

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarCursos);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtfNombreCursoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNombreCursoKeyTyped
        // TODO add your handling code here:
        if(!Validador.validarLongitud(Validador.LONGITUD_CURSO, txtfNombreCurso.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfNombreCursoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblEstadoNombreCurso;
    private javax.swing.JTextField txtfNombreCurso;
    // End of variables declaration//GEN-END:variables

}
