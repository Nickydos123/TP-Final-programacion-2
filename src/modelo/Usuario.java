package modelo;

public class Usuario extends Persona{
    protected String userName;
    protected String password;
    public Usuario(String nombre, String apellido, String dni, String domicilio, String userName, String password) {
        super(nombre, apellido, dni, domicilio);
        this.userName = userName;
        this.password = password;
    }
    public boolean autenticar(String userName, String password){
        return this.userName.equals(userName) && this.password.equals(password);
    }
}
