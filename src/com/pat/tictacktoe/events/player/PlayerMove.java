package com.pat.tictacktoe.events.player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import com.pat.tictacktoe.handlers.StartGame;
import com.pat.tictacktoe.handlers.Status;

public class PlayerMove implements Listener {
	
	@EventHandler
	public void onPlayerLogin(PlayerMoveEvent event){
		Player player = event.getPlayer();
		if(Status.isInGame(player)){
			StartGame.stayLoc(player);
		}
	}
}

