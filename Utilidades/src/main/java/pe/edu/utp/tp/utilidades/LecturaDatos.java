package pe.edu.utp.tp.utilidades;

import java.util.Scanner;

public class LecturaDatos {
    static Scanner lector = new Scanner(System.in);

    // Método para leer una línea de texto del usuario
    public static String leerLinea(String mensaje) {
        System.out.println(mensaje);
        return lector.nextLine();
    }

    // Método para leer un número entero del usuario, con manejo de excepciones y repetición hasta obtener un valor válido
    public static int leerEntero(String mensaje) {
        System.out.println(mensaje);
        while (true) {
            try {
                int entero = lector.nextInt();
                // Se consume el salto de línea pendiente para evitar problemas en la siguiente lectura
                lector.nextLine();
                return entero;
            } catch (Exception e) {
                System.out.println("Se debe ingresar un número entero válido.");
                // Se consume el salto de línea pendiente en caso de excepción
                lector.nextLine();
            }
        }
    }


}