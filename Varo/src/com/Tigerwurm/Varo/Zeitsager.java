package com.Tigerwurm.Varo;

import java.util.UUID;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class Zeitsager implements Runnable {
	
	private String message;
	private UUID playeruuid;
	private Server server;
	private boolean Cancelled;
	
	public Zeitsager(String Message, UUID puuid, Server server) {
		this.message = Message;
		this.playeruuid = puuid;
		this.server = server;
		this.Cancelled = false;
	}
	
	public Zeitsager(String Message, Server server) {
		this.message = Message;
		this.server = server;
		this.Cancelled = false;
	}
	
	@Override
	public void run() {
		if(!Cancelled) {
			if(playeruuid != null) {
				Player player = server.getPlayer(playeruuid);
				if(player != null) {
					player.sendMessage(message);
				}
			} else {
				server.broadcastMessage(message);
			}
		}
	}
	
	public void setCancelled(boolean wert) {
		Cancelled = wert;
	}
}
