package com.aonuferco.doomerbot.events.utils;

import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Poll
 * Creates a poll with up to 10 choices,
 * selected by emoji reactions.
 */
public class Poll implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(Poll.class);
    /* Builder for Discord Embed description. */
    private final StringBuilder description = new StringBuilder();
    /* Map of emojis to use for Discord Embed description build. */
    private final Map<Integer, String> numMap = Stream.of(
            new Object[][]{
                    {1, ":one:"},
                    {2, ":two:"},
                    {3, ":three:"},
                    {4, ":four:"},
                    {5, ":five:"},
                    {6, ":six:"},
                    {7, ":seven:"},
                    {8, ":eight:"},
                    {9, ":nine:"},
                    {10, ":keycap_ten:"}
            }).collect(Collectors.toMap(data -> (Integer) data[0], data -> (String) data[1]));

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        List<OptionMapping> choicesArray = event.getOptions();

        /* Populate description with emojis for choices. */
        for (int i = 1; i < choicesArray.size(); i++) {
            description.append(numMap.get(i))
                    .append(" ").append(choicesArray.get(i).getAsString().trim())
                    .append("\n");
        }

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733205);
        builder.setTitle(event.getOption("question").getAsString());
        builder.setDescription(description);
        event.reply("Your poll was sent!").setEphemeral(true).queue();
        /* Sends a message to the channel and auto-reacts, depending on the choice amount. */
        event.getChannel().sendMessageEmbeds(builder.build()).queue(message -> {
            if (choicesArray.size() > 10) {
                for (int i = 1; i < 10; i++) {
                    message.addReaction(Emoji.fromUnicode("U+003" + i + " U+20E3")).queue();
                }
                message.addReaction(Emoji.fromUnicode("U+1F51F")).queue();
            } else {
                for (int i = 1; i < choicesArray.size(); i++) {
                    message.addReaction(Emoji.fromUnicode("U+003" + i + " U+20E3")).queue();
                }
            }
        });
        description.setLength(0);
    }
}
