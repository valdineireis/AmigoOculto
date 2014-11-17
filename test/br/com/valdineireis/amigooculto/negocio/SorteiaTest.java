package br.com.valdineireis.amigooculto.negocio;

import java.util.Map;

import br.com.valdineireis.amigooculto.excecao.TelefoneDuplicadoException;
import br.com.valdineireis.amigooculto.excecao.TotalDePessoasEhImparException;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import br.com.valdineireis.amigooculto.modelo.Pessoa;

/**
 *
 * @author valdineireis.com.br
 */
public class SorteiaTest {
    
    private Sorteio sorteador;
    
    @Before
    public void setUp() {
        this.sorteador = new Sorteio();
    }

    @Test
    public void deveCriarUmSorteioVazio() {
        assertNotNull(this.sorteador);
        assertEquals(0, this.sorteador.totalDePessoas());
    }
    
    @Test
    public void deveAdicionarUmaPessoa() throws TelefoneDuplicadoException {
        adicionaPessoas(1);
        
        assertEquals(1, this.sorteador.totalDePessoas());
        assertEqualsNomePessoa();
    }
    
    @Test
    public void deveAdicionarDuasPessoa() throws TelefoneDuplicadoException {
        adicionaPessoas(2);
        
        assertEquals(2, this.sorteador.totalDePessoas());
        assertEqualsNomePessoa();
    }
    
    @Test
    public void deveInformarQueAQuantidadeTotalDePessoasEhImpar() throws TelefoneDuplicadoException {
        adicionaPessoas(1);
        assertFalse(this.sorteador.totalDePessoasEhPar());
    }
    
    @Test
    public void deveInformarQueAQuantidadeTotalDePessoasEhPar() throws TelefoneDuplicadoException {   
        adicionaPessoas(2);
        assertTrue(this.sorteador.totalDePessoasEhPar());
    }
    
    @Test(expected = TotalDePessoasEhImparException.class)
    public void deveSortearApenasQuandoAQuantidadeDePessoasForPar() throws TotalDePessoasEhImparException, TelefoneDuplicadoException {
        adicionaPessoas(3);
        this.sorteador.inicia();
    }
    
    @Test(expected = TelefoneDuplicadoException.class)
    public void deveImpedirTelefoneDuplicado() throws TelefoneDuplicadoException {
        Pessoa p1 = new Pessoa("Valdinei", "9999");
        Pessoa p2 = new Pessoa("Siluana", "9999");
        this.sorteador.addPessoa(p1);
        this.sorteador.addPessoa(p2);
    }
    
    @Test
    public void deveSortear() throws TotalDePessoasEhImparException, TelefoneDuplicadoException {
        adicionaPessoas(8);
        Map<String, Pessoa> sorteados = this.sorteador.inicia();
        
        assertNotNull(sorteados);
        assertEquals(8, sorteados.size());
        assertContainsNomePessoa(sorteados);
    }
    
    private void adicionaPessoas(int quantidade) throws TelefoneDuplicadoException {
        for (int i = 0; i < quantidade; i++) {
            Pessoa p = new Pessoa("Pessoa " + i, i + "9999");
            this.sorteador.addPessoa(p);
        }
    }
    
    private void assertEqualsNomePessoa() {
        for (int i = 0; i < this.sorteador.totalDePessoas(); i++) {
            assertEquals("Pessoa " + i, this.sorteador.pessoas().get(i).getNome());
        }
    }
    
    private void assertContainsNomePessoa(Map<String, Pessoa> sorteados) {
        for (int i = 0; i < this.sorteador.totalDePessoas(); i++) {
            Pessoa p = new Pessoa("Pessoa " + i, i + "9999");
            p.setCodigo(i++);
            if(!sorteados.containsValue(p)) {
                fail(p.getNome() + " não encontrado!");
            }
        }
    }
}
