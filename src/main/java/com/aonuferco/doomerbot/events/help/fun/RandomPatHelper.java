package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * RandomPatHelper
 * Random Pat helper for pat command.
 */
public class RandomPatHelper implements HelperAbstraction {
    private static RandomPatHelper randomPatHelper;

    private RandomPatHelper() {
    }

    public static RandomPatHelper getInstance() {
        if (randomPatHelper == null)
            randomPatHelper = new RandomPatHelper();

        return randomPatHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/pat Help**_");
        builder.setDescription("Returns a random anime pat gif.");
        builder.addField("Usage", "/pat", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
