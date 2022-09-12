package com.aonuferco.doomerbot.events.utils;

import com.aonuferco.doomerbot.events.EventAbstraction;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

/**
 * WolframAlpha
 * Binds a picture result from a query,
 * sent to the WolframAlpha API.
 */
public class WolframAlpha implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(WolframAlpha.class);

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        event.deferReply().queue();
        String query = event.getOption("query").getAsString();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setImage("http://api.wolframalpha.com/v1/simple?appid="
                + Dotenv.configure().load().get("WOLFRAM_KEY") + "&i="
                + query.trim().replaceAll(" ", "%20")
                + "&background=36393f&foreground=white&units=metric");
        event.getHook().sendMessageEmbeds(builder.build()).queue();
    }
}
