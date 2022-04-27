package com.example.lab11.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab11.Day;
import com.example.lab11.R;
import com.example.lab11.Repository;

import java.util.ArrayList;

public class DayViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel

    private final Repository repository;
    private final MutableLiveData<ArrayList<Day>> dayLiveData;

    public DayViewModel(@NonNull Application application){
        super(application);
        repository = new Repository(application);
        dayLiveData = repository.getDayLiveData();
    }

    public MutableLiveData<ArrayList<Day>> getDayLiveData() {
        return dayLiveData;
    }

    public void getDayWeather(String city){repository.getDayWeather(city);}
}