/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package back;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author Rafael
 */
public class Presencas extends UnicastRemoteObject implements PresencasInterface{
    private Hashtable<String, IPInfo> presentIPs = new Hashtable<String, IPInfo>();
    
    public Presencas() throws RemoteException{
        super();
    }
    
    public Vector<String> getPresencas(String IPAdress, NovaPresencasInterface c1) throws RemoteException {
        long horaActual = new Date().getTime();
        
        synchronized(this){
            if(presentIPs.containsKey(IPAdress)) {
                IPInfo novoIP = presentIPs.get(IPAdress);
                novoIP.setUltimaUtilizacao(horaActual);
                novoIP.setC1(c1);
            }else {
                IPInfo novoIP = new IPInfo(IPAdress, horaActual, c1);
                presentIPs.put(IPAdress, novoIP);
            }
             
        }
        for(Enumeration<IPInfo> e = presentIPs.elements(); e.hasMoreElements();){
            IPInfo elemento = e.nextElement();
            if(elemento.getIP() != IPAdress){
                try{
                    elemento.getC1().setNovaPresenca(IPAdress);
                }catch(RemoteException ex){
                   System.out.println("e " + elemento.getIP() + " nao disponivel!");
                   synchronized(this){
                       presentIPs.remove(elemento.getIP());
                   }
                }
            }
        }
        return getListaIPs(); 
    }
    
    private Vector<String> getListaIPs(){
        Vector<String> resultado = new Vector<String>();
        for(Enumeration<IPInfo> e = presentIPs.elements(); e.hasMoreElements();){
            IPInfo elemento = e.nextElement();
            if(!elemento.tempoPassado(180*1000)){
                resultado.add(elemento.getIP());
            }
        }
        return resultado;
    }
}

class IPInfo{
    private String ip;
    private long ultimaUtilizacao;
    private NovaPresencasInterface c1;
    
    public IPInfo(String ip, long ultimaUtilizacao, NovaPresencasInterface c1){
        this.ip = ip;
        this.ultimaUtilizacao = ultimaUtilizacao;
        this.c1 = c1;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public NovaPresencasInterface getC1(){
        return this.c1;
    }
    
    public void setUltimaUtilizacao(long time){
        this.ultimaUtilizacao = time;
    }
    
    public void setC1(NovaPresencasInterface c1){
        this.c1 = c1;
    }
    
    public boolean tempoPassado(int timeout){
        boolean resultado = false;
        long tempoDesdeUltimaUtilizacao = new Date().getTime() - this.ultimaUtilizacao;
        if(tempoDesdeUltimaUtilizacao >= timeout){
            resultado = true;
        }
        return resultado;
    }
}
