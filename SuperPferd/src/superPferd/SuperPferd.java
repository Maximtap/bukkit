package superPferd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SuperPferd extends JavaPlugin {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = null;
		
		if(sender instanceof Player) {
			player = (Player) sender;
			if(command.getName().equalsIgnoreCase("pferd") && player.isOp()) {
				Horse SuperPferd = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
				SuperPferd.setAdult();
				SuperPferd.setJumpStrength(1000);
				SuperPferd.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 5, false, false));
				SuperPferd.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 2, false, false));
				SuperPferd.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1, false, false));
				SuperPferd.setTamed(true);
				SuperPferd.setPassenger(player);
				return true;
			}
		}
		return false;
	}
}
