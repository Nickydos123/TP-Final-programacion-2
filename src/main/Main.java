package main;
import modelo.*;


public class Main {
    static void main() {
        //Esto son solo pruebas luego aca deberia ir una clase menu
        Sistema sistema = SistemBackupper.leerSistema();

        sistema.login("admin","admin123");


        if(sistema.isAdminLoggedIn()){
            System.out.println("Bienvenido Administrador " + sistema.getCurrentUser().getNombre());
            Administrador currentAdmin = (Administrador) sistema.getCurrentUser();

            currentAdmin.BackupUsuarios();//Pruebo el backup de usuarios
        }

        sistema.logout();
        sistema.login("xXpacoXx","paco123");

        if(sistema.isRecepcionistaLoggedIn()){
            System.out.println("Bienvenido Recepcionista " + sistema.getCurrentUser().getNombre());
            Recepcionista currentRecepcionista = (Recepcionista) sistema.getCurrentUser();

            System.out.println(currentRecepcionista.mostrarHabitaciones());
            System.out.println(currentRecepcionista.mostrarReservas());
            System.out.println(currentRecepcionista.mostrarEstadias());
        }


    }

}
