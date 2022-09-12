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
 * RandomMeme
 * Returns a random subreddit meme,
 * using Meme API.
 */
public class RandomMeme implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(RandomMeme.class);
    private String title, image, subreddit, src;

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        try {
            getMeme();
            builder.setTitle(title, src);
            builder.setImage(image);
            builder.setDescription("r/" + subreddit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.replyEmbeds(builder.build()).queue();
    }

    /* Returns a random subreddit meme title, image, source for further processing. */
    private void getMeme() throws IOException {
        URL apiURL = new URL("https://meme-api.herokuapp.com/gimme");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()))) {
            String tmp = in.readLine();
            Map jsonJavaRootObject = new Gson().fromJson(tmp, Map.class);
            title = jsonJavaRootObject.get("title").toString();
            image = jsonJavaRootObject.get("url").toString();
            subreddit = jsonJavaRootObject.get("subreddit").toString();
            src = jsonJavaRootObject.get("postLink").toString();
        }
    }
}
