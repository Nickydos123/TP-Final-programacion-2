package modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import persistentes.JsonUtiles;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class SistemBackupper {
    //Tiene funcionalidad para poder leer y guardar usuarios del sistema
    public static void backupUserMap(Map<String, Usuario> map, String archivo){
        JSONArray array = new JSONArray();
        for (Usuario usuario : map.values()) {
            JSONObject obj = Usuario.toJson(usuario);
            array.put(obj);
        }
        JsonUtiles.grabarUnJson(array, archivo);
    }

    public static HashMap<String, Usuario> readUserMap(String archivo,Sistema sistema) throws FileNotFoundException {
        JSONTokener jsonTokener = JsonUtiles.leerUnJson(archivo);
        JSONArray jsonArray = new JSONArray(jsonTokener);
        HashMap<String,Usuario> map = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Usuario usuarioAAgregar = Usuario.fromJson(obj,sistema);//fromJson tambien devuelve hijos de usuario
            map.put(usuarioAAgregar.getUserName(),usuarioAAgregar);
        }
        return map;
    }

    //Metodo estatico que se ocupa guardar los datos de usuario del sistema en un Json
    public static void backupSistema(Sistema sistema){
        Backupper.backupHotel(sistema.getHotel());//Como es para todo el sistema hace tambien backup del hotel del sistema
        SistemBackupper.backupUserMap(sistema.getUsuarios(),"Usuarios.json");
    }

    public static Sistema leerSistema() throws FileNotFoundException{
        Hotel hotelDelSistema = Backupper.leerHotel();
        Sistema sistemaLeido = new Sistema();//Lo instancio primero pq luego se usa a si mismo en el setUsuarios

        sistemaLeido.setUsuarios(SistemBackupper.readUserMap("Usuarios.json",sistemaLeido));
        sistemaLeido.setHotel(hotelDelSistema);
        sistemaLeido.setCurrentUser(null);

        return sistemaLeido;
    }
}
