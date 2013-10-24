package br.unioeste.sisra.controle.observer;

import br.unioeste.sisra.modelo.listener.IObservador;
import br.unioeste.sisra.modelo.listener.ISujeito;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charlinho
 */
public class TabuleiroMensagem implements ISujeito{
    /**
     * Lista com todos os Observers a notificar.
     */
    private List <IObservador> observadores;
 
    /**
     * Ultima mensagem registada.
     */
    private String mensagem;
 
    public TabuleiroMensagem(){
        this.observadores = new ArrayList<IObservador>();
    }
 
    public String getMessage() {
        return mensagem;
    }
 
    /**
     * O método set para alem de atribui um novo valor à
     * mensagem executa o método para notificar os Observers.
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
        notificarObservadores();
    }
 
    /**
     * Adiciona um novo Observer a ser notificado
     */
    @Override
    public void adicionarObservador(IObservador obs) {
        this.observadores.add(obs);
    }
 
    /**
     * Remove um Observer da lista de Observers a serem notificados.
     */
    @Override
    public void removerObservador(IObservador obs) {
        this.observadores.remove(obs);
    }
 
    /**
     * Notifica e atualiza todos o observers registados.
     * Para alem de notificar envia também a nova mensagem.
     */
    @Override
    public void notificarObservadores() {
        for(IObservador obs : this.observadores){
            obs.atualizar(this.mensagem);
        }
    }
}
