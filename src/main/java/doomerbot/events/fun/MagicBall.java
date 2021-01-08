package doomerbot.events.fun;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class MagicBall {

    public static String getString() throws IOException {
        String result;
        URL oracle = new URL("https://customapi.aidenwallis.co.uk/api/v1/misc/8ball");
        BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
        result = in.readLine();
        in.close();

        return result;
    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args.length < 2){
            event.getChannel().sendMessage("Please ask a question after the command").queue();
        } else {
            try {
                event.getChannel().sendMessage("8ball says \uD83C\uDFB1: *" + getString() + "*").queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
