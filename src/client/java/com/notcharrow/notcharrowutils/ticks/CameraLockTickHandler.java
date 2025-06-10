package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.concurrent.atomic.AtomicReference;

public class CameraLockTickHandler {
	public static void register() {
		AtomicReference<Float> yaw = new AtomicReference<>((float) 0);
		AtomicReference<Float> pitch = new AtomicReference<>((float) 0);
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {


				if (!ConfigManager.config.tickregistryCameraLock) {
					yaw.set((float) 0);
					pitch.set((float) 0);
				} else if (yaw.get() == 0 && pitch.get() == 0) {
					yaw.set(client.player.getYaw());
					pitch.set(client.player.getPitch());
				}

				if (ConfigManager.config.tickregistryCameraLock) {
					client.player.setPitch(pitch.get());
					client.player.setYaw(yaw.get());
				}
			}
		});
	}
}