package pe.edu.utp.tp.componentes;

import java.util.Comparator;
import java.util.List;

public class Reporte {

    public static String reporteA(List<ResiduosMunicipales> reporteFilas, int anioMin, int anioMax) {
        reporteFilas.sort(Comparator.comparing(ResiduosMunicipales::getPeriodo));
        StringBuilder reporte = new StringBuilder();
        String encabezado = "Reporte A: Cantidad de residuos de origen domiciliario por año dado un rango de años. (" + anioMin + " - " + anioMax + ")\n";
        reporte.append(encabezado);
        reporte.append("=======================================================\n");
        reporte.append("PERIODO        CANTIDAD_QRESIDUOS_DOM        PORCENTAJE\n");
        reporte.append("=======================================================\n");

        double totalCantidad = calcularTotalCantidad(reporteFilas);
        double porcentajeTotal = calcularPorcentajeTotal(reporteFilas, totalCantidad);

        for (ResiduosMunicipales fila : reporteFilas) {
            double porcentaje = fila.getQResiduosDom() / totalCantidad * 10;
            reporte.append(String.format("%-15s %22.0f %18.2f%%\n", fila.getPeriodo(), fila.getQResiduosDom(), porcentaje));
        }

        reporte.append("===========================\n");
        reporte.append(String.format("TOTAL %23.0f %18.2f%%\n", totalCantidad, porcentajeTotal));
        reporte.append("===========================\n\n");

        return reporte.toString();
    }


    public static String reporteB(List<ResiduosMunicipales> reporteFilas) {
        reporteFilas.sort(Comparator.comparing(ResiduosMunicipales::getPeriodo));
        StringBuilder reporte = new StringBuilder();
        String encabezado = "Reporte B: Cantidad de residuos de origen no domiciliario por cada año.\n";
        reporte.append(encabezado);
        reporte.append("===========================================================\n");
        reporte.append("PERIODO         CANTIDAD_QRESIDUOS_NO_DOM        PORCENTAJE\n");
        reporte.append("===========================================================\n");

        double totalCantidad = calcularTotalCantidad(reporteFilas);
        double porcentajeTotal = calcularPorcentajeTotal(reporteFilas, totalCantidad);

        for (ResiduosMunicipales fila : reporteFilas) {
            double porcentaje = fila.getQResiduosNoDom() / totalCantidad * 100;
            reporte.append(String.format("%-15s %22.0f %18.2f%%\n", fila.getPeriodo(), fila.getQResiduosNoDom(), porcentaje));
        }

        reporte.append("==============================\n");
        reporte.append(String.format("TOTAL %.2f %.2f%%\n", totalCantidad, porcentajeTotal));
        reporte.append("===============================\n");
        reporte.append("\n");

        return reporte.toString();
    }

    public static String reporteC(List<ResiduosMunicipales> reporteFilas, int anioMin, int anioMax) {
        reporteFilas.sort(Comparator.comparing(ResiduosMunicipales::getPeriodo));
        StringBuilder reporte = new StringBuilder();
        String encabezado = "Reporte C: Cantidad de residuos municipales por año dado un rango de años en el departamento de Lambayeque. (" + anioMin + " - " + anioMax + ")\n";
        reporte.append(encabezado);
        reporte.append("PERIODO      DEPARTAMENTO     CANTIDAD_QRESIDUOS_MUN     PORCENTAJE\n");
        reporte.append("===================================================================\n");

        double totalCantidad = calcularTotalCantidad(reporteFilas);
        double porcentajeTotal = calcularPorcentajeTotal(reporteFilas, totalCantidad);

        for (ResiduosMunicipales fila : reporteFilas) {
            double porcentaje = fila.getQResiduosMun() / totalCantidad * 100;
            reporte.append(String.format("%-12s %-15s %-23.2f %-10.2f%%\n", fila.getPeriodo(), "Lambayeque", fila.getQResiduosMun(), porcentaje));

        }

        reporte.append("===================================================================\n");
        reporte.append(String.format("TOTAL %.2f %.2f%%\n", totalCantidad, porcentajeTotal));
        reporte.append("\n");

        return reporte.toString();
    }

    public static String reporteD(List<ResiduosMunicipales> reporteFilas, String regionNatural) {
        reporteFilas.sort(Comparator.comparing(ResiduosMunicipales::getRegNat));
        StringBuilder reporte = new StringBuilder();
        String encabezado = "Reporte C: Cantidad de residuos de origen domiciliario por cada región natural. (" + regionNatural + ")\n";
        reporte.append(encabezado);
        reporte.append("REG_NAT              QRESIDUOS_DOM   PORCENTAJE\n");
        reporte.append("==============================================\n");

        double totalCantidad = calcularTotalCantidad(reporteFilas);
        double porcentajeTotal = calcularPorcentajeTotal(reporteFilas, totalCantidad);

        for (ResiduosMunicipales fila : reporteFilas) {
            double porcentaje = fila.getQResiduosDom() / totalCantidad * 100;
            reporte.append(String.format("%-15s %22.0f %18.2f%%\n", fila.getRegNat(), fila.getQResiduosDom(), porcentaje));
        }

        reporte.append("==============================================\n");
        reporte.append(String.format("TOTAL %23.0f %18.2f%%\n", totalCantidad, porcentajeTotal));
        reporte.append("\n");

        return reporte.toString();
    }


    private static double calcularTotalCantidad(List<ResiduosMunicipales> reporteFilas) {
        double totalCantidad = 0;
        for (ResiduosMunicipales fila : reporteFilas) {
            totalCantidad += fila.getQResiduosDom();
        }
        return totalCantidad;
    }

    private static double calcularPorcentajeTotal(List<ResiduosMunicipales> reporteFilas, double totalCantidad) {
        double porcentajeTotal = 0;
        for (ResiduosMunicipales residuosMunicipales : reporteFilas) {
            porcentajeTotal += (residuosMunicipales.getQResiduosDom() / totalCantidad) * 1000;
        }
        return porcentajeTotal;
    }


}
