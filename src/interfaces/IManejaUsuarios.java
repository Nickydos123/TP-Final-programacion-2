package interfaces;
import exceptions.ExceptionTipoNoValido;
import exceptions.ExceptionUserNameRepetido;
import exceptions.ExceptionUsuarioNoEncontrado;
import modelo.Hotel;
import modelo.Sistema;
import modelo.Usuario;

public interface IManejaUsuarios {
    void addUsuario(Usuario usuario, Sistema sistema) throws ExceptionUserNameRepetido;
    void removeUsuario(String userName, Sistema sistema) throws ExceptionUsuarioNoEncontrado;
    void asignarTipo(String userName,String tipo, Sistema sistema) throws ExceptionUsuarioNoEncontrado, ExceptionTipoNoValido;
}
