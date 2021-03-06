package me.ryarrowsmith.relash3.api.commands.stats;

import me.ryarrowsmith.relash3.api.Utils;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.exceptions.PermissionException;

import java.time.temporal.ChronoUnit;

/**
 * Created by Ryan's PC on 05/03/2017.
 */
public final class Ginfo extends Utils {

    public void gInfo(Message trigger){

        final MessageChannel channel = trigger.getChannel();
        final JDA jda = trigger.getJDA();
        if(channel.getType() != ChannelType.TEXT){

            trigger.addReaction("\uD83D\uDEAB");
            return;
        }
        Guild guild = ((TextChannel) channel).getGuild();
        String roles = "";
        for(Role role : guild.getRoles()) {

            roles += role.getName() + ", ";
        }

        try {

            channel.sendMessage(makeEmbed("❗❗ " + guild.getName() + " Info ❗❗", null, "Owner: @" + guild.getOwner().getUser().getName() + "#" + guild.getOwner().getUser().getDiscriminator(), "Created: " + guild.getCreationTime().until(trigger.getCreationTime(), ChronoUnit.DAYS) + " days ago.", "Channels: " + "Text: " + guild.getTextChannels().size() + "\nVoice: " + guild.getVoiceChannels().size(), "Members: " + guild.getMembers().size(), "Roles: " + roles.trim().replace("@everyone,", "everyone.")).setThumbnail(guild.getIconUrl()).build()).queue();

        } catch (PermissionException e){

            channel.sendMessage("❗❗ " + guild.getName() + " Info ❗❗" ).queue();
        }
    }
}
