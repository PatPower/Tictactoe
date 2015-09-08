package com.pat.tictacktoe.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pat.tictacktoe.handlers.StartGame;
//import com.pat.tictacktoe.handlers.Status;
import com.pat.tictacktoe.handlers.Status;

public class Start implements CommandExecutor {
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return false;
		}
		Player player = (Player) sender;
		
		if (Status.isInGame(player)) {
			player.sendMessage(ChatColor.RED + "You are already in a game");
			return false;
		}

		if (args.length == 0) {
			player.sendMessage(ChatColor.RED + "/start (name) [amount of games]");
			return false;
		}
		if (args.length == 1) {
			player.sendMessage(ChatColor.RED + "/start (name) [amount of games]");
			return false;
		}
		if (args.length == 2) {

			Player target = Bukkit.getServer().getPlayer(args[0]);

			if (target == player) {
				player.sendMessage("You cant play with yourself");
				return false;
			}

			if (target == null) {
				player.sendMessage(args[0] + " is not online");
				return false;
			}
			int num = Integer.parseInt(args[1]);

			if (num == 0) {
				player.sendMessage("Must me more than one game");
			}

			StartGame.start(player, target, num);

			return true;
		}

		return false;
	}

}
