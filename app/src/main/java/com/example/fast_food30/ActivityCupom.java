package com.example.fast_food30;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fast_food30.adapter.CupomAdapter;
import com.example.fast_food30.adapter.MeuAdapter;
import com.example.fast_food30.modelo.Cupom;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityCupom extends AppCompatActivity{

    private ListView listView;
    private List<Cupom> cupons = new ArrayList<>();
    private ArrayAdapter<Cupom> cupomArrayAdapter;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom);

        conectarBanco();
        eventoBanco();

        listView = findViewById(R.id.list_view_cupom);
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(ActivityCupom.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void eventoBanco() {



        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String ID = sharedPreferences.getString("ID", "");
        databaseReference.child("usuario").child(ID).child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Cupom cupom = snapshot.getValue(Cupom.class);
                    cupons.add(cupom);
                }


                cupomArrayAdapter = new CupomAdapter(ActivityCupom.this,
                        (ArrayList<Cupom>) cupons);


                listView.setAdapter(cupomArrayAdapter);

                return;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



}