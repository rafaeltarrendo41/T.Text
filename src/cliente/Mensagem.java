/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

/**
 *
 * @author Rafael
 */
public class Mensagem {
    private String nickname, mensagem;

    public Mensagem(String nickname, String mensagem) {
        this.nickname = nickname;
        this.mensagem = mensagem;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMensagem() {
        return mensagem;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "nickname=" + nickname + ", mensagem=" + mensagem + '}';
    }
    
    
}
