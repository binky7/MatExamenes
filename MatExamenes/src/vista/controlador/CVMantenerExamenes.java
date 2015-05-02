/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerExamenesDELEGATE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class CVMantenerExamenes {
    private final MantenerExamenesDELEGATE mantenerExamenesDELEGATE;
    //Registrar/Consultar Examenes
    private List<CursoDTO> cursos;
    //Registrar/Modificar Examen
    private ExamenDTO examen;
    //Para asignar reactivos manualmente
    private List<ReactivoDTO> reactivos;
    //Para asignar reactivos aleatoriamente
    private List<TemaDTO> temas;
    //Para asignar reactivos aleatoriamente
    private List<Integer> cantidades;
    //Para consultar examenes
    private List<ExamenDTO> examenes;
    
    public CVMantenerExamenes() {
        mantenerExamenesDELEGATE = new MantenerExamenesDELEGATE();
    }
    
    public ExamenDTO obtenerExamen(int indexExamen) {
        ExamenDTO objExamen = null;
        
        if(examenes != null && !examenes.isEmpty()) {
            objExamen = examenes.get(indexExamen);
            objExamen = mantenerExamenesDELEGATE.obtenerExamen(objExamen.getId());
        }
        examen = objExamen;
        
        return objExamen;
    }
    
    public void setCurso(int indexCurso) {
        if(examen == null) {
            examen = new ExamenDTO();
        }
        if(cursos != null && !cursos.isEmpty()) {
            examen.setCurso(cursos.get(indexCurso));
        }
    }
    
    public ReactivoDTO obtenerReactivo(int indexReactivo) {
        ReactivoDTO reactivo = null;
        
        if(reactivos != null && !reactivos.isEmpty()) {
            reactivo = reactivos.get(indexReactivo);
            reactivo = mantenerExamenesDELEGATE.obtenerReactivo(reactivo.getId());
        }

        return reactivo;
    }
    
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso) {
        List<TemaDTO> listaTemas = null;
        
        if(cursos != null && !cursos.isEmpty()) {
            CursoDTO curso = cursos.get(indexCurso);
            listaTemas = mantenerExamenesDELEGATE.obtenerTemasDeCurso(curso);
        }
        return listaTemas;
    }
    
    public List<TemaDTO> obtenerTemasDeCurso() {
        List<TemaDTO> listaTemas = null;
        
        if(examen != null) {
            listaTemas = mantenerExamenesDELEGATE
                    .obtenerTemasDeCurso(examen.getCurso());
        }
        return listaTemas;
    }
    
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = mantenerExamenesDELEGATE.obtenerCursos();
        cursos = listaCursos;
        
        return listaCursos;
    }
    
    public List<ReactivoDTO> obtenerReactivosPorTema(String nombreTema) {
        TemaDTO tema = new TemaDTO();
        
        tema.setNombre(nombreTema);
        List<ReactivoDTO> listaReactivos = mantenerExamenesDELEGATE
                .obtenerReactivosPorTema(tema);
        reactivos = listaReactivos;
        
        return listaReactivos;
    }
    
    public Integer guardarExamen(ExamenDTO objExamen) {
        Integer id = null;
        
        if(examen != null) {
            examen.setInstrucciones(objExamen.getInstrucciones());
            examen.setPermiso(objExamen.getPermiso());
            examen.setTitulo(objExamen.getTitulo());
            examen.setAutor(objExamen.getAutor());
            examen.setFechaCreacion(new Date());
            examen.setFechaModificacion(new Date());
            id = mantenerExamenesDELEGATE.guardarExamen(examen);
        }
        
        return id;
    }
    
    public boolean modificarExamen(ExamenDTO objExamen) {
        boolean ok = false;
        
        if(examen != null) {
            examen.setInstrucciones(objExamen.getInstrucciones());
            examen.setPermiso(objExamen.getPermiso());
            examen.setTitulo(objExamen.getTitulo());
            examen.setFechaModificacion(new Date());
            ok = mantenerExamenesDELEGATE.modificarExamen(examen);
        }
        
        return ok;
    }
    
    public boolean eliminarExamen(int indexExamen) {
        boolean ok = false;
        
        if(examenes != null && !examenes.isEmpty()) {
            ExamenDTO objExamen = examenes.get(indexExamen);
            ok = mantenerExamenesDELEGATE.eliminarExamen(objExamen);
            if(ok) {
                examenes.remove(indexExamen);
            }
        }
        
        return ok;
    }
    
    public void agregarSeleccion(String nombreTema, int cantidad) {
        
        if(temas == null) {
            temas = new ArrayList<TemaDTO>();
            cantidades = new ArrayList<Integer>();
        }
        TemaDTO tema = new TemaDTO();
        tema.setNombre(nombreTema);
        
        temas.add(tema);
        cantidades.add(cantidad);
    }
    
    public void removerSeleccion(int index) {
        
        if(temas != null && !temas.isEmpty()) {
            temas.remove(index);
            cantidades.remove(index);
        }
    }
    
    public List<ReactivoDTO> obtenerReactivosAleatorios(int clave) {
        List<ReactivoDTO> listaReactivos = null;

        if (temas != null && cantidades != null) {
            listaReactivos = mantenerExamenesDELEGATE
                    .obtenerReactivosAleatorios(temas, cantidades);

            if (listaReactivos != null && !listaReactivos.isEmpty()) {
                listaReactivos = agregarReactivos(clave, listaReactivos);
            }
        }

        return listaReactivos;
    }
    
    public ReactivoDTO obtenerReactivo(int indexReactivo, int clave) {
        ReactivoDTO reactivo = null;
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                ClaveExamenDTO objClave = claves.get(clave);
                
                List<ReactivoDTO> reactivosClave = objClave.getReactivos();
                
                if(reactivosClave != null && !reactivosClave.isEmpty()) {
                    reactivo = reactivosClave.get(indexReactivo);
                    reactivo = mantenerExamenesDELEGATE
                            .obtenerReactivo(reactivo.getId());
                }
            }
        }
        return reactivo;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCurso(String nombreCurso,
            boolean todos, UsuarioDTO maestro) {
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        List<ExamenDTO> listaExamenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorCurso(curso, todos, maestro);
        examenes = listaExamenes;
        
        return listaExamenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorTitulo(String titulo, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> listaExamenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorTitulo(titulo, todos, maestro);
        examenes = listaExamenes;
        
        return listaExamenes;
    }
    
    public List<ExamenDTO> obtenerExamenesPorCursoYTitulo(String nombreCurso,
            String titulo, boolean todos, UsuarioDTO maestro) {
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        List<ExamenDTO> listaExamenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorCursoYTitulo(curso, titulo, todos, maestro);
        examenes = listaExamenes;
        
        return listaExamenes;
    }
    
    public List<ReactivoDTO> agregarReactivosSeleccionados(
            List<Integer> indexesReactivo, int clave) {
        List<ReactivoDTO> listaReactivos = new ArrayList<>();
        
        for(int indexReactivo : indexesReactivo) {
            if(reactivos != null && !reactivos.isEmpty()) {
                ReactivoDTO reactivo = reactivos.get(indexReactivo);
                listaReactivos.add(reactivo);
            }
        }
        listaReactivos = agregarReactivos(clave, listaReactivos);
        
        return listaReactivos;
    }
    
    public void removerReactivos(List<Integer> indexesReactivo, int clave) {
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                ClaveExamenDTO objClave = claves.get(clave);
                List<ReactivoDTO> reactivosClave = objClave.getReactivos();
                
                if (reactivosClave != null && !reactivosClave.isEmpty()) {
                    //Remover todos los reactivos seleccionados
                    //Ordenar los indices alrrevez para eliminarlos de la lista
                    Collections.sort(indexesReactivo, Collections.reverseOrder());
                    for (int indexReactivo : indexesReactivo) {
                        reactivosClave.remove(indexReactivo);
                    }
                }
            }
        }
    }
    
    public void agregarClave(int clave) {

        if (examen == null) {
            examen = new ExamenDTO();
        }
        
        ClaveExamenDTO objClave = new ClaveExamenDTO();
        ClaveExamenPK clavePK = new ClaveExamenPK();

        clavePK.setClave(clave);
        objClave.setId(clavePK);
        examen.addClave(objClave);
    }
    
    public void removerClave(int clave) {
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                claves.remove(clave);
            }
        }
    }
    
    public void removerTodasLasClaves() {
        
        if(examen != null) {
            examen.setClaves(new ArrayList<ClaveExamenDTO>());
        }
    }
    
    private List<ReactivoDTO> agregarReactivos(int clave,
            List<ReactivoDTO> listaReactivos) {

        if (examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            ClaveExamenDTO objClave = claves.get(clave);
            List<ReactivoDTO> reactivosClave = objClave.getReactivos();

            if (!reactivosClave.isEmpty()) {
                //Verificar que los reactivos agregados no se repitan en la clave
                for (int i = 0; i < listaReactivos.size(); i++) {
                    if(reactivosClave.contains(listaReactivos.get(i))) {
                        listaReactivos.remove(i);
                        i--;
                    }
                }
            }
            reactivosClave.addAll(listaReactivos);
            //objClave.setReactivos(reactivosClave);
        }
        
        return listaReactivos;
    }

    public int obtenerTotalReactivos(String nombreTema) {
        List<ReactivoDTO> reactivos = obtenerReactivosPorTema(nombreTema);
        int total = 0;
        
        if(reactivos != null) {
            total = reactivos.size();
        }
        
        return total;
    }
    
    public void liberarMemoriaConsultar() {
        examenes = null;
        cursos = null;
    }
    
    public void liberarMemoriaRegistrar() {
        cursos = null;
        examen = null;
    }
    
    public void liberarMemoriaModificar() {
        examen = null;
    }
    
    public void liberarMemoriaAgregarReactivos() {
        reactivos = null;
        temas = null;
        cantidades = null;
    }
    
}