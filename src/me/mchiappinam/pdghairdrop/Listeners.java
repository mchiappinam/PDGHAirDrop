package me.mchiappinam.pdghairdrop;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Listeners implements Listener {
	
	private Main plugin;
	public Listeners(Main main) {
		plugin=main;
	}

	/**private Comando cmd;
	public Listeners(Comando Cmd) {
		cmd=Cmd;
	}*/

	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(plugin.spawn!=null)
				if((e.getClickedBlock().getLocation().getBlockX()==plugin.spawn.getBlockX())&&
						(e.getClickedBlock().getLocation().getBlockY()==plugin.spawn.getBlockY())&&
						(e.getClickedBlock().getLocation().getBlockZ()==plugin.spawn.getBlockZ())) {
		    		if(e.getClickedBlock().getType() == Material.CHEST) {
		    	        e.setCancelled(true);
		    	    	if(plugin.apiutility) {
		    	    		plugin.getMetodos().sendTweet(e.getPlayer().getName()+" PEGOU O AIRDROP! X: "+plugin.um+", Y: "+plugin.dois+", Z: "+plugin.tres+" (ref: "+plugin.quatro+")");
		    	    	}
		    			plugin.ctsumirbau();
		    			e.getClickedBlock().setType(Material.AIR);
		    			for(int a=1;11>a;a++) {
		    				Zombie zombie = (Zombie) e.getClickedBlock().getWorld().spawnEntity(e.getClickedBlock().getLocation(), EntityType.ZOMBIE);
		    				zombie.setCustomName("§aPDGH AirDrop BOSS §e§l"+a);
		    	            zombie.setCustomNameVisible(true);
		    	            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,9999999,5));
		    	            zombie.setMaxHealth(50);
		    	            zombie.setHealth(50);
		    	            zombie.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
		    	            zombie.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
		    	            zombie.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
		    	            zombie.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
		    	            zombie.getEquipment().setHelmetDropChance(0);
		    	            zombie.getEquipment().setChestplateDropChance(0);
		    	            zombie.getEquipment().setLeggingsDropChance(0);
		    	            zombie.getEquipment().setBootsDropChance(0);
		    			}
		    			plugin.getServer().broadcastMessage("§3§l[PDGHAirDrop]");
		    			plugin.getServer().broadcastMessage("§2"+e.getPlayer().getName()+"§a pegou o baú de suprimentos.");
		    			plugin.getServer().broadcastMessage("§aVeja os horários dos baús de suprimentos com o comando §f/airdrop");
		    			plugin.getServer().broadcastMessage("§3§l[PDGHAirDrop]");
		    			plugin.kit(e.getPlayer());
		    		}
				}
		}
	}
	  
	/**@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		plugin.cancelTask(e.getPlayer());
	}
		
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e) {
		plugin.cancelTask(e.getPlayer());
	}*/
}