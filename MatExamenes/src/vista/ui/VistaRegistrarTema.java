/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.controlador.Validador;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author Jesus Donaldo
 */
//IMPORTANTE> El tamaño de los paneles no debe sobrepasar el de 800, 579
//Esto para que quede bien con el frame y los menus. No lo probe mas para ver
//Si quedaba bien de lo ancho pero espero que no sea un mayor problema
public class VistaRegistrarTema extends javax.swing.JPanel implements
        AncestorListener, FocusListener, InterfaceVista {

    private CVMantenerTemas controlVista;
    private InterfaceVista padre;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;
    private String mensajeDatosIncorrectos;

    /**
     * Creates new form VistaRegistrarTema
     */
    public VistaRegistrarTema() {
        initComponents();
        this.addAncestorListener(this);
        txtfNombreTema.addFocusListener(this);

        ICONO_BIEN = new ImageIcon(getClass().getResource("/recursos/bien.png"));
        ICONO_MAL = new ImageIcon(getClass().getResource("/recursos/mal.png"));

        lblEstadoNombreTema.setVisible(false);
    }

    public void setPadre(InterfaceVista padre) {
        this.padre = padre;
    }

    public void setControlador(CVMantenerTemas controlVista) {
        this.controlVista = controlVista;
    }

    /**
     * Limpia los campos de la vista
     */
    @Override
    public void limpiar() {
        txtfNombreTema.setText("");
        lblEstadoNombreTema.setVisible(false);
    }

    /**
     * Permite guardar todos los atributos basicos del objeto de manera
     * ordenada, tambien valida que los atributos sean correctos
     *
     * @return el objeto tema con sus atributos.
     */
    private TemaDTO encapsularTema() {
        TemaDTO tema = null;

        //Validar campos
        String txtNombre = txtfNombreTema.getText();
        if (Validador.esCurso(txtNombre)) {
            //Crear objeto tema
            tema = new TemaDTO();
            tema.setNombre(txtNombre);
            mostrarLabelEstado(txtfNombreTema, true, "");
        } else {
            if (Validador.estaVacio(txtNombre)) {
                mensajeDatosIncorrectos = "No ingresar datos vacíos.";
            } else {
                mensajeDatosIncorrectos = "Ingresa sólo letras y/o números.";
            }
        }

        return tema;
    }

    private boolean consultarCursos() {
        List<CursoDTO> cursos = controlVista.obtenerCursos();

        if (cursos != null && !cursos.isEmpty()) {
            mostrarCursos(cursos);
            return true;
        } else {
            return false;
        }
    }

    private void mostrarCursos(List<CursoDTO> cursos) {
        cbCursos.removeAllItems();
        for (CursoDTO curso : cursos) {
            cbCursos.addItem(curso.getNombre());
        }
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
            Logger.getLogger(VistaRegistrarTema.class.getName()).log(Level.SEVERE, null, ex);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void ancestorAdded(AncestorEvent event) {
        if (isShowing()) {
            if(!consultarCursos()) {
                JOptionPane.showMessageDialog(this, "No hay cursos registrados.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
                padre.mostrarVista(Vista.HOME);
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
        lblEstadoNombreTema.setVisible(false);
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();

        String nombreTema = src.getText();
        if (!Validador.esCurso(nombreTema)) {
            if (Validador.estaVacio(nombreTema)) {
                mensajeDatosIncorrectos = "No ingresar datos vacíos.";
            } else {
                mensajeDatosIncorrectos = "Ingresa sólo letras y/o números.";
            }
            mostrarLabelEstado(txtfNombreTema, false, mensajeDatosIncorrectos);
        } else {
            boolean ok = controlVista.verificarExistencia(nombreTema);

            if (ok) {
                mostrarLabelEstado(txtfNombreTema, false, "Ya existe un tema con ese nombre.");
            } else {
                mostrarLabelEstado(txtfNombreTema, true, "");
            }
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

        jButton1 = new javax.swing.JButton();
        txtfNombreTema = new javax.swing.JTextField();
        lblNombreTema = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        btnCancelar = new javax.swing.JButton();
        lblEstadoNombreTema = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 600));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/guardar24.png"))); // NOI18N
        jButton1.setText("Guardar");
        jButton1.setPreferredSize(new java.awt.Dimension(115, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarTema(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(498, 482, -1, -1));

        txtfNombreTema.setName("lblEstadoNombreTema");
        txtfNombreTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreTema.setPreferredSize(new java.awt.Dimension(350, 30));
        txtfNombreTema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfNombreTemaKeyTyped(evt);
            }
        });
        add(txtfNombreTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, 30));

        lblNombreTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreTema.setText("Nombre del tema:");
        add(lblNombreTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 163, -1, -1));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Registrar Tema");
        add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 62, 780, -1));

        lblCursos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblCursos.setText("Seleccione un curso:");
        add(lblCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(179, 287, -1, -1));

        cbCursos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cbCursos.setPreferredSize(new java.awt.Dimension(200, 30));
        add(cbCursos, new org.netbeans.lib.awtextra.AbsoluteConstraints(354, 280, -1, -1));

        btnCancelar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setPreferredSize(new java.awt.Dimension(115, 30));
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(644, 482, -1, -1));

        lblEstadoNombreTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/bien.png"))); // NOI18N
        add(lblEstadoNombreTema, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 150, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Persiste el objeto en la base de datos....
     *
     * @param evt
     */
    private void guardarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarTema
        //Guardar Tema
        //Encapsular objeto
        TemaDTO tema = encapsularTema();
        if (tema == null) {
            mostrarLabelEstado(txtfNombreTema, false, mensajeDatosIncorrectos);
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //Persistir el objeto en la base de datos
        //Es necesario hacer un try catch en cada clase cuando se llame a
        //este metodo
        int indexCurso = cbCursos.getSelectedIndex();
        Integer id = controlVista.guardarTema(tema, indexCurso);

        if (id == null) {
            //No se pudo guardar porque habia un tema duplicado
            mostrarLabelEstado(txtfNombreTema, false, "Ya existe un tema con ese nombre.");
            JOptionPane.showMessageDialog(this, "Ya existe un tema con ese nombre.", "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Tema Registrado.");
//            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_guardarTema

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            padre.mostrarVista(Vista.HOME);
            limpiar();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtfNombreTemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNombreTemaKeyTyped
        // TODO add your handling code here:
        if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, txtfNombreTema.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfNombreTemaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblEstadoNombreTema;
    private javax.swing.JLabel lblNombreTema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtfNombreTema;
    // End of variables declaration//GEN-END:variables

}
