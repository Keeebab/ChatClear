package me.kebab.chatclear;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatClearCommands
  implements CommandExecutor
{
  @SuppressWarnings({ "unchecked", "rawtypes" })
public static ArrayList<String> mutedp = new ArrayList();
  public static boolean mutestatus = false;
  @SuppressWarnings("unused")
private ChatClear plugin;

  public ChatClearCommands(ChatClear plugin)
  {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] arg3)
  {
    Player player = (Player)sender;

    if ((sender instanceof Player))
    {
      if (cmd.getName().equalsIgnoreCase("clocal"))
      {
        if ((player.hasPermission("ChatClear.local")) || (player.isOp()))
        {
          for (int x = 0; x < 120; x++)
          {
            player.sendMessage(" ");

            if (x == 119)
            {
              player.sendMessage(ChatColor.AQUA + "[Chat] " + ChatColor.GREEN + "You have cleared your own chat.");
            }
          }
          return true;
        }

        player.sendMessage(ChatColor.DARK_RED + "[Chat] Access Denied");
      }

      if (cmd.getName().equalsIgnoreCase("cglobal"))
      {
        if ((player.hasPermission("ChatClear.cglobal")) || (player.isOp()))
        {
          for (Player p : Bukkit.getOnlinePlayers())
          {
            for (int x = 0; x < 120; x++) {
              p.sendMessage(" ");

              if (x == 119)
              {
                p.sendMessage(ChatColor.AQUA + "[Chat] " + ChatColor.GREEN + "Chat cleared by " + ChatColor.RED + sender.getName() + ChatColor.GREEN + ".");
              }
            }
          }

          return true;
        }

        player.sendMessage(ChatColor.DARK_RED + "[Chat] Access Denied");
      }

      if (cmd.getName().equalsIgnoreCase("cmute"))
      {
        if (player.hasPermission("ChatClear.cmute")) {
          mutestatus = !mutestatus;
        }
        if (mutestatus)
          sender.sendMessage("You have started a global mute session.");
        else {
          sender.sendMessage("You have ended a global mute session.");
        }

        for (Player p : Bukkit.getOnlinePlayers())
        {
          if (((mutestatus) && (!p.getPlayer().isOp())) || (!p.getPlayer().hasPermission("ChatClear.muteo"))) {
            mutedp.add(p.getName());
            p.sendMessage(ChatColor.AQUA + "[Chat] " + ChatColor.GREEN + "A global mute is in progress.");
          }

          if ((!mutestatus) && (mutedp.contains(p.getName()))) {
            mutedp.remove(p.getName());
            p.sendMessage(ChatColor.AQUA + "[Chat] " + ChatColor.GREEN + "You may now speak.");
          }
        }
      }
      else if ((!sender.isOp()) || (!sender.hasPermission("chatclear.mute"))) {
        sender.sendMessage(ChatColor.DARK_RED + "[Chat] *Beep* *Beep* Access Denied.");
      }

    }

    return false;
  }
}