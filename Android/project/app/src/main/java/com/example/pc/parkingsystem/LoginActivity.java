package com.example.pc.parkingsystem;

import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

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
                String address = "http://" + inputServer.getText().toString() + ":5000";
                String username = inputUsername.getText().toString();
                String password = inputPassword.getText().toString();

                try {
                    Method callback = LoginActivity.class.getMethod("objectCallback", UserPackage.class);
                    ParkingAPI api = new ParkingAPI(address, LoginActivity.this, callback, null , null);
                    api.getLogin(username, password);
                }
                catch(Exception e){
                    Toast.makeText(LoginActivity.this,"Connection Fails Try agian", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }

            }
        };
        buttonLogin.setOnClickListener(handle);

    }

    public void objectCallback(UserPackage user){
        Intent intent = getIntent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("user", new UserParcel(user));
        bundle.putString("address", "http://" + inputServer.getText().toString() + ":5000");
        intent.putExtras(bundle);
        setResult(1, intent);
        finish();
    }


}
