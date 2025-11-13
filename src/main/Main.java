package main;
import modelo.*;

import java.io.FileNotFoundException;
//AVISO: El programa se puede iniciar con un usuario de nombre "default" y contraseña "default" si no se encuentra un respaldo de usuarios previos
public class Main {
    public static void main(String[] args) {
        Sistema sistema;
        // Intentar leer Sistema; si no existe, crear uno vacío
        try {
            sistema = SistemBackupper.leerSistema();
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró respaldo de Sistema. Iniciando sistema con datos predeterminados.");
            sistema = new Sistema();
        }
        // Intentar leer Hotel por separado
        try {
            Hotel hotel = Backupper.leerHotel();
            sistema.setHotel(hotel);
        } catch (FileNotFoundException e) {
            System.out.println("No se encontró respaldo de Hotel. Creando hotel vacío.");
            sistema.setHotel(new Hotel());
        }

        if (sistema.getHotel() != null) {
            Habitacion.actualizarNextId(sistema.getHotel().getHabitaciones().values());
        }
        // Sincronizar IDs de reservas y estadias con lo cargado desde JSON
        try {
            Reserva.actualizarNextId(sistema.getHotel().getReservas().values());
        } catch (Exception ex) {
            try { Reserva.actualizarNextId((java.util.Collection) sistema.getHotel().getReservas()); } catch (Exception ignored) {}
        }
        try {
            Estadia.actualizarNextId(sistema.getHotel().getEstadias().values());
        } catch (Exception ex) {
            try { Estadia.actualizarNextId((java.util.Collection) sistema.getHotel().getEstadias()); } catch (Exception ignored) {}
        }

        // Que siempre exista un usuario administrador por defecto
        String defaultAdminUser = "defaultAdmin";
        if (!sistema.getUsuarios().containsKey(defaultAdminUser)) {
            Administrador admin = new Administrador(defaultAdminUser, defaultAdminUser, "1", "1", "default", "default", sistema);
            sistema.getUsuarios().put(admin.getUserName(), admin);
        }

        Menu menu = new Menu(sistema);
        menu.startMenu();
    }
}

