package com.Tigerwurm.Drachennester;


import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Drachennester extends JavaPlugin implements Listener {
	
	public void onEnable() {
		
	}
	
	@EventHandler
	public void onLoadChunk(ChunkLoadEvent event) {
		if(event.isNewChunk()) {
			Chunk chunk = event.getChunk();
			new Dungeon();
		}
	}

}
