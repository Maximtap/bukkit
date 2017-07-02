package com.Tigerwurm.Varo;

import org.bukkit.Location;
import org.bukkit.WorldBorder;

//0.001 perfekte Geschw. bei größe 2000 für 20 Tage

public class Border implements Runnable {
	
	int dicke;
	double schnelligkeit;
	GameManager manager;
	WorldBorder border;
	
	public Border(Location loc, int dicke, double schnelligkeit, GameManager manager) {
		this.dicke = dicke;
		this.schnelligkeit = schnelligkeit;
		this.manager = manager;
		border = manager.getServer().getWorld("world").getWorldBorder();
		border.setCenter(loc);
		border.setSize(dicke);
		border.setDamageBuffer(10);
		border.setWarningDistance(10);
		manager.getServer().getScheduler().runTaskTimer(manager, this, 1, 25);
	}
	
	public void run() {
		border.setSize(border.getSize() - schnelligkeit);
	}	
}