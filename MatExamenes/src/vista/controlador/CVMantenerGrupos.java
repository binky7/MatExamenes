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

    private MantenerGruposDELEGATE gruposDELEGATE;
    private MantenerUsuariosDELEGATE usuariosDELEGATE;

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

    public CVMantenerGrupos() {
        gruposDELEGATE = new MantenerGruposDELEGATE();
        usuariosDELEGATE = new MantenerUsuariosDELEGATE();
        listaCursos = new ArrayList<>();
    }

    public List<CursoDTO> obtenerCursos() {
        this.listaCursos = gruposDELEGATE.obtenerCursos();
        return listaCursos;
    }

    public Integer guardarGrupo(GrupoDTO objGrupo) {
        Integer id;
        if (grupo != null) {
            this.grupo.setNombre(objGrupo.getNombre());
            this.grupo.setGrado(objGrupo.getGrado());
            this.grupo.setTurno(objGrupo.getTurno());
            this.grupo.setAlumnos(this.listaAlumnos);
            this.grupo.setMaestros(this.mapaMaestros);
        }
        id = gruposDELEGATE.guardarGrupo(this.grupo);
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
            Object[] fila = new Object[6];
            fila[0] = false;
            fila[1] = String.valueOf(maestro.getId());
            fila[2] = maestro.getNombre();
            fila[3] = maestro.getApellidoPaterno();
            fila[4] = maestro.getApellidoMaterno();
            fila[5] = curso.getNombre();
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

    public List<UsuarioDTO> obtenerAlumnosPorApellido(String apellidoPaterno) {
        this.listaAlumnos = usuariosDELEGATE.obtenerUsuariosPorApellido(apellidoPaterno);
        return listaAlumnos;
    }

    public List<UsuarioDTO> obtenerMaestrosPorApellido(String apellidoPaterno) {
        this.listaMaestros = usuariosDELEGATE.obtenerUsuariosPorApellido(apellidoPaterno);
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
        return alumnos;
    }

    public List<UsuarioDTO> agregarAlumnosLista(List<UsuarioDTO> listaAlumnos) {
        if (this.grupo == null) {
            this.grupo = new GrupoDTO();
        }
        this.listaAlumnos = this.grupo.getAlumnos();
        if (this.listaAlumnos == null) {
            this.listaAlumnos = new ArrayList<>();
        }
        int cont = listaAlumnos.size();
        List<UsuarioDTO> repetidos = new ArrayList<>();
        for (int i = 0; i < cont; i++) {
            boolean ban = false;
            for (int j = 0; j < this.listaAlumnos.size(); j++) {
                if (this.listaAlumnos.get(j).getId() == listaAlumnos.get(j).getId()) {
                    ban = true;
                }
            }
            if (ban) {
                repetidos.add(listaAlumnos.get(i));
            }
        }
        listaAlumnos.removeAll(repetidos);
        this.listaAlumnos.addAll(listaAlumnos);
        this.grupo.setAlumnos(this.listaAlumnos); //Lista de alumnos no repetidos
        return this.grupo.getAlumnos();
    }

    public HashMap<CursoDTO, UsuarioDTO> agregarMaestro(int indexCurso, int indexMaestro) {
        UsuarioDTO maestro = null;
        CursoDTO curso = null;
        if (this.listaMaestros != null && !this.listaMaestros.isEmpty()
                && this.listaCursos != null && !this.listaCursos.isEmpty()) {
            maestro = this.listaMaestros.get(indexMaestro);
            curso = this.listaCursos.get(indexCurso);
        }
        HashMap<CursoDTO, UsuarioDTO> mapaMaestro = agregarMaestro(curso, maestro);
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

    public void removerAlumnos(List<Integer> indexesAlumnos) {
        List<UsuarioDTO> listaRemover = new ArrayList<>();
        if (this.grupo != null) {
            this.listaAlumnos = grupo.getAlumnos();
            if (listaAlumnos != null) {
                int cont = indexesAlumnos.size();
                for (int i = 0; i < cont; i++) {
                    listaRemover.add(this.listaAlumnos.get(indexesAlumnos.get(i)));
                }
                this.listaAlumnos.removeAll(listaRemover);
            }
        }
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

    public void liberarMemoriaRegistrar() {
        this.grupo = null;
        this.listaAlumnos = null;
        this.listaCursos = null;
        this.listaGrupos = null;
        this.listaMaestros = null;
        this.mapaMaestros = null;
    }

    public void liberarMemoriaConsultar() {
        this.grupo = null;
        this.listaAlumnos = null;
        this.listaCursos = null;
        this.listaGrupos = null;
        this.listaMaestros = null;
        this.mapaMaestros = null;
    }

    public void liberarMemoriaModificar() {
        this.grupo = null;
        this.listaAlumnos = null;
        this.listaCursos = null;
        this.listaGrupos = null;
        this.listaMaestros = null;
        this.mapaMaestros = null;
    }
}
