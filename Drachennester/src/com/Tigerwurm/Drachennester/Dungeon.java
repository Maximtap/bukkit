package com.Tigerwurm.Drachennester;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class Dungeon extends BlockPopulator {

	public void populate(World world, Random random, Chunk chunk) {
		int cX = chunk.getX() * 16;
		int cZ = chunk.getZ() * 16;
		int rX = cX + random.nextInt(10);
		int rZ = cZ + random.nextInt(10);
		world.generateTree(new Location(world, rX, (world.getHighestBlockAt(rX, rZ).getY() + 1), rZ), random.nextBoolean() ? TreeType.JUNGLE : TreeType.COCOA_TREE);
	}

}
