package com.example.fast_food30;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fast_food30.modelo.Cupom;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ActivityCupom extends AppCompatActivity{
    private List<Cupom> list = new ArrayList();
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom);
        carregarListView();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void carregarListView(){
        listView = (ListView) findViewById(R.id.list_view_cupom);

        final ArrayAdapter<Cupom> adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, list);
        adaptador.clear();
        listView.setAdapter(adaptador);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cupons");

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                list.add(dataSnapshot.getValue(Cupom.class));
                adaptador.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                list.remove(dataSnapshot.getValue(Cupom.class));
                adaptador.notifyDataSetChanged();
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
