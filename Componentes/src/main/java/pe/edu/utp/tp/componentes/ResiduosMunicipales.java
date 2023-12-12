package pe.edu.utp.tp.componentes;

public class ResiduosMunicipales {
    private String fechaCorte;
    private int nSec;
    private String ubigeo;
    private String regNat;
    private String departamento;
    private String provincia;
    private String distrito;
    private int pobTotal;
    private int pobUrbana;
    private int pobRural;
    private double gpcDom;
    private double qResiduosDom;
    private double qResiduosNoDom;
    private double qResiduosMun;
    private int periodo;

    public ResiduosMunicipales(String fechaCorte, int nSec, String ubigeo, String regNat, String departamento,
                               String provincia, String distrito, int pobTotal, int pobUrbana, int pobRural, double gpcDom,
                               double qResiduosDom, double qResiduosNoDom, double qResiduosMun, int periodo) {
        this.fechaCorte = fechaCorte;
        this.nSec = nSec;
        this.ubigeo = ubigeo;
        this.regNat = regNat;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.pobTotal = pobTotal;
        this.pobUrbana = pobUrbana;
        this.pobRural = pobRural;
        this.gpcDom = gpcDom;
        this.qResiduosDom = qResiduosDom;
        this.qResiduosNoDom = qResiduosNoDom;
        this.qResiduosMun = qResiduosMun;
        this.periodo = periodo;
    }

    // Métodos getter y setter
    public String getFechaCorte() {
        return fechaCorte;
    }

    public void setFechaCorte(String fechaCorte) {
        this.fechaCorte = fechaCorte;
    }

    public int getNSec() {
        return nSec;
    }

    public void setNSec(int nSec) {
        this.nSec = nSec;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getRegNat() {
        return regNat;
    }

    public void setRegNat(String regNat) {
        this.regNat = regNat;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getPobTotal() {
        return pobTotal;
    }

    public void setPobTotal(int pobTotal) {
        this.pobTotal = pobTotal;
    }

    public int getPobUrbana() {
        return pobUrbana;
    }

    public void setPobUrbana(int pobUrbana) {
        this.pobUrbana = pobUrbana;
    }

    public int getPobRural() {
        return pobRural;
    }

    public void setPobRural(int pobRural) {
        this.pobRural = pobRural;
    }

    public double getGpcDom() {
        return gpcDom;
    }

    public void setGpcDom(double gpcDom) {
        this.gpcDom = gpcDom;
    }

    public double getQResiduosDom() {
        return qResiduosDom;
    }

    public void setQResiduosDom(int qResiduosDom) {
        this.qResiduosDom = qResiduosDom;
    }

    public double getQResiduosNoDom() {
        return qResiduosNoDom;
    }

    public void setQResiduosNoDom(int qResiduosNoDom) {
        this.qResiduosNoDom = qResiduosNoDom;
    }

    public double getQResiduosMun() {
        return qResiduosMun;
    }

    public void setQResiduosMun(int qResiduosMun) {
        this.qResiduosMun = qResiduosMun;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

}
