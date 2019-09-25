package com.example.fast_food30.modelo;

public class Pergunta {



    private String uuid;
    private String opção_1;
    private String opção_2;
    private String opção_3;
    private String opção_4;
    private String opção_correta;
    private String titulo_pergunta;



    public Pergunta(){

    }



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOpção_1() {
        return opção_1;
    }

    public void setOpção_1(String opção_1) {
        this.opção_1 = opção_1;
    }

    public String getOpção_2() {
        return opção_2;
    }

    public void setOpção_2(String opção_2) {
        this.opção_2 = opção_2;
    }

    public String getOpção_3() {
        return opção_3;
    }

    public void setOpção_3(String opção_3) {
        this.opção_3 = opção_3;
    }

    public String getOpção_4() {
        return opção_4;
    }

    public void setOpção_4(String opção_4) {
        this.opção_4 = opção_4;
    }

    public String getOpção_correta() {
        return opção_correta;
    }

    public void setOpção_correta(String opção_correta) {
        this.opção_correta = opção_correta;
    }

    public String getTitulo_pergunta() {
        return titulo_pergunta;
    }

    public void setTitulo_pergunta(String titulo_pergunta) {
        this.titulo_pergunta = titulo_pergunta;
    }

    public Pergunta(String uuid, String opção_1, String opção_2, String opção_3, String opção_4, String opção_correta, String titulo_pergunta) {
        this.uuid = uuid;
        this.opção_1 = opção_1;
        this.opção_2 = opção_2;
        this.opção_3 = opção_3;
        this.opção_4 = opção_4;
        this.opção_correta = opção_correta;
        this.titulo_pergunta = titulo_pergunta;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "uuid='" + uuid + '\'' +
                ", opção_1='" + opção_1 + '\'' +
                ", opção_2='" + opção_2 + '\'' +
                ", opção_3='" + opção_3 + '\'' +
                ", opção_4='" + opção_4 + '\'' +
                ", opção_correta='" + opção_correta + '\'' +
                ", titulo_pergunta='" + titulo_pergunta + '\'' +
                '}';
    }







}




