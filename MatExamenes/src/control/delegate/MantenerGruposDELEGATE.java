/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExamenes.
 *
 * MatExamenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExamenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package control.delegate;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import remoteAccess.Enlace;

/**
 * Esta clase se encarga de enviar las peticiones del control vista del caso de
 * uso de Mantener Grupos a la interface Persistencia, la cuál oculta la forma
 * en la cuál se tiene acceso a capas inferiores, el Delegate se encarga de
 * delegar el trabajo a esta interface y obtener los datos que se le pidan.
 *
 * @author Fernando Enrique Avendaño Hernández.
 * @version 1 18 Mayo 2015.
 */
public class MantenerGruposDELEGATE {

    /**
     * Obtiene los cursos existentes llamando a la interface Persistencia.
     *
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null.
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = null;
        try {
            listaCursos = Enlace.getPersistencia()
                    .obtenerEntidades(CursoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex);
        }
        return listaCursos;
    }

    /**
     * Recibe el grupo a guardar y lo envia a la interface Persistencia.
     *
     * @return regresa el id del grupo guardado o -1 si no se pudo guardar.
     */
    public int guardarGrupo(GrupoDTO grupo) {
        int id = -1;
        try {
            id = Enlace.getPersistencia().guardarEntidad(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    /**
     * Obtiene los grupos existentes llamando a la interface Persistencia.
     *
     * @return una lista de GrupoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null.
     */
    public List<GrupoDTO> obtenerGrupos() {
        List<GrupoDTO> listaGrupos = null;
        try {
            listaGrupos = Enlace.getPersistencia().obtenerEntidades(GrupoDTO.class);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGrupos;
    }

    /**
     * Este método es utilizado para obtener el objeto GrupoDTO perteneciente al
     * id ingresado
     *
     * @param id el id del grupo deseado.
     * @return el objeto GrupoDTO con sus relaciones completamente
     * inicializadas.
     */
    public GrupoDTO obtenerGrupo(int id) {
        GrupoDTO grupo = null;
        try {
            grupo = Enlace.getPersistencia().obtenerGrupo(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupo;
    }

    /**
     * Este método es utilizado para modificar el objeto GrupoDTO perteneciente
     * al objeto GrupoDTO ingresado.
     *
     * @param grupo el objeto GrupoDTO.
     * @return vedadero si se modifico o falso si no se pudo modificar.
     */
    public boolean modificarGrupo(GrupoDTO grupo) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().modificarEntidad(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    /**
     * Recibe un objeto GrupoDTO y lo envia a Persistencia para que sea
     * eliminado.
     *
     * @param objGrupo el grupo a eliminar.
     * @return verdader si se elimino y falso si no se pudo eliminar.
     */
    public boolean eliminarGrupo(GrupoDTO objGrupo) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().eliminarEntidad(objGrupo);

        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    /**
     * Recibe un objeto GrupoDTO y lo envia a Persistencia para que verifiqie si
     * ya existe uno con el mismo nombre registrado.
     *
     * @param grupo el grupo a verificar.
     * @return verdadero si ya existe, falso si no existe.
     */
    public boolean verificarExistencia(GrupoDTO grupo) {
        try {
            return Enlace.getPersistencia().verificarExistencia(grupo);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(MantenerGruposDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
