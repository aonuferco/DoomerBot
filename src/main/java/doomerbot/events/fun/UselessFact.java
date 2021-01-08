package doomerbot.events.fun;

import com.google.gson.Gson;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;

public class UselessFact {

        private static String getFact() throws IOException {
        StringBuilder url = new StringBuilder();
        URL apiURL = new URL("https://uselessfacts.jsph.pl/random.json?language=en");
        BufferedReader in = new BufferedReader(new InputStreamReader(apiURL.openStream()));
        String str;
        while ((str = in.readLine()) != null) { url.append(str); }
        in.close();

        Map jsonJavaRootObject = new Gson().fromJson(url.toString(), Map.class);

        return jsonJavaRootObject.get("text").toString();
    }

    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        try {
            event.getChannel().sendMessage(getFact()).queue();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
