package me.kebab.chatclear;

import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class ChatClear extends JavaPlugin
{
  public void onEnable(){
	  getLogger().info("Plugin Enabled");
  }

  public void onDisable(){
  getLogger().info("Plugin Disabled");
  }

  public void register() {
    getCommand("clocal").setExecutor(new ChatClearCommands(this));
    getCommand("cglobal").setExecutor(new ChatClearCommands(this));
    getCommand("cmute").setExecutor(new ChatClearCommands(this));
    getServer().getPluginManager().registerEvents(new ChatClearListener(this), this);
  }
}