package modelo;

import java.time.LocalDate;
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
}
