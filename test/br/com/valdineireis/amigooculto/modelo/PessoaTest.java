package br.com.valdineireis.amigooculto.modelo;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author valdineireis.com.br
 */
public class PessoaTest {
    
    private Pessoa pessoa;

    @Test(expected = IllegalArgumentException.class)
    public void deveGerarUmaExcecaoDeArgumentoInvalido() {
        this.pessoa = new Pessoa(" ", " ");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deveGerarUmaExcecaoDeArgumentoInvalidoNaoInformandoOTelefone() {
        this.pessoa = new Pessoa("Valdinei", " ");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void deveGerarUmaExcecaoDeArgumentoInvalidoNaoInformandoONome() {
        this.pessoa = new Pessoa(" ", "9999");
    }

    @Test
    public void deveGerarUmaPessoa() {
        String valdinei = "Valdinei";
        String telefone = "9999";
        this.pessoa = new Pessoa(valdinei, telefone);
        assertEquals(valdinei, this.pessoa.getNome());
        assertEquals(telefone, this.pessoa.getTelefone());
    }
    
    @Test
    public void naoDeveHaverPessoaSorteadaInicialmente() {
        this.pessoa = new Pessoa("Valdinei", "9999");
        assertNull(this.pessoa.getPessoaSorteada());
    }
    
    @Test
    public void deveAtribuirPessoaSorteada() {
        this.pessoa = new Pessoa("Valdinei", "9999");
        
        String sorteada = "Sorteada";
        String telefone = "1111";
        this.pessoa.addPessoaSorteada(new Pessoa(sorteada, telefone));
        
        assertNotNull(this.pessoa.getPessoaSorteada());
        assertEquals(sorteada, this.pessoa.getPessoaSorteada().getNome());
        assertEquals(telefone, this.pessoa.getPessoaSorteada().getTelefone());
    }
}
