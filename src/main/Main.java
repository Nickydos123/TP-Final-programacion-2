package main;

import enums.EestadoHabitacion;
import enums.EtipoHabitacion;
import modelo.*;

import java.time.LocalDate;

public class Main {
    static void main() {
        //Esto son solo pruebas luego aca deberia ir una clase menu
        Hotel hotel = new Hotel();
        Sistema sistema = new Sistema(hotel);
        Administrador startingAdmin = new Administrador("Juan","Adminovich","3344556","Calle 123","admin","admin123",sistema);
        startingAdmin.addUsuario(startingAdmin);

        sistema.login("admin","admin123");

        if(sistema.isAdminLoggedIn()){
            System.out.println("Bienvenido Administrador " + sistema.getCurrentUser().getNombre());
            Administrador currentAdmin = (Administrador) sistema.getCurrentUser();

            currentAdmin.addUsuario(new Usuario("Paco","Perez","456456456","Calle345","xXpacoXx","paco123",currentAdmin.getSistema()));
            currentAdmin.asignarTipo("xXpacoXx","recepcionista");

            currentAdmin.addHabitacion(new Habitacion(EtipoHabitacion.SENCILLA,3000, EestadoHabitacion.DISPONIBLE));
            currentAdmin.addHabitacion(new Habitacion(EtipoHabitacion.SENCILLA,3000, EestadoHabitacion.DISPONIBLE));
            currentAdmin.addHabitacion(new Habitacion(EtipoHabitacion.SUITE,3000, EestadoHabitacion.DISPONIBLE));
        }

        sistema.logout();
        sistema.login("xXpacoXx","paco123");

        if(sistema.isRecepcionistaLoggedIn()){
            System.out.println("Bienvenido Recepcionista " + sistema.getCurrentUser().getNombre());
            Recepcionista currentRecepcionista = (Recepcionista) sistema.getCurrentUser();

            System.out.println(currentRecepcionista.mostrarHabitaciones(sistema.getHotel()));

            currentRecepcionista.registraReserva(sistema.getHotel(),1,"44635116", LocalDate.of(2002,12,16),LocalDate.of(2002,12,20));
            currentRecepcionista.registraReserva(sistema.getHotel(),1,"254234534",LocalDate.of(2002,12,20), LocalDate.of(2002,12,25));
            //currentRecepcionista.registraReserva(sistema.getHotel(),1,"456745646",LocalDate.of(2002,12,17), LocalDate.of(2002,12,25)); //Funciona el solapamiento de fechas

            currentRecepcionista.hacerCheckIn(sistema.getHotel(),1);
            currentRecepcionista.registraReserva(sistema.getHotel(),2,"456745646",LocalDate.of(2002,12,17), LocalDate.of(2002,12,25)); //Funciona el solapamiento de fechas


            System.out.println(currentRecepcionista.mostrarReservas(sistema.getHotel()));
            System.out.println(currentRecepcionista.mostrarEstadias(sistema.getHotel()));

            System.out.println(currentRecepcionista.hacerCheckOut(sistema.getHotel(),1).toString());//Cuando hago el checkout retorno la reserva terminada
        }
    }

}
