package com.example.fast_food30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food30.R;
import com.example.fast_food30.modelo.Cupom;
import com.example.fast_food30.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class CupomAdapter extends ArrayAdapter<Usuario> {

    private Context context;
    private List<Usuario> tarefas;

    public CupomAdapter(Context context, ArrayList<Usuario> tarefas){

        super(context,0,tarefas);

        this.context = context;
        this.tarefas = tarefas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listaItem = convertView;

        //Inicializando o layout_list na ListView
        if (listaItem == null){
            listaItem = LayoutInflater.from(context).inflate(R.layout.layout_compracupom,parent,false);
        }

        Usuario tarefaAtual = tarefas.get(position);

        TextView nomeTarefa = listaItem.findViewById(R.id.text_nome);
        nomeTarefa.setText(tarefaAtual.getCupons().get(1).toString());




        return listaItem;
    }
}
