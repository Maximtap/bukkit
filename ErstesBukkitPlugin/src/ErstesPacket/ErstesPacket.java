package ErstesPacket;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ErstesPacket extends JavaPlugin {
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		System.out.println("Erstes Plugin geladen.");
		new Eventer(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		
		//falls sender ein PLayer ist machen wir den sender zum player
		Player player = null;
		if(sender instanceof Player){
			player = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("test")) {
			
			if(player == null) {
				String version = this.getDescription().getVersion();
				sender.sendMessage("Ja Test Erfolgreich!" + " Version: " + version);
				return true;
			} else {
				player.sendMessage("Toll " + player.getDisplayName()+ "!");
				return true;
			}
			
		}
		
		return false;
	}
	
}
