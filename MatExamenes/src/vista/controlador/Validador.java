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

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Clase Validador que es utilizada para validar las cadenas de texto y
 * comprobar que cumplen con la longitud máxima, caracteres aceptados, etc.
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class Validador {

    /**
     * Longitud máxima del curso
     */
    public static final int LONGITUD_CURSO = 50;
    /**
     * Longitud máxima del tema
     */
    public static final int LONGITUD_TEMA = 150;
    /**
     * Longitud máxima del nombre del reactivo
     */
    public static final int LONGITUD_NOMBRE_REACTIVO = 100;
    /**
     * Longitud máxima de la redacción del reactivo
     */
    public static final int LONGITUD_REDACCION_REACTIVO = 1000;
    /**
     * Longitud máxima de las opciones del reactivo
     */
    public static final int LONGITUD_OPCION_REACTIVO = 250;
    /**
     * Longitud máxima del nombre del examen
     */
    public static final int LONGITUD_NOMBRE_EXAMEN = 100;
    /**
     * Longitud máxima de las instrucciones del examen
     */
    public static final int LONGITUD_INSTRUCCIONES_EXAMEN = 200;
    /**
     * Longitud máxima de los datos del usuario
     */
    public static final int LONGITUD_DATOS_USUARIO = 50;

    /**
     * Expresion regular para verificar que sea una ipv4.
     */
    private static final String EXPRESION_IP = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$";

    /**
     * Valida que el campo no esté vacío, incluyendo espacios.
     *
     * @param campo la cadena a validar
     * @return false si el campo no está vacío, true si el campo está vacío
     */
    public static boolean estaVacio(String campo) {
        boolean ok = false;

        if (campo == null || campo.trim().isEmpty()) {
            ok = true;
        }

        return ok;
    }

    /**
     * Valida que el campo sea nombre, esto es que no esté vacío y que contenga
     * sólo letras y espacios
     *
     * @param campo la cadena de texto a validar
     * @return true si el campo ingresado es validado como un nombre, false si
     * ocurre lo contrario
     */
    public static boolean esNombre(String campo) {
        boolean ok = true;

        if (estaVacio(campo) || !StringUtils.isAlphaSpace(campo)) {
            ok = false;
        }

        return ok;
    }

    /**
     * Sirve para poner en mayúscula la primera letra de cada palabra y las
     * demás en minúsculas, como un nombre debe ser.
     *
     * @param nombre el nombre que se quiere capitalizar
     * @return el nombre escrito correctamente
     */
    public static String capitalizarNombre(String nombre) {
        String nuevoNombre;

        nuevoNombre = WordUtils.capitalizeFully(nombre);

        return nuevoNombre;
    }

    /**
     * Valida si el campo es un usuario, esto es que sólo contenga letras o
     * números y sin espacios
     *
     * @param campo la cadena de texto a validar
     * @return true si el campo es un usuario
     */
    public static boolean esUsuario(String campo) {
        boolean ok = true;

        if (estaVacio(campo) || !StringUtils.isAlphanumeric(campo)) {
            ok = false;
        }
        return ok;
    }

    /**
     * Valida que el campo sea una contraseña, esto es que no esté vacío y que
     * contenga los caracteres permitidos
     *
     * @param campo la cadena de texto a validar
     * @return true si el campo es contraseña, false si no es así.
     */
    public static boolean esPassword(String campo) {
        boolean ok = true;

        if (estaVacio(campo) || !StringUtils.isAsciiPrintable(campo)
                || campo.contains("'") || campo.contains("\"")
                || campo.contains("<") || campo.contains(">")
                || campo.contains("\\") || campo.contains("&")
                || campo.contains("%") || campo.contains("_")
                || campo.length() < 4) {
            ok = false;
        }

        return ok;
    }

    /**
     * Valida si el campo es un número entero positivo
     *
     * @param campo la cadena de texto a validar
     * @return false si el campo es negativo, decimal o demasiado grande, o si
     * en efecto no es un numero, true si ocurre lo contrario
     */
    public static boolean esNumero(String campo) {
        boolean ok = true;

        //Si es numero entero sin signo
        if (estaVacio(campo) || !StringUtils.isNumeric(campo)) {
            ok = false;
        }

        return ok;
    }

    /**
     * Para validar tanto cursos como temas. Esto es que sólo contengan letras,
     * números y espacios
     *
     * @param campo la cadena de texto a validar
     * @return true si el campo es un curso/tema, false si no
     */
    public static boolean esCurso(String campo) {
        boolean ok = true;

        if (estaVacio(campo) || !StringUtils.isAlphanumericSpace(campo)) {
            ok = false;
        }

        return ok;
    }

    /**
     * Valida que el campo sea un grupo, osea que no esté vacío, y que contenga
     * solo una letra.
     *
     * @param campo la cadena de texto a validar
     * @return true si el campo es un grupo, false si no
     */
    public static boolean esGrupo(String campo) {
        boolean ok = true;
        if (estaVacio(campo) || StringUtils.isNumeric(campo)
                || campo.length() != 1) {
            ok = false;
        }
        return ok;
    }

    /**
     * Valida que la longitud ingresada se cumpla en el campo ingresado.
     *
     * @param longitud el total máximo de caracteres que se espera que un campo
     * de texto tenga
     * @param campoTexto la cadena de texto a validar
     * @return true si la longitud de campoTexto es menor o igual a la longitud
     * especificada. De lo contrario retorna false
     */
    public static boolean validarLongitud(int longitud, String campoTexto) {
        boolean ok = true;

        if (campoTexto.length() >= longitud) {
            ok = false;
        }

        return ok;
    }

    /**
     * Valid la estructura de la ip ingresada para que concuerde con una ipv4.
     *
     * @param ip La ip a validar.
     * @return Verdadero si la ip ingresada fue exitosamente validada como una
     * ip.<br>
     * Falso de otra forma.
     */
    public static boolean esIp(String ip) {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EXPRESION_IP);
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /**
     * Reemplaza las letras que contengan acentos a su letra equivalente sin
     * acento en una cadena de caracteres dada.
     *
     * @param cadena cadena de caracteres de la cual se eliminarán los acentos.
     * @return Cadena de caracteres sin letras con acentos.
     */
    public static String quitarAcentos(String cadena) {
        String noAcentos = "";

        char caracteres[] = cadena.toLowerCase().toCharArray();

        for (int i = 0; i < caracteres.length; i++) {
            switch (caracteres[i]) {
                case 'á':
                    caracteres[i] = 'a';
                    break;
                case 'é':
                    caracteres[i] = 'e';
                    break;
                case 'í':
                    caracteres[i] = 'i';
                    break;
                case 'ó':
                    caracteres[i] = 'o';
                    break;
                case 'ú':
                    caracteres[i] = 'u';
                    break;
                case 'ü':
                    caracteres[i] = 'u';
                    break;
                default:

            }
        }

        noAcentos = String.valueOf(caracteres);

        return noAcentos;
    }

}
