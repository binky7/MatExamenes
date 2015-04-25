/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerReactivosDELEGATE;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class CVMantenerReactivos {
    
    private final MantenerReactivosDELEGATE mantenerReactivosDELEGATE;
    private List<ReactivoDTO> reactivos;
    private List<CursoDTO> cursos;
    private List<TemaDTO> temas;
    private TemaDTO temaReactivo;
    
    
    public CVMantenerReactivos() {
        mantenerReactivosDELEGATE = new MantenerReactivosDELEGATE();
    }
    
    public List<CursoDTO> obtenerCursos() {
        cursos = mantenerReactivosDELEGATE.obtenerCursos();
        return cursos;
    }
    
    public void setTema(int indexTema) {
        if(temas != null && !temas.isEmpty()) {
            temaReactivo = temas.get(indexTema);
        }
    }
    
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso) {
        List<TemaDTO> temas = null;
        
        if(cursos != null && !cursos.isEmpty()) {
            CursoDTO curso = cursos.get(indexCurso);
            temas = mantenerReactivosDELEGATE.obtenerTemasDeCurso(curso);
            this.temas = temas;
        }
        
        return temas;
    }
    
    public List<ReactivoDTO> obtenerReactivosPorTema(String nombreTema) {
        TemaDTO tema = new TemaDTO();
        
        tema.setNombre(nombreTema);
        List<ReactivoDTO> reactivos = mantenerReactivosDELEGATE
                .obtenerReactivosPorTema(tema);
        this.reactivos = reactivos;
        
        return reactivos;
    }
    
    public Integer guardarReactivo(ReactivoDTO reactivo) {
        Integer id;
        
        reactivo.setTema(temaReactivo);
        id = mantenerReactivosDELEGATE.guardarReactivo(reactivo);
        
        return id;
    }
    
    public ReactivoDTO obtenerReactivo(int indexReactivo) {
        ReactivoDTO reactivo = null;
        
        if(reactivos != null && !reactivos.isEmpty()) {
            reactivo = reactivos.get(indexReactivo);
            reactivo = mantenerReactivosDELEGATE
                    .obtenerReactivo(reactivo.getId());
        }
        
        return reactivo;
    }
    
    public boolean modificarReactivo(ReactivoDTO reactivo) {
        return mantenerReactivosDELEGATE.modificarReactivo(reactivo);
    }
    
    public boolean eliminarReactivos(List<Integer> indexesReactivo) {
        boolean ok = false;
        
        if(reactivos != null && !reactivos.isEmpty()) {
            List<ReactivoDTO> objsReactivo = new ArrayList<>();
            
            for(int indexReactivo : indexesReactivo) {
                objsReactivo.add(reactivos.get(indexReactivo));
            }
            ok = mantenerReactivosDELEGATE.eliminarReactivos(objsReactivo);
            if(ok) {
                //Ver que pasa
                reactivos.removeAll(objsReactivo);
            }
        }
        
        return ok;
    }
    
    public void liberarMemoriaConsultar() {
        reactivos = null;
        cursos = null;
        temas = null;
    }
    
    public void liberarMemoriaRegistrar() {
        cursos = null;
        temas = null;
    }
    
}