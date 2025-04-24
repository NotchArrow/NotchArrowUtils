package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.helper.TextFormat;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundEvents;

public class DurabilityWarningTickHandler {
	private static int heldDamage = 0;
	private static long heldWarningTime = 0;

	private static int helmetDamage = 0;
	private static int chestplateDamage = 0;
	private static int leggingDamage = 0;
	private static int bootDamage = 0;
	private static long armorWarningTime = 0;

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (ConfigManager.config.tickregistryDurabilityWarnings && client.player != null) {
				PlayerInventory playerInventory = client.player.getInventory();
				double durabilityPercentage = 1 - ConfigManager.config.tickregistryDurabilityWarningsPercentage * .01;

				if (playerInventory.getMainHandStack().getDamage() > heldDamage
						&& heldDamage > playerInventory.getMainHandStack().getMaxDamage() * durabilityPercentage
						&& (System.currentTimeMillis() - heldWarningTime) > ConfigManager.config.tickregistryDurabilityWarningsTime * 1000L) {
					client.player.playSound(SoundEvents.BLOCK_ANVIL_USE, Float.MAX_VALUE, 1.0F);
					client.inGameHud.setTitle(TextFormat.styledText("Item Durability Low!"));
					heldWarningTime = System.currentTimeMillis();
				}

				if (
						((playerInventory.getArmorStack(0).getDamage() > bootDamage
						&& bootDamage > playerInventory.getArmorStack(0).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getArmorStack(1).getDamage() > leggingDamage
						&& leggingDamage > playerInventory.getArmorStack(1).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getArmorStack(2).getDamage() > chestplateDamage
						&& chestplateDamage > playerInventory.getArmorStack(2).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getArmorStack(3).getDamage() > helmetDamage
						&& helmetDamage > playerInventory.getArmorStack(3).getMaxDamage() * durabilityPercentage))
						&& (System.currentTimeMillis() - armorWarningTime) > ConfigManager.config.tickregistryDurabilityWarningsTime * 1000L) {
					client.player.playSound(SoundEvents.BLOCK_ANVIL_USE, Float.MAX_VALUE, 1.0F);
					client.inGameHud.setTitle(TextFormat.styledText("Armor Durability Low!"));
					armorWarningTime = System.currentTimeMillis();
				}

				heldDamage = playerInventory.getMainHandStack().getDamage();
				bootDamage = playerInventory.getArmorStack(0).getDamage();
				leggingDamage = playerInventory.getArmorStack(1).getDamage();
				chestplateDamage = playerInventory.getArmorStack(2).getDamage();
				helmetDamage = playerInventory.getArmorStack(3).getDamage();
			}
		});
	}
}
