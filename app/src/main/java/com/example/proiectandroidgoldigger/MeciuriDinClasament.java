package com.example.proiectandroidgoldigger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.proiectandroidgoldigger.adaptori.RezultatAdaptor;
import com.example.proiectandroidgoldigger.async.Callback;
import com.example.proiectandroidgoldigger.async.ClasamentJsonParser;
import com.example.proiectandroidgoldigger.async.MeciuriJsonParser;
import com.example.proiectandroidgoldigger.async.preluareHttp;
import com.example.proiectandroidgoldigger.constructorii.Clasament;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;
import com.example.proiectandroidgoldigger.database.ClasamentService;
import com.example.proiectandroidgoldigger.database.MeciuriDinClasamentService;

import java.util.ArrayList;
import java.util.List;

public class MeciuriDinClasament extends AppCompatActivity {

    private ListView lvMeciuri;
    private List<Rezultat> rezultatList = new ArrayList<>();
    private MeciuriDinClasamentService meciuriDinClasamentService;
    public static String adresaUrl="https://jsonkeeper.com/b/5WYX";
    private String pozitie;
    private Toolbar toolbar;
    private ImageButton home;
    private ImageButton clasamente;
    private ImageButton profil;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()==true) {
            setTheme(R.style.AppThemeBlack);
        }
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meciuri_din_clasament);
        lvMeciuri = findViewById(R.id.lvMeciuriDinClasament);
        addAdapter();
        meciuriDinClasamentService =new MeciuriDinClasamentService(getApplicationContext());
        meciuriDinClasamentService.delete(deleteToDbCallback());
        Intent intent=getIntent();
        pozitie = (String) intent.getSerializableExtra("pozitie");
        populareDBDinJson();
        toolbar();
    }

    private void populareDBDinJson() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new preluareHttp(adresaUrl).call();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < MeciuriJsonParser.fromJson(result).size(); i++) {
                            meciuriDinClasamentService.insert(insertIntoDbCallback(), MeciuriJsonParser.fromJson(result).get(i));
                            notifyAdapter();
                        }
                        meciuriDinClasamentService.getSelected(getSelectedFromDB(), pozitie);
                    }
                });
            }
        };
        thread.start();

    }
    private Callback<Integer> deleteToDbCallback() {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                notifyAdapter();
            }
        };
    }
    private Callback<Rezultat> insertIntoDbCallback() {
        return new Callback<Rezultat>() {
            @Override
            public void runResultOnUiThread(Rezultat result) {
                if (result != null) {
                   // result.setTeamNou(pozitie);
                    rezultatList.add(result);
                  //  notifyAdapter();
                }
            }
        };
    }
    private Callback<List<Rezultat>> getSelectedFromDB() {
        return new Callback<List<Rezultat>>() {
            @Override
            public void runResultOnUiThread(List<Rezultat> result) {
                if (result != null) {
                    rezultatList.clear();
                    rezultatList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }
    private void toolbar() {
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.icon_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        clasamente = findViewById(R.id.icon_clasament);
        clasamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), VizualizareMeciuriDinDB.class);
                startActivity(intent);
            }
        });
        profil = findViewById(R.id.icon_profile);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), ProfileSettings.class);
                startActivity(intent);
            }
        });
    }
    private void addAdapter() {
        RezultatAdaptor adaptor = new RezultatAdaptor(getApplicationContext(), R.layout.list_view_meciuri,
                rezultatList, getLayoutInflater());
        lvMeciuri.setAdapter(adaptor);
    }

    private void notifyAdapter() {
        RezultatAdaptor adapter = (RezultatAdaptor) lvMeciuri.getAdapter();
        adapter.notifyDataSetChanged();
    }

}