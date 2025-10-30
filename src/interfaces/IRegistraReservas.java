package interfaces;

import modelo.Reserva;

public interface IRegistraReservas {
    Reserva registraReserva(Hotel hotel, int habitacionId, String dniPasajero, LocalDate desde, LocalDate hasta);
}
