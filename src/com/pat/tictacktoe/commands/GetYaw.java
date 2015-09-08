package com.pat.tictacktoe.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
//import org.bukkit.entity.Player;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.handlers.InGame;
import com.pat.tictacktoe.handlers.Status;

public class GetYaw implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		String a;
		String b;
		
		Player player = (Player) sender;
	//	Status.addPlayer(player);
 		
		sender.sendMessage("Length" + Status.length());
		
		if (InGame.turnCheck(player)){
			a = "true";
		}else{
			a = "false";
		}
		sender.sendMessage("1");
		Player target = Status.findTarget(player);
		
		if (InGame.turnCheck(target)){
			b = "true";
		}else{
			b = "false";
		}
		sender.sendMessage("2");
		sender.sendMessage(player.getName() +  " = " + a);
		sender.sendMessage(target.getName() +  " = " + b);
		sender.sendMessage(Status.firstPlayer(player).getName() +  " is first ");
		sender.sendMessage(Status.firstPlayer(target).getName() +  " is first ");
		sender.sendMessage(InGame.getShow(player) + " = score " + player.getName() );
		sender.sendMessage(InGame.getTo(player) + " = to " + player.getName());
		sender.sendMessage(InGame.getShow(target) + " = score " + player.getName());
		sender.sendMessage(InGame.getTo(target) + " = to " + player.getName());
		return false;
	}

}
