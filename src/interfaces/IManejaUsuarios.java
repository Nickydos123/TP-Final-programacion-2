package interfaces;
import modelo.Hotel;
import modelo.Usuario;

public interface IManejaUsuarios {
    void addUsuario(Usuario usuario, Hotel hotel);
    void removeUsuario(Usuario usuario, Hotel hotel);
}
