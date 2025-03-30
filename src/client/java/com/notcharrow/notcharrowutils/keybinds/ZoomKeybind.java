package com.notcharrow.notcharrowutils.keybinds;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ZoomKeybind {
	private static boolean zooming = false;
	private static int originalFov;
	private static double originalSensitivity;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (KeybindRegistry.zoomKeybind.isPressed()) {
				if (!zooming) {
					originalFov = client.options.getFov().getValue();
					originalSensitivity = client.options.getMouseSensitivity().getValue();
				}
				zooming = true;

				client.options.smoothCameraEnabled = true;
				client.options.getFov().setValue(30);
				client.options.getMouseSensitivity().setValue(originalSensitivity * (ConfigManager.config.mixinZoomSensitivityFactor * 0.01));
			} else if (zooming) {
				zooming = false;
				client.options.smoothCameraEnabled = false;
				client.options.getFov().setValue(originalFov);
				client.options.getMouseSensitivity().setValue(originalSensitivity);
			}
		});
	}
}
