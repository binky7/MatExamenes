/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerGruposDELEGATE;
import control.delegate.MantenerUsuariosDELEGATE;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.dto.CursoDTO;
import modelo.dto.GrupoDTO;
import modelo.dto.UsuarioDTO;

/**
 *
 * @author FernandoEnrique
 */
public class CVMantenerGrupos {

    private MantenerGruposDELEGATE mantenerGruposDELEGATE;
    private MantenerUsuariosDELEGATE mantenerUsuariosDELEGATE;

    //Listas de dto necesarios
    //dtos para consultar
    private List<GrupoDTO> grupos;

    //dto para modificar
    //Es necesario matener en todo momento los objetos obtenidos por hibernate
    //Si se quiere modificar o eliminar dichos objetos, por eso la razon de este
    //atributo
    private GrupoDTO grupo;

    //Lista de dto necesarios para agregar maestro
    public List<CursoDTO> cursos;
    public List<UsuarioDTO> maestros;
    private HashMap<CursoDTO, UsuarioDTO> maestrosMap;

    //Lista de dto necesarios para agregar alumnos
    private List<UsuarioDTO> alumnos;

    public CVMantenerGrupos() {
        mantenerGruposDELEGATE = new MantenerGruposDELEGATE();
        mantenerUsuariosDELEGATE = new MantenerUsuariosDELEGATE();
        cursos = new ArrayList<>();
    }

    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = mantenerGruposDELEGATE.obtenerCursos();
        this.cursos = listaCursos;
        return listaCursos;
    }

    public Integer guardarGrupo(GrupoDTO objGrupo) {
        Integer id = null;
        if (grupo != null) {
            this.grupo.setNombre(objGrupo.getNombre());
            this.grupo.setGrado(objGrupo.getGrado());
            this.grupo.setTurno(objGrupo.getTurno());
            this.grupo.setAlumnos(this.alumnos);
            this.grupo.setMaestros(this.maestrosMap);
        }
        id = mantenerGruposDELEGATE.guardarGrupo(this.grupo);
        return id;
    }

    public List<GrupoDTO> obtenerGrupos() {
        List<GrupoDTO> listaGrupos = mantenerGruposDELEGATE.obtenerGrupos();
        this.grupos = listaGrupos;
        return listaGrupos;
    }

    public GrupoDTO obtenerGrupo(int indexGrupo) {
        GrupoDTO objGrupo = null;
        if (this.grupos != null && !this.grupos.isEmpty()) {
            objGrupo = grupos.get(indexGrupo);
            objGrupo = mantenerGruposDELEGATE.obtenerGrupo(objGrupo.getId());
        }
        this.grupo = objGrupo;
        HashMap<CursoDTO, UsuarioDTO> mapa = new HashMap<>();
        for (CursoDTO curso : grupo.getMaestros().keySet()) {
            UsuarioDTO maestro = grupo.getMaestros().get(curso);
            mapa.put(curso, maestro);
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
        }
        this.maestrosMap = mapa;
        this.grupo.setMaestros(mapa);
        return objGrupo;
    }

    public boolean modificarGrupo(GrupoDTO objGrupo) {
        boolean ok = false;
        if (this.grupo != null) {
            this.grupo.setNombre(objGrupo.getNombre());
            this.grupo.setGrado(objGrupo.getGrado());
            this.grupo.setTurno(objGrupo.getTurno());
            ok = mantenerGruposDELEGATE.modificarGrupo(this.grupo);
        }
        return ok;
    }

    public boolean eliminarGrupo(int indexGrupo) {
        boolean ok = false;
        if (this.grupos != null && !this.grupos.isEmpty()) {
            GrupoDTO objGrupo = this.grupos.get(indexGrupo);
            ok = mantenerGruposDELEGATE.eliminarGrupo(objGrupo);
            if (ok) {
                this.grupos.remove(indexGrupo);
            }
        }
        return ok;
    }

    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellidoPaterno) {
        List<UsuarioDTO> listaAlumnos = mantenerUsuariosDELEGATE.obtenerUsuariosPorApellido(apellidoPaterno);
        this.alumnos = listaAlumnos;
        return listaAlumnos;
    }

    public List<UsuarioDTO> obtenerMaestrosPorApellido(String apellidoPaterno) {
        List<UsuarioDTO> listaMaestros = mantenerUsuariosDELEGATE.obtenerUsuariosPorApellido(apellidoPaterno);
        this.maestros = listaMaestros;
        return listaMaestros;
    }

    public List<UsuarioDTO> agregarAlumnos(List<Integer> indexesAlumnos) {
        List<UsuarioDTO> listaAlumnos = new ArrayList<>();
        int cont = indexesAlumnos.size();
        for (int x = 0; x < cont; x++) {
            int index = indexesAlumnos.get(x);
            if (this.alumnos != null && !this.alumnos.isEmpty()) {
                UsuarioDTO alumno = this.alumnos.get(index);
                listaAlumnos.add(alumno);
            }
        }
        listaAlumnos = agregarAlumnosLista(listaAlumnos);
        return listaAlumnos;
    }

    public List<UsuarioDTO> agregarAlumnosLista(List<UsuarioDTO> listaAlumnos) {
        if (this.grupo == null) {
            this.grupo = new GrupoDTO();
        }
        this.alumnos = this.grupo.getAlumnos();
        if (this.alumnos == null) {
            this.alumnos = new ArrayList<>();
        }
        int cont = listaAlumnos.size();
        List<UsuarioDTO> repetidos = new ArrayList<>();
        for (int x = 0; x < cont; x++) {
            boolean ban = false;
            for (int i = 0; i < alumnos.size(); i++) {
                if (alumnos.get(i).getId() == listaAlumnos.get(x).getId()) {
                    ban = true;
                }
            }
            if (ban) {
                repetidos.add(listaAlumnos.get(x));
            }
        }
        listaAlumnos.removeAll(repetidos);
        this.alumnos.addAll(listaAlumnos);
        this.grupo.setAlumnos(this.alumnos); //Lista de alumnos no repetidos
        return this.grupo.getAlumnos();
    }

    public HashMap<CursoDTO, UsuarioDTO> agregarMaestro(int indexCurso, int indexMaestro) {
        UsuarioDTO maestro = null;
        CursoDTO curso = null;
        if (this.maestros != null && !this.maestros.isEmpty()
                && this.cursos != null && !this.cursos.isEmpty()) {
            maestro = this.maestros.get(indexMaestro);
            curso = this.cursos.get(indexCurso);
        }
        HashMap<CursoDTO, UsuarioDTO> mapaMaestro = agregarMaestro(curso, maestro);
        return mapaMaestro;
    }

    public HashMap<CursoDTO, UsuarioDTO> agregarMaestro(CursoDTO curso, UsuarioDTO maestro) {
        if (this.grupo == null) {
            this.grupo = new GrupoDTO();
        }
        this.maestrosMap = (HashMap<CursoDTO, UsuarioDTO>) this.grupo.getMaestros();
        if (this.maestrosMap == null || this.maestrosMap.isEmpty()) {
            Map<CursoDTO, UsuarioDTO> maestrosMap;
        }
        maestrosMap.put(curso, maestro);
        grupo.setMaestros(maestrosMap);
        return (HashMap<CursoDTO, UsuarioDTO>) grupo.getMaestros();
    }

    public void removerAlumnos(List<Integer> indexesAlumnos) {
        List<UsuarioDTO> listaRemover = new ArrayList<>();
        if (this.grupo != null) {
            this.alumnos = grupo.getAlumnos();
            if (alumnos != null) {
                int cont = indexesAlumnos.size();
                for (int x = 0; x < cont; x++) {
                    listaRemover.add(this.alumnos.get(indexesAlumnos.get(x)));
                }
                this.alumnos.removeAll(listaRemover);
            }
        }
    }

    public void removerMaestro(String nombreCurso) {
        if (grupo != null) {
            this.maestrosMap = (HashMap<CursoDTO, UsuarioDTO>) this.grupo.getMaestros();
            if (this.maestrosMap != null) {
                for (CursoDTO curso : this.maestrosMap.keySet()) {
                    if (curso.getNombre().equals(nombreCurso)) {
                        maestrosMap.remove(curso);
                    }
                }
            }
        }
    }

    public void liberarMemoriaRegistrar() {
    }

    public void liberarMemoriaConsultar() {
    }

    public void liberarMemoriaModificar() {
    }
}
