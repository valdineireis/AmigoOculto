package br.com.valdineireis.amigooculto.negocio;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    public void deveAdicionarUmaPessoa() {
        adicionaPessoas(1);
        
        assertEquals(1, this.sorteador.totalDePessoas());
        assertEqualsNomePessoa();
    }
    
    @Test
    public void deveAdicionarDuasPessoa() {
        adicionaPessoas(2);
        
        assertEquals(2, this.sorteador.totalDePessoas());
        assertEqualsNomePessoa();
    }
    
    @Test
    public void deveInformarQueAQuantidadeTotalDePessoasEhImpar() {
        adicionaPessoas(1);
        assertFalse(this.sorteador.totalDePessoasEhPar());
    }
    
    @Test
    public void deveInformarQueAQuantidadeTotalDePessoasEhPar() {   
        adicionaPessoas(2);
        assertTrue(this.sorteador.totalDePessoasEhPar());
    }
    
    @Test(expected = TotalDePessoasEhImparException.class)
    public void deveSortearApenasQuandoAQuantidadeDePessoasForPar() throws TotalDePessoasEhImparException {
        adicionaPessoas(3);
        this.sorteador.inicia();
    }
    
    @Test
    public void devePermitirTelefoneRepetido() {
        Pessoa p1 = new Pessoa("Valdinei", "9999");
        Pessoa p2 = new Pessoa("Siluana", "9999");
        this.sorteador.addPessoa(p1);
        this.sorteador.addPessoa(p2);
        
        if(!this.sorteador.pessoas().contains(p1)) {
            fail("Deveria existir a pessoa chamada " + p1.getNome());
        }
        if(!this.sorteador.pessoas().contains(p2)) {
            fail("Deveria existir a pessoa chamada " + p2.getNome());
        }
    }
    
    @Test(expected = TotalDePessoasEhImparException.class)
    public void deveNegarOSorteioComAListaDePessoasVazia() throws TotalDePessoasEhImparException {
        this.sorteador.inicia();
    }
    
    @Test
    public void deveSortearComTelefonesRepetidos() throws TotalDePessoasEhImparException {
        Pessoa p1 = new Pessoa("Valdinei", "9999");
        Pessoa p2 = new Pessoa("Siluana", "8888");
        Pessoa p3 = new Pessoa("Hugo", "8888");
        Pessoa p4 = new Pessoa("Yasmin", "9999");
        this.sorteador.addPessoa(p1);
        this.sorteador.addPessoa(p2);
        this.sorteador.addPessoa(p3);
        this.sorteador.addPessoa(p4);
        
        Map<Integer, Pessoa> sorteados = this.sorteador.inicia();
        
        assertNotNull(sorteados);
        assertEquals(4, sorteados.size());
        assertContainsNomePessoa(Arrays.asList(p1, p2, p3, p4), sorteados);
    }
    
    @Test
    public void deveSortear() throws TotalDePessoasEhImparException {
        adicionaPessoas(8);
        Map<Integer, Pessoa> sorteados = this.sorteador.inicia();
        
        assertNotNull(sorteados);
        assertEquals(8, sorteados.size());
        assertContainsNomePessoa(this.sorteador.pessoas(), sorteados);
    }
    
    private void adicionaPessoas(int quantidade) {
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
    
    private void assertContainsNomePessoa(List<Pessoa> participantes, Map<Integer, Pessoa> sorteados) {
        for (int i = 0; i < this.sorteador.totalDePessoas(); i++) {
            Pessoa pessoa = participantes.get(i);
            pessoa.setCodigo(i++);
            if(!sorteados.containsValue(pessoa)) {
                fail("Pessoa com o nome '" + pessoa.getNome() + "', não encontrada!");
            }else if(sorteados.get(pessoa.getCodigo()).getPessoaSorteada() == null) {
                fail("Pessoa com o nome '" + pessoa.getNome() + "', não contém pessoa sorteada!");
            }
        }
    }
}
