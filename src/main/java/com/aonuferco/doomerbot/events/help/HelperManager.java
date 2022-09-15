package com.aonuferco.doomerbot.events.help;

import com.aonuferco.doomerbot.events.help.fun.*;
import com.aonuferco.doomerbot.events.help.utils.*;

import java.util.HashMap;
import java.util.Map;

/**
 * HelperManager
 * Mapper for helper instances.
 */
public class HelperManager {
    private static final Map<String, HelperAbstraction> commands;

    static {
        commands = new HashMap<>();

        commands.put("ping", PingHelper.getInstance());
        commands.put("poll", PollHelper.getInstance());
        commands.put("weather", WeatherHelper.getInstance());
        commands.put("wolfram", WolframAlphaHelper.getInstance());
        commands.put("yt", YTSearchHelper.getInstance());
        commands.put("8ball", MagicBallHelper.getInstance());
        commands.put("hug", RandomHugHelper.getInstance());
        commands.put("meme", RandomMemeHelper.getInstance());
        commands.put("pat", RandomPatHelper.getInstance());
        commands.put("wink", RandomWinkHelper.getInstance());
        commands.put("rate", RaterHelper.getInstance());
        commands.put("uselessfact", UselessFactHelper.getInstance());
        commands.put("uselessweb", UselessWebHelper.getInstance());
    }

    public static Map<String, HelperAbstraction> getCommands() {
        return commands;
    }
}
