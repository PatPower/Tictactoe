package com.pat.tictacktoe.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.pat.tictacktoe.handlers.Status;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		Status.addPlayer(player);
	}
}
