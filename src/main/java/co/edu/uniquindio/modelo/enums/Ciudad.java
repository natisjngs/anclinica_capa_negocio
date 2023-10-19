package co.edu.uniquindio.modelo.enums;

public enum Ciudad {

    ARMENIA(0,"Armenia"),
    PEREIRA(1, "Pereira"),
    MANIZALES(2, "Manizales"),
    CALI(3, "Santiago de Cali"),
    CALARCA(4,"Cálarca");

    private final int codigo;
    private final String ciudad;

    Ciudad(int codigo, String ciudad) {
        this.codigo = codigo;
        this.ciudad = ciudad;
    }
}