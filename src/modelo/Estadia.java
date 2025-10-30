package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Estadia {
    static int cantEstadias = 0;
    private final int id;
    private final int idHabitacion;
    private LocalDate fechaCheckIn;
    private LocalDate fechaCheckOut;

    public Estadia(int idHabitacion, LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        cantEstadias++;
        this.id = cantEstadias;
        this.idHabitacion = idHabitacion;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
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
                '}';
    }
}
