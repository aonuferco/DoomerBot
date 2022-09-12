package com.aonuferco.doomerbot;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;

/**
 * DoomerBot
 * Main application starter for DoomerBot.
 *
 * @author Alexandr Onuferco
 */
public class DoomerBotMain {

    public static void main(String[] args) throws LoginException {
        JDABuilder builder = JDABuilder.createDefault(Dotenv
                .configure().load().get("DISCORD_TOKEN"));
        builder.setActivity(Activity.watching(" you fail miserably"));
        builder.addEventListeners((new CommandsListener()));
        builder.build();
    }
}
