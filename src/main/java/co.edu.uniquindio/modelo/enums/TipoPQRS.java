package co.edu.uniquindio.modelo.enums;

public enum TipoPQRS {

    PETICION(0,"Petición"),
    QUEJA(1,"Queja"),
    RECLAMO(2,"Reclamo"),
    SUGERENCIA(3,"Sugerencia");

    private final int codigo;
    private final String tipoPQRS;

    TipoPQRS(int codigo, String tipoPQRS) {
        this.codigo = codigo;
        this.tipoPQRS = tipoPQRS;
    }
}