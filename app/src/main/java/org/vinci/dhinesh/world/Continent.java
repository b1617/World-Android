package org.vinci.dhinesh.world;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Continent extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListView;
    private ArrayList<Pays> lesPays = new ArrayList<Pays>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continent);


        mListView = (ListView) findViewById(R.id.listView);

        ArrayList<Pays> Asia = this.getIntent().getParcelableArrayListExtra("Asia");
        ArrayList<Pays> Africa = this.getIntent().getParcelableArrayListExtra("Africa");
        ArrayList<Pays> Europe = this.getIntent().getParcelableArrayListExtra("Europe");
        ArrayList<Pays> Oceania = this.getIntent().getParcelableArrayListExtra("Oceania");
        ArrayList<Pays> America = this.getIntent().getParcelableArrayListExtra("America");


        if (Asia != null) {
            final MyAdapter adapterAsia = new MyAdapter(Continent.this, Asia);
            mListView.setAdapter(adapterAsia);
            setTitle("Asia");
            for (int i = 0; i < Asia.size(); i++) {
                lesPays.add(Asia.get(i));
            }

        } else if (Africa != null) {
            final MyAdapter adapterAfrica = new MyAdapter(Continent.this, Africa);
            mListView.setAdapter(adapterAfrica);
            setTitle("Africa");
            for (int i = 0; i < Africa.size(); i++) {
                lesPays.add(Africa.get(i));
            }

        } else if (Oceania != null) {
            final MyAdapter adapterOceania = new MyAdapter(Continent.this, Oceania);
            mListView.setAdapter(adapterOceania);
            setTitle("Oceania");
            for (int i = 0; i < Oceania.size(); i++) {
                lesPays.add(Oceania.get(i));
            }

        } else if (America != null) {
            final MyAdapter adapterAmerica = new MyAdapter(Continent.this, America);
            setTitle("America");
            mListView.setAdapter(adapterAmerica);
            for (int i = 0; i < America.size(); i++) {
                lesPays.add(America.get(i));
            }

        } else {
            final MyAdapter adapterEurope = new MyAdapter(Continent.this, Europe);
            mListView.setAdapter(adapterEurope);
            setTitle("Europe");
            for (int i = 0; i < Europe.size(); i++) {
                lesPays.add(Europe.get(i));
            }

        }


        mListView.setOnItemClickListener(this);
        // Creation back button dans la toolbar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        // Setting Dialog Title
        alertDialog.setTitle(lesPays.get(position).getName());

        // Setting Dialog Message
        alertDialog.setMessage("Id: " + lesPays.get(position).getCode() + "\nCapital: " + lesPays.get(position).getText()
                + "\nArea :" + lesPays.get(position).getSurface() + "KM²"
                + "\n\n\n See more details ?");

        // Setting Icon to Dialog
        int nvImage = getResources().getIdentifier(lesPays.get(position).getCodepng(), "drawable", getPackageName());
        alertDialog.setIcon(nvImage);


        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                dialog.cancel();


            }
        });


        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                // Write your code here to invoke YES event
                Intent intent = new Intent();
                intent.putExtra("nom", lesPays.get(position).getName());
                intent.putExtra("drapeau", lesPays.get(position).getId());
                intent.putExtra("capital", lesPays.get(position).getText());
                intent.putExtra("code", lesPays.get(position).getCode());
                intent.putExtra("region", lesPays.get(position).getRegion());
                intent.putExtra("surface", lesPays.get(position).getSurface());
                intent.setClass(Continent.this, InfoPays.class);
                startActivityForResult(intent, 1);


            }
        });


        // Showing Alert Message

        alertDialog.show();

    }


}


