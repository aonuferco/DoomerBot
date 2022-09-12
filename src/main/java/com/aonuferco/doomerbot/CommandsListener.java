package com.aonuferco.doomerbot;

import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

/**
 * CommandsListener
 * Maps all the commands and events to
 * be used in guilds.
 */
public class CommandsListener extends ListenerAdapter {

    private final Map<String, EventAbstraction> events = EventManager.getEvents();
    private final List<CommandData> commandData = EventManager.getCommandData();

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        for (Map.Entry<String, EventAbstraction> entry : events.entrySet()) {
            if (entry.getKey().equals(command)) {
                entry.getValue().execute(event);
            }
        }
    }

    /* Update commands for servers. */
    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
