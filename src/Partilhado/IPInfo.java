/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Partilhado;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rafael
 */
public class IPInfo implements Serializable{
    private String ip;
    private int porta;
    //private long ultimaUtilizacao;
   // private NovaPresencaInterface c1;
    
    public IPInfo(String ip, int porta){
        this.ip = ip;
        this.porta = porta;
        //this.ultimaUtilizacao = ultimaUtilizacao;
       // this.c1 = c1;
    }
    
    public String getIP(){
        return this.ip;
    }
    
    public int getPorta(){
        return this.porta;
    }    
   
    
    /*public void setUltimaUtilizacao(long time){
        this.ultimaUtilizacao = time;
    }*/
    
   
    
    /*public boolean tempoPassado(int timeout){
        boolean resultado = false;
        long tempoDesdeUltimaUtilizacao = new Date().getTime() - this.ultimaUtilizacao;
        if(tempoDesdeUltimaUtilizacao >= timeout){
            resultado = true;
        }
        return resultado;
    }*/
}
