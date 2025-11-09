package modelo;

import org.json.JSONObject;

public class Usuario extends Persona{
    protected String userName;//Identificador para los usuarios
    protected String password;
    protected Sistema sistema; // Sistema al que el usuario pertenece. Quiero un sistema por usuario

    public Usuario(String nombre, String apellido, String dni, String domicilio, String userName, String password, Sistema sistema) {
        super(nombre, apellido, dni, domicilio);
        this.userName = userName;
        this.password = password;
        this.sistema = sistema;
    }

    public static JSONObject toJson(Usuario u) {
        JSONObject obj = new JSONObject();
        obj.put("nombre", u.getNombre());
        obj.put("apellido", u.getApellido());
        obj.put("dni", u.getDni());
        obj.put("domicilio", u.getDomicilio());
        obj.put("userName", u.getUserName());
        obj.put("password", u.getPassword());

        //Agrego un campo extra al Json de Usuarios para no olvidarme de que tipo especifico eran
        if (u instanceof Recepcionista){
            obj.put("tipo", "Recepcionista");
        }else if (u instanceof Administrador) {
            obj.put("tipo", "Administrador");
        } else {
            obj.put("tipo", "Usuario");
        }
        return obj;
    }

    public static Usuario fromJson(JSONObject obj, Sistema sistema) {
        String tipo = obj.getString("tipo");
        String nombre = obj.getString("nombre");
        String apellido = obj.getString("apellido");
        String dni = obj.getString("dni");
        String domicilio = obj.getString("domicilio");
        String userName = obj.getString("userName");
        String password = obj.getString("password");

        //Me fijo en el parametro tipo para poder instanciar al Usuario como lo que realmente era
        switch (tipo) {
            case "Recepcionista":
                return new Recepcionista(nombre, apellido, dni, domicilio, userName, password, sistema);
            case "Administrador":
                return new Administrador(nombre, apellido, dni, domicilio, userName, password, sistema);
            default:
                return new Usuario(nombre, apellido, dni, domicilio, userName, password, sistema);
        }
    }

    protected String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio='" + domicilio + '\'' +
                '}';
    }
}
