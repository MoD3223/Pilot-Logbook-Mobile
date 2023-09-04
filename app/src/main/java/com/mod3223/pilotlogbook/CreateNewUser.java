package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateNewUser extends AppCompatActivity {

    EditText LoginText;
    EditText PassText;
    EditText AddressText;
    EditText PhoneText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        LoginText = (EditText) findViewById(R.id.newUserLogin);

        PassText = (EditText) findViewById(R.id.newUserPassword);

        AddressText = (EditText) findViewById(R.id.newUserAddress);

        PhoneText = (EditText) findViewById(R.id.newUserPhone);

        button = (Button) findViewById(R.id.newUserAddUserButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Login = LoginText.getText().toString();
                String Password = PassText.getText().toString();
                String Address = AddressText.getText().toString();
                String Phone = PhoneText.getText().toString();
               try {
                   MainActivity.db.execSQL("INSERT INTO Pilots VALUES (?,?,?,?);", new String[]{Login,Password,Address,Phone});
                   MainActivity.db.close();
               }
               catch (SQLException ex){
                   ex.printStackTrace();
               }
                Intent intent = new Intent(CreateNewUser.this, MainActivity.class);
               startActivity(intent);
            }
        });
    }
}