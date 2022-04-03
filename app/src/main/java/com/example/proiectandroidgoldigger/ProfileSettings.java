package com.example.proiectandroidgoldigger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class ProfileSettings extends AppCompatActivity {

    private ImageButton clasamente;
    private ImageButton home;
    private Switch darkMode;
    SharedPref sharedPref;
    SharedPreferences sharedPreferences;
    TextInputEditText fullname;
    Button save;
    TextView userFullName;
    private RadioButton r1;
    private RadioButton r2;
    private RadioButton r3;
    private RadioGroup photos;
    private ImageView profile;
    private Integer [] images = {R.drawable.player,R.drawable.player2,R.drawable.player3};

    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_FULL_NAME="fullname";
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        sharedPref = new SharedPref(this);
        super.onCreate(savedInstanceState);
        if (sharedPref.loadNightModeState()==true) {
            setTheme(R.style.AppThemeBlack);
        }
        else
            setTheme(R.style.AppTheme);
        setContentView(R.layout.activity_profile_settings);
        initcomponents();
        clasamente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VizualizareMeciuriDinDB.class);
                startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        if (sharedPref.loadNightModeState()==true) {
            darkMode.setChecked(true);
        }
        darkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    sharedPref.setNightModeState(true);
                }
                else{
                    sharedPref.setNightModeState(false);
                }
                restartApp();
            }
        });
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_FULL_NAME,fullname.getText().toString());
                editor.apply();
                userFullName.setText(fullname.getText().toString());
                SharedPreferences sp = getSharedPreferences("key", 0);
                SharedPreferences.Editor sedt = sp.edit();
                sedt.putString("textvalue", userFullName.getText().toString());
                sedt.commit();
            }
        });
        SharedPreferences sp = getSharedPreferences("key", 0);
        String tValue = sp.getString("textvalue","");
        userFullName.setText(tValue);
//        name = sharedPreferences.getString(KEY_FULL_NAME,fullname.getText().toString());
//        userFullName.setText(name);
        photos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = photos.findViewById(checkedId);
                int index = photos.indexOfChild(radioButton);
                profile.setImageResource(images[index]);
            }
        });
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean one_isChecked) {
                sharedPreferences.edit().putBoolean("bntChecked1", r1.isChecked()).apply();
            }
        });
        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean two_isChecked) {
                sharedPreferences.edit().putBoolean("bntChecked2", r2.isChecked()).apply();
            }
        });
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean three_isChecked) {
                sharedPreferences.edit().putBoolean("bntChecked3", r3.isChecked()).apply();
            }
        });
        loadRadioButtons();

    }
    public void loadRadioButtons(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        r1.setChecked(sharedPreferences.getBoolean("bntChecked1", false));
        r2.setChecked(sharedPreferences.getBoolean("bntChecked2", false));
        r3.setChecked(sharedPreferences.getBoolean("bntChecked3", false));
    }

    private void restartApp() {
        Intent intent = new Intent(getApplicationContext(),ProfileSettings.class);
        startActivity(intent);
        finish();
    }

    private void initcomponents() {
        clasamente = findViewById(R.id.icon_clasament);
        home=findViewById(R.id.icon_home);
        darkMode=findViewById(R.id.switch_dark_mode);
        save=findViewById(R.id.save);
        fullname=findViewById(R.id.FullName);
        userFullName=findViewById(R.id.userFullNametv);
        profile=findViewById(R.id.soccerplayer);
        photos=findViewById(R.id.radioGroup);
        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        r3=findViewById(R.id.radioButton3);

    }

}