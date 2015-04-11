/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remoteAccess;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Jesus Donaldo
 */
public class Enlace {

    private static Persistencia persistencia;
    
    public static Persistencia getPersistencia() throws RemoteException,
            NotBoundException {
        if (persistencia == null) {
            Registry registro = LocateRegistry.getRegistry("127.0.0.1", 9000);
            persistencia = (Persistencia) registro.lookup("MatExPersist");
        }
        return persistencia;
    }
}