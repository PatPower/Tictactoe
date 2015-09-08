package com.pat.tictacktoe.handlers;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.utils.PlaceBlocks;
import com.pat.tictacktoe.utils.Teleport;

public class StartGame {

	// private static Location pLoc;
	// private static Location tLoc;
	// private static UUID pID;
	// private static UUID tID;

	private static HashMap<Player, Location> loc = new HashMap<Player, Location>();

	public static void start(Player player, Player target, Integer num) {

		player.sendMessage(player.getName() + " and " + target.getName());
		player.teleport(Teleport.tp(player));
		target.teleport(Teleport.tpTarget(player));

		setLoc(player, target);

		String message1 = ("say ===========================");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message1);
		String message = ("say GAME STARTED! (To " + num + " wins)");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		String message12 = ("say ===========================");
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message12);
		Status.setStatus(player, true);
		Status.setStatus(target, true);
		Status.playing(player, target);
		PlaceBlocks.arena(player, target);
		InGame.addPlayer(player, target);
		InGame.addScore(player, target);
		InGame.goesFirst(player, target);
		InGame.setTo(player, num);


	}

	public static void setLoc(Player player, Player target) {
		// pLoc = player.getLocation();
		// tLoc = target.getLocation();
		// pID = player.getUniqueId();
		// tID = target.getUniqueId();
		
		loc.put(player, player.getLocation());
		loc.put(target, target.getLocation());
	}
	

	public static void stayLoc(Player player) {
		if (player.getLocation().distance(loc.get(player)) > 0.1) {
			player.teleport(loc.get(player));
		}
	}
}
