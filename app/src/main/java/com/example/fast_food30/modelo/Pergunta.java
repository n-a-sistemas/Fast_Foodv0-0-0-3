package com.example.fast_food30.modelo;

public class Pergunta {

    private String titulo_pergunta;
    private String opção_1;
    private String opção_2;
    private String opção_3;
    private String opção_4;
    private String resposta_certa;


    public String getTitulo_pergunta() {
        return titulo_pergunta;
    }

    public void setTitulo_pergunta(String titulo_pergunta) {
        this.titulo_pergunta = titulo_pergunta;
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

    public String getResposta_certa() {
        return resposta_certa;
    }

    public void setResposta_certa(String resposta_certa) {
        this.resposta_certa = resposta_certa;
    }


    public Pergunta(String titulo_pergunta, String opção_1, String opção_2, String opção_3, String opção_4, String resposta_certa) {
        this.titulo_pergunta = titulo_pergunta;
        this.opção_1 = opção_1;
        this.opção_2 = opção_2;
        this.opção_3 = opção_3;
        this.opção_4 = opção_4;
        this.resposta_certa = resposta_certa;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "titulo_pergunta='" + titulo_pergunta + '\'' +
                ", opção_1='" + opção_1 + '\'' +
                ", opção_2='" + opção_2 + '\'' +
                ", opção_3='" + opção_3 + '\'' +
                ", opção_4='" + opção_4 + '\'' +
                ", resposta_certa='" + resposta_certa + '\'' +
                '}';
    }
}
