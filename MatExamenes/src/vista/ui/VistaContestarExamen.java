/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

/**
 *
 * @author Alf
 */
public class VistaContestarExamen extends javax.swing.JPanel {

    /**
     * Creates new form VistaContestarExamen
     */
    public VistaContestarExamen() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTiempo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaRedaccion = new javax.swing.JTextArea();
        lblReactivo = new javax.swing.JLabel();
        rbtnRespuesta1 = new javax.swing.JRadioButton();
        rbtnRespuesta2 = new javax.swing.JRadioButton();
        rbtnRespuesta3 = new javax.swing.JRadioButton();
        btnSiguiente = new javax.swing.JButton();

        lblTiempo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTiempo.setText("Tiempo restante: 50:00");

        txtaRedaccion.setColumns(20);
        txtaRedaccion.setRows(5);
        jScrollPane1.setViewportView(txtaRedaccion);

        lblReactivo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblReactivo.setText("Reactivo: 1-10");

        rbtnRespuesta1.setText("Respuesta1");

        rbtnRespuesta2.setText("Respuesta2");

        rbtnRespuesta3.setText("Respuesta3");

        btnSiguiente.setText("Siguiente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(151, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtnRespuesta3)
                    .addComponent(rbtnRespuesta2)
                    .addComponent(lblReactivo)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblTiempo)
                            .addGap(31, 31, 31))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnSiguiente)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(149, 149, 149)))
                    .addComponent(rbtnRespuesta1)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lblTiempo)
                .addGap(67, 67, 67)
                .addComponent(lblReactivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(rbtnRespuesta1)
                .addGap(18, 18, 18)
                .addComponent(rbtnRespuesta2)
                .addGap(18, 18, 18)
                .addComponent(rbtnRespuesta3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSiguiente)
                .addContainerGap(36, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblReactivo;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JRadioButton rbtnRespuesta1;
    private javax.swing.JRadioButton rbtnRespuesta2;
    private javax.swing.JRadioButton rbtnRespuesta3;
    private javax.swing.JTextArea txtaRedaccion;
    // End of variables declaration//GEN-END:variables
}
