/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Rafael
 */
public interface NovaPresencasInterface extends Remote{
    public void setNovaPresenca(String IPAdress) throws RemoteException;
}
