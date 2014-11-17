package br.com.valdineireis.amigooculto.excecao;

/**
 *
 * @author valdinei.silva
 */
public class TotalDePessoasEhImparException extends Exception {
    
    private static final long serialVersionUID = 1L;

    public TotalDePessoasEhImparException() {
    }

    public TotalDePessoasEhImparException(String message) {
        super(message);
    }
    
}
