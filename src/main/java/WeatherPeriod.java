import java.util.ArrayList;

public class WeatherPeriod {

    private String period;
    private ArrayList<String> events = new ArrayList<>();
    private String temperature;
    private String presure;
    private String wet;
    private ArrayList<String> wind = new ArrayList<>();

    public  WeatherPeriod(String weather){
        var data = weather.split(" ");
        period = data[0];

        for(int i = data.length-1; i > 8; i--)
            wind.addFirst(data[i]);

        wet = data[8];
        presure = data[7];
        temperature = data[6];
        for(int i = 6-1; i > 0; i--)
            events.addFirst(data[i]);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(period + " |");
        for (var event :
                events) {
            stringBuilder.append(event).append(" ");
        }
        stringBuilder.append("|");
        stringBuilder.append(temperature).append(" |");
        stringBuilder.append(presure).append(" |");
        stringBuilder.append(wet).append(" |");
        for (var str :
                wind) {
            stringBuilder.append(str).append(" ");
        }
        return stringBuilder.toString();
    }
}
