package modelo;

import interfaces.ItoJson_fromJson;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Objects;

public class Estadia implements ItoJson_fromJson<Estadia> {
    static int cantEstadias = 0;
    private int id;
    private int idHabitacion;
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
        this.fechaCheckOutReal = LocalDate.of(1,1,1);//Valor default porque dejar esto en null es problematico
    }

    public Estadia(int id, int idHabitacion,String pasajeroDni ,LocalDate fechaCheckIn, LocalDate fechaCheckOut, LocalDate fechaCheckOutReal) {
        this.id = id;
        this.idHabitacion = idHabitacion;
        this.pasajeroDni = pasajeroDni;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.fechaCheckOutReal = fechaCheckOutReal;
    }

    public Estadia() {
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("id",this.getId());
        obj.put("idHabitacion",this.getIdHabitacion());
        obj.put("pasajeroDni",this.getPasajeroDni());
        obj.put("fechaCheckIn",this.getFechaCheckIn().toString());//Guardo la fechas como strings YYYY-MM-DD
        obj.put("fechaCheckOut",this.getFechaCheckIn().toString());

        if(this.fechaCheckOutReal.isEqual(LocalDate.of(1,1,1))){//Solucion para los problemas que me daba una fecha null
            obj.put("fechaCheckOutReal","CheckOut Pendiente");
        }else {
            obj.put("fechaCheckOutReal",this.getFechaCheckOutReal().toString());
        }

        return obj;
    }

    public Estadia fromJson(JSONObject obj){
        int id = obj.getInt("id");
        int idHabitacion = obj.getInt("idHabitacion");
        String pasajeroDni = obj.getString("pasajeroDni");
        LocalDate fechaCheckIn = LocalDate.parse(obj.getString("fechaCheckIn"));//Los transformo de nuevo a Date desde String con el metodo estatico
        LocalDate fechaCheckOut = LocalDate.parse(obj.getString("fechaCheckOut"));

        LocalDate fechaCheckOutReal;
        if(obj.getString("fechaCheckOutReal").equals("CheckOut Pendiente")){
            fechaCheckOutReal = LocalDate.of(1,1,1);
        }else {
            fechaCheckOutReal = LocalDate.parse(obj.getString("fechaCheckOutReal"));
        }
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
        if(this.fechaCheckOutReal.isEqual(LocalDate.of(1,1,1))){
            return "Estadia{" +
                    "id=" + id +
                    ", idHabitacion=" + idHabitacion + //Cambiar en caso de que se quiera vincular con una habitacion
                    ", fechaCheckIn=" + fechaCheckIn +
                    ", fechaCheckOut=" + fechaCheckOut +
                    ", fechaCheckOutReal=" + " CheckOut Pendiente" +
                    '}';
        }
        //Adapto el to string para que tenga en cuenta la fechaChekout real sin asignar
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
