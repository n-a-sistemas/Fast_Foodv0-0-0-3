package com.example.fast_food30;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityCupom extends AppCompatActivity{

    private TextView textViewCupom1;
    private TextView textViewCupom2;
    private TextView textViewCupom3;
    private TextView textViewCupom4;

    private ImageView imageViewAlimento1;
    private ImageView imageViewAlimento2;
    private ImageView imageViewAlimento3;
    private ImageView imageViewAlimento4;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupom);

        textViewCupom1 = findViewById(R.id.text_view_cupom1);
        textViewCupom2 = findViewById(R.id.text_view_cupom2);
        textViewCupom3 = findViewById(R.id.text_view_cupom3);
        textViewCupom4 = findViewById(R.id.text_view_cupom4);

        imageViewAlimento1 = findViewById(R.id.image_view_alimento1);
        imageViewAlimento2 = findViewById(R.id.image_view_alimento2);
        imageViewAlimento3 = findViewById(R.id.image_view_alimento3);
        imageViewAlimento4 = findViewById(R.id.image_view_alimento4);

        conectarBanco();
    }

    private void conectarBanco(){
        FirebaseApp.initializeApp(ActivityCupom.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
