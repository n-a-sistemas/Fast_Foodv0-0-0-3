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

import java.util.ArrayList;
import java.util.List;

public class CupomAdapter extends ArrayAdapter<Cupom> {

    private Context context;
    private List<Cupom> cupons;

    private TextView cupom;

    public CupomAdapter(Context context, ArrayList<Cupom> list){
        super(context, 0, list);
        this.context = context;
        cupons = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listCupom = convertView;

        if(listCupom == null){
            listCupom = LayoutInflater.from(context)
                    .inflate(R.layout.activity_cupom, parent, false);
        }

        ListView lista = listCupom.findViewById(R.id.list_view_cupom);

        return listCupom;
    }
}
