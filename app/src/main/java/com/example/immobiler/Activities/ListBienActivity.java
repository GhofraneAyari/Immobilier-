package com.example.immobiler.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.immobiler.Controller.BienController;
import com.example.immobiler.DBHelper;
import com.example.immobiler.R;

import java.util.ArrayList;

public class ListBienActivity extends AppCompatActivity {

    //global variables
    private static DBHelper dbHelper;
    private static BienController BienC;
    private static ListView BienList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bien);

        //instanciation des variables
        BienList = (ListView) findViewById(R.id.bienList);
        dbHelper = new DBHelper(this);
        BienC = new BienController();

        //click in item of list view
        BienList.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        //obtain an Id to selected
                        int realId = obtainSelectedId(position);

                        //change the activity, and send parameters, true if is update
                        //and false if isn't
                        Intent i = new Intent (ListBienActivity.this, MainActivity.class);
                        i.putExtra("isEdit", true);
                        i.putExtra("realId", realId);
                        startActivity(i);
                    }
                }
        );



        //long click delete row
        BienList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                //obtain an Id to selected
                int realId = obtainSelectedId(pos);

                //delete the item selected
                dbHelper.executeSql(BienC.delete(realId));

                //refresh the listview
                refreshListView();
                return true;
            }
        });
    }


//    private AlertDialog AskOption()
//    {
//        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
//                // set message, title, and icon
//                .setTitle("Delete")
//                .setMessage("Do you want to Delete")
//                .setIcon(R.drawable.delete)
//
//                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
//
//                    public void onClick(DialogInterface dialog, int whichButton) {
//                        //your deleting code
//                        dialog.dismiss();
//                    }
//
//                })
//                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        dialog.dismiss();
//
//                    }
//                })
//                .create();
//
//        return myQuittingDialogBox;
//    }



    //Refresh list
    private void refreshListView(){
        ArrayList<String> movieResults = new ArrayList<String>();

        //obtain the cursor of get all
        Cursor getMovies = dbHelper.rawQuery(BienC.getAll());

        if (getMovies != null ) {

            //Move cursor to first row
            if  (getMovies.moveToFirst()) {
                do {

                    //Get version from Cursor
                    String firstName = getMovies.getString(getMovies.getColumnIndex(BienC.getcBienname()));

                    //Add the version to Arraylist 'results'
                    movieResults.add(firstName);
                }while (getMovies.moveToNext()); //Move to next row
            }
        }

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, movieResults);
        BienList.setAdapter(adapter);
    }


    //id item seelectionné
    private int obtainSelectedId(int position){
        int toStop=0;
        int returnId=0;
        Cursor getMovies = dbHelper.rawQuery(BienC.getAll());
        if (getMovies != null ) {
            //Move cursor to first row
            if  (getMovies.moveToFirst()) {
                do {
                    if(position==toStop) {
                        //Get version from Cursor
                        returnId = getMovies.getInt(getMovies.getColumnIndex(BienC.getcIdBien()));
                    }
                    toStop++;
                }while (getMovies.moveToNext()); //Move to next row
            }
        }
        return returnId;
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_bien, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.gotocreatebien) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.gotocreatecategory) {
            Intent intent = new Intent(this,CategoryActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

