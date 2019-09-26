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
import android.widget.ListView;
import android.widget.TextView;

import com.example.fast_food30.adapter.MeuAdapter;
import com.example.fast_food30.modelo.Cupom;
import com.example.fast_food30.modelo.Loja;
import com.example.fast_food30.modelo.TelaLogin;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Declarando objetos
    private TextView textViewVida;
    private TextView textViewTempo;
    private TextView textViewPontos;

    //ListView
    private ListView listView;
    private List<Cupom> cupons = new ArrayList<>();
    private ArrayAdapter<Cupom> arrayAdapterLoja;

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chamaLogin();
        conectarBanco();
        eventoBanco();

        textViewVida = findViewById(R.id.text_view_vida);
        textViewTempo = findViewById(R.id.text_view_tempo);
        textViewPontos = findViewById(R.id.text_view_pontos);
        listView = findViewById(R.id.list_view);

        salvarDadoCupom();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoBanco(){
        databaseReference.child("Cupom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cupom cupom = snapshot.getValue(Cupom.class);
                    cupons.add(cupom);
                }
                arrayAdapterLoja = new MeuAdapter(MainActivity.this, (
                        ArrayList<Cupom>) cupons);
                listView.setAdapter(arrayAdapterLoja);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        compraCupom(cupons.get(i));
                        return;
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void compraCupom(final Cupom cupom){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja comprar esse cupom?");
        builder.setIcon(R.drawable.hamburguer1);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                    databaseReference.child("cupom")
                        .child(cupom.getUuid())
                        .setValue(cupom);
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void salvarDadoCupom(){

        Cupom cupom = new Cupom("cupim", "teste", "123");

        databaseReference.child("Cupom")
                .child(cupom.getUuid())
                .setValue(cupom);
    }

    public void jogarAgora(View view){
        Intent intent = new Intent(this, ActivityPerguntas.class);
        startActivity(intent);
    }

    public void chamaLogin(){
        Intent intent = new Intent(this, TelaLogin.class);
        startActivity(intent);

    }
}
