package com.aonuferco.doomerbot.events.fun;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.google.gson.Gson;
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
 * UselessFact
 * Returns a random useless fact.
 */
public class UselessFact implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(UselessFact.class);

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        try {
            event.reply(getFact()).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Returns a random fact as a string. */
    private String getFact() throws IOException {
        StringBuilder url = new StringBuilder();
        URL apiURL = new URL("https://uselessfacts.jsph.pl/random.json?language=en");
        String str;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()))) {
            while ((str = in.readLine()) != null) {
                url.append(str);
            }
        }

        Map jsonJavaRootObject = new Gson().fromJson(url.toString(), Map.class);

        return jsonJavaRootObject.get("text").toString();
    }
}
