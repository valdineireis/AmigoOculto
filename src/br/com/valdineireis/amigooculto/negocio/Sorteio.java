package br.com.valdineireis.amigooculto.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import br.com.valdineireis.amigooculto.excecao.TotalDePessoasEhImparException;
import br.com.valdineireis.amigooculto.modelo.Pessoa;

/**
 *
 * @author valdineireis.com.br
 */
public class Sorteio {
    
    private final Random random = new Random();
    
    /**
     * Objeto contendo o resultado do sorteio
     */
    private final Map<Integer, Pessoa> pessoasSorteadas;
    
    /**
     * Lista de pessoas adicionadas
     */
    private final List<Pessoa> pessoas;
    
    /**
     * Cópia da lista de pessoas, utilizado para efetuar o sorteio
     */
    private List<Pessoa> pessoasCopia;
    
    /**
     * Controle do código de cada pessoa cadastrada
     */
    private int codigo;
    
    public Sorteio() {
        this.pessoas = new ArrayList<>();
        this.pessoasCopia = new ArrayList<>();
        this.pessoasSorteadas = new HashMap<>();
        
        this.codigo = 0;
    }
    
    /**
     * Lista de pessoas cadastradas
     * @return 
     */
    public List<Pessoa> pessoas() {
        return this.pessoas;
    }
    
    /**
     * Total de pessoas cadastradas
     * @return 
     */
    public int totalDePessoas() {
        return this.pessoas.size();
    }

    /**
     * Adiciona a pessoa
     * @param pessoa Objeto a ser cadastrado 
     */
    public void addPessoa(Pessoa pessoa) {
        pessoa.setCodigo(this.codigo++);
        this.pessoas.add(pessoa);
    }

    /**
     * Verifica se o total de pessoas é PAR
     * @return 
     */
    public boolean totalDePessoasEhPar() {
        return this.pessoas.isEmpty() ? false : this.totalDePessoas() % 2 == 0;
    }

    /**
     * Efetua o sorteio
     * @return Retorna um Map, onde a chave é o código da pessoa, gerado pelo sistema,
     * e o segundo parâmetro é a Pessoa que efetuou o sorteio, contendo a Pessoa sorteada.
     * Exemplo: A Pessoa.getNome(), deve presentear a Pessoa.getPessoaSorteada().getNome()
     * @throws TotalDePessoasEhImparException 
     */
    public Map<Integer, Pessoa> inicia() throws TotalDePessoasEhImparException {
        if(!totalDePessoasEhPar())
            throw new TotalDePessoasEhImparException();
        
        this.pessoasCopia = this.copia(pessoas);
        
        for (int i = 0; i < this.totalDePessoas(); i++) {
            Pessoa pessoaQueSorteou = this.pessoas.get(i);
            Pessoa pessoaQueFoiSorteada = pegaDaLista(pessoaQueSorteou);
            
            pessoaQueSorteou.addPessoaSorteada(pessoaQueFoiSorteada);
            
            this.pessoasSorteadas.put(pessoaQueSorteou.getCodigo(), pessoaQueSorteou);
            this.pessoasCopia.remove(pessoaQueFoiSorteada);
        }
        
        return this.pessoasSorteadas;
    }
    
    private Pessoa pegaDaLista(Pessoa pessoa) {
        int totalDePessoas = this.pessoasCopia.size();
        int index = totalDePessoas > 0 ? random(0, totalDePessoas - 1) : 0;
        Pessoa p = this.pessoasCopia.get(index);
        
        if(pessoa.equals(p)) {
            return pegaDaLista(pessoa);
        }
        
        return p;
    }
    
    private int random(int minimum, int maximum) {
        int range = (maximum - minimum) + 1;
        int randomNum =  random.nextInt(range) + minimum;
        return randomNum;
    }
    
    private List<Pessoa> copia(List<Pessoa> pessoas) {
        List<Pessoa> novaLista = new ArrayList<>();
        for (Pessoa p : pessoas) {
            novaLista.add(p);
        }
        return novaLista;
    }
}

