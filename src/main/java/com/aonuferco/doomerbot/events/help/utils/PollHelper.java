package com.aonuferco.doomerbot.events.help.utils;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * PollHelper
 * Poll helper for poll command.
 */
public class PollHelper implements HelperAbstraction {
    private static PollHelper pollHelper;

    private PollHelper() {
    }

    public static PollHelper getInstance() {
        if (pollHelper == null)
            pollHelper = new PollHelper();

        return pollHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/poll Help**_");
        builder.setDescription("Creates a poll for a given query " +
                "with up to 10 choices. Upvote for the preferred " +
                "choice by reacting. The question and first two " +
                "choices are required, rest are optional.");
        builder.addField("Usage", "/poll [question] [choice_1] [choice_2] ... <choice_10>", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
