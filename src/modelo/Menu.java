package modelo;

import exceptions.*;
import org.json.JSONException;

import java.util.Scanner;

public class Menu {
    private Sistema sistema;
    private Scanner entrada;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.entrada = new Scanner(System.in);
    }

    //Esto es una solucion para el problema de la compatibilidad del limpiado de pantalla segun el OS
    private void limpiarTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la terminal");
        }
    }


    private void menuBienvenida(){
        System.out.println("|--------------------------------------|");
        System.out.println("|Bienvenido al Administrador de Hoteles|");
        System.out.println("|--------------------------------------|");
        System.out.println();
        System.out.println("Ingrese su Nombre de Usuario:");
        String userName = entrada.nextLine();
        System.out.println("Ingrese su Contrasenia:");
        String password = entrada.nextLine();

        try {
            sistema.login(userName,password);
            if (sistema.isAdminLoggedIn()){
                System.out.println("Bienvenido Administrador " + sistema.getCurrentUser().getNombre());
            } else if (sistema.isRecepcionistaLoggedIn()) {
                System.out.println("Bienvenido Recepcionista " + sistema.getCurrentUser().getNombre());
            } else if (sistema.isUserLoggedIn()) {
                System.out.println("Bienvenido Usuario " + sistema.getCurrentUser().getNombre());
            }
        } catch (ExceptionUsuarioNoAutorizado e) {
            System.out.println(e.getMessage());
        }finally {
            limpiarTerminal();
        }
    }

    public void startMenu(){
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Iniciar sesión");
            System.out.println("0. Salir");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();
            switch (opcion) {
                case "1":
                    menuBienvenida();
                    if (sistema.isAdminLoggedIn()) {
                        menuAdmin(sistema, entrada);
                    } else if (sistema.isRecepcionistaLoggedIn()) {
                        menuRecepcionista(sistema, entrada);
                    } else if (sistema.isUserLoggedIn()) {
                        menuUsuario(sistema, entrada);
                    } else {
                        System.out.println("No se ha iniciado sesión correctamente.");
                    }
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuAdmin(Sistema sistema, Scanner entrada) {
        Administrador currentAdmin = (Administrador) sistema.getCurrentUser();
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== Menu Administrador ===");
            System.out.println("1. Mostrar Usuarios");
            System.out.println("2. Agregar un Usuario nuevo");
            System.out.println("3. Eliminar un Usuario");
            System.out.println("4. Asignar tipo a un Usuario");
            System.out.println("5. Agregar una Habitacion al Hotel");
            System.out.println("6. Eliminar una Habitacion del Hotel");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();//Trim saca espacios en blanco

            switch (opcion) {
                case "1":
                    System.out.println("Usuarios registrados en el Sistema:");
                    currentAdmin.mostrarUsuarios();
                    break;
                case "2":
                    System.out.println("Ingrese los datos del usuario que desea agregar");
                    System.out.println("Ingrese el nombre del usuario");
                    String nombre = entrada.nextLine();
                    System.out.println("Ingrese el apellido de usuario");
                    String apellido = entrada.nextLine();
                    System.out.println("Ingrese el domicilio del usuario");
                    String domicilio = entrada.nextLine();
                    System.out.println("Ingrese le DNI del usuario");
                    String dni = entrada.nextLine();
                    System.out.println("Ingrese el Nombre de Usuario del usuario");
                    String userName = entrada.nextLine();
                    System.out.println("Ingrese la contraseña del usuario");
                    String password = entrada.nextLine();

                    try {
                        currentAdmin.addUsuario(new Usuario(nombre,apellido,dni,domicilio,userName,password,sistema));
                    }catch (ExceptionUserNameRepetido e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    System.out.println("Ingrese el Nombre de usuario del Usuario que desea eliminar");
                    String userNameIngresado = entrada.nextLine();
                    try {
                        currentAdmin.removeUsuario(userNameIngresado);
                    } catch (ExceptionUsuarioNoEncontrado e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    System.out.println("Ingrese el Nombre de usuario del Usuario al que desea otorgar permisos");
                    String userNameAasignarPermisos = entrada.nextLine();
                    System.out.println("Ingrese: 'administrador' para asignar permisos de administrador");
                    System.out.println("ingrese: 'recepcionista' para aignar permisos de recpcionista");
                    String tipo = entrada.nextLine();
                    try {
                        currentAdmin.asignarTipo(userNameAasignarPermisos,tipo);
                    }catch (ExceptionTipoNoValido e){

                    }catch (ExceptionUsuarioNoEncontrado e1){

                    }

                    break;
                case "5":
                    System.out.println("");
                    break;
                case "6":
                    System.out.println("");
                    break;
                case "9":
                    try {
                        sistema.logout();
                    } catch (ExceptionNoCurrentUser e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Sesión cerrada.");
                    salir = true;
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuRecepcionista(Sistema sistema, Scanner entrada) {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== Menu Recepcionista ===");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();
            switch (opcion) {
                case "1":
                    System.out.println("");
                    break;
                case "2":
                    System.out.println("");
                    break;
                case "3":
                    System.out.println("");
                    break;
                case "9":
                    try {
                        sistema.logout();
                    } catch (ExceptionNoCurrentUser e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Sesión cerrada.");
                    salir = true;
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void menuUsuario(Sistema sistema, Scanner entrada) {
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== Menu Usuario ===");
            System.out.println("");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();
            switch (opcion) {
                case "1":
                    System.out.println("[USUARIO] Ver mis reservas -> integrar llamada a Sistema");
                    break;
                case "2":
                    System.out.println("[USUARIO] Hacer una reserva -> integrar llamada a Sistema");
                    break;
                case "9":
                    try {
                        sistema.logout();
                    } catch (ExceptionNoCurrentUser e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Sesión cerrada.");
                    salir = true;
                    break;
                case "0":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

}
