package me.mchiappinam.pdghairdrop;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.mchiappinam.pdghapiutility.Metodos;

public class Main extends JavaPlugin implements Listener {
	public Location bau=null;
	public List<String> bloqueado = new ArrayList<String>();
	public boolean contagemIniciada=false;
	public int timer=120;
	static int tcontagem;
	static int tsumirbau;
	static int tcheck;
	protected Location spawn;
	public String zero=null;
	public String um=null;
	public String dois=null;
	public String tres=null;
	public String quatro=null;
	public boolean combate=false;
	public boolean apiutility=false;
	private me.mchiappinam.pdghapiutility.Main api;
	
	public void onEnable() {
		getServer().getPluginCommand("airdrop").setExecutor(new Comando(this));
	    getServer().getPluginManager().registerEvents(new Listeners(this), this);

		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}catch(Exception e) {}
		}
		
		if (getServer().getPluginManager().getPlugin("SimpleNoRelog") == null) {
			getLogger().warning("SimpleNoRelog API nao encontrado!");
			combate=false;
		}else{
			getLogger().info("SimpleNoRelog API ativado!");
			combate=true;
		}
		
		if (getServer().getPluginManager().getPlugin("PDGHAPIUtility") == null) {
			getLogger().warning("PDGHAPIUtility nao encontrado!");
			apiutility=false;
		}else{
			getLogger().info("PDGHAPIUtility ativado!");
			api = (me.mchiappinam.pdghapiutility.Main)getServer().getPluginManager().getPlugin("PDGHAPIUtility");
			apiutility=true;
		}
		getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2ativando tasks...");
		check();
		getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2Acesse: http://pdgh.com.br/");
	}

	public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2Acesse: http://pdgh.com.br/");
	}
	
    public boolean temEspacoInv(Player p) {
        if(p.getInventory().firstEmpty()==-1)
        	return false;
        else
        	return true;
    }
	
    public Metodos getMetodos() {
    	return api.getMetodos();
    }
    
    public void kit(Player p) {
    	if(combate) {
			br.com.devpaulo.simplenorelog.api.SimpleNoRelog.addToPvp(p);
			p.sendMessage("§c§lVocê entrou em combate! Se deslogar morrerá!");
		}
    	boolean full=false;
    	
	    ItemStack espada = new ItemStack(Material.DIAMOND_SWORD, 1);
	    espada.addEnchantment(Enchantment.DAMAGE_ALL, 2);
	    espada.addEnchantment(Enchantment.FIRE_ASPECT, 1);
	    
	    ItemStack arco = new ItemStack(Material.BOW, 1);
	    arco.addEnchantment(Enchantment.ARROW_DAMAGE , 2);
	    arco.addEnchantment(Enchantment.ARROW_FIRE, 1);
	    
	    
        if(temEspacoInv(p))
    	    p.getInventory().addItem(espada);
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), espada);
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(arco);
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), arco);
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.DIAMOND_HELMET, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.DIAMOND_HELMET, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.DIAMOND_BOOTS, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.DIAMOND_BOOTS, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.GOLD_AXE, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLD_AXE, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.IRON_AXE, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.IRON_AXE, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.GOLD_RECORD, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLD_RECORD, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.RECORD_9, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.RECORD_9, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.BONE, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.BONE, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.DIAMOND, 1));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.GHAST_TEAR, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GHAST_TEAR, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.GHAST_TEAR, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GHAST_TEAR, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }

        if(temEspacoInv(p))
    	    p.getInventory().addItem(new ItemStack(Material.FLINT, 64));
        else {
        	full=true;
        	p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.FLINT, 64));
        }
        
        
        
        if(full) {
        	p.sendMessage(" ");
        	p.sendMessage(" ");
        	p.sendMessage("§3§l[PDGHAirDrop] §cSeu inventário está cheio! Dropando itens no chão...");
        	p.sendMessage(" ");
        	p.sendMessage(" ");
        }
    }
	
    public void check() {
		tcheck=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
				if((Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==11)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==15)||
						(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==21))
					if(Calendar.getInstance().get(Calendar.MINUTE)==20) {
						contagem();
						ctcheck();
					}
	  		}
	  	}, 0, 5*20);
	}

	public void ctcheck() {
		getServer().getScheduler().cancelTask(tcheck);
	}
	
    public void getLocation() {
		if(getConfig().contains("locais")) {
			getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2carregando locais...");
			List<String> lista=new ArrayList<String>();
			lista=getConfig().getStringList("locais");
			getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2locais carregados com sucesso!");
	    	Random r = new Random();
	    	int selecionado = r.nextInt(lista.size());
	    	//String vencedor = (String)lista.get(selecionado);
	    	int etapa=0;
			for(String lo : lista.get(selecionado).split(";")) {
				if(etapa==0)
					zero=lo;
				else if(etapa==1)
					um=lo;
				else if(etapa==2)
					dois=lo;
				else if(etapa==3)
					tres=lo;
				else if(etapa==4)
					quatro=lo;
				etapa++;
			}
			spawn = new Location(getServer().getWorld(zero),Double.parseDouble(um),Double.parseDouble(dois)+1,Double.parseDouble(tres));
			//getServer().broadcastMessage(lista.size()+" - "+zero+","+um+","+dois+","+tres+","+quatro);
			/**for(String lo : getConfig().getStringList("locais")) {
				String[] me=lo.split(";");
				Location l=new Location(getServer().getWorld(me[0]),Double.parseDouble(me[1]),Double.parseDouble(me[2]),Double.parseDouble(me[3]));
				plugin.getServer().getWorld("world_eventos").getBlockAt(l).setType(Material.getMaterial(Integer.parseInt(me[3].split(":")[0])));
				plugin.getServer().getWorld("world_eventos").getBlockAt(l).setData((byte)Integer.parseInt(me[3].split(":")[1]));
			}
			plugin.getServer().getConsoleSender().sendMessage("§d[Evento Automático] §fBlocos colocados com sucesso!");*/
		}
    }
	
	public void spawnDropAleatorio() {
		if(getServer().getOnlinePlayers().length==0) {
			getServer().getConsoleSender().sendMessage("§3[PDGHAirDrop] §2Air Drop cancelado. Sem jogadores online.");
			return;
		}
		boolean loaded = false;
		for(World w: getServer().getWorlds()) {
			if(w.getName().equals(spawn.getWorld().getName())) {
				loaded = true;
				break;
			}
		}
		if(!loaded) {
			getServer().broadcastMessage("§3§l[PDGHAirDrop] §cAir Drop cancelado. Mundo "+spawn.getWorld().getName()+" não carregado.");
			return;
		}
		Block bau = spawn.clone().add(0, 0, 0).getBlock();
	    bau.setType(Material.CHEST);
		sumirbau();
		for(int a=1;4>a;a++) {
			Zombie zombie = (Zombie) spawn.getWorld().spawnEntity(new Location(spawn.getWorld(),spawn.getBlockX(),spawn.getBlockY(),spawn.getBlockZ()), EntityType.ZOMBIE);
            zombie.setBaby(true);
			zombie.setCustomName("§aPDGH AirDrop MINIBOSS §e§l"+a);
            zombie.setCustomNameVisible(true);
            zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,9999999,5));
            zombie.setMaxHealth(50);
            zombie.setHealth(50);
            zombie.getEquipment().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
            zombie.getEquipment().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
            zombie.getEquipment().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
            zombie.getEquipment().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
            zombie.getEquipment().setHelmetDropChance(0);
            zombie.getEquipment().setChestplateDropChance(0);
            zombie.getEquipment().setLeggingsDropChance(0);
            zombie.getEquipment().setBootsDropChance(0);
		}
		getServer().broadcastMessage("§3§l[PDGHAirDrop]");
		getServer().broadcastMessage("§aO baú de suprimentos aéreo caiu entre: X: "+um+", Y: "+dois+", Z: "+tres+"");
		getServer().broadcastMessage("§aO comando de referência é "+quatro);
		getServer().broadcastMessage("§3§l[PDGHAirDrop]");
	}
	
    public void contagem() {
    	timer=120;
    	contagemIniciada=true;
		tcontagem=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
	  			if(timer==120) {
	  				getLocation();
	  				getServer().broadcastMessage("§3§l[PDGHAirDrop]");
	  				getServer().broadcastMessage("§aO baú de suprimentos aéreo vai ocorrer dentro de 2 minutos entre: X: "+um+", Y: "+dois+", Z: "+tres+"");
	  				getServer().broadcastMessage("§aO comando de referência é "+quatro);
	  				getServer().broadcastMessage("§3§l[PDGHAirDrop]");
	  			}else if(timer==60) {
	  				getServer().broadcastMessage("§3§l[PDGHAirDrop]");
	  				getServer().broadcastMessage("§aO baú de suprimentos aéreo vai ocorrer dentro de 1 minuto entre: X: "+um+", Y: "+dois+", Z: "+tres+"");
	  				getServer().broadcastMessage("§aO comando de referência é "+quatro);
	  				getServer().broadcastMessage("§3§l[PDGHAirDrop]");
	  			}else if(timer==0) {
	  				spawnDropAleatorio();
					ctcontagem();
	  			}
	  			timer--;
	  		}
	  	}, 0, 20);
	}
	
    public void sumirbau() {
    	timer=60;
		tsumirbau=getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
	  		public void run() {
	  			if(timer==0) {
	  				Block bau = spawn.clone().add(0, 0, 0).getBlock();
	  			    bau.setType(Material.AIR);
	  				getServer().broadcastMessage("§3§l[PDGHAirDrop] §cUm minuto se passou. O baú desapareceu!");
	  		    	if(apiutility) {
	  		    		getMetodos().sendTweet("NINGUÉM PEGOU O AIRDROP! X: "+um+", Y: "+dois+", Z: "+tres+" (ref: "+quatro+")");
	  		    	}
	  				ctsumirbau();
	  			}
	  			timer--;
	  		}
	  	}, 0, 20);
	}

	public void ctcontagem() {
		contagemIniciada=false;
		getServer().getScheduler().cancelTask(tcontagem);
	}

	public void ctsumirbau() {
		spawn=null;
		zero=null;
		um=null;
		dois=null;
		tres=null;
		quatro=null;
		getServer().getScheduler().cancelTask(tsumirbau);
	}

	public void help(Player p) {
		p.sendMessage("§3§lPDGH Air Drop - Info:");
		if(contagemIniciada) {
			p.sendMessage("§2Contagem -§a- §l"+timer+"§a segundos para o baú de suprimentos cair.");
		}else{
			p.sendMessage("§2Contagem -§a- O baú de suprimentos aéreo ainda não foi iniciado.");
			p.sendMessage(" ");
			p.sendMessage("§aHorários dos baús de suprimentos:");
			p.sendMessage("§aTodos os dias às:");
			p.sendMessage("§c> §a11:20 horas");
			p.sendMessage("§c> §a15:20 horas");
			p.sendMessage("§c> §a21:20 horas");
			p.sendMessage("§aArrume seu despertador e pegue sempre o baú de suprimentos!");
		}
		if(p.hasPermission("pdgh.op")) {
			p.sendMessage("§c/airdrop add <cmd-referencia> -§4- Adiciona uma coordenada.");
			p.sendMessage("§c/airdrop fspawn -§4- Força o drop do baú.");
			p.sendMessage("§c/airdrop fcontagem -§4- Inicia a contagem do drop do baú (2 min).");
		}
	}
	
	public boolean isValidNumber(String args) {
		try{
			if(!StringUtils.isNumeric(args)) {
				return false;
			}
			int valor=Integer.parseInt(args);
			if(!(valor>0)&&(valor<=999999999)) {
				return false;
			}
			return true;
		}catch (NumberFormatException nfe){
			return false;
		}
	}
  
}