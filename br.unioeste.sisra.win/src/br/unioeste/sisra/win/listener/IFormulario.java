package br.unioeste.sisra.win.listener;

/**
 *
 * @author Charlinho
 */
public interface IFormulario {
    public Object getDados();
    public void preencherDadosInciais(Object objeto);
    public void configurarHandler();
}
