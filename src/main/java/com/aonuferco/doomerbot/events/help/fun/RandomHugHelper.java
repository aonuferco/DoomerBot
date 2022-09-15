package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * RandomHugHelper
 * Random Hug helper for hug command.
 */
public class RandomHugHelper implements HelperAbstraction {
    private static RandomHugHelper randomHugHelper;

    private RandomHugHelper() {
    }

    public static RandomHugHelper getInstance() {
        if (randomHugHelper == null)
            randomHugHelper = new RandomHugHelper();

        return randomHugHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/hug Help**_");
        builder.setDescription("Returns a random anime hug gif.");
        builder.addField("Usage", "/hug", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
