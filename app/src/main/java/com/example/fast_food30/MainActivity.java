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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    //Declarando objetos
    private TextView textViewVida;
    private TextView textViewPontos;
    private ImageView imageViewCupom;
    Integer vidaAtual;
    Integer pontoAtual;
    Integer cupomPreco;
    String ID;
    Date ultimavisita;
    private List<String> Aleatorio = new ArrayList<String>();


    //ListView
    private ListView listView;
    private List<Cupom> cupons = new ArrayList<Cupom>();
    private List<Cupom> cuponsComprados = new ArrayList<Cupom>();

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
        textViewVida = findViewById(R.id.text_view_vida);
        imageViewCupom = findViewById(R.id.image_view_cupom);
        textViewPontos = findViewById(R.id.text_view_pontos);
        listView = findViewById(R.id.list_view_cupom);
        conectarBanco();

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN", "");




        if (!Boolean.parseBoolean(resultado)) {
            chamaLogin();

        }
        else{
            leituraBanco2();
            consultaPontos();
            consultaVida();
            cupomPesquisaCompra();
            salvarDadoCupom();
            consultaUltimaVisita();
            consultaCupomUsuario();
        }




    }




    private void conectarBanco(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void cupomPesquisaCompra(){
        databaseReference.child("Cupom").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

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


                       ID = cupons.get(i).getUuid();

                       cuponsComprados.add(cupons.get(i));
                        compraCupom(cuponsComprados);

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


              textViewVida.setText(dataSnapshot.child(ID).child("vida").getValue().toString());
              textViewPontos.setText(dataSnapshot.child(ID).child("pontos").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





    public void chamaLogin(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);


    }

    public void compraCupom(final List<Cupom> cupoms){


        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.app_name);
        builder.setMessage("Você deseja comprar esse cupom?");
        builder.setIcon(R.drawable.hamburguer);



            consultaCupom();
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            //É necessário apagar o cupom e ele ir para a tela de cupom


                if(pontoAtual >= cupomPreco) {
                    pontoAtual -= cupomPreco;
                    sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                    String ID = sharedPreferences.getString("ID", "");
                    databaseReference.child("usuario").child(ID).child("pontos").setValue(pontoAtual);





                        for (int j = 0; j < cupoms.size(); j++) {
                        //    if (cupoms.get(j).getToken().equals("falso")) {


                            UUID uuid = UUID.randomUUID();
                            String myRandom = uuid.toString();

                            cupoms.get(j).setToken(myRandom.substring(0,6));
                          //  }

                            //databaseReference.child("usuario").child(ID).child("cupons").child(cupoms.get(j).getToken()).setValue(cupoms.get(j));
                        }
                        databaseReference.child("usuario").child(ID).child("cupons").setValue(cupoms);
                        //databaseReference.child("usuario").child(ID).child("cupons").push().setValue(cupoms);
                    } else {


                    }

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

    public void salvarDadoCupom() {


      Cupom cupom1 = new Cupom("Refri","50","1");
      databaseReference.child("Cupom").child(cupom1.getUuid()).setValue(cupom1);

        Cupom cupom2 = new Cupom("Hamburguer","150","2");
        databaseReference.child("Cupom").child(cupom2.getUuid()).setValue(cupom2);



        Cupom cupom3 = new Cupom("Combo","300","3");
        databaseReference.child("Cupom").child(cupom3.getUuid()).setValue(cupom3);



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





        if(vidaAtual == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.app_name);
            builder.setMessage("Suas Vidas acabaram , tente novamente amanhã");
            builder.setIcon(R.drawable.hamburguer);
            AlertDialog alert = builder.create();
            alert.show();
        }
        else{
            Intent intent = new Intent(this, ActivityPerguntas.class);
            startActivity(intent);
        }
    }

    public void meuscupons(View view){
       Intent intent = new Intent(this, CupomAcitivity.class);

       startActivity(intent);
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
    public void consultaCupom( ){

        databaseReference.child("Cupom").addValueEventListener(new ValueEventListener() {
           @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


              cupomPreco = Integer.parseInt(dataSnapshot.child(ID).child("preco").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });





    }

    public void consultaUltimaVisita(){





                databaseReference.child("usuario").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                        String ID = sharedPreferences.getString("ID", "");
                        ultimavisita = dataSnapshot.child(ID).child("ultimavisita").getValue(Date.class);
                        //comparaVida();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }



    public void comparaVida(){
        Date dataHoraAtual = Calendar.getInstance().getTime();

        long diff = ((dataHoraAtual.getTime() - ultimavisita.getTime() ) /1000);
        long igual = 10;


        if (diff >= igual ) {
            sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
            String ID = sharedPreferences.getString("ID", "");
            databaseReference.child("usuario").child(ID).child("ultimavisita").setValue(dataHoraAtual);
            databaseReference.child("usuario").child(ID).child("vida").setValue(vidaAtual + 1);
        }



    }
    public void consultaCupomUsuario(){

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String ID = sharedPreferences.getString("ID", "");
        databaseReference.child("usuario").child(ID).child("cupons").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cuponsComprados.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Cupom cupom = snapshot.getValue(Cupom.class);
                    cuponsComprados.add(cupom);
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }








}
