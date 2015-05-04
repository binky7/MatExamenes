/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.GenerarEstadisticasDELEGATE;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.TablaEstadisticas;

/**
 *
 * @author Jesus Donaldo
 */
public class CVGenerarEstadisticas {
    
    private final GenerarEstadisticasDELEGATE generarEstadisticasDELEGATE;
    List<ExamenDTO> examenes;
    
    public CVGenerarEstadisticas() {
        generarEstadisticasDELEGATE = new GenerarEstadisticasDELEGATE();
    }
    
    public List<CursoDTO> obtenerCursos() {
        
        return generarEstadisticasDELEGATE.obtenerCursos();
    }
    
    public List<ExamenDTO> obtenerExamenesPorCurso(String nombreCurso) {   
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        
        List<ExamenDTO> listaExamenes = generarEstadisticasDELEGATE
                .obtenerExamenesPorCurso(curso);
        
        examenes = listaExamenes;
        
        return listaExamenes;
    }
    
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<Integer> indexesExamen) {
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorGrupos(listaExamenes);
        }
        
        return tabla;
    }
    
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<Integer> indexesExamen) {
        
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorGrados(listaExamenes);
        }
        
        return tabla;
    }
    
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<Integer> indexesExamen) {
        
        TablaEstadisticas tabla = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            List<ExamenDTO> listaExamenes = new ArrayList<>();
            
            for(int index : indexesExamen) {
                ExamenDTO examen = examenes.get(index);
                
                listaExamenes.add(examen);
            }
            
            tabla = generarEstadisticasDELEGATE
                    .generarEstadisticasPorTurnos(listaExamenes);
        }
        
        return tabla;
    }
    
    public void liberarMemoria() {
        examenes = null;
    }
    
}