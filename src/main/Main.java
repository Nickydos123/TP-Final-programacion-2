package main;
import modelo.*;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        Sistema sistema;
        // Intentar leer Sistema; si no existe, crear uno vacío
        try {
            sistema = SistemBackupper.leerSistema();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró respaldo de Sistema. Iniciando sistema con datos predeterminados.");
            sistema = new Sistema();
        } catch (Exception e) {
            System.out.println("Error al leer Sistema: " + e.getMessage());
            sistema = new Sistema();
        }

        // Intentar leer Hotel por separado
        try {
            Hotel hotel = Backupper.leerHotel();
            sistema.setHotel(hotel);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró respaldo de Hotel. Creando hotel vacío.");
            sistema.setHotel(new Hotel());
        } catch (Exception e) {
            System.out.println("Error al leer Hotel: " + e.getMessage());
            sistema.setHotel(new Hotel());
        }

        // Asegurar administrador si no existe
        String defaultAdminUser = "defaultAdmin";
        if (!sistema.getUsuarios().containsKey(defaultAdminUser)) {
            Administrador admin = new Administrador(defaultAdminUser, defaultAdminUser, "1", "1", "default", "default", sistema);
            sistema.getUsuarios().put(admin.getUserName(), admin);
        }

        Menu menu = new Menu(sistema);
        menu.startMenu();
    }
}

