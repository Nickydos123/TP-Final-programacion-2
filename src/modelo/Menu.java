package modelo;

import enums.EtipoHabitacion;
import exceptions.*;


import java.util.InputMismatchException;
import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    private Sistema sistema;
    private Scanner entrada;

    public Menu(Sistema sistema) {
        this.sistema = sistema;
        this.entrada = new Scanner(System.in);
    }

    //Esto es una solucion para el problema de la compatibilidad del limpiado de pantalla segun el OS
    /*private void limpiarTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {
            System.out.println("No se pudo limpiar la terminal");
        }
    }*/


    private void menuBienvenida(){
        System.out.println("|--------------------------------------|");
        System.out.println("|Bienvenido al Administrador de Hoteles|");
        System.out.println("|--------------------------------------|");
        System.out.println();
        System.out.println("Ingrese su Nombre de Usuario:");
        String userName = entrada.nextLine();
        System.out.println("Ingrese su Contraseña:");
        String password = entrada.nextLine();
            sistema.login(userName,password);
            if (sistema.isAdminLoggedIn()){
                System.out.println("Bienvenido Administrador " + sistema.getCurrentUser().getNombre());
            } else if (sistema.isRecepcionistaLoggedIn()) {
                System.out.println("Bienvenido Recepcionista " + sistema.getCurrentUser().getNombre());
            } else if (sistema.isUserLoggedIn()) {
                System.out.println("Bienvenido Usuario " + sistema.getCurrentUser().getNombre());
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
                    try {
                        menuBienvenida();
                    }catch (ExceptionUsuarioNoAutorizado e){
                        System.out.println("No se pudo iniciar sesión. Verifique sus credenciales.");
                        break;
                    } /*finally {
                        limpiarTerminal();
                    }*/
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
        Backupper.backupHotel(sistema.getHotel());//Al salir hago un backup del hotel
        SistemBackupper.backupSistema(sistema);//Al salir hago un backup del sistema
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
            System.out.println("7. Respaldar datos del hotel");
            System.out.println("8. Respaldar datos del sistema");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();//Trim saca espacios en blanco

            switch (opcion) {
                case "1":
                    System.out.println("Usuarios registrados en el Sistema:");
                    System.out.println(currentAdmin.mostrarUsuarios());
                    break;
                case "2":
                    Usuario usuarioAAgregar = new Usuario();
                    System.out.println("Ingrese los datos del usuario que desea agregar");
                    System.out.println("Ingrese el nombre del usuario");
                    usuarioAAgregar.setNombre(entrada.nextLine());
                    System.out.println("Ingrese el apellido de usuario");
                    usuarioAAgregar.setApellido(entrada.nextLine());
                    System.out.println("Ingrese el domicilio del usuario");
                    usuarioAAgregar.setDomicilio(entrada.nextLine());
                    System.out.println("Ingrese el DNI del usuario");
                    usuarioAAgregar.setDni(entrada.nextLine());
                    System.out.println("Ingrese el Nombre de Usuario del usuario");
                    usuarioAAgregar.setUserName(entrada.nextLine());
                    System.out.println("Ingrese la contraseña del usuario");
                    usuarioAAgregar.setPassword(entrada.nextLine());
                    usuarioAAgregar.setSistema(sistema);//Tengo que agregar el sistema en usuario para que funcione correctamente

                    try {
                        currentAdmin.addUsuario(usuarioAAgregar);
                        System.out.println("Usuario agregado con exito.");
                    }catch (ExceptionUserNameRepetido e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "3":
                    System.out.println("Ingrese el Nombre de usuario del Usuario que desea eliminar");
                    String userNameIngresado = entrada.nextLine();
                    try {
                        currentAdmin.removeUsuario(userNameIngresado);
                        System.out.println("Usuario eliminado con exito.");
                    } catch (ExceptionUsuarioNoEncontrado e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "4":
                    System.out.println("Ingrese el Nombre de usuario del Usuario al que desea otorgar permisos");
                    String userNameAasignarPermisos = entrada.nextLine();
                    System.out.println("Ingrese: 'administrador' para asignar permisos de administrador");
                    System.out.println("ingrese: 'recepcionista' para aignar permisos de recepcionista");
                    String tipo = entrada.nextLine();
                    try {
                        currentAdmin.asignarTipo(userNameAasignarPermisos,tipo);
                        System.out.println("Permisos asignados con exito.");
                    }catch (ExceptionTipoNoValido | ExceptionUsuarioNoEncontrado e){
                        System.out.println(e.getMessage());
                    }

                    break;
                case "5":
                    Habitacion habitacionAAgregar = new Habitacion();
                    System.out.println("Ingrese el tipo de la habitacion a agregar (SENCILLA, DOBLE, SUITE):");
                    String tipoHabitacion = entrada.nextLine();
                    try {
                        habitacionAAgregar.setTipoHabitacion(EtipoHabitacion.valueOf(tipoHabitacion.toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de habitacion no valido. Usar: SENCILLA, DOBLE o SUITE");
                        break;
                    }
                    System.out.println("Ingrese el precio de la habitacion:");
                    try {
                        double precioHabitacion = entrada.nextDouble();
                        habitacionAAgregar.setPrecio(precioHabitacion);
                        entrada.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("Precio no valido. Ingrese un numero.");
                        break;
                    }


                    currentAdmin.addHabitacion(habitacionAAgregar);
                    System.out.println("Habitacion agregada con exito.");
                    break;
                case "6":
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar:");
                    try {
                        int idHabitacionAEliminar = entrada.nextInt();
                        currentAdmin.removeHabitacion(idHabitacionAEliminar);
                        System.out.println("Habitacion eliminada con exito.");
                    } catch (NumberFormatException e) {
                        System.out.println("ID no valido. Ingrese un numero.");
                    } catch (ExceptionIdNoencontrado e) {
                        System.out.println(e.getMessage());
                    }
                    entrada.nextLine();
                    break;
                case "7":
                        System.out.println("Respaldar Datos De Hotel");
                        currentAdmin.BackupHotel();
                        System.out.println("Hotel backup agregada con exito.");
                    break;
                case "8":
                        System.out.println("Respaldar Datos De Usuario");
                        currentAdmin.BackupUsuarios();
                        System.out.println("Usuario backup agregada con exito.");
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
        Recepcionista currentRecepcionista = (Recepcionista) sistema.getCurrentUser();
        boolean salir = false;
        while (!salir) {
            System.out.println();
            System.out.println("=== Menu Recepcionista ===");
            System.out.println("1. Mostrar Habitaciones");
            System.out.println("2. Mostrar Reservas");
            System.out.println("3. Mostrar Estadias");
            System.out.println("4. Registrar Reservas");
            System.out.println("5. Hacer Check In");
            System.out.println("6. Hacer Check Out");
            System.out.println("7. Cambiar Estado de Mantenimiento");
            System.out.println("8. Terminar el Mantenimiento");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();
            switch (opcion) {
                case "1":
                    System.out.println("Habitaciones Disponibles");
                    System.out.println(currentRecepcionista.mostrarHabitaciones());
                    break;
                case "2":
                    System.out.println("Mostrar Reservas");
                    System.out.println(currentRecepcionista.mostrarReservas());
                    break;
                case "3":
                    System.out.println("Mostrar Estadias");
                    System.out.println(currentRecepcionista.mostrarEstadias());
                    break;
                case "4":
                    Reserva reservaIngresar = new Reserva();
                    System.out.println("Registrar Reservas: ");

                    System.out.println("Ingrese el ID de la habitacion");
                    reservaIngresar.setIdHabitacion(entrada.nextInt());
                    entrada.nextLine();

                    System.out.println("Ingrese el DNI Pasajero");
                    reservaIngresar.setPasajeroDni(entrada.nextLine());
                    try {
                        System.out.println("Ingrese la fecha de la reserva:");
                        System.out.println("Ingrese el Año de desde");
                        int desdeAño = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese el Mes desde");
                        int desdeMes = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese el dia desde");
                        int desdeDia = entrada.nextInt();
                        entrada.nextLine();
                        reservaIngresar.setDesde(LocalDate.of(desdeAño, desdeMes, desdeDia));

                        System.out.println("Ingrese el año de hasta");
                        int hastaAño = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese el mes de hasta");
                        int hastaMes = entrada.nextInt();
                        entrada.nextLine();
                        System.out.println("Ingrese el dia de hasta");
                        int hastaDia = entrada.nextInt();
                        entrada.nextLine();
                        reservaIngresar.setHasta(LocalDate.of(hastaAño, hastaMes, hastaDia));
                    } catch (java.time.DateTimeException e) {
                        System.out.println("Fecha no valida.");
                        break;
                    }
                    try {
                       currentRecepcionista.registraReserva(reservaIngresar);
                        System.out.println("Reserva registrada con éxito");
                    } catch (ExceptionReservaConflicto e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    System.out.println("CheckIn");
                    System.out.println("Ingrese el ID de la reserva para hacer CheckIn:");
                    try {
                        currentRecepcionista.hacerCheckIn(entrada.nextInt());
                        entrada.nextLine();
                        System.out.println("CheckIn realizado con éxito");
                    } catch (ExceptionIdNoencontrado | ExceptionEstadoIlegal e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "6":
                    System.out.println("CheckOut");
                    System.out.println("Ingrese el ID de la estadia para hacer CheckOut:");
                    try {
                        Backupper.backupHistorialEstadias(currentRecepcionista.hacerCheckOut(entrada.nextInt()),"historial_estadias.json");//Guardo el historial antes de hacer el checkout
                        entrada.nextLine();
                        System.out.println("CheckOut realizado con éxito");
                    } catch (ExceptionIdNoencontrado | ExceptionEstadoIlegal e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "7":
                    System.out.println("Cambiar Estado de Mantenimiento");
                    System.out.println("Ingrese el ID de la habitacion a cambiar de estado:");
                    try {
                        currentRecepcionista.cambiarEstadoAMantenimiento(entrada.nextInt());
                        entrada.nextLine();
                        System.out.println("El estado se actualizó con éxito");
                    } catch (ExceptionIdNoencontrado | ExceptionHabitacionNoDisponible e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "8":
                    System.out.println("Terminar Mantenimiento");
                    System.out.println("Ingrese el ID de la habitacion a cambiar de estado:");
                    try {
                        currentRecepcionista.terMinarMantenimiento(entrada.nextInt());
                        entrada.nextLine();
                        System.out.println("Mantenimiento finalizado con éxito");
                    } catch (ExceptionIdNoencontrado e) {
                        System.out.println(e.getMessage());
                    }

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
            System.out.println("Los usuarios nencesitan permisos especiales para acceder a funcionalidades");
            System.out.println("9. Cerrar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("> ");
            String opcion = entrada.nextLine().trim();
            switch (opcion) {
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
