package com.example.proiectandroidgoldigger.async;

import com.example.proiectandroidgoldigger.constructorii.Detalii;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MeciuriJsonParser {

    public static List<Rezultat> fromJson(String json) {
        try {
            JSONObject obiect = new JSONObject(json);
        JSONArray array = obiect.getJSONArray("rounds");
        return readMeciuri(array);
    } catch (JSONException e) {
        e.printStackTrace();
    }
        return new ArrayList<>();
    }

    private static List<Rezultat> readMeciuri(JSONArray array) throws JSONException {
        List<Rezultat> meciuriList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            List<Rezultat> meciuri = readMeciuri(array.getJSONObject(i));
            meciuriList.addAll(meciuri);
        }
        return meciuriList;
    }

    private static List<Rezultat> readMeciuri(JSONObject object) throws JSONException {
        JSONArray matches = object.getJSONArray("matches");
        String etapaJ = object.getString("name");
        List<Rezultat> rezultatList = new ArrayList<>();

        for (int i = 0; i < matches.length(); i++) {
            JSONObject meci = matches.getJSONObject(i);

            String dateJ = meci.getString("date");
            String timeJ = meci.getString("time");
            String team1J = meci.getJSONObject("team1").getString("name");
            String team2J = meci.getJSONObject("team2").getString("name");
            int score1J = meci.getInt("score1");
            int score2J = meci.getInt("score2");

            List<Detalii> goals1=new ArrayList<>();
            JSONArray goals1J =meci.getJSONArray("goals1");
            for(int j=0; j<goals1J.length();j++){
                JSONObject golJ=goals1J.getJSONObject(j);
                String nameJ=golJ.getString("name");
                int minutJ=golJ.getInt("minute");
                int score1EchipaJ=golJ.getInt("score1");
                int score2EchipaJ=golJ.getInt("score2");
                Detalii detalii =new Detalii(minutJ,nameJ,score1EchipaJ,score2EchipaJ);
                goals1.add(detalii);
            }

            List<Detalii> goals2=new ArrayList<>();
            JSONArray goals2J =meci.getJSONArray("goals2");
            for(int j=0; j<goals2J.length();j++){
                JSONObject golJ=goals2J.getJSONObject(j);
                String nameJ=golJ.getString("name");
                int minutJ=golJ.getInt("minute");
                int score1EchipaJ=golJ.getInt("score1");
                int score2EchipaJ=golJ.getInt("score2");
                Detalii detalii =new Detalii(minutJ,nameJ,score1EchipaJ,score2EchipaJ);
                goals2.add(detalii);
            }
            String stadionJ = meci.getJSONObject("stadium").getString("name");


            Rezultat rezultat = new Rezultat(goals1,goals2,etapaJ,dateJ,timeJ,
                    stadionJ,team1J,score1J,score2J,team2J);
            rezultatList.add(rezultat);
        }

        return rezultatList;
    }
}
