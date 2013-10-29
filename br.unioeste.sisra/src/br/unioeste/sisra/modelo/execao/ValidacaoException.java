package br.unioeste.sisra.modelo.execao;

public class ValidacaoException extends Exception {

    protected Throwable throwable;
    protected String chaveCampo;

    public ValidacaoException(String chaveCampo, String message) {
        super(message);
        this.chaveCampo = chaveCampo;
    }

    public ValidacaoException(String chaveCampo, String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
        this.chaveCampo = chaveCampo;
    }

    @Override
    public Throwable getCause() {
        return throwable;
    }
    
    public String getChaveCampo(){
        return chaveCampo;
    }

}
