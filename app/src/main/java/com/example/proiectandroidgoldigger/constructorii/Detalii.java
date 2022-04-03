package com.example.proiectandroidgoldigger.constructorii;

import java.io.Serializable;

public class Detalii implements Serializable {

    private int minut;
    private String numeJucator;
    private int golEchipa1;
    private int golEchipa2;

    public Detalii(int minut, String numeJucator, int golEchipa1, int golEchipa2) {
        this.minut = minut;
        this.numeJucator = numeJucator;
        this.golEchipa1 = golEchipa1;
        this.golEchipa2 = golEchipa2;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }


    public String getNumeJucator() {
        return numeJucator;
    }

    public void setNumeJucator(String numeJucator) {
        this.numeJucator = numeJucator;
    }

    public int getGolEchipa1() {
        return golEchipa1;
    }

    public void setGolEchipa1(int golEchipa1) {
        this.golEchipa1 = golEchipa1;
    }

    public int getGolEchipa2() {
        return golEchipa2;
    }

    public void setGolEchipa2(int golEchipa2) {
        this.golEchipa2 = golEchipa2;
    }

    @Override
    public String toString() {
        return "ConstructorDetalii{" +
                ", minut='" + minut + '\'' +
                ", numeJucator='" + numeJucator + '\'' +
                ", golEchipa1='" + golEchipa1 + '\'' +
                ", golEchipa2='" + golEchipa2 + '\'' +
                '}';
    }
}
