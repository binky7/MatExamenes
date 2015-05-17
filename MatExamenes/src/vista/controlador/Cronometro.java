/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.controlador;

import vista.interfaz.InterfaceContestarExamen;

/**
 *
 * @author Alf
 */
public class Cronometro extends Thread {

    private volatile Integer minutos;
    private volatile Integer segundos;
    private volatile boolean cronometroFuncionando;
    private volatile boolean tiempoTerminado;
    private InterfaceContestarExamen iContestarExamen;

    public Cronometro(InterfaceContestarExamen iContestarExamen, int minutos, int segundos) {
        this.iContestarExamen = iContestarExamen;
        this.minutos = minutos;
        this.segundos = segundos;
        cronometroFuncionando = true;
        tiempoTerminado = false;
    }

    @Override
    public void run() {
        Integer milesimas = 0;

        String min;
        String seg;
        try {
            while (cronometroFuncionando) {
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

                if (!cronometroFuncionando && tiempoTerminado) {
                    iContestarExamen.actualizarLblTiempo("Tiempo terminado");
                    iContestarExamen.tiempoTerminado();
                } else {
                    iContestarExamen.actualizarLblTiempo("Tiempo restante: " + min + ":" + seg);
                }
            }
        } catch (Exception e) {
        }
    }

    public void detenerCronometro() {
        this.cronometroFuncionando = false;
        this.tiempoTerminado = false;
    }

}
