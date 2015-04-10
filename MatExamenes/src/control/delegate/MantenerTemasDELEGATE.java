/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.delegate;

import control.facade.FACADEServiceLocator;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

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
    public int guardarTema(TemaDTO tema) {
        int id = FACADEServiceLocator.getTemaFACADE().guardarTema(tema);
        return id;
    }
    
    /**
     * Obtener todos los cursos.
     * @return lista de todos los cursos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos;
        
        listaCursos = FACADEServiceLocator.getCursoFACADE().obtenerCursos();
        return listaCursos;
    }
    
    /**
     * 
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas;

        listaTemas = FACADEServiceLocator.getTemaFACADE()
                .obtenerTemasSinAsignar();        
        return listaTemas;
    }
    
    /**
     * Obtener los temas de un curso
     * @param curso el curso
     * @return los temas de dicho curso
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> listaTemas;
        
        listaTemas = FACADEServiceLocator.getCursoFACADE()
                .obtenerTemasDeCurso(curso);
        return listaTemas;
    }
    
    /**
     * Modificar el tema en la base de datos
     * @param tema el objeto tema con los atributos basicos (Tipos simples)
     * como String, int, double etc... Las relaciones con otros objetos deben
     * ser mantenidos en este controlador para poder hacer la modificacion
     * en la base de datos correctamente
     */
    public void modificarTema(TemaDTO tema) {
        FACADEServiceLocator.getTemaFACADE().modificarTema(tema);
    }
    
    /**
     * Se elimina el tema, este debe estar en la lista mantenida en
     * este controlador
     * @param tema 
     */
    public void eliminarTema(TemaDTO tema) {
        FACADEServiceLocator.getTemaFACADE().eliminarTema(tema);

    }
}
