package br.unioeste.sisra.controle.observer;

/**
 *
 * @author Charlinho
 */
public class Leitor {
    /**
     * Ultima mensagem lida.
     */
    private String mensagem;
    
    /**
     * Identificador do leitor, apenas para distinguir os prints.
     */
    private int id;
    
    public Leitor(int id){
        this.id = id;
        this.mensagem = "";
    }
    
    /**
     * Actualiza a ultima mensagem lida e imprime a mensagem
     */
    public void update(Object obj) {
        this.mensagem = obj.toString();
        lerImprimir();
    }
 
    /**
     * Imprime a ultima mensagem lida
     */
    public void lerImprimir(){
        System.out.println(String.format("Leitor %d -> Mensagem: %s",
                this.id, this.mensagem));
    }
}