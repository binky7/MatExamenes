/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.CursoDAO;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author ivan
 */
public class CursoFACADE {
    private final CursoDAO cursoDAO;
    
    public CursoFACADE() {
        cursoDAO = new CursoDAO();
    }
    
    /**
     * Persiste el curso en la base de datos.
     * @param curso el objeto a persistir.
     * @return el id generado por la inseción.
     */
    public int guardarCurso(CursoDTO curso) {
        int id = cursoDAO.insertar(curso);
        return id;
    }
    
    /**
     * Obtener todos los cursos.
     * @return lista de todos los cursos.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos;
        
        listaCursos = cursoDAO.obtenerTodos();
        return listaCursos;
    }
    
    /**
     * Obtener los temas de un curso
     * @param curso el curso
     * @return los temas de dicho curso
     */
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> listaTemas;
        
        listaTemas = cursoDAO.obtenerTemas(curso);
        return listaTemas;
    }
    
    public void modificarCurso(CursoDTO curso) {
        cursoDAO.modificar(curso);
    }
    
    public void eliminarcurso(CursoDTO curso) {
        cursoDAO.eliminar(curso);
    }
}
