package doomerbot.events.fun;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class RandomGif {

    private static String getGif(String tag) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URL giphyURL = new URL("http://api.giphy.com/v1/gifs/random?api_key=giphy-key-here&tag=" + tag + "&limit=1");
        BufferedReader in = new BufferedReader(new InputStreamReader(giphyURL.openStream()));
        String str;
        while ((str = in.readLine()) != null) {
            jsonString.append(str);
        }
        in.close();

        JsonElement original = JsonParser.parseString(jsonString.toString()).getAsJsonObject().get("data")
                .getAsJsonObject().get("images").getAsJsonObject().get("original").getAsJsonObject().get("url");

        return original.getAsString();
    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().toLowerCase().split("!gif");
        if (args.length < 2) {
            event.getChannel().sendMessage("Please enter a tag to search").queue();
        } else {
            String tag = String.join(" ", args).trim();
            try {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(0xff3923);
                builder.setTitle("Here's the result for \"" + tag + "\"");
                builder.setImage(getGif(tag));
                builder.setFooter("Powered by GIPHY", "https://i.imgur.com/5Ik1ZhR.png");
                event.getChannel().sendMessage(builder.build()).queue();
                //event.getChannel().sendMessage(getGif(tag)).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
