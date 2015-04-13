/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import javax.swing.JOptionPane;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVMantenerTemas;
import vista.controlador.Validador;
import vista.interfaz.InterfazVista;

/**
 *
 * @author Jesus Donaldo
 */
public class VistaModificarTema extends javax.swing.JPanel implements
        InterfazVista {

    private CVMantenerTemas controlVista;
    private InterfazVista padre;
    
    /**
     * Creates new form VistaModificarTema
     */
    public VistaModificarTema() {
        initComponents();
    }

    public void setPadre(InterfazVista padre) {
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
        txtfNombre.setText("");
        controlVista.liberarMemoriaModificar();
    }
    
    private TemaDTO encapsularTema() {
        TemaDTO tema = null;
        
        //Validar campos
        String txtNombre = txtfNombre.getText();
        if(Validador.esCurso(txtNombre)) {
            //Crear objeto tema
            tema = new TemaDTO();
            tema.setNombre(txtNombre);
        }
        
        return tema;
    }
    
    /**
     * Muestra los datos del objeto tema en la vista para poder ser modificado
     * @param tema el objeto tema
     */
    private void mostrarDatos(TemaDTO tema) {
        txtfNombre.setText(tema.getNombre());
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
        jLabel2 = new javax.swing.JLabel();
        txtfNombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(800, 579));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Modificar Temas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tema:");

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarTema(evt);
            }
        });

        jButton2.setText("Regresar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasarControlVistaConsulta(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(179, 179, 179))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(327, 327, 327)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(179, 179, 179)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel1)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(302, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método se llama al seleccionar regresar en modificar, para volver
     * a la vista consulta, esto limpia la vista modificar y libera la memoria
     * asociada a ella, es el mismo procedimiento para cuando se modifica un
     * tema existosamente
     * @param evt 
     */
    private void pasarControlVistaConsulta(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasarControlVistaConsulta
        // Ejecutado cuando selecciona regresar, pero es lo mismo que al
        //Seleccionar modificar
        //Muestra la vista consultar temas
        int ok = JOptionPane.showConfirmDialog(this, "¿Estás segur@ de que "
                + "quieres cambiar de pantalla?\nTodos los cambios no "
                + "guardados se perderán");

        if(ok == 0) {
            padre.mostrarVista(Vista.ConsultarTemas);
            limpiar();
        }
    }//GEN-LAST:event_pasarControlVistaConsulta

    /**
     * Para modificar un tema en la base de datos y regresar a la vista
     * consultar
     * @param evt 
     */
    private void modificarTema(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarTema
        //Modificar Tema
        //Encapsular objeto
        TemaDTO tema = encapsularTema();
        if(tema == null) {
            JOptionPane.showMessageDialog(this, "Datos incorrectos, porfavor " +
                    "sólo ingresa números y letras");
            return;
        }
        //Persistir el objeto en la base de datos
        //Es necesario hacer un try catch en cada clase cuando se llame a
        //este metodo
        boolean ok = controlVista.modificarTema(tema);
        //No se pudo guardar porque habia un tema duplicado
        if (!ok) {
            JOptionPane.showMessageDialog(this, "Tema existente");
        } else {
            JOptionPane.showMessageDialog(this, "Tema Modificado");
            padre.mostrarVistaConEntidad(tema, Vista.ConsultarTemas);
            limpiar();
        }
    }//GEN-LAST:event_modificarTema


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtfNombre;
    // End of variables declaration//GEN-END:variables

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

}
