package com.example.proiectandroidgoldigger.adaptori;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.proiectandroidgoldigger.R;
import com.example.proiectandroidgoldigger.constructorii.Clasament;

import java.util.List;

public class ClasamentAdaptor extends ArrayAdapter<Clasament> {

    private Context context;
    private List<Clasament> listaRezultat;
    private LayoutInflater inflater;
    private int resource;

    public ClasamentAdaptor(@NonNull Context context, int resource,
                            @NonNull List<Clasament> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.listaRezultat = objects;
        this.inflater = inflater;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable android.view.View convertView, @NonNull ViewGroup parent) {
        View view = inflater.inflate(resource, parent, false);
        Clasament clasament = listaRezultat.get(position);
        if (clasament != null) {
            adaugaTeam(view, clasament.getTeam());
            adaugaPozitie(view, clasament.getPozitie());
            adaugaMeciuriJucate(view, clasament.getMeciuriJucate());
            adaugaMeciuriCastigate(view, clasament.getMeciuriCastigate());
            adaugaMeciuriEgale(view, clasament.getMeciuriEgale());
            adaugaMeciuriPierdute(view, clasament.getMeciuriPierdute());
            adaugaGoluriDate(view, clasament.getGoluriPentru());
            adaugaGolPrimite(view, clasament.getGoluriImpotriva());
            adaugaPunctaj(view, clasament.getPunctaj());
            ImageView imageView = view.findViewById(R.id.imageViewSteag);
            Glide.with(context).load(clasament.getPoza()).into(imageView);
        }
        return view;
    }
    private void adaugaTeam(View view, String team) {
        TextView textView = view.findViewById(R.id.textViewClasamentNumeEchipa);
        populateTextViewContent(textView, (team));
    }
    private void adaugaPozitie(View view, int pozitie) {
        TextView textView = view.findViewById(R.id.textViewClasamentPozitie);
        populateTextViewContent(textView, String.valueOf(pozitie));
    }
    private void adaugaMeciuriJucate(View view, int meciuriJucate) {
        TextView textView = view.findViewById(R.id.textViewClasamentJucate);
        populateTextViewContent(textView, String.valueOf(meciuriJucate));
    }
    private void adaugaMeciuriCastigate(View view, int meciuriCastigate) {
        TextView textView = view.findViewById(R.id.textViewMeciuriCastigate);
        populateTextViewContent(textView, String.valueOf(meciuriCastigate));
    }
    private void adaugaMeciuriEgale(View view, int meciuriEgale) {
        TextView textView = view.findViewById(R.id.textViewClasamentMeciuriEgale);
        populateTextViewContent(textView, String.valueOf(meciuriEgale));
    }
    private void adaugaMeciuriPierdute(View view, int meciuriPierdute) {
        TextView textView = view.findViewById(R.id.textViewClasamentMeciuriPierdute);
        populateTextViewContent(textView, String.valueOf(meciuriPierdute));
    }
    private void adaugaGoluriDate(View view, int goluriDate) {
        TextView textView = view.findViewById(R.id.textViewClasamentGoluriPentru);
        populateTextViewContent(textView, String.valueOf(goluriDate));
    }
    private void adaugaGolPrimite(View view, int goluriPrimite) {
        TextView textView = view.findViewById(R.id.textViewGoluriPrimite);
        populateTextViewContent(textView, String.valueOf(goluriPrimite));
    }
    private void adaugaPunctaj(View view, int punctaj) {
        TextView textView = view.findViewById(R.id.textViewClasamentPunctaj);
        populateTextViewContent(textView, String.valueOf(punctaj));
    }

    private void populateTextViewContent(TextView textView, String value) {
        if (value != null && !value.trim().isEmpty()) {
            textView.setText(value);
        }
    }

}