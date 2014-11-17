package br.com.valdineireis.amigooculto.excecao;

/**
 *
 * @author valdineireis.com.br
 */
public class TelefoneDuplicadoException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public TelefoneDuplicadoException() {
    }

    public TelefoneDuplicadoException(String message) {
        super(message);
    }
    
}
