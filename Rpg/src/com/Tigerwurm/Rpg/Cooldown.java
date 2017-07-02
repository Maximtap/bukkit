package com.Tigerwurm.Rpg;

import org.bukkit.entity.Player;

public class Cooldown implements Runnable{
	
	Player player;
	
	public Cooldown(Player p) {
		this.player = p;
	}
	
	@Override
	public void run() {
		DialogManager.getCooldowns().put(player.getUniqueId(), false);
	}

}
