package enums;

public enum EestadoHabitacion {
    DISPONIBLE,//Una habitacion no tiene ninguna persona en ella y puede ser reservada o ocupada
    MANTENIMIENTO,//Una habitacion que no puede ser reservada ni ocupada porque esta en mantenimiento
    OCUPADA//Una habitacion que tiene una persona en ella
    //Se aca podrian agregar mas razones para que la habitacion este fuera de servicio
}
