package doomerbot.events.fun;

import com.google.gson.Gson;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class RandomMeme {
    private static String title, image, subreddit, src;

    private static void getMeme() throws IOException {
        URL apiURL = new URL("https://meme-api.herokuapp.com/gimme");
        BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()));
        String tmp = in.readLine();
        in.close();
        Map jsonJavaRootObject = new Gson().fromJson(tmp, Map.class);
        title = jsonJavaRootObject.get("title").toString();
        image = jsonJavaRootObject.get("url").toString();
        subreddit = jsonJavaRootObject.get("subreddit").toString();
        src = jsonJavaRootObject.get("postLink").toString();
    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        try {
            getMeme();
            builder.setTitle(title, src);
            builder.setImage(image);
            builder.setDescription("r/" + subreddit);
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
