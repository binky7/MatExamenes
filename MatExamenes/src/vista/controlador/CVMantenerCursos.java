/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerCursosDELEGATE;
import java.util.List;
import modelo.dto.CursoDTO;

/**
 *
 * @author ivan
 */
public class CVMantenerCursos {
    
    private final MantenerCursosDELEGATE mantenerCursosDELEGATE;
    private List<CursoDTO> listaCursos;
    private CursoDTO curso;
    
    public CVMantenerCursos() {
        mantenerCursosDELEGATE = new MantenerCursosDELEGATE();
    }
    
    public boolean verificarExistencia(String nombreCurso) {
        boolean existe;
        
        CursoDTO objCurso = new CursoDTO();
        objCurso.setNombre(nombreCurso);
        existe = mantenerCursosDELEGATE.verificarExistencia(objCurso);
        
        return existe;
    }
    
    public Integer guardarCurso(CursoDTO objCurso) {
        Integer id = null;
        curso = new CursoDTO();
        curso.setNombre(objCurso.getNombre());
        id = mantenerCursosDELEGATE.guardarCurso(curso);
        
        return id;
    }
    
    public void liberarMemoriaRegistrarModificar() {
        curso = null;
    }
    
    public void liberarMemoriaConsultar() {
        listaCursos = null;
    }
    
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = mantenerCursosDELEGATE.obtenerCursos();
        this.listaCursos = listaCursos;
        
        return listaCursos;
    }
    
    public CursoDTO obtenerCurso(int indexCurso) {
        CursoDTO objCurso = null;
        
        if(listaCursos != null || !listaCursos.isEmpty()) {
            objCurso = listaCursos.get(indexCurso);
            curso = objCurso;
        }
        
        return objCurso;
    }
    
    public boolean modificarCurso(CursoDTO objCurso) {
        curso.setNombre(objCurso.getNombre());
        boolean ok = mantenerCursosDELEGATE.modificarCurso(curso);
        
        return ok;
    }
    
    public boolean eliminarCurso(int indexCurso) {
        CursoDTO objCurso = listaCursos.get(indexCurso);
        boolean ok = mantenerCursosDELEGATE.eliminarCurso(objCurso);
        if(ok) {
            listaCursos.remove(indexCurso);
        }
        
        return ok;
    }
}