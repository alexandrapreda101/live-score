package com.example.proiectandroidgoldigger;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.proiectandroidgoldigger.constructorii.Rezultat;
import com.example.proiectandroidgoldigger.async.MeciuriJsonParser;
import com.example.proiectandroidgoldigger.adaptori.RezultatAdaptor;
import com.example.proiectandroidgoldigger.async.preluareHttp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button LogOutButton;
    private ListView lvMeciuri;
    public static String adresaUrl="https://jsonkeeper.com/b/5WYX";
    private List<Rezultat> listaMeciuri=new ArrayList<>();
    private ImageButton clasamente;
    private ImageButton profil;
    private TextView emailUtilizator;
    private FirebaseUser user ;
    private String email;
    private Toolbar toolbar;
    SharedPref sharedPref;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState()==true) {
            setTheme(R.style.AppThemeBlack);
        }
        else
            setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initcomponents();
        getMeciuriHttp();
        LogOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(),LogIn.class);
                startActivity(intent);
                finish();
            }
        });
        lvMeciuri.setOnItemClickListener(OnClickItemLV());
        clasamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),VizualizareMeciuriDinDB.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ProfileSettings.class);
                startActivity(intent);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initcomponents() {
        LogOutButton=findViewById(R.id.logout);
        lvMeciuri=findViewById(R.id.lvMain);
        emailUtilizator=findViewById(R.id.textView2);
        addAdapter();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            email=user.getEmail();
            int iend = email.indexOf("@");
            String subString="";
            if (iend != -1)
            {
                subString= email.substring(0 , iend);
            }
            emailUtilizator.setText(subString);
        }

        toolbar = findViewById(R.id.mytoolbar);
        clasamente = findViewById(R.id.icon_clasament);
        profil = findViewById(R.id.icon_profile);
    }
    private AdapterView.OnItemClickListener OnClickItemLV() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, DetaliiMeciuri.class);
                intent.putExtra("PozitieInVector",listaMeciuri.get(position));
                startActivity(intent);
            }

        };
    }

    private void getMeciuriHttp() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new preluareHttp(adresaUrl).call();
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        listaMeciuri.addAll(MeciuriJsonParser.fromJson(result));
                        notifyAdapter();
                    }
                });
            }
        };
        thread.start();
    }

    private void addAdapter(){
        RezultatAdaptor adaptor=new RezultatAdaptor(getApplicationContext(),R.layout.list_view_meciuri,
                listaMeciuri,getLayoutInflater());
        lvMeciuri.setAdapter(adaptor);
    }

    private void notifyAdapter(){
        RezultatAdaptor adapter = (RezultatAdaptor) lvMeciuri.getAdapter();
        adapter.notifyDataSetChanged();
    }

}