/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package vista.ui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JPanel para mostrar la tabla de reactivos asociados a una clave de examen
 * 
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class PnlReactivosTab extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    /**
     * Scroll pane para la tabla de reactivos
     */
    private javax.swing.JScrollPane jScrollPane1;
    /**
     * Label para tabla
     */
    private javax.swing.JLabel lblReactivos;
    /**
     * Table para mostrar los reactivos que pertenecen a este panel
     */
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto PnlReactivosTab e inicializa sus atributos
     */
    public PnlReactivosTab() {
        initComponents();
    }

    /**
     * Inicializa los atributos gráficos y los coloca en su posición.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lblReactivos = new javax.swing.JLabel();

        tabla.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "[X]", "Id", "Nombre", "Fecha Creación", "Fecha Modificación", "Autor"
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
        tabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tabla);
        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setPreferredWidth(20);
            tabla.getColumnModel().getColumn(1).setPreferredWidth(20);
        }

        lblReactivos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        lblReactivos.setText("Reactivos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblReactivos)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addComponent(lblReactivos)
                    .addGap(14, 14, 14)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Para comprobar si la tabla que este panel contiene tiene reactivos o no
     * 
     * @return true si la tabla no contiene reactivos o false de lo contrario
     */
    public boolean sinReactivos() {
        return tabla.getRowCount() == 0;
    }
    
    /**
     * Obtiene el índice seleccionado de la tabla
     * 
     * @return el número de fila seleccionada de la tabla o -1 en caso de que no
     * haya selección
     */
    public int getSelectedIndex() {
        return tabla.getSelectedRow();
    }
    
    /**
     * Para obtener la tabla de reactivos para que se pueda manipular
     * 
     * @return el objeto JTable de la tabla de reactivos de este panel
     */
    public JTable getTabla() {
        return tabla;
    }
    
    /**
     * Regresa los índices seleccionados de esta tabla
     * 
     * @return regresa todos los números de fila seleccionados de la tabla,
     * ordenados de la misma manera que visualmente
     */
    public List<Integer> getSelectedRows() {
        List<Integer> selectedIndexes = new ArrayList<>();
        
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        
        for(int i = 0; i < model.getRowCount(); i++) {
            if((boolean)model.getValueAt(i, 0) == true) {
                selectedIndexes.add(i);
            }
        }
        return selectedIndexes;
    }

}
