package one.digitalinnovation.gof.exception;

public class ExceptionMsg extends RuntimeException{

    private static final long serialVersion = 1L;
    private String campo;

    public ExceptionMsg(String message){
        super(message);
    }

    public ExceptionMsg(String message, String campo) {
        super(message);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}
