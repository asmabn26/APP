package com.example.app.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.app.classes.PersonRecord;
import com.example.app.util.LocalDbHelper;

public class PersonService {

    private LocalDbHelper helper;
    private final String TABLE = "etudiant";

    public PersonService(Context context) {
        helper = new LocalDbHelper(context);
    }

    // CREATE
    public void insertPerson(PersonRecord p) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nom", p.getLastName());
        values.put("prenom", p.getFirstName());

        db.insert(TABLE, null, values);
        Log.d("INSERT", p.getLastName());

        db.close();
    }

    // READ BY ID
    public PersonRecord getPersonById(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE,
                null,
                "id = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            PersonRecord p = new PersonRecord();
            p.setIdentifier(cursor.getInt(0));
            p.setLastName(cursor.getString(1));
            p.setFirstName(cursor.getString(2));

            cursor.close();
            db.close();
            return p;
        }

        cursor.close();
        db.close();
        return null;
    }

    // DELETE
    public void deletePerson(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.delete(TABLE, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    // READ ALL
    public List<PersonRecord> getAllPersons() {
        List<PersonRecord> list = new ArrayList<>();

        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE, null);

        if (cursor.moveToFirst()) {
            do {
                PersonRecord p = new PersonRecord();
                p.setIdentifier(cursor.getInt(0));
                p.setLastName(cursor.getString(1));
                p.setFirstName(cursor.getString(2));

                list.add(p);
                Log.d("DATA", p.getIdentifier() + " " + p.getLastName());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
}