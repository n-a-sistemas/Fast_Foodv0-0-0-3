package com.example.fast_food30.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fast_food30.R;
import com.example.fast_food30.modelo.Loja;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MeuAdapter extends ArrayAdapter<Loja> {

    private Context context;
    private List<Loja> lojas;

    public MeuAdapter(Context context, ArrayList<Loja> list){
        super(context, 0, list);
        this.context = context;
        lojas = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listLoja = convertView;

        if(listLoja == null){
            listLoja = LayoutInflater.from(context)
                    .inflate(R.layout.layout_list_view, parent, false);
        }

        Loja itemLoja = lojas.get(position);

        return listLoja;
    }
}
