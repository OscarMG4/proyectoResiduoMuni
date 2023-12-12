package pe.edu.utp.tp.componentes;

import java.util.*;
import java.util.stream.Collectors;

public class InputResiduosMunicipales {

    public static ResiduosMunicipales[] convertirArregloAResiduosMunicipales(String[] lineas) {
        List<ResiduosMunicipales> residuosList = new ArrayList<>();

        for (int i = 1; i < lineas.length; i++) {
            try {
                String linea = lineas[i];
                String[] segmento = linea.split(";");

                // Validar la cantidad de elementos en la línea
                if (segmento.length == 15) {
                    String fechaCorte = segmento[0].trim();
                    int nSec = parsearEntero(segmento[1].trim(), "nSec");
                    String ubigeo = segmento[2].trim();
                    String regNat = segmento[3].trim();
                    String departamento = segmento[4].trim();
                    String provincia = segmento[5].trim();
                    String distrito = segmento[6].trim();
                    int pobTotal = parsearEntero(segmento[7].trim(), "pobTotal");
                    int pobUrbana = parsearEntero(segmento[8].trim(), "pobUrbana");
                    int pobRural = parsearEntero(segmento[9].trim(), "pobRural");
                    double gpcDom = parsearDouble(segmento[10].trim(), "gpcDom");
                    double qResiduosDom = parsearDouble(segmento[11].trim(), "qResiduosDom");
                    double qResiduosNoDom = parsearDouble(segmento[12].trim(), "qResiduosNoDom");
                    double qResiduosMun = parsearDouble(segmento[13].trim(), "qResiduosMun");
                    int periodo = parsearEntero(segmento[14].trim(), "periodo");

                    ResiduosMunicipales residuos = new ResiduosMunicipales(
                            fechaCorte, nSec, ubigeo, regNat, departamento, provincia, distrito,
                            pobTotal, pobUrbana, pobRural, gpcDom, qResiduosDom, qResiduosNoDom,
                            qResiduosMun, periodo
                    );

                    residuosList.add(residuos);
                } else {
                    System.out.println("Error en la línea " + (i + 1) + ": No hay suficientes elementos en la línea.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error en la línea " + (i + 1) + ": Formato de número no válido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error en la línea " + (i + 1) + ": " + e.getMessage());
            }
        }

        // Convertir la lista a un arreglo y devolverlo
        return residuosList.toArray(new ResiduosMunicipales[0]);
    }

    private static int parsearEntero(String valor, String nombreCampo) {
        try {
            double valorDecimal = Double.parseDouble(valor);
            return (int) valorDecimal;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error en el campo '" + nombreCampo + "': Formato de número no válido.");
        }
    }

    private static double parsearDouble(String valor, String nombreCampo) {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error en el campo '" + nombreCampo + "': Formato de número no válido.");
        }
    }

    public static String conteoA(ResiduosMunicipales[] residuosMunicipales, int anioMin, int anioMax) {
        try {
            // Filtrar residuos de origen domiciliario y calcular total
            ResiduosMunicipales[] residuosDomesticos = Arrays.stream(residuosMunicipales)
                    .filter(fila -> fila.getQResiduosDom() > 0 &&
                            fila.getPeriodo() >= anioMin &&
                            fila.getPeriodo() <= anioMax)
                    .toArray(ResiduosMunicipales[]::new);

            // Estructura de datos para almacenar cantidades por año
            ResiduosMunicipales[] reporte = new ResiduosMunicipales[residuosDomesticos.length];

            // Calcular cantidades por año y agregar al reporte
            for (int i = 0; i < residuosDomesticos.length; i++) {
                int anio = residuosDomesticos[i].getPeriodo();
                reporte[i] = new ResiduosMunicipales(
                        String.valueOf(anio), 0, "", "", "", "", "", 0, 0, 0, 0,
                        residuosDomesticos[i].getQResiduosDom(), 0, 0, 0);
            }

            // Llamar a la función reporteA
            String reporteGenerado = Reporte.reporteA(Arrays.asList(reporte), anioMin, anioMax);

            return reporteGenerado;
        } catch (IllegalArgumentException e) {
            return "Error al generar el conteo A: " + e.getMessage();
        }
    }


