package com.aonuferco.doomerbot.events.help.fun;

import com.aonuferco.doomerbot.events.help.HelperAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * MagicBallHelper
 * Magic Ball helper for 8ball command.
 */
public class MagicBallHelper implements HelperAbstraction {
    private static MagicBallHelper magicBallHelper;

    private MagicBallHelper() {
    }

    public static MagicBallHelper getInstance() {
        if (magicBallHelper == null)
            magicBallHelper = new MagicBallHelper();

        return magicBallHelper;
    }

    @Override
    public void info(@NotNull SlashCommandInteractionEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**/8ball Help**_");
        builder.setDescription("Returns an answer to your question. Generally, questions " +
                "asked to 8 Ball are the ones that can be replied with \nYes/No/Maybe.");
        builder.addField("Usage", "/8ball [question]", false);
        event.replyEmbeds(builder.build()).queue();
    }
}
