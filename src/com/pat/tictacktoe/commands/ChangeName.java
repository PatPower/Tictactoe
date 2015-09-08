package com.pat.tictacktoe.commands;

import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChangeName implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(args.length == 2)){
			sender.sendMessage("Type someone's name and the name you want to change to.");
		}
		
		Player target = Bukkit.getServer().getPlayer(args[0]);
		
		if(target == null){
			sender.sendMessage(args[0] + " is not online");
			return false;
		}
		
		
		target.setDisplayName(args[1]);
		target.setPlayerListName(args[1]);
		target.setCustomName(args[1]);
		
		sender.sendMessage("Name set");
		return false;
	}

}
