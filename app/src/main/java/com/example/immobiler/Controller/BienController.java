package com.example.immobiler.Controller;

public class BienController {

    public static final String C_TABLE_NAME = "BIEN";
    public static final String C_ID_BIEN = "idBien";
    public static final String C_BIEN_NAME = "bienName";
    public static final String C_QUANTITY = "quantity";
    public static final String C_ID_BIEN_CATEGORY = "idBienCategory";

    //the getters and setters
    public String getcTableName() {
        return C_TABLE_NAME;
    }

    public String getcIdBien() {
        return C_ID_BIEN;
    }

    public String getcBienname() {
        return C_BIEN_NAME;
    }

    public String getcQuantity() {
        return C_QUANTITY;
    }

    public String getcIdBienCategory() {
        return C_ID_BIEN_CATEGORY;
    }


    //Insertion du bien
    public String insert(String movieName, int quantity, int idMovieCategory) {
        return "INSERT INTO " + C_TABLE_NAME + "(" + C_BIEN_NAME + " , " + C_QUANTITY + " , " + C_ID_BIEN_CATEGORY + ") " +
                "VALUES ('" + movieName + "'," + quantity + "," + idMovieCategory + ");";
    }

    //Suppression des biens
    public String delete(int idMovie) {
        return "DELETE FROM " + C_TABLE_NAME + " WHERE " + C_ID_BIEN + " = " + idMovie + ";";
    }

   //Mise a jour bien
    public String update(String movieName, int quantity, int idMovieCategory, int idMovie) {
        return "UPDATE " + C_TABLE_NAME +
                " SET " + C_BIEN_NAME + " = '" + movieName + "' , " +
                C_QUANTITY + " = " + quantity + " , " +
                C_ID_BIEN_CATEGORY + " = " + idMovieCategory +
                " WHERE "+ C_ID_BIEN + " = " + idMovie + ";";
    }

    // tous les biens
    public String getAll() {
        return "SELECT * FROM " + C_TABLE_NAME;
    }

    // bien par id
    public String getById(int idMovie) {
        return "SELECT * FROM " + C_TABLE_NAME + " WHERE " + C_ID_BIEN + " = " + idMovie;
    }


}


