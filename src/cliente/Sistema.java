/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.GeralClients;
import cliente.Client;

/**
 *
 * @author Rafael
 */
public class Sistema {
    private Client clienteAtual;
    private GeralClients totalClients;
    
    public Sistema(){
        this.clienteAtual = new Client();
        this.totalClients = new GeralClients();
    }

    public Client getClienteAtual() {
        return clienteAtual;
    }

    public GeralClients getTotalClients() {
        return totalClients;
    }

    public void setClienteAtual(Client clienteAtual) {
        this.clienteAtual = clienteAtual;
    }
    
    
    
}
