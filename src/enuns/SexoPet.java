package src.enuns;

public enum SexoPet {
    FEMININO("Fêmea"), MASCULINO("Macho");

    private final String SEXO;

    SexoPet(String sexo){
        this.SEXO = sexo;
    }

    public String getSEXO() {
        return SEXO;
    }
}
