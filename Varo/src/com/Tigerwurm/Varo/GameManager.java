package com.Tigerwurm.Varo;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.PortalCreateEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


public class GameManager extends JavaPlugin implements Listener {
	
	Border border;
	public static HashMap<UUID, Integer> Tries;
	int[] zahlenwerte = {60, 30, 20, 10, 5, 4, 3, 2, 1};
	int zeitbiskick = 60 * 15 * 25;
	static int phase;
	
	@Override
	public void onEnable() {
		phase = 0;
		getServer().getPluginManager().registerEvents(this, this);
		Tries = new HashMap<>();
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equals("varo")) {
			if(sender instanceof Player && sender.isOp()) {
				Player player = (Player) sender;
				if(args.length == 3) {
					if(args[0].equals("start")) {
						startGame(player.getLocation(), Integer.valueOf(args[1]), Double.valueOf(args[2]));
					}
				}
				
			} else {
				
			}
		}
		return false;
	}
	
	public void onprePhase() {
	}
	
	public void startGame(Location loc, int dicke, double schnelle) {
		getServer().broadcastMessage("Start");
		new Timer("Varo beginnt in time Sekunden.", zahlenwerte, 61, null, this);
		getServer().getScheduler().runTaskLater(this, new Zeitsager("Varo beginnt.", getServer()), 61 * 25);
		Collection<? extends Player> players = getServer().getOnlinePlayers();
		phase = 1;
		border = new Border(loc, dicke, schnelle, this);
		getServer().setWhitelist(true);
		getServer().getWhitelistedPlayers().addAll(players);
		for(Player p : players) {
			Tries.put(p.getUniqueId(), 2);
			kicker(p);
			p.getInventory().clear();
		}
		getServer().getScheduler().runTaskLater(this, new Hochsetzer(this), (3600 * 24) * 25);
		getServer().getScheduler().runTaskLater(this, new PhaseAendern(2, getServer()), 61 * 25);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onJoin(PlayerJoinEvent e) {
		if(phase == 2) {
			Player player = e.getPlayer();
			UUID uuuidplayer = player.getUniqueId();
			BukkitScheduler sch = getServer().getScheduler();
			if(Tries.get(uuuidplayer) <= 0) {
				player.kickPlayer("Du darfst momentan nicht spielen, versuche es später wieder.");
				return;
			}
			Tries.put(uuuidplayer, Tries.get(uuuidplayer) - 1);			
			player.sendMessage("Hallo " + player.getDisplayName() + ", du hast 10 Sekunden Schutzzeit.");
			player.setInvulnerable(true);
			sch.runTaskLater(this, new Runnable() {

				@Override
				public void run() {
					Player p = getServer().getPlayer(uuuidplayer);
					p.setInvulnerable(false);
					p.sendMessage("Deine Schutzzeit ist abgelaufen, in 15 Minuten wirst du gekickt.");
				}
				
			}, 10*25);
			
			kicker(player);
		}
	}
	
	public void kicker(Player player) {
		new Timer(player.getDisplayName() + " hat noch time Sekunden Zeit bevor er gekickt wird.", zahlenwerte, zeitbiskick, null, this);
		new AutoKicker("Deine Zeit ist abgelaufen. Du darfst noch " + Tries.get(player.getUniqueId()) + " Mal joinen.", player, this, zeitbiskick);
	}
	
	public void setzeTriesHoch() {
		Collection<OfflinePlayer> players = getServer().getWhitelistedPlayers();
		for(OfflinePlayer p : players) {
			int neueTries = Tries.get(p.getUniqueId()) + 1;
			Tries.put(p.getUniqueId(), neueTries);
			if(neueTries >= 4) {
				getServer().getWhitelistedPlayers().remove(p);
			}
		}
	}
	
	@EventHandler
	public void onPortalCreate(PortalCreateEvent e) {
		if(e.getReason() == PortalCreateEvent.CreateReason.FIRE && phase == 2) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		if(phase == 2) {
			Player p = e.getEntity();
			p .kickPlayer("Du bist gestorben.\nDu bist jetzt aus Varo ausgeschieden.");
			getServer().getWhitelistedPlayers().remove(p);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onabbau(BlockBreakEvent e) {
		if(phase == 1) {
			e.setCancelled(true);
		}
	}
}
