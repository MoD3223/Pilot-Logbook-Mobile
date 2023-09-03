package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
                            //TODO: Prompt to delete the user
                        }
                        else{
                            String password = cursor.getString(cursor.getColumnIndex("Password"));
                            if (password != null){
                                //TODO: Add navigation to password page
                            }
                            else{
                                //TODO: Add navigation to tabbed page
//                    Intent intent = new Intent(this,CLASS);
//                    intent.putExtra("Login",button.getText());
//                    startActivity(intent);
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
                //TODO: Navigate to create new user page
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
                            button.setBackgroundColor(Color.TRANSPARENT);
                        }
                    }
                }
                else{
                    for (int i = 0; i < btnLayout.getChildCount() - 2; i++) {
                        View childView = btnLayout.getChildAt(i);
                        if (childView instanceof Button){
                            Button button = (Button) childView;
                            button.setBackgroundColor(Color.RED);
                        }
                    }
                }
            }
        });
        btnLayout.addView(delete);

    }
}