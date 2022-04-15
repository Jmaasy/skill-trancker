package com.example;

import javax.inject.Inject;
import com.example.config.SkillTrackerConfig;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ClientShutdown;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(name = "Skill Tracker")
public class SkillTrackerPlugin extends Plugin {

	@Inject
	private Client client;
	@Inject
	private SkillTrackerConfig config;

	private final SkillTrackerHandler skillTrackerHandler = new SkillTrackerHandler();

	@Provides
	SkillTrackerConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillTrackerConfig.class);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		GameState gameState = gameStateChanged.getGameState();

		if(gameState == GameState.LOGGED_IN) {
			skillTrackerHandler.resetTracked();
			skillTrackerHandler.setPlayer(client.getLocalPlayer());
		} else if(gameState == GameState.CONNECTION_LOST || gameState == GameState.LOGIN_SCREEN) {
			skillTrackerHandler.trackPlayer(config);
		}
	}

	@Subscribe
	public void onClientShutdown(ClientShutdown event)
	{
		event.waitFor(skillTrackerHandler.trackPlayerFuture(config));
	}
}
