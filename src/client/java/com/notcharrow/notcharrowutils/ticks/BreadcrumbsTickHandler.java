package com.notcharrow.notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import com.notcharrow.notcharrowutils.config.ConfigManager;

import java.util.ArrayList;
import java.util.List;

public class BreadcrumbsTickHandler {
	public static void register() {
		List<Vec3d> particlePositions = new ArrayList<>();
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryBreadcrumbs && client.world != null && client.player != null) {
				Vec3d playerPos = client.player.getPos();

				if (client.player.isOnGround()) {
					boolean tooConcentrated = false;
					for (Vec3d pos : particlePositions) {
						if (playerPos.isInRange(pos, ConfigManager.config.tickregistryBreadcrumbsMinimumSpacing)) {
							tooConcentrated = true;
						}
					}
					if (!tooConcentrated) {
						particlePositions.add(playerPos);
					}
				}

				for (Vec3d pos : particlePositions) {
					if (playerPos.isInRange(pos, ConfigManager.config.tickregistryBreadcrumbsViewDistance)) {
						MinecraftClient.getInstance().particleManager.addParticle(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 0, 0, 0);
					}
				}

			} else {
				particlePositions.clear();
			}
		});
	}
}
