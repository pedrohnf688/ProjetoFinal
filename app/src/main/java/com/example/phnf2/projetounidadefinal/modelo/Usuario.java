package com.example.phnf2.projetounidadefinal.modelo;

public class Usuario {

    private String IdUser;
    private String NomeUser;
    private String EmailUser;
    private String SenhaUser;

    public Usuario() {
    }

    public Usuario(String idUser, String nomeUser, String emailUser, String senhaUser) {
        IdUser = idUser;
        NomeUser = nomeUser;
        EmailUser = emailUser;
        SenhaUser = senhaUser;
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

    public String getSenhaUser() {
        return SenhaUser;
    }

    public void setSenhaUser(String senhaUser) {
        SenhaUser = senhaUser;
    }
}
