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
import modelo.dto.UsuarioDTO.Tipo;

/**
 *
 * @author FernandoEnrique
 */
public class CVMantenerGrupos {

    private final MantenerGruposDELEGATE gruposDELEGATE;
    private final MantenerUsuariosDELEGATE usuariosDELEGATE;

    //Listas de dto necesarios
    //dtos para consultar
    private List<GrupoDTO> listaGrupos;

    //dto para modificar
    //Es necesario matener en todo momento los objetos obtenidos por hibernate
    //Si se quiere modificar o eliminar dichos objetos, por eso la razon de este
    //atributo
    private GrupoDTO grupo;

    //Lista de dto necesarios para agregar maestro
    public List<CursoDTO> listaCursos;
    public List<UsuarioDTO> listaMaestros;
    private HashMap<CursoDTO, UsuarioDTO> mapaMaestros;

    //Lista de dto necesarios para agregar alumnos
    private List<UsuarioDTO> listaAlumnos;
    private List<UsuarioDTO> listaEliminados;

    public CVMantenerGrupos() {
        gruposDELEGATE = new MantenerGruposDELEGATE();
        usuariosDELEGATE = new MantenerUsuariosDELEGATE();
        listaCursos = new ArrayList<>();
        listaAlumnos = new ArrayList<>();
        listaMaestros = new ArrayList<>();
        listaEliminados = new ArrayList<>();
        grupo = new GrupoDTO();
        mapaMaestros = null;
    }

    public List<CursoDTO> obtenerCursos(int indexMaestro) {
        this.listaCursos = gruposDELEGATE.obtenerCursos();
        UsuarioDTO maestro = this.listaMaestros.get(indexMaestro);
        if (this.mapaMaestros != null) {
            HashMap<CursoDTO, UsuarioDTO> mapaMaestro = this.mapaMaestros;
            for (CursoDTO curso : mapaMaestro.keySet()) {
                if (maestro.getId() == mapaMaestro.get(curso).getId()) {
                    this.listaCursos.remove(curso);
                }
            }
        }
        return this.listaCursos;
    }

    public Integer guardarGrupo(GrupoDTO objGrupo) {
        Integer id = null;
        if (grupo != null) {
            this.grupo.setNombre(objGrupo.getNombre());
            this.grupo.setGrado(objGrupo.getGrado());
            this.grupo.setTurno(objGrupo.getTurno());
            this.grupo.setAlumnos(this.listaAlumnos);
            this.grupo.setMaestros(this.mapaMaestros);
            id = gruposDELEGATE.guardarGrupo(this.grupo);
        }
        return id;
    }

    public List<GrupoDTO> obtenerGrupos() {
        this.listaGrupos = gruposDELEGATE.obtenerGrupos();
        return listaGrupos;
    }

    public GrupoDTO obtenerGrupo(int indexGrupo) {
        GrupoDTO objGrupo = null;
        if (this.listaGrupos != null && !this.listaGrupos.isEmpty()) {
            objGrupo = listaGrupos.get(indexGrupo);
            objGrupo = gruposDELEGATE.obtenerGrupo(objGrupo.getId());
        }
        this.grupo = objGrupo;
        HashMap<CursoDTO, UsuarioDTO> mapa = new HashMap<>();
        for (CursoDTO curso : grupo.getMaestros().keySet()) {
            UsuarioDTO maestro = grupo.getMaestros().get(curso);
            mapa.put(curso, maestro);
        }
        this.mapaMaestros = mapa;
        this.grupo.setMaestros(mapa);
        return objGrupo;
    }

    public boolean modificarGrupo(GrupoDTO objGrupo) {
        boolean ok = false;
        if (this.grupo != null) {
            this.grupo.setNombre(objGrupo.getNombre());
            this.grupo.setGrado(objGrupo.getGrado());
            this.grupo.setTurno(objGrupo.getTurno());
            ok = gruposDELEGATE.modificarGrupo(this.grupo);
        }
        return ok;
    }

    public boolean eliminarGrupo(int indexGrupo) {
        boolean ok = false;
        if (this.listaGrupos != null && !this.listaGrupos.isEmpty()) {
            GrupoDTO objGrupo = this.listaGrupos.get(indexGrupo);
            ok = gruposDELEGATE.eliminarGrupo(objGrupo);
            if (ok) {
                this.listaGrupos.remove(indexGrupo);
            }
        }
        return ok;
    }

    public List<UsuarioDTO> obtenerAlumnos(String busqueda) {
        listaAlumnos = usuariosDELEGATE.obtenerAlumnosPorApellido(busqueda);
        List<Integer> ids = new ArrayList<>();
        for (UsuarioDTO listaAlumno : listaAlumnos) {
            ids.add(listaAlumno.getId());
        }
        for (UsuarioDTO alumno : usuariosDELEGATE.obtenerAlumnosPorApellidoM(busqueda)) {
            if (!ids.contains(alumno.getId())) {
                ids.add(alumno.getId());
                listaAlumnos.add(alumno);
            }
        }
        for (UsuarioDTO alumno : usuariosDELEGATE.obtenerAlumnosPorNombre(busqueda)) {
            if (!ids.contains(alumno.getId())) {
                ids.add(alumno.getId());
                listaAlumnos.add(alumno);
            }
        }
        listaAlumnos.removeAll(listaEliminados);
        listaAlumnos.addAll(listaEliminados);
        return listaAlumnos;
    }

