package exceptions;

public class ExceptionUsuarioNoEncontrado extends RuntimeException {
    public ExceptionUsuarioNoEncontrado() {
        super("No se encontro el usuario buscado");
    }
}
