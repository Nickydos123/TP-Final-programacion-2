package modelo;

public class Administrador extends Usuario implements IManejaUsuarios, IHaceBackups{
    @Override
    public void addUsuario(Usuario usuario, Hotel hotel) {
        hotel.getUsuarios().add(usuario);
    }
    @Override
    public void realizarBackup(Backupper backupper){
        backupper.realizarBackup();
    }
}
