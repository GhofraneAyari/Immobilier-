package com.example.immobiler.Activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.immobiler.Controller.CategoryController;
import com.example.immobiler.DBHelper;
import com.example.immobiler.R;

public class CategoryActivity extends AppCompatActivity {


    EditText txtCategoryName;
    EditText txtCode;
    Button btnSaveCategory;

    private static boolean isUpdate;
    private static int realId;

    DBHelper dbHelper;
    CategoryController categoryC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_category);

        //parametre intent derniere activite
        isUpdate = getIntent().getBooleanExtra("isEdit", false);
        realId = getIntent().getIntExtra("realId", 0);

        //instances des classes
        dbHelper = new DBHelper(this);
        categoryC = new CategoryController();

        //initialized the ui components
        setUIComponents();

        //if is update charge the data, in the form
        if(isUpdate)
        {
            Cursor getCategories = dbHelper.rawQuery(categoryC.getById(realId));

            if (getCategories != null ) {
                //Move cursor to first row
                if  (getCategories.moveToFirst()) {
                    do {
                        //Get version from Cursor
                        txtCategoryName.setText(
                                getCategories.getString(
                                        getCategories.getColumnIndex(
                                                categoryC.getcCategoryname())));

                        txtCode.setText(
                                getCategories.getString(
                                        getCategories.getColumnIndex(
                                                categoryC.getcCode())));

                    }while (getCategories.moveToNext()); //Move to next row
                }
            }
        }
    }

    //composants du layout
    private void setUIComponents(){
        txtCategoryName = (EditText)findViewById(R.id.txtCategoryName);
        txtCode = (EditText)findViewById(R.id.txtCode);
        btnSaveCategory = (Button)findViewById(R.id.btnSaveCategory);

        //set of button click
        btnSaveCategory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //if is create
                if(!isUpdate) {
                    dbHelper.executeSql(categoryC.insert(
                            txtCategoryName.getText().toString(),
                            Integer.parseInt(txtCode.getText().toString())));
                }
                //if is update
                else
                {
                    dbHelper.executeSql(categoryC.update(
                            txtCategoryName.getText().toString(),
                            Integer.parseInt(txtCode.getText().toString()), realId));
                }

                //go to the list activity
                Intent i = new Intent(CategoryActivity.this, ListBienActivity.class);
                startActivity(i);
            }
        });

    }

}
