package pe.edu.utp.tp.utilidades;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos {
    // Método para leer un archivo de texto y devolver su contenido como un arreglo de líneas
    public static String[] leerArchivoComoArreglo(String ruta) throws IOException {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        }
        return lineas.toArray(new String[0]);
    }

    // Método para guardar un contenido de texto en un archivo en la ruta especificada
    public static void guardarArchivo(String contenido, String ruta) throws IOException {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(ruta))) {
            escritor.write(contenido);
        }
    }

    // Método para manejar excepciones y registrar un log de auditoría
    public static void manejarExcepcion(String usuario, String tipoError, String mensajeError) {
        // Obtener la fecha y hora actual
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String hora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        // Formatear el contenido del log con los detalles del error y usuario
        String contenido = String.format("Fecha: %s - Hora: %s - Usuario: %s - Error: %s - Mensaje: %s%n",
                fecha, hora, usuario, tipoError, mensajeError);
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter("auditoria.log", true))) {
            // Escribir el contenido en el archivo de log "auditoria.log"
            escritor.write(contenido);
        } catch (IOException e) {
            // Manejo de excepciones para IOException
            e.printStackTrace();
        }
    }
}