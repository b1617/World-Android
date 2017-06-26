package org.vinci.dhinesh.world;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresApi;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;
import android.util.Log;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import static java.security.AccessController.getContext;
import static org.vinci.dhinesh.world.R.drawable.ad;
import static org.vinci.dhinesh.world.R.drawable.de;
import static org.vinci.dhinesh.world.R.drawable.np;
import static org.vinci.dhinesh.world.R.id.all;
import static org.vinci.dhinesh.world.R.id.parent;

public class MainWorld extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private ArrayList<Pays> lesPays = new ArrayList<Pays>();

    private ArrayList<Pays> listAsia = new ArrayList<Pays>();
    private ArrayList<Pays> listAfrica = new ArrayList<Pays>();
    private ArrayList<Pays> listEurope = new ArrayList<Pays>();
    private ArrayList<Pays> listOceania = new ArrayList<Pays>();
    private ArrayList<Pays> listAmerica = new ArrayList<Pays>();

    private String regionJson;
    private String codeJson;
    private int surfaceJson;
    private MyAdapter adapter;
    private String lower;
    private String cca2;
    private int resImage;
    private String nom;
    private String capital;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_world);


        mListView = (ListView) findViewById(R.id.listView);




        final List<Pays> pays = allPays();


        final MyAdapter adapter = new MyAdapter(MainWorld.this, pays);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);




        for (int i = 0; i < lesPays.size(); i++) {
            if (lesPays.get(i).getRegion().equals("Asia")) {
                listAsia.add(lesPays.get(i));

            }
            if (lesPays.get(i).getRegion().equals("Africa")) {
                listAfrica.add(lesPays.get(i));

            }
            if (lesPays.get(i).getRegion().equals("Europe")) {
                listEurope.add(lesPays.get(i));

            }
            if (lesPays.get(i).getRegion().equals("Oceania")) {
                listOceania.add(lesPays.get(i));

            }
            if (lesPays.get(i).getRegion().equals("Americas")) {
                listAmerica.add(lesPays.get(i));

            }
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }


    private List<Pays> allPays() {

//lecture du fichier contacts.json pour mettre chaque ligne dans une chaine de caractère

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(getAssets().open("country.json")));
            String temp;
            while ((temp = br.readLine()) != null)
                sb.append(temp);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close(); // stop reading
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        String myjsonstring = sb.toString();


        try {


            // Create the root JSONObject from the JSON string.
            JSONObject jsonRootObject = new JSONObject(myjsonstring);
            //Get the instance of JSONArray that contains JSONObjects
            JSONArray jsonArray = jsonRootObject.optJSONArray("pays");
            //Iterate the jsonArray and print the info of JSONObjects

            //    ArrayList<String> genre = new ArrayList<String>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject name = jsonObject.getJSONObject("name");

                nom = name.getString("common");
                codeJson = jsonObject.getString("cioc");
                regionJson = jsonObject.getString("region");
                surfaceJson = jsonObject.getInt("area");
                capital = jsonObject.getString("capital");

                cca2 = jsonObject.getString("cca2");
                lower = cca2.toLowerCase();
                Log.i("contacts", lower + "  " + nom + "  " + capital);


                resImage = getResources().getIdentifier(lower, "drawable", getPackageName());

                lesPays.add(new Pays(resImage, nom, capital, codeJson, regionJson, surfaceJson, lower));


            }

        } catch (JSONException e) {
            e.printStackTrace();


        }

        return lesPays;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.asie:
                Intent asie = new Intent();
                asie.putExtra("Asia", listAsia);
                asie.setClass(MainWorld.this, Continent.class);
                startActivity(asie);
                //  Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            case R.id.afirca:
                //   Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent africa = new Intent();
                africa.putExtra("Africa", listAfrica);
                africa.setClass(MainWorld.this, Continent.class);
                startActivity(africa);
                return true;
            case R.id.europe:
                //     Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent europe = new Intent();
                europe.putExtra("Europe", listEurope);
                europe.setClass(MainWorld.this, Continent.class);
                startActivity(europe);
                return true;
            case R.id.oceania:
                //     Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent oceania = new Intent();
                oceania.putExtra("Oceania", listOceania);
                oceania.setClass(MainWorld.this, Continent.class);
                startActivity(oceania);
                return true;
            case R.id.america:
                //     Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent america = new Intent();
                america.putExtra("America", listAmerica);
                america.setClass(MainWorld.this, Continent.class);
                startActivity(america);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        // Toast.makeText(getApplicationContext(), lesPays.get(position).getName(), Toast.LENGTH_SHORT).show();


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
              //  intent.putExtra("Main","Main");
                intent.setClass(MainWorld.this, InfoPays.class);
                startActivityForResult(intent, 1);


            }
        });


        // Showing Alert Message

        alertDialog.show();

    }


}



