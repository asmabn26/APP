package com.example.app.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LocalDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "ecole_db";
    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE =
            "CREATE TABLE etudiant (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nom TEXT, " +
                    "prenom TEXT)";

    public LocalDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS etudiant");
        onCreate(db);
    }
}