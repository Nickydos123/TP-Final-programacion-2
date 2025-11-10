package interfaces;

import modelo.Reserva;
import modelo.Hotel;
import java.time.LocalDate;

public interface IRegistraReservas {
    Reserva registraReserva(int habitacionId, String dniPasajero, LocalDate desde, LocalDate hasta);
}
