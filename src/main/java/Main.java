import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        try {
            var url = "http://www.nepogoda.ru/russia/barnaul/";
            var parser = new Parser(url);
            ArrayList<DayWeather> week = new ArrayList<>();

            for (var date :
                    parser.getDatesFromDays()) {
                week.add(new DayWeather(date));
            }

            int index = 0;
            var timesOfDay = parser.getTimesOfDay();
            for (DayWeather dayWeather : week) {
                var weather = Parser.getDayWeather(timesOfDay, index);
                dayWeather.setWeather(weather);
                index += weather.size();
                if (index >= timesOfDay.size())
                    break;
            }

            for (var day : week) {
                System.out.println(day.getDate() + "| Явления | Температура | Давлениe | Влажность | Ветер");
                for (var weather:
                     day.WeatherPeriods)
                {
                    System.out.println(weather.toString());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
