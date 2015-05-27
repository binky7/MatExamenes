/*
 * Copyright (C) 2015 Fernando Enrique Avendaño Hernández
 *
 * This file is part of MatExámenes.
 *
 * MatExámenes is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 *
 * MatExámenes is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
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
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Mantener Grupos al delegate del mismo caso de uso, en el cuál se tiene
 * acceso a capas inferiores para acceder a la base de datos. También mantiene
 * en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Fernando Enrique Avendaño Hernández
 * @version 1 18 Mayo 2015
 */
public class CVMantenerGrupos {

    /**
     * Objeto delegate del caso de uso Mantener Grupos, delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación.
     */
    private final MantenerGruposDELEGATE gruposDELEGATE;
    /**
     * Objeto delegate del caso de uso Mantener Usuarios, delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación.
     */
    private final MantenerUsuariosDELEGATE usuariosDELEGATE;

    /**
     * Lista de objetos GrupoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<GrupoDTO> listaGrupos;

    /**
     * Objeto ReactivoDTO que se utilizará como almacén temporal para utilizar
     * su información en las vistas.
     */
    private GrupoDTO grupo;

    /**
     * Lista de objetos CursoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    public List<CursoDTO> listaCursos;

    /**
     * Lista de objetos UsuarioDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas como maestros.
     */
    public List<UsuarioDTO> listaMaestros;

    /**
     * Mapa de objetos CursoDTO y UsuarioDTO que se utilizará como almacén
     * temporal para utilizar su información en las vistas.
     */
    private HashMap<CursoDTO, UsuarioDTO> mapaMaestros;

    /**
     * Lista de objetos UsuarioDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas como alumnos.
     */
    private List<UsuarioDTO> listaAlumnos;

    /**
     * Lista de objetos UsuarioDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas como alumnos removidos.
     */
    private List<UsuarioDTO> listaRemovidos;

    /**
     * Crea un objeto CVMantenerReactivos e inicializa sus atributos.
     */
    public CVMantenerGrupos() {
        gruposDELEGATE = new MantenerGruposDELEGATE();
        usuariosDELEGATE = new MantenerUsuariosDELEGATE();
        listaCursos = new ArrayList<>();
        listaAlumnos = new ArrayList<>();
        listaMaestros = new ArrayList<>();
        listaRemovidos = new ArrayList<>();
        grupo = new GrupoDTO();
        mapaMaestros = null;
    }

    /**
     * Obtiene los cursos existentes llamando al delegate del caso de uso.
     *
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null.
     */
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

    /**
     * Recibe el objeto GrupoDTO para agregarle sus atributos y guardarlo
     * mediante el delegate.
     *
     * @param objGrupo grupo que sera guardado.
     * @return regresa el id del grupo en o null si no se pudo guardar.
     */
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

    /**
     * Obtiene los grupos que se encuentran registrados.
     *
     * @return regresa la lista de grupos.
     */
    public List<GrupoDTO> obtenerGrupos() {
        this.listaGrupos = gruposDELEGATE.obtenerGrupos();
        return listaGrupos;
    }

    /**
     * Recibe el indice del grupo y obtiene la entidad GrupoDTO con todos sus
     * atributos alumnos y maestros.
     *
     * @param indexGrupo indice del grupo.
     * @return regresa el objeto GrupoDTO.
     */
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

    /**
     * Recibe el objeto GrupoDTO que se va a modificar y le asigna sus atributos
     * y lo envia al delegate para que sea guardado.
     *
     * @param objGrupo recibe el objeto GrupoDTO.
     * @return regresa verdadero o falso si no se pudo guardar.
     */
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

    /**
     * Recibe el indice del grupo a eliminar obtiene el objeto GrupoDTO y lo
     * envia al delegate para su eliminacion.
     *
     * @param indexGrupo recibe el indice del grupo.
     * @return regresa verdadero o falso si no se pudo eliminar.
     */
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

