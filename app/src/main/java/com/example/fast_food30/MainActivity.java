package com.example.fast_food30;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fast_food30.adapter.MeuAdapter;
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
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Declarando objetos
    private TextView textViewVida;
    private TextView textViewPontos;
    private ImageView imageViewCupom;

    //ListView
    private ListView listView;
    private List<Cupom> cupons = new ArrayList<>();
    private ArrayAdapter<Cupom> arrayAdapterLoja;

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    //Shared Preferences
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conectarBanco();
        eventoBanco();
        leituraBanco2();
        salvarDadoCupom();
        chamaLogin();

        textViewVida = findViewById(R.id.text_view_vida);
        imageViewCupom = findViewById(R.id.image_view_cupom);
        textViewPontos = findViewById(R.id.text_view_pontos);
        listView = findViewById(R.id.list_view_cupom);
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
    private void leituraBanco2() {

        databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                sharedPreferences = getSharedPreferences("LOGIN",Context.MODE_PRIVATE);
                String ID = sharedPreferences.getString("ID","");

                if (ID == "" || ID == null){

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {

                }

                textViewPontos.setText(dataSnapshot.child(ID).child("pontos").getValue().toString());
                textViewVida.setText(dataSnapshot.child(ID).child("vida").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void chamaLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

    }

    public void compraCupom(final Cupom cupom){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja comprar esse cupom?");
        builder.setIcon(R.drawable.hamburguer);

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            //É necessário apagar o cupom e ele ir para a tela de cupom

                databaseReference.child("Cupom")
                            .child(cupons.get(i).getUuid())
                            .removeValue();
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

        Cupom cupom = new Cupom("cupom", "teste", "123");

            databaseReference.child("Cupom")
                    .child(cupom.getUuid())
                    .setValue(cupom);

        Cupom cupom2 = new Cupom("Combo", "200 pontos", "001");

            databaseReference.child("Cupom")
                    .child(cupom2.getUuid())
                    .setValue(cupom2);

        Cupom cupom3 = new Cupom("Refri", "50 pontos", "002");

            databaseReference.child("Cupom")
                    .child(cupom3.getUuid())
                    .setValue(cupom3);

        Cupom cupom4 = new Cupom("Hamburguer", "150 pontos", "003");

            databaseReference.child("Cupom")
                    .child(cupom4.getUuid())
                    .setValue(cupom4);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_sair){
            sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("LOGIN", "false");
            editor.apply();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void jogarAgora(View view){
        Intent intent = new Intent(this, ActivityPerguntas.class);
        startActivity(intent);
    }

    public void meusCupons(View view){
        Intent intent = new Intent(this, ActivityCupom.class);
        startActivity(intent);
    }
}
