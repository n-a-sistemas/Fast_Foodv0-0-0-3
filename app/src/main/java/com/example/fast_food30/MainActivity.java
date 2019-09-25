package com.example.fast_food30;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Declarando objetos
    private TextView textViewVida;
    private TextView textViewTempo;
    private TextView textViewPontos;

    //ListView
    private ListView listView;
    private List<Loja> lojas = new ArrayList<>();
    private ArrayAdapter<Loja> arrayAdapterLoja;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewVida = findViewById(R.id.text_view_vida);
        textViewTempo = findViewById(R.id.text_view_tempo);
        textViewPontos = findViewById(R.id.text_view_pontos);
        listView = findViewById(R.id.list_view);

        arrayAdapterLoja = new MeuAdapter(MainActivity.this, (ArrayList<Loja>) lojas);
        listView.setAdapter(arrayAdapterLoja);


    }

    public void jogarAgora(View view){
        Intent intent = new Intent(this, ActivityPerguntas.class);
        startActivity(intent);

    }




}
