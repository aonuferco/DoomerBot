package doomerbot.main;

import doomerbot.events.fun.*;
import doomerbot.events.utils.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class CommandsEvent extends ListenerAdapter {

    private static List<String> keys = Arrays.asList("!ping", "!hug", "!meme", "!pat", "!wink", "!ratemuhlooks", "!fact", "!uselessweb");
    private static List<Class<?>> classes = Arrays.asList(Ping.class, RandomHug.class, RandomMeme.class,
            RandomPat.class, RandomWink.class, Rater.class, UselessFact.class, UselessWeb.class);

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        if(StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!gif"))
            RandomGif.execute(event);
        else if (StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!poll"))
            Poll.execute(event);
        else if (StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!weather"))
            Weather.execute(event);
        else if (StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!wolfram"))
            WolframAlpha.execute(event);
        else if(StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!yt"))
            YTSearch.execute(event);
        else if(StringUtils.startsWithIgnoreCase(event.getMessage().getContentRaw(), "!8ball"))
            MagicBall.execute(event);
        else if(keys.contains(event.getMessage().getContentRaw().toLowerCase())) {
            try {
                classes.get(keys.indexOf(event.getMessage().getContentRaw().toLowerCase()))
                        .getDeclaredMethod("execute", GuildMessageReceivedEvent.class).invoke(null, event);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
