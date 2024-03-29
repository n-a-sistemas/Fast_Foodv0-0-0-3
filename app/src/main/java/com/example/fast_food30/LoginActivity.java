package com.example.fast_food30;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.fast_food30.modelo.Cupom;
import com.example.fast_food30.modelo.Pergunta;
import com.example.fast_food30.modelo.Usuario;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private Usuario usuario = new Usuario();

    //Banco
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private List<Cupom> cupons = new ArrayList<Cupom>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_login);

        sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
        String resultado = sharedPreferences.getString("LOGIN", "");

        sharedPreferences = getSharedPreferences("ID" , Context.MODE_PRIVATE);

        conectarBancoUsuario();

        if (!Boolean.parseBoolean(resultado)){
            criarLogin();
        }else{
            finish();
        }
    }

    private void criarLogin(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .setIsSmartLockEnabled(false)
                        .setTheme(R.style.Login)
                        .build(), 321);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 321){

            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK){

                if (response.isNewUser()){
                    cupons.add(new Cupom("Refri","50",UUID.randomUUID().toString()));
                    this.usuario.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    this.usuario.setEmail(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                    this.usuario.setVida(3);
                    this.usuario.setPontos(0);
                    this.usuario.setCupons(cupons);
                    this.usuario.setValido(false);

                    Date dataHoraAtual = Calendar.getInstance().getTime();
                    sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                    String ID = sharedPreferences.getString("ID", "");
                    databaseReference.child("usuario").child(ID).child("ultimavisita").setValue(dataHoraAtual);
                    databaseReference
                            .child("usuario")
                            .child(usuario.getUid())
                            .setValue(usuario);
                }

                sharedPreferences = getSharedPreferences("LOGIN", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LOGIN", "true");
                editor.putString("ID",usuario.getUid());
                editor.apply();
                finish();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
            else {
                if(response == null){
                    finish();
                }
            }
        }
    }

    public void conectarBancoUsuario(){

        FirebaseApp.initializeApp(LoginActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
}
