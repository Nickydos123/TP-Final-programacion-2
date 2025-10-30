package modelo;

import interfaces.IManejaUsuarios;

public class Administrador extends Usuario implements IManejaUsuarios, IHaceBackups{
    public Administrador(String nombre, String apellido, String dni, String domicilio, String userName, String password) {
        super(nombre, apellido, dni, domicilio, userName, password);
    }

    @Override
    public void addUsuario(Usuario usuario, Hotel hotel) {
        hotel.getUsuarios().add(usuario);
    }
    @Override
    public void removeUsuario(Usuario usuario, Hotel hotel) {
        hotel.getUsuarios().remove(usuario);
    }
    @Override
    public void realizarBackup(Backupper backupper){
        backupper.realizarBackup();
    }

}
