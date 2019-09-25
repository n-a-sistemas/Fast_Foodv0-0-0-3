package com.example.fast_food30.modelo;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fast_food30.R;

public class Loja extends AppCompatActivity {

    private TextView textViewTitulo;
    private TextView textViewDescricao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_list_view);

        textViewTitulo = findViewById(R.id.text_view_nome);
        textViewDescricao = findViewById(R.id.text_view_descricao);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("TITULO");
        String descricao = intent.getStringExtra("DESCRICAO");

        textViewTitulo.setText(titulo);
        textViewDescricao.setText(descricao);
    }
}
