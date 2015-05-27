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
package remoteAccess;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Esta clase se encarga de hacer la conexión remota con un registro en la
 * aplicación de persistencia por medio de la interface Persistencia
 *
 * @author Jesus Donaldo Osornio Hernández
 * @version 1 18 Mayo 2015
 */
public class Enlace {

    /**
     * La interface por la cuál se accederá a todos los métodos remotos de la
     * persistencia.
     */
    private static Persistencia persistencia;
    /**
     * La ip del servidor.
     */
    public static String ip;
    /**
     * Puerto por el que escuchara el servidor.
     */
    public static int puerto;

    /**
     * Obtiene una nueva interface Persistencia si no existe, enlazando esta
     * aplicación con la aplicación de persistencia remotamente
     *
     * @return una interface Persistencia que permitirá acceder a la aplicación
     * de persistencia
     * @throws RemoteException en caso de que haya error de conexión remota
     * @throws NotBoundException en caso de que el registro no exista
     */
    public static Persistencia getPersistencia() throws RemoteException,
            NotBoundException {
        if (persistencia == null) {
            Registry registro = LocateRegistry.getRegistry(ip, puerto);
            persistencia = (Persistencia) registro.lookup("MatExPersist");
        }
        return persistencia;
    }

    /**
     * 
     * @param puerto El puerto por el que escuchara el servidor.
     */
    public static void setPuerto(int puerto) {
        Enlace.puerto = puerto;
    }

    /**
     * 
     * @param ip La ip del servidor.
     */
    public static void setIp(String ip) {
        Enlace.ip = ip;
    }
}
