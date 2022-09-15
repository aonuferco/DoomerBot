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
 * RandomHug
 * Returns a random hug gif,
 * using Some-Random-API.
 */
public class RandomHug implements EventAbstraction {
    private static RandomHug randomHugInstance;
    private final Logger logger = JDALogger.getLog(RandomHug.class);

    private RandomHug() {
    }

    public static RandomHug getRandomHugInstance() {
        if (randomHugInstance == null)
            randomHugInstance = new RandomHug();

        return randomHugInstance;
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

    /* Returns a random hug gif URL from Some-Random-API. */
    private String getImage() throws IOException {
        String url;
        URL apiURL = new URL("https://some-random-api.ml/animu/hug");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()))) {
            url = in.readLine();
        }

        Map jsonJavaRootObject = new Gson().fromJson(url, Map.class);

        return jsonJavaRootObject.get("link").toString();
    }
}
