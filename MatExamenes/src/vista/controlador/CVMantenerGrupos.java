/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import java.util.List;
import modelo.dto.GrupoDTO;

/**
 *
 * @author FernandoEnrique
 */
public class CVMantenerGrupos {
    
    //Listas de dto necesarios
    //dtos para consultar
    List<GrupoDTO> grupos;
    
    //dto para modificar
    //Es necesario matener en todo momento los objetos obtenidos por hibernate
    //Si se quiere modificar o eliminar dichos objetos, por eso la razon de este
    //atributo
    GrupoDTO grupo;

    /**
     * Persiste el tema en la base de datos
     * @param grupo
     * @return el id generado por la inserción
     */
//    public int guardarGrupo(GrupoDTO grupo) {
//        int id = grupoDAO.insertar(grupo);
//        return id;
//    }
    
    /**
     * Obtener todos los cursos
     * @return lista de todos los cursos
     */
//    public List<GrupoDTO> obtenerGrupos() {
//        List<GrupoDTO> listaGrupos;
//        
//        //Asigna los cursos obtenidos en la lista.
//        listaGrupos = grupoDAO.obtenerTodos(GrupoDTO.class);
//        grupos = listaGrupos;
//        
//        return listaGrupos;
//    }
    
}
