import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&appid=91dd9d10099b57b7603ccc9d79dedca4&units=metric");

        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }

        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");

        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

        JSONObject wind = object.getJSONObject("wind");
        model.setWindSpeed(wind.getDouble("speed"));


        return  "Город: " + model.getName() + "\n" +
                "Температура воздуха: " + model.getTemp() + "° \n" +
                "Скорость ветра: " + model.getWindSpeed() + "м/с. \n" +
                "Влажность: " + model.getHumidity() + " г/м³";

    }
}
