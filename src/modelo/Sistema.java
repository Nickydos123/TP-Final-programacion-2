package modelo;
import exceptions.ExceptionNoCurrentUser;
import exceptions.ExceptionUsuarioNoAutorizado;

import java.util.HashMap;
import java.util.Map;

public class Sistema {
    private Hotel hotel;
    private Map<String,Usuario> usuarios;
    private Usuario currentUser;

    public Sistema(Hotel hotel, Administrador admin) {
        this.hotel = hotel;
        this.usuarios = new HashMap<>();
        this.usuarios.put(admin.getUserName(),admin);
        this.currentUser = null;
    }
    //Esto sirve para iniciar sin archivos
    public Sistema() {
        this.hotel = new Hotel();
        this.usuarios = new HashMap<>();
        this.currentUser = null;
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

    //Estos se encargan de averiguar de que tipo es mi currentUser
    public boolean isAdminLoggedIn() {
        return currentUser instanceof Administrador;
    }

    public boolean isRecepcionistaLoggedIn() {
        return currentUser instanceof Recepcionista;
    }

    public boolean isUserLoggedIn() {
        return currentUser.getClass() == Usuario.class;
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

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }


}
