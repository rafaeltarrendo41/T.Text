/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.Client;
import java.util.ArrayList;

/**
 *
 * @author Rafael
 */
public class GeralClients {
    
    ArrayList<Client> listaGeralClients;
    
    public GeralClients(){
        listaGeralClients = new ArrayList<>();
    }

    public ArrayList<Client> getListaGeralClients() {
        return listaGeralClients;
    }

    public void adicionarClient(Client client){
        listaGeralClients.add(client);
    }
    
    public int quantidadeClients(){
        return listaGeralClients.size();
    }
    
    
}
