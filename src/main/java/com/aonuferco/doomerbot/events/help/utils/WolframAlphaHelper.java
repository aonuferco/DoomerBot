package com.aonuferco.doomerbot.events.help.utils;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * WolframAlphaHelper
 * Wolfram Alpha helper for wolfram command.
 */
public class WolframAlphaHelper implements HelperAbstraction {
    private static WolframAlphaHelper wolframAlphaHelper;

    private WolframAlphaHelper() {
    }

    public static WolframAlphaHelper getInstance() {
        if (wolframAlphaHelper == null)
            wolframAlphaHelper = new WolframAlphaHelper();

        return wolframAlphaHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/wolfram Help**_");
        builder.setDescription("Returns an image result for the Wolfram Alpha query. " +
                "Image processing may take some time as it depends on result size.");
        builder.addField("Usage", "/wolfram [query]", false);
        builder.setFooter("Anything goes, be it 'what is love?' or '2 + 2 * 2'");
        event.replyEmbeds(builder.build()).queue();
    }
}
