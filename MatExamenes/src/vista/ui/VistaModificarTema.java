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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
public class VistaModificarTema extends javax.swing.JPanel implements
        InterfaceVista, FocusListener {

    private CVMantenerTemas controlVista;
    private InterfaceVista padre;
    private final ImageIcon ICONO_BIEN;
    private final ImageIcon ICONO_MAL;
    private String mensajeDatosIncorrectos;

    /**
     * Creates new form VistaModificarTema
     */
    public VistaModificarTema() {
        initComponents();
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
     * Limpia la vista y la memoria asociada a esta vista
     */
    @Override
    public void limpiar() {
        txtfNombreTema.setText("");
        controlVista.liberarMemoriaModificar();
        lblEstadoNombreTema.setVisible(false);
    }

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
        CursoDTO objCurso = controlVista.obtenerCursoPorTema(tema);
        txtfNombreTema.setText(tema.getNombre());
        cbCursos.setSelectedItem(objCurso.getNombre());
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
            Logger.getLogger(VistaModificarTema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //No implementado
    }

    @Override
    public void mostrarVista(Vista vista) {
        //No implementado
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        //Mostrar los datos de toda la entidad
        //Este metodo equivaldria a mostrarTema(nombreTema) en el pseudocodigo
        mostrarDatos((TemaDTO) entidad);
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
            mostrarLabelEstado(txtfNombreTema, true, "");
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

        lblTitulo = new javax.swing.JLabel();
        lblNombreTema = new javax.swing.JLabel();
        txtfNombreTema = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCursos = new javax.swing.JLabel();
        cbCursos = new javax.swing.JComboBox();
        lblEstadoNombreTema = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(800, 579));

        lblTitulo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Modificar Tema");

        lblNombreTema.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblNombreTema.setText("Nombre del tema: ");

        txtfNombreTema.setName("lblEstadoNombreTema");
        txtfNombreTema.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfNombreTema.setPreferredSize(new java.awt.Dimension(350, 30));
        txtfNombreTema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfNombreTemaKeyTyped(evt);
            }
        });

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreTema)
                    .addComponent(lblCursos))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addGap(193, 193, 193)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método se llama al seleccionar regresar en modificar, para volver a
     * la vista consulta, esto limpia la vista modificar y libera la memoria
     * asociada a ella, es el mismo procedimiento para cuando se modifica un
     * tema existosamente
     *
     * @param evt
     */
    private void pasarControlVistaConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaConsulta
        // Ejecutado cuando selecciona regresar, pero es lo mismo que al
        //Seleccionar modificar
        //Muestra la vista consultar temas
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cancelar la operación?\nTodos los cambios no "
                + "guardados se perderán", "Cancelación", JOptionPane.YES_NO_OPTION);

        if (ok == 0) {
            padre.mostrarVista(Vista.ConsultarTemas);
            limpiar();
        }
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
            mostrarLabelEstado(txtfNombreTema, false, mensajeDatosIncorrectos);
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        //Persistir el objeto en la base de datos
        //Es necesario hacer un try catch en cada clase cuando se llame a
        //este metodo
        int indexCurso = cbCursos.getSelectedIndex();
        boolean ok = controlVista.modificarTema(tema, indexCurso);
        //No se pudo guardar porque habia un tema duplicado
        if (!ok) {
            mensajeDatosIncorrectos = "Ya existe un tema con ese nombre.";
            mostrarLabelEstado(txtfNombreTema, false, mensajeDatosIncorrectos);
            JOptionPane.showMessageDialog(this, mensajeDatosIncorrectos, "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            mostrarLabelEstado(txtfNombreTema, true, "");
            JOptionPane.showMessageDialog(this, "Tema Modificado");
//            padre.mostrarVistaConEntidad(tema, Vista.ConsultarTemas);
            padre.mostrarVista(Vista.ConsultarTemas);
            limpiar();
        }
    }//GEN-LAST:event_modificarTema

    private void txtfNombreTemaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfNombreTemaKeyTyped
        // TODO add your handling code here:
        if (!Validador.validarLongitud(Validador.LONGITUD_TEMA, txtfNombreTema.getText())) {
            evt.consume();
        }
    }//GEN-LAST:event_txtfNombreTemaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox cbCursos;
    private javax.swing.JLabel lblCursos;
    private javax.swing.JLabel lblEstadoNombreTema;
    private javax.swing.JLabel lblNombreTema;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtfNombreTema;
    // End of variables declaration//GEN-END:variables

}
