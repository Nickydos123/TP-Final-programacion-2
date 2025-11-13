package modelo;
import exceptions.ExceptionNoCurrentUser;
import exceptions.ExceptionUsuarioNoAutorizado;

import java.util.HashMap;
import java.util.Map;

public class Sistema {
    private Hotel hotel;
    private Map<String,Usuario> usuarios;
    private String currentUser;

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

    public Usuario login(String userName, String password) throws ExceptionUsuarioNoAutorizado {
        if(!usuarios.containsKey(userName)){
            throw new ExceptionUsuarioNoAutorizado("No existe un usuario con ese nombre de usuario");
        }
        if(!usuarios.get(userName).getPassword().equals(password)){
            throw new ExceptionUsuarioNoAutorizado("Constrasenia incorrecta");
        }
        this.currentUser = usuarios.get(userName).getClass().getSimpleName();//Guardo el tipo de usuario que esta logueado en una variable String
        return usuarios.get(userName);//Devuelvo el usuario logueado para que se pueda usar en el menu
    }

    public void logout() throws ExceptionNoCurrentUser {
        if(currentUser == null){
            throw new ExceptionNoCurrentUser();
        }
        currentUser = null;
    }

    //Estos se encargan de averiguar de que tipo es mi currentUser
    public boolean isAdminLoggedIn() {
        return currentUser.equals("Administrador");
    }

    public boolean isRecepcionistaLoggedIn() {
        return currentUser.equals("Recepcionista");
    }

    public boolean isUserLoggedIn() {
        return currentUser.equals("Usuario");
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

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }


}
