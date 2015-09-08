package com.pat.tictacktoe.events.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

import com.pat.tictacktoe.handlers.InGame;
import com.pat.tictacktoe.handlers.Status;
import com.pat.tictacktoe.threads.Counting;

public class PlayerPunch implements Listener {

	@EventHandler
	public boolean onPlayerPunch(BlockDamageEvent event) {
		Player player = event.getPlayer();
		if (event.getBlock().getType() == Material.IRON_BLOCK) {
			if (Status.isInGame(player)) {

				if (InGame.turnCheck(player)) {

					event.getBlock().setType(InGame.colorCheck(player));

					InGame.noTurn(player);
					if (InGame.checkForWin(player)) {


						InGame.updateScore(player);
						String message1 = ("say ===========================");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message1);
						String message3 = ("say " + player.getName() + " WINS!");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message3);

						if (InGame.showScore(player)) {
							return true;
						}
						Counting.start(player);
						return true;
					} else if (InGame.checkForDraw(player)) {
						String message1 = ("say ===========================");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message1);
						String message232 = ("say Its a draw!");
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message232);
						if (InGame.showScore(player)) {
						}


						Counting.start(player);
						return true;
					}else{
						InGame.nextTurn(player);
					}
					String message23 = ("say Its" + Status.findTarget(player).getName() + "'s Turn.");
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message23);

				} else {
					player.sendMessage("Its not your turn yet!");
				}
			}
		}
		return false;
	}
}
