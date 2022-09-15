package com.aonuferco.doomerbot.events.help;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * HelperAbstraction
 * Used for general method call via
 * polymorphism.
 */
public interface HelperAbstraction {
    void info(@NotNull SlashCommandInteractionEvent event);
}
