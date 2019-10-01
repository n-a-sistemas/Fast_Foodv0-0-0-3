package com.example.fast_food30.modelo;

public class Usuario {
    private String uid;
    private String email;
    private boolean valido;
    private Integer vida;
    private Integer pontos;



    public Usuario(){

    }


    public Usuario(String uid, String email, boolean valido, Integer vida, Integer pontos) {
        this.uid = uid;
        this.email = email;
        this.valido = valido;
        this.vida = vida;
        this.pontos = pontos;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }


    @Override
    public String toString() {
        return "Usuario{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", valido=" + valido +
                ", vida=" + vida +
                ", pontos=" + pontos +
                '}';
    }
}
