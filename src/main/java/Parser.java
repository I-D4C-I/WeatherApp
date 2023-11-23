import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Parser {

    private Document document;
    private Element tableWth;

    public Parser(String url) throws IOException{
        document = Jsoup.parse(new URL(url), 5000);
        tableWth =  document.select("table[class=wt]").first();
    }

    public static Document getHtml(String url) throws IOException {
        return Jsoup.parse(new URL(url), 5000);
    }

    public Elements getTimesOfDay(){
        return  tableWth.select("tr[valign=top]");
    }

    public ArrayList<String> getDatesFromDays()
    {
        var dates = new ArrayList<String>();
        var tableWth = document.select("table[class=wt]").first();
        var daysOfWeek = tableWth.select("tr[class=wth]");
        for (Element day : daysOfWeek){
           dates.add(getDateFromString(day.select("th[id=dt]").text()));
        }
        return dates;
    }

    public String getDateFromString(String date){
        var matcher = Pattern.compile("\\d{2}\\.\\d{2}").matcher(date);
        if(matcher.find())
            return matcher.group();
        return null;
    }

    public static ArrayList<String> getDayWeather(Elements timesOfDay, int index){
        ArrayList<String> weatherList = new ArrayList<>();
        for (int i = index; i < index + 4; i++) {
            var weatherForDay = timesOfDay.get(i);
            var weather = weatherForDay.select("td");
            weatherList.add(weather.text());
            if(index + i < timesOfDay.size())
                if(timesOfDay.get(i + 1).child(0).text().equals("Утро"))
                    break;
        }
        return weatherList;
    }
}
