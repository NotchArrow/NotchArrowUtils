package com.notcharrow.notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import com.notcharrow.notcharrowutils.config.ConfigManager;

public class NightVisionTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryNightVision && client.player != null) {
				client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, false, false, false));
				client.player.removeStatusEffect(StatusEffects.BLINDNESS);
				client.player.removeStatusEffect(StatusEffects.DARKNESS);
			} else if (client.player != null) {
				client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
			}
		});
	}
}