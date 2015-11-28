/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.ui;

import java.awt.Component;
import java.awt.TextComponent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Field;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import modelo.dto.ReactivoDTO;

/**
 * JFrame que mostrará la interfaz gráfica para Ver Reactivo
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class FrmVerReactivo extends javax.swing.JFrame {

    /**
     * JFrame padre de este JFrame
     */
    private JFrame padre;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Scroll pane para la redacción del reactivo
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label para txtfOpt1
     */
    private javax.swing.JLabel lblOpciones1;
    /**
     * Label para txtfOpt2
     */
    private javax.swing.JLabel lblOpciones2;
    /**
     * Label para txtfOpt3
     */
    private javax.swing.JLabel lblOpciones3;
    /**
     * Label para txtfOpt4
     */
    private javax.swing.JLabel lblOpciones4;
    /**
     * Label para txtaRedaccion
     */
    private javax.swing.JLabel lblRedaccion;
    /**
     * Label para respuesta
     */
    private javax.swing.JLabel lblRespuesta;
    /**
     * Panel para agrupar opciones
     */
    private javax.swing.JPanel pnlOpciones;
    /**
     * Radio button para la opción 1
     */
    private javax.swing.JRadioButton rbtnOpt1;
    /**
     * Radio button para la opción 2
     */
    private javax.swing.JRadioButton rbtnOpt2;
    /**
     * Radio button para la opción 3
     */
    private javax.swing.JRadioButton rbtnOpt3;
    /**
     * Radio button para la opción 4
     */
    private javax.swing.JRadioButton rbtnOpt4;
    /**
     * Área de texto para mostrar la redacción del reactivo
     */
    private javax.swing.JTextArea txtaRedaccion;
    /**
     * Campo de texto para mostrar la opción 1
     */
    private javax.swing.JTextField txtfOpt1;
    /**
     * Campo de texto para mostrar la opción 2
     */
    private javax.swing.JTextField txtfOpt2;
    /**
     * Campo de texto para mostrar la opción 3
     */
    private javax.swing.JTextField txtfOpt3;
    /**
     * Campo de texto para mostrar la opción 4
     */
    private javax.swing.JTextField txtfOpt4;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmVerReactivo e inicializa sus atributos
     */
    public FrmVerReactivo() {
        initComponents();
        initListeners();
        //Para igualar los radios con los textos
        rbtnOpt1.setActionCommand("Opt1");
        rbtnOpt2.setActionCommand("Opt2");
        rbtnOpt3.setActionCommand("Opt3");
        rbtnOpt4.setActionCommand("Opt4");
        
        txtfOpt1.setName("Opt1");
        txtfOpt2.setName("Opt2");
        txtfOpt3.setName("Opt3");
        txtfOpt4.setName("Opt4");
        
        setTitle("Ver Reactivo");
        
        //Agregar caret...
        for (Component campo : pnlOpciones.getComponents()) {
            if(campo instanceof JTextComponent) {
                JTextComponent campoTexto = (JTextComponent) campo;
                
                campoTexto.addFocusListener(new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent e) {
                        campoTexto.getCaret().setVisible(true);
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        campoTexto.getCaret().setVisible(false);
                    }
                });
            }
        }
        
        txtaRedaccion.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                txtaRedaccion.getCaret().setVisible(true);
            }

            @Override
            public void focusLost(FocusEvent e) {
                txtaRedaccion.getCaret().setVisible(false);
            }
        });
    }

    /**
     * Este método sirve para agregar los listeners a la ventana, para que al
     * momento de cerrar la ventana se limpien los datos y se habilite el padre
     */
    private void initListeners() {
        this.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                padre.setEnabled(true);
                limpiar();
                dispose();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                padre.setEnabled(true);
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
    }
    
    /**
     * Almacena el padre de este JFrame
     *
     * @param padre padre del JFrame.
     */
    public void setPadre(JFrame padre) {
        this.padre = padre;
    }
    
    /**
     * Este método sirve para deshabilitar al padre, mostrar esta ventana y mostrar
     * los datos del reactivo. Debe ser llamado al principio y sólo una vez.
     * 
     * @param reactivo el objeto ReactivoDTO a mostrar
     */
    public void inicializar(ReactivoDTO reactivo) {
        
        padre.setEnabled(false);
        setVisible(true);
        mostrarReactivo(reactivo);
    }
   
    /**
     * Este método sirve para mostrar los datos del reactivo en los campos del
     * frame
     * 
     * @param reactivo el objeto ReactivoDTO a mostrar
     */
    private void mostrarReactivo(ReactivoDTO reactivo) {
        //Mostrar redacción
        txtaRedaccion.setText(reactivo.getRedaccion());
        
        int i = 0;
        int size = reactivo.getOpcionesIncorrectas().size();
        
        //Mostrar Opciones
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;

                //Mostrar primero opciones incorrectas y al final la respuesta
                if (i < size) {
                    field.setText(reactivo.getOpcionesIncorrectas().get(i));
                } else {
                    field.setText(reactivo.getRespuesta());
                    try {
                        Field fieldObj = getClass().getDeclaredField("rbtn" + field.getName());
                        JRadioButton button = (JRadioButton) fieldObj.get(this);
                        button.setSelected(true);
                    } catch (NoSuchFieldException | IllegalAccessException ex) {
                    }
                }
                i++;
            }
        }
    }
    
    /**
     * Este método sirve para limpiar la información en el frame y debe ser
     * llamado siempre que se cierre este frame y se deje de utilizar.
     */
    public void limpiar() {

        txtaRedaccion.setText("");
        for (Component comp : pnlOpciones.getComponents()) {
            if (comp.getClass() == JTextField.class) {
                JTextField field = (JTextField) comp;
                
                field.setText("");
            } else if(comp.getClass() == JRadioButton.class) {
                JRadioButton button = (JRadioButton) comp;
                
                button.setSelected(false);
            }
        }
    }
    
    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtaRedaccion = new javax.swing.JTextArea();
        pnlOpciones = new javax.swing.JPanel();
        txtfOpt1 = new javax.swing.JTextField();
        txtfOpt2 = new javax.swing.JTextField();
        txtfOpt3 = new javax.swing.JTextField();
        lblOpciones1 = new javax.swing.JLabel();
        lblOpciones2 = new javax.swing.JLabel();
        lblOpciones3 = new javax.swing.JLabel();
        txtfOpt4 = new javax.swing.JTextField();
        lblOpciones4 = new javax.swing.JLabel();
        rbtnOpt1 = new javax.swing.JRadioButton();
        rbtnOpt2 = new javax.swing.JRadioButton();
        rbtnOpt4 = new javax.swing.JRadioButton();
        rbtnOpt3 = new javax.swing.JRadioButton();
        lblRespuesta = new javax.swing.JLabel();
        lblRedaccion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        txtaRedaccion.setEditable(false);
        txtaRedaccion.setColumns(20);
        txtaRedaccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtaRedaccion.setLineWrap(true);
        txtaRedaccion.setRows(5);
        jScrollPane1.setViewportView(txtaRedaccion);

        pnlOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opciones:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        pnlOpciones.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        txtfOpt1.setEditable(false);
        txtfOpt1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt1.setPreferredSize(new java.awt.Dimension(6, 30));

        txtfOpt2.setEditable(false);
        txtfOpt2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt2.setPreferredSize(new java.awt.Dimension(6, 30));

        txtfOpt3.setEditable(false);
        txtfOpt3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt3.setPreferredSize(new java.awt.Dimension(6, 30));

        lblOpciones1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones1.setText("Opción 1:");

        lblOpciones2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones2.setText("Opción 2:");

        lblOpciones3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones3.setText("Opción 3:");

        txtfOpt4.setEditable(false);
        txtfOpt4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtfOpt4.setPreferredSize(new java.awt.Dimension(6, 30));

        lblOpciones4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblOpciones4.setText("Opción 4:");

        rbtnOpt1.setEnabled(false);

        rbtnOpt2.setEnabled(false);

        rbtnOpt4.setEnabled(false);

        rbtnOpt3.setEnabled(false);

        lblRespuesta.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        lblRespuesta.setText("Respuesta");

        javax.swing.GroupLayout pnlOpcionesLayout = new javax.swing.GroupLayout(pnlOpciones);
        pnlOpciones.setLayout(pnlOpcionesLayout);
        pnlOpcionesLayout.setHorizontalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblRespuesta))
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                .addComponent(rbtnOpt4)
                                .addGap(18, 18, 18)
                                .addComponent(lblOpciones4)
                                .addGap(18, 18, 18)
                                .addComponent(txtfOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                .addComponent(rbtnOpt3)
                                .addGap(18, 18, 18)
                                .addComponent(lblOpciones3)
                                .addGap(18, 18, 18)
                                .addComponent(txtfOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                .addComponent(rbtnOpt2)
                                .addGap(18, 18, 18)
                                .addComponent(lblOpciones2)
                                .addGap(18, 18, 18)
                                .addComponent(txtfOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createSequentialGroup()
                                .addComponent(rbtnOpt1)
                                .addGap(18, 18, 18)
                                .addComponent(lblOpciones1)
                                .addGap(18, 18, 18)
                                .addComponent(txtfOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        pnlOpcionesLayout.setVerticalGroup(
            pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRespuesta)
                .addGap(7, 7, 7)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlOpcionesLayout.createSequentialGroup()
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtnOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtfOpt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblOpciones1)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtfOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblOpciones2)))
                    .addComponent(rbtnOpt2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rbtnOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfOpt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOpciones3)))
                .addGap(18, 18, 18)
                .addGroup(pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtfOpt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblOpciones4))
                    .addComponent(rbtnOpt4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblRedaccion.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblRedaccion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRedaccion.setText("Redacción del Reactivo:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRedaccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRedaccion)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(pnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

}
