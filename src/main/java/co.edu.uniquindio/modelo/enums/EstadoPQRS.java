package co.edu.uniquindio.modelo.enums;

public enum EstadoPQRS {
    ESPERA(0,"En espera"),
    ENVIADO(1,"Enviado"),
    CANCELADO(2, "Cancelado"),
    FINALIZADO(3,"Finalizado");

    private final int codigo;
    private final String nombre;

    EstadoPQRS(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }
}