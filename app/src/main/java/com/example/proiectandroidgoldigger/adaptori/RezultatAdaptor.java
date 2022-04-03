package com.example.proiectandroidgoldigger.adaptori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectandroidgoldigger.R;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;

import java.util.List;

public class RezultatAdaptor extends ArrayAdapter<Rezultat> {
    private Context context;
    private List<Rezultat> rezultatList;
    private LayoutInflater inflater;
    private int resource;

    public RezultatAdaptor(@NonNull Context context, int resource,
                           @NonNull List<Rezultat> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.rezultatList = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Rezultat Rezultate = rezultatList.get(position);
        if (Rezultate != null) {
            adaugaEtapa(view, Rezultate.getEtapa());
            adaugaData(view, Rezultate.getData());
            adaugaOra(view, Rezultate.getOra());
            adaugaStadion(view, Rezultate.getStadion());
            adaugaEchipa1(view, Rezultate.getEchipa1());
            adaugaScor1(view, Rezultate.getScor1());
            adaugaScor2(view, Rezultate.getScor2());
            adaugaEchipa2(view, Rezultate.getEchipa2());
        }
        return view;
    }

    private void adaugaEtapa(View view, String Grupa) {
        TextView textView = view.findViewById(R.id.textviewGrupa);
        populateTextViewContent(textView, Grupa);
    }

    private void adaugaData(View view, String Data) {
        TextView textView = view.findViewById(R.id.textviewData);
        populateTextViewContent(textView, Data);
    }

    private void adaugaOra(View view, String ora) {
        TextView textView = view.findViewById(R.id.textviewOra);
        populateTextViewContent(textView, ora);
    }

    private void adaugaStadion(View view, String stadion) {
        TextView textView = view.findViewById(R.id.textviewStadion);
        populateTextViewContent(textView, stadion);
    }

    private void adaugaEchipa1(View view, String echipa1) {
        TextView textView = view.findViewById(R.id.textviewEchipa1);
        populateTextViewContent(textView, echipa1);
    }

    private void adaugaScor1(View view, int scor1) {
        TextView textView = view.findViewById(R.id.textviewScor1);
        populateTextViewContent(textView, String.valueOf(scor1));
    }

    private void adaugaScor2(View view, int scor2) {
        TextView textView = view.findViewById(R.id.textviewScor2);
        populateTextViewContent(textView, String.valueOf(scor2));
    }

    private void adaugaEchipa2(View view, String echipa2) {
        TextView textView = view.findViewById(R.id.textviewEchipa2);
        populateTextViewContent(textView, echipa2);
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        }
    }
}
