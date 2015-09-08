package com.pat.tictacktoe;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.pat.tictacktoe.commands.ChangeName;
import com.pat.tictacktoe.commands.GetYaw;
import com.pat.tictacktoe.commands.Start;
import com.pat.tictacktoe.commands.Surrender;
import com.pat.tictacktoe.events.blocks.BlockBreak;
import com.pat.tictacktoe.events.blocks.BlockPlace;
import com.pat.tictacktoe.events.player.PlayerJoin;
import com.pat.tictacktoe.events.player.PlayerMove;
import com.pat.tictacktoe.events.player.PlayerPunch;
import com.pat.tictacktoe.events.player.PlayerQuit;
import com.pat.tictacktoe.threads.Counting;


@SuppressWarnings("unused")
public class TicTacToe extends JavaPlugin {

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		registerCommands();
		configFile();
		registerEvents();
		startCounting();
		
		logger.info(pdfFile.getName() + " has been enabled. Version. " + pdfFile.getVersion() + ".");
	}
	
	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		logger.info(pdfFile.getName() + " has been disabled. Version. " + pdfFile.getVersion() + ".");
	}
	
	
	private void configFile() {
		
		
	}

	
	private void registerCommands() {
		getCommand("start").setExecutor(new Start());
		getCommand("yaw").setExecutor(new GetYaw());
		getCommand("name").setExecutor(new ChangeName());
		getCommand("ff").setExecutor(new Surrender());
	}

	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerPunch(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new BlockBreak(), this);
		pm.registerEvents(new BlockPlace(), this);
		
	}
	
	@SuppressWarnings("deprecation")
	
	public void startCounting(){
		//String message21 = ("say ===========================");
		//Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message21);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Counting(), 50, 50);
	}
	

}
	
	
	
