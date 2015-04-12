/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.interfaz;

import modelo.dto.UsuarioDTO;


/**
 *
 * @author Jesus Donaldo
 */
public interface InterfazVista {
    
    /**
     * Enumeracion necesaria para identificar a los paneles en el principal
     * agregar conforme se vayan requiriendo
     */
    enum Vista {
        HOME, RegistrarTema, ConsultarTemas, ModificarTema,
        RegistrarCurso, ConsultarCursos, ModificarCurso, RegistrarGrupo,
        ConsultarGrupo, ModificarGrupo, RegistrarExamen, ConsultarExamenes,
        ModificarExamen, ConsultarCalificaciones, ConsultarCalificacionesExamen
    }
    
    /**
     * Se utiliza como intermediario para enviarle el objeto obtenido de la
     * vista consulta a su vista modificar respectiva
     * @param entidad el objeto dto que se mostrara en la vista modificar
     * @param vista el identificador de la vista modificar para mostrarse.
     */
    void mostrarVistaConEntidad(Object entidad, Vista vista);
    
    /**
     * Se utiliza para ordenarle a la vista a mostrar la vista correspondiente
     * al identificador, Para el caso donde se modifica o se regresa de la vista
     * modificar y se muestra la vista consultar
     * @param vista el identificador de la vista consultar para mostrarse
     */
    void mostrarVista(Vista vista);
    
    /**
     * Este método es especial para las vistas modificar que implementan esta
     * interfaz, Es usado para recibir el objeto interfaz y mostrar sus datos
     * en la vista modificar
     * @param entidad el objeto dto que se mostrara en la vista modificar
     */
    void mostrarEntidad(Object entidad);

    boolean confirmarCambio();
    
    UsuarioDTO obtenerUsuarioActual();
    
    void limpiar();
}