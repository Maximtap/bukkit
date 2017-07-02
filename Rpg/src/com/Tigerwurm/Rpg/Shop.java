package com.Tigerwurm.Rpg;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;

public class Shop implements Listener {
	
	Entity verkäufer;
	Manager manage;
	Inventory shop;
	
	public Shop(Entity e, Manager m) {
		this.verkäufer = e;
		this.manage =  m;
		this.shop = manage.getServer().createInventory(null, InventoryType.CHEST);
		
		m.getServer().getPluginManager().registerEvents(this, this.manage);	
		}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onShopOpen(PlayerInteractEntityEvent e) {
		openShop(e.getPlayer());
	}
	
	public void openShop(Player p) {
		p.openInventory(shop);
	}
}
