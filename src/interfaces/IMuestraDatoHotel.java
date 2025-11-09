package interfaces;
import modelo.Hotel;

public interface IMuestraDatoHotel {
    String mostrarHabitaciones (Hotel hotel);
    String mostrarReservas(Hotel hotel);
    String mostrarEstadias(Hotel hotel);
}
