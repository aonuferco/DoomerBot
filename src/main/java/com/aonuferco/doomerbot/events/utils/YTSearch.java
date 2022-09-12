package com.aonuferco.doomerbot.events.utils;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * YTSearch
 * Sends a request to YouTube API and
 * returns the most relevant search result video.
 */
public class YTSearch implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(YTSearch.class);

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        event.deferReply().queue();
        String query = event.getOption("query").getAsString();
        try {
            event.getHook().sendMessage("\uD83C\uDFA5 | https://www.youtube.com/watch?v="
                    + getID(query)).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Returns a YouTube URL for the most relevant video for the given query. */
    private String getID(String tag) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URL ytUrl = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&order=relevance&q="
                + tag.replaceAll(" ", "%20")
                + "&key=" + Dotenv.configure().load().get("YT_KEY"));
        String parsedJson;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(ytUrl.openStream()))) {
            while ((parsedJson = in.readLine()) != null) {
                jsonString.append(parsedJson);
            }
        }

        JsonElement original = JsonParser.parseString(jsonString.toString())
                .getAsJsonObject().getAsJsonArray("items")
                .get(0).getAsJsonObject().get("id").getAsJsonObject().get("videoId");

        return original.getAsString();
    }
}
