package com.Tigerwurm.Varo;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Timer {
	
	String Message;
	int[] zeitabstände;
	int zeitbisEnde;
	Player target;
	JavaPlugin plugin;
	private ArrayList<Zeitsager> sager;
	private boolean Cancelled;
	
	
	public Timer(String Message, int[] zeitabstände, int zeitbisEnde, Player target, JavaPlugin plugin) {
		this.Message = Message;
		this.zeitabstände = zeitabstände;
		this.zeitbisEnde = zeitbisEnde;
		this.target = target;
		this.plugin = plugin;
		this.Cancelled = false;
		this.sager = new ArrayList<>();
		run();
	}
	
	public void run() {
		String[] guteMessage = Message.split("time");
		for(int zahlenwert : zeitabstände) {
			String richtigeMessage = guteMessage[0];
			for(int i = 1; i < guteMessage.length; i++) {
				richtigeMessage = richtigeMessage + String.valueOf(zahlenwert) + guteMessage[i]; 
			}
			if(target != null) {
				plugin.getServer().getScheduler().runTaskLater(plugin, new Zeitsager(richtigeMessage, target.getUniqueId(), plugin.getServer()), (zeitbisEnde - zahlenwert) *25);
			} else {
				plugin.getServer().getScheduler().runTaskLater(plugin, new Zeitsager(richtigeMessage, plugin.getServer()), (zeitbisEnde - zahlenwert) *25);
			}
		}
	}
	
	public void setCancelled(boolean wert) {
		Cancelled = wert;
		if(Cancelled == true) {
			for(Zeitsager d : sager) {
				d.setCancelled(true);
			}
		}
	}
}
