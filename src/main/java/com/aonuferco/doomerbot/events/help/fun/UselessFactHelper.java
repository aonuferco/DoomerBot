package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * UselessFactHelper
 * Useless Fact helper for uselessfact command.
 */
public class UselessFactHelper implements HelperAbstraction {
    private static UselessFactHelper uselessFactHelper;

    private UselessFactHelper() {
    }

    public static UselessFactHelper getInstance() {
        if (uselessFactHelper == null)
            uselessFactHelper = new UselessFactHelper();

        return uselessFactHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/uselessfact Help**_");
        builder.setDescription("Returns a random useless (or not?) fact.");
        builder.addField("Usage", "/uselessfact", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
