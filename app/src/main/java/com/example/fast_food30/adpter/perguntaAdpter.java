package com.example.fast_food30.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food30.R;
import com.example.fast_food30.modelo.Pergunta;

import java.util.ArrayList;
import java.util.List;

public class perguntaAdpter extends ArrayAdapter<Pergunta> {

    private Context context;
    private List<Pergunta> tarefas;


    public perguntaAdpter(Context context, ArrayList<Pergunta> perguntas){


        super(context,0,perguntas);

        this.context = context;
        this.tarefas = perguntas;



    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listaItem = convertView;

        if(listaItem == null){
            listaItem = LayoutInflater.from(context).inflate(R.layout.layout_perguntas,parent,false );
        }

        Pergunta perguntaAtual = tarefas.get(position);
        TextView opçao1= listaItem.findViewById(R.id.btn_opcao1);
        opçao1.setText(perguntaAtual.getOpção_1());


        TextView opçao2= listaItem.findViewById(R.id.btn_opcao2);
        opçao2.setText(perguntaAtual.getOpção_2());


        TextView opçao3= listaItem.findViewById(R.id.btn_opcao3);
        opçao3.setText(perguntaAtual.getOpção_3());

        TextView opçao4= listaItem.findViewById(R.id.btn_opcao4);
        opçao4.setText(perguntaAtual.getOpção_4());


        TextView opçaocorreta= listaItem.findViewById(R.id.btn_opcao5);
        opçaocorreta.setText(perguntaAtual.getOpção_correta());

        TextView tituloPergunta = listaItem.findViewById(R.id.text_titulo);
        tituloPergunta.setText(perguntaAtual.getTitulo_pergunta());















        return listaItem;
    }
}
