package com.example.lab11;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.koushikdutta.ion.Ion;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Repository {

    private static final String API_KEY = "da08ab2145f46a18351d721ddef1df86";
    private static final String URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s";
    private final Application application;
    private final MutableLiveData<ArrayList<Day>> dayLiveData;
    private final ArrayList<Day> arrayList = new ArrayList<>();

    public Repository(Application application) {
        this.application = application;
        dayLiveData = new MutableLiveData<>();
    }

    public void getDayWeather(String city){
        Ion.with(application).load(String.format(URL, city, API_KEY)).asJsonObject()
                .setCallback((e, result) -> {
                    Log.i("getDayWeather", result.getAsJsonPrimitive("cod").toString());
                    parseResults(result);
                });
    }



    private void parseResults(JsonObject result) {
        JsonObject details = (JsonObject) result.getAsJsonArray("weather").get(0);
        JsonObject main = result.getAsJsonObject("main");
        JsonObject sys = result.getAsJsonObject("sys");
        JsonObject wind = result.getAsJsonObject("wind");
        JsonPrimitive date = result.getAsJsonPrimitive("dt");

        String temp = convertTemp(main.get("temp").toString());
        String feels = convertTemp(main.get("feels_like").toString());
        String humidity = main.get("humidity").toString();
        String speed = convertTemp(wind.get("speed").toString());
        String description = String.valueOf(details.get("description")).toUpperCase(Locale.getDefault());
        String ic = details.get("icon").toString();
        String icon = "https://openweathermap.org/img/wn/"+removeAbles(ic)+".png";
        long updated = Long.parseLong(String.valueOf(date.getAsLong()));
        long sunrise = Long.parseLong(sys.get("sunrise").toString());
        long sunset = Long.parseLong(sys.get("sunset").toString());

        Day day = new Day(convertTime(updated), temp, feels, removeAbles(description)
                ,humidity, speed, convertTime(sunrise), convertTime(sunset), icon);
        arrayList.add(day);
        dayLiveData.setValue(arrayList);
    }

    public String convertTime(long time){
        DateFormat dt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault());
        return dt.format(new Date(time*1000));
    }

    public String convertTemp(String temp){
        BigDecimal value = new BigDecimal(temp);
        value = value.setScale(0, RoundingMode.HALF_UP);
        return String.valueOf(value);
    }

    public String removeAbles(String text){
        return text.substring(1, text.length()-1);
    }

    public MutableLiveData<ArrayList<Day>> getDayLiveData() {
        return dayLiveData;
    }
}
