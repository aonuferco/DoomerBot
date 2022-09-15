package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * RaterHelper
 * Rater helper for rate command.
 */
public class RaterHelper implements HelperAbstraction {
    private static RaterHelper raterHelper;

    private RaterHelper() {
    }

    public static RaterHelper getInstance() {
        if (raterHelper == null)
            raterHelper = new RaterHelper();

        return raterHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/rate Help**_");
        builder.setDescription("Rates your look on a scale from 0 to 100.");
        builder.addField("Usage", "/rate", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
