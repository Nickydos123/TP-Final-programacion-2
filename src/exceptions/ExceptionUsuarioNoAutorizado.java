package exceptions;

public class ExceptionUsuarioNoAutorizado extends RuntimeException {
    public ExceptionUsuarioNoAutorizado(String message) {
        super(message);
    }
}
