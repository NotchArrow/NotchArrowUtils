package com.notcharrow.notcharrowutils;

import com.notcharrow.notcharrowutils.keybinds.KeybindRegistry;
import com.notcharrow.notcharrowutils.keybinds.KeybindTickHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import com.notcharrow.notcharrowutils.commands.CommandRegistry;
import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.ticks.TickRegistry;

public class NotchArrowUtils implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Load configuration
		ConfigManager.loadConfig();

		// Register All Commands
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			CommandRegistry.registerCommands(dispatcher);
		});

		// Register All Keybinds
		KeybindRegistry.registerKeybinds();
		KeybindTickHandler.register();

		// Register All TickEvents
		TickRegistry.registerTicks();
	}
}