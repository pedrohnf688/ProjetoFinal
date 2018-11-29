package com.example.phnf2.projetounidadefinal.modelo;

public class Ordenha {

    private String IdOrdenha;
    private double Gord;
    private double Prot;
    private double Cas;
    private double Lact;
    private double St;
    private double Esd;
    private double Nu;
    private double Cel;
    private double Ccs;

    public Ordenha() {
    }

    public Ordenha(String idOrdenha, double gord, double prot, double cas, double lact, double st, double esd, double nu, double cel, double ccs) {
        IdOrdenha = idOrdenha;
        Gord = gord;
        Prot = prot;
        Cas = cas;
        Lact = lact;
        St = st;
        Esd = esd;
        Nu = nu;
        Cel = cel;
        Ccs = ccs;
    }

    public String getIdOrdenha() {
        return IdOrdenha;
    }

    public void setIdOrdenha(String idOrdenha) {
        IdOrdenha = idOrdenha;
    }

    public double getGord() {
        return Gord;
    }

    public void setGord(double gord) {
        Gord = gord;
    }

    public double getProt() {
        return Prot;
    }

    public void setProt(double prot) {
        Prot = prot;
    }

    public double getCas() {
        return Cas;
    }

    public void setCas(double cas) {
        Cas = cas;
    }

    public double getLact() {
        return Lact;
    }

    public void setLact(double lact) {
        Lact = lact;
    }

    public double getSt() {
        return St;
    }

    public void setSt(double st) {
        St = st;
    }

    public double getEsd() {
        return Esd;
    }

    public void setEsd(double esd) {
        Esd = esd;
    }

    public double getNu() {
        return Nu;
    }

    public void setNu(double nu) {
        Nu = nu;
    }

    public double getCel() {
        return Cel;
    }

    public void setCel(double cel) {
        Cel = cel;
    }

    public double getCcs() {
        return Ccs;
    }

    public void setCcs(double ccs) {
        Ccs = ccs;
    }
}
