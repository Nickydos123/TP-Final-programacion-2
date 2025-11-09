package modelo;
import exceptions.ExceptionNoCurrentUser;
import exceptions.ExceptionUsuarioNoAutorizado;

import java.util.HashMap;
import java.util.Map;

public class Sistema {
    private Hotel hotel;
    private Administrador mainAdmin;//Este administrador esta para poder manejar el Sistema desde un comiezo(Puede ser inecesario)
    private Map<String,Usuario> usuarios;
    private Usuario currentUser;

    public Sistema(Hotel hotel, Administrador mainAdmin) {
        this.hotel = hotel;
        this.mainAdmin = mainAdmin;
        this.usuarios = new HashMap<String,Usuario>();
        this.currentUser = null;
        usuarios.put(mainAdmin.getUserName(), mainAdmin);
    }

    public void login(String userName, String password) throws ExceptionUsuarioNoAutorizado {
        if(!usuarios.containsKey(userName)){
            throw new ExceptionUsuarioNoAutorizado("No existe un usuario con ese nombre de usuario");
        }
        if(!usuarios.get(userName).getPassword().equals(password)){
            throw new ExceptionUsuarioNoAutorizado("Constrasenia incorrecta");
        }
        this.currentUser = usuarios.get(userName);
    }

    public void logout() throws ExceptionNoCurrentUser {
        if(currentUser == null){
            throw new ExceptionNoCurrentUser();
        }
        currentUser = null;
    }

    //Estas dos se encaragn de averiguar de que tipo es mi currentUser
    public boolean isAdminLoggedIn() {
        return currentUser instanceof Administrador;
    }

    public boolean isRecepcionistaLoggedIn() {
        return currentUser instanceof Recepcionista;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Administrador getMainAdmin() {
        return mainAdmin;
    }

    public void setMainAdmin(Administrador mainAdmin) {
        this.mainAdmin = mainAdmin;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }
}
