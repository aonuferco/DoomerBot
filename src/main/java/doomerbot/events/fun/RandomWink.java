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

public class RandomWink {

    private static String getImage() throws IOException {
        String url;
        URL apiURL = new URL("https://some-random-api.ml/animu/wink");
        BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()));
        url = in.readLine();
        in.close();

        Map jsonJavaRootObject = new Gson().fromJson(url, Map.class);

        return jsonJavaRootObject.get("link").toString();
    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(0xff3923);
        try {
            builder.setImage(getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }
}
