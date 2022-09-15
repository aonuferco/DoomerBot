package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * RandomWinkHelper
 * Random Wink helper for wink command.
 */
public class RandomWinkHelper implements HelperAbstraction {
    private static RandomWinkHelper randomWinkHelper;

    private RandomWinkHelper() {
    }

    public static RandomWinkHelper getInstance() {
        if (randomWinkHelper == null)
            randomWinkHelper = new RandomWinkHelper();

        return randomWinkHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/wink Help**_");
        builder.setDescription("Returns a random anime wink gif.");
        builder.addField("Usage", "/wink", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
