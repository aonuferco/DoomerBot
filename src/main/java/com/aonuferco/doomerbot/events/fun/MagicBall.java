package com.aonuferco.doomerbot.events.fun;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * MagicBall
 * Returns a random response to a yes-no-maybe
 * question, using Eight Ball Delegator API.
 */
public class MagicBall implements EventAbstraction {
    private static MagicBall magicBallInstance;
    private final Logger logger = JDALogger.getLog(MagicBall.class);

    private MagicBall() {
    }

    public static MagicBall getMagicBallInstance() {
        if (magicBallInstance == null)
            magicBallInstance = new MagicBall();

        return magicBallInstance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        event.deferReply().queue();
        String query = event.getOption("query").getAsString();
        try {
            event.getHook().sendMessage("8ball says \uD83C\uDFB1: *" + getString() + "*").queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* Returns a random response from the Eight Ball API. Masks connection as a browser. */
    public String getString() throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URLConnection openConnection = new URL("https://8ball.delegator.com/magic/JSON/dummy")
                .openConnection();
        openConnection.addRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        String parsedJson;
        try (BufferedReader in = new BufferedReader(new InputStreamReader(openConnection.getInputStream()))) {
            while ((parsedJson = in.readLine()) != null) {
                jsonString.append(parsedJson);
            }
        }
        JsonElement jsonAnswer = JsonParser.parseString(jsonString.toString()).getAsJsonObject().
                get("magic").getAsJsonObject().get("answer");

        return jsonAnswer.getAsString();
    }
}
