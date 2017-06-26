package org.vinci.dhinesh.world;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class InfoPays extends AppCompatActivity {
    private ImageView imageView;
    private TextView nom;
    private TextView code;
    private TextView capital;
    private TextView region;
    private TextView surface;
    private String main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_pays);
        // Recup id
        nom = (TextView)findViewById(R.id.nom);
        capital = (TextView)findViewById(R.id.capital);
        code = (TextView)findViewById(R.id.code);
        region = (TextView)findViewById(R.id.region);
        surface = (TextView)findViewById(R.id.surface);
        imageView =(ImageView)findViewById(R.id.imageView);



        // Set element
        imageView.setImageResource(getIntent().getIntExtra("drapeau",R.drawable.ad));

        String nameIntent = getIntent().getStringExtra("nom");
        nom.setText("Name: " + nameIntent);
        setTitle(nameIntent);

        String capitalIntent = getIntent().getStringExtra("capital");
        capital.setText("Capital: "+ capitalIntent);

        String codeIntent =getIntent().getStringExtra("code");
        code.setText("Code: " + codeIntent);

        String regionIntent=getIntent().getStringExtra("region");
        region.setText("Continent: " + regionIntent);

        int surfaceIntent =   getIntent().getIntExtra("surface", 1);
        surface.setText("Area: " + surfaceIntent +"KM²");

      //  main = getIntent().getStringExtra("Main");
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





}
