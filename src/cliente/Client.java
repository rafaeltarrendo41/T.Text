/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import servidor.PresencasServer;

/**
 *
 * @author Rafael
 */
public class Client {
    
    private String nickname, email, curso, IPAdress;
    private int porta;
    private ArrayList<Client> listaAmigos;
    private ArrayList<Client> pedidosAprovacao;
    String SERVICE_NAME= "/PresencasRemote";
    PresencaInterface presenca = null;

    public Client(String nickname, String email, String curso, String IPAdress, int porta) {
        this.nickname = nickname;
        this.email = email;
        this.curso = curso;
        this.IPAdress = IPAdress;
        this.porta = porta;
        listaAmigos = new ArrayList<>();
        pedidosAprovacao = new ArrayList<>();
    }
    
    public Client(){
        
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getCurso() {
        return curso;
    }

    public String getIPAdress() {
        return IPAdress;
    }

    public int getPorta() {
        return porta;
    }

    public ArrayList<Client> getListaAmigos() {
        return listaAmigos;
    }

    public ArrayList<Client> getPedidosAprovacao() {
        return pedidosAprovacao;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public void setIPAdress(String IPAdress) {
        this.IPAdress = IPAdress;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public void setListaAmigos(ArrayList<Client> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

    public void setPedidosAprovacao(ArrayList<Client> pedidosAprovacao) {
        this.pedidosAprovacao = pedidosAprovacao;
    }
    
    public void putPresencas(String ip) throws UnknownHostException, NotBoundException{
        try{
            NovaPresencaInterface c1 = new NovaPresenca();
            
            presenca = (PresencaInterface) LocateRegistry.getRegistry("127.0.0.1").lookup(SERVICE_NAME);
            //String ip = InetAddress.getLocalHost().getHostAddress();
            Vector<String> presencasLista = presenca.getPresencas(ip, c1);
            
            Iterator<String> it = presencasLista.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
        } catch(RemoteException e){
            System.err.println("Erro");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Client{" + "nickname=" + nickname + ", curso=" + curso + ", IPAdress=" + IPAdress + ", porta=" + porta + '}';
    }
    
    
    
}
