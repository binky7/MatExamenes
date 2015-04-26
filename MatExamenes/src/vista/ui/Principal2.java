package vista.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;
import modelo.dto.UsuarioDTO;
import vista.controlador.CVConsultarCalificaciones;
import vista.controlador.CVMantenerCursos;
import vista.controlador.CVMantenerGrupos;
import vista.controlador.CVMantenerReactivos;
import vista.controlador.CVMantenerTemas;
import vista.controlador.CVMantenerUsuarios;
import vista.interfaz.InterfaceVista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ivan
 */
public class Principal2 extends javax.swing.JFrame implements InterfaceVista {

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

    /**
     * Creates new form Principal2
     */
    public Principal2() {
        initComponents();
        init();
        setTitle("MatExamenes");
    }

    public void setUsuarioActual(UsuarioDTO usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /**
     * Para crear los paneles, los controladores de vista y las relaciones entre
     * esta vista y los paneles
     */
    private void init() {
        //se deben agregar todos los paneles de vista a este panel
        vistas.setLayout(manejadorVista);

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

        vistaRegistrarGrupo = new VistaRegistrarGrupo();
        vistaRegistrarGrupo.setName(Vista.RegistrarGrupo.toString());
        vistaConsultarGrupo = new VistaConsultarGrupo();
        vistaConsultarGrupo.setName(Vista.ConsultarGrupo.toString());
        vistaModificarGrupo = new VistaModificarGrupo();
        vistaModificarGrupo.setName(Vista.ModificarGrupo.toString());

        vistaConsultarCalificaciones = new VistaConsultarCalificaciones();
        vistaConsultarCalificaciones.setName(Vista.ConsultarCalificaciones.toString());
        vistaConsultarCalificacionesExamen
                = new VistaConsultarCalificacionesExamen();
        vistaConsultarCalificacionesExamen.setName(Vista.ConsultarCalificacionesExamen.toString());

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

        //Crear controladores vistas
        CVMantenerTemas cvMantenerTemas = new CVMantenerTemas();
        CVMantenerCursos cvMantenerCursos = new CVMantenerCursos();
        CVMantenerGrupos cvMantenerGrupos = new CVMantenerGrupos();
        CVConsultarCalificaciones cvConsultarCalificaciones
                = new CVConsultarCalificaciones();
        CVMantenerUsuarios cvMantenerUsuarios = new CVMantenerUsuarios();
        CVMantenerReactivos cvMantenerReactivos = new CVMantenerReactivos();

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

        vistaConsultarCalificaciones.setPadre(this);
        vistaConsultarCalificacionesExamen.setPadre(this);

        vistaRegistrarUsuario.setPadre(this);
        vistaConsultarUsuarios.setPadre(this);
        vistaModificarUsuario.setPadre(this);

        vistaRegistrarReactivo.setPadre(this);
        vistaConsultarReactivos.setPadre(this);
        vistaModificarReactivo.setPadre(this);

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

        vistas.add(vistaConsultarCalificaciones, Vista.ConsultarCalificaciones
                .toString());
        vistas.add(vistaConsultarCalificacionesExamen, Vista.ConsultarCalificacionesExamen.toString());

        vistas.add(vistaRegistrarUsuario, Vista.RegistrarUsuario.toString());
        vistas.add(vistaConsultarUsuarios, Vista.ConsultarUsuarios.toString());
        vistas.add(vistaModificarUsuario, Vista.ModificarUsuario.toString());

        vistas.add(vistaRegistrarReactivo, Vista.RegistrarReactivo.toString());
        vistas.add(vistaConsultarReactivos, Vista.ConsultarReactivos.toString());
        vistas.add(vistaModificarReactivo, Vista.ModificarReactivo.toString());
    }

    /**
     * Obtener la vista actualmente mostrada
     *
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

        jPanel3 = new javax.swing.JPanel();
        //UIManager.put("TabbedPane.selected", new Color(230, 216, 174));
        tbpMenu = new javax.swing.JTabbedPane();
        pnlMantenimientos = new javax.swing.JPanel();
        pnlUsuariosGrupos = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnRegistrarUsuario = new javax.swing.JButton();
        btnConsultarUsuarios = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnRegistrarGrupo = new javax.swing.JButton();
        btnConsultarGrupos = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        pnlTemasCursos = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnRegistrarTema = new javax.swing.JButton();
        btnConsultarTemas = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnRegistrarCurso = new javax.swing.JButton();
        btnConsultarCursos = new javax.swing.JButton();
        pnlReactivosExamenes = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        btnRegistrarReactivo = new javax.swing.JButton();
        btnConsultarReactivos = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        btnRegistrarExamen = new javax.swing.JButton();
        btnConsultarExamenes = new javax.swing.JButton();
        btnAsignarExamen = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        pnlEstadisticas = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        pnlEstadistica1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        btnConsultarCalificaciones = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pnlEstadistica = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        btnGenerarEstadisticas = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        vistas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        tbpMenu.setBackground(new java.awt.Color(255, 255, 255));
        tbpMenu.setFocusable(false);
        tbpMenu.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        tbpMenu.setRequestFocusEnabled(false);

        pnlMantenimientos.setFocusable(false);
        pnlMantenimientos.setRequestFocusEnabled(false);

        pnlUsuariosGrupos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "USUARIOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuUsuario.png"))); // NOI18N
        jLabel1.setVerifyInputWhenFocusTarget(false);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRegistrarUsuario.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarUsuario.setText("Registrar");
        btnRegistrarUsuario.setBorder(null);
        btnRegistrarUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarUsuario.setFocusable(false);
        btnRegistrarUsuario.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarUsuarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarUsuarioMouseExited(evt);
            }
        });
        btnRegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarUsuarioActionPerformed(evt);
            }
        });

        btnConsultarUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarUsuarios.setText("Consultar");
        btnConsultarUsuarios.setBorder(null);
        btnConsultarUsuarios.setFocusable(false);
        btnConsultarUsuarios.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarUsuariosMouseExited(evt);
            }
        });
        btnConsultarUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultarUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnRegistrarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "GRUPOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(100, 119));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuGrupos.png"))); // NOI18N
        jLabel2.setVerifyInputWhenFocusTarget(false);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setPreferredSize(new java.awt.Dimension(91, 50));

        btnRegistrarGrupo.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarGrupo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarGrupo.setText("Registrar");
        btnRegistrarGrupo.setBorder(null);
        btnRegistrarGrupo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarGrupo.setFocusable(false);
        btnRegistrarGrupo.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarGrupo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarGrupoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarGrupoMouseExited(evt);
            }
        });
        btnRegistrarGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGrupoActionPerformed(evt);
            }
        });

        btnConsultarGrupos.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarGrupos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarGrupos.setText("Consultar");
        btnConsultarGrupos.setBorder(null);
        btnConsultarGrupos.setFocusable(false);
        btnConsultarGrupos.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarGruposMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarGruposMouseExited(evt);
            }
        });
        btnConsultarGrupos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarGruposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarGrupo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultarGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(btnRegistrarGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarGrupos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(28, 28, 28))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlUsuariosGruposLayout = new javax.swing.GroupLayout(pnlUsuariosGrupos);
        pnlUsuariosGrupos.setLayout(pnlUsuariosGruposLayout);
        pnlUsuariosGruposLayout.setHorizontalGroup(
            pnlUsuariosGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosGruposLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
        );
        pnlUsuariosGruposLayout.setVerticalGroup(
            pnlUsuariosGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
        );

        pnlMantenimientos.add(pnlUsuariosGrupos);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 84, Short.MAX_VALUE)
        );

        pnlMantenimientos.add(jPanel10);

        pnlTemasCursos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "TEMAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel8.setPreferredSize(new java.awt.Dimension(100, 119));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuTemas.png"))); // NOI18N
        jLabel3.setVerifyInputWhenFocusTarget(false);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRegistrarTema.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarTema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarTema.setMnemonic('T');
        btnRegistrarTema.setText("Registrar");
        btnRegistrarTema.setBorder(null);
        btnRegistrarTema.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarTema.setFocusable(false);
        btnRegistrarTema.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarTema.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarTemaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarTemaMouseExited(evt);
            }
        });
        btnRegistrarTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarTemaActionPerformed(evt);
            }
        });

        btnConsultarTemas.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarTemas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarTemas.setText("Consultar");
        btnConsultarTemas.setBorder(null);
        btnConsultarTemas.setFocusable(false);
        btnConsultarTemas.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarTemas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarTemasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarTemasMouseExited(evt);
            }
        });
        btnConsultarTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarTemasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarTema, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultarTemas, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(btnRegistrarTema, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarTemas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CURSOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel11.setPreferredSize(new java.awt.Dimension(100, 119));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuCursos.png"))); // NOI18N
        jLabel4.setVerifyInputWhenFocusTarget(false);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRegistrarCurso.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarCurso.setText("Registrar");
        btnRegistrarCurso.setBorder(null);
        btnRegistrarCurso.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarCurso.setFocusable(false);
        btnRegistrarCurso.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarCursoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarCursoMouseExited(evt);
            }
        });
        btnRegistrarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarCursoActionPerformed(evt);
            }
        });

        btnConsultarCursos.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarCursos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarCursos.setText("Consultar");
        btnConsultarCursos.setBorder(null);
        btnConsultarCursos.setFocusable(false);
        btnConsultarCursos.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarCursosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarCursosMouseExited(evt);
            }
        });
        btnConsultarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCursosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarCurso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultarCursos, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(btnRegistrarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlTemasCursosLayout = new javax.swing.GroupLayout(pnlTemasCursos);
        pnlTemasCursos.setLayout(pnlTemasCursosLayout);
        pnlTemasCursosLayout.setHorizontalGroup(
            pnlTemasCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTemasCursosLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlTemasCursosLayout.setVerticalGroup(
            pnlTemasCursosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 108, Short.MAX_VALUE)
        );

        pnlMantenimientos.add(pnlTemasCursos);

        pnlReactivosExamenes.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "REACTIVOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel13.setPreferredSize(new java.awt.Dimension(100, 119));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuReactivos.png"))); // NOI18N
        jLabel5.setVerifyInputWhenFocusTarget(false);

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRegistrarReactivo.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarReactivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarReactivo.setText("Registrar");
        btnRegistrarReactivo.setBorder(null);
        btnRegistrarReactivo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarReactivo.setFocusable(false);
        btnRegistrarReactivo.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarReactivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarReactivoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarReactivoMouseExited(evt);
            }
        });
        btnRegistrarReactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarReactivoActionPerformed(evt);
            }
        });

        btnConsultarReactivos.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarReactivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarReactivos.setText("Consultar");
        btnConsultarReactivos.setBorder(null);
        btnConsultarReactivos.setFocusable(false);
        btnConsultarReactivos.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarReactivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarReactivosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarReactivosMouseExited(evt);
            }
        });
        btnConsultarReactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarReactivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnRegistrarReactivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnConsultarReactivos, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(btnRegistrarReactivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConsultarReactivos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "EXÁMENES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel15.setPreferredSize(new java.awt.Dimension(190, 119));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuExamenes.png"))); // NOI18N
        jLabel6.setVerifyInputWhenFocusTarget(false);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnRegistrarExamen.setBackground(new java.awt.Color(255, 255, 255));
        btnRegistrarExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnRegistrarExamen.setText("Registrar");
        btnRegistrarExamen.setBorder(null);
        btnRegistrarExamen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrarExamen.setFocusable(false);
        btnRegistrarExamen.setPreferredSize(new java.awt.Dimension(65, 20));
        btnRegistrarExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRegistrarExamenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRegistrarExamenMouseExited(evt);
            }
        });
        btnRegistrarExamen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarExamenActionPerformed(evt);
            }
        });

        btnConsultarExamenes.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarExamenes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuConsultar_1.png"))); // NOI18N
        btnConsultarExamenes.setText("Consultar");
        btnConsultarExamenes.setBorder(null);
        btnConsultarExamenes.setFocusable(false);
        btnConsultarExamenes.setPreferredSize(new java.awt.Dimension(67, 20));
        btnConsultarExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarExamenesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarExamenesMouseExited(evt);
            }
        });
        btnConsultarExamenes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarExamenesActionPerformed(evt);
            }
        });

        btnAsignarExamen.setBackground(new java.awt.Color(255, 255, 255));
        btnAsignarExamen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuRegistrar_1.png"))); // NOI18N
        btnAsignarExamen.setText("Asignar");
        btnAsignarExamen.setBorder(null);
        btnAsignarExamen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAsignarExamen.setFocusable(false);
        btnAsignarExamen.setPreferredSize(new java.awt.Dimension(65, 20));
        btnAsignarExamen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAsignarExamenMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAsignarExamenMouseExited(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRegistrarExamen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultarExamenes, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignarExamen, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel16Layout.createSequentialGroup()
                    .addComponent(btnRegistrarExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(btnConsultarExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(btnAsignarExamen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(81, 81, 81))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlReactivosExamenesLayout = new javax.swing.GroupLayout(pnlReactivosExamenes);
        pnlReactivosExamenes.setLayout(pnlReactivosExamenesLayout);
        pnlReactivosExamenesLayout.setHorizontalGroup(
            pnlReactivosExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReactivosExamenesLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlReactivosExamenesLayout.setVerticalGroup(
            pnlReactivosExamenesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
            .addGroup(pnlReactivosExamenesLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlMantenimientos.add(pnlReactivosExamenes);

        java.awt.FlowLayout menuLayout = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT,10,0);
        pnlMantenimientos.setLayout(menuLayout);

        tbpMenu.addTab("Mantenimientos", pnlMantenimientos);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setPreferredSize(new java.awt.Dimension(138, 114));

        pnlEstadistica1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CALIFICACIONES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnConsultarCalificaciones.setBackground(new java.awt.Color(255, 255, 255));
        btnConsultarCalificaciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/consultarVerde.png"))); // NOI18N
        btnConsultarCalificaciones.setText("Consultar");
        btnConsultarCalificaciones.setBorder(null);
        btnConsultarCalificaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnConsultarCalificaciones.setFocusable(false);
        btnConsultarCalificaciones.setPreferredSize(new java.awt.Dimension(65, 20));
        btnConsultarCalificaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnConsultarCalificacionesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnConsultarCalificacionesMouseExited(evt);
            }
        });
        btnConsultarCalificaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarCalificacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnConsultarCalificaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(btnConsultarCalificaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuCalificaciones.png"))); // NOI18N

        javax.swing.GroupLayout pnlEstadistica1Layout = new javax.swing.GroupLayout(pnlEstadistica1);
        pnlEstadistica1.setLayout(pnlEstadistica1Layout);
        pnlEstadistica1Layout.setHorizontalGroup(
            pnlEstadistica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstadistica1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlEstadistica1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEstadistica1Layout.setVerticalGroup(
            pnlEstadistica1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstadistica1Layout.createSequentialGroup()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEstadistica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnlEstadistica1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(jPanel2);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(138, 114));

        pnlEstadistica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ESTADÍSTICAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGenerarEstadisticas.setBackground(new java.awt.Color(255, 255, 255));
        btnGenerarEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/registrarVerde.png"))); // NOI18N
        btnGenerarEstadisticas.setText("Generar");
        btnGenerarEstadisticas.setBorder(null);
        btnGenerarEstadisticas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarEstadisticas.setFocusable(false);
        btnGenerarEstadisticas.setPreferredSize(new java.awt.Dimension(65, 20));
        btnGenerarEstadisticas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerarEstadisticasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerarEstadisticasMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnGenerarEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(btnGenerarEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menuEstadisticas.png"))); // NOI18N

        javax.swing.GroupLayout pnlEstadisticaLayout = new javax.swing.GroupLayout(pnlEstadistica);
        pnlEstadistica.setLayout(pnlEstadisticaLayout);
        pnlEstadisticaLayout.setHorizontalGroup(
            pnlEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstadisticaLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlEstadisticaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlEstadisticaLayout.setVerticalGroup(
            pnlEstadisticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEstadisticaLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlEstadistica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlEstadisticas.add(jPanel1);

        //java.awt.FlowLayout jPanel1Layout = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        pnlEstadisticas.setLayout(menuLayout);

        tbpMenu.addTab("Estadísticas", pnlEstadisticas);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpMenu)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tbpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 148, Short.MAX_VALUE)
        );

        //SwingUtilities.updateComponentTreeUI(tbpMenu);

        vistas.setBackground(new java.awt.Color(255, 255, 255));
        vistas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        vistas.setPreferredSize(new java.awt.Dimension(801, 468));

        javax.swing.GroupLayout vistasLayout = new javax.swing.GroupLayout(vistas);
        vistas.setLayout(vistasLayout);
        vistasLayout.setHorizontalGroup(
            vistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 796, Short.MAX_VALUE)
        );
        vistasLayout.setVerticalGroup(
            vistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vistas, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(vistas, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarUsuarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioMouseExited
        // TODO add your handling code here:
        btnRegistrarUsuario.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarUsuarioMouseExited

    private void btnRegistrarUsuarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioMouseEntered
        // TODO add your handling code here:
        btnRegistrarUsuario.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarUsuarioMouseEntered

    private void btnRegistrarGrupoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarGrupoMouseEntered
        // TODO add your handling code here:
        btnRegistrarGrupo.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarGrupoMouseEntered

    private void btnRegistrarGrupoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarGrupoMouseExited
        // TODO add your handling code here:
        btnRegistrarGrupo.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarGrupoMouseExited

    private void btnRegistrarTemaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarTemaMouseEntered
        // TODO add your handling code here:
        btnRegistrarTema.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarTemaMouseEntered

    private void btnRegistrarTemaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarTemaMouseExited
        // TODO add your handling code here:
        btnRegistrarTema.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarTemaMouseExited

    private void btnRegistrarCursoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarCursoMouseEntered
        // TODO add your handling code here:
        btnRegistrarCurso.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarCursoMouseEntered

    private void btnRegistrarCursoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarCursoMouseExited
        // TODO add your handling code here:
        btnRegistrarCurso.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarCursoMouseExited

    private void btnRegistrarReactivoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarReactivoMouseEntered
        // TODO add your handling code here:
        btnRegistrarReactivo.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarReactivoMouseEntered

    private void btnRegistrarReactivoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarReactivoMouseExited
        // TODO add your handling code here:
        btnRegistrarReactivo.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarReactivoMouseExited

    private void btnRegistrarExamenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarExamenMouseEntered
        // TODO add your handling code here:
        btnRegistrarExamen.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnRegistrarExamenMouseEntered

    private void btnRegistrarExamenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRegistrarExamenMouseExited
        // TODO add your handling code here:
        btnRegistrarExamen.setBackground(Color.white);
    }//GEN-LAST:event_btnRegistrarExamenMouseExited

    private void btnAsignarExamenMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsignarExamenMouseEntered
        // TODO add your handling code here:
        btnAsignarExamen.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnAsignarExamenMouseEntered

    private void btnAsignarExamenMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAsignarExamenMouseExited
        // TODO add your handling code here:
        btnAsignarExamen.setBackground(Color.white);
    }//GEN-LAST:event_btnAsignarExamenMouseExited

    private void btnRegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarUsuarioActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarUsuario.toString());
        }
    }//GEN-LAST:event_btnRegistrarUsuarioActionPerformed

    private void btnConsultarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarUsuariosActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarUsuarios.toString());
        }
    }//GEN-LAST:event_btnConsultarUsuariosActionPerformed

    private void btnRegistrarGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGrupoActionPerformed
        // TODO add your handling code here:
        //Mostrar vistaRegistrarGrupo
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarGrupo.toString());
        }
    }//GEN-LAST:event_btnRegistrarGrupoActionPerformed

    private void btnConsultarGruposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarGruposActionPerformed
        // TODO add your handling code here:
        //Mostrar vistaConsultarGrupo
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarGrupo.toString());
        }
    }//GEN-LAST:event_btnConsultarGruposActionPerformed

    private void btnRegistrarTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarTemaActionPerformed
        // TODO add your handling code here:
        //Mostrar vistaRegistrarTema
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarTema.toString());
        }
    }//GEN-LAST:event_btnRegistrarTemaActionPerformed

    private void btnConsultarTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarTemasActionPerformed
        // TODO add your handling code here:
        //Mostrar vistaConsultarTemas
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarTemas.toString());
        }
    }//GEN-LAST:event_btnConsultarTemasActionPerformed

    private void btnRegistrarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarCursoActionPerformed
        // TODO add your handling code here:
        //Mostrar Registrar Cursos
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarCurso.toString());
        }
    }//GEN-LAST:event_btnRegistrarCursoActionPerformed

    private void btnGenerarEstadisticasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarEstadisticasMouseEntered
        // TODO add your handling code here:
        btnGenerarEstadisticas.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnGenerarEstadisticasMouseEntered

    private void btnGenerarEstadisticasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarEstadisticasMouseExited
        // TODO add your handling code here:
        btnGenerarEstadisticas.setBackground(Color.white);
    }//GEN-LAST:event_btnGenerarEstadisticasMouseExited

    private void btnConsultarCalificacionesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarCalificacionesMouseEntered
        // TODO add your handling code here:
        btnConsultarCalificaciones.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarCalificacionesMouseEntered

    private void btnConsultarCalificacionesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarCalificacionesMouseExited
        // TODO add your handling code here:
        btnConsultarCalificaciones.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarCalificacionesMouseExited

    private void btnConsultarUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarUsuariosMouseEntered
        // TODO add your handling code here:
        btnConsultarUsuarios.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarUsuariosMouseEntered

    private void btnConsultarGruposMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarGruposMouseEntered
        // TODO add your handling code here:
        btnConsultarGrupos.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarGruposMouseEntered

    private void btnConsultarTemasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarTemasMouseEntered
        // TODO add your handling code here:
        btnConsultarTemas.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarTemasMouseEntered

    private void btnConsultarCursosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarCursosMouseEntered
        // TODO add your handling code here:
        btnConsultarCursos.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarCursosMouseEntered

    private void btnConsultarReactivosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarReactivosMouseEntered
        // TODO add your handling code here:
        btnConsultarReactivos.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarReactivosMouseEntered

    private void btnConsultarExamenesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarExamenesMouseEntered
        // TODO add your handling code here:
        btnConsultarExamenes.setBackground(new Color(0xd5e1f2));
    }//GEN-LAST:event_btnConsultarExamenesMouseEntered

    private void btnConsultarUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarUsuariosMouseExited
        // TODO add your handling code here:
        btnConsultarUsuarios.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarUsuariosMouseExited

    private void btnConsultarGruposMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarGruposMouseExited
        // TODO add your handling code here:
        btnConsultarGrupos.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarGruposMouseExited

    private void btnConsultarTemasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarTemasMouseExited
        // TODO add your handling code here:
        btnConsultarTemas.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarTemasMouseExited

    private void btnConsultarCursosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarCursosMouseExited
        // TODO add your handling code here:
        btnConsultarCursos.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarCursosMouseExited

    private void btnConsultarReactivosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarReactivosMouseExited
        // TODO add your handling code here:
        btnConsultarReactivos.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarReactivosMouseExited

    private void btnConsultarExamenesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnConsultarExamenesMouseExited
        // TODO add your handling code here:
        btnConsultarExamenes.setBackground(Color.white);
    }//GEN-LAST:event_btnConsultarExamenesMouseExited

    private void btnRegistrarReactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarReactivoActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarReactivo.toString());
        }
    }//GEN-LAST:event_btnRegistrarReactivoActionPerformed

    private void btnConsultarReactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarReactivosActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarReactivos.toString());
        }
    }//GEN-LAST:event_btnConsultarReactivosActionPerformed

    private void btnConsultarExamenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarExamenesActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarExamenes.toString());
        }
    }//GEN-LAST:event_btnConsultarExamenesActionPerformed

    private void btnConsultarCalificacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCalificacionesActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarCalificaciones
                    .toString());
        }
    }//GEN-LAST:event_btnConsultarCalificacionesActionPerformed

    private void btnConsultarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarCursosActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.ConsultarCursos.toString());
        }
    }//GEN-LAST:event_btnConsultarCursosActionPerformed

    private void btnRegistrarExamenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarExamenActionPerformed
        // TODO add your handling code here:
        JPanel actual = getVistaActual();
        boolean ok = true;

        if (actual.getName().startsWith("Registrar") || actual.getName()
                .startsWith("Modificar")) {
            ok = ((InterfaceVista) actual).confirmarCambio();
        }

        if (ok) {
            ((InterfaceVista) actual).limpiar();
            manejadorVista.show(vistas, Vista.RegistrarExamen.toString());
        }
    }//GEN-LAST:event_btnRegistrarExamenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal2().setVisible(true);
            }
        });
    }

    public void setVistaAlumno() {
        ocultarTodos();
        //mContestarExamen.setVisible(true);
    }

    public void setVistaMaestro() {
        //mCalificaciones.setVisible(true);
        pnlMantenimientos.setVisible(true);
        pnlReactivosExamenes.setVisible(true);
        //pnlEstadisticas.setVisible(false);
        tbpMenu.remove(1);

    }

    /**
     *
     */
    public void setVistaAdmin() {
        //no se oculta ninguna opcion del menu 
    }

    private void ocultarTodos() {
        //mCalificaciones.setVisible(false);
        //mContestarExamen.setVisible(false);
//        mCursos.setVisible(false);
//        mEstadisticas.setVisible(false);
//        mExamenes.setVisible(false);
//        mGrupos.setVisible(false);
//        mReactivos.setVisible(false);
//        mTemas.setVisible(false);
//        mUsuarios.setVisible(false);

//        pnlUsuariosGrupos.setVisible(false);
//        pnlTemasCursos.setVisible(false);
//        pnlReactivosExamenes.setVisible(false);
        tbpMenu.removeAll();
//        pnlMantenimientos.setVisible(false);
//        pnlEstadisticas.setVisible(false);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarExamen;
    private javax.swing.JButton btnConsultarCalificaciones;
    private javax.swing.JButton btnConsultarCursos;
    private javax.swing.JButton btnConsultarExamenes;
    private javax.swing.JButton btnConsultarGrupos;
    private javax.swing.JButton btnConsultarReactivos;
    private javax.swing.JButton btnConsultarTemas;
    private javax.swing.JButton btnConsultarUsuarios;
    private javax.swing.JButton btnGenerarEstadisticas;
    private javax.swing.JButton btnRegistrarCurso;
    private javax.swing.JButton btnRegistrarExamen;
    private javax.swing.JButton btnRegistrarGrupo;
    private javax.swing.JButton btnRegistrarReactivo;
    private javax.swing.JButton btnRegistrarTema;
    private javax.swing.JButton btnRegistrarUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel pnlEstadistica;
    private javax.swing.JPanel pnlEstadistica1;
    private javax.swing.JPanel pnlEstadisticas;
    private javax.swing.JPanel pnlMantenimientos;
    private javax.swing.JPanel pnlReactivosExamenes;
    private javax.swing.JPanel pnlTemasCursos;
    private javax.swing.JPanel pnlUsuariosGrupos;
    private javax.swing.JTabbedPane tbpMenu;
    private javax.swing.JPanel vistas;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mostrarVistaConEntidad(Object entidad, Vista vista) {
        //Muestra la vista modificar necesaria y le envia el objeto entidad.
        manejadorVista.show(vistas, vista.toString());

        //Obtener la vista que se acabo de mostrar.
        JPanel vistaModificar = getVistaActual();
        //Enviarle el objeto por medio de la interfaz.
        ((InterfaceVista) vistaModificar).mostrarEntidad(entidad);
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
}
