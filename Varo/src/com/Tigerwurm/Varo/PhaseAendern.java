package com.Tigerwurm.Varo;

import java.util.Collection;

import org.bukkit.Difficulty;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class PhaseAendern implements Runnable {
	
	int nextPhase;
	Server server;
	
	public PhaseAendern(int neuePhase, Server server) {
		this.server = server;
		this.nextPhase = neuePhase;
	}
	
	@Override
	public void run() {
		World welt = server.getWorld("world");
		Collection<? extends Player> players = server.getOnlinePlayers();
		GameManager.phase = nextPhase;
		for(Player p : players) {
			p.setHealth(20);
			p.setFoodLevel(20);
		}
		welt.setDifficulty(Difficulty.EASY);
		welt.setThundering(false);
		welt.setStorm(false);
		welt.setTime(600);
	}

}
