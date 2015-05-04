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
import modelo.dto.ExamenDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.TablaEstadisticas;
import remoteAccess.Enlace;

/**
 *
 * @author Jesus Donaldo
 */
public class GenerarEstadisticasDELEGATE {
    
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;
        
        try {
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return cursos;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso) {
        List<ExamenDTO> examenes = null;
        
        try{
            examenes = Enlace.getPersistencia().obtenerExamenesPorCurso(curso,
                    true, null);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    public TablaEstadisticas generarEstadisticasPorGrupos(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try{
            List<GrupoDTO> grupos = Enlace.getPersistencia()
                    .obtenerEntidades(GrupoDTO.class);
            
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorGrupos(examenes, grupos);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
    public TablaEstadisticas generarEstadisticasPorGrados(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try {
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorGrados(examenes);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
    public TablaEstadisticas generarEstadisticasPorTurnos(
            List<ExamenDTO> examenes) {
        
        TablaEstadisticas tabla = null;
        
        try {
            tabla = Enlace.getPersistencia()
                    .generarEstadisticasPorTurnos(examenes);
            
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return tabla;
    }
    
}