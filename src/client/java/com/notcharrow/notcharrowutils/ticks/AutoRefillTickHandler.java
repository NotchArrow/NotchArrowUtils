package com.notcharrow.notcharrowutils.ticks;

import com.notcharrow.notcharrowutils.config.ConfigManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.SlotActionType;

public class AutoRefillTickHandler {
	public static Item previousHeld = Items.AIR;
	private static int previousSlot = -1;

	public static void register() {
		ClientTickEvents.START_CLIENT_TICK.register(client -> {
			if (client.player != null && client.interactionManager != null && ConfigManager.config.tickregistryAutoRefill) {
				if (client.player.getInventory().getStack(client.player.getInventory().getSelectedSlot()).isEmpty()
					&& client.player.getInventory().getSelectedSlot() == previousSlot
					&& client.currentScreen == null) {

					int currentSlot = client.player.getInventory().getEmptySlot();
					int moreItemsSlot = -1;

					for (int i = 0; i < client.player.getInventory().size(); i++) {
						ItemStack stack = client.player.getInventory().getStack(i);
						if (!stack.isEmpty() && stack.getItem() == previousHeld) {
							moreItemsSlot = i;
							break;
						}
					}

					if (moreItemsSlot != -1) {
						if (moreItemsSlot < 9 && moreItemsSlot != currentSlot) {
							client.player.getInventory().setSelectedSlot(moreItemsSlot);
						} else if (moreItemsSlot >= 9) {
							swapItemToHotbar(client, moreItemsSlot, currentSlot);
						}
					}
				}

				previousHeld = client.player.getInventory().getStack(client.player.getInventory().getSelectedSlot()).getItem();
				previousSlot = client.player.getInventory().getSelectedSlot();
			}
		});
	}

	private static void swapItemToHotbar(MinecraftClient client, int fromSlot, int toSlot) {
		if (client.interactionManager != null && client.player != null) {
			client.interactionManager.clickSlot(client.player.currentScreenHandler.syncId, fromSlot, toSlot, SlotActionType.SWAP, client.player);
		}
	}
}