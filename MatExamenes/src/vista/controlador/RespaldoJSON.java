/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.dto.ExamenAsignadoPK;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Alf
 */
public class RespaldoJSON {

    private static final String RESPUESTAS = "respuestas";
    private static final String ID_EXAMEN = "idExamen";
    private static final String ID_ALUMNO = "idAlumno";
    private static final String CONTESTADO = "contestado";
    private static final String RUTA_ARCHIVO = System.getProperty("user.home");
    private static final String ARCHIVO = "file";
    public static final int I_RESPUESTAS = 1;
    public static final int I_EXAMEN_ASIGNADO_PK = 0;
    private ArrayList respaldo;
    private JSONObject alumnoJSON;
    private JSONArray respuestasJSON;

    public RespaldoJSON() {
        alumnoJSON = new JSONObject();
        respuestasJSON = new JSONArray();
    }

    public void inicializarArchivo(int numeroReactivos, String respuesta,
            int idExamen, int idAlumno, boolean contestado) {
        FileOutputStream fos = null;
        try {
            for (int i = 0; i < numeroReactivos; i++) {
                respuestasJSON.add(respuesta);
            }
            alumnoJSON.put(ID_EXAMEN, String.valueOf(idExamen));
            alumnoJSON.put(ID_ALUMNO, String.valueOf(idAlumno));
            alumnoJSON.put(CONTESTADO, String.valueOf(contestado ? 1 : 0));
            alumnoJSON.put(RESPUESTAS, respuestasJSON);
            File file;
            file = new File(RUTA_ARCHIVO, ARCHIVO);
            if (file.exists()) {
                file.delete();
            }//        FileWriter fw = new FileWriter(file);
            //binarios
            fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeUTF(alumnoJSON.toJSONString());
            System.out.println("\nJSON Object: " + alumnoJSON);
            dos.flush();
            dos.close();

//        fw.writeUTF(alumnoJSON.toJSONString());
//        System.out.println("\nJSON Object: " + alumnoJSON);
//
//        fw.flush();
//        fw.close();
        } catch (IOException ex) {
            Logger.getLogger(RespaldoJSON.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(RespaldoJSON.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void modificarRespuesta(int numeroReactivo, String respuesta) {
        FileWriter fw = null;
        try {
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
            System.out.println("\nJSON Object: " + alumnoJSON);

            dos.flush();
            dos.close();
//            fw = new FileWriter(file);
//            fw.write(alumnoJSON.toJSONString());
//            System.out.println("\nJSON Object:" + alumnoJSON);
//            fw.flush();
//            fw.close();
        } catch (IOException ex) {
            //
        }
//        finally {
//            try {
//                fw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(RespaldoJSON.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }

    public boolean existeRespaldo() {
        boolean ok = false;
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        if (file.exists()) {
            ok = true;
            cargarRespaldo();
        }
        return ok;
    }

    private void cargarRespaldo() {
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        String datos = "";

        respaldo = new ArrayList();
//        Scanner sc;
        JSONParser parse = new JSONParser();

        //binarios
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
            DataInputStream dis = new DataInputStream(fis);
            datos = dis.readUTF();
            dis.close();
        } catch (IOException ex) {
        }

//        try {
//            sc = new Scanner(file);
//            datos = sc.nextLine();
//            sc.close();
//        } catch (FileNotFoundException ex) {
//            System.out.println("problemas archivo");
//        }
        try {
            Object obj = parse.parse(datos);
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
        } catch (ParseException ex) {
            System.out.println("exception");
        }
    }

    public ArrayList getRespaldo() {
        return respaldo;
    }

    public ExamenAsignadoPK getExamenAsignadoPKRespaldo() {
        return (ExamenAsignadoPK) respaldo.get(I_EXAMEN_ASIGNADO_PK);
    }

    public void eliminarRespaldo() {
        File file;
        file = new File(RUTA_ARCHIVO, ARCHIVO);
        file.delete();
    }

    public boolean estaContestado() {
        return Integer.parseInt((String) alumnoJSON.get(CONTESTADO)) == 1;
    }

    public void setContestado(boolean contestado) {
        FileWriter fw = null;
        try {
            int n = contestado ? 1 : 0;
            alumnoJSON.put(CONTESTADO, String.valueOf(n));
            File file;
            file = new File(RUTA_ARCHIVO, ARCHIVO);
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            DataOutputStream dos = new DataOutputStream(fos);

            dos.writeUTF(alumnoJSON.toJSONString());
            System.out.println("\nJSON Object: " + alumnoJSON);

            dos.flush();
            dos.close();
//            fw = new FileWriter(file);
//            fw.write(alumnoJSON.toJSONString());
//            System.out.println("\nJSON Object:" + alumnoJSON);
//            fw.flush();
//            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(RespaldoJSON.class.getName()).log(Level.SEVERE, null, ex);
        }
//        finally {
//            try {
//                fw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(RespaldoJSON.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }

    }
}
