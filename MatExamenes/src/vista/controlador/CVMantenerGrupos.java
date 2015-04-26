/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import control.delegate.MantenerGruposDELEGATE;
import java.util.ArrayList;
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
    //private MantenerExamenesDELEGATE mantenerExamenesDELEGATE;

    //Listas de dto necesarios
    //dtos para consultar
    private List<GrupoDTO> grupos;

    //dto para modificar
    //Es necesario matener en todo momento los objetos obtenidos por hibernate
    //Si se quiere modificar o eliminar dichos objetos, por eso la razon de este
    //atributo
    private GrupoDTO grupo;

    //Lista de dto necesarios para agregar maestro
    private List<CursoDTO> cursos;
    private List<UsuarioDTO> maestros;

    //Lista de dto necesarios para agregar alumnos
    private List<UsuarioDTO> alumnos;

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
        List<UsuarioDTO> listaAlumnos = null;
        this.alumnos = listaAlumnos;
        return listaAlumnos;
    }

    public List<UsuarioDTO> obtenerMaestrosPorApellido(String apellidoPaterno) {
        List<UsuarioDTO> listaMaestros = null;
        this.maestros = listaMaestros;
        return listaMaestros;
    }

    public List<UsuarioDTO> agregarAlumnos(List<Integer> indexesAlumnos) {
        List<UsuarioDTO> listaAlumnos = null;
        for (int x = 0; x < indexesAlumnos.size(); x++) {
            int index = indexesAlumnos.remove(x);
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
        //Verificar que los alumnos agregados no se repitan en el grupo
        this.alumnos.addAll(listaAlumnos);
        this.grupo.setAlumnos(this.alumnos); //Lista de alumnos no repetidos
        return this.grupo.getAlumnos();
    }

    public Map<CursoDTO, UsuarioDTO> agregarMaestro(int indexCurso, int indexMaestro) {
//        if (this.maestros != null && !this.maestros.isEmpty()
//                && this.cursos != null && !this.cursos.isEmpty()) {
//            UsuarioDTO maestro = this.maestros.get(indexMaestro);
//            CursoDTO curso = this.cursos.get(indexCurso);
//        }
//        Map<CursoDTO, UsuarioDTO> mapaMaestro = agregarMaestro(curso, maestro);
        return null;
    }

    public Map<CursoDTO, UsuarioDTO> agregarMaestro(CursoDTO curso, UsuarioDTO maestro) {
        if (this.grupo == null) {
            GrupoDTO grupo;
        }
        Map<CursoDTO, UsuarioDTO> maestros = grupo.getMaestros();
        if (maestros == null) {

        }
        return null;
    }

    public void liberarMemoriaRegistrar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removerAlumnos(List<Integer> indexesAlumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void removerMaestro(String curso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Persiste el tema en la base de datos
     *
     * @param grupo
     * @return el id generado por la inserción
     */
//    public int guardarGrupo(GrupoDTO grupo) {
//        int id = grupoDAO.insertar(grupo);
//        return id;
//    }
    /**
     * Obtener todos los cursos
     *
     * @return lista de todos los cursos
     */
//    public List<GrupoDTO> obtenerGrupos() {
//        List<GrupoDTO> listaGrupos;
//        
//        //Asigna los cursos obtenidos en la lista.
//        listaGrupos = grupoDAO.obtenerTodos(GrupoDTO.class);
//        grupos = listaGrupos;
//        
//        return listaGrupos;
//    }
}
