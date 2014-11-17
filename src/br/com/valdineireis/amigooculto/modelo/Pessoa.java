package br.com.valdineireis.amigooculto.modelo;

import java.util.Objects;

/**
 *
 * @author valdineireis.com.br
 */
public class Pessoa {

    private int codigo;
    private String nome;
    private String telefone;
    
    public Pessoa(String nome, String telefone) {
        if(nome.trim().isEmpty() || telefone.trim().isEmpty())
            throw new IllegalArgumentException();
        
        this.nome = nome.trim();
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }
    
    public String getTelefone() {
        return this.telefone;
    }
    
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo() {
        return this.codigo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.codigo;
        hash = 37 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pessoa other = (Pessoa) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return Objects.equals(this.nome, other.nome);
    }
    
}
