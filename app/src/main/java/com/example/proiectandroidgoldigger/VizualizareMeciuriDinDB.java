package com.example.proiectandroidgoldigger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.proiectandroidgoldigger.adaptori.ClasamentAdaptor;
import com.example.proiectandroidgoldigger.async.Callback;
import com.example.proiectandroidgoldigger.async.ClasamentJsonParser;
import com.example.proiectandroidgoldigger.async.preluareHttp;
import com.example.proiectandroidgoldigger.constructorii.Clasament;
import com.example.proiectandroidgoldigger.database.ClasamentService;

import java.util.ArrayList;
import java.util.List;

public class VizualizareMeciuriDinDB extends AppCompatActivity {

    private ListView lvclasament;
    private List<Clasament> clasamentList=new ArrayList<>();
    private ClasamentService clasamentService;
    private static String adresaUrl= "https://jsonkeeper.com/b/BCT8";
    private Spinner spinnerSortare;
    private Toolbar toolbar;
    private ImageButton home;
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
        setContentView(R.layout.activity_vizualizare_meciuri_din_d_b);
        lvclasament=findViewById(R.id.lvClasament);
        spinnerSortare=findViewById(R.id.spinnerSortare);
        addAdapter();
        clasamentService =new ClasamentService(getApplicationContext());
        getClasamentHttp();
        lvclasament.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),MeciuriDinClasament.class);
                intent.putExtra("pozitie",clasamentList.get(position).getTeam());
                startActivity(intent);
            }
        });
        ArrayList<String> populareSpinner = new ArrayList<>();
        populareSpinner.add("All");
        populareSpinner.add("Group A");
        populareSpinner.add("Group B");
        populareSpinner.add("Group C");
        populareSpinner.add("Group D");
        populareSpinner.add("Group E");
        populareSpinner.add("Group F");
        populareSpinner.add("Group H");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, populareSpinner);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSortare.setAdapter(arrayAdapter);
        spinnerSortare.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    clasamentService.getAll(getAllFromDbCallback());
                }
                else {
                    String label = parent.getItemAtPosition(position).toString();
                    clasamentService.getSelected(getSelectedFromDB(), label);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                clasamentService.getAll(getAllFromDbCallback());
            }
        });
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        home=findViewById(R.id.icon_home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        profil = findViewById(R.id.icon_profile);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileSettings.class);
                startActivity(intent);
            }
        });
    }


    private void getClasamentHttp() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new preluareHttp(adresaUrl).call();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0; i<ClasamentJsonParser.fromJson(result).size();i++) {
                            clasamentService.insert(insertIntoDbCallback(), ClasamentJsonParser.fromJson(result).get(i));
                            notifyAdapter();
                        }
                        clasamentService.getAll(getAllFromDbCallback());
                    }
                });
            }
        };
        thread.start();
    }
    private Callback<List<Clasament>> getSelectedFromDB(){
        return new Callback<List<Clasament>>() {
            @Override
            public void runResultOnUiThread(List<Clasament> result) {
                if (result != null) {
                    clasamentList.clear();
                    clasamentList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }
    private Callback<List<Clasament>> getAllFromDbCallback() {
        return new Callback<List<Clasament>>() {
            @Override
            public void runResultOnUiThread(List<Clasament> result) {
                if (result != null) {
                    clasamentList.clear();
                    clasamentList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Clasament> insertIntoDbCallback() {
        return new Callback<Clasament>() {
            @Override
            public void runResultOnUiThread(Clasament result) {
                if (result != null) {
                    clasamentList.add(result);
                    notifyAdapter();
                }
            }
        };
    }

//    private Callback<Clasament> updateToDbCallback() {
//        return new Callback<Clasament>() {
//            @Override
//            public void runResultOnUiThread(Clasament result) {
//                if (result != null) {
//                    for (Clasament clasament : clasamentList) {
//                        if (clasament.getId()==result.getId()) {
//                            clasament.setTeam(result.getTeam());
//                            clasament.setPozitie(result.getPozitie());
//                            clasament.setMeciuriJucate(result.getMeciuriJucate());
//                            clasament.setMeciuriCastigate(result.getMeciuriCastigate());
//                            clasament.setMeciuriEgale(result.getMeciuriEgale());
//                            clasament.setMeciuriPierdute(result.getMeciuriPierdute());
//                            clasament.setGoluriPentru(result.getGoluriPentru());
//                            clasament.setGoluriImpotriva(result.getGoluriImpotriva());
//                            clasament.setPunctaj(result.getPunctaj());
//                            break;
//                        }
//                    }
//                    notifyAdapter();
//                }
//            }
//        };
//    }

    private void addAdapter() {
        ClasamentAdaptor adapter = new ClasamentAdaptor(getApplicationContext(), R.layout.list_view_clasament,
                clasamentList, getLayoutInflater());
        lvclasament.setAdapter(adapter);
    }

    private void notifyAdapter() {
        ClasamentAdaptor adapter = (ClasamentAdaptor) lvclasament.getAdapter();
        adapter.notifyDataSetChanged();
    }

}