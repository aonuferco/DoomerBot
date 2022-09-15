package com.aonuferco.doomerbot.events.help.utils;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * WeatherHelper
 * Weather helper for weather command.
 */
public class WeatherHelper implements HelperAbstraction {
    private static WeatherHelper weatherHelper;

    private WeatherHelper() {
    }

    public static WeatherHelper getInstance() {
        if (weatherHelper == null)
            weatherHelper = new WeatherHelper();

        return weatherHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/weather Help**_");
        builder.setDescription("Returns a weather report for the specified city. " +
                "Information includes temperature, humidity and wind speed.");
        builder.addField("Usage", "/weather [city] OR [city, CC]", false);
        builder.setFooter("CC - two letter country code. e.g. FR, US");
        event.replyEmbeds(builder.build()).queue();
    }
}
