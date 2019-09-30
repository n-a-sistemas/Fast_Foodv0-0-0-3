package com.example.fast_food30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fast_food30.modelo.Pergunta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class ActivityPerguntas extends AppCompatActivity {
    private ListView listView;
    private List<Pergunta> perguntas = new ArrayList<Pergunta>();
    private List<Integer> inteiros = new ArrayList<Integer>();
    private ArrayAdapter<Pergunta> arrayAdapterPergunta ;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private  Button  btn1;
    private  Button  btn2;
    private  Button  btn3;
    private  Button  btn4;
    private  Button  btn5;
    private TextView textViewTitulo;



    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        textViewTitulo = findViewById(R.id.text_titulo);
        conectarBanco();
        leituraBanco();
    }*/

    private void  conectarBanco() {

        FirebaseApp.initializeApp(ActivityPerguntas.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    public void leituraBanco(){

        Random random = new Random();
        int valor = random.nextInt(6) + 1;
        if(!inteiros.contains(valor)){
            inteiros.add(valor);


            databaseReference.child(Integer.toString(valor)).addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    perguntas.clear();
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                        final Pergunta pergunta = snapshot.getValue(Pergunta.class);
                        pergunta.embaralhar();

                        perguntas.add(pergunta);
                        btn1.setText(pergunta.getRespostas().get(0));
                        btn2.setText(pergunta.getRespostas().get(1));
                        btn3.setText(pergunta.getRespostas().get(2));
                        btn4.setText(pergunta.getRespostas().get(3));
                        btn5.setText(pergunta.getRespostas().get(4));
                        textViewTitulo.setText(pergunta.getTitulo_pergunta());

                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Button botao = (Button)view;
                                String respostaBotao = botao.getText().toString();

                                if( pergunta.getResposta_correta().equals(respostaBotao)){

                                    leituraBanco();
                                }
                                else {
                                    finish();


                                }

                            }
                        });
                        btn2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button botao = (Button)view;
                                String respostaBotao = botao.getText().toString();

                                if( pergunta.getResposta_correta().equals(respostaBotao)){
                                    leituraBanco();


                                }
                                else {
                                    finish();

                                }

                            }
                        });
                        btn3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button botao = (Button)view;
                                String respostaBotao = botao.getText().toString();

                                if( pergunta.getResposta_correta().equals(respostaBotao)){

                                    leituraBanco();

                                }
                                else {
                                    finish();

                                }

                            }
                        });
                        btn4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button botao = (Button)view;
                                String respostaBotao = botao.getText().toString();

                                if( pergunta.getResposta_correta().equals(respostaBotao)){
                                    leituraBanco();


                                }
                                else {
                                    finish();

                                }

                            }
                        });
                        btn5.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Button botao = (Button)view;
                                String respostaBotao = botao.getText().toString();

                                if( pergunta.getResposta_correta().equals(respostaBotao)){

                                    leituraBanco();

                                }
                                else {
                                    finish();

                                }

                            }
                        });


                    }








                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        else {
            leituraBanco();

        }
    }

    public void salvarDado(){
        List<String> lista = new ArrayList<String>();
        lista.add("Kung-fu Jão");
        lista.add("Tochiro");
        lista.add("Kawasaki");
        lista.add("Miagy");
        lista.add("Lee");
        Pergunta tarefa = new Pergunta("6","Kung-fu Jão","Qual o nome do chines da sala ? ",lista);
        databaseReference.child("6").child(tarefa.getUuid()).setValue(tarefa);
    }
}
