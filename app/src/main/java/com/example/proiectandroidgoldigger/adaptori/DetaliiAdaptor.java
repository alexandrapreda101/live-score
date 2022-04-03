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
import com.example.proiectandroidgoldigger.constructorii.Detalii;

import java.util.List;

public class DetaliiAdaptor extends ArrayAdapter<Detalii> {
    private Context context;
    private List<Detalii> listaDetalii;
    private LayoutInflater inflater;
    private int resource;

    public DetaliiAdaptor(@NonNull Context context, int resource,
                          @NonNull List<Detalii> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.listaDetalii = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Detalii detalii = listaDetalii.get(position);
        if (detalii != null) {
            adaugaNumeJucator(view, detalii.getNumeJucator());
            adaugaMinut(view, detalii.getMinut());
            adaugaGolEchipa1(view, detalii.getGolEchipa1());
            adaugaGolEchipa2(view, detalii.getGolEchipa2());
        }
        return view;
    }
    private void adaugaMinut(View view, int minut) {
        TextView textView = view.findViewById(R.id.textviewMinut);
        populateTextViewContent(textView, String.valueOf(minut));
    }


    private void adaugaNumeJucator(View view, String numeJucator) {
        TextView textView = view.findViewById(R.id.textViewNumeJucator);
        populateTextViewContent(textView, (numeJucator));
    }

    private void adaugaGolEchipa1(View view, int golEchipa1) {
        TextView textView = view.findViewById(R.id.textviewGolEchipa1);
        populateTextViewContent(textView, String.valueOf(golEchipa1));
    }

    private void adaugaGolEchipa2(View view, int golEchipa2) {
        TextView textView = view.findViewById(R.id.textviewGolEchipa2);
        populateTextViewContent(textView, String.valueOf(golEchipa2));
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        }
    }
}

