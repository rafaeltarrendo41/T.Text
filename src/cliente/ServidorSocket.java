/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class ServidorSocket extends Thread{
    
    ServerSocket servidor = null;
    Sistema sistema;
    int porta;
    private volatile boolean flag = true;
    
    public ServidorSocket(Sistema sistema){
        try{
            porta = sistema.getClienteAtual().getPorta();
            this.sistema = sistema;
            servidor = new ServerSocket(porta);
            System.out.println("Servidor à espera na porta " + porta);
        }catch(Exception e){
            e.printStackTrace();
            System.err.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
    }
    
    @Override
    public void run(){
        while(flag){
            try{
                Socket ligacao = servidor.accept();
                RequestHandler handler = new RequestHandler(ligacao, sistema);
                handler.start();
            } catch (IOException ex) {
                Logger.getLogger(ServidorSocket.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro na execucao do servidor: " + ex);
                System.exit(1);
            }
        }
        
    }
    
    public void stopRun(){
        flag =false;
    }
    
}
