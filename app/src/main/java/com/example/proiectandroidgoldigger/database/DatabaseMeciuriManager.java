package com.example.proiectandroidgoldigger.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.proiectandroidgoldigger.MeciuriDinClasament;
import com.example.proiectandroidgoldigger.constructorii.Clasament;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;

@Database(entities = {Clasament.class, Rezultat.class}, exportSchema = false, version = 4)
public abstract class DatabaseMeciuriManager extends RoomDatabase {
    private static final String DAM_DB = "livescore_db";

    private static DatabaseMeciuriManager databaseManager;

    public static DatabaseMeciuriManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseMeciuriManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseMeciuriManager.class, DAM_DB)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseManager;
    }

    public abstract MeciuriDao getMeciuriDao();
    public abstract MeciuriDinClasamentDao getMeciuriDinClasamentDao();
}
