package com.aonuferco.doomerbot.events;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import javax.annotation.Nonnull;

/**
 * EventAbstraction
 * Used for general method call via
 * polymorphism.
 */
public interface EventAbstraction {
    void execute(@Nonnull SlashCommandInteractionEvent event);
}
