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

public class CupomAdapter extends ArrayAdapter<Cupom> {

    private Context context;
    private List<Cupom> cupons;

    public CupomAdapter(Context context, ArrayList<Cupom> cupons){

        super(context,0,cupons);

        this.context = context;
        this.cupons = cupons;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listaItem = convertView;

        //Inicializando o layout_list na ListView
        if (listaItem == null){
            listaItem = LayoutInflater.from(context).inflate(R.layout.layout_compracupom,parent,false);
        }

        Cupom cupomAtual = cupons.get(position);



        TextView ID = listaItem.findViewById(R.id.text_id);
        ID.setText("Código : " + cupomAtual.getUuid());
        TextView Nome = listaItem.findViewById(R.id.text_nome);
        Nome.setText( cupomAtual.getNome());

        ImageView cupom = listaItem.findViewById(R.id.image_view);

        if(Nome.getText().equals("Hamburguer")) {
            cupom.setImageResource(R.drawable.hamburguer);
        }
        else if(Nome.getText().equals("Refri")){
            cupom.setImageResource(R.drawable.refri);
        }
        else if(Nome.getText().equals("Combo")){
            cupom.setImageResource(R.drawable.combo1);
        }
        else if(Nome.getText().equals("Combo2")){
            cupom.setImageResource(R.drawable.combo2);
        }
        else if(Nome.getText().equals("Cerveja")){
            cupom.setImageResource(R.drawable.beer1);
        }




        return listaItem;
    }
}
