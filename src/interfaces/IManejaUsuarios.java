package interfaces;
import exceptions.ExceptionTipoNoValido;
import exceptions.ExceptionUserNameRepetido;
import exceptions.ExceptionUsuarioNoEncontrado;
import modelo.Hotel;
import modelo.Sistema;
import modelo.Usuario;

public interface IManejaUsuarios {
    void addUsuario(Usuario usuario) throws ExceptionUserNameRepetido;
    void removeUsuario(String userName) throws ExceptionUsuarioNoEncontrado;
    void asignarTipo(String userName,String tipo) throws ExceptionUsuarioNoEncontrado, ExceptionTipoNoValido;
    String mostrarUsuarios();
}
