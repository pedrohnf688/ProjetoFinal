package com.example.phnf2.projetounidadefinal.modelo;

public class Administrador {

    private String idAdmin;
    private String nomeAdmin;
    private String photoAdmin;
    private String emailAdmin;


    public Administrador() {
    }


    public Administrador(String idAdmin, String nomeAdmin, String photoAdmin, String emailAdmin) {
        this.idAdmin = idAdmin;
        this.nomeAdmin = nomeAdmin;
        this.photoAdmin = photoAdmin;
        this.emailAdmin = emailAdmin;
    }

    public String getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(String idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNomeAdmin() {
        return nomeAdmin;
    }

    public void setNomeAdmin(String nomeAdmin) {
        this.nomeAdmin = nomeAdmin;
    }

    public String getPhotoAdmin() {
        return photoAdmin;
    }

    public void setPhotoAdmin(String photoAdmin) {
        this.photoAdmin = photoAdmin;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }

    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }
}
