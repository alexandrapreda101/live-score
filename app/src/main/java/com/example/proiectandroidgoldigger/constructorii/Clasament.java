package com.example.proiectandroidgoldigger.constructorii;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (tableName = "Clasament",indices = @Index(value = {"team"}, unique = true))
public class Clasament {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @NonNull
    @ColumnInfo(name="team")
    private String team;
    @ColumnInfo(name="grupa")
    private String grupa;
    @ColumnInfo(name="pozitie")
    private int pozitie;
    @ColumnInfo(name="meciuriJucate")
    private int meciuriJucate;
    @ColumnInfo(name="meciuriCastigate")
    private int meciuriCastigate;
    @ColumnInfo(name="meciuriEgale")
    private int meciuriEgale;
    @ColumnInfo(name="meciuriPierdute")
    private int meciuriPierdute;
    @ColumnInfo(name="goluriPentru")
    private int goluriPentru;
    @ColumnInfo(name="goluriImpotriva")
    private int goluriImpotriva;
    @ColumnInfo(name="punctaj")
    private int punctaj;
    @ColumnInfo(name="poza")
    private String poza;

    public Clasament(long id, String team, String grupa, int pozitie, int meciuriJucate,
                     int meciuriCastigate, int meciuriEgale, int meciuriPierdute, int goluriPentru, int goluriImpotriva, int punctaj,String poza) {
        this.poza=poza;
        this.id = id;
        this.team = team;
        this.grupa=grupa;
        this.pozitie = pozitie;
        this.meciuriJucate = meciuriJucate;
        this.meciuriCastigate = meciuriCastigate;
        this.meciuriEgale = meciuriEgale;
        this.meciuriPierdute = meciuriPierdute;
        this.goluriPentru = goluriPentru;
        this.goluriImpotriva = goluriImpotriva;
        this.punctaj = punctaj;
    }

    @Ignore
    public Clasament(String team,String grupa, int pozitie, int meciuriJucate, int meciuriCastigate,
                     int meciuriEgale, int meciuriPierdute, int goluriPentru, int goluriImpotriva, int punctaj,String poza) {
        this.poza=poza;
        this.grupa=grupa;
        this.team = team;
        this.pozitie = pozitie;
        this.meciuriJucate = meciuriJucate;
        this.meciuriCastigate = meciuriCastigate;
        this.meciuriEgale = meciuriEgale;
        this.meciuriPierdute = meciuriPierdute;
        this.goluriPentru = goluriPentru;
        this.goluriImpotriva = goluriImpotriva;
        this.punctaj = punctaj;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getGrupa() {
        return grupa;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }
    public int getPozitie() {
        return pozitie;
    }

    public void setPozitie(int pozitie) {
        this.pozitie = pozitie;
    }

    public int getMeciuriJucate() {
        return meciuriJucate;
    }

    public void setMeciuriJucate(int meciuriJucate) {
        this.meciuriJucate = meciuriJucate;
    }

    public int getMeciuriCastigate() {
        return meciuriCastigate;
    }

    public void setMeciuriCastigate(int meciuriCastigate) {
        this.meciuriCastigate = meciuriCastigate;
    }

    public int getMeciuriEgale() {
        return meciuriEgale;
    }

    public void setMeciuriEgale(int meciuriEgale) {
        this.meciuriEgale = meciuriEgale;
    }

    public int getMeciuriPierdute() {
        return meciuriPierdute;
    }

    public void setMeciuriPierdute(int meciuriPierdute) {
        this.meciuriPierdute = meciuriPierdute;
    }

    public int getGoluriPentru() {
        return goluriPentru;
    }

    public void setGoluriPentru(int goluriPentru) {
        this.goluriPentru = goluriPentru;
    }

    public int getGoluriImpotriva() {
        return goluriImpotriva;
    }

    public void setGoluriImpotriva(int goluriImpotriva) {
        this.goluriImpotriva = goluriImpotriva;
    }

    public int getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(int punctaj) {
        this.punctaj = punctaj;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPoza() {
        return poza;
    }

    public void setPoza(String poza) {
        this.poza = poza;
    }
}