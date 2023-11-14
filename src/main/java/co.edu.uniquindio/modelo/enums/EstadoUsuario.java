package co.edu.uniquindio.modelo.enums;

public enum EstadoUsuario {
    INACTIVO(0,"INACTIVO"),
    ACTIVO(1,"ACTIVO");

    private final int codigo;
    private final String nombre;

    EstadoUsuario(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}
