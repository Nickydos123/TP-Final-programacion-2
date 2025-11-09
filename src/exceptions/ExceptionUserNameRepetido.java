package exceptions;

public class ExceptionUserNameRepetido extends RuntimeException {
    public ExceptionUserNameRepetido() {
        super("Este nombre de usuario ya esta en uso");
    }
}
