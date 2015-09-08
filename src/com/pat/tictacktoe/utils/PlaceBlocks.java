package com.pat.tictacktoe.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.handlers.Status;

public class PlaceBlocks {

	public static void arena(Player player, Player target) {
		

		board(player, Material.IRON_BLOCK, Material.GLASS);

	}
	
	public static void resetBoard(Player player){
		Player p = Status.firstPlayer(player);
		board(p,Material.IRON_BLOCK,Material.GLASS);
	}
	
	public static void board(Player player ,Material a, Material b){
		Location location2 = player.getLocation();
		Location target = new Location(location2.getWorld(), location2.getX() - 4, location2.getY(), location2.getZ(),(float) -90,(float) 55);
		for(int x = 1; x <= 3;x++){
			for(int z = -1; z <= 1;z++){
				Location board = new Location(location2.getWorld(), location2.getX() - x, location2.getY() - 2, location2.getZ()+ z);
				board.getBlock().setType(a);
			}
		}
		
		floor(location2, b);
		floor(target, b);
	}
	
	public static void floor(Location l, Material m){
		Location floor = new Location(l.getWorld(), l.getX(), l.getY() - 1, l.getZ());
		floor.getBlock().setType(m);
	}
}