package doomerbot.events.utils;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.time.temporal.ChronoField;

public class Ping {

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        long ping = event.getMessage().getTimeCreated().getLong(ChronoField.MILLI_OF_SECOND);
        event.getChannel().sendMessage("🏓 Ping: " + ping +  " ms").queue();

        if(event.getAuthor().isBot())
            return;
    }
}
