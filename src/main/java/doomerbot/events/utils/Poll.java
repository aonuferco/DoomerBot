package doomerbot.events.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;
import java.util.Map;

public class Poll {

    private static final Map<Integer, String> numMap = Map.of(1, ":one:", 2, ":two:", 3, ":three:",
            4, ":four:", 5, ":five:", 6, ":six:", 7, ":seven:", 8, ":eight:",
            9, ":nine:", 10, ":keycap_ten:");
    private static String description = "";


    public static void execute(@Nonnull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().toLowerCase().split("!poll");
        if (args.length < 2) {
            event.getChannel().sendMessage("Please enter a question followed by up to 10 choices separated by commas.").queue();
        } else {
            String[] arr = args[1].trim().split(",");
            if (arr.length < 2)
                event.getChannel().sendMessage("Please enter choices separated with commas after the question.").queue();
            else if (arr.length > 11) {
                event.getChannel().sendMessage("You can only enter up to 10 choices. You've entered "
                        + (arr.length - 1) + " choices.").queue();
            }
            else {
                for (int i = 1; i < arr.length; i++){
                    description += numMap.get(i) + " " + arr[i].trim() + "\n";
                }

                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(2733205);
                builder.setTitle(arr[0].trim());
                builder.setDescription(description);
                event.getChannel().sendMessage(builder.build()).queue(message -> {
                    if (arr.length > 10){
                        for (int i = 1; i < arr.length; i++){
                            message.addReaction("U+003" + i +" U+20E3").queue();
                        }
                        message.addReaction("U+1F51F").queue();
                    }
                    else {
                        for (int i = 1; i < arr.length; i++){
                            message.addReaction("U+003" + i +" U+20E3").queue();
                        }
                    }
                });
                description = "";
            }
        }
    }
}
