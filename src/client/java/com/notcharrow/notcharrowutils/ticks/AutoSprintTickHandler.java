package com.notcharrow.notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import com.notcharrow.notcharrowutils.config.ConfigManager;

public class AutoSprintTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryAutoSprint && client.player != null
					&& client.player.forwardSpeed > 0 && !client.player.isSprinting()) {
				client.player.setSprinting(true);
			}
		});
	}
}
