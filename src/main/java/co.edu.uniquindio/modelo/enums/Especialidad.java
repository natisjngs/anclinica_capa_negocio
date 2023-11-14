package co.edu.uniquindio.modelo.enums;

public enum Especialidad {
    GENERAL(0,"General"),
    CARDIOLOGIA(1,"Cardiología"),
    PEDIATRIA(2,"Pedriatría"),
    DERMATOLOGIA(3,"Dermatología");

    private final int codigo;
    private final String especializacion;

    Especialidad(int codigo, String especializacion) {
        this.codigo = codigo;
        this.especializacion = especializacion;
    }
}