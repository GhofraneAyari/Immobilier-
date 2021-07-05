package com.example.immobiler;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



public class DBHelper {
    private static SQLiteDatabase BienDB = null;





    //Constructeur
    public DBHelper(Context context) {
        super();
        createDataBase(context);
    }

    //creation de la bd
    public void createDataBase(Context context) {
        BienDB = context.openOrCreateDatabase("TestBienDB.db", Context.MODE_PRIVATE, null);



        //Table Des categories
        BienDB.execSQL("CREATE TABLE IF NOT EXISTS " + "CATEGORY" +
                "( idCategory INTEGER PRIMARY KEY AUTOINCREMENT," +
                " categoryName TEXT," +
                " code INTEGER);");

        //Table des Biens
        BienDB.execSQL("CREATE TABLE IF NOT EXISTS " + "BIEN" +
                " (idBien INTEGER PRIMARY KEY AUTOINCREMENT," +
                " bienName TEXT," +
                " quantity INTEGER," +
                " idBienCategory INTEGER," +
                " FOREIGN KEY(idBienCategory) REFERENCES CATEGORY(idCategory));");

        //Table user
        BienDB.execSQL("CREATE TABLE IF NOT EXISTS " + "USER" +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " Name TEXT," +
                " email TEXT," +
                " password TEXT)");

    }






    //Executer les req sql
    public void executeSql(String sqlQuery) {
        BienDB.execSQL(sqlQuery);
    }

    // renvoie curseur lors de la consultation
    public Cursor rawQuery(String sqlQuery) {
        return BienDB.rawQuery(sqlQuery, null);
    }














}

