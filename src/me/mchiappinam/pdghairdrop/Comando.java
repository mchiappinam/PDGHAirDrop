package me.mchiappinam.pdghairdrop;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {
  private Main plugin;

  public Comando(Main main) {
    plugin = main;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
	if(cmd.getName().equalsIgnoreCase("airdrop")) {
		if((!sender.hasPermission("pdgh.op"))||args.length<1) {
			plugin.help((Player)sender);
			return true;
		}
		if(args[0].equalsIgnoreCase("add")) {
			if(args.length<2) {
				plugin.help((Player)sender);
				return true;
			}
	        StringBuilder sb = new StringBuilder();
	        sb.append(args[1]);
	        for (int i = 2; i < args.length; i++) {
	          sb.append(" ");
	          sb.append(args[i]);
	        }
			List<String> lista=new ArrayList<String>();
			lista=plugin.getConfig().getStringList("locais");
			Player p=(Player)sender;
			lista.add(p.getLocation().getWorld().getName()+";"+p.getLocation().getBlockX()+";"+p.getLocation().getBlockY()+";"+p.getLocation().getBlockZ()+";"+sb.toString().replaceAll(";", "").trim());
			plugin.getConfig().set("locais", lista);
			plugin.saveConfig();
			plugin.reloadConfig();
			lista.clear();
			sender.sendMessage("§cLocal adicionado! Comando de referência: "+sb.toString().replaceAll(";", "").trim());
			return true;
		}else if(args[0].equalsIgnoreCase("fspawn")) {
			if(plugin.contagemIniciada) {
				sender.sendMessage("§cA contagem já foi iniciada!");
				return true;
			}
			if(plugin.spawn!=null) {
				sender.sendMessage("§cO baú ainda está liberado!");
				return true;
			}
			plugin.getLocation();
			plugin.spawnDropAleatorio();
			return true;
		}else if(args[0].equalsIgnoreCase("fcontagem")) {
			if(plugin.contagemIniciada) {
				sender.sendMessage("§cA contagem já foi iniciada!");
				return true;
			}
			if(plugin.spawn!=null) {
				sender.sendMessage("§cO baú ainda está liberado!");
				return true;
			}
			plugin.contagem();
			return true;
		}
		plugin.help((Player)sender);
		return true;
	}
    return false;
  }
  
  
  
  
  
  
}