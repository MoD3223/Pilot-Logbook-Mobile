package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PasswordLogin extends AppCompatActivity {

    private static int Tries = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        EditText Edit = (EditText) findViewById(R.id.editTextTextPassword);
        Button button = (Button) findViewById(R.id.btnCheckPassword);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                String password = Edit.getText().toString();

                    if (intent.getStringExtra("Password").equals(password)){

                        startActivity(new Intent(PasswordLogin.this, MainTabbedPage.class).putExtra("Login",button.getText().toString()));
                    }
                    else{
                        //Incorrect password
                        if (Tries == 3){
                            System.exit(0);
                        }
                        else{
                            Tries++;
                            TextView textView = (TextView) findViewById(R.id.textView5);
                            textView.setText(String.format("INCORRECT PASSWORD! REMAINING TRIES: %s",3 - Tries));
                        }

                    }

            }
        });


    }
}