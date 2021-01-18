/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import Partilhado.IPInfo;
import Partilhado.PresencaInterface;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//import servidor.PresencasServer;

/**
 *
 * @author Rafael
 */
public class Client {
    
    private String nickname, email, curso, IPAdress;
    private int porta;
    private ArrayList<Client> listaAmigos;
    private ArrayList<Client> pedidosAprovacao;
    private ArrayList<Estado> estado;
    String SERVICE_NAME= "/PresencasRemote";
    PresencaInterface presenca = null;
    Hashtable<String, IPInfo> presentes;
    private ArrayList<Mensagem> mensagens;

    public Client(String nickname, String email, String curso, String IPAdress, int porta) {
        this.nickname = nickname;
        this.email = email;
        this.curso = curso;
        this.IPAdress = IPAdress;
        this.porta = porta;
        listaAmigos = new ArrayList<>();
        pedidosAprovacao = new ArrayList<>();
        mensagens = new ArrayList<>();
    }

    public ArrayList<Mensagem> getMensagens() {
        return mensagens;
    }
    public void inserirMensagem(Mensagem mensagem){
        this.mensagens.add(mensagem);
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
    
    public void inserePedidos(Client cliente){
        pedidosAprovacao.add(cliente);
    }
    
    public void inserirAmigo(Client cliente){
        listaAmigos.add(cliente);
    }
    public void removePedido(Client cliente){
        pedidosAprovacao.remove(cliente);
    }
    
    public void insereEstado(Estado estado){
        this.estado.add(estado);
    }
    
    public void setNovaPresenca(String nickname, String ip, int porta){
        
             try{
            presenca = (PresencaInterface) LocateRegistry.getRegistry("127.0.0.1").lookup(SERVICE_NAME);
            presenca.setNovaPresenca(nickname, IPAdress, porta);
            System.out.println(presenca);
             }catch(Exception e){
                 e.printStackTrace();
                 System.err.println(e);
                 
    }
    }
    
    public Hashtable<String,IPInfo> getPresenca(){
        try{
            presenca = (PresencaInterface) LocateRegistry.getRegistry("127.0.0.1").lookup(SERVICE_NAME);
            presentes = presenca.getPresencas();
           if(presenca == null){
               System.out.println("Nada Registado!");
           } else{
               System.out.println(presentes.values());
               
           }
        }catch(Exception e){
            e.printStackTrace();
        }
        return presentes;
    }
    
    
    
    

    @Override
    public String toString() {
        return "Client{" + "nickname=" + nickname + ", curso=" + curso + ", IPAdress=" + IPAdress + ", porta=" + porta + '}';
    }
    
    
    
}
