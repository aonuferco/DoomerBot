package doomerbot.events.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class YTSearch {

    public static void execute(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().toLowerCase().split("!yt");
        if(args.length < 2) {
            event.getChannel().sendMessage("Please enter something to search").queue();
        } else {
            String tag = String.join(" ", args).trim();
            try {
                event.getChannel().sendMessage("\uD83C\uDFA5 | https://www.youtube.com/watch?v=" + getID(tag)).queue();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getID(String tag) throws IOException {
        StringBuilder jsonString = new StringBuilder();
        URL ytUrl = new URL("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&order=relevance&q=" + tag.replaceAll(" ", "%20") + "&key=yt-key-here");
        BufferedReader in = new BufferedReader(new InputStreamReader(ytUrl.openStream()));
        String str;
        while ((str = in.readLine()) != null) { jsonString.append(str); }
        in.close();

        JsonElement original = JsonParser.parseString(jsonString.toString()).getAsJsonObject().getAsJsonArray("items")
                .get(0).getAsJsonObject().get("id").getAsJsonObject().get("videoId");

        return original.getAsString();
    }
}
