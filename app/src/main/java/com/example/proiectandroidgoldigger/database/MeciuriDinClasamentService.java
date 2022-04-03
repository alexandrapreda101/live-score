package com.example.proiectandroidgoldigger.database;

import android.content.Context;

import com.example.proiectandroidgoldigger.async.AsyncTaskManager;
import com.example.proiectandroidgoldigger.async.Callback;
import com.example.proiectandroidgoldigger.constructorii.Rezultat;

import java.util.List;
import java.util.concurrent.Callable;

public class MeciuriDinClasamentService {
    private final MeciuriDinClasamentDao meciuriDinClasamentDao;
    private final AsyncTaskManager taskRunner;

    public MeciuriDinClasamentService(Context context) {
        meciuriDinClasamentDao = DatabaseMeciuriManager.getInstance(context).getMeciuriDinClasamentDao();
        taskRunner = new AsyncTaskManager();
    }

    public void getSelected(Callback<List<Rezultat>> callback, final String team) {
        Callable<List<Rezultat>> callable = new Callable<List<Rezultat>>() {
            @Override
            public List<Rezultat> call() {

                return meciuriDinClasamentDao.getSelected(team);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void insert(final Callback<Rezultat> callback, final Rezultat rezultat) {
        Callable<Rezultat> callable = new Callable<Rezultat>() {
            @Override
            public Rezultat call() {
                if (rezultat == null) {
                    return null;
                }
                long id = meciuriDinClasamentDao.insert(rezultat);
                if (id == -1) {
                    return null;
                }
                rezultat.setId(id);
                return rezultat;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void delete(Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                return meciuriDinClasamentDao.delete();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}