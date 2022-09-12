package com.aonuferco.doomerbot.events.fun;

import com.aonuferco.doomerbot.events.EventAbstraction;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.internal.utils.JDALogger;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.Random;

/**
 * UselessWeb
 * Returns a random link from a
 * pool of fun websites.
 */
public class UselessWeb implements EventAbstraction {
    private final Logger logger = JDALogger.getLog(UselessWeb.class);
    private final String[] websites = new String[]{
            "http://heeeeeeeey.com/",
            "http://thatsthefinger.com/",
            "http://cant-not-tweet-this.com/",
            "http://eelslap.com/",
            "http://www.staggeringbeauty.com/",
            "http://burymewithmymoney.com/",
            "http://www.fallingfalling.com/",
            "http://ducksarethebest.com/",
            "http://www.trypap.com/",
            "http://www.republiquedesmangues.fr/",
            "http://www.movenowthinklater.com/",
            "http://www.partridgegetslucky.com/",
            "http://www.rrrgggbbb.com/",
            "http://beesbeesbees.com/",
            "http://www.sanger.dk/",
            "http://www.koalastothemax.com/",
            "http://www.everydayim.com/",
            "http://www.leduchamp.com/",
            "http://grandpanoclothes.com/",
            "http://www.haneke.net/",
            "http://instantostrich.com/",
            "http://r33b.net/",
            "http://randomcolour.com/",
            "http://cat-bounce.com/",
            "http://cachemonet.com/",
            "http://www.sadforjapan.com/",
            "http://www.taghua.com/",
            "http://chrismckenzie.com/",
            "http://hasthelargehadroncolliderdestroyedtheworldyet.com/",
            "http://ninjaflex.com/",
            "http://iloveyoulikeafatladylovesapples.com/",
            "http://ihasabucket.com/",
            "http://corndogoncorndog.com/",
            "http://giantbatfarts.com/",
            "http://www.ringingtelephone.com/",
            "http://www.pointerpointer.com/",
            "http://www.pleasedonate.biz/",
            "http://imaninja.com/",
            "http://willthefuturebeaweso.me/",
            "http://salmonofcapistrano.com/",
            "http://www.ismycomputeron.com/",
            "http://www.ooooiiii.com/",
            "http://www.wwwdotcom.com/",
            "http://www.nullingthevoid.com/",
            "http://www.muchbetterthanthis.com/",
            "http://www.ouaismaisbon.ch/",
            "http://iamawesome.com/",
            "http://www.pleaselike.com/",
            "http://crouton.net/",
            "http://corgiorgy.com/",
            "http://www.electricboogiewoogie.com/",
            "http://www.nelson-haha.com/",
            "http://www.wutdafuk.com/",
            "http://unicodesnowmanforyou.com/",
            "http://tencents.info/",
            "http://intotime.com/",
            "http://leekspin.com/",
            "http://minecraftstal.com/",
            "http://www.riddlydiddly.com/",
            "http://www.patience-is-a-virtue.org/",
            "http://whitetrash.nl/",
            "http://www.theendofreason.com/",
            "http://zombo.com",
            "http://secretsfornicotine.com/",
            "http://pixelsfighting.com/",
            "http://crapo.la/",
            "http://baconsizzling.com/",
            "http://isitwhite.com/",
            "http://noot.space/",
            "http://tomsdog.com/",
            "http://goat.com/",
            "https://www.dialupsound.com/",
            "http://computerpowertest.com/",
            "http://www.eeyup.com/",
            "http://www.nevernowhere.com/",
            "http://make-everything-ok.com/",
            "http://thenicestplaceontheinter.net/",
            "http://www.nyan.cat/",
            "http://zombo.com/",
            "http://gprime.net/game.php/dodgethedot",
            "http://blank.org/",
            "http://www.thedancinglion.com/",
            "http://touchpianist.com/",
            "http://www.whatsmystarbucksname.com/",
            "http://time.tetrasign.com/emojiclock/",
            "http://2015.tetrasign.com/",
            "http://www.youcanseethemilkyway.com/",
            "http://kolor.moro.es/",
            "http://foaas.com/"
    };

    @Override
    public void execute(@NotNull SlashCommandInteractionEvent event) {
        logger.info("/{} command called by user {}", event.getName(),
                event.getMember().getUser().getAsTag().toUpperCase());
        Random random = new Random();
        String randomString = websites[random.nextInt(websites.length)];
        event.reply(randomString).queue();
    }
}