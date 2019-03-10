package com.example.jcmilena.jdainventario;

public class EquipoInformatico {

    String fabricante;
    String modelo;
    String MAC;
    String aula;

    public EquipoInformatico(String fabricante, String modelo, String MAC, String aula) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.MAC = MAC;
        this.aula = aula;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
