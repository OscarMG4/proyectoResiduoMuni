package pe.edu.utp.tp.componentes;

import pe.edu.utp.tp.utilidades.LecturaDatos;
import pe.edu.utp.tp.utilidades.ManejoArchivos;

import java.io.IOException;

public class Menu {

    private final ResiduosMunicipales[] residuosMunicipales;
    private final String usuario;
    private final String separador = "--------------------------------------------------";
    private String encabezadoMenu;
    private int modulo = 0;
    private String reporte;
    private boolean reporteGenerado = false;

    // Constructor parametrizado sobrecargado para residuosMunicipales
    public Menu(ResiduosMunicipales[] residuosMunicipales, String usuario) {
        this.residuosMunicipales = residuosMunicipales;
        this.usuario = usuario;
    }

    // Método que permite al usuario acceder al menú principal y realizar operaciones
    public void accederMenu() {
        // Opciones del menú principal
        String opciones = """
                1. Cantidad de residuos domiciliarios por año dado un rango de años.
                2. Cantidad de residuos no domiciliarios por año.
                3. Cantidad de residuos municipales por año dado un rango de años en el departamento de Lambayeque.
                4. Cantidad de residuos domiciliarios por cada región natural.
                0. FINALIZAR PROGRAMA""";
        // Se formatea el menú principal
        String menuPrincipal = formatearMenu("MENÚ PRINCIPAL", separador, opciones, 4);
        // Ciclo para mostrar el menú principal y permitir la selección de opciones
        while (true) {
            int opcion = LecturaDatos.leerEntero(menuPrincipal);
            switch (opcion) {
                case 1 -> {
                    this.encabezadoMenu = "MÓDULO 01 – CANTIDAD DE RESIDUOS DOMICILIARIOS POR AÑO";
                    modulo = 1;
                    submenu();
                }
                case 2 -> {
                    this.encabezadoMenu = "MÓDULO 02 – CANTIDAD DE RESIDUOS NO DOMICILIARIOS POR AÑO";
                    modulo = 2;
                    submenu();
                }
                case 3 -> {
                    this.encabezadoMenu = "MÓDULO 03 – CANTIDAD DE RESIDUOS MUNICIPALES POR AÑO EN LAMBAYEQUE";
                    modulo = 3;
                    submenu();
                }
                case 4 -> {
                    this.encabezadoMenu = "MÓDULO 04 – CANTIDAD DE RESIDUOS DOMICILIARIOS POR REGIÓN NATURAL";
                    modulo = 4;
                    submenu();
                }
                // Se finaliza el programa si se selecciona la opción 0
                case 0 -> {
                    System.out.println("Finalizando el programa...");
                    return;
                }
                default -> System.out.println("Opción inválida. Por favor, ingrese un valor válido.");
            }
        }
    }

    // Método que permite al usuario acceder a los submenús y realizar operaciones
    private void submenu() {
        // Opciones del submenú
        String submenuOpciones = """
                1. Imprimir por pantalla.
                2. Exportar a archivo plano.
                0. Volver al menú principal""";
        String menu = formatearMenu(encabezadoMenu, separador, submenuOpciones, 2);
        // Ciclo para mostrar el submenú y permitir la selección de opciones
        while (true) {
            int opcion = LecturaDatos.leerEntero(menu);
            switch (opcion) {
                case 1 -> {
                    // Se realiza la operación correspondiente al módulo y se muestra el resultado en la consola
                    realizarOperacion();
                    System.out.println(reporte);
                    reporteGenerado = true;
                }
                case 2 -> {
                    // Se guarda el reporte generado en un archivo si existe un reporte previamente realizado
                    if (reporteGenerado) {
                        guardarReporte();
                    } else {
                        System.out.println("No se ha generado ningún reporte");
                    }
                }
                case 0 -> {
                    // Se vuelve al menú principal y se reinicia la generación de reportes
                    reporteGenerado = false;
                    System.out.println("Volviendo al menú principal...");
                    return;
                }
                default -> System.out.println("Opción inválida. Por favor, ingrese un valor válido.");
            }
        }
    }

    // Método que realiza la operación correspondiente al módulo seleccionado
    private void realizarOperacion() {
        try {
            switch (modulo) {
                case 1 -> moduloA();
                case 2 -> moduloB();
                case 3 -> moduloC();
                case 4 -> moduloD();
                default -> throw new IllegalArgumentException("Valor de módulo inválido. " +
                        "Error en el método realizarOperacion - Clase Menu.");
            }
        } catch (IllegalArgumentException e) {
            // Manejo de excepciones para IllegalArgumentException
            ManejoArchivos.manejarExcepcion(usuario, "IllegalArgumentException", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    // Método correspondiente al módulo A
    private void moduloA() {
        int anioMin = LecturaDatos.leerEntero("Ingrese el año mínimo: ");
        int anioMax = LecturaDatos.leerEntero("Ingrese el año máximo: ");
        reporte = InputResiduosMunicipales.conteoA(residuosMunicipales, anioMin, anioMax);
    }

    // Método correspondiente al módulo B
    private void moduloB() {
        reporte = InputResiduosMunicipales.conteoB(residuosMunicipales);
    }

    // Método correspondiente al módulo C
    private void moduloC() {
        int anioMin = LecturaDatos.leerEntero("Ingrese el año mínimo: ");
        int anioMax = LecturaDatos.leerEntero("Ingrese el año máximo: ");
        reporte = InputResiduosMunicipales.conteoC(residuosMunicipales, anioMin, anioMax);
    }

    // Método correspondiente al módulo D
    private void moduloD() {
        String regionNatural = LecturaDatos.leerLinea("Ingrese la región natural: ");
        reporte = InputResiduosMunicipales.conteoD(residuosMunicipales, regionNatural);
    }

    // Método para guardar el reporte en un archivo plano
    private void guardarReporte() {
        String nombreArchivo = LecturaDatos.leerLinea("Ingrese el nombre del archivo (sin extensión): ");
        try {
            ManejoArchivos.guardarArchivo(nombreArchivo + ".txt", reporte);
            System.out.println("Reporte guardado exitosamente en el archivo: " + nombreArchivo + ".txt");
        } catch (IOException e) {
            System.out.println("Error al guardar el reporte en el archivo: " + e.getMessage());
        }
    }

    // Método para formatear el menú
    private String formatearMenu(String encabezado, String separador, String opciones, int maxOpciones) {
        return String.format("%s\n%s\n%s\n%s",
                encabezado, separador, opciones, separador);
    }
}
