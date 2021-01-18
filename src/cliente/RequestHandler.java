/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael
 */
public class RequestHandler extends Thread{
    Socket ligacao;
    Sistema sistema;
    BufferedReader in;
    PrintWriter out;
    
    public RequestHandler(Socket ligacao, Sistema sistema){
        this.ligacao = ligacao;
        this.sistema = sistema;
        
        try{
            this.in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));
            this.out = new PrintWriter(ligacao.getOutputStream());
        }catch(IOException e){
            System.out.println("Erro na execucao do servidor: " + e);
            System.exit(1);
        }
            
    }
    
    @Override
    public void run(){
        try{
            System.out.println("Aceitou ligacao de cliente no endereco " + ligacao.getInetAddress() + " na porta " + ligacao.getPort());
            
            String resposta;
            String msg = in.readLine();
            System.out.println("Request=" + msg);
            
            StringTokenizer tokens = new StringTokenizer(msg);
            String metodo = tokens.nextToken();
            
            if(metodo.equals("EnviarPedido")){
                resposta = "201";
                String nickname = tokens.nextToken();
                String email = tokens.nextToken();
                String curso = tokens.nextToken();
                String IP = tokens.nextToken();
                int porta = parseInt(tokens.nextToken());
                Client cliente = new Client(nickname, email, curso, IP, porta);
                System.out.println(cliente);
                
               
                        if(!sistema.getClienteAtual().getPedidosAprovacao().contains(cliente)){
                            sistema.getClienteAtual().inserePedidos(cliente);
                        }
                    
                
                //System.out.println(resposta);
                out.println(resposta);
            }
            
            else if(metodo.equals("ResponderPedido")){
                resposta = "200";
                String nickname = tokens.nextToken();
                String resposta1 = tokens.nextToken();
                
                if(resposta.equals("1")){
                    for(Client cliente : sistema.getTotalClients().getListaGeralClients()){
                        if(cliente.getNickname().equals(nickname)){
                            sistema.getClienteAtual().inserirAmigo(cliente);
                            sistema.getClienteAtual().removePedido(cliente);
                        }
                    }
                }
                System.out.println(resposta);
                out.println(resposta);
            }
            else if(metodo.equals("EnviarEstado")){
                resposta = "200";
                String nickname = tokens.nextToken();
                String estados = tokens.nextToken();
                String estadoFormatado = estados.replaceAll("&nbsp;", " ");
                
                Estado estado = new Estado(nickname, estadoFormatado);
                sistema.getClienteAtual().insereEstado(estado);
                
                System.out.println(resposta);
                out.println(resposta);
            }
            else{
                out.println("404");
            }
            
            out.flush();
            in.close();
            out.close();
            ligacao.close();
            System.out.println(sistema.getClienteAtual().getPedidosAprovacao());
            
        } catch (IOException ex) {
            Logger.getLogger(RequestHandler.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Erro na execução do servidor: " + ex);
            System.exit(1);
        }
    }
}
