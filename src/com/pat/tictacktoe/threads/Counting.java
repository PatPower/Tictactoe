package com.pat.tictacktoe.threads;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.pat.tictacktoe.handlers.InGame;
import com.pat.tictacktoe.handlers.Status;
import com.pat.tictacktoe.utils.PlaceBlocks;

public class Counting extends BukkitRunnable{

	private static int countDown;
	private static boolean switchA = false;
	private static Player player;
	
	public void run() {
		while(switchA){
			countDown = 4;
			String message21 = ("say Next Round starting in 4 seconds");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message21);
			for(;countDown >=0; countDown--){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			PlaceBlocks.resetBoard(player);
			InGame.nextTurn(player);
			String message1 = ("say ===========================");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message1);
			String message = ("say NEXT ROUND");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
			String message2 = ("say ===========================");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message2);
			String message23 = ("say Its" + Status.findTarget(player).getName() + "'s Turn.");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message23);
			stop();

		}
	}
	
	public static void start(Player p){
		player = p;
		switchA = true;
	}
	public static void stop(){
		switchA = false;
	}


}
