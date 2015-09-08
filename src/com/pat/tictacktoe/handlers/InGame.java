package com.pat.tictacktoe.handlers;

import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InGame {

	private static HashMap<Player, Boolean> isTurn = new HashMap<Player, Boolean>();
	private static HashMap<Player, Material> isColor = new HashMap<Player, Material>();
	private static HashMap<Player, DyeColor> isWinning = new HashMap<Player, DyeColor>();
	private static HashMap<Player, Integer> score = new HashMap<Player, Integer>();
	private static HashMap<Player, Integer> scoreTo = new HashMap<Player, Integer>();

	public static void goesFirst(Player player, Player target) {
		int rand = (int) (Math.random() * 2 + 1);
		if (rand == 1) {
			player.getInventory().clear();
			target.getInventory().clear();
			isTurn.put(player, true);
			isColor.put(player, Material.REDSTONE_BLOCK);
			isWinning.put(player, DyeColor.RED);
			isWinning.put(target, DyeColor.YELLOW);
			player.setItemInHand(new ItemStack(Material.REDSTONE));
			isColor.put(target, Material.GOLD_BLOCK);
			target.setItemInHand(new ItemStack(Material.GOLD_INGOT));
			String message = ("say " + player.getName() + " is going first!");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		} else {
			isTurn.put(target, true);
			isColor.put(player, Material.GOLD_BLOCK);
			player.setItemInHand(new ItemStack(Material.GOLD_INGOT));
			isColor.put(target, Material.REDSTONE_BLOCK);
			target.setItemInHand(new ItemStack(Material.REDSTONE));
			String message = ("say " + target.getName() + " is going first!");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		}
	}

	public static void addPlayer(Player player, Player target) {
		isTurn.put(player, false);
		isTurn.put(target, false);
	}

	public static void setTo(Player player, Integer num) {
		Player target = Status.findTarget(player);
		scoreTo.put(player, num);
		scoreTo.put(target, num);
	}
	
	public static void noTurn(Player player) {
		Player target = Status.findTarget(player);
		isTurn.put(player, false);
		isTurn.put(target, false);
	}


	public static Integer getTo(Player player) {
		return scoreTo.get(player);
	}

	public static Integer getShow(Player player) {
		return score.get(player);
	}

	public static DyeColor getWinning(Player player) {
		return isWinning.get(player);
	}

	public static void nextTurn(Player player) {
		Player target = Status.findTarget(player);
		isTurn.put(player, false);
		isTurn.put(target, true);
	}

	public static void addScore(Player player, Player target) {
		int num = 0;
		score.put(player, num);
		score.put(target, num);
	}

	public static void updateScore(Player player) {
		score.put(player, score.get(player) + 1);
	}

	public static boolean showScore(Player target2) {

		Player player = Status.firstPlayer(target2);

		Player target = Status.findTarget(player);

		String message = ("say " + player.getName() + ": " + score.get(player));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message);
		String message2 = ("say " + target.getName() + ": " + score.get(target));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message2);

		if (score.get(target2) >= scoreTo.get(target2)) {
			Status.endGame(target2);
			String message4 = ("say ===========================");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message4);
			String message5 = ("say " + target2.getName() + "HAS WON THE GAME!!!");
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message5);
			return true;
		}
		return false;

	}

	public static boolean turnCheck(Player player) {
		return isTurn.get(player);
	}

	public static boolean checkForDraw(Player target2) {

		Player player = Status.firstPlayer(target2);

		Location location = player.getLocation();

		for (int x = 1; x <= 3; x++) {
			for (int z = -1; z <= 1; z++) {
				Location board = new Location(location.getWorld(), location.getX() - x, location.getY() - 2,
						location.getZ() + z);
				player.sendMessage("Block: " + board.getBlock().getType());
				if (board.getBlock().getType() == Material.IRON_BLOCK) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	public static boolean checkForWin(Player target2) {


		Player player = Status.firstPlayer(target2);

		Location location = player.getLocation();
		Location winning[] = new Location[3];
		// Material[] matList =
		// {Material.ANVIL,Material.ARMOR_STAND,Material.BROWN_MUSHROOM};
		Material[] listArray = new Material[3];
		player.sendMessage("test");
		for (int x = 1; x <= 3; x++) {
			for (int z = -1; z <= 1; z++) {
				Location board = new Location(location.getWorld(), location.getX() - x, location.getY() - 2,location.getZ() + z);
				winning[z + 1] = board;
				listArray[z + 1] = board.getBlock().getType();
				if (listArray[0] == listArray[1] && listArray[1] == listArray[2]
						&& listArray[0] == colorCheck(target2)) {
					for (int i = 0; i < 3; i++) {
						winning[i].getBlock().setType(Material.WOOL);
						winning[i].getBlock().setData(isWinning.get(target2).getData());
						player.sendMessage("test1");
					}
					player.sendMessage("test2");
					return true;
				}
			}
			Arrays.fill(listArray, null);
		}
		for (int z = -1; z <= 1; z++) {
			for (int x = 1; x <= 3; x++) {
				Location board = new Location(location.getWorld(), location.getX() - x, location.getY() - 2,
						location.getZ() + z);
				winning[x - 1] = board;
				listArray[x - 1] = board.getBlock().getType();
				if (listArray[0] == listArray[1] && listArray[1] == listArray[2]
						&& listArray[0] == colorCheck(target2)) {
					for (int i = 0; i < 3; i++) {
						winning[i].getBlock().setType(Material.WOOL);
						winning[i].getBlock().setData(isWinning.get(target2).getData());
					}
					return true;
				}
			}
			Arrays.fill(listArray, null);
		}
		int z2 = -1;
		for (int x = 1; x <= 3; x++) {
			Location board = new Location(location.getWorld(), location.getX() - x, location.getY() - 2,
					location.getZ() + z2);
			winning[z2 + 1] = board;
			listArray[z2 + 1] = board.getBlock().getType();
			if (listArray[0] == listArray[1] && listArray[1] == listArray[2] && listArray[0] == colorCheck(target2)) {
				for (int i = 0; i < 3; i++) {
					winning[i].getBlock().setType(Material.WOOL);
					winning[i].getBlock().setData(isWinning.get(target2).getData());
				}
				return true;
			}
			z2++;
		}
		Arrays.fill(listArray, null);
		int x1 = 1;
		for (int z = 1; z >= -1; z--) {
			Location board = new Location(location.getWorld(), location.getX() - x1, location.getY() - 2,
					location.getZ() + z);
			winning[z + 1] = board;
			listArray[z + 1] = board.getBlock().getType();
			if (listArray[0] == listArray[1] && listArray[1] == listArray[2] && listArray[0] == colorCheck(target2)) {
				for (int i = 0; i < 3; i++) {
					winning[i].getBlock().setType(Material.WOOL);
					winning[i].getBlock().setData(isWinning.get(target2).getData());
				}
				return true;
			}
			x1++;
		}
		return false;
	}

	public static Material colorCheck(Player player) {
		return isColor.get(player);
	}

}
