/*
 * Copyright (C) 2015 Alfredo Rouse Madrigal
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

import vista.interfaz.InterfaceContestarExamen;

/**
 * Encargado de calcular el tiempo transcurrido al contestar examen.
 *
 * @author Alfredo Rouse Madrigal
 * @version 1 18 Mayo 2015
 */
public class Cronometro extends Thread {

    /**
     * Almacena los segundos.
     */
    private volatile Integer minutos;
    /**
     * Almacena los minutos.
     */
    private volatile Integer segundos;
    /**
     * Bandera usada para saber si el cronometro se encuentra activo.
     */
    private volatile boolean cronometroFuncionando;
    /**
     * Bandera usada para saber si el tiempo llego a su límite.
     */
    private volatile boolean tiempoTerminado;
    /**
     * Interface para comunicarse con la vistaContestarExamen.
     */
    private InterfaceContestarExamen iContestarExamen;

    /**
     * Crea un objeto Cronómetro e inicializa sus atributos.
     *
     * @param iContestarExamen Interface para comunicarse con la
     * vistaContestarExamen
     * @param minutos Los minutos con los que el cronómetro iniciara.
     * @param segundos Los segundos con los que el cronómetro iniciara.
     */
    public Cronometro(InterfaceContestarExamen iContestarExamen, int minutos, int segundos) {
        this.iContestarExamen = iContestarExamen;
        this.minutos = minutos;
        this.segundos = segundos;
        cronometroFuncionando = true;
        tiempoTerminado = false;
    }

    /**
     * Método sobrescrito de la clase Thread, encargado de calcular el tiempo
     * transcurrido y actualizar el JLabel de la vista contestar examen.
     */
    @Override
    public void run() {
        Integer milesimas = 0;

        String min;
        String seg;

        while (cronometroFuncionando) {
            try {
                Thread.sleep(4);
                milesimas += 4;

                if (milesimas == 1000) {
                    milesimas = 0;
                    segundos--;
                    if (segundos == -1) {
                        segundos = 59;
                        minutos--;
                        if (minutos == -1) {
                            cronometroFuncionando = false;
                            tiempoTerminado = true;
                        }
                    }
                }

                if (minutos < 10) {
                    min = "0" + minutos;
                } else {
                    min = minutos.toString();
                }
                if (segundos < 10) {
                    seg = "0" + segundos;
                } else {
                    seg = segundos.toString();
                }

                //Si el cronometro termino su tiempo naturalmente.
                if (!cronometroFuncionando && tiempoTerminado) {
                    iContestarExamen.actualizarLblTiempo("Tiempo terminado");
                    iContestarExamen.tiempoTerminado();
                } else {
                    iContestarExamen.actualizarLblTiempo("Tiempo restante: " + min + ":" + seg);
                }
            } catch (InterruptedException ex) {
            }
        }

    }

    /**
     * Método usado para detener el cronómetro antes de tiempo.
     */
    public void detener() {
        cronometroFuncionando = false;
        tiempoTerminado = false;
    }

}
