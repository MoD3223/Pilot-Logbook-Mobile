package com.mod3223.pilotlogbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    MyDatabase myDatabase ;
    SQLiteDatabase db;
    public static int LogbookID;
    public static int RatingID;
    public static int SynthID;
    public static int MedicalID;
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














    //TODO: Add exceptions
    public void Testing(){
        //Create
        try {
            ContentValues values = new ContentValues();
            values.put("Login","Test");
            values.put("Password","Test");
            long newRowID = db.insert("Pilots",null,values);
        }
        catch (Exception ex){

        }
        //Read
        try {
            Cursor cursor = db.query("Pilots", new String[]{"Password"},"Login = ?", new String[]{"Test"},null,null,null);
            if (cursor.moveToFirst()){
                //Found item, all is good
            }
            else{
                //TODO: Throw exception
            }
        }
        catch (Exception ex){

        }
        //Update
        try {
            ContentValues values = new ContentValues();
            values.put("Password","Test2");
            int rowsAffected = db.update("Pilots",values,"Password = ?",new String[]{"Test"});
            if (rowsAffected == 0){
                //TODO: Throw error
            }
        }
        catch (Exception ex){

        }
        //Delete
        try {
            int rowsDeleted = db.delete("Pilots","Login = ?",new String[]{"Test"});
            if (rowsDeleted == 0){
                //TODO: Throw Error
            }
        }
        catch (Exception ex){

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prefs = getSharedPreferences("com.mod3223.pilotlogbook",Context.MODE_PRIVATE);
        prefsEditor = prefs.edit();
        SaveDBChoice = findViewById(R.id.switchSaveDBChoice);
        myDatabase = new MyDatabase(getApplicationContext());
        db = myDatabase.getWritableDatabase();
        Testing();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        db.close();
    }


}