    public static String conteoB(ResiduosMunicipales[] residuosMunicipales) {
        try {
            // Filtrar residuos no domiciliarios y calcular total
            ResiduosMunicipales[] residuosNoDomesticos = Arrays.stream(residuosMunicipales)
                    .filter(fila -> fila.getQResiduosNoDom() > 0)
                    .toArray(ResiduosMunicipales[]::new);

            // Estructura de datos para almacenar cantidades por año
            ResiduosMunicipales[] reporte = new ResiduosMunicipales[residuosNoDomesticos.length];

            // Calcular cantidades por año y agregar al reporte
            for (int i = 0; i < residuosNoDomesticos.length; i++) {
                int anio = residuosNoDomesticos[i].getPeriodo();
                reporte[i] = new ResiduosMunicipales(
                        String.valueOf(anio), 0, "", "", "", "", "", 0, 0, 0, 0,
                        residuosNoDomesticos[i].getQResiduosNoDom(), 0, 0, 0);
            }

            // Llamar a la función reporteB
            String reporteGenerado = Reporte.reporteB(Arrays.asList(reporte));

            return reporteGenerado;
        } catch (IllegalArgumentException e) {
            return "Error al generar el conteo B: " + e.getMessage();
        }
    }



    public static String conteoC(ResiduosMunicipales[] residuosMunicipales, int anioMin, int anioMax) {
        try {
            // Filtrar residuos municipales para Lambayeque y calcular total
            ResiduosMunicipales[] residuosMunicipalesLambayeque = Arrays.stream(residuosMunicipales)
                    .filter(fila -> fila.getDepartamento().equalsIgnoreCase("Lambayeque") &&
                            fila.getPeriodo() >= anioMin &&
                            fila.getPeriodo() <= anioMax)
                    .toArray(ResiduosMunicipales[]::new);

            // Estructura de datos para almacenar cantidades por año
            ResiduosMunicipales[] reporte = new ResiduosMunicipales[residuosMunicipalesLambayeque.length];

            // Calcular cantidades por año y agregar al reporte
            for (int i = 0; i < residuosMunicipalesLambayeque.length; i++) {
                int anio = residuosMunicipalesLambayeque[i].getPeriodo();
                reporte[i] = new ResiduosMunicipales(
                        String.valueOf(anio), 0, "", "Lambayeque", "", "", "", 0, 0, 0, 0,
                        residuosMunicipalesLambayeque[i].getQResiduosMun(), 0, 0, 0);
            }

            // Llamar a la función reporteC
            String reporteGenerado = Reporte.reporteC(Arrays.asList(reporte), anioMin, anioMax);

            return reporteGenerado;
        } catch (IllegalArgumentException e) {
            return "Error al generar el conteo C: " + e.getMessage();
        }
    }


    public static String conteoD(ResiduosMunicipales[] residuosMunicipales, String regionNatural) {
        try {
            // Verificar si la región natural existe
            boolean regionExistente = Arrays.stream(residuosMunicipales)
                    .anyMatch(fila -> fila.getRegNat().equalsIgnoreCase(regionNatural));

            if (!regionExistente) {
                return "La región natural especificada no existe en los datos.";
            }

            // Filtrar residuos de origen domiciliario y calcular total
            ResiduosMunicipales[] residuosDomiciliarios = Arrays.stream(residuosMunicipales)
                    .filter(fila -> fila.getQResiduosDom() > 0 && fila.getRegNat().equalsIgnoreCase(regionNatural))
                    .toArray(ResiduosMunicipales[]::new);

            // Estructura de datos para almacenar cantidades por región natural
            ResiduosMunicipales[] reporte = new ResiduosMunicipales[residuosDomiciliarios.length];

            // Calcular cantidades por región natural y agregar al reporte
            for (int i = 0; i < residuosDomiciliarios.length; i++) {
                reporte[i] = new ResiduosMunicipales(
                        "", 0, "", residuosDomiciliarios[i].getRegNat(), "", "", "",
                        0, 0, 0, 0, residuosDomiciliarios[i].getQResiduosDom(), 0, 0, 0);
            }

            // Llamar a la función reporteD
            String reporteGenerado = Reporte.reporteD(Arrays.asList(reporte), regionNatural);

            return reporteGenerado;
        } catch (IllegalArgumentException e) {
            return "Error al generar el conteo D: " + e.getMessage();
        }
    }



}
