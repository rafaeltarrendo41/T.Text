/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

/**
 *
 * @author Rafael
 */
public class ServerApp {
    
    public static void main(String[] args){
        PresencasServer ps = new PresencasServer();
        ps.criarPresencas();
    }
    
}
