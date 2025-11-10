package interfaces;

import modelo.Habitacion;

public interface ICambiaEstadoHabitacion {
    void cambiarEstadoAMantenimiento(int habitacioId);
    void terMinarMantenimiento(int habitacionId);
}
