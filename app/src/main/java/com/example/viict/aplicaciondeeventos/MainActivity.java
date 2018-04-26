package com.example.viict.aplicaciondeeventos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignin;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private TextView textViewForgotPassword;
    private ImageButton  imageButtonGoogle;
    private ImageButton  imageButtonFacebook;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth =FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        buttonSignin = (Button) findViewById(R.id.buttonSignin);
        buttonSignin.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        textViewSignup = (TextView) findViewById(R.id.textViewSignup);
        textViewSignup.setOnClickListener(this);

        textViewForgotPassword = (TextView) findViewById(R.id.textViewForgotPassword);
        textViewForgotPassword.setOnClickListener(this);

        imageButtonFacebook = (ImageButton) findViewById(R.id.imageButtonFacebook);
        imageButtonFacebook.setOnClickListener(this);
        imageButtonGoogle = (ImageButton) findViewById(R.id.imageButtonGoogle);
        imageButtonGoogle.setOnClickListener(this);


    }

    private void signinUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please ingresa tu correo", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Por favor ingresa tu contraseña", Toast.LENGTH_SHORT).show();

            return;


        }
        //if validation are ok
        // we wil first show progresbar

        progressDialog.setMessage("Ingresando...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //user is succesfully loogged in
                    //we will start the profile activity here
                    Toast.makeText(MainActivity.this,"Ingreso satisfactorio",Toast.LENGTH_SHORT);
                }else{
                    Toast.makeText(MainActivity.this,"Error. Intenta de nuevo",Toast.LENGTH_SHORT);
                }
            }
        });

    }
    @Override
    public void onClick(View view) {
        if (view == buttonSignin) {
            signinUser();
        }

        if(view == textViewSignup){
            //singupUser();
        }

        if(view == textViewForgotPassword){
            //forgotPassword();
        }

        Toast.makeText(MainActivity.this,"ImageButtonFacebook is clicked",Toast.LENGTH_SHORT). show();
        Toast.makeText(MainActivity.this,"ImageButtonGoogle is clicked",Toast.LENGTH_SHORT). show();
    }
}
