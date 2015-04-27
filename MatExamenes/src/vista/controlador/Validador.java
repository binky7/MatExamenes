/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Jesus Donaldo
 */
public class Validador {
    
    /**
     * Valida que el campo no este vacio.
     * @param campo
     * @return falso si el campo no esta vacio,
     * verdadero si el campo esta vacio
     */
    public static boolean estaVacio(String campo) {
        boolean ok = false;
        
        if(campo == null || campo.trim().isEmpty()) {
            ok = true;
        }
        
        
        return ok;
    }
    
    /**
     * Valida que el campo sea nombre, esto es que no este vacio y que
     * contenga solo letras y espacios
     * @param campo
     * @return 
     */
    public static boolean esNombre(String campo) {
        boolean ok = true;
        
        if(estaVacio(campo) || !StringUtils.isAlphaSpace(campo)) {
            ok = false;
        }
        
        return ok;
    }
    
    /**
     * Sirve para poner en mayúscula la primera letra de cada palabra y las
     * demás en minúsculas, como un nombre debe ser.
     * 
     * @param nombre
     * @return el nombre escrito correctamente
     */
    public static String capitalizarNombre(String nombre) {
        String nuevoNombre;
        
        nuevoNombre = WordUtils.capitalizeFully(nombre);
        
        return nuevoNombre;
    }
    
    /**
     * Valida si el campo es un usuario, esto es que solo contenga letras o
     * números y sin espacios
     * @param campo
     * @return true si el campo es un usuario
     */
    public static boolean esUsuario(String campo) {
        boolean ok = true;
        
        if(estaVacio(campo) || !StringUtils.isAlphanumeric(campo)) {
            ok = false;
        }
        return ok;
    }
    
    /**
     * Valida que el campo sea una contraseña, esto es que no esté vacío y que
     * contenga los caracteres permitidos
     * @param campo
     * @return true si el campo es contraseña
     */
    public static boolean esPassword(String campo) {
        boolean ok = true;
        
        if(estaVacio(campo) || !StringUtils.isAsciiPrintable(campo) ||
                campo.contains("'") || campo.contains("\"") ||
                campo.contains("<") || campo.contains(">") ||
                campo.contains("\\") || campo.contains("&") ||
                campo.contains("%") || campo.contains("_")) {
            ok = false;
        }
        
        return ok;
    }
    
    /**
     * Valida si el campo es un número entero positivo
     * @param campo
     * @return false si el campo es negativo, decimal o demasiado grande,
     * o si en efecto no es un numero
     */
    public static boolean esNumero(String campo) {
        boolean ok = true;
        
        //Si es numero entero sin signo
        if(estaVacio(campo) || !StringUtils.isNumeric(campo)) {
            ok = false;
        }
        
        return ok;
    }
    
    /**
     * Para validar tanto cursos como temas, esto es que solo contengan
     * letras, numeros y espacios
     * @param campo
     * @return true si el campo es un curso/tema
     */
    public static boolean esCurso(String campo) {
        boolean ok = true;
        
        if(estaVacio(campo) || !StringUtils.isAlphanumericSpace(campo)) {
            ok = false;
        }
        
        return ok;
    }
    
    /**
     * Valida que el campo sea un grupo, osea que no este vacio, y que contenga
     * maximo 3 letras sin espacios
     * @param campo
     * @return true si el campo es un grupo
     */
    public static boolean esGrupo(String campo) {
        boolean ok = true;
        String numero = String.valueOf(campo.toCharArray()[0]);
        String nombre = campo.toCharArray()[1]+""+campo.toCharArray()[2];
        if(estaVacio(campo) || !StringUtils.isAlpha(nombre) ||
                StringUtils.isAlpha(numero) || campo.length() > 3) {
            ok = false;
        }
        
        return ok;
    }
}