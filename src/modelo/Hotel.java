package modelo;

import enums.EestadoHabitacion;
import exceptions.ExceptionEstadoIlegal;
import exceptions.ExceptionHabitacionNoDisponible;
import exceptions.ExceptionReservaConflicto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Hotel {
    private Map<Integer, Habitacion> habitaciones;
    private HashSet<Reserva> reservas;
    private List<Estadia> estadias;
    private Map<String,Usuario> usuarios;

    private void puedeReservarHabitacion(int habitacionId, LocalDate desde, LocalDate hasta) throws ExceptionReservaConflicto {
        for (Reserva reserva : reservas) {
            if (reserva.getIdHabitacion() == habitacionId) {
                if (!(hasta.isBefore(reserva.getDesde()) || desde.isAfter(reserva.getHasta()))) {
                    throw new ExceptionReservaConflicto("La habitacion ya esta reservada en las fechas indicadas.");
                }

                if(habitaciones.get(habitacionId).getEestadoHabitacion() == enums.EestadoHabitacion.OCUPADA){
                    throw new ExceptionReservaConflicto("La habitacion se encuentra ocupada.");
                }

                if(habitaciones.get(habitacionId).getEestadoHabitacion() == EestadoHabitacion.MANTENIMIENTO){
                    throw new ExceptionReservaConflicto("La habitacion se encuentra en mantenimiento.");
                }
                //La logica de mantenimiento se podria mejorar si se la trata de manera similar a las reservas.
            }
        }
    }

    public void crearReserva(int habitacionId, String pasajeroDni, LocalDate desde, LocalDate hasta) throws ExceptionReservaConflicto {
        puedeReservarHabitacion(habitacionId, desde, hasta); //Si la habitacion no se puede reservar, lanza una excepcion y termina el codigo.
        Reserva nuevaReserva = new Reserva(habitacionId, pasajeroDni, desde, hasta);//Crea la nueva reserva
        reservas.add(nuevaReserva);
        habitaciones.get(habitacionId).setEestadoHabitacion(EestadoHabitacion.RESERVADA);//Cambia el estado de la habitacion a reservada
    }

    public Estadia checkIn(int reservaId) throws ExceptionHabitacionNoDisponible, ExceptionEstadoIlegal {
        Reserva reservaBuscada = null;
        for(Reserva reserva: reservas){
            if(reserva.getId() == reservaId){
                reservaBuscada = reserva; //Busco la reserva con el ID proporcionado
                break;
            }
        }

        if (reservaBuscada == null) {
            throw new ExceptionEstadoIlegal("No se encontro la reserva con el ID proporcionado.");//Por si no se encontro la reserva
        }

        Habitacion habitacionDeLaReserva = habitaciones.get(reservaBuscada.getIdHabitacion());
        if (habitacionDeLaReserva.getEestadoHabitacion() != EestadoHabitacion.RESERVADA) {
            throw new ExceptionEstadoIlegal("La habitacion no esta en estado RESERVADA.");//Por si la habitacion ya no esta reservada
        }

        habitacionDeLaReserva.setEestadoHabitacion(EestadoHabitacion.OCUPADA);
        Estadia nuevaEstadia = new Estadia(habitacionDeLaReserva.getId(), reservaBuscada.getDesde(), reservaBuscada.getHasta());
        estadias.add(nuevaEstadia);
        return nuevaEstadia; //Tambien podria devolver void si no se necesita la estadia creada
    }

    public void checkOut(int estadiaId) throws ExceptionEstadoIlegal {
        Estadia estadiaBuscada = null;
        for(Estadia estadia: estadias){
            if(estadia.getId() == estadiaId){
                estadiaBuscada = estadia; //Busco la estadia con el ID proporcionado
                break;
            }
        }

        if (estadiaBuscada == null) {
            throw new ExceptionEstadoIlegal("No se encontro la estadia con el ID proporcionado.");//Por si no se encontro la estadia
        }

        Habitacion habitacion = habitaciones.get(estadiaBuscada.getIdHabitacion());
        if (habitacion.getEestadoHabitacion() != EestadoHabitacion.OCUPADA) {
            throw new ExceptionEstadoIlegal("La habitacion no esta en estado OCUPADA.");//Por si la habitacion no esta ocupada
        }

        habitacion.setEestadoHabitacion(EestadoHabitacion.DISPONIBLE);
    }

    //Yo diria que aca hay que implementar funcionalidad que esta en Recpcionista

    public Map<Integer, Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Map<Integer, Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    public HashSet<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(HashSet<Reserva> reservas) {
        this.reservas = reservas;
    }

    public List<Estadia> getEstadias() {
        return estadias;
    }

    public void setEstadias(List<Estadia> estadias) {
        this.estadias = estadias;
    }

    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Map<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Habitacion buscarHabitacionPorId(int habitacionId) {
        return habitaciones.get(habitacionId);
    }

    public Reserva buscarReservaPorId(int reservaId) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == reservaId) {
                return reserva;
            }
        }
        return null;
    }

    public Estadia buscarEstadiaPorId(int estadiaId) {
        for (Estadia estadia : estadias) {
            if (estadia.getId() == estadiaId) {
                return estadia;
            }
        }
        return null;
    }
}
