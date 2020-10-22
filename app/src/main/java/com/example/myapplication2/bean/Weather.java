package com.example.myapplication2.bean;

public class Weather {


    /**
     * date : 2020-10-22
     * temperature : 7/20℃
     * weather : 晴转多云
     * direct : 东北风
     */

    private String date;
    private String temperature;
    private String weather;
    private String direct;

    public Weather(String date, String temperature, String weather, String direct) {
        this.date = date;
        this.temperature = temperature;
        this.weather = weather;
        this.direct = direct;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }
}
