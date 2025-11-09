package main;

import modelo.Administrador;
import modelo.Hotel;
import modelo.Sistema;
import modelo.Usuario;

public class Main {
    static void main() {
        //Esto son solo pruebas luego aca deberia ir una clase menu
        Hotel hotel = new Hotel();
        Administrador administrador = new Administrador("Juan","Encargadovich","112435345","Calle 123","admin","admin123");
        Sistema sistema = new Sistema(hotel,administrador);

        sistema.login("admin","admin123");

        if(sistema.isAdminLoggedIn()){
            System.out.println("Bienvenido Administrador");
            Administrador currentAdmin = (Administrador) sistema.getCurrentUser();

            currentAdmin.addUsuario(new Usuario("Paco","Perez","353453456","Calle456","xXPacoXx","paco123"),sistema);

            //Hacen falta mas metodos en recepcionista y admin para manejar el tema de la muestra de datos(Esto de abajo es un prueba)
            System.out.println(sistema.getUsuarios().get("xXPacoXx").toString());
        }


    }

}
