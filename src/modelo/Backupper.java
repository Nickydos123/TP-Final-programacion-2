package modelo;

import interfaces.ItoJson_fromJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import persistentes.JsonUtiles;

import java.util.HashMap;
import java.util.Map;

//Esta clase generica se ocupa de respaldar lo que tiene que ver con el hotel va ha haber un Backupper diferente para Usuarios
public class Backupper<T extends ItoJson_fromJson<T>> {

    public void backupMap(Map<Integer, T> map, String archivo){
        JSONArray array = new JSONArray();
        for (T item : map.values()) {
            JSONObject obj = item.toJson();
            array.put(obj);
        }
        JsonUtiles.grabarUnJson(array, archivo); //Graba el archivo directamente
    }

    public Map<Integer, T> readMap(String archivo, T fabrica){//La fabrica la tengo que recibir desde afura porque no puedo instanciar la dentro con el tipo generico
        JSONTokener jsonTokener = JsonUtiles.leerUnJson(archivo);
        JSONArray jsonArray = new JSONArray(jsonTokener);
        Map<Integer,T> map = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            T nuevo = fabrica.fromJson(obj); // Se usa la "fabrica" para crear otro T a partir del JsonObject
            map.put(nuevo.getId(), nuevo); //Se ingresa el nuevo objeto al Map
        }
        return map;
    }
}
