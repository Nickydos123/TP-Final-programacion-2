package interfaces;

import modelo.Habitacion;
import modelo.Hotel;

public interface IAgrega_Quita_Habitaciones {
    void addHabitacion(Habitacion habitacion);
    void removeHabitacion(int id);
}
