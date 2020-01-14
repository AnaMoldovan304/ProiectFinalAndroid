package com.example.finalandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private TextView userError;
    private TextView passError;
    private TextView loginError;
    private Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.username = findViewById(R.id.username);
        this.password = findViewById(R.id.password);
        this.userError = findViewById(R.id.userError);
        this.passError = findViewById(R.id.passError);
        this.loginError = findViewById(R.id.loginError);
        this.signIn = findViewById(R.id.loginButton);

        this.username.setText("admin");
        this.password.setText("admin");
    }

    public void signInClick(android.view.View v){
        if(username.getText().toString().length()<1){
            userError.setText("Field cannot be empty");
        }else if(username.getText().toString().length()<3){
            userError.setText("Username too short");
        }else if(username.getText().toString().equals("admin")){
            userError.setText("");
        }else userError.setText("Wrong username");


        if(password.getText().toString().length()<1){
            passError.setText("Field cannot be empty");
        }else if(password.getText().toString().length()<3){
            passError.setText("Password too short");
        }else if(password.getText().toString().equals("admin")){
            passError.setText("");
        }else passError.setText("Wrong password");

        if(password.getText().toString().equals("admin") && username.getText().toString().equals("admin")){
            loginError.setText("Sign in successful");
            loginError.setTextColor(Color.GREEN);
            Intent intent = new Intent(this, OfferActivity.class);
            startActivity(intent);
        }else{
            loginError.setText("Sign in failed");
            loginError.setTextColor(Color.RED);
        }
    }
}
