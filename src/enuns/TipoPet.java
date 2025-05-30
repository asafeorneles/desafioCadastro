package src.enuns;

public enum TipoPet {
    GATO (1, "Gato"),
    CACHORRO(2, "Cachorro");

    private final int VALOR;
    private final String TIPO;

    TipoPet(int valor, String tipo){
        this.VALOR = valor;
        this.TIPO = tipo;
    }

    public int getVALOR() {
        return VALOR;
    }

    public String getTIPO() {
        return TIPO;
    }
}
