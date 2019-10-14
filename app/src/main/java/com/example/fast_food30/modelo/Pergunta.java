package com.example.fast_food30.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.*;

public class Pergunta {

    private String uuid;
    private String resposta_correta;
    private String titulo_pergunta;
    private List<String> respostas;

    public Pergunta(){

    }

    public void  embaralhar(){

        Collections.shuffle(respostas);
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getResposta_correta() {
        return resposta_correta;
    }

    public void setResposta_correta(String resposta_correta) {
        this.resposta_correta = resposta_correta;
    }

    public String getTitulo_pergunta() {
        return titulo_pergunta;
    }

    public void setTitulo_pergunta(String titulo_pergunta) {
        this.titulo_pergunta = titulo_pergunta;
    }

    public List<String> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<String> respostas) {
        this.respostas = respostas;
    }

    public Pergunta(String uuid, String resposta_correta, String titulo_pergunta, List<String> respostas) {
        this.uuid = uuid;
        this.resposta_correta = resposta_correta;
        this.titulo_pergunta = titulo_pergunta;
        this.respostas = respostas;
    }
}




