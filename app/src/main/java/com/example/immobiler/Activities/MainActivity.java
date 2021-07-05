package com.example.immobiler.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.immobiler.Controller.BienController;
import com.example.immobiler.Controller.CategoryController;
import com.example.immobiler.DBHelper;
import com.example.immobiler.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText txtBienName;
    EditText txtQuantity;
    Spinner spinnerCategories;
    Button btnSaveBien;

    private static boolean isUpdate;
    private static int realId;
    private static int realCategoryId=0; // categories : residential - Land- Industrial- Commercial...

    DBHelper dbHelper;
    BienController bienC;
    CategoryController categoryC;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenir parametre intent derniere activite
        isUpdate = getIntent().getBooleanExtra("isEdit", false);
        realId = getIntent().getIntExtra("realId", 0);

        //instances de class
        dbHelper = new DBHelper(this);
        bienC = new BienController();
        categoryC =  new CategoryController();

        // GUI layout
        setUIComponents();
        addItemsOnSpinnerCategories();


        //Remplir liste si mise a jour
        if(isUpdate)
        {
            Cursor getBiens = dbHelper.rawQuery(bienC.getById(realId));

            if (getBiens != null ) {
                //Move cursor to first row
                if  (getBiens.moveToFirst()) {
                    do {
                        //Get version from Cursor
                        txtBienName.setText(
                                getBiens.getString(
                                        getBiens.getColumnIndex(
                                                bienC.getcBienname())));

                        txtQuantity.setText(
                                getBiens.getString(
                                        getBiens.getColumnIndex(
                                                bienC.getcQuantity())));


                        spinnerCategories.setSelection(
                                getBiens.getInt(
                                        getBiens.getColumnIndex(
                                                bienC.getcIdBienCategory()))-1);

                    }while (getBiens.moveToNext()); //Move to next row
                }
            }
        }



    }

    public void goToListBien (View view){
        Intent intent = new Intent (this, ListBienActivity.class);
        startActivity(intent);}

    //Composants mtaa gui layout
    private void setUIComponents(){
        txtBienName = (EditText)findViewById(R.id.txtBienName);
        txtQuantity = (EditText)findViewById(R.id.txtQuantity);
        btnSaveBien = (Button)findViewById(R.id.btnSaveBien);
        spinnerCategories = (Spinner)findViewById(R.id.spinnerCategories);

        //bouton click
        btnSaveBien.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //si creation
                if (!isUpdate) {
                    dbHelper.executeSql(bienC.insert(
                            txtBienName.getText().toString(),
                            Integer.parseInt(txtQuantity.getText().toString()), realCategoryId));

                }//si mise a jour
                else {
                    dbHelper.executeSql(bienC.update(
                            txtBienName.getText().toString(),
                            Integer.parseInt(txtQuantity.getText().toString()), realCategoryId, realId));
                }

                //aller liste activité
                Intent i = new Intent(MainActivity.this, ListBienActivity.class);
                startActivity(i);
            }
        });




        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                realCategoryId =  obtainCategorySelectedId(arg2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                //optionally do something here
            }
        });
    }


      //Obtenir Id de l'item selectionné Si position == listviewpos alors obtenir id
    private int obtainCategorySelectedId(int position){
        int toStop=0;
        int returnId=0;
        Cursor getCategories = dbHelper.rawQuery(categoryC.getAll());
        if (getCategories != null ) {

            //Move cursor to first row
            if  (getCategories.moveToFirst()) {
                do {
                    if(position==toStop) {

                        //Get version from Cursor
                        returnId = getCategories.getInt(getCategories.getColumnIndex(categoryC.getcIdCategory()));
                    }
                    toStop++;
                }while (getCategories.moveToNext()); //Move to next row
            }
        }
        return returnId;
    }


   //ajouter items a spinner
    private void addItemsOnSpinnerCategories(){
        ArrayList<String> categoryResults = new ArrayList<String>();

        //obtain the cursor of get all
        Cursor getCategories = dbHelper.rawQuery(categoryC.getAll());

        if (getCategories != null ) {

            //Move cursor to first row
            if  (getCategories.moveToFirst()) {
                do {

                    //Get version from Cursor
                    String firstName = getCategories.getString(getCategories.getColumnIndex(categoryC.getcCategoryname()));

                    //Add the version to Arraylist 'results'
                    categoryResults.add(firstName);
                }while (getCategories.moveToNext()); //Move to next row
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, categoryResults);
        spinnerCategories.setAdapter(adapter);
    }
}


