package co.edu.uniquindio.modelo.enums;

public enum TipoSangre {

    APOSITIVO(0,"A+"),
    ANEGATIVO(1,"A-"),
    BPOSITIVO(2,"B+"),
    BNEGATIVO(3,"B-"),
    ABPOSITIVO(4,"AB+"),
    ABNEGATIVO(5,"AB-"),
    OPOSITIVO(6, "O-"),
    ONEGATIVO(7, "O-");

    private final int codigo;
    private final String tipoSangre;

    TipoSangre(int codigo, String tipoSangre) {
        this.codigo = codigo;
        this.tipoSangre = tipoSangre;
    }
}