import java.util.ArrayList;

public class DayWeather {
    private final String date;
    public ArrayList<WeatherPeriod> WeatherPeriods;

    public String getDate(){
        return  date;
    }

    public DayWeather(String date){
        this.date = date;
    }

    public void setWeather(ArrayList<String> weather){
        WeatherPeriods = new ArrayList<>();
        for (var period :
                weather) {
            WeatherPeriods.add(new WeatherPeriod(period));
        }
    }
}
