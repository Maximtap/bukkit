package com.Tigerwurm.AdventureMap;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Nerv extends BukkitRunnable {
	
	Player spieler;
	Random zufall;
	
	public Nerv(Player p) {
		this.spieler = p;
		zufall = new Random();
	}
	
	@Override
	public void run() {
		spieler.getInventory().setHeldItemSlot(zufall.nextInt(9));
		
	}

}
