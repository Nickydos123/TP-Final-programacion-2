package exceptions;

public class ExceptionNoCurrentUser extends RuntimeException {
    public ExceptionNoCurrentUser() {
        super("No hay un usuario logeado actualmente");
    }
}
