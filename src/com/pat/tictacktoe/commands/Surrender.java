package com.pat.tictacktoe.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.handlers.Status;

public class Surrender implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(Status.isInGame(player)){
			Status.endGame(player);
			player.setHealth(20);
			Status.findTarget(player).setHealth(20);
			return true;
		}else{
			player.sendMessage("You have to be in a game");
			return false;
		}
	}

}
