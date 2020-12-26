/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;
import java.lang.SecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Rafael
 */
public class PresencasServer {
    String SERVICE_NAME = "/PresencasRemote";
    
    private void bindRMI(Presencas presenca) throws RemoteException{
        System.getProperties().put("java.security.policy", "./server.policy");
        
        if(System.getSecurityManager() == null){
            System.setSecurityManager(new SecurityManager());
        }
        
        try{
            LocateRegistry.createRegistry(1099);
        }catch(RemoteException e){
            
        }
        
        try{
            LocateRegistry.getRegistry("127.0.0.1", 1099).rebind(SERVICE_NAME, presenca);
        }catch(RemoteException e){
            System.out.println("Registo nao encontrado!");
        }
    }
    
    public PresencasServer(){
        super();
    }
    
    public void criarPresencas(){
        Presencas presenca = null;
        try{
            presenca = new Presencas();
        }catch(RemoteException e){
            System.err.println("Erro inesperado!");
            e.printStackTrace();
        }
        
        try{
            bindRMI(presenca);
        }catch(RemoteException e){
            System.err.println("Erro ao registar o stub!"); 
            e.printStackTrace();
        }
    }
}
