package pe.edu.utp.tp.aplicacion;

import pe.edu.utp.tp.componentes.ResiduosMunicipales;
import pe.edu.utp.tp.componentes.InputResiduosMunicipales;
import pe.edu.utp.tp.componentes.Menu;
import pe.edu.utp.tp.utilidades.ManejoArchivos;
import pe.edu.utp.tp.utilidades.ValidacionCredenciales;

import java.io.IOException;

public class AppResiduosMunicipales {
    public static void main(String[] args) {
        // Ruta del archivo CSV que contiene los datos de donantes
        String rutaArchivos = "Componentes\\src\\main\\resources\\B_Residuos municipales generados anualmente.csv";
        // Objeto para validar las credenciales de usuario desde el archivo "usuarios.txt"
        ValidacionCredenciales inicioSesion = new ValidacionCredenciales("Componentes\\src\\main\\resources\\usuarios.txt", 3);
        // Se valida las credenciales del usuario
        boolean usuarioValido = inicioSesion.validarUsuario();
        if (usuarioValido) {
            String[] lineas;
            try {
                // Se lee el archivo CSV y se obtiene las líneas de texto como un arreglo de cadenas
                lineas = ManejoArchivos.leerArchivoComoArreglo(rutaArchivos);
            } catch (IOException e) {
                // Manejo de excepciones en caso de error al leer el archivo
                ManejoArchivos.manejarExcepcion(inicioSesion.getUsuario(), "FileNotFoundException", e.getMessage());
                throw new RuntimeException(e);
            }
            // Se convierte el arreglo de líneas en un arreglo de objeto Donante
            ResiduosMunicipales[] baseDatos = InputResiduosMunicipales.convertirArregloAResiduosMunicipales(lineas);
            // Se crea un objeto Menu con la base de datos de donantes y el usuario actual
            Menu menu = new Menu(baseDatos, inicioSesion.getUsuario());
            // Se accede al menú principal
            menu.accederMenu();
        }
    }
}
