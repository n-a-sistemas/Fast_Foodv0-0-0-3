package com.example.fast_food30.modelo;

public class Usuario {
    private String uid;
    private String email;
    private boolean valido;

    public Usuario(String uid, String email, boolean valido) {
        this.uid = uid;
        this.email = email;
        this.valido = valido;
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

}
