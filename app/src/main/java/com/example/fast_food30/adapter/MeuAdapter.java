package com.example.fast_food30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food30.R;
import com.example.fast_food30.modelo.Cupom;

import java.util.ArrayList;
import java.util.List;

public class MeuAdapter extends ArrayAdapter<Cupom> {

    private Context context;
    private List<Cupom> cupons;

    private TextView textViewNome;
    private TextView textViewDescricao;

    public MeuAdapter(Context context, ArrayList<Cupom> list){
        super(context, 0, list);
        this.context = context;
        cupons = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listLoja = convertView;

        //Inicializando o layout_list_view na ListView
        if(listLoja == null){
            listLoja = LayoutInflater.from(context)
                    .inflate(R.layout.layout_list_view, parent, false);
        }

        Cupom itemLoja = cupons.get(position);

        TextView nome = listLoja.findViewById(R.id.text_view_nome);
        nome.setText(itemLoja.getNome());

        TextView descricao = listLoja.findViewById(R.id.text_view_descricao);
        descricao.setText(itemLoja.getPreco() + " Pontos");

        ImageView cupom = listLoja.findViewById(R.id.image_view_cupom);

        if(nome.getText().equals("Hamburguer")) {
            cupom.setImageResource(R.drawable.hamburguer);
        }
        else if(nome.getText().equals("Refri")){
            cupom.setImageResource(R.drawable.refri);
        }
        else {
            cupom.setImageResource(R.drawable.chef);
        }

        return listLoja;
    }
}
