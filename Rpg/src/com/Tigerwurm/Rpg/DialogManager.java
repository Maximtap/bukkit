package com.Tigerwurm.Rpg;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class DialogManager implements Listener {
	
	//Entity
	static HashMap<UUID, String[][]> sprecher;
	static HashMap<UUID, HashMap<UUID, Integer[]>> dialogweite;
	static HashMap<UUID, String> standardphrasen;
	
	//Player
	static HashMap<UUID, Boolean> cooldowns;
	Manager manager;
	
	public DialogManager(Manager m) {
		sprecher = new HashMap<>();
		dialogweite = new HashMap<>();
		standardphrasen = new HashMap<>();
		cooldowns = new HashMap<>();
		this.manager = m;
		this.manager.getServer().getPluginManager().registerEvents(this, this.manager);
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRightClick(PlayerInteractEntityEvent e) {
		
		if(sprecher.containsKey(e.getRightClicked().getUniqueId())) {
			
			Entity redet = e.getRightClicked();
			UUID redendes = redet.getUniqueId();
			Player p = e.getPlayer();
			UUID playerUUID = p.getUniqueId();
			
			if(cooldowns.get(playerUUID) == null) cooldowns.put(playerUUID, true);
			else if(cooldowns.get(playerUUID)) return;
			else {
				cooldowns.put(playerUUID, true);
			}
			manager.getServer().getScheduler().scheduleSyncDelayedTask(manager, new Cooldown(p), 12);
			
			HashMap<UUID, Integer[]> playerweite = dialogweite.get(redendes);
			
			if(playerweite == null) {
				p.sendMessage("Ein Fehler ist aufgetreten.");
				return;
			} 
			

			
			if(playerweite.get(playerUUID) == null) {
				
				Integer[] weite1 = {0, 0};
				playerweite.put(playerUUID, weite1);
//				p.sendMessage("Fehler 1");
				
			}
			Integer[] weite = playerweite.get(playerUUID);
//			p.sendMessage("weite[0] = " + weite[0]);
			String[] dialog = sprecher.get(redendes)[weite[0]];
			if(weite[1] >= dialog.length) {
				p.sendMessage("<" + redet.getCustomName() + "> " + standardphrasen.get(redendes));
				return;
			}
			p.sendMessage("<" + redet.getCustomName() + "> " + dialog[weite[1]]);
			weite[1]++;
			Integer[] neueWeite = {weite[0], weite[1]};
			playerweite.put(playerUUID, neueWeite);
			e.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerLoginEvent e) {
		if(e.getResult() != PlayerLoginEvent.Result.ALLOWED) return;
//		if(e.getPlayer().hasPlayedBefore()) return;
		UUID player = e.getPlayer().getUniqueId();
		Integer[] liste = {0, 0};
		 for(UUID i : dialogweite.keySet()) {
			 dialogweite.get(i).put(player, liste);
		 }
		 e.getPlayer().sendMessage("Deine Gesprächsweite wurde geladen!");
	}
	
	public static void newDialog(Entity redendesTier, String[][] Dialog, String standardphrase) {
		if(redendesTier == null || Dialog == null) return;
		UUID Tier = redendesTier.getUniqueId();
		sprecher.put(Tier, Dialog);
		dialogweite.put(Tier, new HashMap<>());
		if(standardphrase == null) standardphrasen.put(Tier, "Ich freue mich dich zu sehen.");
		else standardphrasen.put(Tier, standardphrase);
	}
	
	public void setDialog(Player p, Entity ent, int i) {
		HashMap<UUID, Integer[]> weite = dialogweite.get(ent.getUniqueId());
		Integer[] spielerweite = weite.get(p.getUniqueId());
		spielerweite[0] = i;
		spielerweite[1] = 0;
//		p.sendMessage("Deine weite wurde gesetzt, neue weite: " + weite.get(p.getUniqueId())[0]);
	}
	
	public int getDialog(Player p, Entity ent) {
		return 0;
		
	}
	
	public static HashMap<UUID, Boolean> getCooldowns() {
		return cooldowns;
	}
}