    public List<UsuarioDTO> obtenerMaestros(String busqueda) {
        listaMaestros = usuariosDELEGATE.obtenerUsuariosPorApellido(busqueda, Tipo.Maestro);
        List<Integer> ids = new ArrayList<>();
        for (UsuarioDTO listaMaestro : listaMaestros) {
            ids.add(listaMaestro.getId());
        }
        for (UsuarioDTO maestro : usuariosDELEGATE.obtenerUsuariosPorApellidoM(busqueda, Tipo.Maestro)) {
            if (!ids.contains(maestro.getId())) {
                ids.add(maestro.getId());
                listaMaestros.add(maestro);
            }
        }
        for (UsuarioDTO maestro : usuariosDELEGATE.obtenerUsuariosPorNombre(busqueda, Tipo.Maestro)) {
            if (!ids.contains(maestro.getId())) {
                ids.add(maestro.getId());
                listaMaestros.add(maestro);
            }
        }
        return listaMaestros;
    }

    public List<UsuarioDTO> agregarAlumnos(List<Integer> indexesAlumnos) {
        List<UsuarioDTO> alumnos = new ArrayList<>();
        int cont = indexesAlumnos.size();
        for (int x = 0; x < cont; x++) {
            int index = indexesAlumnos.get(x);
            if (this.listaAlumnos != null && !this.listaAlumnos.isEmpty()) {
                UsuarioDTO alumno = this.listaAlumnos.get(index);
                alumnos.add(alumno);
            }
        }
        alumnos = agregarAlumnosLista(alumnos);
        this.listaEliminados.removeAll(alumnos);
        return alumnos;
    }

    public List<UsuarioDTO> agregarAlumnosLista(List<UsuarioDTO> alumnos) {
        if (this.grupo == null) {
            this.grupo = new GrupoDTO();
        }
        this.listaAlumnos = this.grupo.getAlumnos();
        if (this.listaAlumnos == null) {
            this.listaAlumnos = new ArrayList<>();
        }
        int cont = alumnos.size();
        List<UsuarioDTO> repetidos = new ArrayList<>();
        for (int i = 0; i < cont; i++) {
            boolean ban = false;
            for (UsuarioDTO listaAlumno : this.listaAlumnos) {
                if (listaAlumno.getId() == alumnos.get(i).getId()) {
                    ban = true;
                }
            }
            if (ban) {
                repetidos.add(alumnos.get(i));
            }
        }
        alumnos.removeAll(repetidos);
        this.listaAlumnos.addAll(alumnos);
        this.grupo.setAlumnos(this.listaAlumnos); //Lista de alumnos no repetidos
        return this.grupo.getAlumnos();
    }

    public HashMap<CursoDTO, UsuarioDTO> agregarMaestro(List indexesCursos, int indexMaestro) {
        UsuarioDTO maestro;
        CursoDTO curso;
        HashMap<CursoDTO, UsuarioDTO> mapaMaestro = null;
        if (this.listaMaestros != null && !this.listaMaestros.isEmpty()
                && this.listaCursos != null && !this.listaCursos.isEmpty()) {
            for (Object indexCurso : indexesCursos) {
                maestro = this.listaMaestros.get(indexMaestro);
                curso = this.listaCursos.get((int) indexCurso);
                mapaMaestro = agregarMaestro(curso, maestro);
            }
        }
        return mapaMaestro;
    }

    public HashMap<CursoDTO, UsuarioDTO> agregarMaestro(CursoDTO curso, UsuarioDTO maestro) {
        if (this.grupo == null) {
            this.grupo = new GrupoDTO();
        }
        this.mapaMaestros = (HashMap<CursoDTO, UsuarioDTO>) this.grupo.getMaestros();
        if (this.mapaMaestros == null || this.mapaMaestros.isEmpty()) {
            Map<CursoDTO, UsuarioDTO> maestrosMap;
        }
        mapaMaestros.put(curso, maestro);
        grupo.setMaestros(mapaMaestros);
        return (HashMap<CursoDTO, UsuarioDTO>) grupo.getMaestros();
    }

    public void removerAlumnos(List<Integer> indexes) {
        List<UsuarioDTO> listaRemover = new ArrayList<>();
        if (this.grupo != null) {
            this.listaAlumnos = grupo.getAlumnos();
            if (listaAlumnos != null) {
                int cont = indexes.size();
                for (int i = 0; i < cont; i++) {
                    listaRemover.add(this.listaAlumnos.get(indexes.get(i)));
                }
                this.listaAlumnos.removeAll(listaRemover);
            }
        }
    }

    public void agregarEliminados(List<UsuarioDTO> eliminados) {
        this.listaEliminados.removeAll(eliminados);
        this.listaEliminados.addAll(eliminados);
    }

    public void removerMaestro(String nombreCurso) {
        if (grupo != null) {
            this.mapaMaestros = (HashMap<CursoDTO, UsuarioDTO>) this.grupo.getMaestros();
            if (this.mapaMaestros != null) {
                for (CursoDTO curso : this.mapaMaestros.keySet()) {
                    if (curso.getNombre().equals(nombreCurso)) {
                        mapaMaestros.remove(curso);
                        break;
                    }
                }
            }
        }
    }

    public boolean verificarExistencia(GrupoDTO grupo) {
        return gruposDELEGATE.verificarExistencia(grupo);
    }

    public void liberarMemoriaRegistrar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaEliminados = new ArrayList<>();
        this.mapaMaestros = null;
    }

    public void liberarMemoriaConsultar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaEliminados = new ArrayList<>();
        this.mapaMaestros = null;
    }

    public void liberarMemoriaModificar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaEliminados = new ArrayList<>();
        this.mapaMaestros = null;
    }
}
