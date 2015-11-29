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

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
 * JFrame que mostrará la interfaz gráfica de la interfaz Principal
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class FrmPrincipal extends javax.swing.JFrame implements InterfaceVista,
        ActionListener, WindowListener {

    /**
     * Usuario que inició sesión
     */
    private UsuarioDTO usuarioActual;

    /**
     * Layout para manipular las vistas
     */
    private final CardLayout manejadorVista = new CardLayout();
    /**
     * Vista HOME
     */
    private VistaHOME vistaHome;
    /**
     * Vista de Registrar Tema
     */
    private VistaRegistrarTema vistaRegistrarTema;
    /**
     * Vista de Consultar Temas
     */
    private VistaConsultarTemas vistaConsultarTemas;
    /**
     * Vista de Modificar Tema
     */
    private VistaModificarTema vistaModificarTema;
    /**
     * Vista de Generar Examen
     */
    private VistaGenerarExamen vistaGenerarExamen;
    /**
     * Vista de Consultar Exámenes
     */
    private VistaConsultarExamenes vistaConsultarExamenes;
    /**
     * Vista de Modificar Examen
     */
    private VistaModificarExamen vistaModificarExamen;
    /**
     * Vista de Asignar Exámenes
     */
    private VistaAsignarExamenes vistaAsignarExamenes;
    /**
     * Vista de Registrar Curso
     */
    private VistaRegistrarCurso vistaRegistrarCurso;
    /**
     * Vista de Consultar Cursos
     */
    private VistaConsultarCursos vistaConsultarCursos;
    /**
     * Vista de Modificar Curso
     */
    private VistaModificarCurso vistaModificarCurso;
    /**
     * Vista de Registrar Grupo
     */
    private VistaRegistrarGrupo vistaRegistrarGrupo;
    /**
     * Vista de Consultar Grupos
     */
    private VistaConsultarGrupo vistaConsultarGrupo;
    /**
     * Vista de Modificar Grupo
     */
    private VistaModificarGrupo vistaModificarGrupo;
    /**
     * Vista de Consultar Calificaciones
     */
    private VistaConsultarCalificaciones vistaConsultarCalificaciones;
    /**
     * Vista de Registrar Usuario
     */
    private VistaRegistrarUsuario vistaRegistrarUsuario;
    /**
     * Vista de Consultar Usuarios
     */
    private VistaConsultarUsuarios vistaConsultarUsuarios;
    /**
     * Vista de Modificar Usuario
     */
    private VistaModificarUsuario vistaModificarUsuario;
    /**
     * Vista de Registrar Reactivo
     */
    private VistaRegistrarReactivo vistaRegistrarReactivo;
    /**
     * Vista de Consultar Reactivos
     */
    private VistaConsultarReactivos vistaConsultarReactivos;
    /**
     * Vista de Modificar Reactivo
     */
    private VistaModificarReactivo vistaModificarReactivo;
    /**
     * Vista de Generar Estadísticas
     */
    private VistaGenerarEstadisticas vistaGenerarEstadisticas;
    /**
     * Vista de Buscar Examen Asignado
     */
    private VistaConsultarExamenesAsignados vistaBuscarExamenAsignado;
    /**
     * Vista de Contestar Examen
     */
    private VistaContestarExamen vistaContestarExamen;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mCalificaciones;
    private javax.swing.JMenu mContestarExamenes;
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
    private javax.swing.JMenuItem miContestarExamenes;
    private javax.swing.JMenuItem miGenerarEstadisticas;
    private javax.swing.JMenuItem miRegistrarCurso;
    private javax.swing.JMenuItem miRegistrarExamen;
    private javax.swing.JMenuItem miRegistrarGrupo;
    private javax.swing.JMenuItem miRegistrarReactivo;
    private javax.swing.JMenuItem miRegistrarTema;
    private javax.swing.JMenuItem miRegistrarUsuario;
    private javax.swing.JPanel vistas;
    // End of variables declaration//GEN-END:variables

    /**
     * Crea un objeto FrmPrincipal e inicializa sus atributos, agrega los
     * listeners necesarios, asocia cada controlador de la vista a sus vistas
     * correspondientes y se crea una relación de padre-hijo con este frame
     */
    public FrmPrincipal() {
        initComponents();
        //Inicializa todo en este frame
        init();
        setTitle("MatExámenes");
        agregarIconos();
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
        miContestarExamenes.addActionListener(this);

        miConsultarCalificaciones.addActionListener(this);
        miGenerarEstadisticas.addActionListener(this);
    }

    /**
     * Para almacenar en la vista el objeto UsuarioDTO del usuario actual
     *
     * @param usuarioActual el objeto UsuarioDTO que representa al usuario que
     * inició sesión en el sistema
     */
    public void setUsuarioActual(UsuarioDTO usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /**
     * Agrega los iconos a la interfaz gráfica y a la barra de tareas.
     */
    private void agregarIconos() {
        List<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono16.png")).getImage());
        icons.add(new ImageIcon(getClass().getResource("/recursos/icono32.png")).getImage());
        this.setIconImages(icons);
    }

    /**
     * Para crear los paneles, los controladores de vista y las relaciones entre
     * esta vista y los paneles. Este método debe ser llamado al inicio en el
     * constructor
     */
    private void init() {
        //se deben agregar todos los paneles de vista a este panel
        vistas.setLayout(manejadorVista);
        //Inicialización de todo lo necesario para que la vista funcione
        initVistas();
        initControladores();
        agregarPadres();
        agregarAPrincipal();

        addWindowListener(this);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * Este método es utilizado para crear los objetos que serán las vistas de
     * cada caso de uso para poder ser mostradas en el Frame Principal.
     */
    private void initVistas() {
        //Crear vistas y asignarles un identificador de la enumeración Vista
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

        vistaGenerarExamen = new VistaGenerarExamen();
        vistaGenerarExamen.setName(Vista.GenerarExamen.toString());
        vistaConsultarExamenes = new VistaConsultarExamenes();
        vistaConsultarExamenes.setName(Vista.ConsultarExamenes.toString());
        vistaModificarExamen = new VistaModificarExamen();
        vistaModificarExamen.setName(Vista.ModificarExamen.toString());
        vistaAsignarExamenes = new VistaAsignarExamenes();
        vistaAsignarExamenes.setName(Vista.AsignarExamenes.toString());
        vistaBuscarExamenAsignado = new VistaConsultarExamenesAsignados();
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
        vistaConsultarCalificaciones.setName(Vista.ConsultarCalificaciones.toString());

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

    /**
     * Este método es utilizado para crear controladores de la vista y
     * asignarlos a diferentes vistas que compartan el mismo caso de uso.
     *
     */
    private void initControladores() {
        //Crear controladores vistas
        CVMantenerTemas cvMantenerTemas = new CVMantenerTemas();
        CVMantenerCursos cvMantenerCursos = new CVMantenerCursos();
        CVMantenerGrupos cvMantenerGrupos = new CVMantenerGrupos();
        CVConsultarCalificaciones cvConsultarCalificaciones
                = new CVConsultarCalificaciones();
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

        vistaRegistrarUsuario.setControlador(cvMantenerUsuarios);
        vistaConsultarUsuarios.setControlador(cvMantenerUsuarios);
        vistaModificarUsuario.setControlador(cvMantenerUsuarios);

        vistaRegistrarReactivo.setControlador(cvMantenerReactivos);
        vistaConsultarReactivos.setControlador(cvMantenerReactivos);
        vistaModificarReactivo.setControlador(cvMantenerReactivos);

        vistaGenerarExamen.setControlador(cvMantenerExamenes);
        vistaConsultarExamenes.setControlador(cvMantenerExamenes);
        vistaModificarExamen.setControlador(cvMantenerExamenes);
        vistaAsignarExamenes.setControlador(cvAsignarExamenes);
        vistaBuscarExamenAsignado.setControlador(cvContestarExamen);
        vistaContestarExamen.setControlador(cvContestarExamen);

        vistaGenerarEstadisticas.setControlador(cvGenerarEstadisticas);
    }

    /**
     * Este método sirve para crear la relación entre este Frame y las vistas
     * hijas, para poder así recibir mensajes de ellas.
     */
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

        vistaGenerarExamen.setPadre(this);
        vistaConsultarExamenes.setPadre(this);
        vistaModificarExamen.setPadre(this);
        vistaAsignarExamenes.setPadre(this);
        vistaBuscarExamenAsignado.setPadre(this);
        vistaContestarExamen.setPadre(this);

        vistaConsultarCalificaciones.setPadre(this);

        vistaRegistrarUsuario.setPadre(this);
        vistaConsultarUsuarios.setPadre(this);
        vistaModificarUsuario.setPadre(this);

        vistaRegistrarReactivo.setPadre(this);
        vistaConsultarReactivos.setPadre(this);
        vistaModificarReactivo.setPadre(this);

        vistaGenerarEstadisticas.setPadre(this);
    }

    /**
     * Este método sirve para agregar todas las vistas creadas al Frame
     * Principal por medio de un identificador que servirá para mostrar las
     * vistas más adelante.
     */
    private void agregarAPrincipal() {
        //Agregar un panel y su identificador.
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

        vistas.add(vistaGenerarExamen, Vista.GenerarExamen.toString());
        vistas.add(vistaConsultarExamenes, Vista.ConsultarExamenes.toString());
        vistas.add(vistaModificarExamen, Vista.ModificarExamen.toString());
        vistas.add(vistaAsignarExamenes, Vista.AsignarExamenes.toString());
        vistas.add(vistaBuscarExamenAsignado, Vista.BuscarExamenAsignado.toString());
        vistas.add(vistaContestarExamen, Vista.ContestarExamen.toString());

        vistas.add(vistaConsultarCalificaciones, Vista.ConsultarCalificaciones
                .toString());

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
     *
     * @return vista actual en forma de JPanel
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
     * Inicializa los atributos gráficos y los coloca en su posición.
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
        mContestarExamenes = new javax.swing.JMenu();
        miContestarExamenes = new javax.swing.JMenuItem();
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
        miRegistrarExamen.setText("Generar Examen");
        miRegistrarExamen.setName("GenerarExamen"); // NOI18N
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

        mContestarExamenes.setText("Contestar Exámenes");

        miContestarExamenes.setText("Contestar Exámenes");
        miContestarExamenes.setName("BuscarExamenAsignado"); // NOI18N
        mContestarExamenes.add(miContestarExamenes);

        mbPrincipal.add(mContestarExamenes);

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

    /**
     * Este método sirve para acomodar el Principal en base a los permisos que
     * tiene un usuario de tipo Alumno para realizar funciones en el sistema.
     * Este método es llamado desde el Login para preparar al sistema según las
     * funciones del usuario.
     */
    public void setVistaAlumno() {
        ocultarTodos();
        mContestarExamenes.setVisible(true);
    }

    /**
     * Este método sirve para acomodar el Principal en base a los permisos que
     * tiene un usuario de tipo Maestro para realizar funciones en el sistema.
     * Este método es llamado desde el Login para preparar al sistema según las
     * funciones del usuario.
     */
    public void setVistaMaestro() {
        ocultarTodos();
        mCalificaciones.setVisible(true);
        mExamenes.setVisible(true);
        mReactivos.setVisible(true);
        vistaConsultarReactivos.deshabilitarBtnEliminar();
    }

    /**
     * Este método sirve para acomodar el Principal en base a los permisos que
     * tiene un usuario de tipo Administrador para realizar funciones en el
     * sistema. Este método es llamado desde el Login para preparar al sistema
     * según las funciones del usuario.
     */
    public void setVistaAdmin() {
        mContestarExamenes.setVisible(false);
    }

    /**
     * Este método sirve para ocultar todos los menús del Principal y así hacer
     * más rápida la selección de funcionalidades por usuario.
     */
    private void ocultarTodos() {
        mCalificaciones.setVisible(false);
        mContestarExamenes.setVisible(false);
        mCursos.setVisible(false);
        mEstadisticas.setVisible(false);
        mExamenes.setVisible(false);
        mGrupos.setVisible(false);
        mReactivos.setVisible(false);
        mTemas.setVisible(false);
        mUsuarios.setVisible(false);
    }

    /**
     * Este método sirve para mostrar una vista con un objeto entidad enviados
     * desde otra vista en el Principal. Este método es utilizado como
     * intermediario de comunicación entre las vistas, ya que una vista puede
     * decidir la siguiente vista a mostrar junto con información que le pueda
     * enviar a través de este método.
     *
     * @param entidad el objeto que se desea enviar a la vista destino,
     * generalmente un dto.
     * @param vista el identificador de la vista la cual se quiere mostrar
     */
    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //Muestra la vista modificar necesaria y le envia el objeto entidad.
        manejadorVista.show(vistas, vista.toString());

        //Obtener la vista que se acabo de mostrar.
        JPanel vistaModificar = getVistaActual();
        //Enviarle el objeto por medio de la interfaz.
        ((InterfaceVista) vistaModificar).mostrarEntidad(entidad);
    }

    /**
     * Este método sirve para mostrar una vista desde otra vista en el
     * Principal. Este método es utilizado como intermediario de comunicación
     * entre las vistas, ya que una vista puede decidir la siguiente vista a
     * mostrar.
     *
     * @param vista el identificador de la vista la cual se quiere mostrar
     */
    @Override
    public void mostrarVista(Vista vista) {
        manejadorVista.show(vistas, vista.toString());
    }

    @Override
    public void mostrarEntidad(Object entidad) {
    }

    @Override
    public boolean confirmarCambio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return el objeto UsuarioDTO que representa al usuario que inició sesión
     * en el sistema
     */
    @Override
    public UsuarioDTO obtenerUsuarioActual() {
        return this.usuarioActual;
    }

    @Override
    public void limpiar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Este método es llamado cuando se selecciona cualquiera de los menús y
     * sirve para mostrar la vista en base al menú seleccionado.
     *
     * @param e el objeto ActionEvent generado por el evento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Para cuando se haga click en un menu item
        //Obtener la vista actual
        JPanel actual = getVistaActual();
        //boolean ok = true;

        String nombreMenu = ((JMenuItem) e.getSource()).getName();

        /*
         Esta parte del código fue eliminada el 19 de octubre del 2015 debido
         a que ya no se utilizará el método para confirmar el cambio cuando se
         quiere cambiar de vista. El método llamado 'confirmarCambio'
         posiblemente sea eliminado de la 'InterfaceVista'.
        
        
         //Si la vista actual no tiene que ver con Registrar, Modificar, Generar
         //o Asignar simplemente se muestra la vista, en caso contrario se pide
         //la confirmación a la vista actual para cambiar de panel.
         if ((actual.getName().startsWith("Registrar") || actual.getName()
         .startsWith("Modificar") || actual.getName().startsWith("Generar")
         || actual.getName().startsWith("Asignar"))
         && !actual.getName().equals(nombreMenu)) {
         ok = ((InterfaceVista) actual).confirmarCambio();
         }

         */
        //Si todo es correcto limpia la vista actual y muestra la nueva vista
        if (!actual.getName().equals(nombreMenu)) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, nombreMenu);
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    /**
     * Para cuando se cierra la ventana pedir confirmación.
     *
     * @param e objeto generado por el evento
     */
    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    /**
     * Para cuando se cierra la ventana pedir confirmación.
     *
     * @param e objeto generado por el evento
     */
    @Override
    public void windowClosed(WindowEvent e) {
        System.exit(0);
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
}
