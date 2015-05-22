/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;
import remoteAccess.Enlace;

/**
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class ContestarExamenDELEGATE {

    /**
     * Retorna una lista de ExamenAsignadoDTO del alumno.
     *
     * @param alumno Alumno por el cual se buscaran los exámenes asigandos.
     * @return Lista de exámenes asignados para el alumno.<br>
     * Si no tiene exámenes retornara null.
     */
    public List<ExamenAsignadoDTO> obtenerExamensAsignados(UsuarioDTO alumno) {
        List<ExamenAsignadoDTO> ea = null;
        try {
            ea = Enlace.getPersistencia().obtenerExamenesAsignados(alumno);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ea;
    }

    /**
     * Busca en la base de datos, el ExamenAsigndoDTO correspondiente al alumno
     * que solicito el examen.
     *
     * @param id ExamenAsignadoPK con los identificadores del alumno y le
     * examen.
     * @return El exmen correspondiente al alumno.
     */
    public ExamenAsignadoDTO obetnerExamenAsignado(ExamenAsignadoPK id) {
        ExamenAsignadoDTO examen = null;
        try {
            examen = Enlace.getPersistencia().obtenerExamenAsignado(id);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return examen;
    }

    /**
     * Actualiza el examen en la base de datos.
     *
     * @param examen El examen que fue contestado por el alumno.
     * @return Verdadero en caso de que la actualización fue exitosa.<br>
     * Falso de otra forma.
     */
    public boolean actualizarExamen(ExamenAsignadoDTO examen) {
        boolean ok = false;
        try {
            ok = Enlace.getPersistencia().modificarEntidad(examen);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ContestarExamenDELEGATE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

}
