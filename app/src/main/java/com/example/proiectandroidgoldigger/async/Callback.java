package com.example.proiectandroidgoldigger.async;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
