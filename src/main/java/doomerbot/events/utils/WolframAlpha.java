package doomerbot.events.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;

public class WolframAlpha {

    public static void execute(@Nonnull  GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().toLowerCase().split("!wolfram");
        if (args.length < 2) {
            event.getChannel().sendMessage("Please enter a valid query.").queue();
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setImage("http://api.wolframalpha.com/v1/simple?appid=wolfram-appid-here&i=" +
                    args[1].trim().replaceAll(" ", "%20") + "&background=36393f&foreground=white&units=metric");
            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

}
