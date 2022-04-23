package net.craftunity.bans;

import net.craftunity.bans.Commands.*;
import net.craftunity.bans.Listener.Join;
import net.craftunity.bans.Utils.MySQL;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;


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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
