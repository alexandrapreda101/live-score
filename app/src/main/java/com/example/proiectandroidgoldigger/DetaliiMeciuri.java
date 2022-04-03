package com.example.proiectandroidgoldigger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proiectandroidgoldigger.async.AsyncTaskManager;
import com.example.proiectandroidgoldigger.constructorii.Detalii;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;
import com.example.proiectandroidgoldigger.adaptori.DetaliiAdaptor;

import java.util.ArrayList;
import java.util.List;

public class DetaliiMeciuri extends AppCompatActivity {

    private TextView echipa1;
    private TextView scor1;
    private TextView scor2;
    private TextView echipa2;
    private ListView lvDetalii;
    public static String adresaUrl="https://jsonkeeper.com/b/5WYX";
    private Intent intent;
    Rezultat meci;
    private List<Detalii> listaDetalii=new ArrayList<>();
    private AsyncTaskManager asyncTaskRunner = new AsyncTaskManager();
    private Toolbar toolbar;
    private ImageButton clasamente;
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
        setContentView(R.layout.activity_detalii_meciuri);
        initcomponents();
        setSupportActionBar(toolbar);
        clasamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VizualizareMeciuriDinDB.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileSettings.class);
                startActivity(intent);
            }
        });

    }

    private void initcomponents() {
        echipa1=findViewById(R.id.textViewEchipa1Detalii);
        scor1=findViewById(R.id.textViewScor1Detalii);
        scor2=findViewById(R.id.textViewScor2Detalii);
        echipa2=findViewById(R.id.textViewEchipa2Detalii);
        lvDetalii=findViewById(R.id.lvDetalii);
        addAdapter();
        intent=getIntent();
        meci = (Rezultat) intent.getSerializableExtra("PozitieInVector");
        listaDetalii.addAll(meci.getGoals1());
        listaDetalii.addAll(meci.getGoals2());
        inlocuireTitlu();
        toolbar = findViewById(R.id.mytoolbar);
        clasamente = findViewById(R.id.icon_clasament);
        home=findViewById(R.id.icon_home);
        profil = findViewById(R.id.icon_profile);
    }

    private void inlocuireTitlu(){
        echipa1.setText(meci.getEchipa1());
        echipa2.setText(meci.getEchipa2());
        scor1.setText(String.valueOf(meci.getScor1()));
        scor2.setText(String.valueOf(meci.getScor2()));
    }

    private void addAdapter(){
        DetaliiAdaptor adaptor=new DetaliiAdaptor(getApplicationContext(),R.layout.list_view_detalii,
                listaDetalii,getLayoutInflater());
        lvDetalii.setAdapter(adaptor);
    }


    private void notifyAdapter() {
        ArrayAdapter adapter = (ArrayAdapter) lvDetalii.getAdapter();
        adapter.notifyDataSetChanged();
    }
}