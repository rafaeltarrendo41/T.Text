/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partilhado;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Rafael
 */
public interface PresencaInterface extends Remote{
    public Hashtable<String, IPInfo> getPresencas() throws RemoteException;
    
    public void setNovaPresenca(String nickname, String IPAdress, int porta) throws RemoteException;
}
