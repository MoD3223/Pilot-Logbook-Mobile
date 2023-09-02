package com.mod3223.pilotlogbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    private static final String DB_CREATE_PILOTS = "CREATE TABLE Pilots (\n" +
            "    Login TEXT NOT NULL CONSTRAINT PK_Pilots PRIMARY KEY,\n" +
            "    PhoneNumber INTEGER,\n" +
            "    Address TEXT,\n" +
            "    Password TEXT\n" +
            ")";

    private static final String DB_CREATE_LOGBOOKS ="CREATE TABLE \"Logbooks\" (\n" +
            "    \"LogbookID\" INTEGER NOT NULL CONSTRAINT \"PK_Logbooks\" PRIMARY KEY AUTOINCREMENT,\n" +
            "    \"pilotLogin\" TEXT,\n" +
            "    \"NotesAndEndorsements\" TEXT,\n" +
            "    \"Date\" TEXT NOT NULL,\n" +
            "    \"AircraftType\" TEXT,\n" +
            "    \"AircraftIdent\" TEXT,\n" +
            "    \"RouteFrom\" TEXT,\n" +
            "    \"DepartureTime\" TEXT NOT NULL,\n" +
            "    \"RouteTo\" TEXT,\n" +
            "    \"ArrivalTime\" TEXT NOT NULL,\n" +
            "    \"FlightDuration\" TEXT NOT NULL,\n" +
            "    \"StickTime\" TEXT,\n" +
            "    \"PilotInCommand\" TEXT,\n" +
            "    \"SecondInCommand\" TEXT,\n" +
            "    \"CrossCountry\" INTEGER NOT NULL,\n" +
            "    \"EngineType\" INTEGER,\n" +
            "    \"MultiPilotTime\" TEXT,\n" +
            "    \"NightFlyingTime\" TEXT,\n" +
            "    \"IFRFlyingTime\" TEXT,\n" +
            "    \"PIC\" TEXT,\n" +
            "    \"CoPilot\" TEXT,\n" +
            "    \"Dual\" TEXT,\n" +
            "    \"Instructor\" TEXT,\n" +
            "    \"Day\" INTEGER NOT NULL,\n" +
            "    \"Night\" INTEGER NOT NULL,\n" +
            "    \"SingleEngineLand\" INTEGER NOT NULL,\n" +
            "    \"MultiEngineLand\" INTEGER NOT NULL,\n" +
            "    CONSTRAINT \"FK_Logbooks_Pilots_pilotLogin\" FOREIGN KEY (\"pilotLogin\") REFERENCES \"Pilots\" (\"Login\")\n" +
            ")";

    private static final String DB_CREATE_RATINGS = "CREATE TABLE \"Ratings\" (\n" +
            "    \"RatingID\" INTEGER NOT NULL CONSTRAINT \"PK_Ratings\" PRIMARY KEY AUTOINCREMENT,\n" +
            "    \"pilotLogin\" TEXT,\n" +
            "    \"DateOfIssue\" TEXT NOT NULL,\n" +
            "    \"CustomRating\" TEXT,\n" +
            "    CONSTRAINT \"FK_Ratings_Pilots_pilotLogin\" FOREIGN KEY (\"pilotLogin\") REFERENCES \"Pilots\" (\"Login\")\n" +
            ")";

    private static final String DB_CREATE_CERTIFICATIONS = "CREATE TABLE \"Certifications\" (\n" +
            "    \"Number\" TEXT NOT NULL CONSTRAINT \"PK_Certifications\" PRIMARY KEY,\n" +
            "    \"pilotLogin\" TEXT,\n" +
            "    \"RecievedGrade\" INTEGER NOT NULL,\n" +
            "    \"CustomGrade\" TEXT,\n" +
            "    \"DateOfIssue\" TEXT NOT NULL,\n" +
            "    CONSTRAINT \"FK_Certifications_Pilots_pilotLogin\" FOREIGN KEY (\"pilotLogin\") REFERENCES \"Pilots\" (\"Login\")\n" +
            ")";

    private static final String DB_CREATE_SYNTHETICFLIGHTS = "CREATE TABLE \"SynthethicFlights\" (\n" +
            "    \"SynthID\" INTEGER NOT NULL CONSTRAINT \"PK_SynthethicFlights\" PRIMARY KEY AUTOINCREMENT,\n" +
            "    \"pilotLogin\" TEXT,\n" +
            "    \"date\" TEXT NOT NULL,\n" +
            "    \"type\" INTEGER NOT NULL,\n" +
            "    \"TotalTime\" TEXT NOT NULL,\n" +
            "    CONSTRAINT \"FK_SynthethicFlights_Pilots_pilotLogin\" FOREIGN KEY (\"pilotLogin\") REFERENCES \"Pilots\" (\"Login\")\n" +
            ")";

    private static final String DB_CREATE_MEDICALCERTS = "CREATE TABLE \"MedicalCerts\" (\n" +
            "    \"MedicalID\" INTEGER NOT NULL CONSTRAINT \"PK_MedicalCerts\" PRIMARY KEY AUTOINCREMENT,\n" +
            "    \"pilotLogin\" TEXT,\n" +
            "    \"MedicalDate\" TEXT NOT NULL,\n" +
            "    \"MedicalClass\" TEXT,\n" +
            "    \"FlightDate\" TEXT NOT NULL,\n" +
            "    \"InstrumentDate\" TEXT NOT NULL,\n" +
            "    CONSTRAINT \"FK_MedicalCerts_Pilots_pilotLogin\" FOREIGN KEY (\"pilotLogin\") REFERENCES \"Pilots\" (\"Login\")\n" +
            ")";

    public MyDatabase(Context ct){
        super(ct,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_CREATE_PILOTS);
        sqLiteDatabase.execSQL(DB_CREATE_LOGBOOKS);
        sqLiteDatabase.execSQL(DB_CREATE_RATINGS);
        sqLiteDatabase.execSQL(DB_CREATE_CERTIFICATIONS);
        sqLiteDatabase.execSQL(DB_CREATE_SYNTHETICFLIGHTS);
        sqLiteDatabase.execSQL(DB_CREATE_MEDICALCERTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
