package com.raphfrk.bukkit.serverportsimpleportal;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.raphfrk.bukkit.eventlinkapi.EventLinkAPI;
import com.raphfrk.bukkit.serverportcoreapi.ServerPortCoreAPI;

public class SimplePortal extends JavaPlugin {
	
	public static Server server;
	public static Logger log;

	public EventLinkAPI eventLink;
	public ServerPortCoreAPI serverPortCore;
	public PluginDescriptionFile pdfFile;
	public SimplePortalPlayerListener playerListener = new SimplePortalPlayerListener(this);
	
	public PortalManager portalManager = new PortalManager(this);
	PluginManager pm;
	ServicesManager sm;
	
	File pluginDirectory;
	
	public void onDisable() {
		
	}

	public void onEnable() {
		
		server = getServer();
		pm = server.getPluginManager();
		sm = server.getServicesManager();
		
		log = server.getLogger();
		
		if(!pluginDirectory.exists()) {
			pluginDirectory.mkdirs();
		}
		
		portalManager.readPortals();
		
		pm.registerEvent(Type.PLAYER_MOVE, playerListener, Priority.Normal, this);
		
	}
	
	public static void log(String message) {
		log.info("[SimplePortal]: " + message);
	}
}