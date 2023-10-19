package co.edu.uniquindio.modelo.enums;

public enum EstadoCita {

    PROGRAMADA(0,"Programada"),
    COMPLETADA(1,"Completada"),
    CANCELADA(2,"Cancelada"),
    PENDIENTE(3,"Pendiente");

    private final int codigo;
    private final String estadoCita;

    EstadoCita(int codigo,String estadoCita) {
        this.codigo = codigo;
        this.estadoCita = estadoCita;
    }
}