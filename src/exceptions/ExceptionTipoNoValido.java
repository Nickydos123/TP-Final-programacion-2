package exceptions;

public class ExceptionTipoNoValido extends RuntimeException {
    public ExceptionTipoNoValido() {
        super("El tipo de usuario Ingresado no es valido");
    }
}
