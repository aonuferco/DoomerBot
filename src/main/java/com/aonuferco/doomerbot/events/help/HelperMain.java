package com.aonuferco.doomerbot.events.help;

import com.aonuferco.doomerbot.EventManager;
import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * HelperMain
 * Main class for executing and mapping
 * helper instances.
 */
public class HelperMain implements EventAbstraction {
    private static HelperMain helperMainInstance;
    private final Logger logger = JDALogger.getLog(HelperMain.class);
    private final Map<String, HelperAbstraction> commands = HelperManager.getCommands();
    private final List<SlashCommandData> commandDataList = EventManager.getCommandData();

    private HelperMain() {
    }

    public static HelperMain getHelperMainInstance() {
        if (helperMainInstance == null)
            helperMainInstance = new HelperMain();

        return helperMainInstance;
    }

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        List<OptionMapping> optionsList = event.getOptions();

        /* If option list is not empty, meaning a command was specified. */
        if (!optionsList.isEmpty()) {
            String command = optionsList.get(0).getAsString();

            /* For unrecognized commands. */
            if (!commands.containsKey(command)) {
                event.reply("Command '" + command + "' not found.").queue();
                return;
            }
            commands.get(command).info(event);
            return;
        }
        event.replyEmbeds(generalInfo().build()).queue();
    }

    /* General info when for no specific command. */
    private EmbedBuilder generalInfo() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(2733920);
        builder.setTitle("_**DoomerBot Help**_");
        builder.setDescription(getEmbedDescription());
        builder.setFooter("Use /help <command> for detailed command view.");

        return builder;
    }

    /* Loading all the default descriptions for the commands from the EventManager. */
    private String getEmbedDescription() {
        StringBuilder description = new StringBuilder();
        for (SlashCommandData commandData : commandDataList) {
            description
                    .append("**/")
                    .append(commandData.getName())
                    .append("** - ")
                    .append(commandData.getDescription())
                    .append("\n");
        }
        return description.toString();
    }
}
