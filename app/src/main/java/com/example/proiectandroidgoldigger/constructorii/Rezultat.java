package com.example.proiectandroidgoldigger.constructorii;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "meciuri",
        foreignKeys = @ForeignKey(entity = Clasament.class, parentColumns = "team", childColumns = "teamNou")
)
public class Rezultat implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo (name="teamNou")
    private String teamNou;
    @Ignore
    List<Detalii> goals1;
    @Ignore
    List<Detalii> goals2;

    @ColumnInfo(name = "etapa")
    private String etapa;
    @ColumnInfo(name = "data")
    private String data;
    @ColumnInfo(name = "ora")
    private String ora;
    @ColumnInfo(name = "stadion")
    private String stadion;
    @ColumnInfo(name = "echipa1")
    private String echipa1;
    @ColumnInfo(name = "scor1")
    private int scor1;
    @ColumnInfo(name = "scor2")
    private int scor2;
    @ColumnInfo(name = "echipa2")
    private String echipa2;

    public Rezultat(long id ,String teamNou,String etapa, String data, String ora,
                    String stadion, String echipa1, int scor1, int scor2, String echipa2) {
        this.id=id;
        this.teamNou=teamNou;
        this.etapa = etapa;
        this.data = data;
        this.ora = ora;
        this.stadion = stadion;
        this.echipa1 = echipa1;
        this.scor1 = scor1;
        this.scor2 = scor2;
        this.echipa2 = echipa2;
    }
    @Ignore
    public Rezultat(List<Detalii> goals1, List<Detalii> goals2, String etapa,
                    String data, String ora, String stadion, String echipa1, int scor1, int scor2, String echipa2) {
        this.goals1 = goals1;
        this.goals2 = goals2;
        this.etapa = etapa;
        this.data = data;
        this.ora = ora;
        this.stadion = stadion;
        this.echipa1 = echipa1;
        this.scor1 = scor1;
        this.scor2 = scor2;
        this.echipa2 = echipa2;
    }

    public List<Detalii> getGoals1() {
        return goals1;
    }

    public void setGoals1(List<Detalii> goals1) {
        this.goals1 = goals1;
    }

    public List<Detalii> getGoals2() {
        return goals2;
    }

    public void setGoals2(List<Detalii> goals2) {
        this.goals2 = goals2;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

    public String getEchipa1() {
        return echipa1;
    }

    public void setEchipa1(String echipa1) {
        this.echipa1 = echipa1;
    }

    public int getScor1() {
        return scor1;
    }

    public void setScor1(int scor1) {
        this.scor1 = scor1;
    }
    public int getScor2() {
        return scor2;
    }

    public void setScor2(int scor2) {
        scor2 = scor2;
    }

    public String getEchipa2() {
        return echipa2;
    }

    public void setEchipa2(String echipa2) {
        this.echipa2 = echipa2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamNou() {
        return teamNou;
    }
    public void setTeamNou(String teamNou) {
        this.teamNou = teamNou;
    }
    @Override
    public String toString() {
        return "ConstructorRezultat{" +
                "grupa='" + etapa + '\'' +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", stadion='" + stadion + '\'' +
                ", echipa1='" + echipa1 + '\'' +
                ", Scor1='" + scor1 + '\'' +
                ", Scor2='" + scor1 + '\'' +
                ", echipa2='" + echipa2 + '\'' +
                '}';
    }

}