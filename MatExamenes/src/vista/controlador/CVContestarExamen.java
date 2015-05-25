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
package vista.controlador;

import control.delegate.ContestarExamenDELEGATE;
import java.util.List;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Contestar Exámenes al delegate del mismo caso de uso, en el cuál se tiene
 * acceso a capas inferiores para acceder a la base de datos. También mantiene
 * en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class CVContestarExamen {

    /**
     * Índice número reactivos.
     */
    public static final int N_REACTIVOS = 2;
    /**
     * Índice respuestas correctas.
     */
    public static final int BUENAS = 1;
    /**
     * Índice calificación alumno.
     */
    public static final int CALIFICACION = 0;

    /**
     * Objeto delegate del caso de uso Mantener Usuarios. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final ContestarExamenDELEGATE contestarExamenDELEGATE;
    /**
     * Examen que el alumno se encuentra contestando o contesto.
     */
    private ExamenAsignadoDTO examenAsignado;
    /**
     * Lista que almacena los exámenes asignados que posee el alumno.
     */
    List<ExamenAsignadoDTO> examenesAsignados;

    /**
     * Crea un objeto CVContestarExamen e inicializa sus atributos.
     */
    public CVContestarExamen() {
        contestarExamenDELEGATE = new ContestarExamenDELEGATE();
        examenAsignado = null;
    }

    /**
     * Retorna una lista de ExamenAsignadoDTO del alumno.
     *
     * @param alumno Alumno por el cual se buscaran los exámenes asigandos.
     * @return Lista de exámenes asignados para el alumno.<br>
     * Si no tiene exámenes retornara null.
     */
    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno) {
        examenesAsignados = contestarExamenDELEGATE.obtenerExamensAsignados(alumno);
        return examenesAsignados;
    }

    /**
     * Busca el examen asignado y lo guarda en el atributo examenAsignado.
     *
     * @param id ExamenAsignadoPK por el cual se buscara el ExamenAsignadoDTO.
     */
    public void setExamenAsignado(ExamenAsignadoPK id) {
        examenAsignado = contestarExamenDELEGATE.obetnerExamenAsignado(id);
    }

    /**
     * Devuelve la lista previamente busacada en la base de dastos, de los
     * examenes asigandos para el alumno.
     *
     * @return Lista de ExamenAsignadoDTO
     */
    public List<ExamenAsignadoDTO> getExamenesAsignados() {
        return examenesAsignados;
    }

    /**
     * Una vez que se seleccione el examen, se usara este método para guardar
     * una referencia a ese mismo examen.
     *
     * @return El examenAsignado del alumno.
     */
    public ExamenAsignadoDTO getExamenAsignado() {
        return examenAsignado;
    }

    /**
     * Actualiza el examen en la base de datos.
     *
     * @param examen El examen que fue contestado por el alumno.
     * @return Verdadero en caso de que la actualización fue exitosa.<br>
     * Falso de otra forma.
     */
    public boolean actualizarExamen(ExamenAsignadoDTO examen) {
        return contestarExamenDELEGATE.actualizarExamen(examen);
    }

    /**
     * Retorna la calificación obtenid por el alumno de 0 a 10.
     *
     * @param nReactivos Número de reactivos en el examen.
     * @param respuestasAlumno Lista de respuestas del alumno.
     * @param examen El examen asignado al alumno.
     * @return La calificación obtenida por el alumno.
     */
    public double[] calificarExamen(int nReactivos, List<String> respuestasAlumno,
            ExamenAsignadoDTO examen) {
        int buenas = 0;
        double calificacion[] = new double[3];
        for (int i = 0; i < nReactivos; i++) {
            try {
                examen.getReactivos().get(i).setRespuestaAlumno(respuestasAlumno.get(i));
                String respuesta = examen.getReactivos().get(i).getRespuestaReactivo();
                String respuestaAlumno = examen.getReactivos().get(i).getRespuestaAlumno();
                if (respuesta.compareTo(respuestaAlumno) == 0) {
                    buenas++;
                }
            } catch (IndexOutOfBoundsException e) {
                //reactivo no respondido
            }
        }
        calificacion[CALIFICACION] = (buenas / (double) nReactivos) * 10;
        calificacion[BUENAS] = buenas;
        calificacion[N_REACTIVOS] = nReactivos;
        examen.setCalificacion(calificacion[0]);
        return calificacion;
    }

}
