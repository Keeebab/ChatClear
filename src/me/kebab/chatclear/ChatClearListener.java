package me.kebab.chatclear;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

@SuppressWarnings({ "deprecation", "unused" })
public class ChatClearListener
  implements Listener
{
  private ChatClear plugin;

  public ChatClearListener(ChatClear plugin)
  {
    this.plugin = plugin;
  }

  @EventHandler
  public void onPlayerChat(PlayerChatEvent e) {
    if ((ChatClearCommands.mutestatus) && (ChatClearCommands.mutedp.contains(e.getPlayer().getName()))) {
      e.setCancelled(true);
      e.getPlayer().sendMessage(ChatColor.RED + "[ChatClear] " + ChatColor.AQUA + "A global mute is in progress.");
    }
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent e) {
    if (ChatClearCommands.mutestatus) {
      ChatClearCommands.mutedp.add(e.getPlayer().getName());
      e.getPlayer().sendMessage(ChatColor.RED + "[ChatClear] " + ChatColor.AQUA + "A global mute is in progress.");
    }
  }

  @EventHandler
  public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
    if ((ChatClearCommands.mutestatus) && (ChatClearCommands.mutedp.contains(e.getPlayer().getName()))) {
      e.setCancelled(true);
      e.getPlayer().sendMessage(ChatColor.RED + "[ChatClear] " + ChatColor.AQUA + "A global mute is in progress.");
    }
  }
}