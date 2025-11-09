package modelo;

import enums.EestadoHabitacion;
import enums.EtipoHabitacion;
import interfaces.ItoJson_fromJson;
import jdk.dynalink.beans.StaticClass;
import org.json.JSONObject;

import java.util.Objects;



public class Habitacion implements ItoJson_fromJson<Habitacion> {

    static int contID = 0;
    private int id;
    private EtipoHabitacion tipoHabitacion;
    private double precio;
    private EestadoHabitacion eestadoHabitacion;

    public Habitacion(EtipoHabitacion tipoHabitacion, double precio, EestadoHabitacion eestadoHabitacion) {
        this.id = ++contID;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
        this.eestadoHabitacion = eestadoHabitacion;
    }

    public Habitacion() {
    }

    public JSONObject toJson(){
        JSONObject obj = new JSONObject();
        obj.put("tipoHabitacion",this.getTipoHabitacion());
        obj.put("precio",this.getPrecio());
        obj.put("eestadoHabitacion",this.getEestadoHabitacion());
        return obj;
    }

    public Habitacion fromJson(JSONObject obj){
        EtipoHabitacion etipoHabitacion = obj.getEnum(EtipoHabitacion.class,"tipoHabitacion");
        double precio = obj.getDouble("precio");
        EestadoHabitacion eestadoHabitacion1 = obj.getEnum(EestadoHabitacion.class,"eestadoHabitacion");

        return new Habitacion(etipoHabitacion,precio,eestadoHabitacion1);
    }

    public int getId() {
        return id;
    }

    public EtipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(EtipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public EestadoHabitacion getEestadoHabitacion() {
        return eestadoHabitacion;
    }

    public void setEestadoHabitacion(EestadoHabitacion eestadoHabitacion) {
        this.eestadoHabitacion = eestadoHabitacion;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Habitacion that)) return false;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", tipoHabitacion=" + tipoHabitacion +
                ", precio=" + precio +
                ", eestadoHabitacion=" + eestadoHabitacion +
                '}';
    }
}
