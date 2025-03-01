package com.notcharrow.notcharrowutils.ticks;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import com.notcharrow.notcharrowutils.config.ConfigManager;

public class AutoAttackTickHandler {
	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryAutoAttack && client.player != null && client.crosshairTarget != null) {
				if (client.crosshairTarget.getType() == HitResult.Type.ENTITY) {
					EntityHitResult hitResult = (EntityHitResult) client.crosshairTarget;
					Entity target = hitResult.getEntity();

					if (target != null && !target.isRemoved() && (target instanceof HostileEntity || ConfigManager.config.tickregistryAutoAttackPassive)) {
						ClientPlayerEntity player = client.player;

						if (player.getAttackCooldownProgress(1.0f) >= 1.0f) {
							client.interactionManager.attackEntity(player, target);
							player.swingHand(Hand.MAIN_HAND);
						}
					}
				}
			}
		});
	}
}
