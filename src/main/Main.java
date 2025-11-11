package main;
import modelo.*;


public class Main {
    static void main() {
        Sistema sistema = SistemBackupper.leerSistema();
        sistema.setHotel(Backupper.leerHotel());
        Menu menu = new Menu(sistema);
        menu.startMenu();
    }
}
