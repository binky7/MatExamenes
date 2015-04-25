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
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Jesus Donaldo
 */
public class MantenerReactivosDELEGATE {
    
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
    
    public Integer guardarReactivo(ReactivoDTO reactivo) {
        Integer id = null;
        
        try{
            id = Enlace.getPersistencia().guardarEntidad(reactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return id;
    }
    
    public boolean modificarReactivo(ReactivoDTO reactivo) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().modificarEntidad(reactivo);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
    
    public boolean eliminarReactivos(List<ReactivoDTO> reactivos) {
        boolean ok = false;
        
        try{
            ok = Enlace.getPersistencia().eliminarReactivos(reactivos);
        }catch(RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        
        return ok;
    }
}