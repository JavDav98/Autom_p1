package com.company;

import java.util.ArrayList;
import java.util.List;

public class Operacion {
    private int linea;
    private int resultado;
    private String txtoperacion;
    private Operacion suboperacion;
    private String tipoerror;
    private String output1;
    private String output2;
    List<String> elemento = new ArrayList<>();

    public List<String> getElemento() {
        return elemento;
    }

    public void setElemento(List<String> elemento) {
        this.elemento = elemento;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public String getTxtoperacion() {
        return txtoperacion;
    }

    public void setTxtoperacion(String txtoperacion) {
        this.txtoperacion = txtoperacion;
    }

    public Operacion getSuboperacion() {
        return suboperacion;
    }

    public void setSuboperacion(Operacion suboperacion) {
        this.suboperacion = suboperacion;
    }

    public String getTipoerror() {
        return tipoerror;
    }

    public void setTipoerror(String tipoerror) {
        this.tipoerror = tipoerror;
    }

    public String getOutput1() {
        return output1;
    }

    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getOutput2() {
        return output2;
    }

    public void setOutput2(String output2) {
        this.output2 = output2;
    }
}
