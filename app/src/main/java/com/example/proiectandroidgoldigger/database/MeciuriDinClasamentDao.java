package com.example.proiectandroidgoldigger.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroidgoldigger.constructorii.Rezultat;

import java.util.List;

@Dao
public interface MeciuriDinClasamentDao {

    @Query("select * from meciuri where  echipa1=(:team) or echipa2=(:team) ")
    List<Rezultat> getSelected(String team);

    @Insert
    long insert(Rezultat rezultat);

    @Query("delete from meciuri")
    int delete();
}