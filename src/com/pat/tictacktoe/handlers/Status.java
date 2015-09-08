package com.pat.tictacktoe.handlers;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.utils.PlaceBlocks;

public class Status {

	private static HashMap<UUID, Boolean> inGame = new HashMap<UUID, Boolean>();
	private static HashMap<Player, Integer> playing = new HashMap<Player, Integer>();

	private static int numOfGames = 0;

	public static void addPlayer(Player player) {
		UUID name = player.getUniqueId();
		if (inGame.containsKey(name)) {
			String message = "say Welcome Back! " + player.getName();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
			return;
		} else {
			inGame.put(name, false);
			String message = "say Welcome to the server " + player.getName();
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		}

	}

	public static void removePlayer(Player player) {
		UUID name = player.getUniqueId();
		inGame.remove(name);
	}

	public static Boolean isInGame(Player player) {
		UUID name = player.getUniqueId();
		return inGame.get(name);
	}

	public static int length() {
		return inGame.size();
	}

	public static void playing(Player player, Player target) {
		numOfGames++;
		playing.put(player, numOfGames);
		playing.put(target, numOfGames);
	}

	public static Player findTarget(Player player) {
		int team = playing.get(player);

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (playing.containsValue(team) && p != player) {
				return p;
			}
		}
		return null;
	}

	public static void endGame(Player player) {
		Player target = Status.findTarget(player);
		if (player.getLocation().getX() > target.getLocation().getX()) {
			PlaceBlocks.board(player, Material.AIR, Material.AIR);
		}else{
			PlaceBlocks.board(target, Material.AIR, Material.AIR);
		}
		Status.setStatus(player, false);
		Status.setStatus(target, false);
	}

	public static Player firstPlayer(Player target2){
		Player target = Status.findTarget(target2);
		Player player;
		
		if (target2.getLocation().getX() > target.getLocation().getX()) {
			player = target2;
		}else{
			player = target;
		}
		return player;
	}
	
	public static void setStatus(Player player, Boolean bool) {
		UUID name = player.getUniqueId();
		inGame.put(name, bool);
	}

}