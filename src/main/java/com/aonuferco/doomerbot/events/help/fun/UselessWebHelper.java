package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * UselessWebHelper
 * Useless Web helper for uselessweb command.
 */
public class UselessWebHelper implements HelperAbstraction {
    private static UselessWebHelper uselessWebHelper;

    private UselessWebHelper() {
    }

    public static UselessWebHelper getInstance() {
        if (uselessWebHelper == null)
            uselessWebHelper = new UselessWebHelper();

        return uselessWebHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/uselessweb Help**_");
        builder.setDescription("Returns a random useless (or not?) website.");
        builder.addField("Usage", "/uselessweb", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
