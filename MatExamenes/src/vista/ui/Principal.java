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
import vista.controlador.CVMantenerUsuarios;
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
    private VistaRegistrarExamen vistaRegistrarExamen;
    private VistaConsultarExamenes vistaConsultarExamenes;
    private VistaModificarExamen vistaModificarExamen;
    private VistaRegistrarCurso vistaRegistrarCurso;
    private VistaConsultarCursos vistaConsultarCursos;
    private VistaRegistrarGrupo vistaRegistrarGrupo;
    private VistaConsultarGrupo vistaConsultarGrupo;
    private VistaModificarGrupo vistaModificarGrupo;
    private VistaConsultarCalificaciones vistaConsultarCalificaciones;
    private VistaConsultarCalificacionesExamen vistaConsultarCalificacionesExamen;
    private VistaRegistrarUsuario vistaRegistrarUsuario;
    private VistaConsultarUsuarios vistaConsultarUsuarios;
    
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
        vistaRegistrarExamen = new VistaRegistrarExamen();
        vistaRegistrarExamen.setName(Vista.RegistrarExamen.toString());
        vistaConsultarExamenes = new VistaConsultarExamenes();
        vistaConsultarExamenes.setName(Vista.ConsultarExamenes.toString());
        vistaModificarExamen = new VistaModificarExamen();
        vistaModificarExamen.setName(Vista.ModificarExamen.toString());
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
        vistaConsultarCalificaciones.setName(Vista
                .ConsultarCalificaciones.toString());
        vistaConsultarCalificacionesExamen = 
                new VistaConsultarCalificacionesExamen();
        vistaConsultarCalificacionesExamen.setName(Vista
                .ConsultarCalificacionesExamen.toString());
        vistaRegistrarUsuario = new VistaRegistrarUsuario();
        vistaRegistrarUsuario.setName(Vista.RegistrarUsuario.toString());
        vistaConsultarUsuarios = new VistaConsultarUsuarios();
        vistaConsultarUsuarios.setName(Vista.ConsultarUsuarios.toString());
        
        //Crear controladores vistas
        CVMantenerTemas cvMantenerTemas = new CVMantenerTemas();
        CVMantenerGrupos cvMantenerGrupos = new CVMantenerGrupos();
        CVConsultarCalificaciones cvConsultarCalificaciones =
                new CVConsultarCalificaciones();
        CVMantenerUsuarios cvMantenerUsuarios = new CVMantenerUsuarios();
        
        
        //Asignar controladores a vistas
        vistaRegistrarTema.setControlador(cvMantenerTemas);
        vistaModificarTema.setControlador(cvMantenerTemas);
        vistaConsultarTemas.setControlador(cvMantenerTemas);
        vistaRegistrarGrupo.setControlador(cvMantenerGrupos);
        vistaConsultarGrupo.setControlador(cvMantenerGrupos);
        vistaModificarGrupo.setControlador(cvMantenerGrupos);
        vistaConsultarCalificaciones.setControlador(cvConsultarCalificaciones);
        vistaConsultarCalificacionesExamen
                .setControlador(cvConsultarCalificaciones);
        vistaRegistrarUsuario.setControlador(cvMantenerUsuarios);
        vistaConsultarUsuarios.setControlador(cvMantenerUsuarios);
        
        //Asignar padre a vistas
        vistaRegistrarTema.setPadre(this);
        vistaModificarTema.setPadre(this);
        vistaConsultarTemas.setPadre(this);
        vistaConsultarCursos.setPadre(this);
        vistaRegistrarGrupo.setPadre(this);
        vistaConsultarGrupo.setPadre(this);
        vistaModificarGrupo.setPadre(this);
        vistaRegistrarExamen.setPadre(this);
        vistaConsultarExamenes.setPadre(this);
        vistaModificarExamen.setPadre(this);
        vistaConsultarCalificaciones.setPadre(this);
        vistaConsultarCalificacionesExamen.setPadre(this);
        vistaRegistrarUsuario.setPadre(this);
        vistaConsultarUsuarios.setPadre(this);
        
        //Agregar un panel y su identificador. Para agregar mas identificadores
        //ir a vista.interfaz.InterfazVista y agregarlos al enum Vista
        vistas.add(vistaHome, Vista.HOME.toString());
        vistas.add(vistaRegistrarTema, Vista.RegistrarTema.toString());
        vistas.add(vistaConsultarTemas, Vista.ConsultarTemas.toString());
        vistas.add(vistaModificarTema, Vista.ModificarTema.toString());
        vistas.add(vistaRegistrarCurso, Vista.RegistrarCurso.toString());
        vistas.add(vistaConsultarCursos, Vista.ConsultarCursos.toString());
        vistas.add(vistaRegistrarGrupo, Vista.RegistrarGrupo.toString());
        vistas.add(vistaConsultarGrupo, Vista.ConsultarGrupo.toString());
        vistas.add(vistaModificarGrupo, Vista.ModificarGrupo.toString());
        vistas.add(vistaRegistrarExamen, Vista.RegistrarExamen.toString());
        vistas.add(vistaConsultarExamenes, Vista.ConsultarExamenes.toString());
        vistas.add(vistaModificarExamen, Vista.ModificarExamen.toString());
        vistas.add(vistaConsultarCalificaciones, Vista.ConsultarCalificaciones
                .toString());
        vistas.add(vistaConsultarCalificacionesExamen, Vista
                .ConsultarCalificacionesExamen.toString());
        vistas.add(vistaRegistrarUsuario, Vista.RegistrarUsuario.toString());
        vistas.add(vistaConsultarUsuarios, Vista.ConsultarUsuarios.toString());
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
        mTemas = new javax.swing.JMenu();
        miRegistrarTema = new javax.swing.JMenuItem();
        miConsultarTemas = new javax.swing.JMenuItem();
        mCursos = new javax.swing.JMenu();
        miRegistrarCurso = new javax.swing.JMenuItem();
        miConsultarCursos = new javax.swing.JMenuItem();
        mUsuarios = new javax.swing.JMenu();
        miRegistrarUsuario = new javax.swing.JMenuItem();
        miConsultarUsuarios = new javax.swing.JMenuItem();
        mReactivos = new javax.swing.JMenu();
        miRegistrarReactivo = new javax.swing.JMenuItem();
        miConsultarReactivos = new javax.swing.JMenuItem();
        mExamenes = new javax.swing.JMenu();
        miRegistrarExamen = new javax.swing.JMenuItem();
        miConsultarExamenes = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        miAsignarExamen = new javax.swing.JMenuItem();
        mGrupos = new javax.swing.JMenu();
        miRegistrarGrupo = new javax.swing.JMenuItem();
        miConsultarGrupos = new javax.swing.JMenuItem();
        mContestarExamen = new javax.swing.JMenu();
        mCalificaciones = new javax.swing.JMenu();
        miConsultarCalificaciones = new javax.swing.JMenuItem();
        mEstadisticas = new javax.swing.JMenu();
        miGenerarEstadisticas = new javax.swing.JMenuItem();

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

        mTemas.setText("Temas");

        miRegistrarTema.setText("Registrar Tema");
        miRegistrarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarTemaActionPerformed(evt);
            }
        });
        mTemas.add(miRegistrarTema);

        miConsultarTemas.setText("Consultar Temas");
        miConsultarTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarTemasActionPerformed(evt);
            }
        });
        mTemas.add(miConsultarTemas);

        jMenuBar1.add(mTemas);

        mCursos.setText("Cursos");

        miRegistrarCurso.setText("Registrar Curso");
        miRegistrarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarCursoActionPerformed(evt);
            }
        });
        mCursos.add(miRegistrarCurso);

        miConsultarCursos.setText("Consultar Cursos");
        miConsultarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarCursosActionPerformed(evt);
            }
        });
        mCursos.add(miConsultarCursos);

        jMenuBar1.add(mCursos);

        mUsuarios.setText("Usuarios");

        miRegistrarUsuario.setText("Registrar Usuario");
        miRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarUsuarioActionPerformed(evt);
            }
        });
        mUsuarios.add(miRegistrarUsuario);

        miConsultarUsuarios.setText("Consultar Usuarios");
        miConsultarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarUsuariosActionPerformed(evt);
            }
        });
        mUsuarios.add(miConsultarUsuarios);

        jMenuBar1.add(mUsuarios);

        mReactivos.setText("Reactivos");
        mReactivos.setToolTipText("");

        miRegistrarReactivo.setText("Registrar Reactivo");
        mReactivos.add(miRegistrarReactivo);

        miConsultarReactivos.setText("Consultar Reactivos");
        mReactivos.add(miConsultarReactivos);

        jMenuBar1.add(mReactivos);

        mExamenes.setText("Examenes");

        miRegistrarExamen.setText("Registrar Examen");
        miRegistrarExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarExamenActionPerformed(evt);
            }
        });
        mExamenes.add(miRegistrarExamen);

        miConsultarExamenes.setText("Consultar Exámenes");
        miConsultarExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarExamenesActionPerformed(evt);
            }
        });
        mExamenes.add(miConsultarExamenes);
        mExamenes.add(jSeparator2);

        miAsignarExamen.setText("Asignar Examen");
        mExamenes.add(miAsignarExamen);

        jMenuBar1.add(mExamenes);

        mGrupos.setText("Grupos");

        miRegistrarGrupo.setText("Registrar Grupo");
        miRegistrarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miRegistrarGrupoActionPerformed(evt);
            }
        });
        mGrupos.add(miRegistrarGrupo);

        miConsultarGrupos.setText("Consultar Grupos");
        miConsultarGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarGruposActionPerformed(evt);
            }
        });
        mGrupos.add(miConsultarGrupos);

        jMenuBar1.add(mGrupos);

        mContestarExamen.setText("Contestar Examen");
        jMenuBar1.add(mContestarExamen);

        mCalificaciones.setText("Calificaciones");

        miConsultarCalificaciones.setText("Consultar Calificaciones");
        miConsultarCalificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConsultarCalificacionesActionPerformed(evt);
            }
        });
        mCalificaciones.add(miConsultarCalificaciones);

        jMenuBar1.add(mCalificaciones);

        mEstadisticas.setText("Estadísticas");

        miGenerarEstadisticas.setText("Generar Estadísticas");
        mEstadisticas.add(miGenerarEstadisticas);

        jMenuBar1.add(mEstadisticas);

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

    private void miRegistrarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarTemaActionPerformed
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
    }//GEN-LAST:event_miRegistrarTemaActionPerformed

    private void miConsultarTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarTemasActionPerformed
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
    }//GEN-LAST:event_miConsultarTemasActionPerformed

    private void miRegistrarExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarExamenActionPerformed
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
    }//GEN-LAST:event_miRegistrarExamenActionPerformed

    private void miRegistrarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarCursoActionPerformed
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
    }//GEN-LAST:event_miRegistrarCursoActionPerformed

    private void miConsultarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarCursosActionPerformed
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
    }//GEN-LAST:event_miConsultarCursosActionPerformed

    private void miRegistrarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarGrupoActionPerformed
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
    }//GEN-LAST:event_miRegistrarGrupoActionPerformed

    private void miConsultarGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarGruposActionPerformed
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
    }//GEN-LAST:event_miConsultarGruposActionPerformed

    private void miConsultarCalificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarCalificacionesActionPerformed
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
    }//GEN-LAST:event_miConsultarCalificacionesActionPerformed

    private void miConsultarExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarExamenesActionPerformed
        //Mostrar vistaConsultarExamenes
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarExamenes
                    .toString());
        }
    }//GEN-LAST:event_miConsultarExamenesActionPerformed

    private void miRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miRegistrarUsuarioActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarUsuario.toString());
        }
    }//GEN-LAST:event_miRegistrarUsuarioActionPerformed

    private void miConsultarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConsultarUsuariosActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfazVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfazVista)actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarUsuarios.toString());
        }
    }//GEN-LAST:event_miConsultarUsuariosActionPerformed

    public void setVistaAlumno() {
        //para ocultar los menus
    }
    
    public void setVistaMaestro() {
        //para ocultar los menus
    }

    public void setVistaAdmin() {
        //para igual
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mCalificaciones;
    private javax.swing.JMenu mContestarExamen;
    private javax.swing.JMenu mCursos;
    private javax.swing.JMenu mEstadisticas;
    private javax.swing.JMenu mExamenes;
    private javax.swing.JMenu mGrupos;
    private javax.swing.JMenu mReactivos;
    private javax.swing.JMenu mTemas;
    private javax.swing.JMenu mUsuarios;
    private javax.swing.JMenuItem miAsignarExamen;
    private javax.swing.JMenuItem miConsultarCalificaciones;
    private javax.swing.JMenuItem miConsultarCursos;
    private javax.swing.JMenuItem miConsultarExamenes;
    private javax.swing.JMenuItem miConsultarGrupos;
    private javax.swing.JMenuItem miConsultarReactivos;
    private javax.swing.JMenuItem miConsultarTemas;
    private javax.swing.JMenuItem miConsultarUsuarios;
    private javax.swing.JMenuItem miGenerarEstadisticas;
    private javax.swing.JMenuItem miRegistrarCurso;
    private javax.swing.JMenuItem miRegistrarExamen;
    private javax.swing.JMenuItem miRegistrarGrupo;
    private javax.swing.JMenuItem miRegistrarReactivo;
    private javax.swing.JMenuItem miRegistrarTema;
    private javax.swing.JMenuItem miRegistrarUsuario;
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
