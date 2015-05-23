/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVAsignarExamenes;
import vista.controlador.CVConsultarCalificaciones;
import vista.controlador.CVContestarExamen;
import vista.controlador.CVGenerarEstadisticas;
import vista.controlador.CVMantenerCursos;
import vista.controlador.CVMantenerExamenes;
import vista.controlador.CVMantenerGrupos;
import vista.controlador.CVMantenerReactivos;
import vista.controlador.CVMantenerTemas;
import vista.controlador.CVMantenerUsuarios;
import vista.interfaz.InterfaceVista;

/**
 *
 * @author Jesus Donaldo
 */
public class FrmPrincipal extends javax.swing.JFrame implements InterfaceVista,
        ActionListener {

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
    private VistaAsignarExamenes vistaAsignarExamenes;
    private VistaRegistrarCurso vistaRegistrarCurso;
    private VistaConsultarCursos vistaConsultarCursos;
    private VistaModificarCurso vistaModificarCurso;
    private VistaRegistrarGrupo vistaRegistrarGrupo;
    private VistaConsultarGrupo vistaConsultarGrupo;
    private VistaModificarGrupo vistaModificarGrupo;
    private VistaConsultarCalificaciones vistaConsultarCalificaciones;
    private VistaConsultarCalificacionesExamen vistaConsultarCalificacionesExamen;
    private VistaRegistrarUsuario vistaRegistrarUsuario;
    private VistaConsultarUsuarios vistaConsultarUsuarios;
    private VistaModificarUsuario vistaModificarUsuario;
    private VistaRegistrarReactivo vistaRegistrarReactivo;
    private VistaConsultarReactivos vistaConsultarReactivos;
    private VistaModificarReactivo vistaModificarReactivo;
    private VistaGenerarEstadisticas vistaGenerarEstadisticas;
    private VistaConsultarExamenAsignado vistaBuscarExamenAsignado;
    private VistaContestarExamen vistaContestarExamen;
    
    /**
     * Creates new form Principal
     */
    public FrmPrincipal() {
        initComponents();
        init();
        //manejadorVista.last(vistas);
        setTitle("MatExamenes");
        
        //Agregar los listeners para los menus
        miRegistrarTema.addActionListener(this);
        miConsultarTemas.addActionListener(this);
        
        miRegistrarCurso.addActionListener(this);
        miConsultarCursos.addActionListener(this);
        
        miRegistrarUsuario.addActionListener(this);
        miConsultarUsuarios.addActionListener(this);
        
        miRegistrarReactivo.addActionListener(this);
        miConsultarReactivos.addActionListener(this);
        
        miRegistrarGrupo.addActionListener(this);
        miConsultarGrupos.addActionListener(this);
        
        miRegistrarExamen.addActionListener(this);
        miConsultarExamenes.addActionListener(this);
        miAsignarExamenes.addActionListener(this);
        miContestarExamen.addActionListener(this);
        
        miConsultarCalificaciones.addActionListener(this);
        miGenerarEstadisticas.addActionListener(this);
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
        initVistas();
        initControladores();        
        agregarPadres();
        agregarAPrincipal();
    }
    
    private void initVistas() {
        //Crear vistas
        vistaHome = new VistaHOME();
        vistaHome.setName(Vista.HOME.toString());
        
        vistaRegistrarTema = new VistaRegistrarTema();
        vistaRegistrarTema.setName(Vista.RegistrarTema.toString());
        vistaConsultarTemas = new VistaConsultarTemas();
        vistaConsultarTemas.setName(Vista.ConsultarTemas.toString());
        vistaModificarTema = new VistaModificarTema();
        vistaModificarTema.setName(Vista.ModificarTema.toString());
        
        vistaRegistrarCurso = new VistaRegistrarCurso();
        vistaRegistrarCurso.setName(Vista.RegistrarCurso.toString());
        vistaConsultarCursos = new VistaConsultarCursos();
        vistaConsultarCursos.setName(Vista.ConsultarCursos.toString());
        vistaModificarCurso = new VistaModificarCurso();
        vistaModificarCurso.setName(Vista.ModificarCurso.toString());
        
        vistaRegistrarExamen = new VistaRegistrarExamen();
        vistaRegistrarExamen.setName(Vista.RegistrarExamen.toString());
        vistaConsultarExamenes = new VistaConsultarExamenes();
        vistaConsultarExamenes.setName(Vista.ConsultarExamenes.toString());
        vistaModificarExamen = new VistaModificarExamen();
        vistaModificarExamen.setName(Vista.ModificarExamen.toString());
        vistaAsignarExamenes = new VistaAsignarExamenes();
        vistaAsignarExamenes.setName(Vista.AsignarExamenes.toString());
        vistaBuscarExamenAsignado = new VistaConsultarExamenAsignado();
        vistaBuscarExamenAsignado.setName(Vista.BuscarExamenAsignado.toString());
        vistaContestarExamen = new VistaContestarExamen();
        vistaContestarExamen.setName(Vista.ContestarExamen.toString());
        
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
        vistaModificarUsuario = new VistaModificarUsuario();
        vistaModificarUsuario.setName(Vista.ModificarUsuario.toString());
        
        vistaRegistrarReactivo = new VistaRegistrarReactivo();
        vistaRegistrarReactivo.setName(Vista.RegistrarReactivo.toString());
        vistaConsultarReactivos = new VistaConsultarReactivos();
        vistaConsultarReactivos.setName(Vista.ConsultarReactivos.toString());
        vistaModificarReactivo = new VistaModificarReactivo();
        vistaModificarReactivo.setName(Vista.ModificarReactivo.toString());
        
        vistaGenerarEstadisticas = new VistaGenerarEstadisticas();
        vistaGenerarEstadisticas.setName(Vista.GenerarEstadisticas.toString());
    }
    
    private void initControladores() {
        //Crear controladores vistas
        CVMantenerTemas cvMantenerTemas = new CVMantenerTemas();
        CVMantenerCursos cvMantenerCursos = new CVMantenerCursos();
        CVMantenerGrupos cvMantenerGrupos = new CVMantenerGrupos();
        CVConsultarCalificaciones cvConsultarCalificaciones =
                new CVConsultarCalificaciones();
        CVMantenerUsuarios cvMantenerUsuarios = new CVMantenerUsuarios();
        CVMantenerReactivos cvMantenerReactivos = new CVMantenerReactivos();
        CVMantenerExamenes cvMantenerExamenes = new CVMantenerExamenes();
        CVAsignarExamenes cvAsignarExamenes = new CVAsignarExamenes();
        CVGenerarEstadisticas cvGenerarEstadisticas = new CVGenerarEstadisticas();
        CVContestarExamen cvContestarExamen = new CVContestarExamen();
        
        //Asignar controladores a vistas
        vistaRegistrarTema.setControlador(cvMantenerTemas);
        vistaModificarTema.setControlador(cvMantenerTemas);
        vistaConsultarTemas.setControlador(cvMantenerTemas);
        
        vistaRegistrarCurso.setControlador(cvMantenerCursos);
        vistaModificarCurso.setControlador(cvMantenerCursos);
        vistaConsultarCursos.setControlador(cvMantenerCursos);
        
        vistaRegistrarGrupo.setControlador(cvMantenerGrupos);
        vistaConsultarGrupo.setControlador(cvMantenerGrupos);
        vistaModificarGrupo.setControlador(cvMantenerGrupos);
        
        vistaConsultarCalificaciones.setControlador(cvConsultarCalificaciones);
        vistaConsultarCalificacionesExamen
                .setControlador(cvConsultarCalificaciones);
        
        vistaRegistrarUsuario.setControlador(cvMantenerUsuarios);
        vistaConsultarUsuarios.setControlador(cvMantenerUsuarios);
        vistaModificarUsuario.setControlador(cvMantenerUsuarios);
        
        vistaRegistrarReactivo.setControlador(cvMantenerReactivos);
        vistaConsultarReactivos.setControlador(cvMantenerReactivos);
        vistaModificarReactivo.setControlador(cvMantenerReactivos);
        
        vistaRegistrarExamen.setControlador(cvMantenerExamenes);
        vistaConsultarExamenes.setControlador(cvMantenerExamenes);
        vistaModificarExamen.setControlador(cvMantenerExamenes);
        vistaAsignarExamenes.setControlador(cvAsignarExamenes);
        vistaBuscarExamenAsignado.setControlador(cvContestarExamen);
        vistaContestarExamen.setControlador(cvContestarExamen);
        
        vistaGenerarEstadisticas.setControlador(cvGenerarEstadisticas);
    }
    
    private void agregarPadres() {
        //Asignar padre a vistas
        vistaRegistrarTema.setPadre(this);
        vistaConsultarTemas.setPadre(this);
        vistaModificarTema.setPadre(this);
        
        vistaRegistrarCurso.setPadre(this);
        vistaConsultarCursos.setPadre(this);
        vistaModificarCurso.setPadre(this);
        
        vistaRegistrarGrupo.setPadre(this);
        vistaConsultarGrupo.setPadre(this);
        vistaModificarGrupo.setPadre(this);
        
        vistaRegistrarExamen.setPadre(this);
        vistaConsultarExamenes.setPadre(this);
        vistaModificarExamen.setPadre(this);
        vistaAsignarExamenes.setPadre(this);
        vistaBuscarExamenAsignado.setPadre(this);
        vistaContestarExamen.setPadre(this);
        
        vistaConsultarCalificaciones.setPadre(this);
        vistaConsultarCalificacionesExamen.setPadre(this);
        
        vistaRegistrarUsuario.setPadre(this);
        vistaConsultarUsuarios.setPadre(this);
        vistaModificarUsuario.setPadre(this);
        
        vistaRegistrarReactivo.setPadre(this);
        vistaConsultarReactivos.setPadre(this);
        vistaModificarReactivo.setPadre(this);

        vistaGenerarEstadisticas.setPadre(this);
    }
    
    private void agregarAPrincipal() {
        //Agregar un panel y su identificador. Para agregar mas identificadores
        //ir a vista.interfaz.InterfazVista y agregarlos al enum Vista
        vistas.add(vistaHome, Vista.HOME.toString());
        
        vistas.add(vistaRegistrarTema, Vista.RegistrarTema.toString());
        vistas.add(vistaConsultarTemas, Vista.ConsultarTemas.toString());
        vistas.add(vistaModificarTema, Vista.ModificarTema.toString());
        
        vistas.add(vistaRegistrarCurso, Vista.RegistrarCurso.toString());
        vistas.add(vistaConsultarCursos, Vista.ConsultarCursos.toString());
        vistas.add(vistaModificarCurso, Vista.ModificarCurso.toString());
        
        vistas.add(vistaRegistrarGrupo, Vista.RegistrarGrupo.toString());
        vistas.add(vistaConsultarGrupo, Vista.ConsultarGrupo.toString());
        vistas.add(vistaModificarGrupo, Vista.ModificarGrupo.toString());
        
        vistas.add(vistaRegistrarExamen, Vista.RegistrarExamen.toString());
        vistas.add(vistaConsultarExamenes, Vista.ConsultarExamenes.toString());
        vistas.add(vistaModificarExamen, Vista.ModificarExamen.toString());
        vistas.add(vistaAsignarExamenes, Vista.AsignarExamenes.toString());
        vistas.add(vistaBuscarExamenAsignado, Vista.BuscarExamenAsignado.toString());
        vistas.add(vistaContestarExamen, Vista.ContestarExamen.toString());
        
        vistas.add(vistaConsultarCalificaciones, Vista.ConsultarCalificaciones
                .toString());
        vistas.add(vistaConsultarCalificacionesExamen, Vista
                .ConsultarCalificacionesExamen.toString());
        
        vistas.add(vistaRegistrarUsuario, Vista.RegistrarUsuario.toString());
        vistas.add(vistaConsultarUsuarios, Vista.ConsultarUsuarios.toString());
        vistas.add(vistaModificarUsuario, Vista.ModificarUsuario.toString());
        
        vistas.add(vistaRegistrarReactivo, Vista.RegistrarReactivo.toString());
        vistas.add(vistaConsultarReactivos, Vista.ConsultarReactivos.toString());
        vistas.add(vistaModificarReactivo, Vista.ModificarReactivo.toString());
        
        vistas.add(vistaGenerarEstadisticas, Vista.GenerarEstadisticas.toString());
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
        mbPrincipal = new javax.swing.JMenuBar();
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
        miAsignarExamenes = new javax.swing.JMenuItem();
        mGrupos = new javax.swing.JMenu();
        miRegistrarGrupo = new javax.swing.JMenuItem();
        miConsultarGrupos = new javax.swing.JMenuItem();
        mContestarExamen = new javax.swing.JMenu();
        miContestarExamen = new javax.swing.JMenuItem();
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

        miRegistrarTema.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarTema.setText("Registrar Tema");
        miRegistrarTema.setName("RegistrarTema"); // NOI18N
        mTemas.add(miRegistrarTema);

        miConsultarTemas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarTemas.setText("Consultar Temas");
        miConsultarTemas.setName("ConsultarTemas"); // NOI18N
        mTemas.add(miConsultarTemas);

        mbPrincipal.add(mTemas);

        mCursos.setText("Cursos");

        miRegistrarCurso.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarCurso.setText("Registrar Curso");
        miRegistrarCurso.setName("RegistrarCurso"); // NOI18N
        mCursos.add(miRegistrarCurso);

        miConsultarCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarCursos.setText("Consultar Cursos");
        miConsultarCursos.setName("ConsultarCursos"); // NOI18N
        mCursos.add(miConsultarCursos);

        mbPrincipal.add(mCursos);

        mUsuarios.setText("Usuarios");

        miRegistrarUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarUsuario.setText("Registrar Usuario");
        miRegistrarUsuario.setName("RegistrarUsuario"); // NOI18N
        mUsuarios.add(miRegistrarUsuario);

        miConsultarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarUsuarios.setText("Consultar Usuarios");
        miConsultarUsuarios.setName("ConsultarUsuarios"); // NOI18N
        mUsuarios.add(miConsultarUsuarios);

        mbPrincipal.add(mUsuarios);

        mReactivos.setText("Reactivos");
        mReactivos.setToolTipText("");

        miRegistrarReactivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarReactivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarReactivo.setText("Registrar Reactivo");
        miRegistrarReactivo.setName("RegistrarReactivo"); // NOI18N
        mReactivos.add(miRegistrarReactivo);

        miConsultarReactivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarReactivos.setText("Consultar Reactivos");
        miConsultarReactivos.setName("ConsultarReactivos"); // NOI18N
        mReactivos.add(miConsultarReactivos);

        mbPrincipal.add(mReactivos);

        mExamenes.setText("Examenes");

        miRegistrarExamen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarExamen.setText("Registrar Examen");
        miRegistrarExamen.setName("RegistrarExamen"); // NOI18N
        mExamenes.add(miRegistrarExamen);

        miConsultarExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarExamenes.setText("Consultar Exámenes");
        miConsultarExamenes.setName("ConsultarExamenes"); // NOI18N
        mExamenes.add(miConsultarExamenes);
        mExamenes.add(jSeparator2);

        miAsignarExamenes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        miAsignarExamenes.setText("Asignar Examenes");
        miAsignarExamenes.setName("AsignarExamenes");
        mExamenes.add(miAsignarExamenes);

        mbPrincipal.add(mExamenes);

        mGrupos.setText("Grupos");

        miRegistrarGrupo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        miRegistrarGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo24.png"))); // NOI18N
        miRegistrarGrupo.setText("Registrar Grupo");
        miRegistrarGrupo.setName("RegistrarGrupo"); // NOI18N
        mGrupos.add(miRegistrarGrupo);

        miConsultarGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consulta24.png"))); // NOI18N
        miConsultarGrupos.setText("Consultar Grupos");
        miConsultarGrupos.setName("ConsultarGrupo"); // NOI18N
        mGrupos.add(miConsultarGrupos);

        mbPrincipal.add(mGrupos);

        mContestarExamen.setText("Contestar Examen");

        miContestarExamen.setText("Contestar Examen");
        miContestarExamen.setName("BuscarExamenAsignado"); // NOI18N
        mContestarExamen.add(miContestarExamen);

        mbPrincipal.add(mContestarExamen);

        mCalificaciones.setText("Calificaciones");

        miConsultarCalificaciones.setText("Consultar Calificaciones");
        miConsultarCalificaciones.setName("ConsultarCalificaciones"); // NOI18N
        mCalificaciones.add(miConsultarCalificaciones);

        mbPrincipal.add(mCalificaciones);

        mEstadisticas.setText("Estadísticas");

        miGenerarEstadisticas.setText("Generar Estadísticas");
        miGenerarEstadisticas.setName("GenerarEstadisticas"); // NOI18N
        mEstadisticas.add(miGenerarEstadisticas);

        mbPrincipal.add(mEstadisticas);

        setJMenuBar(mbPrincipal);

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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void setVistaAlumno() {
        ocultarTodos();
        mContestarExamen.setVisible(true);
    }
    
    public void setVistaMaestro() {
        ocultarTodos();
        mCalificaciones.setVisible(true);
        mExamenes.setVisible(true);
        mReactivos.setVisible(true);
        vistaConsultarReactivos.deshabilitarBtnEliminar();
    }
    /**
     * 
     */
    public void setVistaAdmin() {
       //no se oculta ninguna opcion del menu 
    }
    
    private void ocultarTodos(){
        mCalificaciones.setVisible(false);
        mContestarExamen.setVisible(false);
        mCursos.setVisible(false);
        mEstadisticas.setVisible(false);
        mExamenes.setVisible(false);
        mGrupos.setVisible(false);
        mReactivos.setVisible(false);
        mTemas.setVisible(false);
        mUsuarios.setVisible(false);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu9;
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
    private javax.swing.JMenuBar mbPrincipal;
    private javax.swing.JMenuItem miAsignarExamenes;
    private javax.swing.JMenuItem miConsultarCalificaciones;
    private javax.swing.JMenuItem miConsultarCursos;
    private javax.swing.JMenuItem miConsultarExamenes;
    private javax.swing.JMenuItem miConsultarGrupos;
    private javax.swing.JMenuItem miConsultarReactivos;
    private javax.swing.JMenuItem miConsultarTemas;
    private javax.swing.JMenuItem miConsultarUsuarios;
    private javax.swing.JMenuItem miContestarExamen;
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
        ((InterfaceVista)vistaModificar).mostrarEntidad(entidad);
    }

    @Override
    public void mostrarVista(Vista vista) {
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
        return this.usuarioActual;
    }

    @Override
    public void limpiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Para cuando se haga click en un menu item
        JPanel actual = getVistaActual();
        boolean ok = true;
        
        if(actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista)actual).confirmarCambio();
        }
        
        if(ok) {
            ((InterfaceVista)actual).limpiar();
            manejadorVista.show(vistas, ((JMenuItem)e.getSource()).getName());
        }
    }
}
