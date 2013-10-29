package br.unioeste.sisra.modelo.execao;

public class DaoException extends Exception {

    protected Throwable throwable;

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    @Override
    public Throwable getCause() {
        return throwable;
    }

}
