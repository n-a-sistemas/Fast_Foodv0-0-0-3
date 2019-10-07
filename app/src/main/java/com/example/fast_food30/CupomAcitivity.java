package com.example.fast_food30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fast_food30.adapter.CupomAdapter;
import com.example.fast_food30.modelo.Cupom;
import com.example.fast_food30.modelo.Usuario;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CupomAcitivity extends AppCompatActivity {



    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Cupom> tarefas = new ArrayList<Cupom>();
    private ArrayAdapter<Cupom> ArrayAdpterUsuario;
    private ListView listView;
    private SharedPreferences sharedPreferences;
    private Cupom jorge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom_acitivity);
        listView = findViewById(R.id.list_view_original);
        conectarBanco();
        eventoBanco();
    }


    private void conectarBanco(){

        FirebaseApp.initializeApp(CupomAcitivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }

    private void eventoBanco(){

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String ID = sharedPreferences.getString("ID","");






        databaseReference.child("usuario").child(ID).child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tarefas.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

                    jorge = snapshot.getValue(Cupom.class);
                    tarefas.add(jorge);
                }

                ArrayAdpterUsuario = new CupomAdapter(CupomAcitivity.this,
                        (ArrayList<Cupom>) tarefas );

                listView.setAdapter(ArrayAdpterUsuario);







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Leitura do Banco

    }








}
