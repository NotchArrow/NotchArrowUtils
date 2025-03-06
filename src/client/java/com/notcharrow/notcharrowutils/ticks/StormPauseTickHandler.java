package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import com.notcharrow.notcharrowutils.config.ConfigManager;

public class StormPauseTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryStormPause && client.player != null
					&& client.world != null && client.world.isThundering() && client.isInSingleplayer()) {
				client.player.sendMessage(TextFormat.styledText("Pausing to preserve thunderstorm time."), false);
				client.player.sendMessage(TextFormat.styledText("Feature has been automatically disabled. Press Escape to unpause."), false);

				client.openGameMenu(true);
				ConfigManager.config.tickregistryStormPause = false;
				ConfigManager.saveConfig();
			}
		});
	}
}