package modelo;

import interfaces.IHaceBackUps;
import interfaces.IManejaUsuarios;

public class Administrador extends Usuario implements IManejaUsuarios, IHaceBackUps{
    public Administrador(String nombre, String apellido, String dni, String domicilio, String userName, String password) {
        super(nombre, apellido, dni, domicilio, userName, password);
    }

    @Override
    public void addUsuario(Usuario usuario, Hotel hotel) {
        hotel.getUsuarios().put(usuario.getUserName(), usuario);
    }
    @Override
    public void removeUsuario(Usuario usuario, Hotel hotel) {
        hotel.getUsuarios().remove(usuario.getUserName());
    }

    @Override
    public void realizarBackup(Backupper backupper) {

    }
}

