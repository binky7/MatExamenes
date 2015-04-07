/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package matexamenes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import modelo.dao.CursoDAO;
import modelo.dao.ExamenDAO;
import modelo.dao.GenericDAO;
import modelo.dao.GrupoDAO;
import modelo.dao.HibernateUtil;
import modelo.dao.ReactivoDAO;
import modelo.dao.TemaDAO;
import modelo.dao.UsuarioDAO;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Jesus Donaldo
 */
public class MatExamenes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
//        SessionFactory fact = HibernateUtil.getSessionFactory();
//        System.out.println(fact);
//        Session s = fact.openSession();
//        Transaction tx = s.beginTransaction();
//        ReactivoDTO reactivo = null;
//        
//        try {
//            
//            Criteria c = s.createCriteria(ReactivoDTO.class)
//                    .add(Restrictions.idEq(1)).setFetchMode("opciones", FetchMode.JOIN);
//            reactivo = (ReactivoDTO) c.uniqueResult();
//            
//            
//            
//            tx.commit();
//            
//        } catch(Exception ex) {
//            if(tx != null) {
//                tx.rollback();
//                throw ex;
//            }
//        }
//        finally {
//            s.close();
//        }
//        fact.close();
//        
//        System.out.println("Nombre: " + reactivo.getNombre());
//        System.out.println("Redaccion: " + reactivo.getRedaccion());
//        System.out.println("Tema: " + reactivo.getTema().getNombre());
//        System.out.println("Usuario: " + reactivo.getAutor().getApellidoPaterno());
//        System.out.println("Usuario: " + reactivo.getAutor().getUsuario());
//        for(String opcion : reactivo.getOpciones()) {
//            System.out.println("Opcion: " + opcion);
//        }
        
//        UsuarioDTO usuario = new UsuarioDTO();
//        usuario.setNombre("Christofer");
//        usuario.setApellidoPaterno("Aguilar");
//        usuario.setApellidoMaterno("Augustus");
//        usuario.setUsuario("christoferloko");
//        usuario.setPassword("manco");
//        usuario.setTipo(UsuarioDTO.Tipo.Alumno);
//        CursoDTO curso = new CursoDTO();
//        
//        curso.setNombre("Matematicas");
//        List<TemaDTO> temas;
        
        
//        Session s = fact.openSession();
//        Transaction tx = s.beginTransaction();
//        
//        Criteria c = s.createCriteria(CursoDTO.class);
//        
//        c.add(Restrictions.eq("id", 2));
//        
//        c.setFetchMode("temas", FetchMode.JOIN);
//        
//        CursoDTO curso = (CursoDTO) c.uniqueResult();
//        
//        tx.commit();
//        s.close();
//        System.out.println("Session closed");
//        
//        
//         s = fact.openSession();
//        tx = s.beginTransaction();
//        
//        List<TemaDTO> temas = new ArrayList<TemaDTO>();
//        
//        temas.add((TemaDTO)s.get(TemaDTO.class, 2));
//        temas.add((TemaDTO)s.get(TemaDTO.class, 7));
//        temas.add((TemaDTO)s.get(TemaDTO.class, 4));
//        
//        tx.commit();
//        s.close();
//        
//        s = fact.openSession();
//        tx = s.beginTransaction();
//        
//        curso.setTemas(temas);
//        
//        s.update(curso);
//        
//        tx.commit();
//        s.close();
//        
//        fact.close();
//        System.out.println("Factory closed");
//        
//        System.out.println(curso.getId());
//        for(TemaDTO tema : curso.getTemas()) {
//            System.out.print(tema.getId() + " ");
//            System.out.println(tema.getNombre());
//        }
        
//        TemaDAO temaDAO = new TemaDAO();
//        
//        TemaDTO tema = new TemaDTO();
//        tema.setId(8);
//        tema.setNombre("hue");
//        
//        temaDAO.eliminar(tema);
        
//        Session s = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = s.beginTransaction();
//        
//        ExamenDTO examen = (ExamenDTO) s.createCriteria(ExamenDTO.class)
//                .uniqueResult();
//        
//        tx.commit();
//        s.close();
        
//        ExamenDTO examen = new ExamenDAO().obtener(1);
//        
//        System.out.println("Autor: " + examen.getAutor().getApellidoMaterno() +
//                "\nTitulo: " + examen.getTitulo() + "\nCurso: " + examen.getCurso().getNombre() +
//                "\nPermiso: " + examen.getPermiso() + "\n-----------------------");
//        for(ClaveExamenDTO clave : examen.getClaves()) {
//            System.out.println("Clave: " + clave.getClave());
//            for(ReactivoDTO reactivo : clave.getReactivos()) {
//                System.out.println("Nombre: " + reactivo.getNombre() + "\n" +
//                        "Respuesta" + reactivo.getRespuesta());
//            }
//            
//        }
        
//         Session s = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = s.beginTransaction();
//        
//        Criteria c = s.createCriteria(UsuarioDTO.class)
//                .add(Restrictions.eq("tipo", UsuarioDTO.Tipo.Alumno));
//        
//        List<UsuarioDTO> alumnos = c.list();
//        
//        UsuarioDTO maestro1 = (UsuarioDTO) s.get(UsuarioDTO.class, 1);
//        UsuarioDTO maestro2 = (UsuarioDTO) s.get(UsuarioDTO.class, 5);
//        
//        tx.commit();
//        s.close();
//        
//        List<CursoDTO> cursos = new CursoDAO().obtenerTodos();
//        
//        System.out.println(alumnos.size());
//        
//        HashMap<CursoDTO, UsuarioDTO> maestros =
//                new HashMap<CursoDTO, UsuarioDTO>();
//        
//        maestros.put(cursos.get(0), maestro1);
//        maestros.put(cursos.get(1), maestro2);
//        
//        GrupoDTO grupo = new GrupoDTO();
//        
//        grupo.setNombre("B");
//        grupo.setGrado(2);
//        grupo.setTurno(GrupoDTO.Turno.M);
//        grupo.setAlumnos(alumnos);
//        grupo.setMaestros(maestros);
//        
//        GrupoDAO grupoDAO = new GrupoDAO();
//        
//        grupoDAO.guardar(grupo);
        
//        GrupoDTO grupo = new GrupoDAO().obtener(1);
//        
//        System.out.println(grupo);
//        
//        System.out.println("Grado: " + grupo.getGrado() + "\nNombre: " +
//                grupo.getNombre() + "\nTurno: " + grupo.getTurno());
//        
//        
//        for(UsuarioDTO alumno : grupo.getAlumnos()) {
//            System.out.println("Nombre: " + alumno.getNombre());
//            System.out.println("Usuario: " + alumno.getUsuario());
//        }
//        
//        System.out.println(grupo.getMaestros());
//        
//        for(CursoDTO curso : grupo.getMaestros().keySet()) {
//            System.out.println("Curso: " + curso.getNombre());
//            UsuarioDTO maestro = grupo.getMaestros().get(curso);
//            
//            System.out.println("Maestro: " + maestro.getApellidoPaterno() + "\n" +
//                    maestro.getUsuario());
//        }
        
        List<TemaDTO> temas = new TemaDAO().obtenerTodos();
        
        for(TemaDTO tema : temas) {
            System.out.println(tema.getNombre());
        }
        
        HibernateUtil.getSessionFactory().close();
    }
}