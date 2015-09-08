package com.pat.tictacktoe.utils;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class Teleport {
	
	public static Location tp(Player player) {
		Location location = player.getLocation();
		Location loc = new Location(location.getWorld(),(int) location.getX()+ 0.5, location.getY() + 10,(int) location.getZ()+ 0.5,(float) 90,(float) 55);

		
		return loc;	
	}
	
	public static Location tpTarget(Player player) {
		Location location = player.getLocation();
		Location loc = new Location(location.getWorld(),(float) location.getX() - 4, location.getY(), location.getZ(),(float) -90,(float) 55);
		return loc;	
	}
	
}
