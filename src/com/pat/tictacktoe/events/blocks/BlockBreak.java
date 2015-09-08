package com.pat.tictacktoe.events.blocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.pat.tictacktoe.handlers.Status;

public class BlockBreak implements Listener{

	@EventHandler
	public void onPlayerBlockBreak(BlockBreakEvent event){
		Player player = event.getPlayer();
		
		if(Status.isInGame(player)){
			event.setCancelled(true);
		}

	}
}
