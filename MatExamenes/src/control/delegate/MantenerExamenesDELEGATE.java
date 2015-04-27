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
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Jesus Donaldo
 */
public class MantenerExamenesDELEGATE {
    
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> cursos = null;
        
        try {
            cursos = Enlace.getPersistencia().obtenerEntidades(CursoDTO.class);
        } catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return cursos;
    }
    
    public List<TemaDTO> obtenerTemasDeCurso(CursoDTO curso) {
        List<TemaDTO> temas = null;
        try{
            temas = Enlace.getPersistencia().obtenerTemasDeCurso(curso);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return temas;
    }
    
    public ReactivoDTO obtenerReactivo(int idReactivo) {
        ReactivoDTO reactivo = null;
        try{
            reactivo = Enlace.getPersistencia().obtenerReactivo(idReactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivo;
    }
    
    public List<ReactivoDTO> obtenerReactivosPorTema(TemaDTO tema) {
        List<ReactivoDTO> reactivos = null;
        try{
            reactivos = Enlace.getPersistencia().obtenerReactivosPorTema(tema);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivos;
    }
    
    public List<ReactivoDTO> obtenerReactivosAleatorios(List<TemaDTO> temas,
            List<Integer> cantidades) {
        List<ReactivoDTO> reactivos = null;
        try{
            reactivos = Enlace.getPersistencia()
                    .obtenerReactivosAleatorios(temas, cantidades);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return reactivos;
    }
    
    public ExamenDTO obtenerExamen(int idExamen) {
        ExamenDTO examen = null;
        try{
            examen = Enlace.getPersistencia().obtenerExamen(idExamen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examen;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCurso(CursoDTO curso, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia().obtenerExamenesPorCurso(curso,
                    todos, maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia().obtenerExamenesPorTitulo(titulo,
                    todos, maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCursoYTitulo(CursoDTO curso,
            String titulo, boolean todos, UsuarioDTO maestro) {
        List<ExamenDTO> examenes = null;
        try{
            examenes = Enlace.getPersistencia()
                    .obtenerExamenesPorCursoYTitulo(curso, titulo, todos,
                            maestro);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return examenes;
    }
    
    public Integer guardarExamen(ExamenDTO examen) {
        Integer id = null;
        
        try{
            id = Enlace.getPersistencia().guardarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return id;
    }
    
    public boolean modificarExamen(ExamenDTO examen) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().modificarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
    
    public boolean eliminarExamen(ExamenDTO examen) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().eliminarEntidad(examen);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
}