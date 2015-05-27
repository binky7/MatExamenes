/*
 * Copyright (C) 2015 Jesús Donaldo Osornio Hernández
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

import control.delegate.MantenerExamenesDELEGATE;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import modelo.dto.ClaveExamenDTO;
import modelo.dto.ClaveExamenPK;
import modelo.dto.CursoDTO;
import modelo.dto.ExamenDTO;
import modelo.dto.ReactivoDTO;
import modelo.dto.TemaDTO;
import modelo.dto.UsuarioDTO;

/**
 * Esta clase se encarga de enviar las peticiones de las vistas del caso de uso
 * de Mantener Exámenes al delegate del mismo caso de uso, en el cuál se tiene
 * acceso a capas inferiores para acceder a la base de datos. También mantiene
 * en memoria los objetos dto que se utilizan para el caso de uso.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class CVMantenerExamenes {
    /**
     * Objeto delegate del caso de uso Mantener Exámenes. Delega el trabajo a
     * capas inferiores para acceder a los datos de la aplicación
     */
    private final MantenerExamenesDELEGATE mantenerExamenesDELEGATE;
    /**
     * Lista de objetos CursoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<CursoDTO> cursos;
    /**
     * Objeto ExamenDTO que representa el examen actual, del que se obtienen
     * los datos para modificarlos o guardarlos para un nuevo registro.
     */
    private ExamenDTO examen;
    /**
     * Lista de objetos ReactivoDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<ReactivoDTO> reactivos;
    /**
     * Lista de objetos TemaDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas. Se utilizará para guardar los
     * temas que se vayan seleccionando en la selección aleatoria
     */
    private List<TemaDTO> temas;
    /**
     * Lista de cantidades que se utilizará para identificar cuantos reactivos
     * por cada tema se quiere obtener antes de seleccionar los reactivos
     * aleatoriamente
     */
    private List<Integer> cantidades;
    /**
     * Lista de objetos ExamenDTO que se utilizará como almacén temporal para
     * utilizar su información en las vistas.
     */
    private List<ExamenDTO> listaExamenes;
    
    /**
     * Crea un objeto CVMantenerExamenes e inicializa sus atributos
     */
    public CVMantenerExamenes() {
        mantenerExamenesDELEGATE = new MantenerExamenesDELEGATE();
    }
    
    /**
     * Este método es utilizado para obtener un objeto examen en base a su
     * índice, el cuál está asociado a alguna identificación en la vista, accede
     * a los datos de la aplicación por medio del delegate y regresa el objeto
     * ExamenDTO
     * 
     * @param indexExamen el índice que representa el objeto examen almacenado
     * en este objeto, el cuál no está completamente inicializado.
     * @return El objeto ExamenDTO con sus relaciones completamente inicializadas
     */
    public ExamenDTO obtenerExamen(int indexExamen) {
        ExamenDTO objExamen = null;
        
        if(listaExamenes != null && !listaExamenes.isEmpty()) {
            objExamen = listaExamenes.get(indexExamen);
            objExamen = mantenerExamenesDELEGATE.obtenerExamen(objExamen.getId());
        }
        examen = objExamen;
        
        return objExamen;
    }
    
    /**
     * Este método sirve para almacenar el curso que será parte de los datos del
     * examen actual. El indexCurso recibido sirve para buscar en la lista de
     * cursos en este objeto el objeto curso correspondiente.
     * 
     * @param indexCurso un entero que representa el índice en la vista que
     * está asociado con el objeto CursoDTO en la lista cursos de este objeto
     */
    public void setCurso(int indexCurso) {
        if(examen == null) {
            examen = new ExamenDTO();
        }
        if(cursos != null && !cursos.isEmpty()) {
            examen.setCurso(cursos.get(indexCurso));
        }
    }
    
    /**
     * Este método es utilizado para obtener un objeto reactivo en base a su
     * índice, el cuál está asociado a alguna identificación en la vista, accede
     * a los datos de la aplicación por medio del delegate y regresa el objeto
     * ReactivoDTO
     * 
     * @param indexReactivo el índice que representa el objeto reactivo almacenado
     * en este objeto, el cuál no está completamente inicializado.
     * @return El objeto ReactivoDTO con sus relaciones completamente inicializadas
     */
    public ReactivoDTO obtenerReactivo(int indexReactivo) {
        ReactivoDTO reactivo = null;
        
        if(reactivos != null && !reactivos.isEmpty()) {
            reactivo = reactivos.get(indexReactivo);
            reactivo = mantenerExamenesDELEGATE.obtenerReactivo(reactivo.getId());
        }

        return reactivo;
    }
    
    /**
     * Obtiene los temas pertenecientes al curso seleccionado, en base al índice
     * ingresado
     * 
     * @param indexCurso el índice que representa al objeto CursoDTO en la lista
     * de cursos almacenada en este objeto.
     * 
     * @return una lista de TemaDTO con los temas del curso seleccionado, en caso
     * de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso(int indexCurso) {
        List<TemaDTO> listaTemas = null;
        
        if(cursos != null && !cursos.isEmpty()) {
            CursoDTO curso = cursos.get(indexCurso);
            listaTemas = mantenerExamenesDELEGATE.obtenerTemasDeCurso(curso);
        }
        return listaTemas;
    }
    
    /**
     * Obtiene los temas pertenecientes al curso seleccionado, en este caso el
     * curso seleccionado será el que ya pertenezca al examen actual (Examen a 
     * modificar)
     * 
     * @return una lista de TemaDTO con los temas del curso seleccionado, en caso
     * de que no exista ningún tema regresa null
     */
    public List<TemaDTO> obtenerTemasDeCurso() {
        List<TemaDTO> listaTemas = null;
        
        if(examen != null) {
            //Obtiene los temas del curso del examen en vez de seleccionar un
            //curso por medio de un índice
            listaTemas = mantenerExamenesDELEGATE
                    .obtenerTemasDeCurso(examen.getCurso());
        }
        return listaTemas;
    }
    
    /**
     * Obtiene los cursos existentes llamando al delegate del caso de uso.
     * 
     * @return una lista de CursoDTO con los cursos existentes, en caso de que
     * no exista ningún curso regresa null
     */
    public List<CursoDTO> obtenerCursos() {
        List<CursoDTO> listaCursos = mantenerExamenesDELEGATE.obtenerCursos();
        cursos = listaCursos;
        
        return listaCursos;
    }
    
    /**
     * Obtiene los reactivos pertenecientes al tema seleccionado, en base al
     * nombre del tema ingresado. Este método regresará a la vista sólo aquellos
     * reactivos que no se repitan en la clave a la que se agregarán.
     * 
     * @param nombreTema el nombre del tema del objeto TemaDTO en la lista
     * de temas almacenada en este objeto.
     * @param clave el índice de la clave a la que se agregarán los reactivos,
     * para validar que no se muestre reactivos repetidos al usuario
     * 
     * @return una lista de ReactivoDTO con los reactivos del tema seleccionado,
     * en caso de que no exista ningún reactivo regresa null. En caso de que no
     * haya ningún reactivo que no esté repetido en la clave regresa una lista
     * vacía.
     */
    public List<ReactivoDTO> obtenerReactivosPorTema(String nombreTema, int clave) {
        TemaDTO tema = new TemaDTO();
        
        tema.setNombre(nombreTema);
        List<ReactivoDTO> listaReactivos = mantenerExamenesDELEGATE
                .obtenerReactivosPorTema(tema);
        
        //Validar que los reactivos a mostrar no se repitan en la clave del examen
        if(examen != null && clave != -1) {
            
            //Obtener los reactivos de la clave
            List<ClaveExamenDTO> claves = examen.getClaves();
            ClaveExamenDTO objClave = claves.get(clave);
            List<ReactivoDTO> reactivosClave = objClave.getReactivos();

            //Eliminar cada reactivo repetido de la lista de respuesta
            if (!reactivosClave.isEmpty()) {
                //Verificar que los reactivos agregados no se repitan en la clave
                for (int i = 0; i < listaReactivos.size(); i++) {
                    if(reactivosClave.contains(listaReactivos.get(i))) {
                        listaReactivos.remove(i);
                        i--;
                    }
                }
            }
        }
        
        reactivos = listaReactivos;
        
        //Lista de reactivos sin los reactivos repetidos en la clave
        return listaReactivos;
    }
    
    /**
     * Este método almacena el objeto ExamenDTO, mediante la utilización del
     * delegate del caso de uso, para llamar a las capas inferiores.
     * 
     * @param objExamen el objeto ExamenDTO que se desea almacenar
     * 
     * @return un objeto Integer que contiene el número de id generado en este
     * registro o null, en caso de que haya habido problemas al guardar
     */
    public Integer guardarExamen(ExamenDTO objExamen) {
        Integer id = null;
        
        if(examen != null) {
            //Se pasan todos los nuevos datos al objeto examen de este objeto
            examen.setInstrucciones(objExamen.getInstrucciones());
            examen.setPermiso(objExamen.getPermiso());
            examen.setNombre(objExamen.getNombre());
            examen.setAutor(objExamen.getAutor());
            examen.setFechaCreacion(new Date());
            examen.setFechaModificacion(new Date());
            id = mantenerExamenesDELEGATE.guardarExamen(examen);
        }
        
        return id;
    }
    
    /**
     * Este método actualiza el objeto ExamenDTO, mediante la utilización del
     * delegate del caso de uso, para llamar a las capas inferiores.
     * 
     * @param objExamen el objeto ExamenDTO que se desea actualizar
     * 
     * @return true si la actualización se realizó correctamente o false, en 
     * caso de que haya habido problemas al modificar
     */
    public boolean modificarExamen(ExamenDTO objExamen) {
        boolean ok = false;
        
        if(examen != null) {
            //Pasa los datos del examen de entrada al examen de este objeto y
            //después lo actualiza mediante el delegate
            examen.setInstrucciones(objExamen.getInstrucciones());
            examen.setPermiso(objExamen.getPermiso());
            examen.setNombre(objExamen.getNombre());
            examen.setFechaModificacion(new Date());
            ok = mantenerExamenesDELEGATE.modificarExamen(examen);
        }
        
        return ok;
    }
    
    /**
     * Elimina el examen asociado al índice de entrada.
     * 
     * @param indexExamen el índice del examen a eliminar
     * 
     * @return true si la operación se realizó correctamente o false si ocurrió
     * un error
     */
    public boolean eliminarExamen(int indexExamen) {
        boolean ok = false;
        
        //Eliminar el examen de la lista de este objeto después de confirmar
        //la eliminación exitosa
        if(listaExamenes != null && !listaExamenes.isEmpty()) {
            ExamenDTO objExamen = listaExamenes.get(indexExamen);
            ok = mantenerExamenesDELEGATE.eliminarExamen(objExamen);
            if(ok) {
                listaExamenes.remove(indexExamen);
            }
        }
        
        return ok;
    }
    
    /**
     * Este método sirve para agregar una selección a este objeto. Una selección
     * consiste de un par de tema y cantidad, donde el tema es el tema que se
     * decidió para escoger para seleccionar reactivos aleatorios, y la cantidad
     * es el total de reactivos por ese tema que se seleccionarán aleatoriamente.
     * Se pueden agregar varias selecciones antes de aceptar agregar los reactivos
     * 
     * @param nombreTema el nombre del tema seleccionado
     * @param cantidad la cantidad por el tema seleccionado
     */
    public void agregarSeleccion(String nombreTema, int cantidad) {
        
        if(temas == null) {
            temas = new ArrayList<TemaDTO>();
            cantidades = new ArrayList<Integer>();
        }
        TemaDTO tema = new TemaDTO();
        tema.setNombre(nombreTema);
        
        //Se agregan los datos a las listas paralelas de temas y cantidades
        temas.add(tema);
        cantidades.add(cantidad);
    }
    
    /**
     * Este método sirve para remover una selección existente en el objeto.
     * Este proceso elimina tanto el tema como la cantidad de sus respectivas
     * listas
     * 
     * @param index el índice de la selección que se desea eliminar
     */
    public void removerSeleccion(int index) {
        
        if(temas != null && !temas.isEmpty()) {
            temas.remove(index);
            cantidades.remove(index);
        }
    }
    
    /**
     * Este método sirve para seleccionar una cantidad de reactivos por un tema
     * aleatoriamente, todo elegido por el usuario. Al final los reactivos
     * seleccionados se agregan tanto a este objeto en su clave correspondiente
     * como también se agregan a la vista para mostrarse
     * 
     * @param clave el ínidice de clave en la cuál se almaacenarán los reactivos
     * seleccionados
     * 
     * @return una lista de reactivos filtrada donde se envían sólo los reactivos
     * que se agregaron realmente (por la duplicidad y eso)
     */
    public List<ReactivoDTO> obtenerReactivosAleatorios(int clave) {
        List<ReactivoDTO> listaReactivos = null;

        if (temas != null && cantidades != null) {
            listaReactivos = mantenerExamenesDELEGATE
                    .obtenerReactivosAleatorios(temas, cantidades);

            //Se agregan los reactivos internamente en este objeto
            if (listaReactivos != null && !listaReactivos.isEmpty()) {
                listaReactivos = agregarReactivos(clave, listaReactivos);
            }
        }

        return listaReactivos;
    }
    
    /**
     * Este método es utilizado para obtener un objeto reactivo en base a su
     * índice, el cuál está asociado a alguna identificación en la vista, accede
     * a los datos de la aplicación por medio del delegate y regresa el objeto
     * ReactivoDTO. Este método es especial para cuando se desea obtener un reactivo
     * de una clave en particular, no de una lista de reactivos sin dueño.
     * 
     * @param indexReactivo el índice que representa el objeto reactivo almacenado
     * en este objeto, el cuál no está completamente inicializado.
     * @param clave el índice que representa el objeto clave que forma parte
     * del examen actual, y de la que se quiere extraer el reactivo
     * 
     * @return El objeto ReactivoDTO con sus relaciones completamente inicializadas
     */
    public ReactivoDTO obtenerReactivo(int indexReactivo, int clave) {
        ReactivoDTO reactivo = null;
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                ClaveExamenDTO objClave = claves.get(clave);
                
                //Obtiene los reactivos de la clave del examen actual
                List<ReactivoDTO> reactivosClave = objClave.getReactivos();
                
                if(reactivosClave != null && !reactivosClave.isEmpty()) {
                    reactivo = reactivosClave.get(indexReactivo);
                    //Obtiene el reactivo completo de la lista de reactivos de la
                    //clave
                    reactivo = mantenerExamenesDELEGATE
                            .obtenerReactivo(reactivo.getId());
                }
            }
        }
        return reactivo;
    }
    
    /**
     * Este método sirve para obtener exámenes por el curso seleccionado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes disponibles en ese curso u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el curso ingresado
     * 
     * @param nombreCurso el nombre del curso del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * sean del curso ingresado. En caso de que la bandera sea false el sistema
     * recupera los exámenes de permiso público y todos aquellos que sean hechos
     * por el usuario actual, además de que coincidieran con el curso ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que pertenezcan
     * al curso y que además fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCurso(String nombreCurso,
            boolean todos, UsuarioDTO maestro) {
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        List<ExamenDTO> examenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorCurso(curso, todos, maestro);
        this.listaExamenes = examenes;
        
        return examenes;
    }
   
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado,
     * permitiendo seleccionar entre dos modalidades para recuperar un examen,
     * obteniendo todos los exámenes que coincidan con el nombre u obteniendo sólo
     * los exámenes que fueran públicos o aquellos que fueran hechos por el usuario
     * actual del sistema, y que por supuesto coincidieran con el nombre ingresado
     * 
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado. En caso de que la bandera sea false el
     * sistema recupera los exámenes de permiso público y todos aquellos que sean
     * hechos por el usuario actual, además de que coincidieran con el nombre
     * ingresado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se decida
     * enviar false en el parámetro todos. Sirve para filtrar la búsqueda por el
     * autor del examen. Esta consulta regresaría los exámenes que coincidieran
     * con el nombre ingresado y que además fueran públicos o hechos por el
     * usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorNombre(String nombre, boolean todos,
            UsuarioDTO maestro) {
        List<ExamenDTO> examenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorNombre(nombre, todos, maestro);
        this.listaExamenes = examenes;
        
        return examenes;
    }
    
    
    /**
     * Este método sirve para obtener exámenes por el nombre ingresado y el curso
     * seleccionado permitiendo seleccionar entre dos modalidades para recuperar
     * un examen, obteniendo todos los exámenes que coincidan con el nombre y el
     * curso u obteniendo sólo los exámenes que fueran públicos o aquellos que
     * fueran hechos por el usuario actual del sistema, y que por supuesto
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param nombreCurso el nombre del curso del que se quieren obtener los
     * exámenes que pertenecen a dicho curso
     * @param nombre el nombre o parte del nombre del examen utilizado como filtro
     * 
     * @param todos si la bandera es igual a true, no es necesario mandar el
     * objeto maestro (puede ser null). Esto regresa todos los exámenes que
     * coincidan con el nombre ingresado y el curso seleccionado. En caso de que
     * la bandera sea false el sistema recupera los exámenes de permiso público
     * y todos aquellos que sean hechos por el usuario actual, además de que
     * coincidieran con el nombre ingresado y el curso seleccionado
     * 
     * @param maestro este objeto es opcional, sólo es obligatorio cuando se
     * decida enviar false en el parámetro todos. Sirve para filtrar la búsqueda
     * por el autor del examen. Esta consulta regresaría los exámenes que
     * coincidieran con el nombre ingresado y el curso seleccionado y que además
     * fueran públicos o hechos por el usuario actual.
     * 
     * @return Una lista de ExamenDTO con los exámenes que cumplen las coincidencias
     * o null, en caso de que no haya coincidencias
     */
    public List<ExamenDTO> obtenerExamenesPorCursoYNombre(String nombreCurso,
            String nombre, boolean todos, UsuarioDTO maestro) {
        CursoDTO curso = new CursoDTO();
        
        curso.setNombre(nombreCurso);
        List<ExamenDTO> examenes = mantenerExamenesDELEGATE
                .obtenerExamenesPorCursoYNombre(curso, nombre, todos, maestro);
        this.listaExamenes = examenes;
        
        return examenes;
    }
    
    /**
     * Este método sirve para agregar los reactivos en base a su índice a la
     * clave ingresada. Este método es llamado cuando se agregan reactivo
     * manualmente.
     * 
     * @param indexesReactivo la lista de índices de reactivos que representan
     * los reactivos almacenados en este objeto
     * 
     * @param clave el índice de la clave relativa al examen actual, en donde
     * se agregarán los reactivos seleccionados
     * 
     * @return la lista real de reactivos agregados, después de eliminar los
     * repetidos, regresa una lista de reactivos que son nuevos para la clave o
     * regresa una lista vacía en caso de que todos los reactivos ya existan en
     * la clave
     */
    public List<ReactivoDTO> agregarReactivosSeleccionados(
            List<Integer> indexesReactivo, int clave) {
        List<ReactivoDTO> listaReactivos = new ArrayList<>();
        
        //Transformar los índices a los objetos reactivo que representaban
        for(int indexReactivo : indexesReactivo) {
            if(reactivos != null && !reactivos.isEmpty()) {
                ReactivoDTO reactivo = reactivos.get(indexReactivo);
                listaReactivos.add(reactivo);
            }
        }
        //Agregar los reactivos a este objeto y obtener los reactivos realmente
        //agregados
        listaReactivos = agregarReactivos(clave, listaReactivos);
        
        return listaReactivos;
    }
    
    /**
     * Este método sirve para remover una lista de reactivos de la clave ingresada
     * este método es útil para remover de memoria los reactivos eliminados en
     * la vista y tiene que estar siempre sincronizado en el mismo orden.
     * 
     * @param indexesReactivo la lista de índices de reactivo a remover
     * @param clave el índice de la clave de la que se removerán reactivos
     */
    public void removerReactivos(List<Integer> indexesReactivo, int clave) {
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                ClaveExamenDTO objClave = claves.get(clave);
                List<ReactivoDTO> reactivosClave = objClave.getReactivos();
                
                if (reactivosClave != null && !reactivosClave.isEmpty()) {
                    //Remover todos los reactivos seleccionados
                    //Ordenar los indices alrrevez para eliminarlos de la lista
                    Collections.sort(indexesReactivo, Collections.reverseOrder());
                    for (int indexReactivo : indexesReactivo) {
                        reactivosClave.remove(indexReactivo);
                    }
                }
            }
        }
    }
    
    /**
     * Este método sirve para crear una nueva clave secuencial en el examen actual,
     * ya sea para modificar el examen o para registrar uno nuevo.
     * La clave estará vacía en un principio.
     * 
     * @param clave el número real que la clave tendrá como identificador
     */
    public void agregarClave(int clave) {

        if (examen == null) {
            examen = new ExamenDTO();
        }
        
        ClaveExamenDTO objClave = new ClaveExamenDTO();
        ClaveExamenPK clavePK = new ClaveExamenPK();

        clavePK.setClave(clave);
        objClave.setId(clavePK);
        examen.addClave(objClave);
    }
    
    /**
     * Este método remueve la clave asociada al índice de la clave junto con sus
     * reactivos.
     * 
     * @param clave el índice de la clave a remover.
     */
    public void removerClave(int clave) {
        
        if(examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            
            if(claves != null && !claves.isEmpty()) {
                claves.remove(clave);
            }
        }
    }
    
    /**
     * Este método remueve todas las claves junto con sus reactivos, así que se
     * tiene que tener especial cuidado al usar este método. Un buen ejemplo para
     * usarlo es cuando se quiere seleccionar otro curso para el examen y se
     * requiere asegurarse de que un examen no pueda tener reactivos de varios
     * cursos.
     */
    public void removerTodasLasClaves() {
        
        if(examen != null) {
            examen.setClaves(new ArrayList<ClaveExamenDTO>());
        }
    }
    
    /**
     * Este método sirve para agregar los reactivos de entrada a la clave del
     * índice de entrada. sólo se agregan los reactivos no repetidos, por lo que
     * el método regresa una lista de reactivos con los objetos reales que se
     * agregaron, los objetos repetidos en la clave fueron descartados.
     * 
     * @param clave el índice de la clave donde se agregarán los reactivos
     * @param listaReactivos la lista de objetos ReactivoDTO que se desea agregar
     * a la clave.
     * @return 
     */
    private List<ReactivoDTO> agregarReactivos(int clave,
            List<ReactivoDTO> listaReactivos) {

        if (examen != null) {
            List<ClaveExamenDTO> claves = examen.getClaves();
            ClaveExamenDTO objClave = claves.get(clave);
            List<ReactivoDTO> reactivosClave = objClave.getReactivos();

            if (!reactivosClave.isEmpty()) {
                //Verificar que los reactivos agregados no se repitan en la clave
                for (int i = 0; i < listaReactivos.size(); i++) {
                    if(reactivosClave.contains(listaReactivos.get(i))) {
                        listaReactivos.remove(i);
                        i--;
                    }
                }
            }
            reactivosClave.addAll(listaReactivos);
        }
        
        return listaReactivos;
    }

    /**
     * Este método sirve para conocer el total de reactivos por un tema dado.
     * Esto surgió con la necesidad de mostrarle a los usuarios la cantidad total
     * de los reactivos por tema, para que estuvieran conscientes y pudieran
     * elegir mejor las cantidades por tema para agregar.
     * 
     * @param nombreTema el nombre del tema que se buscará para obtener el total
     * de reactivos por dicho tema
     * 
     * @return un entero con la cantidad de reactivos por el tema ingresado
     */
    public int obtenerTotalReactivos(String nombreTema) {
        List<ReactivoDTO> listaReactivos = obtenerReactivosPorTema(nombreTema, -1);
        int total = 0;
        
        if(listaReactivos != null) {
            total = listaReactivos.size();
        }
        
        return total;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Consultar.
     */
    public void liberarMemoriaConsultar() {
        listaExamenes = null;
        cursos = null;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Registrar.
     */
    public void liberarMemoriaRegistrar() {
        cursos = null;
        examen = null;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Modificar.
     */
    public void liberarMemoriaModificar() {
        examen = null;
    }
    
    /**
     * Utilizado para liberar la memoria almacenada en el objeto relacionada a
     * la vista Agregar Reactivos.
     */
    public void liberarMemoriaAgregarReactivos() {
        reactivos = null;
        temas = null;
        cantidades = null;
    }
    
}