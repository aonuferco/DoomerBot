package com.aonuferco.doomerbot.events.utils;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Weather
 * Weather check by city and country code,
 * using OpenWeather API.
 */
public class Weather implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(Weather.class);
    private String description, city, country, icon;
    private int statusCode, temp, humidity, wind;

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        event.deferReply().queue();
        String location = event.getOption("location").getAsString();
        try {
            getWeatherData(location);
            if (statusCode == 200) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(0xc3e907);
                builder.setThumbnail("http://openweathermap.org/img/wn/" + icon + "@2x.png");
                builder.addField(city, temp + " °C\nHumidity: " + humidity + "%", true);
                builder.addField(country + " :flag_" + country.toLowerCase() + ":",
                        description + "\nWind: " + wind + " m/s", true);
                event.getHook().sendMessageEmbeds(builder.build()).queue();
            } else {
                event.getHook().sendMessage("City not found. Try format [city, CC], "
                        + "where CC is the two-letter country code.").queue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Retrieves the data from OpenWeather API, containing details about city weather. */
    private void getWeatherData(String tag) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URL dataURL = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + tag
                + "&units=metric&appid=" + Dotenv.configure().load().get("WEATHER_KEY"));
        String parsedJson;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(dataURL.openStream()))) {
            while ((parsedJson = in.readLine()) != null) {
                jsonString.append(parsedJson);
            }

            /* Response status code is mapped as "cod" on OpenWeather API. */
            JsonElement jsonCod = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                    get("cod");
            statusCode = jsonCod.getAsInt();

            if (statusCode == 200) {
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
        } catch (FileNotFoundException e) {
            statusCode = 404;
        }

    }
}