    /**
     * Recibe el criterio de busqueda y obtiene todos los alumnos en los que
     * coincida con el apellido paterno, apellido materno o nombre despues
     * combina los resultados para no tener alumnos repetidos.
     *
     * @param busqueda recibe el criterio de busqueda.
     * @return regresa la lista de alumnos.
     */
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
        listaAlumnos.removeAll(listaRemovidos);
        listaAlumnos.addAll(listaRemovidos);
        return listaAlumnos;
    }

    /**
     * Recibe el criterio de busqueda y obtiene todos los maestros en los que
     * coincida con el apellido paterno, apellido materno o nombre despues
     * combina los resultados para no tener alumnos repetidos.
     *
     * @param busqueda recibe el criterio de busqueda.
     * @return regresa la lista de maestros.
     */
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

    /**
     * Recibe los indices de los alumnos y los envia para agregarlos a la lista.
     *
     * @param indexesAlumnos recibe los indices de los alumnos seleccionados.
     * @return regresa la lista de alumnos.
     */
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
        this.listaRemovidos.removeAll(alumnos);
        return alumnos;
    }

    /**
     * Recibe la lista de alumnos y elimina los repetidos y los agrega a la
     * lista de alumnos local.
     *
     * @param alumnos recibe la lista de alumnos.
     * @return regresa la lista de alumnos.
     */
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

    /**
     * Recibe el indice del maestro y los indices de los cursos seleccionados ,
     * los guarda en un mapa y los envia para guardarlos en el mapa local.
     *
     * @param indexesCursos recibe los indices de los cursos.
     * @param indexMaestro recibe el indice del maestro.
     * @return regresa el mapa MaestroDTO y CursoDTO.
     */
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

    /**
     * Recibe los objetod CursoDTO y UsuarioDTO, crea el mapa y lo agrega en el
     * mapa local.
     *
     * @param curso recibe el objeto CursoDTO.
     * @param maestro recibe el objeto UsuarioDTO.
     * @return regresa el mapa CursoDTO MaestroDTO.
     */
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

    /**
     * Recibe los indices de loa alumnos a remover y los remueve de la lista
     * local de alumnos.
     *
     * @param indexes recibe los indices de los alumnos.
     */
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

    /**
     * Agrega los alumnos removidos a la lista de alumnos removidos.
     *
     * @param eliminados recibe la lista de alumnos removidos.
     */
    public void agregarEliminados(List<UsuarioDTO> eliminados) {
        this.listaRemovidos.removeAll(eliminados);
        this.listaRemovidos.addAll(eliminados);
    }

    /**
     * Recibe el nombre del curso del maestro que se removera de la lista.
     *
     * @param nombreCurso recibe el nombre del curso.
     */
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

    /**
     * Recibe el objeto GrupoDTO y lo envia al delegate para que verifique si ya
     * existe.
     *
     * @param grupo recibe el objeto GrupoDTO.
     * @return regresa verdadero si ya existe y falso si no existe.
     */
    public boolean verificarExistencia(GrupoDTO grupo) {
        return gruposDELEGATE.verificarExistencia(grupo);
    }

    /**
     * Libera la memoria de las variables en el caso de registrar un grupo.
     */
    public void liberarMemoriaRegistrar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaRemovidos = new ArrayList<>();
        this.mapaMaestros = null;
    }

    /**
     * Libera la memoria de las variables en caso de consultar un grupo.
     */
    public void liberarMemoriaConsultar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaRemovidos = new ArrayList<>();
        this.mapaMaestros = null;
    }

    /**
     * Libera la memoria de las variables en caso de modificar un grupo.
     */
    public void liberarMemoriaModificar() {
        this.grupo = new GrupoDTO();
        this.listaAlumnos = new ArrayList<>();
        this.listaCursos = new ArrayList<>();
        this.listaGrupos = new ArrayList<>();
        this.listaMaestros = new ArrayList<>();
        this.listaRemovidos = new ArrayList<>();
        this.mapaMaestros = null;
    }
}
