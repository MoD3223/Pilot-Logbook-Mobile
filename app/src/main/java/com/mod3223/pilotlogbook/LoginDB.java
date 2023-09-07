package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class LoginDB extends AppCompatActivity {

    Boolean boolDelete = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_db);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,10,0,0);
        LinearLayout btnLayout = findViewById(R.id.LoginBtnContainer);
        Cursor cursor = MainActivity.db.rawQuery("SELECT * FROM Pilots",null);
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("Login"));
            Button button = new Button(this);
            button.setText(name);

            button.setLayoutParams(layoutParams);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view instanceof Button){
                        Button button = (Button) view;
                        Drawable background = button.getBackground();
                        int backgroundColor = 0;
                        if (background instanceof ColorDrawable){
                            ColorDrawable color = (ColorDrawable) background;
                            backgroundColor = color.getColor();
                        }

                        if (backgroundColor == Color.RED){
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginDB.this);
                            builder.setTitle("Really delete?").setMessage("Are you sure you want to delete this user?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    String login = button.getText().toString();
                                    int RowsDeleted = MainActivity.db.delete("Pilots","Login = ?", new String[]{login});
                                    if (RowsDeleted == 0){
                                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getBaseContext());
                                        builder1.setTitle("ERROR").setMessage("Couldn't delete the specified profile");
                                        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                dialogInterface.dismiss();
                                            }
                                        });
                                        builder1.create().show();
                                    }
                                    recreate();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });

                                builder.create().show();

                        }
                        else{
                            String password = null;
                            try {
                                Cursor haslo = MainActivity.db.rawQuery("SELECT Password from Pilots where Login = ?", new String[]{button.getText().toString()});
                                haslo.moveToFirst();
                                password = haslo.getString(haslo.getColumnIndex("Password"));
                                haslo.close();
                            }
                            catch (Exception ex){

                            }
                            if (!password.equals("")){
                                startActivity(new Intent(LoginDB.this,PasswordLogin.class).putExtra("Login", button.getText().toString()).putExtra("Password",password));
                            }
                            else{
                                startActivity(new Intent(LoginDB.this, MainTabbedPage.class).putExtra("Login",button.getText().toString()));
                            }
                        }



                    }
                }
            });

        btnLayout.addView(button);

        }
        cursor.close();

        Button create = new Button(this);
        create.setText("Create new account");
        create.setLayoutParams(layoutParams);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginDB.this, CreateNewUser.class);
                startActivity(intent);

            }
        });
        btnLayout.addView(create);

        Button delete = new Button(this);
        delete.setText("Delete existing account");
        delete.setLayoutParams(layoutParams);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (boolDelete){
                    for (int i = 0; i < btnLayout.getChildCount() - 2; i++) {
                        View childView = btnLayout.getChildAt(i);
                        if (childView instanceof Button){
                            Button button = (Button) childView;
                            button.setBackgroundColor(Color.LTGRAY);
                        }
                    }
                    boolDelete = !boolDelete;
                }
                else{
                    for (int i = 0; i < btnLayout.getChildCount() - 2; i++) {
                        View childView = btnLayout.getChildAt(i);
                        if (childView instanceof Button){
                            Button button = (Button) childView;
                            button.setBackgroundColor(Color.RED);
                        }
                    }
                    boolDelete = !boolDelete;
                }
            }
        });
        btnLayout.addView(delete);

    }
}