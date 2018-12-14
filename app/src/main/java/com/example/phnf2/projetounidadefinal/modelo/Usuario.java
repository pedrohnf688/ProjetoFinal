package com.example.phnf2.projetounidadefinal.modelo;

public class Usuario {

    private String IdUser;
    private String NomeUser;
    private String EmailUser;
    private String PhotoUser;

    public Usuario() {
    }

    public Usuario(String idUser, String nomeUser, String emailUser, String photoUser) {
        IdUser = idUser;
        NomeUser = nomeUser;
        EmailUser = emailUser;
        PhotoUser = photoUser;
    }

    public String getIdUser() {
        return IdUser;
    }

    public void setIdUser(String idUser) {
        IdUser = idUser;
    }

    public String getNomeUser() {
        return NomeUser;
    }

    public void setNomeUser(String nomeUser) {
        NomeUser = nomeUser;
    }

    public String getEmailUser() {
        return EmailUser;
    }

    public void setEmailUser(String emailUser) {
        EmailUser = emailUser;
    }

    public String getPhotoUser() {
        return PhotoUser;
    }

    public void setPhotoUser(String photoUser) {
        PhotoUser = photoUser;
    }
}
