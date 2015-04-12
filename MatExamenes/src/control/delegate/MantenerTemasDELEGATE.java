/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;
import remoteAccess.Enlace;

/**
 *
 * @author ivan
 */
public class MantenerTemasDELEGATE {
    
    /**
     * Persiste el tema en la base de datos
     * @param tema el objeto a persistir
     * @return el id generado por la inserción
     */
    public Integer guardarTema(TemaDTO tema) {
        Integer id = null;
        try {
            id = Enlace.getPersistencia().guardarEntidad(tema);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return id;
    }
    
    /**
     * Obtener todos los cursos.
     * @return lista de todos los cursos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;
        
        try {
            listaCursos = Enlace.getPersistencia()
                    .obtenerEntidades(CursoDTO.class);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }
        
        return listaCursos;
    }
    
    /**
     * 
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas = null;

        try {
            listaTemas = Enlace.getPersistencia().obtenerTemasSinAsignar();
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }
        
        return listaTemas;
    }
    
    /**
     * Obtener los temas de un curso
     * @param curso el curso
     * @return los temas de dicho curso
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> listaTemas = null;
        
        try {
            listaTemas = Enlace.getPersistencia().obtenerTemasDeCurso(curso);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }
        
        return listaTemas;
    }
    
    /**
     * Modificar el tema en la base de datos
     * @param tema el objeto tema con los atributos basicos (Tipos simples)
     * como String, int, double etc... Las relaciones con otros objetos deben
     * ser mantenidos en este controlador para poder hacer la modificacion
     * en la base de datos correctamente
     * @return 
     */
    public boolean modificarTema(TemaDTO tema) {
        boolean ok = false;
        
        try {
            ok = Enlace.getPersistencia().modificarEntidad(tema);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
    
    /**
     * Se elimina el tema, este debe estar en la lista mantenida en
     * este controlador
     * @param tema 
     * @return  
     */
    public boolean eliminarTema(TemaDTO tema) {
        boolean ok = false;
        
        try {
            ok = Enlace.getPersistencia().eliminarEntidad(tema);
        } catch (RemoteException ex) {
            System.out.println(ex);
        } catch (NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
}