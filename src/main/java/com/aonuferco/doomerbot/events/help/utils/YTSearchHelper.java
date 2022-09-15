package com.aonuferco.doomerbot.events.help.utils;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * YTSearchHelper
 * YTSearch helper for yt command.
 */
public class YTSearchHelper implements HelperAbstraction {
    private static YTSearchHelper ytSearchHelper;

    private YTSearchHelper() {
    }

    public static YTSearchHelper getInstance() {
        if (ytSearchHelper == null)
            ytSearchHelper = new YTSearchHelper();

        return ytSearchHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/yt Help**_");
        builder.setDescription("Returns the most relevant YouTube video for your query.");
        builder.addField("Usage", "/yt [query]", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
