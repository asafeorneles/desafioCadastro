package src.enuns;

public enum BuscaPet {
    NOME_E_SOBRENOME (1, "Nome e Sobrenome"),
    SEXO(2, "sexo"),
    IDADE(3, "idade"),
    PESO(4, "Peso"),
    RACA(5, "Raça"),
    ENDERECO(6, "Endereço");

    private final int VALOR;
    private final String INFO;

    BuscaPet(int valor, String info){
        this.VALOR = valor;
        this.INFO = info;
    }

    public int getVALOR() {
        return VALOR;
    }

    public String getINFO() {
        return INFO;
    }
}
