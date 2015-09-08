package com.pat.tictacktoe.events.blocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.pat.tictacktoe.handlers.Status;

public class BlockPlace implements Listener{

	@EventHandler
	public void onPlayerBlockBreak(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(!(Status.isInGame(player))){
			return;
		}
		event.setCancelled(true);
	}
}
