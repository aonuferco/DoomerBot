package com.aonuferco.doomerbot.events.fun;

import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Random;

/**
 * Rater
 * Returns a random int value,
 * attributed to your look.
 */
public class Rater implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(Rater.class);

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        int randomInt = new Random().nextInt(100);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        builder.setTitle("Rating your look for today...");
        builder.setDescription("You look " + randomInt + "% sharp today");
        event.replyEmbeds(builder.build()).queue();
    }
}
