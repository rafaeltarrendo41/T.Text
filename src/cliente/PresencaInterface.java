/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 *
 * @author Rafael
 */
public interface PresencaInterface extends Remote{
    public Vector<String> getPresencas(String IPAdress, NovaPresencaInterface c1) throws RemoteException;
}