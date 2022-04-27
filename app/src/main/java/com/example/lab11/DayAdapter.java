package com.example.lab11;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder>{

    private ArrayList<Day> dayList;

    public DayAdapter() {this.dayList = new ArrayList<>();}

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        Day day = dayList.get(position);
        holder.info.setText(day.getInfo());
        holder.currentTemperature.setText(day.getCurrentTemp());
        holder.feelsTemperature.setText(day.getCurrentTemp());
        holder.humidity.setText(day.getHumidity());
        holder.speed.setText(day.getSpeed());
        holder.sunrise.setText(day.getSunrise());
        holder.sunset.setText(day.getSunset());
        holder.description.setText(day.getDescription());
        Picasso.get().load(day.getIcon()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

    public void setDayList(final ArrayList<Day> dayList) {this.dayList = dayList; notifyDataSetChanged();}

    static class DayViewHolder extends RecyclerView.ViewHolder{
        private final TextInputEditText info;
        private final TextInputEditText currentTemperature;
        private final TextInputEditText feelsTemperature;
        private final TextInputEditText description;
        private final TextInputEditText humidity;
        private final TextInputEditText speed;
        private final TextInputEditText sunrise;
        private final TextInputEditText sunset;
        private final ImageView icon;

        public DayViewHolder(@NonNull View v){
            super(v);
            info = v.findViewById(R.id.txtWeatherInfo);
            currentTemperature = v.findViewById(R.id.txtCurrentTemp);
            feelsTemperature = v.findViewById(R.id.txtFeelsLike);
            description = v.findViewById(R.id.txtDescription);
            humidity = v.findViewById(R.id.txtHumidity);
            speed = v.findViewById(R.id.txtWindSpeed);
            sunrise = v.findViewById(R.id.txtSunrise);
            sunset = v.findViewById(R.id.txtSunset);
            icon = v.findViewById(R.id.imgWeatherIcon);
        }
    }
}
