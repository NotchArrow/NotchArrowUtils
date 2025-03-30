package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;

public class ProjectileTrailTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null && client.world != null && ConfigManager.config.tickregistryProjectileTrail) {
				BlockPos playerPos = client.player.getBlockPos();
				for (Entity entity:client.world.getEntities()) {
					if (entity instanceof ProjectileEntity) {
						BlockPos entityPos = entity.getBlockPos();
						if (entityPos.isWithinDistance(playerPos, ConfigManager.config.tickregistryProjectileTrailDistance)
								&& (entity.getVelocity().x != 0 || entity.getVelocity().z != 0)) {
							client.particleManager.addParticle(ParticleTypes.END_ROD, entityPos.getX(), entityPos.getY(), entityPos.getZ(), 0, 0, 0);
						}
					}
				}
			}
		});
	}
}