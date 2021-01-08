package doomerbot.events.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Weather {

    private static String description, city, country, icon;
    private static int cod, temp, humidity, wind;


    private static void getWeatherData(String tag) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        try {
            URL dataURL = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + tag
                    + "&units=metric&appid=weathermap-appid-here");
            BufferedReader in = new BufferedReader(new InputStreamReader(dataURL.openStream()));
            String str;
            while ((str = in.readLine()) != null) {
                jsonString.append(str);
            }
            in.close();

            JsonElement jsonCod = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                    get("cod");
            cod = jsonCod.getAsInt();

            if (cod == 200) {
                JsonElement jsonDesc = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        getAsJsonArray("weather").get(0).getAsJsonObject().get("main");
                description = jsonDesc.getAsString();
                JsonElement jsonCity = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        get("name");
                city = jsonCity.getAsString();
                JsonElement jsonCountry = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        get("sys").getAsJsonObject().get("country");
                country = jsonCountry.getAsString();
                JsonElement jsonIcon = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        getAsJsonArray("weather").get(0).getAsJsonObject().get("icon");
                icon = jsonIcon.getAsString();
                JsonElement jsonTemp = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        get("main").getAsJsonObject().get("temp");
                temp = jsonTemp.getAsInt();
                JsonElement jsonHumidity = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        get("main").getAsJsonObject().get("humidity");
                humidity = jsonHumidity.getAsInt();
                JsonElement jsonWind = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                        get("wind").getAsJsonObject().get("speed");
                wind = jsonWind.getAsInt();
            }
        } catch (FileNotFoundException e) { cod = 404; }

    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().toLowerCase().split("!weather");
        if (args.length < 2) {
            event.getChannel().sendMessage("Please enter a city to search").queue();
        } else {
            String tag = args[1].trim().replace(" ", "%20");
            try {
                getWeatherData(tag);
                if (cod == 200) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setColor(0xc3e907);
                    builder.setThumbnail("http://openweathermap.org/img/wn/" + icon + "@2x.png");
                    builder.addField(city, temp + " °C\nHumidity: " + humidity + "%", true);
                    builder.addField(country + " :flag_" + country.toLowerCase() + ":",
                            description + "\nWind: " + wind + " m/s",  true);
                    event.getChannel().sendMessage(builder.build()).queue();
                } else {
                    event.getChannel().sendMessage("City not found. Try format [city, CC], " +
                            "where CC is the two-letter country code.").queue();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
