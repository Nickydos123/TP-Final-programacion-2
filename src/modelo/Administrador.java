package modelo;

import exceptions.*;
import interfaces.IAgrega_Quita_Habitaciones;
import interfaces.IHaceBackUps;
import interfaces.IManejaUsuarios;

public class Administrador extends Usuario implements IManejaUsuarios, IHaceBackUps, IAgrega_Quita_Habitaciones {
    //El administrador también deberia poder agregar y quitar habitaciones del hotel

    public Administrador(String nombre, String apellido, String dni, String domicilio, String userName, String password, Sistema sistema) {
        super(nombre, apellido, dni, domicilio, userName, password, sistema);
    }

    public Administrador() {
    }

    @Override
    public void addUsuario(Usuario usuario) throws ExceptionUserNameRepetido {
        if (sistema.getUsuarios().containsKey(usuario.getUserName())){
            throw new ExceptionUserNameRepetido();
        }
        sistema.getUsuarios().put(usuario.getUserName(),usuario);
    }

    @Override
    public void removeUsuario(String userName) throws ExceptionUsuarioNoEncontrado {
        if (!sistema.getUsuarios().containsKey(userName)){
            throw new ExceptionUsuarioNoEncontrado();
        }
        sistema.getUsuarios().remove(userName);
    }

    @Override
    public void addHabitacion(Habitacion habitacion) throws ExceptionIdRepetido {
        if(sistema.getHotel().getHabitaciones().containsKey(habitacion.getId())){
            throw new ExceptionIdRepetido("El id de la habitacion ya se encuntra en uso");
        }
        sistema.getHotel().getHabitaciones().put(habitacion.getId(),habitacion);
    }

    @Override
    public void removeHabitacion(int id) throws ExceptionIdNoencontrado {
        if(!sistema.getHotel().getHabitaciones().containsKey(id)){
            throw new ExceptionIdNoencontrado("No se encontro una habitacion con el id proporcionado");
        }
        sistema.getHotel().getHabitaciones().remove(id);
    }

    @Override
    public void asignarTipo(String userName, String tipo) throws ExceptionUsuarioNoEncontrado, ExceptionTipoNoValido {
        if (!sistema.getUsuarios().containsKey(userName)){
            throw new ExceptionUsuarioNoEncontrado();
        }
        Usuario usuarioAAsignar = sistema.getUsuarios().get(userName);

        Usuario nuevoUsuario = null;
        if(tipo.equalsIgnoreCase("administrador")){
            nuevoUsuario = new Administrador(usuarioAAsignar.getNombre(),usuarioAAsignar.getApellido(),usuarioAAsignar.getDni(),usuarioAAsignar.getDomicilio(),usuarioAAsignar.getUserName(),usuarioAAsignar.getPassword(),usuarioAAsignar.getSistema());
        } else if (tipo.equalsIgnoreCase("recepcionista")) {
            nuevoUsuario = new Recepcionista(usuarioAAsignar.getNombre(),usuarioAAsignar.getApellido(),usuarioAAsignar.getDni(),usuarioAAsignar.getDomicilio(),usuarioAAsignar.getUserName(),usuarioAAsignar.getPassword(),usuarioAAsignar.getSistema());
        }else {
            throw new ExceptionTipoNoValido();
        }

        sistema.getUsuarios().remove(userName); //Elimino al usuario anterior que no tenia tipo
        sistema.getUsuarios().put(nuevoUsuario.getUserName(),nuevoUsuario);//Lo reemplazo por el nuevo usuario asignado
    }

    @Override
    public void BackupHotel() {
        Backupper.backupHotel(sistema.getHotel());//Realiza un Backup del Hotel al que pertenece
    }

    public void BackupUsuarios(){
        SistemBackupper.backupUserMap(sistema.getUsuarios(),"Usuarios.json");
    }

    @Override
    public String mostrarUsuarios() {
        StringBuilder sb = new StringBuilder();
        for(Usuario usuario : sistema.getUsuarios().values()){
            sb.append(usuario.toString()).append("\n");
        }
        return sb.toString();
    }
}

