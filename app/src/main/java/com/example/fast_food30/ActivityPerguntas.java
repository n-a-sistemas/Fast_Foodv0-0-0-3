package com.example.fast_food30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fast_food30.adpter.perguntaAdpter;
import com.example.fast_food30.modelo.Pergunta;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.*;


public class ActivityPerguntas extends AppCompatActivity {


    private ListView listView;
    private List<Pergunta> perguntas = new ArrayList<Pergunta>();
    private ArrayAdapter<Pergunta> arrayAdapterPergunta ;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;



    //BOTÕES


    private  Button  btn1;
    private  Button  btn2;
    private  Button  btn3;
    private  Button  btn4;
    private  Button  btn5;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        listView = findViewById(R.id.list_view_perguntas);


        //Botões





        conectarBanco();
        leituraBanco();
    }

    private void  conectarBanco() {

        FirebaseApp.initializeApp(ActivityPerguntas.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    private void leituraBanco(){


        Random random = new Random();
        int valor = random.nextInt(6) + 1;


        databaseReference.child(Integer.toString(valor)).addValueEventListener(new ValueEventListener() {




            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                perguntas.clear();
                for (DataSnapshot snapshot: dataSnapshot.getChildren()){

                    Pergunta pergunta = snapshot.getValue(Pergunta.class);
                    perguntas.add(pergunta);







                }



                arrayAdapterPergunta = new perguntaAdpter(ActivityPerguntas.this,
                        (ArrayList<Pergunta>)perguntas);






                listView.setAdapter(arrayAdapterPergunta);














            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });










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
