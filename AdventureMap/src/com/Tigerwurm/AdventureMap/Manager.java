package com.Tigerwurm.AdventureMap;

import org.bukkit.event.EventHandler;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.WeatherType;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Manager extends JavaPlugin implements Listener{
	
	String[] Trolls = {"KeyTroll", "Blitz", "Write"};
	
	@Override
	public void onEnable() {
		super.onEnable();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("troll") && args.length >= 1) {
			if(contains(Trolls, args[0])) {
				boolean succesfull = troll(args);
				return succesfull;
			}
		}
		return false;
	}
	
	public void trolli(Player spieler) {
		int distance = 10;
		World welt = spieler.getWorld();
		Location loc = spieler.getLocation();
		welt.setTime(15000);
		int x = (int) loc.getX();
		int y = (int) loc.getY();
		int z = (int) loc.getZ(); 
		welt.strikeLightning(new Location(welt, x + distance, y, z));
		welt.strikeLightning(new Location(welt, x, y, z + distance));
		welt.strikeLightning(new Location(welt, x + distance, y, z + distance));
		welt.strikeLightning(new Location(welt, x + distance, y, z - distance));
		welt.strikeLightning(new Location(welt, x - distance, y, z + distance));
		welt.strikeLightning(new Location(welt, x - distance, y, z));
		welt.strikeLightning(new Location(welt, x, y, z - distance));
		welt.strikeLightning(new Location(welt, x - distance, y, z - distance));
	}
	
	public boolean troll(String[] args) {
		if(args.length == 1) {
		} else if(args.length >= 2) {
			Player p = getServer().getPlayer(args[1]);
			if(p != null) {
				if(args.length == 2) {
					if(args[0].equalsIgnoreCase("KeyTroll")) {
						KeyTroll(p);
						return true;
					} else if(args[0].equalsIgnoreCase("Blitz")) {
						trolli(p);
						return true;
					}
				} else if(args.length >= 3) {
					if(args[0].equalsIgnoreCase("write")) {
						String text = "";
						for(int i = 2; i < args.length; i++) {
							text += (args[i] + " ");
						}
						write(p, text);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void KeyTroll(Player spieler) {
		Nerv Task = new Nerv(spieler);
		Task.runTaskTimer(this, 10, 10);
	}
	
	public void write(Player spieler, String text) {
		if(!text.startsWith("/")) {
			spieler.chat(text);
		}
	}
	
	public boolean contains(String[] List, String docontain) {
		for(String wort : List) {
			if(wort.equalsIgnoreCase(docontain)) {
				return true;
			}
		}
		return false;
	}
}
