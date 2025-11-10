package modelo;

import interfaces.ItoJson_fromJson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import persistentes.JsonUtiles;

import java.util.HashMap;
import java.util.Map;

//Esta clase generica se ocupa de respaldar lo que tiene que ver con el hotel va ha haber un Backupper diferente para Usuarios
//Deje en publicos los metodos backupMap y readMap por si llego a implementar logs de algun tipo, pero por ahora podrian ser privados
public class Backupper<T extends ItoJson_fromJson<T>> {

    public Backupper() {
    }

    public void backupMap(Map<Integer, T> map, String archivo){
        JSONArray array = new JSONArray();
        for (T item : map.values()) {
            JSONObject obj = item.toJson();
            array.put(obj);
        }
        JsonUtiles.grabarUnJson(array, archivo); //Graba el archivo directamente
    }

    public HashMap<Integer, T> readMap(String archivo, T fabrica){//La fabrica la tengo que recibir desde afuera porque no puedo instanciar la dentro con el tipo generico
        JSONTokener jsonTokener = JsonUtiles.leerUnJson(archivo);
        JSONArray jsonArray = new JSONArray(jsonTokener);
        HashMap<Integer,T> map = new HashMap<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            T nuevo = fabrica.fromJson(obj); // Se usa la "fabrica" para crear otro T a partir del JsonObject
            map.put(nuevo.getId(), nuevo); //Se ingresa el nuevo objeto al Map
        }
        return map;
    }

    //Metodo para hacer una backup de el hotel al completo
    public static void backupHotel(Hotel hotel){
        Backupper<Habitacion> habitacionesBackupper = new Backupper<>();
        Backupper<Estadia> estadiasBackupper = new Backupper<>();
        Backupper<Reserva> reservasBackupper = new Backupper<>();

        habitacionesBackupper.backupMap(hotel.getHabitaciones(),"habitaciones.json");
        estadiasBackupper.backupMap(hotel.getEstadias(),"estadias.json");
        reservasBackupper.backupMap(hotel.getReservas(),"reservas.json");
    }

    //Metodo para leer el hotel al completo
    public static Hotel leerHotel(){
        Backupper<Habitacion> habitacionesBackupper = new Backupper<>();
        Backupper<Estadia> estadiasBackupper = new Backupper<>();
        Backupper<Reserva> reservasBackupper = new Backupper<>();

        HashMap<Integer,Habitacion> habitaciones = habitacionesBackupper.readMap("habitaciones.json",new Habitacion());
        HashMap<Integer,Estadia> estadias = estadiasBackupper.readMap("estadias.json",new Estadia());
        HashMap<Integer,Reserva> reservas = reservasBackupper.readMap("reservas.json",new Reserva());

        return new Hotel(habitaciones,reservas,estadias);
    }
}
