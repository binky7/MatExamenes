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
    public Integer guardarTema(TemaDTO tema) {
        Integer id = mantenerTemasDELEGATE.guardarTema(tema);
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
     * @param index
     * @return los temas de dicho curso
     */
    public List<TemaDTO> obtenerTemasDeCurso(int index) {
        List<TemaDTO> listaTemas = null;

        if (cursos != null && !cursos.isEmpty()) {
            //Se obtiene el objeto de la lista (que contiene la llave prim)
            CursoDTO objCurso = cursos.get(index);
            listaTemas = mantenerTemasDELEGATE
                    .obtenerTemasDeCurso(objCurso);
            temas = listaTemas;
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
     * @param index
     * @return el objeto tema con su nombre de tema y id. Es importante denotar
     * que al buscarse por nombre y no por id se puede modificar un tema pero
     * no reflejarse los cambios al momento en la vista. Por esta razon al
     * buscar el antigüo nombre del curso en la lista se regresara null
     */
    public TemaDTO obtenerTema(int index) {
        TemaDTO objTema = null;

        //Se obtiene el objeto completo, como no tiene mas relaciones no es
        //Necesario llamar al dao, tambien como se va a modificar se asigna
        //al atributo tema
        if (temas != null && !temas.isEmpty()) {
            objTema = temas.get(index);
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
     * @return 
     */
    public boolean modificarTema(TemaDTO tema) {
        //Pasar todos los atributos basicos que pudieron cambiar al atributo
        //tema para ser enviado
        boolean ok;
        
        if (this.tema != null) {
            this.tema.setNombre(tema.getNombre());

            ok = mantenerTemasDELEGATE.modificarTema(this.tema);
        }
        else {
            System.out.println("Error inesperado!");
            ok = false;
        }
        return ok;
    }
    
    /**
     * Se elimina el tema, este debe estar en la lista mantenida en
     * este controlador
     * @param index 
     * @return  
     */
    public boolean eliminarTema(int index) {

        boolean ok = false;

        if (temas != null && !temas.isEmpty()) {
            //Se obtiene el objeto de la lista (que contiene la llave prim)
            TemaDTO objTema = temas.get(index);
            ok = mantenerTemasDELEGATE.eliminarTema(objTema);
            if (ok) {
                temas.remove(index);
            }
        }

        return ok;
    }
    
    
    public void liberarMemoriaConsultar() {
        cursos = null;
        temas = null;
    }
    
    public void liberarMemoriaModificar() {
        tema = null;
    }

}