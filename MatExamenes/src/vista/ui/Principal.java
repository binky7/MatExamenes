/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVConsultarCalificaciones;
import vista.controlador.CVMantenerGrupos;
import vista.controlador.CVMantenerTemas;
import vista.interfaz.InterfazVista;

/**
 *
 * @author Jesus Donaldo
 */
public class Principal extends javax.swing.JFrame implements InterfazVista {

    //Usuario que inicio sesion
    private UsuarioDTO usuarioActual;
    
    //Para manipular las vistas
    private final CardLayout manejadorVista = new CardLayout();
    private VistaHOME vistaHome;
    private VistaRegistrarTema vistaRegistrarTema;
    private VistaConsultarTemas vistaConsultarTemas;
    private VistaModificarTema vistaModificarTema;
    private RegistrarExamen vistaRegistrarExamen;
    private VistaRegistrarCurso vistaRegistrarCurso;
    private VistaConsultarCursos vistaConsultarCursos;
    private VistaRegistrarGrupo vistaRegistrarGrupo;
    private VistaConsultarGrupo vistaConsultarGrupo;
    private VistaModificarGrupo vistaModificarGrupo;
    private VistaConsultarCalificaciones vistaConsultarCalificaciones;
    private VistaConsultarCalificacionesExamen vistaConsultarCalificacionesExamen;
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        init();
        //manejadorVista.last(vistas);
        setTitle("MatExamenes");
    }
    
    public void setUsuarioActual(UsuarioDTO usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /**
     * Para crear los paneles, los controladores de vista y las relaciones
     * entre esta vista y los paneles
     */
    private void init() {
        //se deben agregar todos los paneles de vista a este panel
        vistas.setLayout(manejadorVista);
        
        //Crear vistas
        vistaHome = new VistaHOME();
        vistaHome.setName(Vista.HOME.toString());
        vistaRegistrarTema = new VistaRegistrarTema();
        vistaRegistrarTema.setName(Vista.RegistrarTema.toString());
        vistaRegistrarExamen = new RegistrarExamen();
        vistaRegistrarExamen.setName(Vista.RegistrarExamen.toString());
        vistaConsultarTemas = new VistaConsultarTemas();
        vistaConsultarTemas.setName(Vista.ConsultarTemas.toString());
        vistaModificarTema = new VistaModificarTema();
        vistaModificarTema.setName(Vista.ModificarTema.toString());
        vistaRegistrarCurso = new VistaRegistrarCurso();
        vistaRegistrarCurso.setName(Vista.RegistrarCurso.toString());
        vistaConsultarCursos = new VistaConsultarCursos();
        vistaConsultarCursos.setName(Vista.ConsultarCursos.toString());
        vistaRegistrarGrupo = new VistaRegistrarGrupo();
        vistaRegistrarGrupo.setName(Vista.RegistrarGrupo.toString());
        vistaConsultarGrupo = new VistaConsultarGrupo();
        vistaConsultarGrupo.setName(Vista.ConsultarGrupo.toString());
        vistaModificarGrupo = new VistaModificarGrupo();
        vistaModificarGrupo.setName(Vista.ModificarGrupo.toString());
        vistaConsultarCalificaciones = new VistaConsultarCalificaciones();
        vistaConsultarCalificaciones.setName(Vista.ConsultarCalificaciones.toString());
        vistaConsultarCalificacionesExamen = new VistaConsultarCalificacionesExamen();
        vistaConsultarCalificacionesExamen.setName(Vista.ConsultarCalificacionesExamen.toString());
        
        //Crear controladores vistas
        CVMantenerTemas cvMantenerTemas = new CVMantenerTemas();
        CVMantenerGrupos cvMantenerGrupos = new CVMantenerGrupos();
        CVConsultarCalificaciones cvConsultarCalificaciones =
                new CVConsultarCalificaciones();
        
        //Asignar controladores a vistas
        vistaRegistrarTema.setControlador(cvMantenerTemas);
        vistaModificarTema.setControlador(cvMantenerTemas);
        vistaConsultarTemas.setControlador(cvMantenerTemas);
        vistaRegistrarGrupo.setControlador(cvMantenerGrupos);
        vistaConsultarGrupo.setControlador(cvMantenerGrupos);
        vistaModificarGrupo.setControlador(cvMantenerGrupos);
        vistaConsultarCalificaciones.setControlador(cvConsultarCalificaciones);
        vistaConsultarCalificacionesExamen.setControlador(cvConsultarCalificaciones);
        
        //Asignar padre a vistas
        vistaRegistrarTema.setPadre(this);
        vistaModificarTema.setPadre(this);
        vistaConsultarTemas.setPadre(this);
        vistaConsultarCursos.setPadre(this);
        vistaRegistrarGrupo.setPadre(this);
        vistaConsultarGrupo.setPadre(this);
        vistaModificarGrupo.setPadre(this);
        vistaConsultarCalificaciones.setPadre(this);
        vistaConsultarCalificacionesExamen.setPadre(this);
        
        //Agregar un panel y su identificador. Para agregar mas identificadores
        //ir a vista.interfaz.InterfazVista y agregarlos al enum Vista
        vistas.add(vistaHome, Vista.HOME.toString());
        vistas.add(vistaRegistrarTema, Vista.RegistrarTema.toString());
        vistas.add(vistaConsultarTemas, Vista.ConsultarTemas.toString());
        vistas.add(vistaModificarTema, Vista.ModificarTema.toString());
        vistas.add(vistaRegistrarExamen, Vista.RegistrarExamen.toString());
        vistas.add(vistaRegistrarCurso, Vista.RegistrarCurso.toString());
        vistas.add(vistaConsultarCursos, Vista.ConsultarCursos.toString());
        vistas.add(vistaRegistrarGrupo, Vista.RegistrarGrupo.toString());
        vistas.add(vistaConsultarGrupo, Vista.ConsultarGrupo.toString());
        vistas.add(vistaModificarGrupo, Vista.ModificarGrupo.toString());
        vistas.add(vistaConsultarCalificaciones, Vista.ConsultarCalificaciones.toString());
        vistas.add(vistaConsultarCalificacionesExamen, Vista.ConsultarCalificacionesExamen.toString());
    }
    
    /**
     * Obtener la vista actualmente mostrada
     * @return vista actual
     */
    private JPanel getVistaActual() {
        JPanel vista = null;

        for (Component comp : vistas.getComponents()) {
            if (comp.isVisible()) {
                vista = (JPanel) comp;
                break;
            }
        }
        
        return vista;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JSeparator();
        vistas = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu12 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();

        jMenu9.setText("jMenu9");

        jMenu10.setText("jMenu10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        javax.swing.GroupLayout vistasLayout = new javax.swing.GroupLayout(vistas);
        vistas.setLayout(vistasLayout);
        vistasLayout.setHorizontalGroup(
            vistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        vistasLayout.setVerticalGroup(
            vistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );

        jMenu1.setText("Temas");

        jMenuItem1.setText("Registrar Tema");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Consultar Temas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Cursos");

        jMenuItem3.setText("Registrar Curso");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Consultar Cursos");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Usuarios");

        jMenuItem5.setText("Registrar Usuario");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Consultar Usuarios");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Reactivos");
        jMenu4.setToolTipText("");

        jMenuItem7.setText("Registrar Reactivo");
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Consultar Reactivos");
        jMenu4.add(jMenuItem8);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Examenes");

        jMenuItem9.setText("Registrar Examen");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem9);

        jMenuItem10.setText("Consultar Exámenes");
        jMenu5.add(jMenuItem10);
        jMenu5.add(jSeparator2);

        jMenuItem11.setText("Asignar Examen");
        jMenu5.add(jMenuItem11);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("Grupos");

        jMenuItem12.setText("Registrar Grupo");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem13.setText("Consultar Grupos");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Contestar Examen");
        jMenuBar1.add(jMenu7);

        jMenu11.setText("Calificaciones");

        jMenuItem15.setText("Consultar Calificaciones");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem15);

        jMenuBar1.add(jMenu11);

        jMenu12.setText("Estadísticas");

        jMenuItem14.setText("Generar Estadísticas");
        jMenu12.add(jMenuItem14);

        jMenuBar1.add(jMenu12);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        //Mostrar vistaRegistrarTema
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarTema.toString());
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        //Mostrar vistaConsultarTemas
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarTemas.toString());
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            //Mostrar vistaRegistrarExamen (Solo era una prueba)
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarExamen.toString());
        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
         JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            //Mostrar vistaRegistrarCurso (Solo era una prueba)
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarCurso.toString());
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        //Mostrar vistaConsultarCursos
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarCursos.toString());
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        //Mostrar vistaRegistrarGrupo
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarGrupo.toString());
        }
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
         //Mostrar vistaConsultarGrupo
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarGrupo.toString());
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
         //Mostrar vistaConsultarCalificaciones
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarCalificaciones
                    .toString());
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //Aqui se ponia el security manager.... stuuppidd
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPanel vistas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //Muestra la vista modificar necesaria y le envia el objeto entidad.
        manejadorVista.show(vistas, vista.toString());
        
        //Obtener la vista que se acabo de mostrar.
        JPanel vistaModificar = getVistaActual();
        //Enviarle el objeto por medio de la interfaz.
        ((InterfazVista)vistaModificar).mostrarEntidad(entidad);
    }

    @Override
    public void mostrarVista(Vista vista) {
        //Muestra la vista consultar necesaria
        manejadorVista.show(vistas, vista.toString());
    }

    @Override
    public void mostrarEntidad(Object entidad) {
        //No implementado
    }

    @Override
    public boolean confirmarCambio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void limpiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
