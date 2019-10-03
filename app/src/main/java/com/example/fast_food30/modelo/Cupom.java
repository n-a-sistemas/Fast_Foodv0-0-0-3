package com.example.fast_food30.modelo;

public class Cupom {

    private String nome;
    private String descricao;
    private String uuid;
    private String cupom;

    public Cupom(){

    }

    public Cupom(String nome, String descricao, String uuid) {

        this.nome = nome;
        this.descricao = descricao;
        this.uuid = uuid;
        this.cupom = cupom;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCupom() {
        return cupom;
    }

    public void setCupom(String cupom) {
        this.cupom = cupom;
    }

    }
