package com.aonuferco.doomerbot;

import com.aonuferco.doomerbot.events.EventAbstraction;
import com.aonuferco.doomerbot.events.fun.*;
import com.aonuferco.doomerbot.events.utils.*;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * EventManager
 * Responsible for creation of all events and
 * commands attributed to them.
 */
public class EventManager {
    private static final Map<String, EventAbstraction> events;
    private static final List<CommandData> commandData;

    static {
        events = new HashMap<>();
        commandData = new ArrayList<>();

        events.put("ping", new Ping());
        events.put("hug", new RandomHug());
        events.put("meme", new RandomMeme());
        events.put("pat", new RandomPat());
        events.put("wink", new RandomWink());
        events.put("rate", new Rater());
        events.put("uselessfact", new UselessFact());
        events.put("uselessweb", new UselessWeb());

        events.put("yt", new YTSearch());
        events.put("weather", new Weather());
        events.put("wolfram", new WolframAlpha());
        events.put("8ball", new MagicBall());
        events.put("poll", new Poll());

        commandData.add(Commands.slash("ping", "ping network test"));
        commandData.add(Commands.slash("hug", "random hug gif"));
        commandData.add(Commands.slash("meme", "random subreddit meme"));
        commandData.add(Commands.slash("pat", "random pat gif"));
        commandData.add(Commands.slash("wink", "random wink gif"));
        commandData.add(Commands.slash("rate", "how good you look today"));
        commandData.add(Commands.slash("uselessfact", "random useless fact"));
        commandData.add(Commands.slash("uselessweb", "random interesting website"));

        commandData.add(Commands.slash("yt", "search something on YouTube")
                .addOption(OptionType.STRING, "query", "the query to search", true));
        commandData.add(Commands.slash("weather", "look up the weather")
                .addOption(OptionType.STRING, "location", "e.g. London", true));
        commandData.add(Commands.slash("wolfram", "search up on wolfram")
                .addOption(OptionType.STRING, "query", "e.g. Einstein curve", true));
        commandData.add(Commands.slash("8ball", "ask the ball a question")
                .addOption(OptionType.STRING, "query", "anything you want to ask", true));
        commandData.add(Commands.slash("poll", "poll with up to 10 choices")
                .addOption(OptionType.STRING, "question", "poll question", true)
                .addOption(OptionType.STRING, "1st", "first choice", true)
                .addOption(OptionType.STRING, "2nd", "second choice", true)
                .addOption(OptionType.STRING, "3rd", "third choice", false)
                .addOption(OptionType.STRING, "4th", "forth choice", false)
                .addOption(OptionType.STRING, "5th", "fifth choice", false)
                .addOption(OptionType.STRING, "6th", "sixth choice", false)
                .addOption(OptionType.STRING, "7th", "seventh choice", false)
                .addOption(OptionType.STRING, "8th", "eighth choice", false)
                .addOption(OptionType.STRING, "9th", "ninth choice", false)
                .addOption(OptionType.STRING, "10th", "tenth choice", false));
    }

    public static Map<String, EventAbstraction> getEvents() {
        return events;
    }

    public static List<CommandData> getCommandData() {
        return commandData;
    }
}
