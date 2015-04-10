/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.facade;

import java.util.List;
import modelo.dao.DAOServiceLocator;
import modelo.dto.TemaDTO;

/**
 *
 * @author ivan
 */
public class TemaFACADE {

    /**
     * Persiste el tema en la base de datos
     * @param tema el objeto a persistir
     * @return el id generado por la inserción
     */
    public int guardarTema(TemaDTO tema) {
        int id = DAOServiceLocator.getTemaDAO().insertar(tema);
        return id;
    }
    
    /**
     * 
     * @return Los temas que no pertenecen a ningun curso
     */
    public List<TemaDTO> obtenerTemasSinAsignar() {
        List<TemaDTO> listaTemas;

        listaTemas = DAOServiceLocator.getTemaDAO()
                .obtenerTemasSinAsignar();        
        return listaTemas;
    }
    
    /**
     * Modificar el tema en la base de datos
     * @param tema el objeto tema con los atributos basicos (Tipos simples)
     * como String, int, double etc... Las relaciones con otros objetos deben
     * ser mantenidos en este controlador para poder hacer la modificacion
     * en la base de datos correctamente
     */
    public void modificarTema(TemaDTO tema) {
        DAOServiceLocator.getTemaDAO().modificar(tema);
    }
    
    /**
     * Se elimina el tema, este debe estar en la lista mantenida en
     * este controlador
     * @param tema 
     */
    public void eliminarTema(TemaDTO tema) {
        DAOServiceLocator.getTemaDAO().eliminar(tema);

    }
}
