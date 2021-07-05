package com.example.immobiler.Controller;

public class CategoryController {



    public static final String C_TABLE_NAME = "CATEGORY";
    public static final String C_ID_CATEGORY = "idCategory";
    public static final String C_CATEGORYNAME = "categoryName";
    public static final String C_CODE = "code";

    //the getters and setters
    public String getcTableName() {
        return C_TABLE_NAME;
    }

    public String getcIdCategory() {
        return C_ID_CATEGORY;
    }

    public String getcCategoryname() {
        return C_CATEGORYNAME;
    }

    public String getcCode() {
        return C_CODE;
    }


   //Insertion d'une categorie
    public String insert(String categoryName, int code) {
        return "INSERT INTO " + C_TABLE_NAME + "(" + C_CATEGORYNAME + " , " + C_CODE + ") " +
                "VALUES ('" + categoryName + "'," + code + ");";
    }

    //Supprimer une categorie
    public String delete(int idCategory) {
        return "DELETE FROM " + C_TABLE_NAME + " WHERE " + C_ID_CATEGORY + " = " + idCategory + ";";
    }

    //mettre a jour une categorie
    public String update(String categoryName, int code, int idCategory) {
        return "UPDATE " + C_TABLE_NAME +
                " SET " + C_CATEGORYNAME + " = '" + categoryName + "' , " +
                C_CODE + " = " + code +
                " WHERE " + C_ID_CATEGORY + " = " + idCategory + ";";
    }

    //Rerourne all categories
    public String getAll() {
        return "SELECT * FROM " + C_TABLE_NAME;
    }

    //Retourne categorie par id
    public String getById(int idCategory) {
        return "SELECT * FROM " + C_TABLE_NAME + " WHERE " + C_ID_CATEGORY + " = " + idCategory;
    }
}

