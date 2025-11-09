package modelo;
import interfaces.IMuestraDatoHotel;
import interfaces.IRegistraReservas;
import interfaces.IHacerCheckInyCheckOut;
import java.time.LocalDate;


public class Recepcionista extends Usuario implements IMuestraDatoHotel, IRegistraReservas, IHacerCheckInyCheckOut{
    public Recepcionista(String nombre, String apellido, String dni, String domicilio, String userName, String password, Sistema sistema) {
        super(nombre, apellido, dni, domicilio, userName, password, sistema);
    }

    @Override
    public String mostrarHabitaciones(Hotel hotel) {
        StringBuilder sb = new StringBuilder();
        for(Habitacion habitacion : hotel.getHabitaciones().values()){
            sb.append(habitacion.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String mostrarReservas(Hotel hotel) {
        StringBuilder sb = new StringBuilder();
        for(Reserva reserva : hotel.getReservas().values()){
            sb.append(reserva.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String mostrarEstadias(Hotel hotel) {
        StringBuilder sb = new StringBuilder();
        for(Estadia estadia : hotel.getEstadias().values()){
            sb.append(estadia.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Reserva registraReserva(Hotel hotel, int habitacionId, String dniPasajero, LocalDate desde, LocalDate hasta) {
        return hotel.crearReserva(habitacionId,dniPasajero,desde,hasta);
    }
    @Override
    public Estadia hacerCheckIn(Hotel hotel, int reservaId) {
        return hotel.checkIn(reservaId);
    }

    @Override
    public Estadia hacerCheckOut(Hotel hotel, int estadiaId) {
        return hotel.checkOut(estadiaId);
    }
}
