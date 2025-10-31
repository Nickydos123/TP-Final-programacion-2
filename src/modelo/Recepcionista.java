package modelo;
import interfaces.IMuestraHabitaciones;
import interfaces.IRegistraReservas;
import interfaces.IHacerCheckInyCheckOut;
import enums.EestadoHabitacion;
import java.time.LocalDate;


public class Recepcionista implements IMuestraHabitaciones, IRegistraReservas, IHacerCheckInyCheckOut{
    @Override
    public String mostrarHabitaciones(Hotel hotel) {
        StringBuilder sb = new StringBuilder();
        for(Habitacion habitacion : hotel.getHabitaciones().values()){
            sb.append(habitacion.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Reserva registraReserva(Hotel hotel, int habitacionId, String dniPasajero, LocalDate desde, LocalDate hasta) {

        Habitacion habitacion = hotel.buscarHabitacionPorId(habitacionId);
        if(habitacion != null && habitacion.getEestadoHabitacion() == EestadoHabitacion.DISPONIBLE){
            Reserva reserva = new Reserva(habitacionId, dniPasajero, desde, hasta);
            hotel.getReservas().add(reserva);
            habitacion.setEestadoHabitacion(EestadoHabitacion.RESERVADA);
            return reserva;
        }
        return null;
    }
    @Override
    public Estadia hacerCheckIn(Hotel hotel, int reservaId) {
        Reserva reserva = hotel.buscarReservaPorId(reservaId);
        if(reserva != null){
            Estadia estadia = new Estadia(reserva.getIdHabitacion(), LocalDate.now(), null);
            hotel.getEstadias().add(estadia);
            hotel.buscarHabitacionPorId(reserva.getIdHabitacion()).setEestadoHabitacion(EestadoHabitacion.OCUPADA);
            hotel.getReservas().remove(reserva);
            return estadia;
        }
        return null;
    }
    @Override
    public void hacerCheckOut(Hotel hotel, int estadiaId) {
        Estadia estadia = hotel.buscarEstadiaPorId(estadiaId);
        if (estadia != null) {
            estadia.setFechaCheckOut(LocalDate.now());
            hotel.buscarHabitacionPorId(estadia.getIdHabitacion()).setEestadoHabitacion(EestadoHabitacion.DISPONIBLE);
        }
    }
}
