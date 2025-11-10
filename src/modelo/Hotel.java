package modelo;

import enums.EestadoHabitacion;
import exceptions.ExceptionEstadoIlegal;
import exceptions.ExceptionIdNoencontrado;
import exceptions.ExceptionReservaConflicto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    private Map<Integer, Habitacion> habitaciones;
    private Map<Integer,Reserva> reservas;
    private Map<Integer,Estadia> estadias;
    //Usuarios no deberian estar en hotel, sino en Sistema


    public Hotel() {
        this.habitaciones = new HashMap<>();
        this.reservas = new HashMap<>();
        this.estadias = new HashMap<>();
    }

    public Hotel(Map<Integer, Habitacion> habitaciones, Map<Integer, Reserva> reservas, Map<Integer, Estadia> estadias) {
        this.habitaciones = habitaciones;
        this.reservas = reservas;
        this.estadias = estadias;
    }

    private boolean seSolapanLasFechas(LocalDate desde1, LocalDate hasta1, LocalDate desde2, LocalDate hasta2){
        if (desde1 == null || hasta1 == null || desde2 == null || hasta2 == null){
            return false;
        }
        return desde1.isBefore(hasta2) && desde2.isBefore(hasta1);
        //Este metodo se encarga de verificar si dos intervalos de fechas se solapan
    }

    private void puedeReservarHabitacion(int habitacionId, LocalDate desde, LocalDate hasta) throws ExceptionReservaConflicto {
        if (desde == null || hasta == null || !desde.isBefore(hasta)) {
            throw new ExceptionReservaConflicto("Fechas inválidas. 'desde' debe ser antes de 'hasta'.");
        }

        Habitacion habitacion = habitaciones.get(habitacionId);
        if(habitacion == null){
            throw  new ExceptionReservaConflicto("No existe la habitacion buscada");
        }

        if (habitacion.getEestadoHabitacion() == EestadoHabitacion.MANTENIMIENTO) {
            throw new ExceptionReservaConflicto("La habitación se encuentra en mantenimiento.");
        }


        for (Reserva reserva : reservas.values()){
            if(reserva.getIdHabitacion() == habitacionId){
                if (seSolapanLasFechas(desde,hasta, reserva.getDesde(), reserva.getHasta())){
                    throw new ExceptionReservaConflicto("La habitacion ya esta reservada en las fechas indicadas");
                }
            }
        }

        for (Estadia estadia : estadias.values()) {
            if (estadia.getIdHabitacion() == habitacionId) {
                if (seSolapanLasFechas(desde, hasta, estadia.getFechaCheckIn(), estadia.getFechaCheckOut())) {
                    throw new ExceptionReservaConflicto("La habitación está ocupada en las fechas indicadas");
                }
            }
        }
        //Logica para tener en cuenta que una habitacion deberia poder reservarse despues de que deje de estar ocupada
    }

    public void crearReserva(int habitacionId, String pasajeroDni, LocalDate desde, LocalDate hasta){
        puedeReservarHabitacion(habitacionId, desde, hasta); //Si la habitacion no se puede reservar, lanza una excepcion y termina el codigo.
        Reserva nuevaReserva = new Reserva(habitacionId, pasajeroDni, desde, hasta);//Crea la nueva reserva
        reservas.put(nuevaReserva.getId(),nuevaReserva);
    }

    public Estadia checkIn(int reservaId) throws ExceptionEstadoIlegal, ExceptionIdNoencontrado {
        Reserva reservaBuscada = reservas.get(reservaId);

        if (reservaBuscada == null) {
            throw new ExceptionIdNoencontrado("No se encontro la reserva con el ID proporcionado.");//Por si no se encontro la reserva
        }

        Habitacion habitacionDeLaReserva = habitaciones.get(reservaBuscada.getIdHabitacion());
        if (habitacionDeLaReserva.getEestadoHabitacion() != EestadoHabitacion.DISPONIBLE) {
            throw new ExceptionEstadoIlegal("La habitación no está en estado DISPONIBLE.");
        }

        Estadia nuevaEstadia = new Estadia(habitacionDeLaReserva.getId(),reservaBuscada.getPasajeroDni() ,reservaBuscada.getDesde(), reservaBuscada.getHasta());
        estadias.put(nuevaEstadia.getId(), nuevaEstadia);
        reservas.remove(reservaBuscada.getId());


        habitacionDeLaReserva.setEestadoHabitacion(EestadoHabitacion.OCUPADA);
        return nuevaEstadia;
    }

    public Estadia checkOut(int estadiaId) throws ExceptionEstadoIlegal,ExceptionIdNoencontrado {
        Estadia estadiaBuscada = estadias.get(estadiaId);

        if (estadiaBuscada == null) {
            throw new ExceptionIdNoencontrado("No se encontro la estadia con el ID proporcionado.");//Por si no se encontro la estadia
        }

        Habitacion habitacionDeLaEstadia = habitaciones.get(estadiaBuscada.getIdHabitacion());
        if (habitacionDeLaEstadia == null){
            throw  new ExceptionEstadoIlegal("No se encontro la habitacion asociada a la estadia");
        }

        if (habitacionDeLaEstadia.getEestadoHabitacion() != EestadoHabitacion.OCUPADA) {
            throw new ExceptionEstadoIlegal("La habitacion no esta en estado OCUPADA.");//Por si la habitacion no esta ocupada
        }

        estadiaBuscada.setFechaCheckOutReal(LocalDate.now());
        estadias.remove(estadiaBuscada.getId());

        habitacionDeLaEstadia.setEestadoHabitacion(EestadoHabitacion.DISPONIBLE);
        return estadiaBuscada;
    }

    public Map<Integer, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Map<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Map<Integer,Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Map<Integer,Reserva> reservas) {
        this.reservas = reservas;
    }

    public Map<Integer,Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(Map<Integer,Estadia> estadias) {
        this.estadias = estadias;
    }

}
