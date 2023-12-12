package pe.edu.utp.tp.utilidades;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ValidacionCredenciales {
    private static String rutaUsuarios;
    private final int intentos;
    private String usuario;

    public ValidacionCredenciales(String rutaUsuarios, int intentos) {
        ValidacionCredenciales.rutaUsuarios = rutaUsuarios;
        this.intentos = intentos;
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean validarUsuario() {
        boolean usuarioValido = false;
        int intentosUsados = 0;


            // Ciclo para validar el usuario y contraseña hasta que sean correctos o se agoten los intentos
            while (!usuarioValido && intentosUsados < intentos) {
                usuario = LecturaDatos.leerLinea("Ingresar usuario: ");
                String contrasena = LecturaDatos.leerLinea("Ingresar contraseña: ");
                usuarioValido = verificarCredenciales(usuario, contrasena);

                if (!usuarioValido) {
                    intentosUsados++;
                    System.out.println("Usuario o contraseña inválidos.\nIntentos restantes: "
                            + (intentos - intentosUsados));
                }
            }

        // Mostrar mensaje de bienvenida o acceso denegado
        if (usuarioValido) {
            System.out.println("Bienvenido al sistema");
        } else {
            System.out.println("Se han agotado los intentos. Acceso denegado.");
        }

        return usuarioValido;
    }



    // Método privado para verificar las credenciales del usuario
    private static boolean verificarCredenciales(String usuario, String contrasena) {
        try {
            String[] lineas = ManejoArchivos.leerArchivoComoArreglo(rutaUsuarios);
            for (String linea : lineas) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String usuarioArchivo = partes[0];
                    String contrasenaArchivo = partes[1];
                    if (usuario.equals(usuarioArchivo) && contrasena.equals(contrasenaArchivo)) {
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // Manejo de excepción en caso de error de lectura del archivo de usuarios
            ManejoArchivos.manejarExcepcion("Sin Usuario", "FileNotFoundException", e.getMessage());
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
