package com.aonuferco.doomerbot.events.help.utils;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * PingHelper
 * Ping helper for ping command.
 */
public class PingHelper implements HelperAbstraction {
    private static PingHelper pingHelper;

    private PingHelper() {
    }

    public static PingHelper getInstance() {
        if (pingHelper == null)
            pingHelper = new PingHelper();

        return pingHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/ping Help**_");
        builder.setDescription("Displays the network delay between " +
                "the communication of the server with the bot.");
        builder.addField("Usage", "/ping", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
