/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerCursosDELEGATE;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author ivan
 */
public class CVMantenerCursos {
    
    private final MantenerCursosDELEGATE mantenerCursosDELEGATE;
    private List<CursoDTO> cursos;
    private CursoDTO curso;
    private List<TemaDTO> temasSinAsignar;
    
    public CVMantenerCursos() {
        mantenerCursosDELEGATE = new MantenerCursosDELEGATE();
    }
    
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemasSinAsignar = 
                mantenerCursosDELEGATE.obtenerTemasSinAsignar();

        temasSinAsignar = listaTemasSinAsignar;
        return listaTemasSinAsignar;
    }
    
    public void agregarTemaSeleccionado(int indexTema) {
        TemaDTO tema = temasSinAsignar.get(indexTema);
        temasSinAsignar.remove(indexTema);
        agregarTema(tema);
    }
    
    private void agregarTema(TemaDTO tema) {
        if(curso == null) {
            curso =  new CursoDTO();
        }
        List<TemaDTO> temas = curso.getTemas();
        if(temas == null) {
            temas = new ArrayList<TemaDTO>();
        }
        temas.add(tema);
        curso.setTemas(temas);
    }
    
    public void removerTemaSeleccionado(int indexTema) {
        if(curso != null) {
            List<TemaDTO> temas = curso.getTemas();
            if(temas != null) {
                TemaDTO tema = temas.remove(indexTema);
                temasSinAsignar.add(tema);
            }
        }
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
        if(curso != null) {
            curso.setNombre(objCurso.getNombre());
            id = mantenerCursosDELEGATE.guardarCurso(curso);
        }
        
        return id;
    }
    
    public void liberarMemoriaRegistrarModificar() {
        curso = null;
        temasSinAsignar = null;
    }
}
