package halsband;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.List;
import java.util.ArrayList;

public class Halsband extends JavaPlugin {
	
	@Override
	public void onEnable() {
		addRecipe();
	}
	
	public void addRecipe() {
		
		List<String> liste = new ArrayList<String>();
		ItemStack stack =  new ItemStack(Material.LEASH);
		ItemMeta meta = stack.getItemMeta();
		liste.add("§Leash");
		meta.setLore(liste);
		meta.setDisplayName("§Halsband");
		stack.setItemMeta(meta);
		ShapedRecipe recipe = new ShapedRecipe(stack);
		recipe.shape("iii", "i!i", "iii");
		recipe.setIngredient('i', Material.DIAMOND);
		Bukkit.getServer().addRecipe(recipe);
	}

}
