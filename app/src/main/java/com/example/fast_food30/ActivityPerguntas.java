package com.example.fast_food30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.p2p.WifiP2pManager;
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
    private TextView textViewVida;
    private TextView textViewPontos;
    private SharedPreferences sharedPreferences;
    Integer pontoganho = 15;
    Integer pontoAtual;
    Integer vidaAtual;
    Integer vidaPerdida = -1;
    private String respostaCorretissima;
    private List<Button> botoes = new ArrayList<Button>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        textViewTitulo = findViewById(R.id.text_titulo);
        textViewPontos = findViewById(R.id.text_view_pontos);
        textViewVida = findViewById(R.id.text_view_vida);
        conectarBanco();
       // leituraBanco();
        lerBanco();

    }
            private void conectarBanco(){

            FirebaseApp.initializeApp(ActivityPerguntas.this);
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();

             }



            private void lerBanco(){

            Random random = new Random();
            final int valor = random.nextInt(3);
            //int valor = 0;

            databaseReference.child("Perguntas").child(Integer.toString(valor)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        Pergunta pergunta = dataSnapshot.child(Integer.toString(valor)).getValue(Pergunta.class);

                        respostaCorretissima = pergunta.getResposta_correta();
                        perguntas.add(pergunta);
                        btn1.setText(pergunta.getRespostas().get(0));
                        btn2.setText(pergunta.getRespostas().get(1));
                        btn3.setText(pergunta.getRespostas().get(2));
                        btn4.setText(pergunta.getRespostas().get(3));
                        btn5.setText(pergunta.getRespostas().get(4));
                        textViewTitulo.setText(pergunta.getTitulo_pergunta());

                        botoes.add(btn1);
                        botoes.add(btn2);
                        botoes.add(btn3);
                        botoes.add(btn4);
                        botoes.add(btn5);



                        consultaPontos();
                        consultaVida();
                        for (int i = 0; i < 5; i++) {


                            botoes.get(i).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    Button botao = (Button) view;
                                    String respostaBotao = botao.getText().toString();


                                    if (respostaCorretissima.equals(respostaBotao)) {

                                        sharedPreferences = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
                                        String ID = sharedPreferences.getString("ID","");
                                        pontoAtual += pontoganho;
                                        databaseReference.child("usuario").child(ID).child("pontos").setValue(pontoAtual);
                                        lerBanco();
                                    }

                                    else {

                                        sharedPreferences = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
                                        String ID = sharedPreferences.getString("ID","");
                                        vidaAtual += vidaPerdida;
                                        databaseReference.child("usuario").child(ID).child("vida").setValue(vidaAtual);
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





    public void consultaVida() {
        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                String ID = sharedPreferences.getString("ID", "");
                vidaAtual = Integer.parseInt(dataSnapshot.child(ID).child("vida").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void consultaPontos(){
        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sharedPreferences = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
                String ID = sharedPreferences.getString("ID","");
                pontoAtual = Integer.parseInt(dataSnapshot.child(ID).child("pontos").getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }






    @Override
    public void onBackPressed() {
    }

}
