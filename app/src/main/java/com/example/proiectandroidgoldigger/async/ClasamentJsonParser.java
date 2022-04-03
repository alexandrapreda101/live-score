package com.example.proiectandroidgoldigger.async;

import com.example.proiectandroidgoldigger.constructorii.Clasament;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClasamentJsonParser {
    public static List<Clasament> fromJson(String json) {
        try {
            JSONObject obiect = new JSONObject(json);
            JSONArray array = obiect.getJSONArray("groups");
            return readClasament(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Clasament> readClasament(JSONArray array) throws JSONException {
        List<Clasament> rezultatList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            List<Clasament> clasamente = readClasament(array.getJSONObject(i));
            rezultatList.addAll(clasamente);
        }
        return rezultatList;
    }

    private static List<Clasament> readClasament(JSONObject object) throws JSONException {
        String grupaJ=object.getString("name");
        JSONArray standings = object.getJSONArray("standings");
        List<Clasament> rezultatList = new ArrayList<>();
        for (int i = 0; i < standings.length(); i++) {
            JSONObject locInClasament = standings.getJSONObject(i);

            String teamJ = locInClasament.getJSONObject("team").getString("name");
            int posJ = locInClasament.getInt("pos");
            int playedJ = locInClasament.getInt("played");
            int wonJ = locInClasament.getInt("won");
            int drawnJ = locInClasament.getInt("drawn");
            int lostJ = locInClasament.getInt("lost");
            int goalsforJ = locInClasament.getInt("goals_for");
            int goalsagainstJ = locInClasament.getInt("goals_against");
            int pointsJ = locInClasament.getInt("pts");
            String pozaJ = locInClasament.getString("poza");

            Clasament rezultat = new Clasament(teamJ,grupaJ, posJ, playedJ, wonJ, drawnJ, lostJ, goalsforJ, goalsagainstJ, pointsJ,pozaJ);
            rezultatList.add(rezultat);
        }
        return rezultatList;
    }
}
