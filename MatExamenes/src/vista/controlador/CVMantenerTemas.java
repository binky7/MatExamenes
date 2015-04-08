/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerTemasDELEGATE;
import java.util.Collections;
import java.util.List;
import modelo.dto.CursoDTO;
import modelo.dto.TemaDTO;

/**
 *
 * @author Jesus Donaldo
 */
public class CVMantenerTemas {
    
    //No se usara aqui pero es para demostrar como funciona....
    private final MantenerTemasDELEGATE mantenerTemasDELEGATE;
    
    //Listas de dto necesarios
    //dtos para consultar
    List<CursoDTO> cursos;
    List<TemaDTO> temas;
    //dto para modificar
    //Es necesario matener en todo momento los objetos obtenidos por hibernate
    //Si se quiere modificar o eliminar dichos objetos, por eso la razon de este
    //atributo
    TemaDTO tema;
    
    public CVMantenerTemas() {
        mantenerTemasDELEGATE = new MantenerTemasDELEGATE();
    }
    
    /**
     * Persiste el tema en la base de datos
     * @param tema el objeto a persistir
     * @return el id generado por la inserción
     */
    public int guardarTema(TemaDTO tema) {
        int id = mantenerTemasDELEGATE.guardarTema(tema);
        return id;
    }
    
    /**
     * Obtener todos los cursos
     * @return lista de todos los cursos
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos;
        
        //Asigna los cursos obtenidos en la lista.
        listaCursos = mantenerTemasDELEGATE.obtenerCursos();
        cursos = listaCursos;
        
        return listaCursos;
    }
 
    /**
     * Obtener los temas de un curso
     * @param curso el curso
     * @return los temas de dicho curso
     */
    public List<TemaDTO> obtenerTemasDeCurso(String curso) {
        List<TemaDTO> listaTemas = null;
        
        if(cursos != null && !cursos.isEmpty()) {
            CursoDTO objCurso = new CursoDTO();
            objCurso.setNombre(curso);
            
            //Ordena y busca en la lista el curso con el nombre especificado
            Collections.sort(cursos);
            int index = Collections.binarySearch(cursos, objCurso);
            
            //Si se encontro el objeto
            if(index >= 0) {
                //Se obtiene el objeto de la lista (que contiene la llave prim)
                objCurso = cursos.get(index);
                listaTemas = mantenerTemasDELEGATE.obtenerTemasDeCurso(objCurso);
                temas = listaTemas;
            }
        }
        
        return listaTemas;
    }

    /**
     * 
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas;
        
        //Se obtienen los temas sin asignar y se sustituye la lista
        listaTemas = mantenerTemasDELEGATE.obtenerTemasSinAsignar();
        temas = listaTemas;
        
        return listaTemas;
    }
    
    /**
     * Obtener el tema completo con todas sus relaciones, en este caso no tiene
     * ninguna asi que nomas buscamos en la lista de temas y lo regresamos
     * @param nombreTema el nombre del tema obtenido de la vista
     * @return el objeto tema con su nombre de tema y id. Es importante denotar
     * que al buscarse por nombre y no por id se puede modificar un tema pero
     * no reflejarse los cambios al momento en la vista. Por esta razon al
     * buscar el antigüo nombre del curso en la lista se regresara null
     */
    public TemaDTO obtenerTema(String nombreTema) {
        TemaDTO objTema = null;
        
        //Se obtiene el objeto completo, como no tiene mas relaciones no es
        //Necesario llamar al dao, tambien como se va a modificar se asigna
        //al atributo tema
        
        if(temas != null && !temas.isEmpty()) {
            objTema = new TemaDTO();
            objTema.setNombre(nombreTema);
            
            //Ordena y busca en la lista el tema con el nombre especificado
            Collections.sort(temas);
            int index = Collections.binarySearch(temas, objTema);
            
            //Si se encontro el objeto
            if(index >= 0) {
                //Se obtiene el objeto de la lista (que contiene la llave prim)
                objTema = temas.get(index);
            }
            else {
                objTema = null;
            }
        }
        
        //Se guarda el objeto para su posterior uso en la modificacion
        tema = objTema;
        
        return tema;
    }
    
    /**
     * Modificar el tema en la base de datos
     * @param tema el objeto tema con los atributos basicos (Tipos simples)
     * como String, int, double etc... Las relaciones con otros objetos deben
     * ser mantenidos en este controlador para poder hacer la modificacion
     * en la base de datos correctamente
     */
    public void modificarTema(TemaDTO tema) {
        //Pasar todos los atributos basicos que pudieron cambiar al atributo
        //tema para ser enviado
        if (this.tema != null) {
            this.tema.setNombre(tema.getNombre());

            mantenerTemasDELEGATE.modificarTema(this.tema);
        }
        else {
            System.out.println("Error inesperado!");
        }
    }
    
    /**
     * Se elimina el tema, este debe estar en la lista mantenida en
     * este controlador
     * @param tema 
     */
    public void eliminarTema(String tema) {

        if(temas != null && !temas.isEmpty()) {
            TemaDTO objTema = new TemaDTO();
            objTema.setNombre(tema);
            
            //Ordena y busca en la lista el tema con el nombre especificado
            Collections.sort(temas);
            int index = Collections.binarySearch(temas, objTema);
            
            //Si se encontro el objeto
            if(index >= 0) {
                //Se obtiene el objeto de la lista (que contiene la llave prim)
                objTema = temas.get(index);
                mantenerTemasDELEGATE.eliminarTema(objTema);
                temas.remove(index);
            }
        }
    }
    
    
    public void liberarMemoriaConsultar() {
        cursos = null;
        temas = null;
    }
    
    public void liberarMemoriaModificar() {
        tema = null;
    }

}