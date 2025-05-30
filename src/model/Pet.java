package src.model;

import src.enuns.SexoPet;
import src.enuns.TipoPet;

public class Pet {
    protected String nomeCompleto;
    protected TipoPet tipo;
    protected SexoPet sexo;
    protected String endereco;
    protected String idade;
    protected String peso;
    protected String raca;
    public static final String NULL = "NÃO INFORMADO";

    public Pet(String nomeCompleto, TipoPet tipo, SexoPet sexo, String endereco, String idade, String peso, String raca) {
        this.nomeCompleto = nomeCompleto;
        this.tipo = tipo;
        this.sexo = sexo;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public Pet(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public SexoPet getSexo() {
        return sexo;
    }

    public void setSexo(SexoPet sexo) {
        this.sexo = sexo;
    }

    public void setTipo(TipoPet tipo) {
        this.tipo = tipo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
