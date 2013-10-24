package br.unioeste.sisra.modelo.listener;

/**
 *
 * @author Charlinho
 */
public interface IObservador {
     /**
     * Método que faz a notificação e recebe as actualizações.
     */
    public void atualizar(Object obj);
}
