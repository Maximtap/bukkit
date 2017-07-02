package com.Tigerwurm.Varo;

import java.util.List;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AutoKicker extends BukkitRunnable {
	
	private String kickNachricht;
	private Player spieler;
	private static int entfernung = 20;
	
	public AutoKicker(String Message, Player player, JavaPlugin plugin, int delay) {
		this.kickNachricht = Message;
		this.spieler = player;
		this.runTaskTimer(plugin, delay, 25);
	}
	
	@Override
	public void run() {
		List<Entity> alleEntities = spieler.getNearbyEntities(entfernung, entfernung, entfernung);
		if(alleEntities != null) {
			for(Entity p : alleEntities) {
				if(p instanceof Player) return;
			}
		}
		spieler.kickPlayer(kickNachricht);
		this.cancel();
	}
	
}
