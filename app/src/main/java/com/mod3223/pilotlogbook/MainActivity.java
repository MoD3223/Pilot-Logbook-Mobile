package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    public boolean IsLocal;

    Switch SaveDBChoice;

    SharedPreferences prefs;
    SharedPreferences.Editor prefsEditor;
    

    public void btnLocalDB_Clicked(View v) {
        if (SaveDBChoice.isChecked()){
            prefsEditor.putString("DBChoice","LocalDB");
            prefsEditor.apply();
        }
        else{
            prefsEditor.remove("DBChoice");
        }
    }

    public void btnExternalDB_Clicked(View v){
        if (SaveDBChoice.isChecked()){
            prefsEditor.putString("DBChoice","ExternalDB");
            prefsEditor.apply();
        }
        else{
            prefsEditor.remove("DBChoice");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.mod3223.pilotlogbook",Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
        SaveDBChoice = findViewById(R.id.switchSaveDBChoice);

    }


}