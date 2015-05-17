/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.ContestarExamenDELEGATE;
import java.util.List;
import modelo.dto.ExamenAsignadoDTO;
import modelo.dto.ExamenAsignadoPK;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author Alf
 */
public class CVContestarExamen {

    private final ContestarExamenDELEGATE contestarExamenDELEGATE;
    private ExamenAsignadoDTO examenAsignado;

    public CVContestarExamen() {
        contestarExamenDELEGATE = new ContestarExamenDELEGATE();
    }

    public List<ExamenAsignadoDTO> obtenerExamenesAsignados(UsuarioDTO alumno) {
        List<ExamenAsignadoDTO> examenes = contestarExamenDELEGATE.obtenerExamensAsignados(alumno);
        return examenes;
    }

    public void setExamenAsignado(ExamenAsignadoPK id) {
        examenAsignado = contestarExamenDELEGATE.obetnerExamenAsignado(id);
    }

    public ExamenAsignadoDTO obtenerExamenAsignado() {
        return examenAsignado;
    }

    public boolean actualizarExamen(ExamenAsignadoDTO examen) {
        return contestarExamenDELEGATE.actualizarExamen(examen);
    }

    public double calificarExamen(int nReactivos, List<String> respuestasAlumno,
            ExamenAsignadoDTO examen) {
        int buenas = 0;
        double calificacion;
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
        calificacion = (buenas / (double) nReactivos) * 100;
        examen.setCalificacion(calificacion);
        return calificacion;
    }

}
