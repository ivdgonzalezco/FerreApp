package com.ferreapp.ferreapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends Activity {

    private Button btnRegistrar;
    private EditText nombreUsuario;
    private EditText correo;
    private EditText contrasena;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener listener;
    private ProgressDialog pbProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nombreUsuario = (EditText) findViewById(R.id.usuarioId);
        correo = (EditText) findViewById(R.id.correoId);
        contrasena = (EditText) findViewById(R.id.contrasenaId);
        btnRegistrar = (Button) findViewById(R.id.registroId);


        pbProgreso = new ProgressDialog(this);
        pbProgreso.setIndeterminate(true);

        mAuth = FirebaseAuth.getInstance();
//        listener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                //conocer si el usuario está logeado
//                FirebaseUser user = mAuth.getCurrentUser();
//                if (user !=null){
//                    abrirActividadInicio();
//                }
//            }
//        };

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registrarUsuario();
            }
        });
    }

    //!!!!!!!!!CAMBIAR EL SIGUIENTE CODIGO DE ACUERDO AL NOMBRE DE LA ACTIVIDAD DE IVAN!!!!!!!!!!!!!!
    private void abrirActividadInicio() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        finish();
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void registrarUsuario() {

        final String usuario = nombreUsuario.getText().toString();
        final String email = correo.getText().toString();
        final String password = contrasena.getText().toString();

        if (!usuario.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            pbProgreso.setMessage("Registrando usuario");
            pbProgreso.show();
            Log.d("salida: ", email +" "+ password);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
//                        Log.d("salida", "createUserWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(getApplicationContext(), "Registro exitoso", Toast.LENGTH_LONG).show();
                    }else{
//                        Log.w("salida", "createUserWithEmail:failure", task.getException());

                        Toast.makeText(getApplicationContext(), "ERROR AL REGISTRAR", Toast.LENGTH_LONG).show();
                    }
                    pbProgreso.dismiss();
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Diligenciar todos los campos", Toast.LENGTH_LONG).show();
            pbProgreso.dismiss();
        }

    }

    public void onRadioButtonClicked(View view) {
    }
}
