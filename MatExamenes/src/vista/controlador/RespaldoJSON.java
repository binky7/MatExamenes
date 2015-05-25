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

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import modelo.dto.ExamenAsignadoPK;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Encargado de llevar el respaldo al momento de contestar examen.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class RespaldoJSON {

    /**
     * Utilizada para identificar las respuestas en el objeto JSON.
     */
    private static final String RESPUESTAS = "respuestas";
    /**
     * Utilizada para identificar el idExamen en el objeto JSON.
     */
    private static final String ID_EXAMEN = "idExamen";
    /**
     * Utilizada para identificar el idAlumno en el objeto JSON.
     */
    private static final String ID_ALUMNO = "idAlumno";
    /**
     * Utilizada para identificar el estado del examen en el objeto JSON.
     */
    private static final String CONTESTADO = "contestado";
    /**
     * Contiene la ruta por defecto donde se guardaran los archivos de respaldo.
     */
    private static final String RUTA_ARCHIVO = System.getProperty("user.home");
    /**
     * Contiene el nombre del archivo que se le dara al respaldo.
     */
    private static final String ARCHIVO = "respaldoMatExamenes.me";
    /**
     * Indice de la lista de respuestas, utilizado cuando obtenemos el respaldo
     * en una lista.
     */
    public static final int I_RESPUESTAS = 1;
    /**
     * Indice del ExamenAsignadoDTO, utilizado cuando obtenemos el respaldo en
     * una lista.
     */
    public static final int I_EXAMEN_ASIGNADO_PK = 0;
    /**
     * String usado para iniciar las respuestas de los respaldos.
     */
    private static final String RESPUESTA_INICIAL = ".";
    /**
     * ArrayList Usado para guardar los objetos ExamenAsignadoPK y una List de
     * las respuestas de los alumnos.
     */
    private ArrayList respaldo;
    /**
     * Objeto usado para crear el formato JSON de los datos del alumno.
     */
    private JSONObject alumnoJSON;
    /**
     * Objeto usado para crear un Array de objetos contenidos en el JSONObject.
     */
    private JSONArray respuestasJSON;

    /**
     * Crea un nuevo RespaldoJSON e inicializa sus atributos.
     */
    public RespaldoJSON() {
        alumnoJSON = new JSONObject();
        respuestasJSON = new JSONArray();
    }

    /**
     * Crea al archivo que servira de respaldo para contestar examen.
     *
     * @param numeroReactivos Número de reactivos contenidos en el examen.
     * @param idExamen ID del examen asignado.
     * @param idAlumno ID del alumno que contestara el examen.
     * @throws java.io.IOException Si hay problemas de escritura en el respaldo.
     */
    public void inicializarArchivo(int numeroReactivos,
            int idExamen, int idAlumno) throws IOException {
        FileOutputStream fos;
        for (int i = 0; i < numeroReactivos; i++) {
            respuestasJSON.add(RESPUESTA_INICIAL);
        }
        alumnoJSON.put(ID_EXAMEN, String.valueOf(idExamen));
        alumnoJSON.put(ID_ALUMNO, String.valueOf(idAlumno));
        alumnoJSON.put(CONTESTADO, String.valueOf(false));
        alumnoJSON.put(RESPUESTAS, respuestasJSON);

        File file = new File(RUTA_ARCHIVO, ARCHIVO);
        if (file.exists()) {
            file.delete();
        }
        fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeUTF(alumnoJSON.toJSONString());
        dos.flush();
        dos.close();
        fos.close();

    }

    /**
     * Modifica la respuesta del alumno en el respaldo.
     *
     * @param numeroReactivo Número de reactivo a modificar en el respaldo.
     * @param respuesta Respuesta del alumno en el respaldo.
     * @throws java.io.IOException Si hay problemas de escritura en el respaldo.
     */
    public void modificarRespuesta(int numeroReactivo, String respuesta)
            throws IOException {

        respuestasJSON.set(numeroReactivo, respuesta);
        alumnoJSON.put(RESPUESTAS, respuestasJSON);
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF(alumnoJSON.toJSONString());

        dos.flush();
        dos.close();

    }

    /**
     * Verifica si el archivo respaldo ha sido creado previamente.
     *
     * @return Verdadero si el respaldo existe.<br>
     * Falso de lo contrario.
     */
    public boolean existeRespaldo() {
        boolean ok = false;
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        if (file.exists()) {
            ok = true;
            try {
                cargarRespaldo();
            } catch (IOException ex) {
            }
        }
        return ok;
    }

    /**
     *
     * Lee el archivo con las respuestas del alumno, y lo transforma de vuelta a
     * un objeto JSONObject
     *
     * @throws java.io.IOException Si hay problemas de lectura en el respaldo.
     */
    private void cargarRespaldo() throws IOException {
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        String datos = "";

        respaldo = new ArrayList();
        JSONParser parse = new JSONParser();

        FileInputStream fis;

        fis = new FileInputStream(file);
        DataInputStream dis = new DataInputStream(fis);
        datos = dis.readUTF();
        dis.close();

        Object obj = null;
        try {
            obj = parse.parse(datos);
        } catch (ParseException ex) {
        }
        alumnoJSON = (JSONObject) obj;
        int idAlumno = Integer.parseInt((String) alumnoJSON.get(ID_ALUMNO));
        Integer idExamen = Integer.parseInt((String) alumnoJSON.get(ID_EXAMEN));
        respuestasJSON = (JSONArray) alumnoJSON.get(RESPUESTAS);

        List<String> respuestas = new ArrayList<>();

        for (int i = 0; i < respuestasJSON.size(); i++) {
            respuestas.add((String) respuestasJSON.get(i));
        }

        ExamenAsignadoPK eapk = new ExamenAsignadoPK(idExamen, idAlumno);
        respaldo.add(eapk);
        respaldo.add(respuestas);

    }

    /**
     *
     * @return ArrayList Contenedor de los id, examen, alumno y sus respuestas.
     */
    public ArrayList getRespaldo() {
        return respaldo;
    }

    /**
     *
     * @return Retorna ExamenAsignadoDTO utilizado para consultar el examen
     * asignado del alumno.
     */
    public ExamenAsignadoPK getExamenAsignadoPKRespaldo() {
        return (ExamenAsignadoPK) respaldo.get(I_EXAMEN_ASIGNADO_PK);
    }

    /**
     * Elimina el respaldo.
     */
    public void eliminarRespaldo() {
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        file.delete();
    }

    /**
     * Verifica si el respaldo se encuentra en el estado contestado.
     *
     * @return Verdadero si el respaldo esta en estado contestado.<br>
     * Falso de lo contrario.
     */
    public boolean estaContestado() {
        return Boolean.parseBoolean((String)alumnoJSON.get(CONTESTADO));
    }

    /**
     * Cambia el estado del respaldo a contestado
     *
     * @throws java.io.IOException Si hay problemas de escritura en el respaldo.
     */
    public void setContestado() throws IOException {
        alumnoJSON.put(CONTESTADO, String.valueOf(true));
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fos = new FileOutputStream(file);
        DataOutputStream dos = new DataOutputStream(fos);

        dos.writeUTF(alumnoJSON.toJSONString());

        dos.flush();
        dos.close();
    }
}
