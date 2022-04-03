package com.example.proiectandroidgoldigger.database;

import android.content.Context;

import com.example.proiectandroidgoldigger.async.AsyncTaskManager;
import com.example.proiectandroidgoldigger.async.Callback;
import com.example.proiectandroidgoldigger.constructorii.Clasament;

import java.util.List;
import java.util.concurrent.Callable;


public class ClasamentService {
    private final MeciuriDao meciuriDao;
    private final AsyncTaskManager taskRunner;

    public ClasamentService(Context context) {
        meciuriDao = DatabaseMeciuriManager.getInstance(context).getMeciuriDao();
        taskRunner = new AsyncTaskManager();
    }
    public void getAll(Callback<List<Clasament>> callback) {
        Callable<List<Clasament>> callable = new Callable<List<Clasament>>() {
            @Override
            public List<Clasament> call() {
                return meciuriDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
    public void getSelected(Callback<List<Clasament>> callback, final String grupa) {
        Callable<List<Clasament>> callable = new Callable<List<Clasament>>() {
            @Override
            public List<Clasament> call() {
                return meciuriDao.getSelected(grupa);
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(Callback<Clasament> callback, final Clasament clasament) {
        Callable<Clasament> callable = new Callable<Clasament>() {
            @Override
            public Clasament call() {
                if (clasament == null) {
                    return null;
                }
                long id = meciuriDao.insert(clasament);
                if (id == -1) {
                    return null;
                }
                clasament.setId(id);
                return clasament;
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
