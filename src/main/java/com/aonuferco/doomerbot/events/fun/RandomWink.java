package com.aonuferco.doomerbot.events.fun;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.google.gson.Gson;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

/**
 * RandomWink
 * Returns a random wink gif,
 * using Some-Random-API.
 */
public class RandomWink implements EventAbstraction {
    private static RandomWink randomWinkInstance;
    private final Logger logger = JDALogger.getLog(RandomWink.class);

    private RandomWink() {
    }

    public static RandomWink getRandomWinkInstance() {
        if (randomWinkInstance == null)
            randomWinkInstance = new RandomWink();

        return randomWinkInstance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        try {
            builder.setImage(getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.replyEmbeds(builder.build()).queue();
    }

    /* Returns a random wink gif URL from Some-Random-API. */
    private String getImage() throws IOException {
        String url;
        URL apiURL = new URL("https://some-random-api.ml/animu/wink");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()))) {
            url = in.readLine();
        }

        Map jsonJavaRootObject = new Gson().fromJson(url, Map.class);

        return jsonJavaRootObject.get("link").toString();
    }
}
