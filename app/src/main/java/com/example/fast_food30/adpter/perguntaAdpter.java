package com.example.fast_food30.adpter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food30.ActivityPerguntas;
import com.example.fast_food30.MainActivity;
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

        perguntaAtual.embaralhar();

        TextView opcao1= listaItem.findViewById(R.id.btn_opcao1);



        opcao1.setText(perguntaAtual.getRespostas().get(0));


        TextView opcao2= listaItem.findViewById(R.id.btn_opcao2);
        opcao2.setText(perguntaAtual.getRespostas().get(1));


        TextView opcao3= listaItem.findViewById(R.id.btn_opcao3);
        opcao3.setText(perguntaAtual.getRespostas().get(2));

        TextView opcao4= listaItem.findViewById(R.id.btn_opcao4);
        opcao4.setText(perguntaAtual.getRespostas().get(3));


        TextView opcao5= listaItem.findViewById(R.id.btn_opcao5);
        opcao5.setText(perguntaAtual.getRespostas().get(4));

        TextView tituloPergunta = listaItem.findViewById(R.id.text_titulo);
        tituloPergunta.setText(perguntaAtual.getTitulo_pergunta());







        return listaItem;



    }







}
