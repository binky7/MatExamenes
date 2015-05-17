/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerReactivosDELEGATE;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<ReactivoDTO> listaReactivos;
    private List<CursoDTO> cursos;
    private List<TemaDTO> temas;
    private TemaDTO temaReactivo;
    
    private ReactivoDTO reactivo;
    
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
        this.listaReactivos = reactivos;
        
        return reactivos;
    }
    
    public Integer guardarReactivo(ReactivoDTO reactivo) {
        Integer id;
        
        reactivo.setTema(temaReactivo);
        id = mantenerReactivosDELEGATE.guardarReactivo(reactivo);
        
        return id;
    }
    
    public ReactivoDTO obtenerReactivo(int indexReactivo) {
        ReactivoDTO objReactivo = null;
        
        if(listaReactivos != null && !listaReactivos.isEmpty()) {
            objReactivo = listaReactivos.get(indexReactivo);
            objReactivo = mantenerReactivosDELEGATE
                    .obtenerReactivo(objReactivo.getId());
            this.reactivo = objReactivo;
        }
        
        return objReactivo;
    }
    
    public boolean modificarReactivo(ReactivoDTO objReactivo) {
        
        this.reactivo.setNombre(objReactivo.getNombre());
        this.reactivo.setOpciones(objReactivo.getOpciones());
        this.reactivo.setRedaccion(objReactivo.getRedaccion());
        this.reactivo.setRespuesta(objReactivo.getRespuesta());
        this.reactivo.setFechaModificacion(objReactivo.getFechaModificacion());

        return mantenerReactivosDELEGATE.modificarReactivo(this.reactivo);
    }
    
    public boolean eliminarReactivos(List<Integer> indexesReactivo) {
        boolean ok = false;
        
        if(listaReactivos != null && !listaReactivos.isEmpty()) {
            List<ReactivoDTO> objsReactivo = new ArrayList<>();
            
            for(int indexReactivo : indexesReactivo) {
                objsReactivo.add(listaReactivos.get(indexReactivo));
            }
            ok = mantenerReactivosDELEGATE.eliminarReactivos(objsReactivo);
            if(ok) {
                //Ordenar los indices alrrevez para eliminarlos de la lista
                Collections.sort(indexesReactivo, Collections.reverseOrder());
                
                for(int index : indexesReactivo) {
                    listaReactivos.remove(index);
                }
            }
        }
        
        return ok;
    }
    
    public void liberarMemoriaConsultar() {
        listaReactivos = null;
        cursos = null;
        temas = null;
    }
    
    public void liberarMemoriaRegistrar() {
        cursos = null;
        temas = null;
        temaReactivo = null;
    }
    
    public void liberarMemoriaModificar() {
        reactivo = null;
    }
    
}