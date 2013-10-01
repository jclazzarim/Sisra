package br.unioeste.sisra.modelo.listener;

/**
 *
 * @author Charlinho
 */
public interface ISujeito {
   /**
     * Adiciona um novo IObservador à lista a notificar.
     */
    public void adicionarObservador(IObservador obs);
 
    /**
     * Remove um IObservador da lista.
     */
    public void removerObservador(IObservador obs);
 
    /**
     * Notifica todos os IObservador registados.
     */
    public void notificarObservadores();
}
