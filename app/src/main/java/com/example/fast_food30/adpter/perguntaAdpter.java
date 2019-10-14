package com.example.fast_food30.adpter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.fast_food30.ActivityPerguntas;
import com.example.fast_food30.MainActivity;
import com.example.fast_food30.R;
import com.example.fast_food30.modelo.Pergunta;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

/*public class perguntaAdpter extends ArrayAdapter<Pergunta> {

    private Context context;
    private List<Pergunta> tarefas;
    private Button btn1;
    private  Button  btn2;
    private  Button  btn3;
    private  Button  btn4;
    private  Button  btn5;
    private int i;


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

        final Pergunta perguntaAtual = tarefas.get(position);

        perguntaAtual.embaralhar();

        final TextView opcao1= listaItem.findViewById(R.id.btn_1);



        opcao1.setText(perguntaAtual.getRespostas().get(0));


        TextView opcao2= listaItem.findViewById(R.id.btn_2);
        opcao2.setText(perguntaAtual.getRespostas().get(1));


        TextView opcao3= listaItem.findViewById(R.id.btn_3);
        opcao3.setText(perguntaAtual.getRespostas().get(2));

        TextView opcao4= listaItem.findViewById(R.id.btn_4);
        opcao4.setText(perguntaAtual.getRespostas().get(3));


        TextView opcao5= listaItem.findViewById(R.id.btn_5);
        opcao5.setText(perguntaAtual.getRespostas().get(4));

        TextView tituloPergunta = listaItem.findViewById(R.id.text_titulo);
        tituloPergunta.setText(perguntaAtual.getTitulo_pergunta());



        final Button btnopcao1 = listaItem.findViewById(R.id.btn_1);

       btnopcao1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button botao = (Button)view;
                String respostaBotao = botao.getText().toString();



                if( perguntaAtual.getResposta_correta().equals(respostaBotao)){

                    Intent intent = new Intent(context, ActivityPerguntas.class);
                    context.startActivity(intent);

                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        final Button btnopcao2 = listaItem.findViewById(R.id.btn_2);

        btnopcao2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Button botao = (Button)view;
                String respostaBotao = botao .getText().toString();


                if( perguntaAtual.getResposta_correta().equals(respostaBotao)){

                    Intent intent = new Intent(context, ActivityPerguntas.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        final Button btnopcao3 = listaItem.findViewById(R.id.btn_3);

        btnopcao3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Button botao = (Button)view;
                String respostaBotao = botao.getText().toString();



                if( perguntaAtual.getResposta_correta().equals(respostaBotao)){

                    Intent intent = new Intent(context, ActivityPerguntas.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        final Button btnopcao4 = listaItem.findViewById(R.id.btn_4);

        btnopcao4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button botao = (Button)view;
                String respostaBotao = botao.getText().toString();



                if( perguntaAtual.getResposta_correta().equals(respostaBotao)){

                    Intent intent = new Intent(context, ActivityPerguntas.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        final Button btnopcao5 = listaItem.findViewById(R.id.btn_5);

        btnopcao5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button botao = (Button)view;
                String respostaBotao = botao.getText().toString();

                if( perguntaAtual.getResposta_correta().equals(respostaBotao)){

                    Intent intent = new Intent(context, ActivityPerguntas.class);
                    context.startActivity(intent);
                }
                else {
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });

        return listaItem;
    }
}*/
