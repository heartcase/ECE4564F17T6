package com.example.pc.parkingsystem;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin;
    TextInputEditText inputServer;
    TextInputEditText inputUsername;
    TextInputEditText inputPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        buttonLogin = findViewById(R.id.button_login);
        inputServer = findViewById(R.id.input_server);
        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);

        View.OnClickListener handle = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO
                String pattern =  "";



            }
        };

        buttonLogin.setOnClickListener(handle);

    }
}
