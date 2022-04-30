package net.craftunity.bans;

import net.craftunity.bans.Commands.*;
import net.craftunity.bans.Discord.Channel.PunishLogChannel;
import net.craftunity.bans.Discord.Messages.Embeds;
import net.craftunity.bans.Listener.Join;
import net.craftunity.bans.Utils.MySQL;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;


public final class Main extends Plugin {



    @Override
    public void onEnable() {
        // Plugin startup logic



        getProxy().getPluginManager().registerListener(this, new Join(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new BanCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new MuteCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new ReportCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new KickCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new UnmuteCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new UnbanCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new PlayerInfoCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new GotoCommand(this));
        getProxy().getPluginManager().registerCommand(this, (Command)new ModSettingCommand(this));
        MySQL.connect();

        // MySQL Log in is in the MySQL Class


        DiscordApi api = new DiscordApiBuilder().setToken("insert Token for Discord Bot here <3").login().join();
        // Add a listener which answers with "Pong!" if someone writes "!ping"


        PunishLogChannel.setChannel(  api.getTextChannelById("Punishment Log ChannelID here").get());
        PunishLogChannel.setChannel(  api.getTextChannelById("Report Log ChannelID here").get());




    }








    @Override
    public void onDisable() {
        // Plugin shutdown logic


        PunishLogChannel.getChannel().sendMessage("Bot Now Shutting Down due to Proxy Shutdown / restart");
    }




}
