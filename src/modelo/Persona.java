package modelo;

public abstract class Persona {
    protected String nombre;
    protected String apellido;
    protected String dni;
    protected String domicilio;

    public Persona(String nombre, String apellido, String dni, String domicilio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }
}
