package ErstesPacket;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Eventer implements Listener {
	
	public Eventer(JavaPlugin plugin) {
		
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerTod(PlayerDeathEvent event) {
		Player player = event.getEntity();
		player.sendMessage("Haha " + player.getDisplayName());
		player.sendMessage("Du bist aus " + player.getFallDistance() + " Höhe gefallen.");
	}

}
