package doomerbot.events.fun;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Random;

public class Rater {

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        int randomInt = new Random().nextInt(100);

        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        builder.setTitle("Rating your look for today...");
        builder.setDescription("You look " + randomInt + "% sharp today");
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
