package com.Tigerwurm.Rpg;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Manager extends JavaPlugin {
	
	Entity ent;
	DialogManager d;
	
	@Override
	public void onEnable() {
		d = new DialogManager(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(command.getName().equalsIgnoreCase("hier")) {
				if(args == null || args.length<=0) return false;
				if(Integer.valueOf(args[0]) == 1) {
					p.sendMessage("Wird gemacht!");
					String[][] dialog = {{"Hallo", "Ich bin die Kuh Berta", "Es freut mich dich zu sehen"}, {"Dies ist mein zweiter Dialog!", "Aufregend nicht war?"}};
					Cow kuh = (Cow) p.getWorld().spawnEntity(p.getLocation(), EntityType.COW);
					kuh.setCustomName("Berta");
					kuh.setCustomNameVisible(true);
					DialogManager.newDialog(kuh, dialog, "Hey was geht!");
					ent = kuh;
					new Shop(ent, this);
					return true;
				} else if(Integer.valueOf(args[0]) == 2) {
					d.setDialog(p, ent, 1);
					return true;
				}
			
			}
		}
		return false;
	}
}
