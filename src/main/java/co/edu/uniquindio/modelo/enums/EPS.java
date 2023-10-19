package co.edu.uniquindio.modelo.enums;

public enum EPS {
    SOS(0,"SOS"),
    SALUDTOTAL(1, "SaludTotal"),
    SURA(2, "SURA"),
    MEDIMAS(3, "MEDIMAS"),
    COOMEVA(4, "COOMEVA");

    private final int codido;
    private final String nombreEps;
    EPS(int codigo, String nombreEps) {
        this.codido = codigo;
        this.nombreEps = nombreEps;
    }
}