package com.notcharrow.notcharrowutils.keybinds;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import com.notcharrow.notcharrowutils.ticks.AutoRefillTickHandler;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

import java.util.List;
import java.util.function.BooleanSupplier;

public class HotbarCycleKeybinds {
	private static long lastCycleTime = 0;
	private static final List<BooleanSupplier> slotLocks = List.of(
			() -> ConfigManager.config.hotbarCyclingLockSlot1,
			() -> ConfigManager.config.hotbarCyclingLockSlot2,
			() -> ConfigManager.config.hotbarCyclingLockSlot3,
			() -> ConfigManager.config.hotbarCyclingLockSlot4,
			() -> ConfigManager.config.hotbarCyclingLockSlot5,
			() -> ConfigManager.config.hotbarCyclingLockSlot6,
			() -> ConfigManager.config.hotbarCyclingLockSlot7,
			() -> ConfigManager.config.hotbarCyclingLockSlot8,
			() -> ConfigManager.config.hotbarCyclingLockSlot9
	);

	public static void register() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {

			if (client.interactionManager != null && client.player != null) {
				if (KeybindRegistry.hotbarCycleDownKeybind.isPressed()
						&& (System.currentTimeMillis() - lastCycleTime) > 200) {

					if (ConfigManager.config.tickregistryAutoRefill) {
						AutoRefillTickHandler.previousHeld = Items.AIR;
					}

					for (int i = 0; i < 9; i++) {
						if (!slotLocks.get(i).getAsBoolean()) {
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 9 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 18 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 27 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 36 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 9 + i, 0, SlotActionType.PICKUP, client.player);
						}
					}
					lastCycleTime = System.currentTimeMillis();
				}

				if (KeybindRegistry.hotbarCycleUpKeybind.isPressed()
						&& (System.currentTimeMillis() - lastCycleTime) > 200) {

					if (ConfigManager.config.tickregistryAutoRefill) {
						AutoRefillTickHandler.previousHeld = Items.AIR;
					}

					for (int i = 0; i < 9; i++) {
						if (!slotLocks.get(i).getAsBoolean()) {
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 9 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 36 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 27 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 18 + i, 0, SlotActionType.PICKUP, client.player);
							client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, 9 + i, 0, SlotActionType.PICKUP, client.player);
						}
					}
					lastCycleTime = System.currentTimeMillis();
				}
			}
		});
	}
}
