package co.edu.uniquindio.modelo.enums;

public enum Alergias {

    ASMA(0,"Asma"),
    DERMATITIS_ATOPICA(1,"Dermatitis Atópica"),
    POLIPOSIS_NASAL(2 ,"Poliposis Nasal"),
    RINITIS(3, "Rinitis"),
    URTICARIA(4,"Urticaria");
    private final int codigo;
    private final String alergias;
    Alergias(int codigo, String alergias) {
        this.alergias = alergias;
        this.codigo = codigo;
    }
}