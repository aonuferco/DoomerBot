package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * RandomMemeHelper
 * Random Meme helper for meme command.
 */
public class RandomMemeHelper implements HelperAbstraction {
    private static RandomMemeHelper randomMemeHelper;

    private RandomMemeHelper() {
    }

    public static RandomMemeHelper getInstance() {
        if (randomMemeHelper == null)
            randomMemeHelper = new RandomMemeHelper();

        return randomMemeHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/meme Help**_");
        builder.setDescription("Returns a random meme from a random subreddit.");
        builder.addField("Usage", "/meme", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
