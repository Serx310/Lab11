package com.example.lab11.fragment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.lab11.Day;
import com.example.lab11.DayAdapter;
import com.example.lab11.R;
import com.koushikdutta.ion.Ion;

import java.util.ArrayList;

public class DayFragment extends Fragment {

    private DayViewModel mViewModel;

    private String city;
    DayAdapter dayAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.day_fragment, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.recycler_day);
        recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
        dayAdapter = new DayAdapter();
        recyclerView.setAdapter(dayAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            city = getArguments().getString("city");
            Log.i("MainFragment", city);
        }else Toast.makeText(getActivity(), "No city provided", Toast.LENGTH_SHORT).show();

        DayViewModel dayViewModel = new ViewModelProvider(this).get(DayViewModel.class);
        dayViewModel.getDayWeather(city);
        dayViewModel.getDayLiveData().observe(this, days -> dayAdapter.setDayList(days));
    }

}