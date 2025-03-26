package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS course(username TEXT PRIMARY KEY, password TEXT, highscore INTEGER DEFAULT 0)";
        sqLiteDatabase.execSQL(query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query= "DROP TABLE IF EXISTS course";
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    public long addItems(String username, String password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("highscore", 0);
        return sqLiteDatabase.insert("course", null, contentValues);
    }

    public Cursor displayData(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM course",null);

        return cursor;
    }

    public Boolean account_login_match(String usernm, String pswrd){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM course where username = ? AND password = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query,new String[]{usernm, pswrd});

        return (cursor.getCount()>0);
    }

    public Boolean account_exist(String usernm){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM course where username = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(query,new String[]{usernm});

        return (cursor.getCount()>0);

    }

    public void updateHighScore(String username, int newScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT highscore FROM course WHERE username = ?", new String[]{username});

        if (cursor.moveToFirst()) {
            int currentScore = cursor.getInt(0);
            if (newScore > currentScore) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("highscore", newScore);
                db.update("course", contentValues, "username = ?", new String[]{username});
            }
        }
        cursor.close();
    }
    public Cursor getTopScores(int limit) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT username, highscore FROM course ORDER BY highscore DESC LIMIT ?";
        return db.rawQuery(query, new String[]{String.valueOf(limit)});
    }
    public int getUserHighScore(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT highscore FROM course WHERE username = ?", new String[]{username});
        int highScore = 0; // Default value if not found
        if (cursor.moveToFirst()) {
            highScore = cursor.getInt(0);
        }
        cursor.close();
        return highScore;
    }


}
