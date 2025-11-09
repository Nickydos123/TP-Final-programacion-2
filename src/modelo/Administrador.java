package modelo;

import exceptions.ExceptionTipoNoValido;
import exceptions.ExceptionUserNameRepetido;
import exceptions.ExceptionUsuarioNoEncontrado;
import interfaces.IHaceBackUps;
import interfaces.IManejaUsuarios;

public class Administrador extends Usuario implements IManejaUsuarios, IHaceBackUps{
    //Puede que el Administrador y la recepcionista deban tener al sistema como un parametro
    //El administrador tambien deberia poder agregar y quitar habitaciones del hotel

    public Administrador(String nombre, String apellido, String dni, String domicilio, String userName, String password) {
        super(nombre, apellido, dni, domicilio, userName, password);
    }

    @Override
    public void addUsuario(Usuario usuario, Sistema sistema) throws ExceptionUserNameRepetido {
        if (sistema.getUsuarios().containsKey(usuario.getUserName())){
            throw new ExceptionUserNameRepetido();
        }
        sistema.getUsuarios().put(usuario.getUserName(),usuario);
    }

    @Override
    public void removeUsuario(String userName, Sistema sistema) throws ExceptionUsuarioNoEncontrado {
        if (!sistema.getUsuarios().containsKey(userName)){
            throw new ExceptionUsuarioNoEncontrado();
        }
        sistema.getUsuarios().remove(userName);
    }

    @Override
    public void asignarTipo(String userName, String tipo, Sistema sistema) throws ExceptionUsuarioNoEncontrado, ExceptionTipoNoValido {
        if (!sistema.getUsuarios().containsKey(userName)){
            throw new ExceptionUsuarioNoEncontrado();
        }
        Usuario usuarioAAsignar = sistema.getUsuarios().get(userName);

        Usuario nuevoUsuario = null;
        if(tipo.equalsIgnoreCase("administrador")){
            nuevoUsuario = new Administrador(usuarioAAsignar.getNombre(),usuarioAAsignar.getApellido(),usuarioAAsignar.getDni(),usuarioAAsignar.getDomicilio(),usuarioAAsignar.getUserName(),usuarioAAsignar.getPassword());
        } else if (tipo.equalsIgnoreCase("recepcionista")) {
            nuevoUsuario = new Recepcionista(usuarioAAsignar.getNombre(),usuarioAAsignar.getApellido(),usuarioAAsignar.getDni(),usuarioAAsignar.getDomicilio(),usuarioAAsignar.getUserName(),usuarioAAsignar.getPassword());
        }else {
            throw new ExceptionTipoNoValido();
        }

        sistema.getUsuarios().remove(userName); //Elimino al usuario anterior que era generico
        sistema.getUsuarios().put(nuevoUsuario.getUserName(),nuevoUsuario);//Lo reemplazo por el nuevo usuario asignado
    }

    @Override
    public void realizarBackup(Backupper backupper) {

    }
}

