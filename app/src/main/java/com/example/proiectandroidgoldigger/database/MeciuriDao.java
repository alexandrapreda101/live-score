package com.example.proiectandroidgoldigger.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroidgoldigger.constructorii.Clasament;


import java.util.List;

@Dao
public interface MeciuriDao {

    @Query("select * from Clasament")
    List<Clasament> getAll();

    @Query("select * from Clasament where grupa=(:grupa) ")
    List<Clasament> getSelected(String grupa);

    @Insert
    long insert(Clasament clasament);
}
