/*
 * Copyright (C) 2015 E. Iván Mariscal Martínez
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
package modelo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author E. Iván Mariscal Martínez
 * @version 1 21 de mayo 2015
 */
@Entity
@Table(name = "curso")
public class CursoDTO implements Serializable, Comparable<CursoDTO> {

    /**
     * El id del curso.
     */
    private int id;

    /**
     * El nombre del curso.
     */
    private String nombre;

    /**
     * La lista de temas del curso.
     */
    private List<TemaDTO> temas = new ArrayList<TemaDTO>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    /**
     * @return el id del curso.
     */
    public int getId() {
        return id;
    }

    /**
     * @param id el id del curso a guardar.
     */
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "nombre", nullable = false, unique = true, length = 50)
    /**
     * @return el nombre del curso.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre el nombre del curso a guardar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @OneToMany
    @JoinTable(
            name = "curso_tema",
            joinColumns = @JoinColumn(name = "idCurso"),
            inverseJoinColumns = @JoinColumn(name = "idTema")
    )
    /**
     * @return la lista de temas del curso.
     */
    public List<TemaDTO> getTemas() {
        return temas;
    }

    /**
     * @param temas la lista de temas del curso a guardar.
     */
    public void setTemas(List<TemaDTO> temas) {
        this.temas = temas;
    }

    /**
     * Sobreescribir el método equals para poder ser utilizado al ordenar con
     * Collections.
     *
     * @param o el objeto a comparar con este
     * @return true si el objeto es igual a éste, false si no.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof CursoDTO)) {
            return false;
        }
        CursoDTO c = (CursoDTO) o;

        return c.nombre.equals(nombre);
    }

    /**
     * Sobreescribir el método hashCode para poder ser utilizado al ordenar con
     * Collections
     *
     * @return el hashCode de este objeto
     */
    @Override
    public int hashCode() {
        return 37 * nombre.hashCode();
    }

    /**
     * Método necesario para poder utilizar la búsqueda binaria de Collections,
     * se debe regresar un valor entero para poder ser ordenado, Debe involucrar
     * los mismos atributos que aparezcan en equals y hashCode, y estos
     * atributos deben identificar a la entidad como única, ademas de que sea un
     * atributo fácilmente obtenible de la vista, por eso se escogió el nombre
     * del tema en vez del id, ya que en la vista se tendrán los nombres, no los
     * ids, de todas formas el nombre también identifica a la entidad
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(CursoDTO o) {
        return nombre.compareTo(o.nombre);
    }

}
