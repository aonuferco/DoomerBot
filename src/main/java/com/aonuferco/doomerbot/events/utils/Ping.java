package com.aonuferco.doomerbot.events.utils;

import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.slf4j.Logger;

import javax.annotation.Nonnull;
import java.time.temporal.ChronoField;

/**
 * Ping
 * Network response check.
 */
public class Ping implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(Ping.class);

    @Override
    public void execute(@Nonnull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        long ping = event.getTimeCreated().getLong(ChronoField.MILLI_OF_SECOND);
        event.reply("🏓 Ping: " + ping + " ms").queue();
    }
}
