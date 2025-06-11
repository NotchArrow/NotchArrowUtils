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

				if (playerInventory.getStack(client.player.getInventory().getSelectedSlot()).getDamage() > heldDamage
						&& heldDamage > playerInventory.getStack(client.player.getInventory().getSelectedSlot()).getMaxDamage() * durabilityPercentage
						&& (System.currentTimeMillis() - heldWarningTime) > ConfigManager.config.tickregistryDurabilityWarningsTime * 1000L) {
					client.player.playSound(SoundEvents.BLOCK_ANVIL_USE, Float.MAX_VALUE, 1.0F);
					client.inGameHud.setTitle(TextFormat.styledText("Item Durability Low!"));
					heldWarningTime = System.currentTimeMillis();
				}

				if (
						((playerInventory.getStack(36).getDamage() > bootDamage
						&& bootDamage > playerInventory.getStack(36).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getStack(37).getDamage() > leggingDamage
						&& leggingDamage > playerInventory.getStack(37).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getStack(38).getDamage() > chestplateDamage
						&& chestplateDamage > playerInventory.getStack(38).getMaxDamage() * durabilityPercentage) ||
						(playerInventory.getStack(39).getDamage() > helmetDamage
						&& helmetDamage > playerInventory.getStack(39).getMaxDamage() * durabilityPercentage))
						&& (System.currentTimeMillis() - armorWarningTime) > ConfigManager.config.tickregistryDurabilityWarningsTime * 1000L) {
					client.player.playSound(SoundEvents.BLOCK_ANVIL_USE, Float.MAX_VALUE, 1.0F);
					client.inGameHud.setTitle(TextFormat.styledText("Armor Durability Low!"));
					armorWarningTime = System.currentTimeMillis();
				}

				heldDamage = playerInventory.getStack(client.player.getInventory().getSelectedSlot()).getDamage();
				bootDamage = playerInventory.getStack(36).getDamage();
				leggingDamage = playerInventory.getStack(37).getDamage();
				chestplateDamage = playerInventory.getStack(38).getDamage();
				helmetDamage = playerInventory.getStack(39).getDamage();
			}
		});
	}
}
