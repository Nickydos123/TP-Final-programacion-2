package main;

import enums.EestadoHabitacion;
import enums.EtipoHabitacion;
import modelo.*;

import java.time.LocalDate;

public class Main {
    static void main() {
        //Esto son solo pruebas luego aca deberia ir una clase menu
        Sistema sistema = new Sistema();
        Administrador startingAdmin = new Administrador("Juan","Adminovich","3344556","Calle 123","admin","admin123",sistema);
        startingAdmin.addUsuario(startingAdmin);

        sistema.login("admin","admin123");


        if(sistema.isAdminLoggedIn()){
            System.out.println("Bienvenido Administrador " + sistema.getCurrentUser().getNombre());
            Administrador currentAdmin = (Administrador) sistema.getCurrentUser();

            currentAdmin.addUsuario(new Usuario("Paco","Perez","456456456","Calle345","xXpacoXx","paco123",currentAdmin.getSistema()));
            currentAdmin.asignarTipo("xXpacoXx","recepcionista");
        }



        sistema.logout();
        sistema.login("xXpacoXx","paco123");


        if(sistema.isRecepcionistaLoggedIn()){
            System.out.println("Bienvenido Recepcionista " + sistema.getCurrentUser().getNombre());
            Recepcionista currentRecepcionista = (Recepcionista) sistema.getCurrentUser();

            System.out.println(currentRecepcionista.mostrarHabitaciones(sistema.getHotel()));
            System.out.println(currentRecepcionista.mostrarReservas(sistema.getHotel()));
            System.out.println(currentRecepcionista.mostrarEstadias(sistema.getHotel()));
        }


    }

}
