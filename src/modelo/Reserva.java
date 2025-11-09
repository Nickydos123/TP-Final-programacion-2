package modelo;

import enums.EestadoHabitacion;
import enums.EtipoHabitacion;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Reserva {
    static int cantReservas = 0;
    private final int id;
    private final int idHabitacion;
    private String pasajeroDni; //en caso de que se quiera vincular con un pasajero cambiarlo por un Id de pasajero
    private LocalDate desde;
    private LocalDate hasta;

    public Reserva(int idHabitacion, String pasajeroDni, LocalDate desde, LocalDate hasta) {
        cantReservas++;
        this.id = cantReservas;
        this.idHabitacion = idHabitacion;
        this.pasajeroDni = pasajeroDni;
        this.desde = desde;
        this.hasta = hasta;
    }

    public Reserva(int id, int idHabitacion, String pasajeroDni, LocalDate desde, LocalDate hasta) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.pasajeroDni = pasajeroDni;
        this.desde = desde;
        this.hasta = hasta;
    }

    public static JSONObject toJson(Reserva r){
        JSONObject obj = new JSONObject();
        obj.put("id",r.getId());
        obj.put("idHabitacion",r.getIdHabitacion());
        obj.put("pasajeroDni",r.getPasajeroDni());
        obj.put("desde",r.getDesde().toString());//Guardo la fechas como strings YYYY-MM-DD
        obj.put("hasta",r.getHasta().toString());
        return obj;
    }

    public static Reserva fromJson(JSONObject obj){
        int id = obj.getInt("id");
        int idHabitacion = obj.getInt("idHabitacion");
        String pasajeroDni = obj.getString("pasajeroDni");
        LocalDate desde = LocalDate.parse(obj.getString("desde"));//Los transformo de nuevo a Date desde String con el metodo estatico
        LocalDate hasta = LocalDate.parse(obj.getString("hasta"));

        return new Reserva(id,idHabitacion,pasajeroDni,desde,hasta);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Reserva reserva)) return false;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", idHabitacion=" + idHabitacion + //cambiar en caso de que se quiera vincular con una habitacion
                ", pasajeroDni='" + pasajeroDni + '\'' +
                ", desde=" + desde +
                ", hasta=" + hasta +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public String getPasajeroDni() {
        return pasajeroDni;
    }

    public void setPasajeroDni(String pasajeroDni) {
        this.pasajeroDni = pasajeroDni;
    }

    public LocalDate getDesde() {
        return desde;
    }

    public void setDesde(LocalDate desde) {
        this.desde = desde;
    }

    public LocalDate getHasta() {
        return hasta;
    }

    public void setHasta(LocalDate hasta) {
        this.hasta = hasta;
    }

}
