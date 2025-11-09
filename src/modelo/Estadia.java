package modelo;

import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Estadia {
    static int cantEstadias = 0;
    private final int id;
    private final int idHabitacion;
    private String pasajeroDni;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;
    private LocalDate fechaCheckOutReal;

    public Estadia(int idHabitacion, String pasajeroDni, LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        cantEstadias++;
        this.id = cantEstadias;
        this.idHabitacion = idHabitacion;
        this.pasajeroDni = pasajeroDni;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.fechaCheckOutReal = null;
    }

    public Estadia(int id, int idHabitacion,String pasajeroDni ,LocalDate fechaCheckIn, LocalDate fechaCheckOut, LocalDate fechaCheckOutReal) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.pasajeroDni = pasajeroDni;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.fechaCheckOutReal = fechaCheckOutReal;
    }

    public static JSONObject toJson(Estadia e){
        JSONObject obj = new JSONObject();
        obj.put("id",e.getId());
        obj.put("idHabitacion",e.getIdHabitacion());
        obj.put("pasajeroDni",e.getPasajeroDni());
        obj.put("fechaCheckIn",e.getFechaCheckIn().toString());//Guardo la fechas como strings YYYY-MM-DD
        obj.put("fechaCheckOut",e.getFechaCheckIn().toString());
        obj.put("fechaCheckOutReal",e.getFechaCheckOutReal().toString());
        return obj;
    }

    public static Estadia fromJson(JSONObject obj){
        int id = obj.getInt("id");
        int idHabitacion = obj.getInt("idHabitacion");
        String pasajeroDni = obj.getString("pasajeroDni");
        LocalDate fechaCheckIn = LocalDate.parse(obj.getString("fechaCheckIn"));//Los transformo de nuevo a Date desde String con el metodo estatico
        LocalDate fechaCheckOut = LocalDate.parse(obj.getString("fechaCheckOut"));
        LocalDate fechaCheckOutReal = LocalDate.parse(obj.getString("fechaCheckOutReal"));

        return new Estadia(id,idHabitacion,pasajeroDni,fechaCheckIn,fechaCheckOut,fechaCheckOutReal);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Estadia estadia)) return false;
        return id == estadia.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estadia{" +
                "id=" + id +
                ", idHabitacion=" + idHabitacion + //Cambiar en caso de que se quiera vincular con una habitacion
                ", fechaCheckIn=" + fechaCheckIn +
                ", fechaCheckOut=" + fechaCheckOut +
                ", fechaCheckOutReal=" + fechaCheckOutReal +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(LocalDate fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(LocalDate fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public static int getCantEstadias() {
        return cantEstadias;
    }

    public static void setCantEstadias(int cantEstadias) {
        Estadia.cantEstadias = cantEstadias;
    }

    public LocalDate getFechaCheckOutReal() {
        return fechaCheckOutReal;
    }

    public String getPasajeroDni() {
        return pasajeroDni;
    }

    public void setPasajeroDni(String pasajeroDni) {
        this.pasajeroDni = pasajeroDni;
    }

    public void setFechaCheckOutReal(LocalDate fechaCheckOutReal) {
        this.fechaCheckOutReal = fechaCheckOutReal;
    }
}
