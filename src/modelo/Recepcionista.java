package modelo;
import enums.EestadoHabitacion;
import exceptions.ExceptionHabitacionNoDisponible;
import exceptions.ExceptionIdNoencontrado;
import interfaces.ICambiaEstadoHabitacion;
import interfaces.IMuestraDatoHotel;
import interfaces.IRegistraReservas;
import interfaces.IHacerCheckInyCheckOut;
import java.time.LocalDate;


public class Recepcionista extends Usuario implements IMuestraDatoHotel, IRegistraReservas, IHacerCheckInyCheckOut, ICambiaEstadoHabitacion {
    public Recepcionista(String nombre, String apellido, String dni, String domicilio, String userName, String password, Sistema sistema) {
        super(nombre, apellido, dni, domicilio, userName, password, sistema);
    }

    public Recepcionista() {
    }

    @Override
    public String mostrarHabitaciones() {
        StringBuilder sb = new StringBuilder();
        for(Habitacion habitacion : sistema.getHotel().getHabitaciones().values()){
            sb.append(habitacion.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String mostrarReservas() {
        StringBuilder sb = new StringBuilder();
        for(Reserva reserva : sistema.getHotel().getReservas().values()){
            sb.append(reserva.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String mostrarEstadias() {
        StringBuilder sb = new StringBuilder();
        for(Estadia estadia : sistema.getHotel().getEstadias().values()){
            sb.append(estadia.toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public Reserva registraReserva(int habitacionId, String dniPasajero, LocalDate desde, LocalDate hasta) {
        Hotel hotel = sistema.getHotel();
        return hotel.crearReserva(habitacionId,dniPasajero,desde,hasta);
    }
    @Override
    public Estadia hacerCheckIn(int reservaId) {
        Hotel hotel = sistema.getHotel();
        return hotel.checkIn(reservaId);
    }

    @Override
    public Estadia hacerCheckOut(int estadiaId) {
        Hotel hotel = sistema.getHotel();
        return hotel.checkOut(estadiaId);
    }

    @Override
    public void cambiarEstadoAMantenimiento(int habitacionId)  throws ExceptionHabitacionNoDisponible, ExceptionIdNoencontrado {
        if(!sistema.getHotel().getHabitaciones().containsKey(habitacionId)){
            throw new ExceptionIdNoencontrado("No se encontro una habitacion con ese id");
        }

        if (sistema.getHotel().getHabitaciones().get(habitacionId).getEestadoHabitacion() == (EestadoHabitacion.OCUPADA)){
            throw new ExceptionHabitacionNoDisponible("Desocupe la habitacion antes de ponerla en mantenimiento");
        }
        sistema.getHotel().getHabitaciones().get(habitacionId).setEestadoHabitacion(EestadoHabitacion.MANTENIMIENTO);
    }

    @Override
    public void terMinarMantenimiento(int habitacionId) throws ExceptionIdNoencontrado{
        if(!sistema.getHotel().getHabitaciones().containsKey(habitacionId)){
            throw new ExceptionIdNoencontrado("No se encontro una habitacion con ese id");
        }

        sistema.getHotel().getHabitaciones().get(habitacionId).setEestadoHabitacion(EestadoHabitacion.DISPONIBLE);
    }
}
